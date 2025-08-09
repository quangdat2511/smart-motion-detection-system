package com.javaweb.service.impl;

import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRegistry sessionRegistry;

    @Override
    public void logoutAllUsers(String deviceId) {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            if (principal instanceof MyUserDetail) {
                MyUserDetail userDetail = (MyUserDetail) principal;

                if (userDetail.getDeviceId() != null && userDetail.getDeviceId().equals(deviceId)) {
                    List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
                    for (SessionInformation session : sessions) {
                        session.expireNow();
                        System.out.println("🚪 Đã logout user: " + userDetail.getUsername() +
                                " (deviceId: " + deviceId + ")");
                    }
                }
            }
        }
    }
}
