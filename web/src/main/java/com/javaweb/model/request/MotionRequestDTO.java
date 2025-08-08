package com.javaweb.model.request;

import com.javaweb.model.dto.AbstractDTO;
import com.javaweb.model.response.MotionSearchResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotionRequestDTO extends AbstractDTO<MotionSearchResponse> {
    String deviceId;
}
