package com.jakubvanko.experiment.actions;

import com.jakubvanko.experiment.OutputStrategy;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import static tech.tablesaw.aggregate.AggregateFunctions.mean;

public class AveragePriceYearAction extends LoggingAction {
    public AveragePriceYearAction(OutputStrategy outputStrategy) {
        super(outputStrategy);
    }

    @Override
    protected Table prepareTable(Table table) {
        return table
                .summarize(table.column(4), mean)
                .by(table.dateColumn(1).year(), table.stringColumn(5));
    }

    @Override
    protected void logInformation(Table table) {
        outputStrategy.createSection("Average price per year");
        for (Row row : table) {
            outputStrategy.write(
                    row.getInt(0) + " " + row.getString(1),
                    Double.toString(row.getDouble(2)));
        }
    }
}
