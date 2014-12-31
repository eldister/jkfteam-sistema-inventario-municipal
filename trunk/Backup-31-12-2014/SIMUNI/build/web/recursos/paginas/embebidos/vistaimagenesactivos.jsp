<%@page import="java.util.Iterator"%>
<%@page import="simuni.classes.LN.UtilidadesServlet"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.classes.EN.imagenActivo"%>

<fieldset>
    <%
        ArrayList<imagenActivo> imagenes = (ArrayList<imagenActivo>) request.getAttribute("imagenes");
        String codigoactivo = "0";
        codigoactivo = (request.getAttribute("codigoactivo").toString());

        if (imagenes != null) {

            Iterator<imagenActivo> iteradorimagenes = imagenes.iterator();
            if (iteradorimagenes!=null) {
                out.print("<legend>Imagenes del activo ");
                out.print(codigoactivo);
                out.print("</legend>");
                out.print("<div class='sm_fieldset_generalimgcontainer'>");
                while (iteradorimagenes.hasNext()) {
                    imagenActivo imagen = iteradorimagenes.next();
                    out.print("<div class='sm_fieldset_itemimgcontainer'>");
                    out.print(" <img src='");
                    out.print(imagen.getPa_url());
                    out.print("/");
                    out.print(imagen.getPa_nombreArchivo());
                    out.print("' width='250' height='250'>");
                    out.print("</div>");

                }
            }
            else{
            out.print("<strong>No existen imagenes asociadas!!</strong>");    
            }

        } else {
            out.print("<strong>No existen imagenes asociadas!! Vacio</strong>");
        }
        out.print("</div>");
        out.print("</fieldset>");

    %>

