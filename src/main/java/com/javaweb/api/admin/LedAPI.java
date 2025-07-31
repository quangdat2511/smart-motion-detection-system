package com.javaweb.api.admin;

import com.javaweb.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/led")
public class LedAPI {
    @Autowired
    private MqttService mqttService;

    @PostMapping("/{message}")
    public ResponseEntity<?> turnOnOrOffLed(@PathVariable String message) {
        if (!message.equalsIgnoreCase("on") && !message.equalsIgnoreCase("off")) {
            return ResponseEntity.badRequest().body("❌ Giá trị 'message' phải là 'on' hoặc 'off'");
        }
        mqttService.publishLedMessage(message.toLowerCase());
        return ResponseEntity.ok("✅ LED đã " + (message.equalsIgnoreCase("on") ? "bật" : "tắt"));
    }
}
