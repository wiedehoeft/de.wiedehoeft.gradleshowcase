package de.wiedehoeft.csvtablekata;

import java.util.ArrayList;
import java.util.List;

public class Column {

    private String header;
    private List<String> rows;
    private int longestRowLength;

    public Column() {
        this.rows = new ArrayList<>();
    }

    public Column(String header) {
        this();
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public List<String> getRowContent() {
        return rows;
    }

    public void setRowContent(List<String> rows) {
        this.rows = rows;
    }

    public int getLongestRowLength() {
        return longestRowLength;
    }

    public void setLongestRowLength(int longestRowLength) {
        this.longestRowLength = longestRowLength;
    }

    @Override
    public String toString() {
        return "Column{" +
                "header='" + header + '\'' +
                ", rows=" + rows +
                '}';
    }

}
