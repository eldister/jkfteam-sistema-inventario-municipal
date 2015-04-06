<%@page import="simuni.clases.ui.MostradorPrestamo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/bootstrap.min.css'>
    </head>
    <body>
        <div id="sm_proveedorindexcontainer">
            <%
                String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
                String query = (String) request.getAttribute("query");
                String mostrar_inactivos = (String) request.getAttribute("mostrar_inactivos");
                if (mostrar_inactivos == null) {
                    mostrar_inactivos = "";
                }

                int paginacion = 0;
                if (UtilidadesServlet.tryParseInt(pagaux)) {
                    paginacion = Integer.parseInt(pagaux);
                }
                ResultSet listado = (ResultSet) request.getAttribute("listado");
                out.write(new MostradorPrestamo().Renderizar_Asinc(listado, paginacion));
            %>
            <script src='<%=request.getContextPath()%>/js/script_jquery-2.1.1.min.js' charset="utf-8"></script>
            <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js' charset="utf-8"></script>
            <script src='<%=request.getContextPath()%>/js/script_paginas/script_modulo_prestamoindex_asinc.js' charset="utf-8"></script>

        </div>
    </body>
</html>
