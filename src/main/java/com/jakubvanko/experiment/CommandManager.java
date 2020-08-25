package com.jakubvanko.experiment;

import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(
        name = "da-tool",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        description = "%nPrototype for simple dataset analysis"
)
public class CommandManager implements Runnable {
    @CommandLine.Option(
            names = {"-d", "--dataset"},
            description = "Path to remote (HTTP link) or local CSV file",
            converter = InputFileTypeConverter.class,
            required = true
    )
    private File inputFile;

    @CommandLine.Option(
            names = {"-m", "--methods"},
            description = "Manipulation methods",
            arity = "1..*",
            required = true
    )
    private List<Actions> manipulationMethods;

    @CommandLine.Option(
            names = {"-o", "--output-type"},
            description = "Output type (json, xml, plain)",
            defaultValue = "plain"
    )
    private String outputType;

    @CommandLine.Parameters(
            index = "0",
            description = "Path of the output file",
            defaultValue = "result.csv"
    )
    private Path outputFile;

    private final TableLoadingStrategy tableLoadingStrategy;

    public CommandManager(TableLoadingStrategy tableLoadingStrategy) {
        this.tableLoadingStrategy = tableLoadingStrategy;
    }

    @Override
    public void run() {
        Table table = tableLoadingStrategy.loadTable(inputFile);
        OutputStrategyFactory outputStrategyFactory = new OutputStrategyFactory();
        OutputStrategy outputStrategy = outputStrategyFactory.createOutputStrategy(outputType, outputFile);
        for (Actions actions : manipulationMethods) {
            outputStrategy.resetLevel();
            table = actions.examineTable(table, outputStrategy);
        }
        outputStrategy.save();
        System.out.println("All methods were applied successfully");
        System.out.println("The resulting file was saved to the specified location");
    }
}
