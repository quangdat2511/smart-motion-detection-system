package com.javaweb.repository.custom;

import com.javaweb.entity.MotionEntity;
import com.javaweb.model.dto.MotionDTO;

import java.util.List;

public interface MotionRepositoryCustom {
    List<MotionEntity> findAll(MotionDTO motionDTO);
    int countTotalItems();
}
