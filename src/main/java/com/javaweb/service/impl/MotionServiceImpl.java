package com.javaweb.service.impl;

import com.javaweb.converter.MotionConverter;
import com.javaweb.entity.MotionEntity;
import com.javaweb.model.dto.MotionDTO;
import com.javaweb.model.response.MotionSearchResponse;
import com.javaweb.repository.MotionRepository;
import com.javaweb.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MotionServiceImpl implements MotionService {

    @Autowired
    private MotionRepository motionRepository;
    @Autowired
    private MotionConverter motionConverter;
    @Override
    public List<MotionSearchResponse> findAll(MotionDTO motionDTO) {
        List<MotionEntity> motionEntities = motionRepository.findAll(motionDTO);
        return motionEntities.stream().map(motionConverter::toMotionSearchResponse).collect(Collectors.toList());
    }
    @Override
    public int countTotalItems() {
        return motionRepository.countTotalItems();
    }
}
