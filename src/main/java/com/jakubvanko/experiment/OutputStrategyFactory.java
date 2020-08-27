package com.jakubvanko.experiment;

import java.nio.file.Path;

public enum OutputStrategyFactory {
    PLAIN {
        public OutputStrategy createOutputStrategy(Path path) {
            return new PlainOutputStrategy(path);
        }
    },
    XML {
        public OutputStrategy createOutputStrategy(Path path) {
            // XML strategy is not yet implemented
            return new PlainOutputStrategy(path);
        }
    },
    JSON {
        public OutputStrategy createOutputStrategy(Path path) {
            // JSON strategy is not yet implemented
            return new PlainOutputStrategy(path);
        }
    };

    public abstract OutputStrategy createOutputStrategy(Path path);
}
