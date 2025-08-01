package com.javaweb.api.admin;

import com.javaweb.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buzzer")
public class BuzzerAPI {

    @Autowired
    private MqttService mqttService;

    @PostMapping("/{message}")
    public ResponseEntity<?> turnOnOrOffBuzzer(@PathVariable String message) {
        if (!message.equalsIgnoreCase("on") && !message.equalsIgnoreCase("off")) {
            return ResponseEntity.badRequest().body("❌ Giá trị 'message' phải là 'on' hoặc 'off'");
        }
        mqttService.publishBuzzerMessage(message.toLowerCase());
        return ResponseEntity.ok("✅ Buzzer đã " + (message.equalsIgnoreCase("on") ? "bật" : "tắt"));
    }
}
