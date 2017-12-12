package ru.sbt.mipt.oop;


import java.io.IOException;

public interface StateWriter {
    void saveState(SmartHome home) throws IOException;
}
