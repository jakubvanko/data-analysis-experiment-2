package com.jakubvanko.experiment;

import picocli.CommandLine;
import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.List;

@CommandLine.Command(
        name = "da-tool",
        sortOptions = false,
        parameterListHeading = "%nParameters:%n",
        optionListHeading = "%nOptions:%n",
        description = "%nPrototype for simple dataset analysis"
)
public class Main implements Runnable {

    @CommandLine.Option(
            names = {"-d", "--dataset"},
            description = "Path to remote or local CSV file",
            converter = InputFileTypeConverter.class,
            required = true
    )
    private File inputFile;

    @CommandLine.Option(names = {"-m", "--methods"}, description = "Manipulation methods", arity = "")
    private List<String> manipulationMethods;

    @CommandLine.Option(names = {"-o", "--output-type"}, description = "Output type (json, xml, plain)")
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
        CsvReadOptions.Builder builder = CsvReadOptions
                .builder(inputFile)
                .header(false)
                .dateFormat(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        try {
            Table table = Table.read().usingOptions(builder.build());



            // TODO: TABLE IS HERE LOADED ;)
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
