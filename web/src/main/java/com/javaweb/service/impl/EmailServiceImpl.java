package com.javaweb.service.impl;

import com.javaweb.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendMail(int motionCount, int motionHasPersonCount) {
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom("khoanguyen.2005ct@gmail.com");
            mailMessage.setTo("nhakhoa23@clc.fitus.edu.vn");
            String body = "Trong 1 giờ qua, hệ thống phát hiện " + motionCount + " chuyển động, trong đó có " + motionHasPersonCount + " chuyển động có người.";
            mailMessage.setText(body);
            mailMessage.setSubject("Thông báo từ hệ thống phát hiện chuyển động");

            // Sending the mail
            mailSender.send(mailMessage);
            System.out.println("Mail Sent Successfully...");
            return true;
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            System.out.println("Error while Sending Mail");
            return false;
        }
    }
}


