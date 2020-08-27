package com.jakubvanko.experiment.actions;

import tech.tablesaw.api.Table;

public class RemoveEmptyAction extends ModifyingAction{
    private final int columnIndex;

    public RemoveEmptyAction(int columnIndex){
        this.columnIndex = columnIndex;
    }

    @Override
    protected Table modifyTable(Table table) {
        return table.dropWhere(table.stringColumn(columnIndex).isEmptyString());
    }
}
