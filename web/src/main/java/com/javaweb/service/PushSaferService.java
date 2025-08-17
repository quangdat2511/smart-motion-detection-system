package com.javaweb.service;

public interface PushSaferService {
    void sendPush();
    boolean isReceiveMessage();
    void setReceiveMessage(boolean receiveMessage);
}
