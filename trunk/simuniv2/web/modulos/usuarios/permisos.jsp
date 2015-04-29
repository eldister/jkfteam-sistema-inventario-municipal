<%
    request.setAttribute("permiso_requerido", 47);
%>
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
    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
    Iterator<Usuario> iter_usuarios = null;
    if (usuarios != null) {
        iter_usuarios = usuarios.iterator();
    }
    String idusuario = "";
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Mantenimientos - Gestión de Permisos </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>        
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_permisos_usuario.js' charset="utf-8"></script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active"><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/usuario">Usuarios</a></li>
            <li class="active">Gestión de Permisos</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div class="container" style="width: 1100px;">
            <form  method="POST"  id="formulario_mantenimiento_asignacionpermisos" class="form-horizonal" >
                <fieldset>
                    <legend>
                        Formulario de Asignacion de Permisos
                    </legend>
                    <div class="form-group row">
                        <div class="col-xs-11 col-sm-5 col-md-6 col-lg-5 col-sm-offset-0 col-xs-offset-1 ">
                            <div class="form-group">
                                <label class="control-label"  for="inputSuccess2">Seleccionar Usuario</label>
                                <select id="cmbusuario" class="form-control"  name="cmbusuario">
                                    <%
                                        while (iter_usuarios != null && iter_usuarios.hasNext()) {
                                            Usuario user = iter_usuarios.next();
                                            String selected = user.getNombreusuario() == idusuario ? "selected='selected'" : "";
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
                        <div class="col-xs-11 col-sm-5 col-md-6 col-lg-5 col-sm-offset-0 col-xs-offset-1 ">
                            <div class="form-group">
                                <label class="control-label"  for="cmbpermisosdisponibles">Permisos Disponibles</label>
                                <select multiple="multiple" id="cmbpermisosdisponibles" class="form-control"  name="cmbpermisosdisponibles">
                                </select>
                                <span class="notifier" aria-hidden="true"></span>
                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                            </div>
                        </div>
                        <div class="col-xs-11 col-sm-1 col-md-1 col-lg-1 col-sm-offset-0 col-xs-offset-1 ">
                            <div class="row">&nbsp;</div>
                            <div class="row">
                                <div class="col-xs-11  col-sm-offset-7 col-xs-offset-1 ">
                                    <button  class="btn btn-primary" id="btnasignar">></button>
                                </div>
                            </div>
                            <div class="row">&nbsp;</div>
                            <div class="row">
                                <div class="col-xs-11 col-sm-offset-7 col-xs-offset-1  ">
                                    <button class="btn btn-primary" id="btndesasignar">&lt;</button>
                                </div>
                            </div>								
                        </div>
                        <div class="col-xs-11 col-sm-5 col-md-6 col-lg-5 col-sm-offset-1 col-xs-offset-1 ">
                            <div class="form-group">
                                <label class="control-label"  for="cmbpermisosasignados">Permisos Asignados</label>
                                <select multiple="multiple" id="cmbpermisosasignados" class="form-control"  name="cmbpermisoasignados[]">
                                </select>
                                <span class="notifier" aria-hidden="true"></span>
                                <span id="inputSuccess2Status" class="sr-only">(success)</span>
                            </div>
                        </div>							
                    </div>
                    <div class="form-group row">
                        <div class="col-xs-11 col-sm-4 col-md-4 col-lg-4 col-lg-offset-4 col-xs-offset-1  col-sm-offset-3 col-md-offset-3">
                            <input type="submit" class="form-control btn-primary" value="Guardar AsignaciÃ³n" />
                        </div>
                    </div>
                    <div class="row">

                        <div class="col-xs-11 col-sm-6 col-md-5 col-lg-5 col-lg-offset-4 col-xs-offset-1  col-md-offset-4  col-sm-offset-4 ">
                            <div id="respuesta">
                                &nbsp;
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
    </decorator:content>
</decorator:decorate>