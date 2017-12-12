import org.junit.Test;
import ru.sbt.mipt.oop.*;

import static org.junit.Assert.assertTrue;

public class AlarmSystemTest {
    private final SensorEvent event = new SensorEvent(SensorEventType.DOOR_OPEN, "1");
    private final String password = "1234";

    @Test
    public void testFromOffState(){
        AlarmSystem alarmSystem = new AlarmSystem(password);

        alarmSystem.turnOn();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateWaitPassword);

        alarmSystem = new AlarmSystem(password);
        alarmSystem.enterPassword("1234");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);

        alarmSystem = new AlarmSystem(password);
        alarmSystem.enterPassword("658534");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);

        alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOff();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);

        alarmSystem = new AlarmSystem(password);
        alarmSystem.onSensorEvent(event);
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);
    }

    private AlarmSystem getOnState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOn();
        alarmSystem.enterPassword("1234");
        return alarmSystem;
    }

    @Test
    public void testFromOnState(){
        AlarmSystem alarmSystem = getOnState();
        alarmSystem.turnOn();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOn);

        alarmSystem = getOnState();
        alarmSystem.enterPassword("1234");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOn);

        alarmSystem = getOnState();
        alarmSystem.onSensorEvent(event);
        assertTrue(alarmSystem.getState() instanceof AlarmSystemAlarmState);

        alarmSystem = getOnState();
        alarmSystem.turnOff();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);
    }

    private AlarmSystem getWaitForPasswordState() {
        AlarmSystem alarmSystem = new AlarmSystem(password);
        alarmSystem.turnOn();
        return alarmSystem;
    }

    @Test
    public void testFromWaitForPasswordState() {

        AlarmSystem alarmSystem = getWaitForPasswordState();
        alarmSystem.turnOn();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateWaitPassword);


        alarmSystem = getWaitForPasswordState();
        alarmSystem.onSensorEvent(event);
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateWaitPassword);


        alarmSystem = getWaitForPasswordState();
        alarmSystem.enterPassword("43634564");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateWaitPassword);

        alarmSystem = getWaitForPasswordState();
        alarmSystem.turnOff();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOff);

        alarmSystem = getWaitForPasswordState();
        alarmSystem.enterPassword("1234");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOn);


    }

    private AlarmSystem getAlarmState() {
        AlarmSystem system = new AlarmSystem(password);
        system.turnOn();
        system.enterPassword("1234");
        system.onSensorEvent(event);
        return system;
    }

    @Test
    public void testFromAlarmState() {
        AlarmSystem alarmSystem = getAlarmState();
        alarmSystem.turnOn();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemAlarmState);


        alarmSystem = getAlarmState();
        alarmSystem.onSensorEvent(event);
        assertTrue(alarmSystem.getState() instanceof AlarmSystemAlarmState);


        alarmSystem = getAlarmState();
        alarmSystem.enterPassword("567563");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemAlarmState);

        alarmSystem = getAlarmState();
        alarmSystem.turnOff();
        assertTrue(alarmSystem.getState() instanceof AlarmSystemAlarmState);

        alarmSystem = getAlarmState();
        alarmSystem.enterPassword("1234");
        assertTrue(alarmSystem.getState() instanceof AlarmSystemStateOn);
    }


}