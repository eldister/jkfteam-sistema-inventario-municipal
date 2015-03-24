<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.reportes.Rprt_Base"%>
<%@page import="java.util.ArrayList"%>
<%
    response.setContentType("application/pdf");
    try {
        String[] columns = null;
        ArrayList<String[]> datos =(ArrayList<String[]>)request.getAttribute("Rprt_Datos");
        if(datos!=null){
            Iterator<String[]>iter=datos.iterator();

            columns=datos.get(0);
            datos.remove(0);
        }
        Rprt_Base base = new Rprt_Base(columns, datos, "Reporte General de Proveedores Activos", request.getSession().getAttribute("USERNAME")+"", "", true, response.getOutputStream());
        base.generarReporte();
    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>