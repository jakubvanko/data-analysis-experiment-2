package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;

import java.io.File;

public interface ITableLoadingStrategy {
    Table loadTable(File inputFile);
}
