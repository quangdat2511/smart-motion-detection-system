package com.javaweb.service.impl;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.stereotype.Service;

import java.io.File;
import java.net.URL;
import java.util.Base64;

@Service
public class OpenCvServiceImpl implements com.javaweb.service.OpenCvService {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load thư viện OpenCV 1 lần
    }
    private String matToBase64(Mat image) {
        // Encode Mat -> byte[]
        MatOfByte mob = new MatOfByte();
        Imgcodecs.imencode(".jpg", image, mob);
        byte[] byteArray = mob.toArray();

        // byte[] -> Base64
        return Base64.getEncoder().encodeToString(byteArray);
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

            // 5. Load Haar Cascade từ resources/data
            URL xmlUrl = getClass().getClassLoader().getResource("data/haarcascade_frontalface_alt2.xml");
            if (xmlUrl == null) {
                System.out.println("❌ Haar Cascade XML file not found in resources!");
                return 0;
            }

            File xmlFile = new File(xmlUrl.toURI());
            CascadeClassifier faceDetector = new CascadeClassifier(xmlFile.getAbsolutePath());
            if (faceDetector.empty()) {
                System.out.println("❌ Failed to load Haar Cascade from " + xmlFile.getAbsolutePath());
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
                Imgproc.rectangle(
                        inputImage,
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
            String base64Output = matToBase64(inputImage);
            System.out.println("Preview Base64: " + base64Output.substring(0,100) + "...");
            System.out.println("✅ Faces detected: " + faceArray.length + " -> Saved as " + outputPath);
            return faceArray.length;

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
