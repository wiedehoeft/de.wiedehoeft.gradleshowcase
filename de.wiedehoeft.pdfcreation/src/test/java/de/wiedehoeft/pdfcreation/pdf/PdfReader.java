package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

class PdfReader {

  PDDocument loadPdf(String name) {
    File myFile = new File(String.format("%s.pdf", name));

    try {
      final PDDocument pdDocument = PDDocument.load(myFile);
      pdDocument.close();
      return pdDocument;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to load pdf");
    }
  }

  String readPdf(String name) {
    File myFile = new File(String.format("%s.pdf", name));
    try {
      final PDDocument pdDocument = PDDocument.load(myFile);
      PDFTextStripper stripper = null;
      stripper = new PDFTextStripper();
      final String content = stripper.getText(pdDocument);
      pdDocument.close();
      return content;
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException("Unable to read pdf");
    }
  }
}

