package ru.sbt.mipt.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonStateWriter implements StateWriter {

    @Override
    public void saveState(SmartHome home) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(home);
        System.out.println(jsonString);
        Path path = Paths.get("output.js");
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write(jsonString);
        }
    }
}
