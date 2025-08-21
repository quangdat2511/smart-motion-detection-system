package com.javaweb.api;

import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/device")
public class DeviceAPI {

    @Autowired
    private DeviceService deviceService;

    @PutMapping("/choose")
    public ResponseEntity<String> chooseDevice(@RequestParam String deviceId) {
        // 1. Update DB hoặc session nếu cần
        deviceService.updateDeviceIdOfUser(deviceId);

        // 2. Lấy Authentication hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetail currentUser = (MyUserDetail) authentication.getPrincipal();

        // 3. Cập nhật deviceId mới
        currentUser.setDeviceId(deviceId);

        // 4. Tạo Authentication mới với principal đã cập nhật
        UsernamePasswordAuthenticationToken newAuth =
                new UsernamePasswordAuthenticationToken(
                        currentUser,
                        authentication.getCredentials(), // dùng credentials hiện tại
                        currentUser.getAuthorities()
                );

        // 5. Ghi lại vào SecurityContext
        SecurityContextHolder.getContext().setAuthentication(newAuth);

        return ResponseEntity.ok("✅ Đã chọn thiết bị có id: " + deviceId);
    }
    @GetMapping("/current")
    public ResponseEntity<String> getCurrentDevice() {
        // 1. Lấy Authentication hiện tại
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetail currentUser = (MyUserDetail) authentication.getPrincipal();

        // 2. Trả về deviceId hiện tại
        String deviceId = currentUser.getDeviceId();
        if (deviceId == null || deviceId.isEmpty()) {
            return ResponseEntity.badRequest().body("❌ Không có thiết bị nào được chọn");
        }
        return ResponseEntity.ok().body("✅ Id của thiết bị hiện tại: " + deviceId);
    }
}
