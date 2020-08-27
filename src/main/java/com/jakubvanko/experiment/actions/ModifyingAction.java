package com.jakubvanko.experiment.actions;

import tech.tablesaw.api.Table;

public abstract class ModifyingAction implements Action {

    @Override
    public Table triggerAction(Table table) {
        return modifyTable(table);
    }

    protected abstract Table modifyTable(Table table);
}
