package com.jakubvanko.experiment.actions;

import tech.tablesaw.api.Table;

public interface Action {
    Table triggerAction(Table table);
}
