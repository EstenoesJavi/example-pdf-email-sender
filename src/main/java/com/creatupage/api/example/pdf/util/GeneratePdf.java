package com.creatupage.api.example.pdf.util;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.*;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class GeneratePdf {

    private java.util.List<String> listProducts = java.util.List.of("Arepas");

    public void generate() throws IOException {

        // Creating the Object of Document
        Document document = new Document(PageSize.A4,50f,50f,110f,150f);

        var out = new FileOutputStream("src/main/resources/ListadeProductos.pdf");
        // Getting instance of PdfWriter
        HeeaderFooterPersonalized event = new HeeaderFooterPersonalized();

        var writter = PdfWriter.getInstance(document, out);

        // Opening the created document to modify it
        document.open();

        // img background
        /*Image image = Image.getInstance("src/main/resources/headerfooter.jfif");
        PdfContentByte canvas = writter.getDirectContentUnder();
        image.scalePercent(67);
        //mage.scaleAbsolute(0);
        image.setAbsolutePosition(0, 0);
        canvas.saveState();
        PdfGState state = new PdfGState();
        //state.setFillOpacity(0.1f);
        canvas.setGState(state);
        canvas.addImage(image);
        canvas.restoreState();*/

        //Event to create footer and header
        /*writter.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContent();
                Image img = null;
                Image headerImg = null;
                try {
                    img = Image.getInstance("src/main/resources/footer.png");
                    headerImg = Image.getInstance("src/main/resources/header.png");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img.scalePercent(100f,45f);
                img.setAbsolutePosition(-5, 0);
                img.setAlignment(Element.ALIGN_CENTER);
                //headerImg
                headerImg.scalePercent(100f,50f);
                headerImg.setAbsolutePosition(5, 780);
                headerImg.setAlignment(Element.ALIGN_CENTER);
                cb.addImage(img);
                cb.addImage(headerImg);
                //cb.rectangle(header);
                //cb.rectangle(footer);
                //cb.rectangle(box);
            }
        });*/
        writter.setPageEvent(new PdfPageEventHelper() {
            @Override
            public void onEndPage(PdfWriter writer, Document document) {
                PdfContentByte cb = writer.getDirectContentUnder();
                Image img = null;
                try {
                    img = Image.getInstance("src/main/resources/headerfooter.jfif");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                img.scalePercent(66f);
                img.setAbsolutePosition(0, 0);
                img.setAlignment(Element.ALIGN_CENTER);
                cb.addImage(img,true);
            }
        });

        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(12);
        // Creating paragraph
        Paragraph paragraph = new Paragraph("El cliente xxx nit con la sucursal xxx ha ingresado un", fontTiltle);
        Paragraph paragraph2 = new Paragraph("nuevo pedido por DAN con el número 001-PTE-123 el día", fontTiltle);
        Paragraph paragraph3 = new Paragraph("2022-05-23", fontTiltle);
        paragraph3.setSpacingAfter(10f);

        // Aligning the paragraph in document
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph2.setAlignment(Paragraph.ALIGN_CENTER);
        paragraph3.setAlignment(Paragraph.ALIGN_CENTER);

        // Adding the created paragraph in document
        document.add(paragraph);
        document.add(paragraph2);
        document.add(paragraph3);


        // Creating a table of 3 columns
        PdfPTable table = new PdfPTable(3);
        // Setting width of table, its columns and spacing
        table.setWidthPercentage(100f);
        table.setWidths(new int[]{5, 2, 2});
        table.setSpacingBefore(5);

        table.getDefaultCell().setBorderColor(new Color(179, 179, 179  ));

        // Create Table Cells for table header
        PdfPCell cell = new PdfPCell();
        cell.setBorderColor(new Color(179, 179, 179 ));
        // Setting the background color and padding
        cell.setBackgroundColor(new Color(209, 209, 209));
        cell.setPadding(5);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);

        // Creating font
        // Setting font style and size
        Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        font.setColor(CMYKColor.BLACK);

        // Adding headings in the created table cell/ header
        // Adding Cell to table
        cell.setPhrase(new Phrase("Productos", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Referencia", font));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Cantidad", font));
        table.addCell(cell);

        // Iterating over the list of students

        for (int i = 0; i < 60; i++) {
            // Adding student id
            cell.setBackgroundColor(Color.white);
            cell.setPhrase(new Phrase("Arepas".concat(String.valueOf(i)), font));
            table.addCell(cell);
            // Adding student name
            cell.setPhrase(new Phrase(String.valueOf(i), font));
            table.addCell(cell);
            // Adding student section
            cell.setPhrase(new Phrase(String.valueOf(i), font));
            table.addCell(cell);
        }
        // Adding the created table to document
        document.add(table);
        // Closing the document
        document.close();

        //return out;
    }
}
