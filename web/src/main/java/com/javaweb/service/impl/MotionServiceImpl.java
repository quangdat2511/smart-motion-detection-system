package com.javaweb.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.javaweb.enums.MotionType;
import com.javaweb.model.request.MotionRequestDTO;
import com.javaweb.model.response.MotionSearchResponse;
import com.javaweb.service.MotionService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class MotionServiceImpl implements MotionService {


    @Override
    public List<MotionSearchResponse> findAll(MotionRequestDTO motionDTO) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        // Tạo đối tượng phân trang: page bắt đầu từ 1, nhưng PageRequest.of() cần index từ 0
        Pageable pageable = PageRequest.of(motionDTO.getPage() - 1, motionDTO.getMaxPageItems());
        int limit = pageable.getPageSize();          // số phần tử mỗi trang
        int offset = (int) pageable.getOffset();     // vị trí bắt đầu

        // Query Firestore:
        // 1. Lọc theo deviceId
        // 2. Sắp xếp theo time giảm dần (nhất định phải có orderBy nếu muốn dùng offset)
        // 3. Giới hạn số kết quả (limit)
        // 4. Bỏ qua offset phần tử đầu
        CollectionReference motionRef = dbFirestore.collection("motion");
        Query query = motionRef
                .whereEqualTo("deviceId", motionDTO.getDeviceId())
                .orderBy("time", Query.Direction.DESCENDING)    // Sắp xếp theo time giảm dần
                .offset(offset)                           // Bỏ qua offset bản ghi đầu
                .limit(limit);                            // Lấy tối đa limit bản ghi

        // Thực thi và thu thập kết quả
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        List<MotionSearchResponse> resultList = new ArrayList<>();
        for (DocumentSnapshot doc : querySnapshot.get().getDocuments()) {
            resultList.add(doc.toObject(MotionSearchResponse.class));
        }
        for (MotionSearchResponse motion : resultList) {
            motion.setMotionType(MotionType.getMotionTypeName(motion.getMotionType()));
        }
        return resultList;
    }

    @Override
    public int countTotalItems(String deviceId) throws ExecutionException, InterruptedException {
        // 1. Lấy instance Firestore
        Firestore db = FirestoreClient.getFirestore();

        // 2. Truy vấn tất cả document trong collection "motion" có field "deviceId" khớp
        ApiFuture<QuerySnapshot> future = db.collection("motion")
                .whereEqualTo("deviceId", deviceId)
                .get();

        // 3. Chờ kết quả và lấy danh sách documents
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        // 4. Trả về số lượng document
        return documents.size();
    }

}
