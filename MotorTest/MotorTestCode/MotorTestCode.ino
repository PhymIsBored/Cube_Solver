int GSM1 = 10;
int in1 = 9;
int in2 = 8;

// Gleichstrommotor 2

int GSM2 = 5;
int in3 = 7;
int in4 = 6;

void setup()
{
  pinMode(GSM1, OUTPUT);    
  pinMode(GSM2, OUTPUT);
  pinMode(in1, OUTPUT);
  pinMode(in2, OUTPUT);
  pinMode(in3, OUTPUT);
  pinMode(in4, OUTPUT);
}
void loop()
{
  digitalWrite(in1, HIGH);  // Motor 1 beginnt zu rotieren
  digitalWrite(in2, LOW);

  analogWrite(GSM1, 100);   // Motor 1 soll mit der Geschwindigkeit "200" (max. 255) rotieren 

  digitalWrite(in3, HIGH);  // Motor 2 beginnt zu rotieren
  digitalWrite(in4, LOW);

  analogWrite(GSM2, 100);   // Motor 2 soll ebenfalls mit der Geschwindigkeit "200" (max. 255) rotieren
  delay(650);

  digitalWrite(in1, LOW);
  digitalWrite(in2, LOW);
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);
  delay(1000);
  
  digitalWrite(in1, HIGH);   // Durch die Veränderung von HIGH auf LOW (bzw. LOW auf HIGH) wird die Richtung der Rotation verändert.
  digitalWrite(in2, LOW);  
  digitalWrite(in3, HIGH);
  digitalWrite(in4, LOW); 
  delay(1300);

  digitalWrite(in1, LOW);   // Anschließend sollen die Motoren 2 Sekunden ruhen.
  digitalWrite(in2, LOW);  
  digitalWrite(in3, LOW);
  digitalWrite(in4, LOW);

  delay(5000);
}
