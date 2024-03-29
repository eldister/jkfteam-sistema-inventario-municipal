<%
    request.setAttribute("permiso_requerido", 45);
%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorUsuarioInactivo"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Mantenimientos - Usuarios </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
        <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_usuarios_inactivos.js' charset="utf-8"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/usuario">Usuarios</a></li> 
            <li class="active">Usuarios Inactivos</li>
        </ol>
            
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
            String query = (String) request.getAttribute("query");
            int paginacion = 0;
            if (UtilidadesServlet.tryParseInt(pagaux)) {
                paginacion = Integer.parseInt(pagaux);
            }
            ResultSet listado = (ResultSet) request.getAttribute("listado");
            out.write(new MostradorUsuarioInactivo().Renderizar(listado, paginacion));
        %>
        <div id="sm_body_ventanamodal">&nbsp;</div>
        <script>
            $(document).ready(function() {
                $("#sm_div_txtcriteriobusquedagrilla").val("<%out.print(query);%>");
            });
        </script>
    </decorator:content>
</decorator:decorate>