package com.jakubvanko.experiment;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import static tech.tablesaw.aggregate.AggregateFunctions.*;

public enum Actions {
    REMOVE_EMPTY_EMAIL {
        @Override
        public Table examineTable(Table table, OutputStrategy outputStrategy) {
            return table.dropWhere(table.stringColumn(2).isEmptyString());
        }
    },
    REMOVE_EMPTY_ADDRESS {
        @Override
        public Table examineTable(Table table, OutputStrategy outputStrategy) {
            return table.dropWhere(table.stringColumn(3).isEmptyString());
        }
    },
    TOTAL_PRICE_YEAR {
        @Override
        public Table examineTable(Table table, OutputStrategy outputStrategy) {
            Table sortedTable = table
                    .dropWhere(table.stringColumn(5).isNotEqualTo("PAID"))
                    .sortOn(1);
            Table summarizedTable = sortedTable
                    .summarize(sortedTable.column(4), sum)
                    .by(sortedTable.dateColumn(1).year());
            outputStrategy.increaseLevel("Total price per year (only PAID orders)");
            for (Row row : summarizedTable) {
                outputStrategy.write(
                        Integer.toString(row.getInt(0)),
                        Double.toString(row.getDouble(1)));
            }
            return table;
        }
    },
    AVERAGE_PRICE_YEAR {
        @Override
        public Table examineTable(Table table, OutputStrategy outputStrategy) {
            Table summarizedTable = table
                    .summarize(table.column(4), mean)
                    .by(table.dateColumn(1).year(), table.stringColumn(5));
            outputStrategy.increaseLevel("Average price per year");
            for (Row row : summarizedTable) {
                outputStrategy.write(
                        row.getInt(0) + " " + row.getString(1),
                        Double.toString(row.getDouble(2)));
            }
            return table;
        }
    },
    TOP_THREE_CUSTOMERS {
        @Override
        public Table examineTable(Table table, OutputStrategy outputStrategy) {
            Table summarizedTable = table
                    .summarize(table.column(4), count)
                    .by(table.stringColumn(2))
                    .sortOn(1)
                    .last(3);
            outputStrategy.increaseLevel("Top three customers");
            for (Row row : summarizedTable) {
                outputStrategy.write(
                        row.getString(0),
                        Double.toString(row.getDouble(1)));
            }
            return table;
        }
    };

    public abstract Table examineTable(Table table, OutputStrategy outputStrategy);
}
