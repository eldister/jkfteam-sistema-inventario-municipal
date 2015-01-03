<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.ImagenActivo"%>
<%

    ArrayList<ImagenActivo> imagenes = (ArrayList<ImagenActivo>) request.getAttribute("imagenes");
    String registro = (String) request.getParameter("registro");
    Iterator<ImagenActivo> iterador_imagenes = null;
    ArrayList<Respuesta> respuesta = null;
    boolean error = false;
    boolean proceso = false;
    try {
        respuesta = (ArrayList<Respuesta>) request.getAttribute("respuesta");
        if (respuesta != null) {
            proceso = true;
        }
        if (proceso) {
            Iterator<Respuesta> iresp = respuesta.iterator();
            if (iresp.hasNext()) {
                Respuesta r = iresp.next();
                if (r.getNivel() == 2) {
                    error = true;
                }
            }
        }
    } catch (Exception ex) {
        //redirigir a pagina de error
    }
    if (imagenes != null) {
        iterador_imagenes = imagenes.iterator();
    }
%>
<fieldset>
    <legend>Imagenes del Activo <strong><%out.print(registro);%></strong></legend>
    <div class="sm_imgcontainer" id="sm_imgcontainer">
        <%
            if (iterador_imagenes != null && iterador_imagenes.hasNext()) {
                while (iterador_imagenes.hasNext()) {
                    ImagenActivo imgaux = iterador_imagenes.next();
        %> 
        <div class="sm_imgcontainer_itemcontainer">
            <img class='sm_imgcontainer_item' width='300' height='300'  src='<%out.print(imgaux.getUrldocumento());%>' title='Vista Previa imagen del activo <%out.print(imgaux.getCodigoActivo());%>, Subido el <%out.print(imgaux.getFechaSubida());%>'/>
        </div>
        <%
            }
        } else {
        %>
        <center><strong>No tiene imagenes registradas.</strong></center>
            <%
                }
            %>

    </div>
</fieldset>
<fieldset class="formulario_subida_imagencontainer">
    <legend><h4>Agregar Una Imagen<small>* (cada activo tiene un máximo de 4 imágenes)</small></h4></legend>

    <form class="form" enctype="multipart/form-data" id="formulario_subidaimagen"  method="POST">

        <div class="form-group">
            <label  class="control-label"for="filearchivos">Seleccionar</label>
            <input type="file" class="form-control" required="required" multiple="multiple" name="filearchivos" id="filearchivos">
        </div>   
        <div class="form-group">
            <input type="submit" id="btnsubmitform" value="Subir Imagen" class="form-control btn-info">
            <input type="hidden" value="<%out.print(registro);%>" id="registro">
        </div>
    </form>
</fieldset>
<%if (proceso) {%>  
<div id="area-mensajes">
    <div class="<%out.print((error) ? "text-danger bg-danger" : "text-success bg-success");%>">
        Resultados de la solicitud.
        <%if (error) {%>          
        <small>
            *Parece que hubo ciertos errores.
        </small>
        <%}%>
        <details>
            <summary>Ver Resultados (Recomendado)</summary>
            <ul>

                <%
                    Iterator<Respuesta> iteradorrespuestas = respuesta.iterator();
                    if (iteradorrespuestas != null) {
                        while (iteradorrespuestas.hasNext()) {
                            Respuesta au = iteradorrespuestas.next();
                %>
                <li>
                    <%
                        if (au.getNivel() == 2) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                        <span class="sr-only">Error:</span>
                        <%out.print(au.getMensaje());%>
                    </div>
                    <%} else {%>
                    <div class="alert alert-success" role="alert">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                        <span class="sr-only">Correcto:</span>
                        <%out.print(au.getMensaje());%>
                    </div>                        
                    <%}%>
                </li>   
                <%
                        }
                    }
                %>

            </ul>
            <%%>
        </details>
    </div>
</div>
<%}%>        

<style>
    .formulario_subida_imagencontainer{
        margin-top: 50px;
    }
    .sm_imgcontainer_itemcontainer{
        display: inline-block;
        box-shadow: 0 0 5px rgba(0,0,0,0.5);
        padding: 5px;
        margin: 10px;
        text-align: center;
    }

    .sm_imgcontainer{
        text-align: center;
    }

</style>
<script>

</script>

<%if (proceso && !error) { %>
<div>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <script>
                                    setTimeout(function(){
                                    if (confirm("¿Desea agregar mas imágenes?")) {
                                        $("#area-mensajes").remove();
                                       
                                        addsubidaImagenEvento();
                                    } else { 
                                        $("#filearchivos").remove();
                                        $("#btnsubmitform").remove();
                                        $(".formulario_subida_imagencontainer").remove();
                                        
                                    }},1500);
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%} else if (error) {%>
<script>addsubidaImagenEvento();</script>
<%}%>