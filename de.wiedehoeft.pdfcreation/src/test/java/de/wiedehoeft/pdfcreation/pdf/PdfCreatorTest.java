package de.wiedehoeft.pdfcreation.pdf;

import io.github.glytching.junit.extension.folder.TemporaryFolder;
import io.github.glytching.junit.extension.folder.TemporaryFolderExtension;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(TemporaryFolderExtension.class)
class PdfCreatorTest {

  private TemporaryFolder temporaryFolder;

  @BeforeEach
  void prepare(TemporaryFolder temporaryFolder) {
    this.temporaryFolder = temporaryFolder;
  }


  @Test
  void shouldCreateOneDocumentWithAnyContent() throws IOException, InterruptedException {
    // Arrange
    final String pdfAtPathWithName = temporaryFolder.getRoot().getPath() + "/myFirstPdf";
    PdfFileCreator myFirstPdf = new PdfFileCreator(pdfAtPathWithName);

    // Act
    myFirstPdf.addPage();
    myFirstPdf.save();

    // Assert
    PDDocument result = new PdfReader().loadPdf(pdfAtPathWithName);
    assertThat(result.getPages().getCount()).isEqualTo(1);
  }

  @Test
  void shouldCreateDocumentWithContent() throws IOException {
    // Arrange
    final String pdfAtPathWithName = temporaryFolder.getRoot().getPath() + "/myFirstPdf";
    PdfFileCreator myFirstPdf = new PdfFileCreator(pdfAtPathWithName);
    myFirstPdf.addPage();

    // Act
    myFirstPdf.addSection(1);
    myFirstPdf.addTable(1);
    myFirstPdf.save();

    // Assert
    String content = new PdfReader().readPdf(pdfAtPathWithName);
    assertThat(content).isNotEmpty();
  }
}
