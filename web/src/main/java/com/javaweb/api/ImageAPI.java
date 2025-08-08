package com.javaweb.api;

import com.javaweb.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageAPI {
    @Autowired
    private MotionService motionService;
//    @GetMapping()
//    public String getImage() {
//        return motionService.getImage();
}
