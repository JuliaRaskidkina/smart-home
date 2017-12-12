package ru.sbt.mipt.oop;


public interface AlarmSystemState {
    void turnOn();
    void turnOff();
    void enterPassword(String password);
    void onSensorEvent(SensorEvent event);
}
