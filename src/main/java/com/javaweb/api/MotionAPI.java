package com.javaweb.api;

import com.javaweb.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/motion")
public class MotionAPI {

    @Autowired
    private MotionService motionService;
    @GetMapping("/status")
    public String getLatestMotionStatus() {
        return motionService.getLatestMotionStatus();
    }
}
