/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades.reportes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.StringReader;
import java.net.URL;
import simuni.enums.Recursos;

/**
 *
 * @author FchescO
 */
public class HeaderFooterHorizontal extends PdfPageEventHelper {

    private String[] columnas;
    private String url_image;

    public HeaderFooterHorizontal() {
        url_image = Recursos.R.LOGOSIMUNIURL.toString();
    }

    public HeaderFooterHorizontal(String[] columnas) {
        this.columnas = columnas;
        url_image = Recursos.R.LOGOSIMUNIURL.toString();
    }

    public HeaderFooterHorizontal(String[] columnas, String url_image) {
        this.columnas = columnas;
        this.url_image = url_image;
    }

    @Override
    public void onStartPage(PdfWriter writer, Document document) {
        super.onStartPage(writer, document); //To change body of generated methods, choose Tools | Templates.
        try {
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            document.add(new Phrase("\n"));
            if (document.getPageNumber() > 1) {
                HTMLWorker htmlWorker = new HTMLWorker(document);

                String str = generarEncabezados();
                htmlWorker.parse(new StringReader(str));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String generarEncabezados() {
        String respuesta = "";
        respuesta = "<html>"
                + "     <body>"
                + "         <table border=1>"
                + "             <tr>";
        for (String columna : columnas) {
            respuesta += "<th color='green' style='font-weight: 900;'>"
                    + columna
                    + " </th>";
        }
        respuesta += "         </tr>";
        respuesta += ""
                + "         </table>"
                + "     </body>"
                + "</html>";
        return respuesta;
    }

    @Override
    public void onEndPage(PdfWriter writer, Document document) {
        Rectangle rect = writer.getBoxSize("art");
        try {
            Image img = Image.getInstance(new URL(url_image));
            System.out.println(img!=null);
            img.setAbsolutePosition(20, rect.getWidth() - 20);
            img.scaleAbsolute(70, 70);
            img.setTransparency(new int[]{0x01, 0x01});
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("MUNICIPALIDAD DE NANDAYURE"),
                    (rect.getHeight() / 2) + 30, rect.getWidth() + 35, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("ALCALDIA MUNICIPAL"),
                    (rect.getHeight() / 2) + 30, rect.getWidth() + 20, 0);
            ColumnText.showTextAligned(writer.getDirectContent(),
                    Element.ALIGN_CENTER, new Phrase("TELEFAX 2657-9005"),
                    (rect.getHeight() / 2) + 30, rect.getWidth() + 5, 0);
            document.add(img);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        Phrase pr = new Phrase("“MUNICIPALIDAD DE NANDAYURE, TRABAJANDO CON TRANSPARENCIA Y EQUIDAD”");
        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_CENTER, pr,
                (rect.getHeight() + 70) / 2, rect.getBottom() - 18, 0);
        Phrase i = new Phrase(String.format("Página  %d", writer.getPageNumber()));

        ColumnText.showTextAligned(writer.getDirectContent(),
                Element.ALIGN_LEFT, i,
                (rect.getHeight()), rect.getBottom() - 20, 0);
    }

    /**
     * @return the columnas
     */
    public String[] getColumnas() {
        return columnas;
    }

    /**
     * @param columnas the columnas to set
     */
    public void setColumnas(String[] columnas) {
        this.columnas = columnas;
    }

    /**
     * @return the url_image
     */
    public String getUrl_image() {
        return url_image;
    }

    /**
     * @param url_image the url_image to set
     */
    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

}
