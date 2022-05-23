#include <Arduino.h>
#line 1 "d:\\Cube_Solver\\MotorTest\\ArduinoTurns\\turns.ino"
//Gleichstrommotor 1
int GSM1 = 10;
int in1 = 9;
int in2 = 8;

// Gleichstrommotor 2

int GSM2 = 5;
int in3 = 7;
int in4 = 6;

#line 12 "d:\\Cube_Solver\\MotorTest\\ArduinoTurns\\turns.ino"
void setup();
#line 22 "d:\\Cube_Solver\\MotorTest\\ArduinoTurns\\turns.ino"
void loop();
#line 12 "d:\\Cube_Solver\\MotorTest\\ArduinoTurns\\turns.ino"
void setup() {
  Serial.begin(9600);
  pinMode(GSM1, OUTPUT);
  pinMode(GSM2, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
}

void loop() {
  if (Serial.available())
  {
     char input = Serial.read();
    if (input == '1') //Viertel drehung des Tellers (D')
    {
      Serial.println("move1");
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(413);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(500);
    }
    if (input == '2')
    {
      Serial.println("move2"); // Halbe Drehung des Tellers (D2)
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(820);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(500);
    }
    if (input == '3')
    {
      Serial.println("move3");
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      analogWrite(GSM1, 255);
      delay(413);
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(500);
    }
    if (input == '4')
    {
      Serial.println("move4");
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      analogWrite(GSM2, 255);
      delay(200);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(500);
    }
    if (input == '5')
    {
      Serial.println("move5");
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      analogWrite(GSM2, 200);
      delay(200);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(500);
    }
    if (input == '6')
    {
      Serial.println("move6");
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      analogWrite(GSM2, 200);
      delay(300);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(500);
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      analogWrite(GSM2, 255);
      delay(200);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(500);
    }
    
  }
  

}

