package de.wiedehoeft.csvtablekata;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CsvTableConverterTest {

    private CsvTable csvTable;

    @Before
    public void setUp() throws Exception {
        csvTable = new CsvTable();
    }

    @Test
    public void extractTableColumns() {
        // Given
        String header = "Name;Strasse";

        // When
        csvTable.extractColumnHeader(header);

        // Then
        assertThat(csvTable.getColumns().get(0).getHeader()).contains("Name");
        assertThat(csvTable.getColumns().get(1).getHeader()).contains("Strasse");
    }

    @Test
    public void extractTableContent() {
        // Given
        String content = "Peter Pan;Am Hang 5";
        CsvTable csvTable = new CsvTable(2);

        // When
        csvTable.extractTableRows(content);

        // Then
        assertThat(csvTable.getColumns().get(0).getRowContent().get(0)).contains("Peter Pan");
        assertThat(csvTable.getColumns().get(1).getRowContent().get(0)).contains("Am Hang 5");
    }

    @Test
    public void extractMultiRowTableContent() {
        // Given
        String firstContentRow = "Peter Pan;Am Hang 5";
        String secondContentRow = "Maria Schmitz;Kölner Straße 45";
        CsvTable csvTable = new CsvTable(2);

        // When
        csvTable.extractTableRows(firstContentRow + "\n" + secondContentRow);

        // Then
        assertThat(csvTable.getColumns().get(0).getRowContent()).contains("Peter Pan", "Maria Schmitz");
        assertThat(csvTable.getColumns().get(1).getRowContent()).contains("Am Hang 5", "Kölner Straße 45");
    }

    @Test
    public void evaluateLongestContentOfColumn() {
        // Given
        String header = "Name; Strasse";
        String firstContentRow = "Peter Pan; Am Hang 5";
        String secondContentRow = "Maria Schmitz; Kölner Straße 45";

        CsvTable csvTable = new CsvTable();
        csvTable.extractColumnHeader(header);
        csvTable.extractTableRows(firstContentRow + "\n" + secondContentRow);

        // When
        csvTable.calculateLongestColumn();

        // Then
        assertThat(csvTable.getColumns().get(0).getLongestRowLength()).isEqualTo(13);
        assertThat(csvTable.getColumns().get(1).getLongestRowLength()).isEqualTo(16);
    }
}
