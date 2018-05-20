#include <Servo.h>


int pinServos[] = {2, 3, 4, 5, 6, 7};
Servo servos[6];
//const int

void setup() {
  Serial.begin(9600);
  // put your setup code here, to run once:
  //for (int i = 0; i < 6; i++)
  servos[0].attach(5);

}
int maxGrades = 180;
int minGrades = 0;
//100-left
//200-right

void loop() {
  Serial.println("0-180");
  //
  delay(1000);
  // put your main code here, to run repeatedly:
  //left
  for (int i = minGrades; i < maxGrades; i++) {
    servos[0].write(i);
    delay(25);

  }
  delay(1000);
  Serial.println("180-0");
  for (int i = maxGrades; i == minGrades; i--) {
    servos[0].write(i);
    delay(25);
  }
}

