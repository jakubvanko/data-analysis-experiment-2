package com.jakubvanko.experiment;

import java.io.IOException;

public interface OutputStrategy {
    void write(String key, String value);
    void write(String value);
    void createSection(String header);
    void finishSection();
    void resetLevel();
    void save() throws IOException;
}
