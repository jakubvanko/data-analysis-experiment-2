package com.jakubvanko.experiment;

import java.io.IOException;

public interface IOutputStrategy {
    void write(String key, String value);
    void write(String value);
    void increaseLevel(String header);
    void decreaseLevel();
    void resetLevel();
}
