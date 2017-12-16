import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

public class ObserverTest {
    @Test
    public void TestEventObserver() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler lightEventHandler = new LightEventHandler(smartHome);
        EventHandler doorEventHandler = new DoorEventHandler(smartHome);
        EventObserver observer = new EventObserver(asList(lightEventHandler, doorEventHandler));
        SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
        observer.onSensorEvent(event);

        Door testDoor = null;
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals("1")) {
                    testDoor = door;
                    break;
                }
            }
        }
        assertTrue(testDoor.isOpen());
    }
}
