#define MQTT_MAX_PACKET_SIZE 24576

#include <WiFi.h>
#include <PubSubClient.h>
#include "esp_camera.h"
#include "base64.h"
#include "board_config.h"
#include <WiFiManager.h>

// WiFi info
// const char* ssid = "i86";
// const char* password = "KyNiem10Nam@1!";
WiFiManager wm;
unsigned long lastCheck = 0;

// MQTT info
const char* mqttServer = "broker.hivemq.com";
int port = 1883;
WiFiClient wifiClient;
PubSubClient mqttClient(wifiClient);

// void wifiConnect() {
//   WiFi.begin(ssid, password);
//   while (WiFi.status() != WL_CONNECTED) {
//     delay(500);
//   }
// }

void mqttConnect() {
  while (!mqttClient.connected()) {
    String clientId = "ESP32Client-" + String(random(0xffff), HEX);
    if (mqttClient.connect(clientId.c_str())) {

      //***Subscribe all topic you need***
      // mqttClient.subscribe("/group7/get/561");
      mqttClient.subscribe("/group7/lcd/561"); 
      mqttClient.subscribe("/group7/buzzer/561");
      mqttClient.subscribe("/group7/servo/561");

    } else {
      delay(5000);
    }
  }
}

// Xử lý ảnh và gửi qua MQTT
void captureAndSendImage() {
  camera_fb_t* fb = esp_camera_fb_get();
  if (!fb) {
    Serial.println("fail");  // Gửi lỗi về Arduino
    return;
  }

  String imageBase64 = base64::encode(fb->buf, fb->len);
  bool success = mqttClient.publish("/group7/image", imageBase64.c_str());
  esp_camera_fb_return(fb);

  // if (success) {
  //   Serial.println("done");  // Gửi về Arduino: gửi thành công
  // } else {
  //   Serial.println("publish_fail");
  // }
}

// MQTT callback
void callback(char* topic, byte* message, unsigned int length) {
  String msg;
  for (int i = 0; i < length; i++) {
    msg += (char)message[i];
  }

  //***Code here to process the received package***
  if (String(topic) == "/group7/get" && msg == "get") {
    captureAndSendImage();
  }
  if (String(topic) == "/group7/lcd") {
    Serial.println("LCD: " + msg);
  }
  if (String(topic) == "/group7/buzzer") {
    Serial.println("BUZZER: " + msg);
  }
  if (String(topic) == "/group7/servo") {
    Serial.println("SERVO: " + msg);
  }
}

void setupCamera() {
  camera_config_t config;
  config.ledc_channel = LEDC_CHANNEL_0;
  config.ledc_timer = LEDC_TIMER_0;
  config.pin_d0 = Y2_GPIO_NUM;
  config.pin_d1 = Y3_GPIO_NUM;
  config.pin_d2 = Y4_GPIO_NUM;
  config.pin_d3 = Y5_GPIO_NUM;
  config.pin_d4 = Y6_GPIO_NUM;
  config.pin_d5 = Y7_GPIO_NUM;
  config.pin_d6 = Y8_GPIO_NUM;
  config.pin_d7 = Y9_GPIO_NUM;
  config.pin_xclk = XCLK_GPIO_NUM;
  config.pin_pclk = PCLK_GPIO_NUM;
  config.pin_vsync = VSYNC_GPIO_NUM;
  config.pin_href = HREF_GPIO_NUM;
  config.pin_sccb_sda = SIOD_GPIO_NUM;
  config.pin_sccb_scl = SIOC_GPIO_NUM;
  config.pin_pwdn = PWDN_GPIO_NUM;
  config.pin_reset = RESET_GPIO_NUM;
  config.xclk_freq_hz = 20000000;
  config.frame_size = FRAMESIZE_UXGA;
  config.pixel_format = PIXFORMAT_JPEG;
  config.grab_mode = CAMERA_GRAB_WHEN_EMPTY;
  config.fb_location = CAMERA_FB_IN_PSRAM;
  config.jpeg_quality = 12;
  config.fb_count = 1;

  if (config.pixel_format == PIXFORMAT_JPEG) {
    if (psramFound()) {
      config.jpeg_quality = 10;
      config.fb_count = 2;
      config.grab_mode = CAMERA_GRAB_LATEST;
    } else {
      config.frame_size = FRAMESIZE_SVGA;
      config.fb_location = CAMERA_FB_IN_DRAM;
    }
  } else {
    config.frame_size = FRAMESIZE_240X240;
#if CONFIG_IDF_TARGET_ESP32S3
    config.fb_count = 2;
#endif
  }

#if defined(CAMERA_MODEL_ESP_EYE)
  pinMode(13, INPUT_PULLUP);
  pinMode(14, INPUT_PULLUP);
#endif

  esp_err_t err = esp_camera_init(&config);
  if (err != ESP_OK) {
    return;
  }

  sensor_t *s = esp_camera_sensor_get();
  if (s->id.PID == OV3660_PID) {
    s->set_vflip(s, 1);
    s->set_brightness(s, 1);
    s->set_saturation(s, -2);
  }
  if (config.pixel_format == PIXFORMAT_JPEG) {
    s->set_framesize(s, FRAMESIZE_QVGA);
  }

#if defined(CAMERA_MODEL_M5STACK_WIDE) || defined(CAMERA_MODEL_M5STACK_ESP32CAM)
  s->set_vflip(s, 1);
  s->set_hmirror(s, 1);
#endif

#if defined(CAMERA_MODEL_ESP32S3_EYE)
  s->set_vflip(s, 1);
#endif
}

void setup() {
  Serial.begin(9600);  // Dùng UART0 để giao tiếp với Arduino (TX=GPIO1, RX=GPIO3)

  // wifiConnect();
  setupCamera();

  mqttClient.setServer(mqttServer, port);
  mqttClient.setCallback(callback);
  mqttClient.setKeepAlive(90);
  mqttClient.setBufferSize(20000);
  
  //Code cho chức năng AP
  wm.setConfigPortalTimeout(180);
  // Serial.println("LCD: Not WiFi connect, enter AP mode!")
  // wm.autoConnect("ESP32-CAM-Setup", "12345678"
  if (!wm.autoConnect("ESP32-CAM-Setup", "12345678")) {
    Serial.println("LCD: Not WiFi: AP mode!");
  } else {
    Serial.println("LCD: Connect WiFi successfully!");
  }
}

unsigned long lastMotionSent = 0;
const unsigned long interval = 500; // 0.5 giây
void loop() {
  // if (WiFi.status() != WL_CONNECTED) {
  //   wifiConnect();
  // }
  if (millis() - lastCheck > 5000) {
    lastCheck = millis();
    if (WiFi.status() != WL_CONNECTED) {
      Serial.println("LCD: Not WiFi: AP mode!");
      wm.startConfigPortal("ESP32-CAM-Setup", "12345678");
      Serial.println("LCD: Connect WiFi successfully!");
    }
  }

  if (!mqttClient.connected()) {
    mqttConnect();
  }

  mqttClient.loop();

  // Nhận lệnh từ Arduino
  if (Serial.available()) {
    String msg = Serial.readStringUntil('\n');
    msg.trim();
    if (msg == "motion") {
      captureAndSendImage();
    }
    if (msg == "button"){
      mqttClient.publish("/group7/button", "1");
    }
  }

}



