package com.javaweb.api;

import com.javaweb.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        deviceService.updateDeviceIdOfUser(Integer.valueOf(deviceId));
        return ResponseEntity.ok("✅ Đã chọn thiết bị có id: " + deviceId);
    }
}
