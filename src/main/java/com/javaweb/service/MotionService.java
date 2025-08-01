package com.javaweb.service;

import com.javaweb.model.dto.MotionDTO;
import com.javaweb.model.response.MotionSearchResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MotionService {
    List<MotionSearchResponse> findAll(MotionDTO motionDTO);
    int countTotalItems();
    String getLatestMotionStatus();
    void setLatestMotionStatus(String status);
}
