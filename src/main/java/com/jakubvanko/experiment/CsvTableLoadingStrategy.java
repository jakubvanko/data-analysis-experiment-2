package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CsvTableLoadingStrategy implements TableLoadingStrategy {
    @Override
    public Table loadTable(File inputFile) {
        try {
            return Table.read().usingOptions(createBuilder(inputFile).build());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(100);
            return Table.create();
        }
    }

    private CsvReadOptions.Builder createBuilder(File inputFile) {
        return CsvReadOptions
                .builder(inputFile)
                .header(false)
                .dateFormat(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }
}
