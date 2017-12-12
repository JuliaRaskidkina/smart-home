package ru.sbt.mipt.oop;


import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler {

    private SmartHome smartHome;
    private EventHandler lightEventHandler;


    public DoorEventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void handle(SensorEvent event) {
        if (!isDoorEvent(event)) {
            return;
        }

        smartHome.executeAction((object, parent) -> {

            if (parent instanceof Room && object instanceof Door) {
                Room room = (Room) parent;

                Door door = (Door) object;
                if (door.getId().equals(event.getObjectId())) {
                    changeDoorState(door, room, event);
                }
            }
        }, this);

    }

    private void changeDoorState(Door door, Room room, SensorEvent event) {
        if (event.getType() == SensorEventType.DOOR_CLOSED) {

            door.setOpen(false);
            System.out.println("Door " + door.getId() + " in " + room.getName() + " is closed");
            if (room.getName().equals("hall")) {
                switchAllLightsOff();
            }


        } else {
            door.setOpen(true);
            System.out.println("Door " + door.getId() + " in " + room.getName() + " is open");
        }
    }

    private void switchAllLightsOff() {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " in " + room.getName() + " is turned off");
            }
        }
    }

    private boolean isDoorEvent(SensorEvent event) {
        return event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED;
    }

}
