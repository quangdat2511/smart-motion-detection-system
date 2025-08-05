package com.javaweb.converter;


import com.javaweb.entity.MotionEntity;
import com.javaweb.enums.MotionType;
import com.javaweb.model.response.MotionSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MotionConverter {
    @Autowired
    private ModelMapper modelMapper;

    public MotionSearchResponse toMotionSearchResponse(MotionEntity motionEntity){
        MotionSearchResponse motionSearchResponse = modelMapper.map(motionEntity, MotionSearchResponse.class);
        motionSearchResponse.setMotionType(MotionType.getMotionTypeName(motionEntity.getMotionType()));
        return motionSearchResponse;
    }
}
