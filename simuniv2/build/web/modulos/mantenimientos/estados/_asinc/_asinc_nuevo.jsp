<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.mantenimientos.Estado"%>
<%
    Estado estado = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    try {
        estado = (Estado) request.getAttribute("nuevoregistro");
        respuesta = (Respuesta) request.getAttribute("respuesta");
        if (respuesta != null && estado != null) {
            proceso = true;
        }
        if (proceso && respuesta.getNivel() == 2) {
            error = true;
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



<form class="sm_div_formulario" method="POST" id="formulario_registro">
    <fieldset id="sm_fs_articulos">
        <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>" >Registro de Estado</legend>
        <div class="sm_form_registroinformacion">
            <table class="sm_tb_campos table">
                <tr class="sm_table_formulariofila">
                    <th>
                        <label>Nombre del Estado</label>
                    </th>
                    <td>
                        <input type="text" value="<%out.print((error && proceso && estado != null) ? estado.getNombreestado(): "");%>" class="form-control" id="txtnombreestado" required="required"  name="txtnombreestado">
                    </td>  
                </tr>  
                <tr class="sm_table_formulariofila">
                    <td colspan="2">
                        <input type="submit" class="form-control" value="Registrar">
                    </td>
                </tr>                          
            </table>
        </div>
    </fieldset>
    <%if (proceso) {%>  
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
    <%if (proceso && !error) {%>
    <div>
        <div>
            <div>
                <div>
                    <div>
                        <div>
                            <div>
                                <div>
                                    <script>
                                        if (confirm("¿Desea agregar un nuevo registro?")) {
                                            $("#txtnombreestado").val('');
                                            $("#area-mensajes").remove();
                                            AddSubmitFormNuevoEventHandler();
                                        }
                                        else {
                                            $("#sm_body_ventanamodal").html('');
                                            $("#sm_body_ventanamodal").dialog('close');
                                            $("#sm_body_ventanamodal").dialog('destroy');
                                            location.reload();
                                        }
                                    </script>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%} else if(error) {%>
    <script> AddSubmitFormNuevoEventHandler();</script>
    <%}%>
</div>
