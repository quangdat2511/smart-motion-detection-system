package com.javaweb.service;

public interface OpenCvService {
    /**
     * Detect faces in an image and save result.
     * @param inputPath  đường dẫn ảnh gốc
     * @param outputPath đường dẫn ảnh kết quả
     * @return số lượng khuôn mặt tìm thấy
     */
    int detectAndSave(String inputPath, String outputPath);
}
