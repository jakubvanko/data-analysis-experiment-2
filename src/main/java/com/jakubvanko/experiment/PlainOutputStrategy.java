package com.jakubvanko.experiment;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PlainOutputStrategy implements OutputStrategy {

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
    public void createSection(String header) {
        write(header);
        currentLevel += 1;
    }

    @Override
    public void finishSection() {
        if (currentLevel > 0) {
            currentLevel -= 1;
        }
    }

    @Override
    public void resetLevel() {
        currentLevel = 0;
    }

    @Override
    public void save() throws IOException {
        FileUtils.writeLines(file, lines);
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
