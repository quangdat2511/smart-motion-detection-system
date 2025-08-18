package com.javaweb.model.dto;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MotionDTO {
    private String deviceId;
    private Date time;
    private String motionType;
    private String image;
}
