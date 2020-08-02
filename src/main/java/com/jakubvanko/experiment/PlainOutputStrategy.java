package com.jakubvanko.experiment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlainOutputStrategy implements IOutputStrategy {

    private int currentLevel = 0;
    private final File file;
    private final List<String> lines;

    public PlainOutputStrategy(Path path) {
        this.file = path.toFile();
        lines = new ArrayList<>();
    }

    @Override
    public void write(String key, String value) {
        write(key + ": " + value);
    }

    @Override
    public void write(String value) {
        lines.add(formatString(value));
    }

    @Override
    public void increaseLevel(String header) {
        write(header);
        currentLevel += 1;
    }

    @Override
    public void decreaseLevel() {
        if (currentLevel > 0) {
            currentLevel -= 1;
        }
    }

    @Override
    public void resetLevel() {
        currentLevel = 0;
    }

    @Override
    public void save() {
        try {
            FileUtils.writeLines(file, lines);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formatString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < currentLevel; i++) {
            stringBuilder.append("    ");
        }
        stringBuilder.append(string);
        return stringBuilder.toString();
    }
}
