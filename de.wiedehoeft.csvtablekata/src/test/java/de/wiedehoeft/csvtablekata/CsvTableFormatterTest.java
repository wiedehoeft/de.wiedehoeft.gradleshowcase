package de.wiedehoeft.csvtablekata;

import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvTableFormatterTest {

    @Test
    public void createColumn() {
        // Given
        Column firstColumn = new Column("Name");
        Column secondColumn = new Column("Strasse");

        ArrayList<String> rows = new ArrayList<>();
        rows.add("Peter Pan");
        rows.add("Maria Schmitz");
        firstColumn.setRowContent(rows);
        firstColumn.setLongestRowLength(13);

        ArrayList<String> rows1 = new ArrayList<>();
        rows1.add("Am Hang 5");
        rows1.add("Kölner Straße 45");
        secondColumn.setRowContent(rows1);
        secondColumn.setLongestRowLength(16);

        // When
        String result = new CsvTableFormatter().printTableFrom(firstColumn, secondColumn);

        // Then
        assertThat(result).isEqualTo("" +
                "Name         |Strasse         |" + "\n" +
                "-------------+----------------+" + "\n" +
                "Peter Pan    |Am Hang 5       |" + "\n" +
                "Maria Schmitz|Kölner Straße 45|");
    }

    @Test
    public void rightPadString() {

        // Given
        String toPad = "Name";

        // When
        String result = new CsvTableFormatter().padRight(toPad, 5);


        // Then
        assertThat(result).isEqualTo("Name ");

    }
}
