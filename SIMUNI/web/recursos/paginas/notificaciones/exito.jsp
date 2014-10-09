<%@page import="simuni.classes.EN.MensajesUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='/recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>
        <%
            out.print("Proceso correcto " + request.getParameter("id") != null ? "para " + request.getParameter("idactivo") : "Proceso correcto");
        %>
    </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_mensajesEE.css">
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div>
        <%
            out.print("siiiiiii"+request.getParameter("ayuda"));
            out.print("<div class='sm_body_contenedormensaje'>");
            out.print("Codigo de proceso :"+request.getParameter("id"));
            out.print("<hr>");
            out.print(MensajesUsuario.MensajesExito.EXITOREGISTROACTIVOARTICULO);    
            out.print("</div>");
            

        %>
        </div>
        <br/>
        <hr/>
        <br/>
        <div id="sm_div_contBotones">
            <div id="sm_div_reenvioInicio">
                <a href="#">Ir a la pantalla de inicio &raquo;</a>
            </div>
            <div id="sm_div_reenvioRegistroActivos">
                <a href="#">Ir a la pantalla de registro de activos &raquo;</a>
            </div>
            <div id="sm_div_reenvioGrilla">
                <a href="#">Ir a la pantalla de listado de los activos &raquo;</a>
            </div>
        </div>
    </decorator:content>
</decorator:decorate>