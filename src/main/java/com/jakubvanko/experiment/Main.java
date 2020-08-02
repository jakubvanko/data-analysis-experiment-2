package com.jakubvanko.experiment;

import picocli.CommandLine;

public class Main {

    public static void main(String[] args) {
        ITableLoadingStrategy tableLoadingStrategy = new CsvTableLoadingStrategy();
        CommandManager commandManager = new CommandManager(tableLoadingStrategy);
        CommandLine cl = new CommandLine(commandManager);
        cl.setCaseInsensitiveEnumValuesAllowed(true);
        cl.execute(args);
    }
}
