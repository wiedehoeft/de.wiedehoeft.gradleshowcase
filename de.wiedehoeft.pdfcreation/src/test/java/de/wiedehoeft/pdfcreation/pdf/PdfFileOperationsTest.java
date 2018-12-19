package de.wiedehoeft.pdfcreation.pdf;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PdfFileOperationsTest {

  @Test
  void initPdfFile() {

    // Act
    PdfFileCreator pdfFileCreator = new PdfFileCreator("MyFirstPdf");

    // Assert
    assertThat(pdfFileCreator.pdfDocument).isNotNull();
    assertThat(pdfFileCreator.title).isEqualTo("MyFirstPdf");
  }

  @Test
  void addPagesToPdf() {
    // Arrange
    PdfFileCreator pdfFileCreator = new PdfFileCreator("MyFirstPdf");

    // Act
    pdfFileCreator.addPage();

    // Assert
    assertThat(pdfFileCreator.pdfPages.size()).isEqualTo(1);
    assertThat(pdfFileCreator.pdfDocument.getPages().get(0)).isNotNull();
  }
}
