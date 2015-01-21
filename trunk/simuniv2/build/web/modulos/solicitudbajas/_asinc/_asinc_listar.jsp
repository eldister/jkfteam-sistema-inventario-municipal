<%@page import="simuni.clases.ui.MostradorSolicitudesBaja"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorSolicitudBaja"%>
<%@page import="simuni.utils.UtilidadesServlet"%>


<%
    String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
    int paginacion = 0;
    if (UtilidadesServlet.tryParseInt(pagaux)) {
        paginacion = Integer.parseInt(pagaux);
    }

    ResultSet listado = (ResultSet) request.getAttribute("listado");
    if(request.getAttribute("mod")!=null){
     out.write(new MostradorSolicitudesBaja().RenderizarActualizacion(listado, paginacion));   
    }else{
    out.write(new MostradorSolicitudBaja().RenderizarActualizacion(listado, paginacion));
    }
%>
