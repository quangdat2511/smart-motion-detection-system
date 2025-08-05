package com.javaweb.service;

import com.javaweb.model.dto.MotionDTO;
import com.javaweb.model.response.MotionSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MotionService {
    List<MotionSearchResponse> findAll(MotionDTO motionDTO) throws ExecutionException, InterruptedException;
    int countTotalItems(String deviceId) throws ExecutionException, InterruptedException;
    String getLatestMotionStatus();
    void setLatestMotionStatus(String status);
}
