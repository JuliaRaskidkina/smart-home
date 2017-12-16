import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CompositeTest {

    @Test
    public void TestComposite() throws IOException {
        SmartHome home = new JsonStateReader().read("smart-home-1.js");
        List<Room> rooms = new ArrayList<>();
        List<Light> lights = new ArrayList<>();
        List<Door> doors = new ArrayList<>();
        List<SmartHome> homes = new ArrayList<>();
        home.executeAction(new Action() {
            @Override
            public void execute(Object object, Object parent) {
                if (object instanceof  Room) {
                    rooms.add((Room) object);
                } else if(object instanceof Light) {
                    lights.add((Light) object);
                } else if(object instanceof Door) {
                    doors.add((Door) object);
                } else {
                    homes.add((SmartHome) object);
                }
            }
        }, this);

        int roomCount = 0;
        int lightCount = 0;
        int doorCount = 0;

        for (Room room : home.getRooms()) {
            lightCount += room.getLights().size();
            doorCount += room.getDoors().size();
            roomCount++;
        }

        assertEquals(rooms.size(), roomCount);
        assertEquals(lights.size(), lightCount);
        assertEquals(doors.size(), doorCount);
        assertEquals(homes.size(), 1);
    }
}
