<%@page import="simuni.classes.EN.MensajesUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='/recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>

        <%
            out.print("Proceso correcto " + request.getParameter("id") != null ? "para " + request.getParameter("idactivo") : "Proceso correcto");
        %>
    </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>Insert title here</decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div>66666
        <%
            out.print("<div class='sm_body_contenedormensaje'>");
            out.print("Codigo de proceso :"+request.getParameter("id"));
            out.print("<hr>");
            out.print(MensajesUsuario.MensajesExito.EXITOREGISTROACTIVOARTICULO);    
            out.print("</div>");
            

        %></div>
    </decorator:content>
</decorator:decorate>