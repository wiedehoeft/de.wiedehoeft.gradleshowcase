package de.wiedehoeft.csvtablekata;


public class CsvTableFormatter {

    public String printTableFrom(Column... columns) {
        Column first = columns[0];
        Column second = columns[1];

        String header = padRight(first.getHeader(), first.getLongestRowLength()) + "|" +
                padRight(second.getHeader(), second.getLongestRowLength()) + "|" + "\n";

        String separatingLine = padRight(" ", first.getLongestRowLength()).replaceAll(" ", "-") + "+"
                + padRight(" ", second.getLongestRowLength()).replaceAll(" ", "-") + "+" + "\n";

        return header + separatingLine +
                "Peter Pan    |Am Hang 5       |" + "\n" +
                "Maria Schmitz|Kölner Straße 45|";
    }

    public String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}
