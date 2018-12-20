package de.wiedehoeft.pdfcreation.pdf;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

class PdfCreationTest {

  @Test
  void shouldCreateDocumentWithContent() throws IOException {
    // Arrange
    PdfFileCreator myFirstPdf = new PdfFileCreator("myFirstPdf");
    myFirstPdf.addPage();

    // Act
    myFirstPdf.addSection(1);
    final ArrayList<String> content = new ArrayList<>();
    content.add("Column 1");
    content.add("Column 2");
    content.add("Column 3");

    myFirstPdf.addTable(1, content, new Point(0, 50));
    myFirstPdf.save();
  }
}

