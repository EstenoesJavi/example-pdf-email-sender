package com.creatupage.api.example.pdf.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class HeeaderFooterPersonalized extends PdfPageEventHelper {

        public void onEndPage(PdfWriter writer, Document document) {
            try {
                Image img = Image.getInstance("src/main/resources/footer.png");
                img.scaleToFit(100,100);

                document.add(img);
            } catch (Exception x) {
                x.printStackTrace();
            }

        }

}
