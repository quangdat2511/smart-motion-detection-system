package com.javaweb.service.impl;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
public class OpenCvServiceImpl implements com.javaweb.service.OpenCvService {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load thư viện OpenCV 1 lần
    }

    @Override
    public int detectAndSave(String base64Image, String outputPath) {
        try {
            // 1. Decode Base64 → byte[]
            byte[] imageBytes = Base64.getDecoder().decode(base64Image);

            // 2. byte[] → Mat (dùng OpenCV)
            Mat inputImage = Imgcodecs.imdecode(new MatOfByte(imageBytes), Imgcodecs.IMREAD_COLOR);
            if (inputImage.empty()) {
                System.out.println("❌ Could not decode Base64 image!");
                return 0;
            }

            // 3. Chuyển sang grayscale
            Mat grayFrame = new Mat();
            Imgproc.cvtColor(inputImage, grayFrame, Imgproc.COLOR_BGR2GRAY);
            Imgproc.equalizeHist(grayFrame, grayFrame);

            // 4. Xác định kích thước tối thiểu của khuôn mặt
            int height = grayFrame.height();
            int absoluteFaceSize = Math.round(height * 0.2f);

            // 5. Load Haar Cascade
            CascadeClassifier faceDetector = new CascadeClassifier();
            boolean loaded = faceDetector.load("data/haarcascade_frontalface_alt2.xml");
            if (!loaded) {
                System.out.println("❌ Could not load Haar Cascade XML file!");
                return 0;
            }

            // 6. Detect faces
            MatOfRect faces = new MatOfRect();
            faceDetector.detectMultiScale(
                    grayFrame,
                    faces,
                    1.1,
                    2,
                    Objdetect.CASCADE_SCALE_IMAGE,
                    new Size(absoluteFaceSize, absoluteFaceSize),
                    new Size()
            );

            Rect[] faceArray = faces.toArray();
            for (Rect rect : faceArray) {
                Imgproc.rectangle(inputImage,
                        new Point(rect.x, rect.y),
                        new Point(rect.x + rect.width, rect.y + rect.height),
                        new Scalar(0, 0, 255),
                        3
                );
            }

            // 7. Save output image
            boolean saved = Imgcodecs.imwrite(outputPath, inputImage);
            if (!saved) {
                System.out.println("❌ Could not save output image to " + outputPath);
                return 0;
            }

            System.out.println("✅ Faces detected: " + faceArray.length + " -> Saved as " + outputPath);
            return faceArray.length;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
