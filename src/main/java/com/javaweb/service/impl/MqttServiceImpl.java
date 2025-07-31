package com.javaweb.service.impl;

import com.javaweb.service.MqttService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.stereotype.Service;

@Service
public class MqttServiceImpl implements MqttService {

    private final String BROKER = "tcp://broker.hivemq.com:1883";
    private final String CLIENT_ID = "JavaClient-" + System.currentTimeMillis();
    private final String SUB_TOPIC = "/23127341/temperature";
    private final String LED_TOPIC = "/23127341/led";
    private final String BUZZER_TOPIC = "/23127341/buzzer";
    private final String LCD_TOPIC = "/23127341/lcd";

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
                    System.out.println("📥 Nhận được từ [" + topic + "]: " + message.toString());
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

            client.subscribe(SUB_TOPIC);
            System.out.println("🔔 Đang lắng nghe topic: " + SUB_TOPIC);

        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    private void publishMessage(String message, String topic) {
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);
            client.publish(topic, mqttMessage);
            System.out.println("🚀 Đã gửi: " + message + " tới topic " + topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void publishLedMessage(String message) {
        publishMessage(message, LED_TOPIC);
    }
    @Override
    public void publishBuzzerMessage(String message) {
        publishMessage(message, BUZZER_TOPIC);
    }

    @Override
    public void publishLcdMessage(String message) {
        publishMessage(message, LCD_TOPIC);
    }
}
