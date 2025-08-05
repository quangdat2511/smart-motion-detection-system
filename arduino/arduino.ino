#include <SoftwareSerial.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);
// Kết nối với ESP32-CAM: RX = D10, TX = D11
SoftwareSerial espSerial(10, 11); // RX, TX

// Giả lập cảm biến chuyển động
int motionPin = 2;  // hoặc thay bằng tín hiệu từ PIR
bool lastMotion = false;

void setup() {
  Serial.begin(9600);       // Debug
  espSerial.begin(9600);    // UART đến ESP32-CAM
  pinMode(motionPin, INPUT);
  Serial.println("Arduino ready.");
}

void loop() {
  bool motion = digitalRead(motionPin);
  
  // Đọc cảm biến
  if (motion && !lastMotion) {
    Serial.println("Motion detected! Sending to ESP32...");
    espSerial.println("motion");  // Gửi lệnh cho ESP32-CAM chụp ảnh
  }
  lastMotion = motion;

  // Nhận phản hồi từ ESP32-CAM
  if (espSerial.available()) {
    String line = espSerial.readStringUntil('\n');
    line.trim(); 

    // In ra serial debug
    Serial.print("Recv: [");
    Serial.print(line);
    Serial.println("]");

    if (line.startsWith("LCD: ")) {
      String content = line.substring(5);
      lcd.clear();
      lcd.setCursor(0, 0);
      lcd.print(content);
    }
    
  }
}