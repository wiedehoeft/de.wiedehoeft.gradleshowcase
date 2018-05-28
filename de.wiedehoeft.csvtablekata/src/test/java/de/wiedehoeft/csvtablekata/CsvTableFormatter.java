package de.wiedehoeft.csvtablekata;

public class CsvTableFormatter {

    public String createTableContent(Column... columns) {
        StringBuilder rowBuilder = new StringBuilder();

        int rowCount = columns[0].getRowContent().size();
        for (int i = 0; i < rowCount; i++) {
            for (Column column : columns) {
                rowBuilder.append(padRight(column.getRowContent().get(i), column.getLongestRowLength()));
                rowBuilder.append("|");
            }
            if (i < rowCount - 1) {
                rowBuilder.append("\n");
            }
        }
        return rowBuilder.toString();
    }

    public String createTableSeparator(Column... columns) {
        StringBuilder separatingLine = new StringBuilder();
        for (Column column : columns) {
            separatingLine.append(padRight(" ", column.getLongestRowLength()).replaceAll(" ", "-"));
            separatingLine.append("+");
        }
        separatingLine.append("\n");
        return separatingLine.toString();
    }

    public String createTableHeader(Column... columns) {
        StringBuilder header = new StringBuilder();

        for (Column column : columns) {
            header.append(padRight(column.getHeader(), column.getLongestRowLength()));
            header.append("|");
        }
        header.append("\n");
        return header.toString();
    }

    public String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}
