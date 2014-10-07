<%@page import="simuni.classes.EN.MensajesUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='/recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>

        <%
            out.print("Proceso correcto " + request.getParameter("id") != null ? "para " + request.getParameter("idactivo") : "Proceso fallido");
        %>
    </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>Insert title here</decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div>
        <%
            out.print("<div class='sm_body_contenedormensaje'>");
            out.print("Codigo de proceso :"+request.getParameter("id"));
            out.print("<hr>");
            out.print(MensajesUsuario.MensajesError.ERRORENREGISTROACTIVOARTICULO);    
            out.print("</div>");
            

        %>
        </div>
        <div id="sm_div_contBotones">
            <div id="sm_div_reenvioInicio">
                <a href="../../../">Ir a la pantalla de inicio</a>
            </div>
            <div id="sm_div_reenvioRegistroActivos">
                <a href="../../../modulos/activos/articulos/registro.jsp">Ir a la pantalla de Registro</a>
            </div>
            <div id="sm_div_reenvioGrilla">
                <a href="../../../modulos/activos/articulos/mantenimiento_1.jsp">Ir a la pantalla de listado</a>
            </div>
        </div>
    </decorator:content>
</decorator:decorate>