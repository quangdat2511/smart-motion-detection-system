package com.javaweb.model.dto;

import com.javaweb.model.response.MotionSearchResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotionDTO extends AbstractDTO<MotionSearchResponse>{
    private String latestMotionStatus;
}
