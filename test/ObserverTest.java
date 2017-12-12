import org.junit.Test;
import ru.sbt.mipt.oop.*;

import java.io.IOException;

import static java.util.Arrays.asList;

public class ObserverTest {
    @Test
    public void TestEventObserver() throws IOException {
        StateReader stateReader = new JsonStateReader();

        SmartHome smartHome = stateReader.read("smart-home-1.js");
        EventHandler lightEventHandler = new LightEventHandler(smartHome);
        EventHandler doorEventHandler = new DoorEventHandler(smartHome);
        EventObserver observer = new EventObserver(asList(lightEventHandler, doorEventHandler));
        observer.startExecutionCycle();

    }
}
