<%@page import="simuni.entidades.Usuario"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="simuni.entidades.mantenimientos.Puesto"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.enums.Recursos"%>

<%@page import="java.sql.ResultSet"%>

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
        ManejadorPuesto mpuesto = new ManejadorPuesto();
        
        Usuario user = new Usuario();
        user = muInfoU.obtenerUsuario(request.getSession().getAttribute("USERNAME").toString());
        
        Departamento depto = new Departamento();
        depto = mdDepto.getDepartamento(user.getAreatrabajo());
        
        Puesto puesto = new Puesto();
        puesto = mpuesto.getPuesto(user.getCodigoPuesto());
        
        TipoUsuario tuTipoU = new TipoUsuario();
        tuTipoU = mtuTipoU.getTipoUsuario(user.getTipousuario());
        
        Respuesta respuesta = null;
        boolean error = false;
        boolean proceso = false;
        
        ArrayList<Puesto> alpuesto = new ArrayList<Puesto>();
        alpuesto = new ManejadorPuesto().listadoPuesto();
        Iterator<Puesto> it_puesto = alpuesto.iterator();
        
        ArrayList<Departamento> aldepto = new ArrayList<Departamento>();
        aldepto = new ManejadorDepartamento().listadoDepartamento();
        Iterator<Departamento> it_depto = aldepto.iterator();
        
        ArrayList<TipoUsuario> altipousuario = new ArrayList<TipoUsuario>();
        altipousuario = new ManejadorTipoUsuario().listadoTipoUsuarios();
        Iterator<TipoUsuario> it_tipousuario = altipousuario.iterator();
        
        try {
            respuesta = (Respuesta) request.getAttribute("respuesta");
            if (user != null) {
                proceso = true;
            }
            if (proceso &&respuesta!=null&& respuesta.getNivel() == 2) {
                error = true;
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
                <div id="config" class="config" name="config">
                    <a href="javascript:mostrarventanamodal();">Actualizar información</a>
                </div>
            </div>
            <hr/>
            <div id="saludo" class="saludo" name="saludo">
                        Bienvenido <strong><%=request.getSession().getAttribute("USERNAME")%></strong>
                        <input type="hidden" id="id_usuario" name="id_usuario" class="id_usuario">
                    </div>
            <center>
                <div id="userinfo" class="userinfo" name="userinfo">
                    <table>
                        <tr>
                            <td colspan="4">Nombre completo: <%out.print(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());%></td>
                        </tr>
                        <tr>
                            <td>Cédula: <%out.print(user.getCedula());%></td>
                            <td>Departamento: <%out.print(depto.getNombredepartamento());%></td>
                            <td>Puesto: <%out.print(puesto.getNombrePuesto());%></td>
                        </tr>
                        <tr>
                            <td colspan="4">Correo electrónico: <%out.print(user.getEmail());%></td>
                        </tr>
                    </table>
                </div>
            </center>
            <hr/>
        </div>
                
                            
                
                
                <!-- ventana modal -->
                
        <div id="sm_body_ventanamodal">
            <form class="sm_div_formulario" method="POST" id="formulario_actualizacion">
                <fieldset id="sm_fs_articulos">
                    <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>" ><h4>Actualización de Información <small> *(Tendrá que reingresar una contraseña para el usuario)</small></h4> </legend>
                    <div class="sm_form_registroinformacion">
                        <table class="sm_tb_campos table">
                            <tr class="sm_table_formulariofila">
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtcedulausuario">Cédula </label>
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
                                        <input type="text" value="<%out.print((proceso) ? user.getApellido1() : "");%>" class="form-control" name="txtapellido1"  id="txtapellido1" placeholder="Ej. Ramírez">
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <label  class="control-label"for="txtapellido2">2do Apellido</label>
                                        <input type="text" value="<%out.print((proceso)? user.getApellido2() : "");%>" class="form-control" name="txtapellido2" id="txtapellido2" placeholder="Ej. Ramírez">
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
                                                while(it_depto.hasNext()){
                                                    Departamento deptos = it_depto.next();
                                            %>
                                            <option value="<%out.print(deptos.getIddepartamento());%>"><%out.print(deptos.getNombredepartamento());%></option>
                                            <%
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
                                                while(it_puesto.hasNext()){
                                                    Puesto puestos = it_puesto.next();
                                            %>
                                            <option value="<%out.print(puestos.getCodigoPuesto());%>"><%out.print(puestos.getNombrePuesto());%></option>
                                            <%
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
                                                while(it_tipousuario.hasNext()){
                                                    TipoUsuario tipousuario = it_tipousuario.next();
                                            %>
                                            <option value="<%out.print(tipousuario.getIdtipousuario());%>"><%out.print(tipousuario.getNombretipo());%></option>
                                            <%
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
                                    <input type="submit" class="form-control" value="Actualizar">
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
                                                <style>
                                                    #sm_body_ventanamodal{
                                                        z-index: 1001;
                                                    }
                                                </style>
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