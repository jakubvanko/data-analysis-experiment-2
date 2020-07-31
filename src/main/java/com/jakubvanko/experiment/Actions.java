package com.jakubvanko.experiment;

import tech.tablesaw.api.Row;
import tech.tablesaw.api.Table;

import java.util.HashMap;
import java.util.Map;

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
    WEST {
        @Override
        public Table examineTable(Table table) {
            return null;
        }
    };


    public abstract Table examineTable(Table table);
}
