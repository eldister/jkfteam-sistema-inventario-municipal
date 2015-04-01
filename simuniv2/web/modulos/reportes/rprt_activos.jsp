<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.reportes.Rprt_Base"%>
<%@page import="java.util.ArrayList"%>
<%
    response.setContentType("application/pdf");
    try {
        String[] columns = null;
        ArrayList<String[]> datos = (ArrayList<String[]>) request.getAttribute("Rprt_Datos");
        boolean usaFechas = request.getAttribute("Usa_Fechas") != null && ((Boolean) request.getAttribute("Usa_Fechas"));
        System.out.print(request.getAttribute("Usa_Fechas"));
        java.sql.Date fein = (java.sql.Date) request.getAttribute("Fecha_Ini");
        java.sql.Date fin = (java.sql.Date) request.getAttribute("Fecha_Fin");
        String titulo = "";
        if (usaFechas) {
            titulo = "Reporte General de Activos Ingresados del " + fein + " al " + fin + " en el sistema";
        } else {
            titulo = "Reporte General de Activos";
        }

        if (datos != null && datos.size() > 0) {
            Iterator<String[]> iter = datos.iterator();

            columns = datos.get(0);
            datos.remove(0);
        }
        Rprt_Base base = new Rprt_Base(columns, datos, titulo, request.getSession().getAttribute("USERNAME") + "", "", true, response.getOutputStream());
        base.generarReporte();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>