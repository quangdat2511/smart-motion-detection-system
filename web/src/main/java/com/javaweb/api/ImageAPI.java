package com.javaweb.api;

import com.javaweb.service.ImageService;
import com.javaweb.service.MotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/image")
public class ImageAPI {
    @Autowired
    private ImageService imageService;
    @GetMapping()
    public ResponseEntity<String> getLatestImage() {
        String filename = imageService.getLatestFilename();
        return ResponseEntity.ok(filename);
    }
}
