package ru.sbt.mipt.oop;

public class AlarmSystem {
    private AlarmSystemState state;
    private String password;

    public AlarmSystem(String password) {
        this.state = new AlarmSystemStateOff(this);
        this.password = password;
    }



    public void turnOn() {
        state.turnOn();
    }

    public void turnOff() {
        state.turnOff();
    }

    public void enterPassword(String password) {
        state.enterPassword(password);
    }

    public void onSensorEvent(SensorEvent event) {
        state.onSensorEvent(event);
    }

    public void setCurrentState(AlarmSystemState state) {
        this.state = state;
    }

    public AlarmSystemState getState() {
        return state;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }
}
