package ru.sbt.mipt.oop;

public class LightEventHandler implements EventHandler {

    private SmartHome smartHome;

    public LightEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void handle(SensorEvent event) {
        if(!isLightEvent(event)) {
            return;
        }

        smartHome.executeAction((object, parent) -> {

            if(object instanceof Light && parent instanceof Room) {
                Room room = (Room) parent;
                Light light = (Light) object;
                if(light.getId().equals(event.getObjectId())) {
                    changeLightState(light, room, event);
                }
            }
        }, this);
    }

    private void changeLightState(Light light, Room room, SensorEvent event) {
        if(event.getType() == SensorEventType.LIGHT_OFF) {
            light.setOn(false);
            System.out.println("Light " + light.getId() + " in " + room.getName() + " is turned off");
        } else {
            light.setOn(true);
            System.out.println("Light " + light.getId() + " in " + room.getName() + " is turned on");
        }
    }

    private boolean isLightEvent(SensorEvent event) {
        return event.getType() == SensorEventType.LIGHT_ON || event.getType() == SensorEventType.LIGHT_OFF;
    }


}
