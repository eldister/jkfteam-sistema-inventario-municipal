<%@page import="simuni.entidades.mantenimientos.Puesto"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.Usuario"%>
<%
    Usuario usuario = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    ArrayList<Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    ArrayList<TipoUsuario> tiposusuarios = (ArrayList<TipoUsuario>) request.getAttribute("tiposusuario");
    ArrayList<Puesto> puestos = (ArrayList<Puesto>) request.getAttribute("puestos");

    Iterator<Departamento> iterador_departamento = null;
    Iterator<TipoUsuario> iterador_tipousuario = null;
    Iterator<Puesto> iterador_puesto = null;
    
    try {
        usuario = (Usuario) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");
        if (usuario != null) {
            proceso = true;
        }
        if (proceso &&respuesta!=null&& respuesta.getNivel() == 2) {
            error = true;
        }

        if (departamentos != null) {
            iterador_departamento = departamentos.iterator();
        }
        if (tiposusuarios != null) {
            iterador_tipousuario = tiposusuarios.iterator();
        } 
        if (puestos != null) {
            iterador_puesto = puestos.iterator();
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



<form class="sm_div_formulario" method="POST" id="formulario_actualizacion">
    <fieldset id="sm_fs_articulos">
        <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>" ><h4>Registro de Usuario <small> *(Tendrá que reingresar una contraseña para el usuario)</small></h4> </legend>
        <div class="sm_form_registroinformacion">
            <table class="sm_tb_campos table">
                <tr class="sm_table_formulariofila">
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtcedulausuario">Cédula </label>
                            <input  type="text" value="<%out.print((proceso) ? usuario.getCedula() : "");%>" class="form-control" name="txtcedulausuario" required="required" id="txtcedulausuario" placeholder="#-####-#####">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtnombre">Nombre </label>
                            <input type="text" value="<%out.print((proceso) ? usuario.getNombre() : "");%>" class="form-control" name="txtnombre"  id="txtnombre" placeholder="Ej. Juan">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtapellido1">1er Apellido</label>
                            <input type="text" value="<%out.print((proceso) ? usuario.getApellido1() : "");%>" class="form-control" name="txtapellido1"  id="txtapellido1" placeholder="Ej. Ramírez">
                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtapellido2">2do Apellido</label>
                            <input type="text" value="<%out.print((proceso)? usuario.getApellido2() : "");%>" class="form-control" name="txtapellido2" id="txtapellido2" placeholder="Ej. Ramírez">
                        </div>
                    </td>                    
                </tr> 
                <tr class="sm_table_formulariofila">
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtemail">E-mail </label>
                            <input type="email" value="<%out.print((proceso) ? usuario.getEmail(): "");%>" class="form-control" name="txtemail" id="txtemail" placeholder="Ej. ejemplo@ejemplo.com">
                        </div>
                    </td>                    
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="cmbdepartamento">Departamento *</label>
                            <select class="form-control" name="cmbdepartamento" required="required" id="cmbdepartamento">
                                <%
                                    if (iterador_departamento != null && iterador_departamento.hasNext()) {
                                        do {
                                            Departamento depto = iterador_departamento.next();
                                %>
                                <option <%out.print(((proceso) && usuario.getAreatrabajo() == depto.getIddepartamento()) ? "selected='selected'" : "");%> value="<%out.print(depto.getIddepartamento());%>"><%out.print(depto.getNombredepartamento());%></option>
                                <%
                                        } while (iterador_departamento.hasNext());
                                    }
                                %>
                            </select>
                        </div>                         
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="cmbpuesto">Puesto</label>
                            <select class="form-control" name="cmbpuesto" required="required" id="cmbpuesto">
                                <%
                                    if (iterador_puesto != null && iterador_puesto.hasNext()) {
                                        do {
                                            Puesto puesto = iterador_puesto.next();
                                %>
                                <option <%out.print(((proceso) && usuario.getCodigoPuesto()== puesto.getCodigoPuesto()) ? "selected='selected'" : "");%> value="<%out.print(puesto.getCodigoPuesto());%>"><%out.print(puesto.getNombrePuesto());%></option>
                                <%
                                        } while (iterador_puesto.hasNext());
                                    }
                                %>
                            </select>
                        </div>                         
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="cmbtipousuario">Tipo de Usuario</label>
                            <select class="form-control" name="cmbtipousuario" required="required" id="cmbtipousuario">
                                <%
                                    if (iterador_tipousuario != null && iterador_tipousuario.hasNext()) {
                                        do {
                                            TipoUsuario tipousuario = iterador_tipousuario.next();
                                %>
                                <option <%out.print(((proceso) && usuario.getTipousuario()== tipousuario.getIdtipousuario()) ? "selected='selected'" : "");%> value="<%out.print(tipousuario.getIdtipousuario());%>"><%out.print(tipousuario.getNombretipo());%></option>
                                <%
                                        } while (iterador_tipousuario.hasNext());
                                    }
                                %>
                            </select>
                        </div>                         
                    </td>                    
                </tr>
                <tr class="sm_table_formulariofila">
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtnombreusuario_l">Nombre de Usuario</label>
                            <input  type="text" readonly="readonly" value="<%out.print((proceso) ? usuario.getNombreusuario(): "");%>" class="form-control" name="txtnombreusuario_l" required="required" id="txtnombreusuario_l" placeholder="Ej. juancito">
                            <input type="hidden" value="<%out.print(proceso ? usuario.getNombreusuario(): "");%>" name="registro">

                        </div>
                    </td>
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtpassword1">Contraseña</label>
                            <input type="password" value="" class="form-control" name="txtpassword1" required="required" id="txtpassword1" placeholder="********">
                        </div>
                    </td>  
                    <td>
                        <div class="form-group">
                            <label  class="control-label"for="txtpassword2">Repite la Contraseña</label>
                            <input type="password" value="" class="form-control" name="txtpassword2" required="required" id="txtpassword2" placeholder="********">
                        </div>
                    </td>                      
                </tr>
                <tr class="sm_table_formulariofila">
                    <td>&nbsp;</td>
                    <td colspan="2">
                        <input type="submit" class="form-control" value="Actualizar Usuario">
                    </td>
                    <td>&nbsp;</td>
                </tr>                          
            </table>
        </div>
    </fieldset>
    <%if (proceso&&respuesta!=null) {%>  
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
