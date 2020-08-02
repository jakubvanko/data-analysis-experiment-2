package com.jakubvanko.experiment;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.HashMap;
import java.util.Map;

import static tech.tablesaw.aggregate.AggregateFunctions.*;

public enum Actions {
    REMOVE_EMPTY_EMAIL {
        @Override
        public Table examineTable(Table table) {
            return table.dropWhere(table.stringColumn(2).isEmptyString());
        }
    },
    REMOVE_EMPTY_ADDRESS {
        @Override
        public Table examineTable(Table table) {
            return table.dropWhere(table.stringColumn(3).isEmptyString());
        }
    },
    TOTAL_PRICE_YEAR {
        @Override
        public Table examineTable(Table table) {
            Table sortedTable = table
                    .dropWhere(table.stringColumn(5).isNotEqualTo("PAID"))
                    .sortOn(1);

            Table firstOption = sortedTable.summarize(sortedTable.column(4), sum).by(sortedTable.dateColumn(1).year());
            System.out.println("First option total year:");
            System.out.println(firstOption.print());
            System.out.println("Second option: ");

            Map<Integer, Integer> totalYearMap = new HashMap<>();
            for (Row row : sortedTable) {
                Integer rowYear = row.getDate(1).getYear();
                Integer rowPrice = row.getInt(4);
                totalYearMap.compute(rowYear, (k, v) -> (v == null) ? rowPrice : v + rowPrice);
            }
            totalYearMap.forEach((k, v) -> System.out.println(k.toString() + " : " + v.toString()));
            return table;
        }
    },
    AVERAGE_PRICE_YEAR {
        @Override
        public Table examineTable(Table table) {
            Table firstOption = table
                    .summarize(table.column(4), mean)
                    .by(table.dateColumn(1).year(), table.stringColumn(5));
            System.out.println("First option average year:");
            System.out.println(firstOption.print());
            return table;
        }
    },
    TOP_THREE_CUSTOMERS {
        @Override
        public Table examineTable(Table table) {
            Table firstOption = table.summarize(table.column(4), count).by(table.stringColumn(2)).sortOn(1).last(3);
            System.out.println("TOP THREE customers: ");
            System.out.println(firstOption.print());
            return table;
        }
    };


    public abstract Table examineTable(Table table);
}
