/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades.reportes;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletOutputStream;

/**
 *
 * @author FchescO
 */
public class Rprt_Base {
 
    //atributos de la clase base para los reportes
    private String[] columnas;
    private ArrayList<String[]> datos;
    private String titulo;
    private String usuario;
    private String observaciones;
    private boolean horizontal = true;
    PdfPageEventHelper event_helper;
    private Document document;
    private PdfWriter writer;
    int cantidadCampos;

    /**
     * primer constructor de la clase 
     * contructor con parametros para la intanciación de objetos relacionados con los
     *reportes que será generado por un usuario
     */
    public Rprt_Base() {
    }

    /**
     * segundo constructor de la clase
     * contructor con parametros para la intanciación de objetos relacionados con los
     * reportes que será generado por un usuario
     * 
     * @param columnas vector o arreglo con las columnas que se mostraran en la tabla del reporte
     * @param datos arraylist con los datos que se hayan obtenido en una consulta a la base
     * de datos con relación a los registros de los procesos que se desean hacer un reporte.
     * @param titulo titulo del reporte
     * @param usuario individuo quién realiza o realizó la generación del reporte
     * @param observaciones observaciones que se realicen
     * @param event_helper un ayudador de evento
     * ♠
     */
    public Rprt_Base(String[] columnas, ArrayList<String[]> datos, String titulo, String usuario, String observaciones, PdfPageEventHelper event_helper) {
        this.columnas = columnas;
        this.datos = datos;
        this.titulo = titulo;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.event_helper = event_helper;
    }

    /**
     * tercer constructor de la clase
     * contructor con parametros para la intanciación de objetos relacionados con los
     * reportes que será generado por un usuario
     * 
     * @param columnas vector o arreglo con las columnas que se mostraran en la tabla del reporte
     * @param datos arraylist con los datos que se hayan obtenido en una consulta a la base
     * de datos con relación a los registros de los procesos que se desean hacer un reporte.
     * @param titulo titulo del reporte
     * @param usuario individuo quién realiza o realizó la generación del reporte
     * @param observaciones observaciones que se realicen
     * @param horizontal el horizontal que será mostrado
     * @param writer el escritor que sera mostrado
     */
    public Rprt_Base(String[] columnas, ArrayList<String[]> datos, String titulo, String usuario, String observaciones, boolean horizontal, PdfWriter writer) {
        this.columnas = columnas;
        this.datos = datos;
        this.titulo = titulo;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.horizontal = horizontal;
        this.writer = writer;

        if (this.horizontal) {
            event_helper = new HeaderFooterHorizontal(this.columnas);
        }

    }

    /**
     * cuarto constructor de la clase
     * contructor con parametros para la intanciación de objetos relacionados con los
     * reportes que será generado por un usuario
     * 
     * @param columnas vector o arreglo con las columnas que se mostraran en la tabla del reporte
     * @param datos arraylist con los datos que se hayan obtenido en una consulta a la base
     * de datos con relación a los registros de los procesos que se desean hacer un reporte.
     * @param titulo titulo del reporte
     * @param usuario individuo quién realiza o realizó la generación del reporte
     * @param observaciones observaciones que se realicen
     * @param horizontal el horizontal que será mostrado
     */
    public Rprt_Base(String[] columnas, ArrayList<String[]> datos, String titulo, String usuario, String observaciones, boolean horizontal, ServletOutputStream out) {
        this.columnas = columnas;
        this.datos = datos;
        this.titulo = titulo;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.horizontal = horizontal;

        try {
            document = new Document(PageSize.A4.rotate(), 20, 20, 50, 50);
            this.writer = PdfWriter.getInstance(document, out);
        } catch (DocumentException ex) {
            Logger.getLogger(Rprt_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        ; //el punto y coma del aberno

        if (this.horizontal) {
            event_helper = new HeaderFooterHorizontal(this.columnas);
        }

    }

    /**
     * quinto constructor de la clase
     * contructor con parametros para la intanciación de objetos relacionados con los
     * reportes que será generado por un usuario
     * 
     * @param columnas vector o arreglo con las columnas que se mostraran en la tabla del reporte
     * @param datos arraylist con los datos que se hayan obtenido en una consulta a la base
     * de datos con relación a los registros de los procesos que se desean hacer un reporte.
     * @param titulo titulo del reporte
     * @param usuario individuo quién realiza o realizó la generación del reporte
     * @param observaciones observaciones que se realicen
     * @param horizontal el horizontal que será mostrado
     * @param document document to set
     * @param out flujo de salida de datos
     */
    public Rprt_Base(String[] columnas, ArrayList<String[]> datos, String titulo, String usuario, String observaciones, boolean horizontal, Document document, ServletOutputStream out) {
        this.columnas = columnas;
        this.datos = datos;
        this.titulo = titulo;
        this.usuario = usuario;
        this.observaciones = observaciones;
        this.horizontal = horizontal;

        try {
            this.document = document;
            this.writer = PdfWriter.getInstance(this.document, out);
        } catch (DocumentException ex) {
            Logger.getLogger(Rprt_Base.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (this.horizontal) {
            event_helper = new HeaderFooterHorizontal(this.columnas);
        }

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
     * @return the datos
     */
    public ArrayList<String[]> getDatos() {
        return datos;
    }

    /**
     * @param datos the datos to set
     */
    public void setDatos(ArrayList<String[]> datos) {
        this.datos = datos;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the horizontal
     */
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     * @param horizontal the horizontal to set
     */
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Método utilizado para gnerar o crear una tabla html
     * 
     * @return una cadena que contiene código fuente en html
     */
    public String generarTablaHtml() {
        String respuesta = "";
        if (columnas != null && datos != null) {
            respuesta = "<html>"
                    + "     <body>"
                    + "         <table border='0.1'>"
                    + "             <tr>";
            for (String columna : columnas) {
                respuesta += "<th color='green'  style='text-align:center;font-weight: 900;'>"
                        + columna
                        + " </th>";
            }
            respuesta += "</tr>";
            Iterator<String[]> iterator = datos.iterator();
            while (iterator != null && iterator.hasNext()) {
                cantidadCampos++;
                String[] filadatos = iterator.next();
                respuesta += "<tr>";
                for (String columna : filadatos) {
                    respuesta += "<td>"
                            + columna
                            + " </td>";
                }
                respuesta += "</tr>";
            }
            respuesta += ""
                    + "         </table>"
                    + "     </body>"
                    + "</html>";
        }
        System.out.println(respuesta);
        return respuesta;

    }

    /**
     * Método que obtiene la fecha en la cual fue generado el reporte
     * 
     * @return un string con la fecha y hora del reporte generado
     */
    private String getFechaReporte() {
        String respuesta = "";

        Calendar c = Calendar.getInstance();

        respuesta += "Emitido el "
                + (((c.get(Calendar.DAY_OF_MONTH ))>9)?(c.get(Calendar.DAY_OF_MONTH ))+"":"0"+(c.get(Calendar.DAY_OF_MONTH )))
                + "-"
                + (((c.get(Calendar.MONTH )+1)>9)?(c.get(Calendar.MONTH )+1)+"":"0"+(c.get(Calendar.MONTH )+1))
                + "-"
                + c.get(Calendar.YEAR)
                + " a las "
                + c.get(Calendar.HOUR_OF_DAY)
                + ":"
                + c.get(Calendar.MINUTE)
                + ":"
                + c.get(Calendar.SECOND)
                + " por el usuario > "+this.usuario+"";

        return respuesta;
    }

    /**
     * Método utilizado para generar un reporte
     * para ser mostrado como un documento <strong>PDF</strong>
     */
    public void generarReporte() {
        try {
            writer.setBoxSize("art", new Rectangle(36, 54, 559, 788));    
            writer.setPageEvent(getPdfEventHelper());
            document.open();
            Paragraph par3 = new Paragraph(titulo);
            par3.setAlignment(Element.ALIGN_CENTER);
            Paragraph par2 = new Paragraph(getFechaReporte());
            par2.setAlignment(Element.ALIGN_CENTER);
            document.add(par3);
            document.add(par2);
            document.add(new Phrase("\n"));
            HTMLWorker htmlWorker = new HTMLWorker(document);
            String str = generarTablaHtml();
            htmlWorker.parse(new StringReader(str));

            Paragraph par4 = new Paragraph(getCantidadRegistros());
            par4.setAlignment(Element.ALIGN_RIGHT);
            
            document.add(par4);
            document.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return cadena con el total de los registros
     */
    private String getCantidadRegistros() {
        return "Total " + cantidadCampos;
    }

    /**
     * @return event_helper
     */
    public PdfPageEventHelper getPdfEventHelper() {

        return this.event_helper;

    }

    /**
     * @return the document
     */
    public Document getDocument() {
        return document;
    }

    /**
     * @param document the document to set
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    /**
     * @return the writer
     */
    public PdfWriter getWriter() {
        return writer;
    }

    /**
     * @param writer the writer to set
     */
    public void setWriter(PdfWriter writer) {
        this.writer = writer;
    }

}
