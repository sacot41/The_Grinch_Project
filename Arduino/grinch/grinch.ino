#include <SoftwareSerial.h>
#include "SerialCommand.h"

#define COMMAND_PIN 13
#define STATUS_ON true
#define STATUS_OFF false

boolean command_status;
SerialCommand SCmd;

void setup() {
  Serial.begin(9600);
  
  pinMode(COMMAND_PIN, OUTPUT);
  setOff();
  
  SCmd.addCommand("ON",setOn);
  SCmd.addCommand("OFF",setOff);
  SCmd.addCommand("STATUS",getState);
  //Serial.println("ready");
}

void loop() {
  SCmd.readSerial();
}

void setOn() {
  command_status = STATUS_ON;
  digitalWrite(COMMAND_PIN, HIGH);
}

void setOff() {
  command_status = STATUS_OFF;
  digitalWrite(COMMAND_PIN,LOW);
}

void getState() {
  if (command_status == STATUS_ON) {
    Serial.write("N");
  } else {
    Serial.write("F");
  }
}
