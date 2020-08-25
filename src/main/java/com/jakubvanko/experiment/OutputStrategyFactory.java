package com.jakubvanko.experiment;

import java.nio.file.Path;

public class OutputStrategyFactory {
    public OutputStrategy createOutputStrategy(String type, Path path) {
        switch (type) {
            case "plain": {
                return new PlainOutputStrategy(path);
            }
            case "xml": {
                // Duplicated line since xml is not yet supported
                return new PlainOutputStrategy(path);
            }
            case "json": {
                // Duplicated line since json is not yet supported
                return new PlainOutputStrategy(path);
            }
            default: {
                return new PlainOutputStrategy(path);
            }
        }
    }
}
