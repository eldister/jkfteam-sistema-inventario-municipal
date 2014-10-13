/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.EN.RE;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.EN.Servidor;

/**
 *
 * @author FchescO
 */
public class Plantilla_ReporteVertical extends PdfPageEventHelper {

    int npaginaactual;
    int ntotalpaginas;
    public String url;

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        ntotalpaginas++;
        super.onEndPage(writer, document); //To change body of generated methods, choose Tools | Templates.
        try {
            PdfContentByte canvas = writer.getDirectContent();
            PdfContentByte cb = canvas;
            BaseFont sloganmunifont = BaseFont.createFont(BaseFont.TIMES_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
            cb.saveState();
            cb.beginText();
            cb.moveText(190, 800);
            cb.setFontAndSize(sloganmunifont, 14);
            cb.showText("MUNICIPALIDAD DE NANDAYURE");
            cb.endText();
            cb.beginText();
            cb.moveText(240, 780);
            cb.showText("ALCALDIA MUNICIPAL");
            cb.endText();
            cb.beginText();
            cb.moveText(250, 760);
            cb.showText("TELEFAX 2657-9005");
            cb.endText();
            cb.beginText();
            cb.setFontAndSize(sloganmunifont, 10);
            cb.setColorStroke(new BaseColor(1, 255, 50, 40));
            cb.moveText(100, 50);
            cb.showText("“MUNICIPALIDAD DE NANDAYURE, TRABAJANDO CON TRANSPARENCIA Y EQUIDAD”");
            cb.endText();
            cb.beginText();
            cb.setFontAndSize(sloganmunifont, 11);
            cb.setColorStroke(BaseColor.BLACK);
            cb.moveText(500, 25);
            cb.showText("Página " + document.getPageNumber());
            cb.endText();
            cb.restoreState();
            Image img = Image.getInstance(new URL(Servidor.R.LOGOSIMUNIURL.toString()));
            img.setAbsolutePosition(50, 740);

            img.scaleAbsolute(85, 85);
            document.add(img);

        } catch (DocumentException ex) {
            Logger.getLogger(Plantilla_ReporteVertical.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Plantilla_ReporteVertical.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(System.getProperty("user.dir"));
    }

}
