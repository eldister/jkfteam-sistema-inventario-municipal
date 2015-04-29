<%
    request.setAttribute("permiso_requerido", 25);
%>
<%@page import="simuni.clases.ui.MostradorPrestamo"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Prestamo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
        <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js' charset="utf-8"></script>
                <script src='<%=request.getContextPath()%>/js/script_paginas/script_modulo_prestamoindex.js' charset="utf-8"></script>

 </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Prestamo</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
            String query = (String) request.getAttribute("query");
            //String mostrar_inactivos=(String)request.getAttribute("mostrar_inactivos");
            //if(mostrar_inactivos==null){mostrar_inactivos="";}
            
            int paginacion = 0;
            if (UtilidadesServlet.tryParseInt(pagaux)) {
                paginacion = Integer.parseInt(pagaux);
            }
            ResultSet listado = (ResultSet) request.getAttribute("listado");
            out.write(new MostradorPrestamo().Renderizar(listado, paginacion));
        %>
        <div id="sm_body_ventanamodal">&nbsp;</div>
        <script>
            $(document).ready(function() {
                $("#sm_div_txtcriteriobusquedagrilla").val("<%out.print(query);%>");
                
            });
        </script>

    </decorator:content>
</decorator:decorate>