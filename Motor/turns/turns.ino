//Gleichstrommotor 1
int GSM1 = 10;
int in1 = 9;
int in2 = 8;

// Gleichstrommotor 2

int GSM2 = 5;
int in3 = 7;
int in4 = 6;

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
     //Serial.println(input);
     // turns without hand
    if (input == '1') //quarter turn like y'
    {
      Serial.println("move_1");
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(100);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20); // counter rotation to stop the motor
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(720);
    }
    if (input == '2')
    {
      Serial.println("move_2"); // half turn like y2
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(200);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(740);
    }
    if (input == '3') // reverse quarter turn like y
    {
      Serial.println("move_3");
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      analogWrite(GSM1, 255);
      delay(100);
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(720);
    }
    // turns with hand
    if (input == '4') //quarter turn like y'
    {
      Serial.println("move_4");
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(141);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20); // counter rotation to stop the motor
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(720);
    }
    if (input == '5')
    {
      Serial.println("move_5"); // half turn like y2
     digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      analogWrite(GSM1, 255);
      delay(270);
      digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(740);
    }
    if (input == '6') // reverse quarter turn like y
    {
      Serial.println("move_6");
     digitalWrite(in1, LOW);
      digitalWrite(in2, HIGH);
      analogWrite(GSM1, 255);
      delay(141);
      digitalWrite(in1, HIGH);
      digitalWrite(in2, LOW);
      delay(20);
      digitalWrite(in1, LOW);
      digitalWrite(in2, LOW);
      delay(720);
    }
    // hand
    if (input == '7')
    {
      Serial.println("move_7");
      analogWrite(GSM2, 200);
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      delay(175);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(700);
    }
    if (input == '8')
    {
      Serial.println("move_8");
      analogWrite(GSM2, 200);
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      delay(195);
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(700);
    }
    if (input == '9')
    {
      Serial.println("move_9");
      digitalWrite(in3, HIGH);
      digitalWrite(in4, LOW);
      analogWrite(GSM2, 200);
      delay(200); //move back
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(300); //wait
      digitalWrite(in3, LOW);
      digitalWrite(in4, HIGH);
      analogWrite(GSM2, 255);
      delay(110); // move forward
      digitalWrite(in3, LOW);
      digitalWrite(in4, LOW);
      delay(600);
    }
    
  }
  

}
