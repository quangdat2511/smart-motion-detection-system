package com.javaweb.service.impl;

import com.javaweb.service.MqttService;
import com.javaweb.service.SessionService;
import org.apache.logging.log4j.util.Strings;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MqttServiceImpl implements MqttService {

    @Autowired
    private SessionService sessionService;

    private final String BROKER = "tcp://broker.hivemq.com:1883";

    private final String BASE_GET_TOPIC = "/group7/get";
    private final String BASE_IMAGE_TOPIC = "/group7/image";
    private final String BASE_BUTTON_TOPIC = "/group7/button";
    private final String BASE_BUZZER_TOPIC = "/group7/buzzer";
    private final String BASE_LCD_TOPIC = "/group7/lcd";
    private final String BASE_SERVO_TOPIC = "/group7/servo";

    // Map quản lý client MQTT theo deviceId
    private final Map<String, MqttClient> clientMap = new ConcurrentHashMap<>();

    // Map quản lý tập user đang điều khiển từng deviceId
    private final Map<String, Set<String>> deviceUsersMap = new ConcurrentHashMap<>();


    /**
     * Khi user đăng nhập và bắt đầu điều khiển deviceId.
     * Thêm user vào deviceUsersMap, tạo client MQTT nếu chưa có.
     */
    public synchronized void handleLogin(String deviceId, String username) {
        if (deviceId == null || Strings.isBlank(deviceId) || username == null || username.isEmpty()) {
            System.out.println("User này chưa quản lí thiết bị nào, không cần kết nối MQTT");
            return;
        }

        // Thêm username vào tập user điều khiển deviceId
        deviceUsersMap.computeIfAbsent(deviceId, k -> ConcurrentHashMap.newKeySet()).add(username);

        // Nếu chưa có client kết nối cho deviceId, tạo mới
        clientMap.compute(deviceId, (key, existingClient) -> {
            try {
                if (existingClient != null && existingClient.isConnected()) {
                    System.out.println("⚠ Đã có client MQTT kết nối với deviceId này rồi: " + deviceId);
                    return existingClient;
                }

                // Ngắt kết nối client cũ nếu có
                if (existingClient != null) {
                    existingClient.disconnect();
                    existingClient.close();
                    System.out.println("♻️ Đóng client MQTT cũ cho deviceId: " + deviceId);
                }

                // Tạo client mới
                String clientId = "JavaClient-" + deviceId + "-" + System.currentTimeMillis();
                MqttClient clientNew = new MqttClient(BROKER, clientId, new MemoryPersistence());

                clientNew.setCallback(new MqttCallback() {
                    @Override
                    public void connectionLost(Throwable cause) {
                        System.out.println("❌ Mất kết nối MQTT deviceId=" + deviceId + ": " + cause.getMessage());
                    }

                    @Override
                    public void messageArrived(String topic, MqttMessage message) {
                        String msg = message.toString();
                        System.out.println("📥 Nhận [" + topic + "] deviceId=" + deviceId + ": " + msg);

                        String buttonTopic = BASE_BUTTON_TOPIC + "/" + deviceId;
                        if (topic.equals(buttonTopic) && "1".equals(msg)) {
                            System.out.println("🔔 Nút bấm được nhấn. Logout tất cả user quản lí deviceId: " + deviceId);
                            sessionService.logoutAllUsers(deviceId);
                        }
                    }

                    @Override
                    public void deliveryComplete(IMqttDeliveryToken token) {
                        System.out.println("✅ Gửi thành công deviceId=" + deviceId);
                    }
                });

                MqttConnectOptions options = new MqttConnectOptions();
                options.setCleanSession(true);
                options.setAutomaticReconnect(true);

                clientNew.connect(options);

                clientNew.subscribe(BASE_IMAGE_TOPIC + "/" + deviceId);
                clientNew.subscribe(BASE_BUTTON_TOPIC + "/" + deviceId);

                System.out.println("✅ Kết nối thành công tới MQTT Broker cho deviceId: " + deviceId);
                System.out.println("📡 Đã subscribe các topic cho deviceId: " + deviceId);

                clearStartupRetainedMessages(deviceId, clientNew);

                return clientNew;

            } catch (MqttException e) {
                System.out.println("❌ Lỗi khi kết nối MQTT deviceId=" + deviceId + ": " + e.getMessage());
                e.printStackTrace();
                return existingClient;
            }
        });
    }

    /**
     * Khi user logout hoặc ngừng điều khiển deviceId,
     * xóa user khỏi deviceUsersMap,
     * nếu không còn user nào, ngắt kết nối client MQTT cho deviceId.
     */
    public synchronized void handleLogout(String deviceId, String username) {
        if (deviceId == null || Strings.isBlank(deviceId) || username == null || username.isEmpty()) {
            System.out.println("⚠ deviceId hoặc username không hợp lệ");
            return;
        }

        Set<String> users = deviceUsersMap.get(deviceId);
        if (users != null) {
            users.remove(username);
            System.out.println("🔻 Đã xóa user '" + username + "' khỏi deviceId " + deviceId);

            // Nếu không còn user nào điều khiển deviceId này nữa → đóng client
            if (users.isEmpty()) {
                deviceUsersMap.remove(deviceId);

                MqttClient client = clientMap.remove(deviceId);
                if (client != null && client.isConnected()) {
                    try {
                        client.disconnect();
                        client.close();
                        System.out.println("♻️ Đóng client MQTT deviceId " + deviceId + " vì không còn user nào điều khiển");
                    } catch (MqttException e) {
                        System.out.println("❌ Lỗi khi đóng client MQTT deviceId " + deviceId + ": " + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public synchronized void updateDeviceId(String oldDeviceId, String newDeviceId, String username) {
        if (username == null || username.isEmpty()) {
            System.out.println("⚠ Username không hợp lệ khi update deviceId");
            return;
        }
        if (oldDeviceId != null && !Strings.isBlank(oldDeviceId)) {
            handleLogout(oldDeviceId, username);
        }
        if (newDeviceId != null && !Strings.isBlank(newDeviceId)) {
            handleLogin(newDeviceId, username);
        }
    }



    private void clearStartupRetainedMessages(String deviceId, MqttClient client) {
        clearRetainedMessage(BASE_BUZZER_TOPIC + "/" + deviceId, client);
        clearRetainedMessage(BASE_LCD_TOPIC + "/" + deviceId, client);
        clearRetainedMessage(BASE_SERVO_TOPIC + "/" + deviceId, client);
    }

    private void clearRetainedMessage(String topic, MqttClient client) {
        try {
            MqttMessage mqttMessage = new MqttMessage("".getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);
            client.publish(topic, mqttMessage);
            System.out.println("🧹 Đã xóa retained message của topic: " + topic);
        } catch (MqttException e) {
            System.out.println("❌ Lỗi xóa retained message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void publishMessage(String message, String topic, String deviceId) {
        MqttClient client = clientMap.get(deviceId);
        if (client == null || !client.isConnected()) {
            System.out.println("⚠ MQTT chưa kết nối hoặc client không tồn tại cho deviceId: " + deviceId);
            return;
        }
        try {
            MqttMessage mqttMessage = new MqttMessage(message.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(false);
            client.publish(topic, mqttMessage);
            System.out.println("🚀 Gửi [" + message + "] tới topic: " + topic + " deviceId: " + deviceId);
        } catch (MqttException e) {
            System.out.println("❌ Gửi thất bại: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void publishBuzzerMessage(String message, String deviceId) {
        publishMessage(message, BASE_BUZZER_TOPIC + "/" + deviceId, deviceId);
    }

    @Override
    public void publishLcdMessage(String message, String deviceId) {
        publishMessage(message, BASE_LCD_TOPIC + "/" + deviceId, deviceId);
    }

    @Override
    public void publishServoMessage(String message, String deviceId) {
        publishMessage(message, BASE_SERVO_TOPIC + "/" + deviceId, deviceId);
    }
}
