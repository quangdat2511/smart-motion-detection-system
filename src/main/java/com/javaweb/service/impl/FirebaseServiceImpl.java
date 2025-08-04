package com.javaweb.service.impl;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.javaweb.model.dto.FirebaseDTO;
import com.javaweb.service.FirebaseService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class FirebaseServiceImpl implements FirebaseService {
    @Override
    public String create(FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = db.collection("crud_user").document(firebaseDTO.getDocumentId()).set(firebaseDTO);
        return future.get().getUpdateTime().toString();
    }

    @Override
    public String update(FirebaseDTO firebaseDTO) throws ExecutionException, InterruptedException {
        // Lấy document theo ID là name, nếu không tồn tại thì return thông báo
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference = dbFirestore.collection("crud_user").document(firebaseDTO.getDocumentId());
        ApiFuture<DocumentSnapshot> future = documentReference.get();
        DocumentSnapshot document = future.get();

        if (!document.exists()) {
            return "Document with name '" + firebaseDTO.getName() + "' not found.";
        }

        // Cập nhật document
        ApiFuture<WriteResult> collectionsApiFuture = documentReference.set(firebaseDTO);
        return "Update time: " + collectionsApiFuture.get().getUpdateTime();
    }


    @Override
    public FirebaseDTO get(String documentId) throws ExecutionException, InterruptedException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference docRef = dbFirestore.collection("crud_user").document(documentId);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();
        FirebaseDTO firebaseDTO = null;
        if (document.exists()) {
            firebaseDTO = document.toObject(FirebaseDTO.class);
            return firebaseDTO;
        }
        return firebaseDTO;
    }

    @Override
    public String delete(String documentId) {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future = dbFirestore.collection("crud_user").document(documentId).delete();
        return "Successfully deleted " + documentId;
    }
}
