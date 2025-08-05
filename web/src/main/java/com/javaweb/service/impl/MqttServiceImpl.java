package com.javaweb.service.impl;

import com.javaweb.service.MotionService;
import com.javaweb.service.MqttService;
import com.javaweb.service.SessionService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class MqttServiceImpl implements MqttService {

    @Autowired
    private MotionService motionService;
    @Autowired
    private SessionService sessionService;
    private final String BROKER = "tcp://broker.hivemq.com:1883";
    private final String CLIENT_ID = "JavaClient-" + System.currentTimeMillis();
    private final String MOTION_TOPIC = "/23127341/motion";
    private final String BUTTON_TOPIC = "/23127341/button";
    private final String BUZZER_TOPIC = "/23127341/buzzer";
    private final String LCD_TOPIC = "/23127341/lcd";
    private final String SERVO_TOPIC = "/23127341/servo";

    private MqttClient client;

    @PostConstruct
    public void init() {
        connectAndSubscribe();
    }

    private void connectAndSubscribe() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("❌ Mất kết nối MQTT: " + cause.getMessage());
                    reconnect();
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String msg = message.toString();
                    System.out.println("📥 Nhận [" + topic + "]: " + msg);

                    if (topic.equals(MOTION_TOPIC)) {
                        if ("1".equals(msg)) {
                            System.out.println("🚨 Có chuyển động!");
                            if (motionService != null) {
                                motionService.setLatestMotionStatus("Có chuyển động");
                            }
                        } else if ("0".equals(msg)) {
                            System.out.println("✅ Không có chuyển động.");
                            if (motionService != null) {
                                motionService.setLatestMotionStatus("Không có chuyển động");
                            }
                        } else {
                            System.out.println("⚠️ Dữ liệu không hợp lệ: " + msg);
                        }
                    }

                    if (topic.equals(BUTTON_TOPIC)) {
                        if ("1".equals(msg)) {
                            sessionService.logoutAllUsers();
                            System.out.println("🔔 Nút bấm đã được nhấn.");
                        } else {
                            System.out.println("⚠️ Dữ liệu không hợp lệ từ button: " + msg);
                        }
                    }
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    System.out.println("✅ Gửi thành công.");
                }
            });

            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true);

            client.connect(options);
            System.out.println("✅ Kết nối thành công tới MQTT Broker!");

            subscribeTopics();
            clearStartupRetainedMessages();

        } catch (MqttException e) {
            System.out.println("❌ Lỗi khi kết nối MQTT: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void reconnect() {
        new Thread(() -> {
            while (!client.isConnected()) {
                try {
                    System.out.println("🔁 Đang thử kết nối lại...");
                    client.reconnect();
                    subscribeTopics();
                    System.out.println("🔄 Đã kết nối lại và subscribe.");
                } catch (MqttException e) {
                    System.out.println("❌ Thử lại thất bại: " + e.getMessage());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ignored) {}
                }
            }
        }).start();
    }

    private void subscribeTopics() throws MqttException {
        client.subscribe(MOTION_TOPIC);
        client.subscribe(BUTTON_TOPIC);
        System.out.println("📡 Đã subscribe các topic.");
    }

    private void publishMessage(String message, String topic) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(false);
            client.publish(topic, mqttMessage);
            System.out.println("🚀 Gửi [" + message + "] tới topic: " + topic);
        } catch (MqttException e) {
            System.out.println("❌ Gửi thất bại: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void clearStartupRetainedMessages() {
        clearRetainedMessage(BUZZER_TOPIC);
        clearRetainedMessage(LCD_TOPIC);
        clearRetainedMessage(SERVO_TOPIC);
    }

    private void clearRetainedMessage(String topic) {
        try {
            MqttMessage mqttMessage = new MqttMessage("".getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);
            client.publish(topic, mqttMessage);
            System.out.println("🧹 Đã xóa retained message của topic: " + topic);
        } catch (MqttException e) {
            System.out.println("❌ Lỗi xóa retained: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void publishBuzzerMessage(String message) {
        publishMessage(message, BUZZER_TOPIC);
    }

    @Override
    public void publishLcdMessage(String message) {
        publishMessage(message, LCD_TOPIC);
    }

    @Override
    public void publishServoMessage(String message) {
        publishMessage(message, SERVO_TOPIC);
    }
}
