package ru.sbt.mipt.oop;


public class AlarmSystemStateOn implements AlarmSystemState {
    private AlarmSystem alarmSystem;

    public AlarmSystemStateOn(AlarmSystem system) {
        this.alarmSystem = system;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {
        alarmSystem.setCurrentState(new AlarmSystemStateOff(alarmSystem));
    }

    @Override
    public void enterPassword(String password) {

    }

    @Override
    public void onSensorEvent(SensorEvent event) {
            alarmSystem.setCurrentState(new AlarmSystemAlarmState(alarmSystem));
    }

}
