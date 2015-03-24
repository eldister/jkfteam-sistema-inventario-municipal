<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.reportes.Rprt_Base"%>
<%@page import="com.itextpdf.text.Chunk"%>
<%@page import="java.io.StringReader"%>
<%@page import="com.itextpdf.text.html.simpleparser.HTMLWorker"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="com.itextpdf.text.pdf.ColumnText"%>
<%@page import="com.itextpdf.text.pdf.PdfPageEventHelper"%>
<%@page import="com.itextpdf.text.pdf.PdfTemplate"%>
<%@page import="com.itextpdf.text.Element"%>
<%@page import="com.itextpdf.text.BaseColor"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.pdf.PdfPCell"%>
<%@page import="com.itextpdf.text.Phrase"%>
<%@page import="com.itextpdf.text.pdf.PdfPTable"%>
<%@page import="com.itextpdf.text.Font"%>
<%@page import="com.itextpdf.text.pdf.PdfContentByte"%>
<%@page import="com.itextpdf.text.PageSize"%>
<%@page import="com.itextpdf.text.Rectangle"%>
<%@page import="com.itextpdf.text.pdf.BaseFont"%>
<%@page import="com.itextpdf.text.Document"%>
<%@page import="com.itextpdf.text.Paragraph"%>
<%@page import="com.itextpdf.text.pdf.PdfWriter"%>
<%

    response.setContentType("application/pdf");
    try {
        String[] columns = new String[]{"Placa", "Descripción", "Precio", "Departamento", "Estado", "Categoría", "Modelo", "Marca"};
        ArrayList<String[]> datos = new ArrayList<String[]>();
        String[] datos2 = new String[]{"123456789123", "123456789123", "123456789123", "123456789123", "123456789123", "123456789123", "123456789123", "123456789123",};
        datos.add(datos2);
        datos.add(datos2);
        datos.add(datos2);
        datos.add(datos2);
        datos.add(datos2);
        Rprt_Base base = new Rprt_Base(columns, datos, "Reporte general Activos", "Ignacio", "", true, response.getOutputStream());
        base.generarReporte();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>