package de.wiedehoeft.csvtablekata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvTable {
    public static final String LINE_FEED = "\n";
    public static final String LINE_DELIMITER = ";";
    private List<Column> columns;

    public CsvTable() {
        this.columns = new ArrayList<>();
    }

    public CsvTable(int columns) {
        this();
        for (int i = 0; i < columns; i++) {
            this.columns.add(new Column());
        }

    }

    public void extractColumnHeader(String header) {
        String[] columnHeaders = header.split(";");

        Arrays.asList(columnHeaders).forEach(this::fillColumnHeader);

        //Alternative
//        Arrays.asList(columnHeaders).forEach(element -> this.columns.add(new Column(element)));
    }

    private void fillColumnHeader(String header) {
        this.columns.add(new Column(header));
    }

    public void extractTableRows(String singleLineContent) {

        for (int i = 0; i < columns.size(); i++) {
            List<String> contentRows = new ArrayList<>();
            Column current = this.columns.get(i);
            current.setRowContent(contentRows);

            String[] rows = singleLineContent.split(LINE_FEED);

            for (String row : rows) {
                contentRows.add(row.split(LINE_DELIMITER)[i]);
            }
        }
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void calculateLongestColumn() {

        for (Column column : this.columns) {
            String header = column.getHeader();
            List<String> rows = column.getRowContent();

            column.setLongestRowLength(header.trim().length());

//            for (String row : rows) {
//
//                if (row.trim().length() > column.getLongestRowLength()) {
//                    column.setLongestRowLength(row.trim().length());
//                }
//            }
//
//            long count = rows.stream().count();
//            column.setLongestRowLength(rows.stream().sorted(String::compareTo).skip(count-1).findFirst().get().trim().length());

            rows.parallelStream().reduce((first, second) -> (first.trim().length() < second.trim().length()) ? second : first)
                    .ifPresent(longestRow -> column.setLongestRowLength(longestRow.trim().length()));

//            rows.parallelStream().sorted(String::compareTo).reduce((first, second) -> second).ifPresent(longestRow -> column.setLongestRowLength(longestRow.trim().length()));
//            rows.parallelStream().sorted(String::compareTo).reduce((first, second) -> second).ifPresent(System.out::println);
        }
    }


}
