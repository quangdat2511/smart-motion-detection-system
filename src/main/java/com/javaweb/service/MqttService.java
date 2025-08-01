package com.javaweb.service;

public interface MqttService {
    void publishLedMessage(String message);
    void publishBuzzerMessage(String message);
    void publishLcdMessage(String message);
    void publishServoMessage(String message);
}
