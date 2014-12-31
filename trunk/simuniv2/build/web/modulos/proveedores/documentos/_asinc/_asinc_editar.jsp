<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.EntregaDocumento"%>
<%
    EntregaDocumento entregadocumento = null;
    ArrayList<Respuesta> respuesta = null;
    boolean error = false;
    boolean proceso = false;
    try {
        entregadocumento = (EntregaDocumento) request.getAttribute("registro");
        respuesta = (ArrayList<Respuesta>) request.getAttribute("respuesta");
        if (respuesta != null && entregadocumento != null) {
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
        if(entregadocumento==null){
            entregadocumento=new EntregaDocumento();
        }
    } catch (Exception ex) {
        //redirigir a pagina de error
    }

%>
<style>


    .sm_div_formulario{
        padding: 20px;
    }
    .sm_div_formulario input[type=submit]{
        background-color: lightgray;
        width: 50%;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<script>
    cmbestadoentrega = "<%out.print(entregadocumento.getEstadoentrega()); %>";
    function loadcmb() {
        $("#cmbestadoentrega").val(cmbestadoentrega);
       
    }
</script>


<form class="sm_div_formulario" method="POST" enctype="multipart/form-data" id="formulario_actualizacion">
    <fieldset id="sm_fs_articulos">
        <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>" >Actualización de Entrega de Documentos</legend>
        <div class="sm_form_registroinformacion">
            <table class="sm_tb_campos table">
                <tr class="sm_table_formulariofila">
                    <th>
                        <label class="control-label">Estado de la entrega</label>
                    </th>
                    <td>
                        <select class="form-control" id="cmbestadoentrega" name="cmbestadoentrega">
                            <option>Aceptada</option>
                            <option>En estudio</option>
                            <option>Rechazada</option>
                        </select>
                    </td>  
                </tr>  
                <tr class="sm_table_formulariofila">
                    <th>
                        <label class="control-label">Observaciones</label>
                    </th>
                    <td>
                        <textarea class="form-control" placeholder="Observaciones" name="txtobservaciones"><%out.print(( entregadocumento != null) ? entregadocumento.getObseravaciones() : "");%></textarea>
                    </td>  
                </tr>  
                <tr class="sm_table_formulariofila">
                    <th>
                        <label class="control-label">Agregar Documento(s) a Entrega</label>
                    </th>
                    <td>
                        <input type="file" multiple="multiple" placeholder="Subir un Archivo" name="filedocumentos" class="form-control">
                    </td>  
                </tr>                  
                <tr class="sm_table_formulariofila">
                    <td colspan="2">
                        <input type="submit" class="form-control" id="btnsubmitform" value="Actualizar Información de Entrega">
                    </td>
                </tr>   
                <tr>
                    <td><input type="hidden" name="registroentrega" value="<%out.print(( entregadocumento != null) ? entregadocumento.getCodigo(): "");%>"></td>
                </tr>
            </table>
        </div>
    </fieldset>
    <%if (proceso) {%>  
    <footer id="area-mensajes">
        <div class="<%out.print((error) ? "text-danger bg-danger" : "text-success bg-success");%>">
            Resultados de la solicitud.
            <%if (error) {%>          
            <small>
                *Parece que hubo ciertos errores.
            </small>
            <%} else {%>
            <a href="#" onclick="nuevo_registro()">Registrar otra entrega</a>
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
    </footer>
    <%}%>
</form>
<div>
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
                                            alert("Se actualizo correctamente");
                                            $("#sm_body_ventanamodal").html('');
                                            $("#sm_body_ventanamodal").dialog('close'); 
                                            $("#sm_body_ventanamodal").dialog('destroy');
                                            location.reload();
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%}%>
</div>
<%
    entregadocumento=null;
%>
