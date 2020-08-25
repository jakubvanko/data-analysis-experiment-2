package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.csv.CsvReadOptions;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class CsvTableLoadingStrategy implements TableLoadingStrategy {
    @Override
    public Table loadTable(File inputFile) {
        CsvReadOptions.Builder builder = CsvReadOptions
                .builder(inputFile)
                .header(false)
                .dateFormat(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        try {
            return Table.read().usingOptions(builder.build());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(100);
            return Table.create();
        }
    }
}
