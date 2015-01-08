<%@page import="simuni.entidades.Notificacion"%>
<%@page import="simuni.entidades.Respuesta"%>
<%
    boolean error = false;
    boolean proceso = false;
    Respuesta respuesta = null;
    Notificacion notificacion = null;

    notificacion = (Notificacion) request.getAttribute("registro");
    respuesta = (Respuesta) request.getAttribute("respuesta");

    if (respuesta != null && notificacion != null) {
        proceso = true;
    }
    if (proceso && respuesta.getNivel() == 2) {
        error = true;
    }
%>

<%if (proceso) {%>  
<div id="area-mensajes">
    <h4 class="<%out.print((error) ? "text-danger bg-danger" : "text-success bg-success");%>">
        <%out.print(respuesta.getMensaje());%>
        <%if (!error) {%>          
        <small>
            Mensaje enviado a <%out.print(notificacion!=null?notificacion.getUsuarioObjetivo():"");%>
        </small>
        <%}%>
    </h4>
</div>
<%}%>