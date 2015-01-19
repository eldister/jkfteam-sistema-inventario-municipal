<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Activoes </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <style>
            .sm_notificacion_fecha{

            }
            .sm_div_notificacion_block{
                padding: 20px;
                margin: 5px;
                margin-bottom: 10px;
                box-shadow: 0 0 5px rgba(0,0,0,0.7);
            }
            .sender_label{
                margin: 5px;
                font-size: 1.2em;
                text-decoration: overline;
                font-weight: 700;
            }


        </style>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Notificaciones</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) request.getAttribute("notificaciones");
            if (notificaciones != null) {
                Iterator<Notificacion> iter = notificaciones.iterator();
                if (iter.hasNext()) {
        %>
        <fieldset>
            <legend>Notificaciones del Usuario</legend>
            <%
                while (iter.hasNext()) {
                    Notificacion notificacion = iter.next();
            %> 

            <div class='sm_div_notificacion_block'>   
                <code class="sm_notificacion_fecha"> <% out.print(notificacion.getFechaNotificacion());%></code>
                <span class="sm_div_textonotificacionsender_block">
                    - Generado por <span class="sender_label"><% out.print(notificacion.getUsuarioOrigen());%></span> -
                </span>
                <span class='sm_div_textonotificacion_block'>
                    <% out.print(notificacion.getDescripcionNotificacion());%>
                </span>  

            </div>

            <%
                }
            %>
        </fieldset>
        <%
                }else{
         %>
            <div class='sm_div_notificacion_block'>   
                <strong class="text-info text-justify">Usuario no tiene notificaciones por el momento!</strong>
            </div>         
         <%
                }
            }
        %>
    </decorator:content>
</decorator:decorate>