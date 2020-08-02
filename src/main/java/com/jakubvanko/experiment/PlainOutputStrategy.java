package com.jakubvanko.experiment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PlainOutputStrategy implements IOutputStrategy {

    private int currentLevel = 0;
    private final File file;

    public PlainOutputStrategy(Path path) {
        this.file = path.toFile();
    }

    @Override
    public void write(String key, String value) {
        try {
            FileUtils.writeStringToFile(file, formatString(key, value));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String value) {
        try {
            FileUtils.writeStringToFile(file, formatString(value));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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

    private String formatString(String string) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < currentLevel; i++) {
            stringBuilder.append("    ");
        }
        stringBuilder.append(string);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }

    private String formatString(String key, String value) {
        return formatString(key + ": " + value);
    }
}
