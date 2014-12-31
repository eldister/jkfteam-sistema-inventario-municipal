<%@page import="simuni.classes.LN.UtilidadesServlet"%>
<%@page import="simuni.classes.EN.Notificacion"%>
<%@page import="simuni.classes.EN.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.classes.LN.ManejadorNotificaciones"%>
<%
    String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
    String modoaux = request.getParameter("mod");
    ManejadorNotificaciones manejadornotif = new ManejadorNotificaciones();
    int modo = 0;
    System.out.println(modoaux);
    if (UtilidadesServlet.tryParseInt(modoaux)) {
        modo = Integer.parseInt(modoaux);
    }
    switch (modo) {
        case 1:
            //notificacion

            ArrayList<Notificacion> notificaciones = manejadornotif.obtenerUltimasNotificacionesUsuario(idusuario);
            if (notificaciones != null) {
                Iterator<Notificacion> iter = notificaciones.iterator();
                if (iter.hasNext()) {
                    while (iter.hasNext()) {
                        System.out.println("otra notificacione");
                        Notificacion notificacion = iter.next();
                        out.print("<div class='sm_div_notificacion'>");
                        out.print("<div class='sm_div_notificacionfechacontainer'>");
                        out.print(notificacion.getFechaNotificacion().toLocaleString());
                        out.print("        <hr>");
                        out.print("      </div>");
                        out.print("<p class='sm_div_textonotificacion'>");
                        out.print(notificacion.getDescripcionNotificacion());
                        out.print("</p>");
                        out.print("</div> ");
                        out.print("<hr>");
                    }
                }
            }

            break;
        case 2:
            //mensajes
            notificaciones = manejadornotif.obtenerUltimosMensajesUsuario(idusuario);
            if (notificaciones != null) {
                Iterator<Notificacion> iter = notificaciones.iterator();
                if (iter.hasNext()) {
                    while (iter.hasNext()) {
                        Notificacion notificacion = iter.next();
                        out.print("<div class='sm_div_mensaje'>");
                        out.print("<div class='sm_div_notificacionfechacontainer'>");
                        out.print(notificacion.getFechaNotificacion().toLocaleString());
                        out.print("        <hr>");
                        out.print("      </div>");
                        out.print("<strong>");
                        out.print(notificacion.getUsuarioOrigen());
                        out.print("</strong>");
                        out.print("<p class='sm_div_textomensaje'>");
                        out.print(notificacion.getDescripcionNotificacion());
                        out.print("</p>");
                        out.print("</div> ");
                        out.print("<hr>");
                    }
                }
            }

            break;
        default:

    }

%>