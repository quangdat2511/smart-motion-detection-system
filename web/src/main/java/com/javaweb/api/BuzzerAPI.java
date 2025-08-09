package com.javaweb.api;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.MqttService;
import com.javaweb.service.SessionService; // giả sử service này có phương thức lấy deviceId theo username
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buzzer")
public class BuzzerAPI {

    @Autowired
    private MqttService mqttService;
    @Autowired
    private UserService userService;
    @PutMapping("/{message}")
    public ResponseEntity<?> turnOnOrOffBuzzer(@PathVariable String message) {
        if (!message.equalsIgnoreCase("on") && !message.equalsIgnoreCase("off")) {
            return ResponseEntity.badRequest().body("❌ Giá trị 'message' phải là 'on' hoặc 'off'");
        }

        // Lấy username đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy deviceId từ sessionService theo username
        UserDTO user = userService.findOneByUserNameAndStatus(username, 1);
        String deviceId = user.getDeviceId();
        if (deviceId == null || deviceId.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Không tìm thấy thiết bị cho tài khoản này");
        }

        // Gửi lệnh qua mqttService
        mqttService.publishBuzzerMessage(message.toLowerCase(), deviceId);

        return ResponseEntity.ok("✅ Buzzer đã " + (message.equalsIgnoreCase("on") ? "bật" : "tắt"));
    }
}
