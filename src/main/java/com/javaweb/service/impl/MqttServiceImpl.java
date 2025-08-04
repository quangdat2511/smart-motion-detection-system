package com.javaweb.service.impl;

import com.javaweb.service.MotionService;
import com.javaweb.service.MqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService {
    @Autowired
    private MotionService motionService;

    private final String BROKER = "tcp://broker.hivemq.com:1883";
    private final String CLIENT_ID = "JavaClient-" + System.currentTimeMillis();
    private final String MOTION_TOPIC = "/23127341/motion";
    private final String BUZZER_TOPIC = "/23127341/buzzer";
    private final String LCD_TOPIC = "/23127341/lcd";
    private final String SERVO_TOPIC = "/23127341/servo";

    private MqttClient client;

    public MqttServiceImpl() {
        try {
            client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    System.out.println("❌ Mất kết nối: " + cause.getMessage());
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) {
                    String msg = message.toString();
                    System.out.println("📥 Nhận được từ [" + topic + "]: " + msg);

                    if (topic.equals(MOTION_TOPIC)) {
                        if ("1".equals(msg)) {
                            System.out.println("🚨 Có chuyển động!");
                            motionService.setLatestMotionStatus("Có chuyển động");
                        }
                        else if ("0".equals(msg)) {
                            System.out.println("✅ Không có chuyển động.");
                            motionService.setLatestMotionStatus("Không có chuyển động");
                        }
                        else {
                            System.out.println("⚠️ Dữ liệu không hợp lệ: " + msg);
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
            System.out.println("✅ Đã kết nối tới MQTT Broker!");

            // 🧹 Xóa các retained message cũ ngay khi kết nối
            clearRetainedMessage(BUZZER_TOPIC);
            clearRetainedMessage(LCD_TOPIC);
            clearRetainedMessage(SERVO_TOPIC);
            client.subscribe(MOTION_TOPIC);
            System.out.println("🔔 Đang lắng nghe topic: " + MOTION_TOPIC);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void publishMessage(String message, String topic) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(false); // Không lưu lại message
            client.publish(topic, mqttMessage);
            System.out.println("🚀 Đang gửi: " + message + " tới topic " + topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void clearRetainedMessage(String topic) {
        try {
            MqttMessage mqttMessage = new MqttMessage("".getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true); // Message rỗng + retained = clear
            client.publish(topic, mqttMessage);
            System.out.println("🧹 Đã xóa retained message của topic: " + topic);
        } catch (MqttException e) {
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
    public void publishServoMessage(String message) {publishMessage(message, SERVO_TOPIC);
        }
}
