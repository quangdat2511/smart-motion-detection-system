package com.javaweb.service.impl;

import com.javaweb.service.ImageService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Getter
    @Setter
    private volatile String latestFilename;
}