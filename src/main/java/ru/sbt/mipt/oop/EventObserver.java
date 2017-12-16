package ru.sbt.mipt.oop;

import java.util.ArrayList;
import java.util.List;

public class EventObserver {

    private List<EventHandler> handlers = new ArrayList<>();

    public EventObserver(List<EventHandler> handlers) {
        this.handlers = handlers;
    }

    public void addHandler(EventHandler handler) {
        handlers.add(handler);
    }

    public void startExecutionCycle() {

        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            notifyAll(event);
            event = getNextSensorEvent();
        }
    }

    public void notifyAll(SensorEvent event) {
        for (EventHandler handler : handlers) {
            handler.handle(event);
        }
    }

    private  SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventType.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        return new SensorEvent(sensorEventType, objectId);
    }

    public void onSensorEvent(SensorEvent event) {
        System.out.println("Got event: " + event.getType());
        for (EventHandler handler: handlers) {
            handler.handle(event);
        }
    }


}
