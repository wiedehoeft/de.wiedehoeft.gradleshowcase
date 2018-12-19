package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;

public class PdfReader {

  void readPdf() {
    File myFile = new File("wwii.pdf");

    try (PDDocument doc = PDDocument.load(myFile)) {

      PDFTextStripper stripper = new PDFTextStripper();
      String text = stripper.getText(doc);

      System.out.println("Text size: " + text.length() + " characters:");
      System.out.println(text);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
