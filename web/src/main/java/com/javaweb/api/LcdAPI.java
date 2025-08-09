package com.javaweb.api;

import com.javaweb.model.dto.UserDTO;
import com.javaweb.service.MqttService;
import com.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lcd")
public class LcdAPI {
    @Autowired
    private MqttService mqttService;
    @Autowired
    private UserService userService;
    @PostMapping()
    public ResponseEntity<?> displayMessageOnLcd(@RequestParam String message) {
        // Lấy username đang đăng nhập
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // Lấy deviceId từ sessionService theo username
        UserDTO user = userService.findOneByUserNameAndStatus(username, 1);
        String deviceId = user.getDeviceId();
        if (deviceId == null || deviceId.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Không tìm thấy thiết bị cho tài khoản này");
        }
        mqttService.publishLcdMessage(message, deviceId);
        return ResponseEntity.ok("✅ LCD đã hiển thị: " + message);
    }

}
