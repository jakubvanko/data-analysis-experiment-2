package com.jakubvanko.experiment.actions;

import com.jakubvanko.experiment.OutputStrategy;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import static tech.tablesaw.aggregate.AggregateFunctions.count;

public class TopThreeCustomersAction extends LoggingAction {
    public TopThreeCustomersAction(OutputStrategy outputStrategy) {
        super(outputStrategy);
    }

    @Override
    protected Table prepareTable(Table table) {
        return table
                .summarize(table.column(4), count)
                .by(table.stringColumn(2))
                .sortOn(1)
                .last(3);
    }

    @Override
    protected void logInformation(Table table) {
        outputStrategy.createSection("Top three customers");
        for (Row row : table) {
            outputStrategy.write(
                    row.getString(0),
                    Double.toString(row.getDouble(1)));
        }
    }
}
