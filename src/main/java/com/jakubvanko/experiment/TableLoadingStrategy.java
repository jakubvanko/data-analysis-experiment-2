package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;

import java.io.File;

public interface TableLoadingStrategy {
    Table loadTable(File inputFile);
}
