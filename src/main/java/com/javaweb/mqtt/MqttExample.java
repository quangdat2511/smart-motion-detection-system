package com.javaweb.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
public class MqttExample {

    private static final String BROKER = "tcp://broker.hivemq.com:1883";
    private static final String CLIENT_ID = "JavaClient-" + System.currentTimeMillis();
    private static final String SUB_TOPIC = "/23127341/temperature";
    private static final String PUB_TOPIC = "/23127341/led";

    public static void main(String[] args) {
        try {
            // Tạo client MQTT
            MqttClient client = new MqttClient(BROKER, CLIENT_ID, new MemoryPersistence());

            // Thiết lập callback
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

            // Thiết lập tùy chọn kết nối
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            options.setAutomaticReconnect(true); // Tự động reconnect nếu mất mạng

            client.connect(options);
            System.out.println("✅ Đã kết nối tới MQTT Broker!");

            // Đăng ký nhận dữ liệu từ topic
            client.subscribe(SUB_TOPIC);
            System.out.println("🔔 Đang lắng nghe topic: " + SUB_TOPIC);

            // Gửi dữ liệu bật LED
            String msg = "on"; // Thay "off" nếu cần tắt
            MqttMessage mqttMessage = new MqttMessage(msg.getBytes());
            mqttMessage.setQos(0);
            mqttMessage.setRetained(true);     // Lưu lại trên broker
            client.publish(PUB_TOPIC, mqttMessage);
            System.out.println("🚀 Đã gửi: " + msg + " tới topic " + PUB_TOPIC);

            // Giữ chương trình chạy
            while (true) {
                Thread.sleep(5000); // Hoặc có thể xử lý logic khác ở đây
            }

        } catch (MqttException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
