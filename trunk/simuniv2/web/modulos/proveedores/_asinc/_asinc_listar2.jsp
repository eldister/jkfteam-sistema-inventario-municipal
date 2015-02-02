<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page import="java.util.ArrayList"%>
<%
ArrayList<Proveedor> proveedores=(ArrayList<Proveedor>)request.getAttribute("registros");
Iterator<Proveedor>iter_proveedor=proveedores!=null?proveedores.iterator():null;
%>

<meta charset="utf-8">
<div>
    <table class="table table-bordered table-condensed table-striped">
        <%
            if(iter_proveedor!=null&&iter_proveedor.hasNext()){
        %> 
        <tr><th>Cédula</th><th>Nombre</th><th>Apellido 1</th><th>Apellido 2</th><th>Email</th></tr>
        <%
            while(iter_proveedor.hasNext()){
                Proveedor provaux=iter_proveedor.next();
            
        %>
        <tr>
            <td><%out.print(provaux.getCedula());%></td>
            <td><%out.print(provaux.getNombre());%></td>
            <td><%out.print(provaux.getPrimerApellido());%></td>
            <td><%out.print(provaux.getSegundoApellido());%>
            </td><td class="email_container"><%out.print(provaux.getEmail());%></td>
        </tr>
        <%}%>
        <%}else{%>
        <tr><td><center><strong>No hay proveedores en esta clasificación</strong></center></td></tr>
        <%}%>
    </table>
</div>