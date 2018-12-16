package de.wiedehoeft.pdfcreation.pdf;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.Cell;
import be.quodlibet.boxable.Row;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PdfCreatorTest {

  @Test
  void smokeTest() throws IOException {
    try (PDDocument doc = new PDDocument()) {

      PDPage myPage = new PDPage();
      doc.addPage(myPage);

      try (PDPageContentStream cont = new PDPageContentStream(doc, myPage)) {

        cont.beginText();

        cont.setFont(PDType1Font.TIMES_ROMAN, 12);
        cont.setLeading(14.5f);

        cont.newLineAtOffset(25, 700);
        String line1 = "World War II (often abbreviated to WWII or WW2), "
          + "also known as the Second World War,";
        cont.showText(line1);

        cont.newLine();

        String line2 = "was a global war that lasted from 1939 to 1945, "
          + "although related conflicts began earlier.";
        cont.showText(line2);
        cont.newLine();

        String line3 = "It involved the vast majority of the world's "
          + "countries—including all of the great powers—";
        cont.showText(line3);
        cont.newLine();

        String line4 = "eventually forming two opposing military "
          + "alliances: the Allies and the Axis.";
        cont.showText(line4);
        cont.newLine();

        cont.endText();


        //Dummy Table
        float margin = 100;
        // starting y position is whole page height subtracted by top and bottom margin
        float yStartNewPage = myPage.getMediaBox().getHeight() - (2 * margin);
        // we want table across whole page width (subtracted by left and right margin ofcourse)
        float tableWidth = myPage.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float yStart = yStartNewPage;
        float bottomMargin = 70;
        // y position is your coordinate of top left corner of the table
        float yPosition = 550;

        BaseTable table = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, myPage, true,
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

      doc.save("wwii.pdf");
    }

    File myFile = new File("wwii.pdf");

    try (PDDocument doc = PDDocument.load(myFile)) {

      PDFTextStripper stripper = new PDFTextStripper();
      String text = stripper.getText(doc);

      System.out.println("Text size: " + text.length() + " characters:");
      System.out.println(text);
    }
  }

  private List<String[]> getFacts() {
    ArrayList<String[]> facts = new ArrayList<>();
    facts.add(new String[]{"Hello World", "Hello Apache", "Hello PDF"});
    facts.add(new String[]{"Blubb"});
    facts.add(new String[]{"Geschmeidig"});
    return facts;
  }
}
