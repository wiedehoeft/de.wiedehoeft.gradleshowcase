package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class PdfFileCreator {

  final String title;
  final PDDocument pdfDocument;
  final List<PdfPage> pdfPages;

  PdfFileCreator(String title) {
    pdfPages = new ArrayList<>();
    pdfPages.add(null); //Empty entry so page counting starts at index 1
    this.title = title;
    pdfDocument = new PDDocument();
  }

  void save() throws IOException {
    final File file = new File(String.format("%s.pdf", title));
    pdfDocument.save(file);
  }

  void addPage() {
    PDPage page = new PDPage();
    this.pdfPages.add(new PdfPage(page));
    pdfDocument.addPage(page);
  }

  void addSection(int site) throws IOException {
    PdfPage pdfPage = this.pdfPages.get(site);
    pdfPage.addSection(pdfDocument);
  }

  void addTable(int site) throws IOException {
    PdfPage pdfPage = this.pdfPages.get(site);
    pdfPage.addTable(pdfDocument);
  }
}
