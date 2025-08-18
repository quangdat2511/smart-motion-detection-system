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
        String url = "https://www.pushsafer.com/api?k=78bFXhbkew8pyMtyhgDD&m=Ph%C3%A1t%20hi%E1%BB%87n%20chuy%E1%BB%83n%20%C4%91%E1%BB%99ng%20c%C3%B3%20ng%C6%B0%E1%BB%9Di";
        restTemplate.getForObject(url, String.class);
    }
}
