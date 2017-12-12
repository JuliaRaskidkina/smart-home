package ru.sbt.mipt.oop;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonStateReader implements StateReader {
    @Override
    public SmartHome read(String path) throws IOException {
        Gson gson = new Gson();

        String json = new String(Files.readAllBytes(Paths.get(path)));
        SmartHome smartHome = gson.fromJson(json, SmartHome.class);
        return smartHome;

    }
}
