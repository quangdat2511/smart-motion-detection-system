package com.javaweb.service;

public interface EmailService {
    boolean sendMail(String deviceId, int motionCount, int motionHasPersonCount);
}
