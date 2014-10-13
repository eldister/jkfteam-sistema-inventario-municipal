<%@page import="simuni.classes.EN.RE.Reporte_ActivoArticuloParticular"%>
<%@page import="simuni.classes.LN.UtilidadesServlet"%>
<%@page import="simuni.classes.EN.TipoPago"%>
<%@page import="simuni.classes.EN.EstadoActivo"%>
<%@page import="simuni.classes.EN.Activos_Articulos"%>
<%@page import="simuni.classes.EN.Departamento"%>
<%@page import="simuni.classes.EN.TipoActivo"%>
<%@page import="java.util.ArrayList"%>
<%

    String cod = request.getParameter("mod");
    int modo = 0;
    if (UtilidadesServlet.tryParseInt(cod)) {
         
        
        
        modo = Integer.parseInt(cod);
        switch (modo) {
            case 1: //reporte de activo articulo particular inicio
                ArrayList<Departamento> deptos = (ArrayList<Departamento>) request.getAttribute("departamentos");
                ArrayList<TipoActivo> tiposactivos = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");
                ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
                ArrayList<EstadoActivo> tiposestadoactivo = (ArrayList<EstadoActivo>) request.getAttribute("estadoactivos");
                Activos_Articulos activo = (Activos_Articulos) request.getAttribute("articulo");
                if (activo == null) {
                    activo = new Activos_Articulos();
                }
                Reporte_ActivoArticuloParticular re = new Reporte_ActivoArticuloParticular();
                response.reset();

                re.generarReporte(" jef0143 ",activo,deptos,tiposactivos,tipospago,tiposestadoactivo, response.getOutputStream());

                break;//reporte de activo articulo particular fin
        }
    }


%>