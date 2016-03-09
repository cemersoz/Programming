
int motor1A,motor1B, motor2A, motor2B, motor1E, motor2E, led1,led2, sensorF, sensorR, sensorL, qtrR, qtrL, butt1, butt2, butt3;

// the setup routine runs once when you press reset:
void setup() {
  // initialize serial communication at 9600 bits per second:
  Serial.begin(9600); 
  motor1A=A3;
  motor1B=4;
  motor1E=9;
  motor2A=12;
  motor2B=7;
  motor2E=3;
  sensorF=A4;
  sensorR=A1;
  sensorL=A2;
  qtrR=A3;
  qtrL=0;
  butt1=8;
  butt2=9;
  butt3=A0;
  led1=11;
  led2=10;
  pinMode(motor1A, OUTPUT);
  pinMode(motor1B, OUTPUT);
  pinMode(motor1E, OUTPUT);
  pinMode(motor2A, OUTPUT);
  pinMode(motor2B, OUTPUT);
  pinMode(motor2E, OUTPUT);
  pinMode(led1, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(sensorF, INPUT);
  pinMode(sensorR, INPUT);
  pinMode(sensorL, INPUT);
  pinMode(qtrL, INPUT);
  pinMode(qtrR, INPUT);
  pinMode(butt1, INPUT);
  pinMode(butt2, INPUT);
  pinMode(butt3, INPUT);
}

void forward(int speed){
  digitalWrite(motor1A,HIGH);
  digitalWrite(motor1B,LOW);
  digitalWrite(motor2A,HIGH);
  digitalWrite(motor2B,LOW); 
  if (speed >= 0 && speed <= 255){
    analogWrite(motor1E, speed);
    analogWrite(motor2E, speed);
  }
}

int program(){
  int a=0;
  while(!digitalRead(butt3)){
    while(digitalRead(butt2)||digitalRead(butt1)){
      if(!digitalRead(butt2)&&a<10){
        a++;
        }
      else if(!digitalRead(butt3)){
        a=0;
        }
    }
    delay(200);
    for(int b=1; b<=a; b++){
      digitalWrite(led1, HIGH);
      delay(200);
      digitalWrite(led1, LOW);
      delay(200);
    }
    delay(1000);
  }
  return a;
}
void backward(int speed){
  digitalWrite(motor1A,LOW);
  digitalWrite(motor1B,HIGH);
  digitalWrite(motor2A,LOW);
  digitalWrite(motor2B,HIGH); 
  if (speed >= 0 && speed <= 255){
    analogWrite(motor1E, speed);
    analogWrite(motor2E, speed);
  }
}

void turnRight(int speed1, int speed2){
  digitalWrite(motor1A,HIGH);
  digitalWrite(motor1B,LOW);
  if(speed2>0){
    digitalWrite(motor2A,LOW);
    digitalWrite(motor2B,HIGH); 
  }
  if (speed1 >= 0 && speed1 <= 255){
    analogWrite(motor1E, speed1);
  }
  if (speed2 >= 0 && speed2 <= 255){
    analogWrite(motor2E, speed2);
  }
  else if (speed2 <= 0 && speed2 >= -255){
    analogWrite(motor2E, -speed2);
  }
}

void turnLeft(int speed1, int speed2){
  digitalWrite(motor2A,HIGH);
  digitalWrite(motor2B,LOW);
  if(speed2>0){
    digitalWrite(motor1A,LOW);
    digitalWrite(motor1B,HIGH); 
  }
  if(speed2<0){
    digitalWrite(motor1A,HIGH);
    digitalWrite(motor1B,LOW); 
  }
  if (speed1 >= 0 && speed1 <= 255){
    analogWrite(motor2E, speed1);
  }
  if (speed2 >= 0 && speed2 <= 255){
    analogWrite(motor1E, speed2);
  }
  else if (speed2 <= 0 && speed2 >= -255){
    analogWrite(motor1E, -speed2);
  }
}

// the loop routine runs over and over again forever:
void loop() {
  digitalWrite(led1,HIGH);
  digitalWrite(led2,LOW);
  delay(1000);
  digitalWrite(led2,HIGH);
  digitalWrite(led1,LOW);
  delay(1000);
  digitalWrite(led1,HIGH);
  digitalWrite(led2,LOW);
  delay(1000);
  digitalWrite(led2,HIGH);
  digitalWrite(led1,LOW);
  delay(1000);
  for(int i=0;i<255;i++){
    forward(i);
    delay(100);
    }
  digitalWrite(motor1A,LOW);
  digitalWrite(motor1B,LOW);
  digitalWrite(motor1E,LOW);
  digitalWrite(motor2A,LOW);
  digitalWrite(motor2B,LOW);
  digitalWrite(motor2E,LOW);
  delay(1000);
    for(int i=0;i<255;i++){
    backward(i);
    delay(100);
    }
  }


