package ru.sbt.mipt.oop;


public class AlarmSystemStateWaitPassword implements AlarmSystemState {

    private AlarmSystem alarmSystem;

    public AlarmSystemStateWaitPassword(AlarmSystem alarmSystem) {
        this.alarmSystem = alarmSystem;
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
        if(alarmSystem.checkPassword(password)) {
            alarmSystem.setCurrentState(new AlarmSystemStateOn(alarmSystem));
        }
    }


    @Override
    public void onSensorEvent(SensorEvent event) {

    }

}
