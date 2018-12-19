package de.wiedehoeft.pdfcreation.pdf;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class PdfCreatorTest {

  @Test
  void integration() throws IOException {
    PdfFileCreator myFirstPdf = new PdfFileCreator("myFirstPdf");

    myFirstPdf.addPage();

    myFirstPdf.addSection(1);
    myFirstPdf.addTable(1);

    myFirstPdf.save();
  }
}
