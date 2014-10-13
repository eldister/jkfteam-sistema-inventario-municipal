<%@page import="simuni.classes.EN.TipoActivo"%>
<%@page import="simuni.classes.UI.ActivosMostrador"%>
<%@page import="simuni.classes.EN.Activos_Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Activos</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/recursos/estilos/style_grillageneral.css'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/recursos/estilos/style_actualizacionarticulos.css'>        
        <script src='<%=request.getContextPath()%>/recursos/scripts/script_sm_grillageneral.js'></script>
        <script src='<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_mantenimientoactivos.js'></script>
        
        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
                inicializarpaginamantenimiento();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            ArrayList<Activos_Articulos> to_articulo=(ArrayList<Activos_Articulos>)request.getAttribute("listadoarticulos");
            ArrayList<TipoActivo>to_tipoactivo=(ArrayList<TipoActivo>)request.getAttribute("listadotiposactivo");
            out.write(new ActivosMostrador().RenderizarActivos(to_tipoactivo,to_articulo,10));

                
        %>
        <div id="sm_body_ventanamodal">55555</div>
        <div id="sm_respuesta"></div>
    </decorator:content>
</decorator:decorate>