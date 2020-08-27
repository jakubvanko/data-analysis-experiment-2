package com.jakubvanko.experiment.actions;

import com.jakubvanko.experiment.OutputStrategy;
import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import static tech.tablesaw.aggregate.AggregateFunctions.sum;

public class TotalPriceYearAction extends LoggingAction {
    public TotalPriceYearAction(OutputStrategy outputStrategy) {
        super(outputStrategy);
    }

    @Override
    protected Table prepareTable(Table table) {
        Table sortedTable = table
                .dropWhere(table.stringColumn(5).isNotEqualTo("PAID"))
                .sortOn(1);
        return sortedTable
                .summarize(sortedTable.column(4), sum)
                .by(sortedTable.dateColumn(1).year());
    }

    @Override
    protected void logInformation(Table table) {
        outputStrategy.createSection("Total price per year (only PAID orders)");
        for (Row row : table) {
            outputStrategy.write(
                    Integer.toString(row.getInt(0)),
                    Double.toString(row.getDouble(1)));
        }
    }
}
