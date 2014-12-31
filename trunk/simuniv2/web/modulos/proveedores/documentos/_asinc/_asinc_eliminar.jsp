<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.EntregaDocumento"%>
<%
    EntregaDocumento entregadocumento = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    try {
        entregadocumento = (EntregaDocumento) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");
        if (entregadocumento != null) {
            proceso = true;
        }
        if (proceso && respuesta != null && respuesta.getNivel() == 2) {
            error = true;
            out.print("estoy entrando aqui" + respuesta.getMensaje());
        }
    } catch (Exception ex) {

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



<form class="sm_div_formulario" method="POST" id="formulario_eliminacion">
    <fieldset id="sm_fs_articulos">
        <legend style="<%out.print((error && proceso) ? "color:red;" : "color:green");%>" >Actualizacion de Tipo Pago</legend>
        <div class="sm_form_registroinformacion">
            <table class="sm_tb_campos table">
                <tr class="sm_table_formulariofila">
                    <th>
                        <label>Estado de la entrega</label>
                    </th>
                    <td>
                        <input type="text" readonly="readonly" value="<%out.print(proceso ? entregadocumento.getEstadoentrega(): "");%>" class="form-control" id="txtnombreentregadocumento" required="required"  name="txtnombreentregadocumento">
                        <input type="hidden" value="<%out.print(proceso ? entregadocumento.getCodigo(): "");%>" name="registro">
                    </td>  
                </tr>  
                <tr class="sm_table_formulariofila">
                    <th>
                        <label class="control-label">Observaciones</label>
                    </th>
                    <td>
                        <textarea readonly="readonly" class="form-control" placeholder="Observaciones" name="txtobservaciones"><%out.print(( entregadocumento != null) ? entregadocumento.getObseravaciones() : "");%></textarea>
                    </td>  
                </tr>                  
                <tr class="sm_table_formulariofila">
                    <td>
                        <input type="submit" class="form-control" value="Eliminación">
                        
                    </td>
                    <td>
                       <input name="chkeliminardocumentos" type="checkbox"> Eliminar tambien documentos
                    </td>
                </tr>                          
            </table>
        </div>
    </fieldset>
    <%if (proceso && respuesta != null) {%>  
    <footer id="area-mensajes">
        <h4 class="<%out.print((error) ? "text-danger bg-danger" : "text-success bg-success");%>">
            <%out.print(respuesta.getMensaje());%>
            <%if (error) {%>          
            <small>
                Puede reintentarlo
            </small>
            <%}%>
        </h4>
    </footer>
    <%}%>
</form>
<div>
    <%if (proceso && respuesta != null && !error) { %>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <div>
                                    <script>   
                                            alert("Se elimino el registro correctamente");
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
