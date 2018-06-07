package de.wiedehoeft.csvtablekata;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvTableFormatterTest {

    private Column firstColumn;
    private Column secondColumn;

    @Before
    public void setUp() throws Exception {
        firstColumn = new Column("Name");
        secondColumn = new Column("Strasse");

        firstColumn.setLongestRowLength(13);
        secondColumn.setLongestRowLength(16);
    }

    @Test
    public void createTableHeader() {
        // When
        String result = new CsvTableFormatter().createTableHeader(firstColumn, secondColumn);

        // Then
        assertThat(result).isEqualTo("" +
                "Name         |Strasse         |" + "\n");
    }

    @Test
    public void createTableSeparator() {
        // When
        String result = new CsvTableFormatter().createTableSeparator(firstColumn, secondColumn);

        // Then
        assertThat(result).isEqualTo("" +
                "-------------+----------------+" + "\n");
    }

    @Test
    public void createTableContent() {
        // Given
        ArrayList<String> rows = new ArrayList<>();
        rows.add("Peter Pan");
        rows.add("Maria Schmitz");
        firstColumn.setRowContent(rows);

        ArrayList<String> rows1 = new ArrayList<>();
        rows1.add("Am Hang 5");
        rows1.add("Kölner Straße 45");
        secondColumn.setRowContent(rows1);

        // When
        String result = new CsvTableFormatter().createTableContent(firstColumn, secondColumn);

        // Then
        assertThat(result).isEqualTo("" +
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
