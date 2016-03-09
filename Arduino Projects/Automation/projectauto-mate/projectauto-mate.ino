#include <SPI.h>
#include <Ethernet.h>

#include <Servo.h>
Servo myservo;  //servo kullanmak istersek

byte mac[] = {
  0x90, 0xA2, 0xDA, 0x0F, 0x4A, 0x57
}; //mac address
byte ip[] = {
  10, 0, 4, 11
}; // seçilen ip
EthernetServer server(80);
byte gateway[] = {
  10, 0, 0, 253
};
byte subnet[] = {
  255, 255, 248, 0
};
String readString;

//////////////////////

void setup() {
  int freq = 0;
  pinMode(6, OUTPUT);
  //pinMode(10, OUTPUT); //karışmayın sd için gerekli
  Ethernet.begin(mac, ip, gateway, subnet); //ethernet başlangıç
  server.begin();
  Serial.begin(9600);
  Serial.println("loop");
  Serial.print("SD kurulamadı");
}

void loop() {
  EthernetClient client = server.available();
  if (client) {
    while (client.connected()) {
      if (client.available()) {
        char c = client.read();
        //read char by char HTTP request
        if (readString.length() < 100) {
          //store characters to string
          readString += c;
          //Serial.print(c);
        }
        if (readString.indexOf("?lighton") > 0) //checks for    on
        {
          digitalWrite(6, LOW);    // set pin 4 high
          Serial.println("Led On");
        }
        else {
          if (readString.indexOf("?lightoff") > 0 ) //checks for off
          {
            digitalWrite(6, HIGH);    // set pin 4 low
            Serial.println("Led Off");
          }
        }
        if (readString.indexOf("?light2on") > 0) //checks for on
        {
          digitalWrite(7, LOW);    // set pin 4 high
          Serial.println("Led On");
        }
        else {
          if (readString.indexOf("?light2off") > 0) //checks for off
          {
            digitalWrite(7, HIGH);    // set pin 4 lo
            Serial.println("Led Off");
          }
        }
        if (readString.indexOf("?ledon") > 0) //checks for on
        {
          digitalWrite(5, LOW);    // set pin 4 high
          Serial.println("Led On");
        }
        else {
          if (readString.indexOf("?ledoff") > 0 ) //checks for off
          {
            digitalWrite(5, HIGH);    // set pin 4 low
            Serial.println("Led Off");
          }
        }
        if (c == '\n') {
          ///////////////
          Serial.println(readString);
          client.println("HTTP/1.1 200 OK"); //send new page
          client.println("Content-Type: text/html");
          client.println();
          client.println("<HTML>");
          client.println("<HEAD>");
          client.println("<meta name='apple-mobile-web-app-capable' content='yes' />");
          client.println("<meta name='apple-mobile-web-app-status-bar-style' content='black-translucent' />");
          client.println("<link rel='stylesheet' type='text/css' href='http://homeautocss.net84.net/a.css' />");
          client.println("<TITLE>Control Panel</TITLE>");
          client.println("</HEAD>");
          client.println("<BODY>");
          client.println("<H1>Hisar CS Patates Control Panel</H1>");
          client.println("<br />");
          client.println("Software Version : v1.4beta2");
          client.println("<hr />");
          client.println("<br />");
          client.println(Ethernet.localIP());
          client.println("<br />");
          client.println("<br />");
          client.println("<a href=\"/?mode1\"\">mode-1</a>");
          client.println("<a href=\"/?mode2\"\">mode-2</a><br />");
          client.println("<br />");
          client.println("<a href=\"/?mode3\"\">mode-3</a>");
          client.println("<a href=\"/?mode4\"\">mode-4 </a><br />");
          client.println("<br />");
          client.println("<a href=\"/?mode5\"\">mode-5</a>");
          client.println("<a href=\"/?turnoff\"\">Turn Off</a><br />");
          client.println("<br />");

          client.print("Light 1: ");
          if (digitalRead(6) == HIGH) {
            client.print("is OFF");
          }
          else {
            client.print("is ON.");
          }

          client.println("<br />");
          client.print("Light 2: ");
          if (digitalRead(7) == HIGH) {
            client.print("is OFF.");
          }
          else {
            client.print("is ON.");
          }

          client.println("<br />");
          client.print("Led's : ");
          if (digitalRead(5) == HIGH) {
            client.print("are OFF.");
          }
          else {
            client.print("are ON.");
          }

          /*client.println("<br />");
          client.print("Nem ");
          client.println(DHT.humidity, 1);
          client.print("Sicaklik ");
          client.println(DHT.temperature, 1);
          */
          client.println("</BODY>");
          client.println("</HTML>");

          delay(1);

          client.stop();//bağlantı fin.


          readString = "";


        }
      }
    }
  }
  if (freq == 1) {
    D = 100;
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);        // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 10);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 10);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(500);              // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 20);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 5);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D);              // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)             // wait for a second             // wait for a second
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(D * 10);            // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(D * 2);            // wait for a second
  }
  else if (freq == 2) {
    digitalWrite(13, HIGH);
    delay(250);
    digitalWrite(13, LOW);
    delay(250);
  }
  else if (freq == 3) {
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(250);
    digitalWrite(13, LOW);   // turn the LED on (HIGH is the voltage level)
    delay(50);
    digitalWrite(13, HIGH);   // turn the LED on (HIGH is the voltage level)
    delay(250);    // wait for a second
    digitalWrite(13, LOW);    // turn the LED off by making the voltage LOW
    delay(1000);              // wait for a second
  }

