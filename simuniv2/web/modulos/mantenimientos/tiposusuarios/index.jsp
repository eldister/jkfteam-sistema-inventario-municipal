<%@page import="simuni.enums.Recursos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorTipoUsuario"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Mantenimientos - Tipos de Usuario </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
        <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_tipousuario.js' charset="utf-8"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Tipos de Usuario</li>
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
            out.write(new MostradorTipoUsuario().Renderizar(listado, paginacion));
        %>
        <div id="sm_body_ventanamodal">&nbsp;</div>
        <script>
            $(document).ready(function() {
                $("#sm_div_txtcriteriobusquedagrilla").val("<%out.print(query);%>");
            });
        </script>
    </decorator:content>
</decorator:decorate>