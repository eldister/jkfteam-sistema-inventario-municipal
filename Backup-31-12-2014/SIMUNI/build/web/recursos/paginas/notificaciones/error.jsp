<%  request.getSession().setAttribute("ERROR", "error"); %>
<%@page import="java.util.Date"%>
<%@page import="simuni.classes.EN.MensajesUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='/recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>
        <%
            out.print("SIMUNI - Error ");
        %>
    </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_mensajesEE.css">
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div>
            <%
                int tipomensaje = Integer.parseInt(request.getParameter("msg"));

                switch (tipomensaje) {
                    case 1:
                        out.print("<div class='sm_body_contenedormensaje'>");
                        out.print("Codigo de proceso :" + request.getParameter("id"));
                        out.print("<hr>");
                        out.print(MensajesUsuario.MensajesError.ERRORENREGISTROACTIVOARTICULO);
                        out.print("</div>");
                        break;
                    case 2:
                        break;
                    case 3:
                        //eliminar
                        out.print("<div class='sm_body_contenedormensaje'>");
                        out.print("El proceso de eliminación no se completó");
                        out.print("<hr>");
                        out.print("Codigo de proceso :" + request.getParameter("id"));
                        out.print(new Date().toString());
                        out.print("</div>");
                        break;
                    case 4:
                        out.print("<div class='sm_body_contenedormensaje'>");
                        out.print("El sistema ha presentado una falla inesperada");
                        out.print("<hr>");
                        out.print("Razónes posible  :") ;
                        out.print("<ol>");
                        out.print("<li>No se puede connectar a la base de datos</li>");
                        out.print("<li>El tiempo de respuesta ha sobrepasado el tiempo limite</li>");
                        out.print("<li>Se esta en labores de mantenimiento</li>");
                        out.print("<li>No hay conexión con el servidor</li>");
                        out.print("</ol>");
                       // out.print(request.getParameter("id"));
                       // out.print("</sub>");

//+// request.getParameter("id"));
                        out.print(new Date().toString());
                        out.print("</div>");
                        break;

                }

//DE ACUERDO al tipo de usuario se ha de mostrar lo siguiente. en un primer momento solo ignacio.
%>
        </div>
        <br/>
        <hr/>
        <br/>

        <div id="sm_div_contBotones">
            <div id="sm_div_reenvioInicio">
                <a href="/SIMUNI">Ir a la pantalla de inicio &raquo;</a>
            </div>
        </div>
    </decorator:content>
</decorator:decorate>