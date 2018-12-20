package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PdfTableTest {

  private PdfTable pdfTable;

  @BeforeEach
  void setUp() {
    pdfTable = new PdfTable(100);
  }

  @Test
  void calculateTableRowSizeFromUnevenNumber() {
    // Arrange
    final int rowCount = 3;

    // Act
    final double rowSize = pdfTable.calculateRowSize(rowCount);

    // Assert
    assertThat(rowSize).isEqualTo(33.33);
  }

  @Test
  void calculateTableRowSizeFromEvenNumber() {
    // Arrange
    final int rowCount = 4;

    // Act
    final double rowSize = pdfTable.calculateRowSize(rowCount);

    // Assert
    assertThat(rowSize).isEqualTo(25.00);
  }

  @Test
  void tableShouldStartAtPageTop() {
    // Arrange
    final Point point = new Point(0, 0);
    final PDRectangle pdRectangle = new PDRectangle();
    pdRectangle.setLowerLeftX(0);
    pdRectangle.setLowerLeftY(0);
    pdRectangle.setUpperRightX(300);
    pdRectangle.setUpperRightY(300);

    // Act
    final Point result = pdfTable.calculateStartingPosition(pdRectangle, point);

    // Assert
    assertThat(result.x).isEqualTo(300);
    assertThat(result.y).isEqualTo(300);
  }

  @Test
  void tableShouldStartAtMiddleOfPage() {
    // Arrange
    final Point point = new Point(25, 50);
    final PDRectangle pdRectangle = new PDRectangle();
    pdRectangle.setLowerLeftX(0);
    pdRectangle.setLowerLeftY(0);
    pdRectangle.setUpperRightX(50);
    pdRectangle.setUpperRightY(200);

    // Act
    final Point result = pdfTable.calculateStartingPosition(pdRectangle, point);

    // Assert
    assertThat(result.x).isEqualTo(25);
    assertThat(result.y).isEqualTo(150);
  }
}
