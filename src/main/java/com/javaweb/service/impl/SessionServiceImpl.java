package com.javaweb.service.impl;

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
    public void logoutAllUsers() {
        for (Object principal : sessionRegistry.getAllPrincipals()) {
            List<SessionInformation> sessions = sessionRegistry.getAllSessions(principal, false);
            for (SessionInformation session : sessions) {
                session.expireNow();
            }
        }
        System.out.println("🚪 Đã logout toàn bộ người dùng.");
    }
}
