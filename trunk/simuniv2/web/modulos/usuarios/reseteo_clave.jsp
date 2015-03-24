<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.enums.Recursos"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="simuni.clases.ui.MostradorUsuario"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>

<%
    Respuesta respuesta = null;
    Iterator<Usuario> iter_usuarios = null;
    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos = 1;
    String idusuario = (String) request.getAttribute("idusuario");
    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");

    if (usuarios != null) {
        iter_usuarios = usuarios.iterator();
    }
    respuesta = (Respuesta) request.getAttribute("respuesta");
    if (respuesta != null) {
        proceso = true;
    }
    if (proceso) {
        if (respuesta.getNivel() == 2) {
            error = true;
        }

    }

    if (proceso && error) {
        tipo_botones_requeridos = 2;
    } else if (proceso && !error) {
        tipo_botones_requeridos = 3;
    }

%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Mantenimientos - Reseteo de Clave</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>        
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_reseteo_clave.js' charset="utf-8"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active"><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/usuario">Usuarios</a></li>
            <li class="active">Reseteo de Clave</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div class="container-fluid">
            <div class=" container">
                <form  method="POST"  id="formulario_mantenimiento_reseteo_clave" class="form-horizonal" >
                    <fieldset>
                        <legend>
                            Formulario de Reseteo de Clave General
                        </legend>
                        <div class="form-group row">
                            <div class="col-xs-11 col-sm-5 col-md-6 col-lg-5 col-sm-offset-0 col-xs-offset-1 ">
                                <div class="form-group">
                                    <label class="control-label"  for="inputSuccess2">Seleccionar Usuario</label>
                                    <select id="cmbusuario" class="form-control"  name="txtnombreusuario_l">
                                        <%
                                            while (iter_usuarios != null && iter_usuarios.hasNext()) {
                                                Usuario user = iter_usuarios.next();
                                                String selected = user.getNombreusuario().equals(idusuario) ? "selected='selected'" : "nada";
                                                out.print("<option value=\'" + user.getNombreusuario() + "\'" + selected + ">" + user.getNombre() + "</option>");

                                            }
                                        %>
                                    </select>
                                    <span class="notifier" aria-hidden="true"></span>
                                    <span id="inputSuccess2Status" class="sr-only">(success)</span>
                                </div>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-xs-11 ">
                                <div id="registerInformation">
                                    <table id="sm_tb_campos" style="width: 100%;">
                                        <tr id="sm_contenedor_activo">
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label" for="txtpassword1">Nueva Contraseña*</label>
                                                    <input type="password" value="" required="required" class="form-control" name="txtpassword1" id="txtpassword1" placeholder="********">
                                                </div>
                                            </td> 

                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label" for="txtpassword2">Reingrese Contraseña*</label>
                                                    <input type="password" value="" required="required" class="form-control" name="txtpassword2" id="txtpassword2" placeholder="********">
                                                </div>
                                            </td>                                          
                                        </tr>                      

                                        <tr>
                                            <%if (proceso) { %>
                                            <td colspan="5">
                                                <fieldset >
                                                    <legend>
                                                        Resultado de la Operación
                                                    </legend>
                                                    <%if (respuesta != null) { %>

                                                    <%
                                                        if (respuesta.getNivel() == 2) {
                                                    %>
                                                    <div class="alert alert-danger" role="alert">
                                                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                                        <span class="sr-only">Error:</span>
                                                        <%out.print(respuesta.getMensaje());%>
                                                    </div>
                                                    <%} else {%>
                                                    <div class="alert alert-success" role="alert">
                                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                                        <span class="sr-only">Correcto:</span>
                                                        <%out.print(respuesta.getMensaje());%>
                                                    </div>                        
                                                    <%}%>                                        
                                                    <%} else {%>
                                                    <div>Proceso no devolvió nada!*</div>

                                                    <% }%>
                                                    <%}%>  
                                                </fieldset>
                                            </td> 
                                        </tr>
                                        <td>&nbsp;</td>
                                        <tr id="sm_contenedor_controles">
                                            <%if (!proceso || !error) {%>     
                                            <td>&nbsp;</td>
                                            <%}%>    
                                            <td class="btn_controles_sinprocesocontainer">
                                                <div class="form-group">
                                                    <input type="submit" value="Resetear Clave" class="form-control btn-info">
                                                </div>
                                            </td> 
                                            <%if (proceso) {%>     

                                            <td>
                                                <div class="form-group">
                                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Página de Inicio</button>
                                                </div>
                                            </td> 
                                            <td id="btn_controles_procesoerror">
                                                <div class="form-group">
                                                    <button id="sm_btn_reintentar" class="form-control btn-danger">Reintentar</button>
                                                </div>
                                            </td>  
                                            <%}%>
                                        </tr>  
                                    </table>
                                </div>                               
                            </div>			
                        </div>
                    </fieldset>
                </form>
            </div>
            <style>
                #cmbpermisosdisponibles ,#cmbpermisosasignados{
                    height: 300px;
                }

            </style>
        </div>	
        <script>
//se inicializan fechas
            inicializarValores(<%out.print("'" + tipo_botones_requeridos + "'");%>);


        </script>                                          
    </decorator:content>
</decorator:decorate>