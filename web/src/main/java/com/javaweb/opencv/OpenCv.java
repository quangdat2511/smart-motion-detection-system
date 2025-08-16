package com.javaweb.opencv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;

public class OpenCv {
    public static void main(String[] arg){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//        System.out.println(Core.VERSION);
      String filePath = "images/input.png";
        Mat image = Imgcodecs.imread(filePath);

        if (image.empty()) {
            System.out.println("Could not load image!");
        } else {
            System.out.println("Image loaded: " + image.size());
        }
//        detectAndSave
        detectAndSave(image);
    }
    private static void detectAndSave(Mat image) {
        MatOfRect faces = new MatOfRect();

        // Convert to gray scale
        Mat grayFrame = new Mat();
        Imgproc.cvtColor(image, grayFrame, Imgproc.COLOR_BGR2GRAY);

        // Improve contrast
        Imgproc.equalizeHist(grayFrame, grayFrame);

        int height = grayFrame.height();
        int absoluteFaceSize = 0;
        if (Math.round(height * 0.2f) > 0) {
            absoluteFaceSize = Math.round(height * 0.2f);
        }

        // Load cascade classifier
        CascadeClassifier faceDetector = new CascadeClassifier();
        boolean loaded = faceDetector.load("data/haarcascade_frontalface_alt2.xml");
        if (!loaded) {
            System.out.println("❌ Could not load Haar Cascade XML file!");
            return;
        }

        // Detect faces
        faceDetector.detectMultiScale(
                grayFrame,
                faces,
                1.1,        // scaleFactor
                2,          // minNeighbors
                Objdetect.CASCADE_SCALE_IMAGE,
                new Size(absoluteFaceSize, absoluteFaceSize),
                new Size()
        );

        // Draw rectangles around detected faces
        Rect[] faceArray = faces.toArray();
        for (Rect rect : faceArray) {
            Imgproc.rectangle(image,
                    new Point(rect.x, rect.y),
                    new Point(rect.x + rect.width, rect.y + rect.height),
                    new Scalar(0, 0, 255),
                    3
            );
        }

        // Save output image
        Imgcodecs.imwrite("images/output.png", image);
//        faceArray.length chính là số lượng khuôn mặt mà OpenCV tìm thấy trong ảnh.
        System.out.println("✅ Faces detected: " + faceArray.length + " -> Saved as output.png");
    }

}
