package com.javaweb.service;

import com.javaweb.model.dto.MotionDTO;
import com.javaweb.model.request.MotionRequestDTO;
import com.javaweb.model.response.MotionSearchResponse;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MotionService {
    List<MotionSearchResponse> findAll(MotionRequestDTO motionDTO) throws ExecutionException, InterruptedException;
    int countTotalItems(String deviceId) throws ExecutionException, InterruptedException;
    void addMotion(MotionDTO motionDTO) throws ExecutionException, InterruptedException;
    List<MotionDTO> getMotionsLastHour(String deviceId) throws ExecutionException, InterruptedException;
    List<MotionDTO> getMotions(String deviceId) throws ExecutionException, InterruptedException;
}
