package de.wiedehoeft.pdfcreation.pdf;

import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.text.NumberFormat;
import java.util.Locale;

class PdfTable {

  private final int margin;

  PdfTable(int margin) {
    this.margin = margin;
  }

  Point calculateStartingPosition(PDRectangle pdRectangle, Point startingOffset) {
    final int xStart = (int) (pdRectangle.getWidth() - startingOffset.x);
    final int yStart = (int) (pdRectangle.getHeight() - startingOffset.y);
    return new Point(xStart, yStart);
  }

  double calculateRowSize(int rowCount) {
    NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
    nf.setMaximumFractionDigits(2);
    return Double.valueOf(nf.format(100d / rowCount));
  }
}
