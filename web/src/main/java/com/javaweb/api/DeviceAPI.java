package com.javaweb.api;

import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.security.utils.SecurityUtils;
import com.javaweb.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/device")
public class DeviceAPI {
    @Autowired
    private DeviceService deviceService;
    @PutMapping("/choose")
    public ResponseEntity<String> chooseDevice(@RequestParam String deviceId) {
        deviceService.updateDeviceIdOfUser(deviceId);

        // 2. Cập nhật lại principal trong session
        MyUserDetail currentUser = SecurityUtils.getPrincipal();
        currentUser.setDeviceId(deviceId); // set lại giá trị mới

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        currentUser, // principal mới
                        currentUser.getPassword(),
                        currentUser.getAuthorities()
                );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return ResponseEntity.ok("✅ Đã chọn thiết bị có id: " + deviceId);
    }
}
