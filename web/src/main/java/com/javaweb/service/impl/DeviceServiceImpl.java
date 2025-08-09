package com.javaweb.service.impl;

import com.javaweb.entity.UserEntity;
import com.javaweb.repository.UserRepository;
import com.javaweb.service.DeviceService;
import com.javaweb.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MqttService mqttService;
    @Override
    public void updateDeviceIdOfUser(String deviceId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        UserEntity userEntity = userRepository.findOneByUserName(userName);
        String oldDeviceId = userEntity.getDeviceId();
        userEntity.setDeviceId(deviceId);
        mqttService.updateDeviceId(oldDeviceId, deviceId, userName);
        userRepository.save(userEntity);
    }
}
