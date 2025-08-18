package com.javaweb.service;

public interface OpenCvService {
    /**
     * Detect faces in an image and save result.
     * @param base64Image chuỗi base64 của ảnh gốc
     * @param outputPath đường dẫn ảnh kết quả
     * @return số lượng khuôn mặt tìm thấy
     */
    int detectAndSave(String base64Image, String outputPath);
}
