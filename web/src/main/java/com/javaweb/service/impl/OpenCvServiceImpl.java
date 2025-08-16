package com.javaweb.service.impl;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.stereotype.Service;

@Service
public class OpenCvServiceImpl implements com.javaweb.service.OpenCvService {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // Load thư viện OpenCV 1 lần
    }

    @Override
    public int detectAndSave(String inputPath, String outputPath) {
        Mat image = Imgcodecs.imread(inputPath);
        if (image.empty()) {
            System.out.println("❌ Could not load image: " + inputPath);
            return 0;
        }

        Mat grayFrame = new Mat();
        Imgproc.cvtColor(image, grayFrame, Imgproc.COLOR_BGR2GRAY);
        Imgproc.equalizeHist(grayFrame, grayFrame);

        int height = grayFrame.height();
        int absoluteFaceSize = Math.round(height * 0.2f);

        CascadeClassifier faceDetector = new CascadeClassifier();
        boolean loaded = faceDetector.load("data/haarcascade_frontalface_alt2.xml");
        if (!loaded) {
            System.out.println("❌ Could not load Haar Cascade XML file!");
            return 0;
        }

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
            Imgproc.rectangle(image,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }

        Imgcodecs.imwrite(outputPath, image);
        System.out.println("✅ Faces detected: " + faceArray.length + " -> Saved as " + outputPath);

        return faceArray.length;
    }
}
