<%@page import="simuni.enums.Recursos"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) request.getAttribute("notificaciones");
    if (notificaciones != null) {
        Iterator<Notificacion> iter = notificaciones.iterator();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
                Notificacion notificacion = iter.next();
%> 
            <div class='sm_div_notificacion'>
                <div class='sm_div_notificacionfechacontainer'>
                    <% out.print(notificacion.getFechaNotificacion());%>
                    <hr>
                </div>
                <p class='sm_div_textonotificacion'>
                    <% out.print(notificacion.getDescripcionNotificacion());%>
                </p>
                <p><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/notificacion?proceso=notificaciones">Ver</a></p>
                                  
            </div> 
<%
                    }
                }
            }
%>