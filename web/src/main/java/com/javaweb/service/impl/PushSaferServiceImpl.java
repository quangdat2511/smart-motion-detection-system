package com.javaweb.service.impl;

import com.javaweb.service.PushSaferService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PushSaferServiceImpl implements PushSaferService {
    @Getter
    @Setter
    private boolean receiveMessage = true; // mặc định bật

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public void sendPush() {
        String url = "https://www.pushsafer.com/api?k=ITwaxpzZbDGqE4xAbQiC&m=C%C3%B3%20chuy%E1%BB%83n%20%C4%91%E1%BB%99ng";
        restTemplate.getForObject(url, String.class);
    }
}
