package com.jakubvanko.experiment;

import tech.tablesaw.api.Table;

import java.io.File;

public interface ITableLoadingStrategy {
    public Table loadTable(File inputFile);
}
