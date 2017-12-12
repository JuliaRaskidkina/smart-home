package ru.sbt.mipt.oop;


public class AlarmSystemStateOff implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemStateOff(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {
        alarmSystem.setCurrentState(new AlarmSystemStateWaitPassword(alarmSystem));
    }

    @Override
    public void turnOff() {

    }

    @Override
    public void enterPassword(String password) {

    }

    @Override
    public void onSensorEvent(SensorEvent event) {

    }

}
