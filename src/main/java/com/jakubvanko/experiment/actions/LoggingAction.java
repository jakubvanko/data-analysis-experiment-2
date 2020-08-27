package com.jakubvanko.experiment.actions;

import com.jakubvanko.experiment.OutputStrategy;
import tech.tablesaw.api.Table;

public abstract class LoggingAction implements Action {
    protected final OutputStrategy outputStrategy;

    public LoggingAction(OutputStrategy outputStrategy) {
        this.outputStrategy = outputStrategy;
    }

    @Override
    public Table triggerAction(Table table) {
        logInformation(prepareTable(table));
        return table;
    }

    protected abstract Table prepareTable(Table table);

    protected abstract void logInformation(Table table);
}
