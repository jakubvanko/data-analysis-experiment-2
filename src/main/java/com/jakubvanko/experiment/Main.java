package com.jakubvanko.experiment;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {
        CommandManager commandManager = new CommandManager();
        CommandLine cl = new CommandLine(commandManager);
        cl.setCaseInsensitiveEnumValuesAllowed(true);
        cl.execute(args);
    }
}
