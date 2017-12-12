import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class CompositeTest {
    private final String lightId = "1";
    @Test
    public void TestComposite() throws IOException {
        SmartHome home = new JsonStateReader().read("smart-home-1.js");

        home.executeAction(new Action() {
            @Override
            public void execute(Object object, Object parent) {
                if(object instanceof Light && parent instanceof Room) {
                    Light light = (Light) object;

                    if(light.getId().equals(lightId)) {
                        light.setOn(true);
                    }
                }
            }
        }, this);

        Light light = null;
        for (Room room : home.getRooms()) {
            for (Light light1 : room.getLights()) {
                if (light1.getId().equals(lightId)) {
                    light = light1;
                }
            }
        }

        assertTrue(light.isOn());
    }
}
