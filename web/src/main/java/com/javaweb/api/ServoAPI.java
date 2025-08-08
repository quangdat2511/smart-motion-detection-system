package com.javaweb.api;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.MqttService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/servo")
public class ServoAPI {
    @Autowired
    private MqttService mqttService;
    @Autowired
    private UserService userService;
    @PutMapping("/{angle}")
    public ResponseEntity<?> setServoAngle(@PathVariable String angle) {
        int angleValue;
        try {
            angleValue = Integer.parseInt(angle);
        }
        catch (NumberFormatException e) {
            return ResponseEntity.badRequest().body("❌ Giá trị 'angle' phải là một số nguyên.");
        }
        if (angleValue < 0 || angleValue > 180) {
            return ResponseEntity.badRequest().body("❌ Góc phải nằm trong khoảng từ 0 đến 180 độ.");
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy deviceId từ sessionService theo username
        UserDTO user = userService.findOneByUserNameAndStatus(username, 1);
        Integer deviceId = user.getDeviceId();
        if (deviceId == null) {
            return ResponseEntity.badRequest().body("❌ Không tìm thấy thiết bị cho tài khoản này");
        }
        // Publish góc quay lên MQTT
        mqttService.publishServoMessage(angle, deviceId.toString());

        return ResponseEntity.ok("✅ Servo đã xoay đến " + angle + " độ.");
    }
}
