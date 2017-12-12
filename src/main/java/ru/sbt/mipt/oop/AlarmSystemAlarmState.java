package ru.sbt.mipt.oop;

public class AlarmSystemAlarmState implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemAlarmState(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
    }

    @Override
    public void turnOn() {

    }

    @Override
    public void turnOff() {

    }

    @Override
    public void enterPassword(String password) {
        if (alarmSystem.checkPassword(password)) {
            alarmSystem.setCurrentState(new AlarmSystemStateOn(alarmSystem));
        }
    }


    @Override
    public void onSensorEvent(SensorEvent event) {

    }



}
