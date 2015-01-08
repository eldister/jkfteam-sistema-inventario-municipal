<%@page import="simuni.enums.Recursos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%
    ArrayList<Notificacion> notificaciones = (ArrayList<Notificacion>) request.getAttribute("notificaciones");
    if (notificaciones != null) {
        Iterator<Notificacion> iter = notificaciones.iterator();
        if (iter.hasNext()) {
            while (iter.hasNext()) {
                Notificacion notificacion = iter.next();
%>
                    <div class='sm_div_mensaje'>
                        <div class='sm_div_notificacionfechacontainer'>
                            <% out.print(notificacion.getFechaNotificacion());%>
                            <hr>
                        </div>
                        <strong>
                            <%out.print(notificacion.getUsuarioOrigen());%>
                        </strong>
                        <p class='sm_div_textomensaje'>
                            <% out.print(notificacion.getDescripcionNotificacion());%>
                        </p>
                        <p><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/notificacion?proceso=ver_mensaje&id=<%out.print(notificacion.getIdNotificacion());%>">Ver</a></p>
                    </div> 
<%
                    }
                }
            }

%>