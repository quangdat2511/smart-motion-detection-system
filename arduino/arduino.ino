#include <SoftwareSerial.h>
#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Servo.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);
// Kết nối với ESP32-CAM: RX = D10, TX = D11
SoftwareSerial espSerial(10, 11); // RX, TX

// Giả lập cảm biến chuyển động
int pirPin = 3;  // hoặc thay bằng tín hiệu từ PIR
int buttonPin = 4;
int buzzerPin = 12; 
int ledPin = 13;
int servoPin = 9;
bool lastMotion = false;
bool lastButton = false;
Servo myServo;

void setup() {
  Serial.begin(9600);       // Debug
  espSerial.begin(9600);    // UART đến ESP32-CAM
  pinMode(pirPin, INPUT);
  pinMode(buttonPin, INPUT);
  pinMode(buzzerPin, OUTPUT); // setup buzzer output
  digitalWrite(buzzerPin, LOW); // buzzer tắt ban đầu
  lcd.init();
  lcd.backlight();
  myServo.attach(9);
  Serial.println("Arduino ready.");
}

void loop() {
  bool motion = digitalRead(pirPin);
  bool button = digitalRead(buttonPin);
  // Đọc cảm biến
  if (motion && !lastMotion) {
    Serial.println("Motion detected! Sending to ESP32...");
    espSerial.println("motion");  // Gửi lệnh cho ESP32-CAM chụp ảnh
  }
  if (button && !lastButton){
    Serial.println("Button press detected! Sending to ESP32...");
    espSerial.println("button");  // Gửi lệnh cho ESP32-CAM button
  }
  lastMotion = motion;
  lastButton = button;
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
    
      lcd.print(content.substring(0, min(16, content.length())));

      if (content.length() > 16) {
        lcd.setCursor(0, 1);
        lcd.print(content.substring(16, min(32, content.length())));
      }
    }
    if (line.startsWith("BUZZER: ")) {
      String content = line.substring(8);
      if (content == "on") {
        digitalWrite(buzzerPin, HIGH);
        Serial.println("Buzzer turned ON.");
      } 
      else if (content == "off") 
      {
        digitalWrite(buzzerPin, LOW);
        Serial.println("Buzzer turned OFF.");
      } 
      else 
      {
        Serial.print("Unknown BUZZER command: ");
        Serial.println(content);
      }
    }
    if (line.startsWith("SERVO: ")){
      String content = line.substring(7);
      int angle = content.toInt();
      myServo.write(angle);
    }
    
  }
}