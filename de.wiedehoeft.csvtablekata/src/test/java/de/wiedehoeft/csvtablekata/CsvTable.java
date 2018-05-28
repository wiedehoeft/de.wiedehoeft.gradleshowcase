package de.wiedehoeft.csvtablekata;

import java.util.ArrayList;
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

        for (String columnHeader : columnHeaders) {
            this.columns.add(new Column(columnHeader));
        }
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

            for (String row : rows) {

                if (row.trim().length() > column.getLongestRowLength()) {
                    column.setLongestRowLength(row.trim().length());
                }
            }
        }
    }
}
