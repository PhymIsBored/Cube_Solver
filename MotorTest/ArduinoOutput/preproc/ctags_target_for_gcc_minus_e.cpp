# 1 "d:\\Cube_Solver\\MotorTest\\test\\test.ino"
String input = ("hi");

void setup() {
  Serial.begin(9600);
  // put your setup code here, to run once:
}

void loop() {
  if (Serial.available())
  {
     char input = Serial.read();
    if (input == '1')
    {
      Serial.println("move1");
    }
    if (input == '2')
    {
      Serial.println("move2");
    }
    if (input == '3')
    {
      Serial.println("move3");
    }
    if (input == '4')
    {
      Serial.println("move4");
    }
    if (input == '5')
    {
      Serial.println("move5");
    }
    if (input == '6')
    {
      Serial.println("move6");
    }

  }


}
