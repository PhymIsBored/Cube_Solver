# 1 "d:\\Cube_Solver\\MotorTest\\test\\test.ino"
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
  pinMode(GSM1, 0x1);
  pinMode(GSM2, 0x1);
  pinMode(in1, 0x1);
  pinMode(in2, 0x1);
  pinMode(in3, 0x1);
  pinMode(in4, 0x1);
}

void loop() {
  if (Serial.available())
  {
     char input = Serial.read();
    if (input == '1') //Viertel drehung des Tellers (D')
    {
      Serial.println("move1");
      digitalWrite(in1, 0x1);
      digitalWrite(in2, 0x0);
      analogWrite(GSM1, 255);
      delay(413);
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x1);
      delay(20);
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x0);
      delay(500);
    }
    if (input == '2')
    {
      Serial.println("move2"); // Halbe Drehung des Tellers (D2)
      digitalWrite(in1, 0x1);
      digitalWrite(in2, 0x0);
      analogWrite(GSM1, 255);
      delay(820);
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x1);
      delay(20);
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x0);
      delay(500);
    }
    if (input == '3')
    {
      Serial.println("move3");
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x1);
      analogWrite(GSM1, 255);
      delay(413);
      digitalWrite(in1, 0x1);
      digitalWrite(in2, 0x0);
      delay(20);
      digitalWrite(in1, 0x0);
      digitalWrite(in2, 0x0);
      delay(500);
    }
    if (input == '4')
    {
      Serial.println("move4");
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x1);
      analogWrite(GSM2, 255);
      delay(200);
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x0);
      delay(500);
    }
    if (input == '5')
    {
      Serial.println("move5");
      digitalWrite(in3, 0x1);
      digitalWrite(in4, 0x0);
      analogWrite(GSM2, 200);
      delay(200);
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x0);
      delay(500);
    }
    if (input == '6')
    {
      Serial.println("move6");
      digitalWrite(in3, 0x1);
      digitalWrite(in4, 0x0);
      analogWrite(GSM2, 200);
      delay(300);
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x0);
      delay(500);
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x1);
      analogWrite(GSM2, 255);
      delay(200);
      digitalWrite(in3, 0x0);
      digitalWrite(in4, 0x0);
      delay(500);
    }

  }


}
