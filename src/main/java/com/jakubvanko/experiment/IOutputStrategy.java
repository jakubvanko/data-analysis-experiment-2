package com.jakubvanko.experiment;

public interface IOutputStrategy {
    void write(String key, String value);
    void write(String value);
    void increaseLevel(String header);
    void decreaseLevel();
    void resetLevel();
    void save();
}
