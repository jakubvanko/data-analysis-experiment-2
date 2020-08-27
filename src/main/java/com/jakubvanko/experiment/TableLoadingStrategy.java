package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;

import java.io.File;
import java.io.IOException;

public interface TableLoadingStrategy {
    Table loadTable(File inputFile) throws IOException;
}
