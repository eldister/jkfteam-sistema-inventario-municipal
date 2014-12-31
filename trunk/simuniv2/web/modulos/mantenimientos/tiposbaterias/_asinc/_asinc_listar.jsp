<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorTipoBateria"%>
<%@page import="simuni.entidades.mantenimientos.TipoBateria"%>
<%@page import="simuni.utils.UtilidadesServlet"%>


<%
    String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
    int paginacion = 0;
    if (UtilidadesServlet.tryParseInt(pagaux)) {
        paginacion = Integer.parseInt(pagaux);
    }

    ResultSet listado = (ResultSet) request.getAttribute("listado");
    out.write(new MostradorTipoBateria().RenderizarActualizacion(listado, paginacion));
%>
