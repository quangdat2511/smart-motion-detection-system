package com.javaweb.model.response;

import com.javaweb.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class MotionSearchResponse extends AbstractDTO<MotionSearchResponse> {
    private Date time;
    private String motionType;
    private String image;
}
