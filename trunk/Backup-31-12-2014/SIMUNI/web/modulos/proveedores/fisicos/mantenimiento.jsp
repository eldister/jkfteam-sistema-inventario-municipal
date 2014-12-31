<%@page import="simuni.classes.LN.UtilidadesServlet"%>
<%@page import="simuni.classes.UI.ProveedoresFisicosMostrador"%>
<%@page import="simuni.classes.EN.ProveedorFisico"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Proveedores</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/recursos/estilos/style_grillageneral.css'>
        <script src='<%=request.getContextPath()%>/recursos/scripts/script_sm_grillageneral.js'></script>
        <script src='<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_mantenimientoproveedorfisico.js'></script>
        <script>
            $(document).ready(function() {
                sm_grillageneral_inicializar();
                inicializarpaginamantenimiento();
            });
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            
         String pagaux=request.getAttribute("paginacion")!=null?request.getAttribute("paginacion").toString():"1";
        int paginacion=0;
       if( UtilidadesServlet.tryParseInt(pagaux)){
           paginacion=Integer.parseInt(pagaux);
       }           
            ArrayList<ProveedorFisico> to_proveedores = (ArrayList<ProveedorFisico>)request.getAttribute("listadoproveedoresfisicos");
           
            out.write(new ProveedoresFisicosMostrador().RenderizarProveedoresFisicos(to_proveedores, paginacion));

        %>
       <div id="sm_body_ventanamodal">55555</div>
        <div id="sm_respuesta"></div>
    </decorator:content>
</decorator:decorate>