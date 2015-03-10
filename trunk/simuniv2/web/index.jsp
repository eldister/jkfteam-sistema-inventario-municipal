<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="simuni.entidades.mantenimientos.Puesto"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="simuni.entidades.Respuesta"%>

<%@page import="simuni.clases.ln.ManejadorUsuario"%>
<%@page import="simuni.clases.ln.ManejadorDepartamento"%>
<%@page import="simuni.clases.ln.ManejadorPuesto"%>
<%@page import="simuni.clases.ln.ManejadorTipoUsuario"%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <%
        ManejadorUsuario muInfoU = new ManejadorUsuario();
        ManejadorDepartamento mdDepto = new ManejadorDepartamento();
        ManejadorTipoUsuario mtuTipoU = new ManejadorTipoUsuario();
        
        Usuario user = new Usuario();
        user = muInfoU.obtenerUsuario(request.getSession().getAttribute("USERNAME").toString());
        
        Departamento depto = new Departamento();
        depto = mdDepto.getDepartamento(user.getAreatrabajo());
        
        TipoUsuario tuTipoU = new TipoUsuario();
        tuTipoU = mtuTipoU.getTipoUsuario(user.getTipousuario());
        
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
        respuesta = (Respuesta) request.getAttribute("respuesta");
        if (user != null) {
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
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Inicio</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/css_paginas/style_pag_inicio.css">
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_usuarios.js' charset="utf-8"></script>
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>
        </ol>
    </decorator:content>                  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id="container" class="container">
            <div id="logocampo" name="logocampo" class="logocampo">
                <div id="logosys" class="logosys" name="logosys">
                    <img  src="/simuniv2/recursos/imagenes/sistema/sm_logo_simuni.png" width="80" height="80">
                </div>
                <div id="simunipa" class="simunipa">
                    <h3>SIMUNI</h3>
                </div>
            </div>
            <hr/>
            <div id="saludo" class="saludo" name="saludo">
                Bienvenido <strong><%=request.getSession().getAttribute("USERNAME")%></strong>
                <input type="hidden" id="id_usuario" name="id_usuario" class="id_usuario">
            </div>
            <div id="config" class="config" name="config">
                <a href="javascript:mostrarventanamodal();">Actualizar informaci�n</a>
            </div>
            <hr/>
            <center>
                <div id="userinfo" class="userinfo" name="userinfo">
                    <table>
                        <tr>
                            <td colspan="4">Nombre completo: <%out.print(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());%></td>
                        </tr>
                        <tr>
                            <td>C�dula: <%out.print(user.getCedula());%></td>
                            <td>Departamento: <%out.print(depto.getNombredepartamento());%></td>
                            <td>Puesto: <%out.print(user.getCodigoPuesto());%></td>
                        </tr>
                        <tr>
                            <td colspan="4">Correo electr�nico: <%out.print(user.getEmail());%></td>
                        </tr>
                    </table>
                </div>
            </center>
            <hr/>
            <center>
                <div id="modxuser" name="modxuser" class="modxuser">
                    <label>Los m�dulos para el tipo usuario:</label><small><%out.print(tuTipoU.getNombretipo());%></small>
                </div>
                <hr/>
                <div id="procesos" name="procesos" class="procesos">
                    proceso 1 proceso 2 etc...
                </div>
            </center>
        </div>
                
                
                
                
                <!-- ventana modal -->
                
        <div id="sm_body_ventanamodal">
            <form class="sm_div_formulario" method="POST" id="formulario_actualizacion">
                <fieldset id="sm_fs_articulos">
                    <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>" ><h4>Actualizaci�n de Informaci�n <small> *(Tendr� que reingresar una contrase�a para el usuario)</small></h4> </legend>
                    <div class="sm_form_registroinformacion">
                        <table class="sm_tb_campos table">
                            <tr class="sm_table_formulariofila">
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtcedulausuario">C�dula </label>
                                        <input  type="text" disabled="disabled" value="<%out.print((proceso) ? user.getCedula() : "");%>" class="form-control" name="txtcedulausuario" required="required" id="txtcedulausuario">
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtnombre">Nombre </label>
                                        <input type="text" value="<%out.print((proceso) ? user.getNombre() : "");%>" class="form-control" name="txtnombre"  id="txtnombre" placeholder="Ej. Juan">
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtapellido1">1er Apellido</label>
                                        <input type="text" value="<%out.print((proceso) ? user.getApellido1() : "");%>" class="form-control" name="txtapellido1"  id="txtapellido1" placeholder="Ej. Ram�rez">
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtapellido2">2do Apellido</label>
                                        <input type="text" value="<%out.print((proceso)? user.getApellido2() : "");%>" class="form-control" name="txtapellido2" id="txtapellido2" placeholder="Ej. Ram�rez">
                                    </div>
                                </td>                    
                            </tr> 
                            <tr class="sm_table_formulariofila">
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtemail">E-mail </label>
                                        <input type="email" value="<%out.print((proceso) ? user.getEmail(): "");%>" class="form-control" name="txtemail" id="txtemail" placeholder="Ej. ejemplo@ejemplo.com">
                                    </div>
                                </td>                    
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="cmbdepartamento">Departamento *</label>
                                        <select class="form-control" name="cmbdepartamento" required="required" id="cmbdepartamento">
                                            <%
                                                if (iterador_departamento != null && iterador_departamento.hasNext()) {
                                                    do {
                                                        Departamento deptos = iterador_departamento.next();
                                            %>
                                            <option <%out.print(((proceso) && user.getAreatrabajo() == depto.getIddepartamento()) ? "selected='selected'" : "");%> value="<%out.print(depto.getIddepartamento());%>"><%out.print(depto.getNombredepartamento());%></option>
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
                                            <option <%out.print(((proceso) && user.getCodigoPuesto()== puesto.getCodigoPuesto()) ? "selected='selected'" : "");%> value="<%out.print(puesto.getCodigoPuesto());%>"><%out.print(puesto.getNombrePuesto());%></option>
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
                                            <option <%out.print(((proceso) && user.getTipousuario()== tipousuario.getIdtipousuario()) ? "selected='selected'" : "");%> value="<%out.print(tipousuario.getIdtipousuario());%>"><%out.print(tipousuario.getNombretipo());%></option>
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
                                        <input  type="text" readonly="readonly" value="<%out.print((proceso) ? user.getNombreusuario(): "");%>" class="form-control" name="txtnombreusuario_l" required="required" id="txtnombreusuario_l" placeholder="Ej. juancito">
                                        <input type="hidden" value="<%out.print(proceso ? user.getNombreusuario(): "");%>" name="registro">

                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtpassword1">Contrase�a</label>
                                        <input type="password" value="" class="form-control" name="txtpassword1" required="required" id="txtpassword1" placeholder="********">
                                    </div>
                                </td>  
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtpassword2">Repite la Contrase�a</label>
                                        <input type="password" value="" class="form-control" name="txtpassword2" required="required" id="txtpassword2" placeholder="********">
                                    </div>
                                </td>                      
                            </tr>
                            <tr class="sm_table_formulariofila">
                                <td>&nbsp;</td>
                                <td colspan="2">
                                    <input type="submit" class="form-control" value="Actualizar la informaci�n">
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
        </div>
    </decorator:content>
</decorator:decorate>