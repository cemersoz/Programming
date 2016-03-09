int statePin = A0;
int state = 0;
int oldState = 0;
int manualOverride = A1;
int binaryOne = A2;
int binaryTwo = A3;
boolean overRidden;
boolean stateChanged;
String str1 = "00";
String str2 = "00";
String strNew = "0000";
String line[4] = {"0000", "0000", "0000", "0000"};
int wall[4][4] = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
void setup() {
  for (int i = 0; i <= 13; i++) {
    pinMode(i, OUTPUT);
  }
  pinMode(A4, OUTPUT);
  pinMode(A5, OUTPUT);
  pinMode(A0, OUTPUT);
  pinMode(A1, OUTPUT);
  pinMode(A2, OUTPUT);
  pinMode(A3, OUTPUT);
}
void loop() {
  changeVars();
  if (!overRidden) {
    if (oldState = 0 && state != 0) {
      changeLines();
    }
    writeToWall();
    delay(250);
  }
  oldState = state;
}
void writeToWall() {
  for (int q = 0; q < 4; q++) {
    for (int i = 0; i < 4; i++) {
      if (line[q].charAt(i) == '0') {
        digitalWrite(wall[q][i], LOW);
      }
      else {
        digitalWrite(wall[q][i], HIGH);
      }
    }
  }
}
void changeLines() {
  if (state == 1) {
    line[0] = strNew;
  }
  else if (state == 2) {
    line[1] = strNew;
  }
  else if (state == 3) {
    line[2] = strNew;
  }
  else if (state == 4) {
    line[3] = strNew;
  }
}
void changeVars() {
  overRidden = digitalRead(manualOverride);
  if (analogRead(statePin) < 50) {
    state = 0;
  }
  else if (analogRead(statePin) < 100) {
    state = 1;
  }
  else if (analogRead(statePin) < 150) {
    state = 2;
  }
  else if (analogRead(statePin) < 200) {
    state = 3;
  }
  else {
    state = 4;
  }
  if (analogRead(binaryOne) < 60) {
    str1 = "00";
  }
  else if (analogRead(binaryOne) < 120) {
    str1 = "01";
  }
  else if (analogRead(binaryOne) < 180) {
    str1 = "10";
  }
  else {
    str1 = "11";
  }
  if (analogRead(binaryTwo) < 60) {
    str2 = "00";
  }
  else if (analogRead(binaryTwo) < 120) {
    str2 = "01";
  }
  else if (analogRead(binaryTwo) < 180) {
    str2 = "10";
  }
  else {
    str2 = "11";
  }
  strNew = str1 + str2;
}
