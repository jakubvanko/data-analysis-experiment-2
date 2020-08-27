package com.jakubvanko.experiment;

import com.jakubvanko.experiment.actions.ActionFactory;
import picocli.CommandLine;
import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(
        name = "da-tool",
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        description = "%nPrototype for simple dataset analysis",
        mixinStandardHelpOptions = true,
        version = "2.0"
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
    private List<ActionFactory> manipulationMethods;

    @CommandLine.Option(
            names = {"-o", "--output-type"},
            description = "Output type (json, xml, plain)",
            defaultValue = "plain"
    )
    private OutputStrategyFactory outputStrategyFactory;

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
        try {
            setupAndTriggerActions();
            logSuccess();
        } catch (Exception e) {
            logFailure(e);
        }
    }

    private void setupAndTriggerActions() throws IOException {
        Table table = tableLoadingStrategy.loadTable(inputFile);
        OutputStrategy outputStrategy = outputStrategyFactory.createOutputStrategy(outputFile);
        for (ActionFactory actionFactory : manipulationMethods) {
            table = actionFactory.createAction(outputStrategy).triggerAction(table);
        }
        outputStrategy.save();
    }

    private void logSuccess() {
        System.out.println("All methods were applied successfully");
        System.out.println("The resulting file was saved to the specified location");
    }

    private void logFailure(Exception e) {
        System.out.println("There was an error");
        e.printStackTrace();
    }
}
