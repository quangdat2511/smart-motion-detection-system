package com.javaweb.api;

import com.javaweb.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lcd")
public class LcdAPI {
    @Autowired
    private MqttService mqttService;

    @PostMapping()
    public ResponseEntity<?> displayMessageOnLcd(@RequestParam String message) {
        mqttService.publishLcdMessage(message);
        return ResponseEntity.ok("✅ LCD đã hiển thị: " + message);
    }

}
