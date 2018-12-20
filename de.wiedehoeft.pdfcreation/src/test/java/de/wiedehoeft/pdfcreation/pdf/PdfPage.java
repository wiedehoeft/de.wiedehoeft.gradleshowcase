package de.wiedehoeft.pdfcreation.pdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class PdfPage {

  private PDPage pdPage;
  private PDRectangle rectangle;

  private static final Logger logger = LogManager.getLogger(PdfPage.class);

  PdfPage(PDPage pdPage) {
    this.pdPage = pdPage;
    this.rectangle = pdPage.getMediaBox();
    logPdfInformation();
  }

  private void logPdfInformation() {
    logger.debug("Initializing PDF document with following size.");
    logger.debug("Höhe: " + rectangle.getHeight());
    logger.debug("Breite: " + rectangle.getWidth());
    logger.debug("Links unten X: " + rectangle.getLowerLeftX());
    logger.debug("Rechts oben X: " + rectangle.getUpperRightX());
    logger.debug("Unten links Y: " + rectangle.getLowerLeftY());
    logger.debug("Oben rechts Y: " + rectangle.getUpperRightY());
  }

  void addSection(PDDocument pdDocument, PdfFont pdfFont, int xStart, int yStart) throws IOException {

    PDPageContentStream contentStream = new PDPageContentStream(pdDocument, pdPage);

    contentStream.beginText();

    contentStream.setFont(pdfFont.fontType, pdfFont.size);
    contentStream.setLeading(14.5f);

    contentStream.newLineAtOffset(xStart, yStart);
    String line1 = "World War II (often abbreviated to WWII or WW2), "
      + "also known as the Second World War,";
    contentStream.showText(line1);

    contentStream.newLine();

    String line2 = "was a global war that lasted from 1939 to 1945, "
      + "although related conflicts began earlier.";
    contentStream.showText(line2);
    contentStream.newLine();

    String line3 = "It involved the vast majority of the world's "
      + "countries—including all of the great powers—";
    contentStream.showText(line3);
    contentStream.newLine();

    String line4 = "eventually forming two opposing military "
      + "alliances: the Allies and the Axis.";
    contentStream.showText(line4);
    contentStream.newLine();

    contentStream.endText();

    contentStream.close();
  }

  void addTable(PDDocument pdDocument, Optional<String> header, List<String> content, Point position) throws IOException {
    final int margin = 50;
    final PdfTable pdfTable = new PdfTable(margin);

    final Point startingPosition = pdfTable.calculateStartingPosition(rectangle, position);
    final float columnSize = (float) pdfTable.calculateRowSize(content.size());

    logger.debug("Creating table at {} with column-size {}", startingPosition, columnSize);

    boolean drawContent = true;
    float bottomMargin = 10;

    float tableWidth = rectangle.getWidth() - (2 * margin);

    BaseTable table = new BaseTable(startingPosition.y, startingPosition.y, bottomMargin, tableWidth, margin, pdDocument, pdPage, true,
      drawContent);

    Row<PDPage> headerRow = table.createRow(15f);
    Cell<PDPage> cell = headerRow.createCell(100, "Awesome Facts About Belgium");
    cell.setFont(PDType1Font.HELVETICA_BOLD);
    //cell.setFillColor(Color.BLACK);
    table.addHeaderRow(headerRow);

    content.forEach(rowContent -> {
      Row<PDPage> row = table.createRow(10f);
      row.createCell(columnSize, rowContent);
    });

    table.draw();
  }

  @Deprecated
  void addTable(PDDocument pdfDocument) throws IOException {
    //Dummy Table
    float margin = 100;
    // starting y position is whole page height subtracted by top and bottom margin
    float yStartNewPage = rectangle.getHeight() - (2 * margin);
    // we want table across whole page width (subtracted by left and right margin ofcourse)
    float tableWidth = rectangle.getWidth() - (2 * margin);

    boolean drawContent = true;
    float bottomMargin = 70;

    BaseTable table = new BaseTable(yStartNewPage, yStartNewPage, bottomMargin, tableWidth, margin, pdfDocument, pdPage, true,
      drawContent);
    //Create Header row
    Row<PDPage> headerRow = table.createRow(15f);
    Cell<PDPage> cell = headerRow.createCell(100, "Awesome Facts About Belgium");
    cell.setFont(PDType1Font.HELVETICA_BOLD);
    //cell.setFillColor(Color.BLACK);
    table.addHeaderRow(headerRow);
    List<String[]> facts = getFacts();
    for (String[] fact : facts) {
      Row<PDPage> row = table.createRow(10f);
      cell = row.createCell((100 / 3.0f), fact[0]);
      for (int i = 1; i < fact.length; i++) {
        cell = row.createCell((100 / 3.0f), fact[i]);
      }
    }
    table.draw();
  }

  @Deprecated
  private List<String[]> getFacts() {
    ArrayList<String[]> facts = new ArrayList<>();
    facts.add(new String[]{"Hello World", "Hello Apache", "Hello PDF"});
    facts.add(new String[]{"Blubb"});
    facts.add(new String[]{"Geschmeidig"});
    return facts;
  }
}
