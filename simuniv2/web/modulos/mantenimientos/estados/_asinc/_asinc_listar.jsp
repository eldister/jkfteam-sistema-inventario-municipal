<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorEstado"%>
<%@page import="simuni.utils.UtilidadesServlet"%>


<%
    String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
    int paginacion = 0;
    if (UtilidadesServlet.tryParseInt(pagaux)) {
        paginacion = Integer.parseInt(pagaux);
    }

    ResultSet listado = (ResultSet) request.getAttribute("listado");
    out.write(new MostradorEstado().RenderizarActualizacion(listado, paginacion));
%>