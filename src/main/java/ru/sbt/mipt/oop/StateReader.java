package ru.sbt.mipt.oop;

import java.io.IOException;

public interface StateReader {
     SmartHome read(String path) throws IOException;
}
