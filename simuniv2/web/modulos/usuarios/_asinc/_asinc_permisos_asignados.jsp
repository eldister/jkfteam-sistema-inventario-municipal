<%@page import="simuni.entidades.mantenimientos.Permiso"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Permiso> permisos = (ArrayList<Permiso>) request.getAttribute("permisos");
    Iterator<Permiso> iter_permiso = null;
    if (permisos != null) {
        iter_permiso = permisos.iterator();
    }
    
    while (iter_permiso != null && iter_permiso.hasNext()) {
        Permiso permiso = iter_permiso.next();
        out.print("<option value='" + permiso.getCodigoPermiso() + "'>" + permiso.getNombrePermiso() + "</option>");
    }
%>