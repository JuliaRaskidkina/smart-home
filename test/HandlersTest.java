import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HandlersTest {

    @Test
    public void TestOpenDoorEventProcessor() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler handler = new DoorEventHandler(smartHome);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        handler.handle(event);

        Door myDoor = null;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    myDoor = door;
                }
            }
        }
        assertTrue(myDoor.isOpen());

    }


    @Test
    public void TestCloseDoorEventProcessor() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler handler = new DoorEventHandler(smartHome);
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_CLOSED, "1");
        handler.handle(event);

        Door myDoor = null;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    myDoor = door;
                }
            }
        }
        assertFalse(myDoor.isOpen());

    }


    @Test
    public void TestLightOffEventProcessor() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler handler = new LightEventHandler(smartHome);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_OFF, "1");
        handler.handle(event);

        Light myLight = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    myLight = light;
                }
            }
        }
        assertFalse(myLight.isOn());

    }

    @Test
    public void TestLightOnEventProcessor() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler handler = new LightEventHandler(smartHome);
        SensorEvent event = new SensorEvent(SensorEventType.LIGHT_ON, "1");
        handler.handle(event);

        Light myLight = null;
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(event.getObjectId())) {
                    myLight = light;
                }
            }
        }

        assertTrue(myLight.isOn());
    }
}
