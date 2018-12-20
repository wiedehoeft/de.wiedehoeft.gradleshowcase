package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.font.PDType1Font;

class PdfFont {

  static final PdfFont TIMES_ROMAN = new PdfFont(PDType1Font.TIMES_ROMAN, 12);

  final PDType1Font fontType;
  final int size;

  private PdfFont(PDType1Font fontType, int size) {
    this.fontType = fontType;
    this.size = size;
  }
}
