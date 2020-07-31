package com.jakubvanko.experiment;

import picocli.CommandLine;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

@CommandLine.Command(
        name = "da-tool",
        sortOptions = false,
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        description = "%nPrototype for simple dataset analysis"
)
public class Main implements Runnable {

    @CommandLine.Option(names = "-d", description = "Path to remote or local CSV file", converter = InputFileTypeConverter.class)
    private File inputFile;

    @CommandLine.Option(names = "-m", description = "Manipulation methods", arity = "")
    private List<String> manipulationMethods;

    @CommandLine.Option(names = "-o", description = "Output type (json, xml, plain)")
    private String outputType = "plain";

    //@CommandLine.Parameters(index = "0", description = "Path of the output file")
    private Path outputFile;

    public static void main(String[] args) {
        Main main = new Main();

        CommandLine cl = new CommandLine(main);
        cl.setCaseInsensitiveEnumValuesAllowed(true);
        cl.execute(args);

    }

    @Override
    public void run() {
        // TODO: WORK
        System.out.println(inputFile.canWrite());
        System.out.println(inputFile.canRead());
        System.out.println(inputFile.getAbsolutePath());
        System.out.println(inputFile.isFile());
        System.out.println();
        System.out.println();
    }
}
