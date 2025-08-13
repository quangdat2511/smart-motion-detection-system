package com.javaweb.security;

import com.javaweb.model.dto.MyUserDetail;
import com.javaweb.service.MqttService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Getter
    @Setter
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Autowired
    private MqttService mqttService;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String deviceId = null; // mặc định là null

        Object principal = authentication.getPrincipal();
        if (principal instanceof MyUserDetail) {
            deviceId = ((MyUserDetail) principal).getDeviceId();

            // Chặn luôn trường hợp "null" hoặc rỗng
            if (deviceId == null || deviceId.trim().isEmpty() || "null".equalsIgnoreCase(deviceId.trim())) {
                deviceId = null; // đặt lại về null nếu không hợp lệ
            }
        }

        MyUserDetail myUserDetail = (MyUserDetail) principal;
        mqttService.handleLogin(deviceId, myUserDetail.getUsername());

        String targetUrl = "/admin/home";
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

}
