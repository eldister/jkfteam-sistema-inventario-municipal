<%
    request.setAttribute("permiso_requerido", 34);
%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorEntregaDocumento"%>
<%@page import="simuni.entidades.EntregaDocumento"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Entrega Documentos Proveedor </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel='stylesheet' href='<%=request.getContextPath()%>/css/style_grillageneral.css'>
        <script src='<%=request.getContextPath()%>/js/script_sm_grillageneral.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_entregadocumento.js' charset="utf-8"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=listado">Proveedores</a></li>  
            <li class="active">Entrega Documentos de Proveedor</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            /*para parte de proveedor*/
            Proveedor proveedor = null;

            try {
                proveedor = (Proveedor) request.getAttribute("registro");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            if (proveedor == null) {
                proveedor = new Proveedor();
            }
        %>
        <fieldset>
            <legend>Datos del Proveedor</legend> 
            <table class="sm_datos_registro_table table table-condensed table-bordered ">
                <tr>
                    <th>Cédula</th><td><%out.print(proveedor.getCedula()); %></td>
                    <th>Nombre</th><td><%out.print(proveedor.getNombre()); %></td> 
                    <th>1er Apellido</th><td><%out.print(proveedor.getPrimerApellido()); %></td>
                    <th>2do Apellido</th><td><%out.print(proveedor.getSegundoApellido()); %></td>
                </tr>                       
                <tr>
                    <th>Telefono</th><td><%out.print(proveedor.getTelFijo()); %></td>
                    <th>Telefono oficina</th><td><%out.print(proveedor.getTelOfic()); %></td>
                    <th>Telefono movil</th><td><%out.print(proveedor.getTelMovil()); %></td>
                    <th>fax</th><td><%out.print(proveedor.getTelFax()); %></td>
                </tr> 
                <input type="hidden" value="<%out.print(proveedor.getCedula()); %>" id="cedulaproveedor">
            </table>
        </fieldset>
        <%
            String pagaux = request.getAttribute("paginacion") != null ? request.getAttribute("paginacion").toString() : "1";
            String query = (String) request.getAttribute("query");
            int paginacion = 0;
            if (UtilidadesServlet.tryParseInt(pagaux)) {
                paginacion = Integer.parseInt(pagaux);
            }
            ResultSet listado = (ResultSet) request.getAttribute("listado");
            out.write(new MostradorEntregaDocumento().Renderizar(listado, paginacion));
        %>
        <div id="sm_body_ventanamodal">&nbsp;</div>
        <input type="hidden" value="<%out.print(proveedor.getCedula()); %>"  id="cedulaproveedor">
        <script>
            $(document).ready(function() {
                $("#sm_div_txtcriteriobusquedagrilla").val("<%out.print(query);%>");
            });
        </script>
    </decorator:content>
</decorator:decorate>