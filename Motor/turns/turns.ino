#include <Stepper.h>
int SPU = 2048;
Stepper Motor(SPU,3,5,4,6);
//Gleichstrommotor 1
int GSM1 = 10;
int in1 = 9;
int in2 = 8;


void setup() {
  Serial.begin(9600);
  pinMode(GSM1, OUTPUT);
  //pinMode(GSM2, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  //pinMode(in3, OUTPUT);
  //pinMode(in4, OUTPUT);
  Motor.setSpeed(5);
}

void loop() {
  
  if (Serial.available())
  {
     char input = Serial.read();
     //Serial.println(input);
     // turns without hand
    if (input == '1') //quarter turn like y'
    {
      Serial.println("move_1");
      Motor.step(512);
      delay(500);
    }
    if (input == '2')
    {
      Serial.println("move_2"); // half turn like y2
      Motor.step(1024);
      delay(500);
    }
    if (input == '3') // reverse quarter turn like y
    {
      Serial.println("move_3");
      Motor.step(1536);
      delay(500);
    }
    // hand
    if (input == '4')
    {
      Serial.println("move_4");
      analogWrite(GSM1, 200);
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      delay(100);
      analogWrite(GSM1,50);
      delay(350);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(700);
    }
    if (input == '5')
    {
      Serial.println("move_5");
      analogWrite(GSM1, 200);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(200);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(700);
    }
    if (input == '6')
    {
      Serial.println("move_6");
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      analogWrite(GSM1, 200);
      delay(263); //move back
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(150); //wait
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(100); // move forward
      analogWrite(GSM1, 80);
      delay(160);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(800);
    }
    
  }
  

}
