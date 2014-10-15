<%@page import="java.util.Iterator"%>
<%@page import="simuni.classes.EN.ProveedorFisico"%>
<%@page import="java.util.ArrayList"%>
<fieldset>
    <legend>Listado de Proveedores</legend>
    <%
        ArrayList<ProveedorFisico> proveedores = (ArrayList<ProveedorFisico>) request.getAttribute("listadoproveedoresfisicos");
        if (proveedores != null) {
            Iterator<ProveedorFisico> iter = proveedores.iterator();
            if (iter != null) {
                              
                out.print("<table>");
                out.print("<tr>"
                        + "<th>Identificador de Proveedor</th>"
                        + "<th>Nombre Proveedor</th>"
                        + "<th>Primer Apellido</th>"
                        + "<th>Segundo Apellido</th>"
                        + "<th>Email </th>"
                        + "<th>Movil</th>"
                        + "</tr>");
                while (iter != null && iter.hasNext()) {
                    ProveedorFisico item=iter.next();
                      out.print("<tr>");
                        out.print("<td>");
                        out.print("<span class='sm_popup_identificadorproveedor'>");
                        out.print(item.getPa_cedula());
                        out.print("</span>");
                        out.print("</td>");
                        out.print("<td>");
                        out.print(item.getPa_primerApellido());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(item.getPa_segundoApellido());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(item.getPa_correoElectronico());
                        out.print("</td>");
                        out.print("<td>");
                        out.print(item.getPa_telefonoMovil());
                        out.print("</td>");
                       out.print("</tr>");


                }
                
                out.print("</table>");
            }
            else{
                out.print("<strong>No hay proveedores registrados!!</strong>");
            }

        }
        else{
                            out.print("<strong>No hay proveedores registrados!!</strong>");
        }
    %>

</fieldset>


