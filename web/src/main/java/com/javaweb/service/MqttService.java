package com.javaweb.service;

public interface MqttService {
    void publishBuzzerMessage(String message, String deviceId);
    void publishLcdMessage(String message, String deviceId);
    void publishServoMessage(String message, String deviceId);
    void handleLogin(String deviceId, String username);
    void handleLogout(String deviceId, String username);
    void updateDeviceId(String oldDeviceId, String newDeviceId, String username);

}
