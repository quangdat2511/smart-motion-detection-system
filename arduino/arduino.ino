#include <Wire.h>
#include <LiquidCrystal_I2C.h>
#include <Servo.h>

LiquidCrystal_I2C lcd(0x27, 16, 2);

int pirPin = 3;
int buttonPin = 4;
int buzzerPin = 12; 
int ledPin = 13;
int servoPin = 6;
int ldrPin = 2;
bool lastMotion = false;
bool lastButton = false;
Servo myServo;

void setup() {
  Serial.begin(9600); //UART đến ESP32-CAM
  //PIR
  pinMode(pirPin, INPUT);
  //Button
  pinMode(buttonPin, INPUT);
  //Buzzer
  pinMode(buzzerPin, OUTPUT);
  digitalWrite(buzzerPin, LOW);
  //LDR
  pinMode(ldrPin, INPUT);
  //LED
  pinMode(ledPin, OUTPUT);
  //LCD
  lcd.init();
  lcd.backlight();
  //Servo
  myServo.attach(servoPin);
}

//Hàm xử lí in ra LCD
void printToScreen(String content) {
  lcd.clear();
  lcd.setCursor(0, 0);

  lcd.print(content.substring(0, min(16, content.length())));

  if (content.length() > 16) {
    lcd.setCursor(0, 1);
    lcd.print(content.substring(16, min(32, content.length())));
  }
}

void loop() {
  bool motion = digitalRead(pirPin);
  bool button = digitalRead(buttonPin);
  bool no_light = digitalRead(ldrPin);

  //Đọc cảm biến
  if (motion && !lastMotion) {
    Serial.println("motion"); 
  }
  if (button && !lastButton){
    printToScreen("Button press detected!");
    Serial.println("button");
  }
  lastMotion = motion;
  lastButton = button;

  //Trời tối bật đèn, trời sáng tắt đèn
  if (no_light == true) {
    digitalWrite(ledPin, true);
  } else {
    digitalWrite(ledPin, false);
  }

  //Nhận yêu cầu từ ESP32-CAM
  if (Serial.available()) {
    String line = Serial.readStringUntil('\n');
    line.trim(); 
    //In ra LCD
    if (line.startsWith("LCD: ")) {
      String content = line.substring(5);
      printToScreen(content);
    }
    //Bật/tắt buzzer
    if (line.startsWith("BUZZER: ")) {
      String content = line.substring(8);
      if (content == "on") {
        digitalWrite(buzzerPin, HIGH);
      } 
      else if (content == "off") 
      {
        digitalWrite(buzzerPin, LOW);
      }
    }
    //Xoay servo (nhằm chỉnh góc cam)
    if (line.startsWith("SERVO: ")){
      String content = line.substring(7);
      int angle = content.toInt();
      myServo.write(angle);
    }
    
  }
}