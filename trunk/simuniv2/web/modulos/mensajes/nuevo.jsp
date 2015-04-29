<%
    request.setAttribute("permiso_requerido", 23);
%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Proveedor"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%

    ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("usuarios");
    Iterator<Usuario> iterador_usuario = null;
    try {
        if (usuarios != null) {
            iterador_usuario = usuarios.iterator();
            System.out.println("listo entree");
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Nuevo Proveedor </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_nuevo_mensaje.js' charset="utf-8"></script>

        <style>
            #sm_tb_campos td .form-group{
                margin: 15px;
            }
            #sm_tb_campos td label small{
                font-size: 0.8em;
                font-weight: 100;
            }

            #sm_tb_campos{

                margin-left: auto;
                margin-right:auto;
            }
            #formulario {
                padding: 10px;
                margin:15px;
            }
        </style>
        <script>
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/notificacion?proceso?listado">Proveedores</a></li> 
            <li class="active">Nuevo</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario" class="form" method="POST">
            <fieldset id="notificaciones">
                <legend>Enviar Mensaje <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="cmbusuarioobjetivo">Selecciona el Usuario a Enviar</label>
                                    <select required="required" class="form-control" name="cmbusuarioobjetivo" id="cmbusuarioobjetivo">
                                        <%
                                            if (iterador_usuario != null && iterador_usuario.hasNext()) {
                                                do {
                                                    Usuario usuario = iterador_usuario.next();
                                        %>
                                        <option value="<%out.print(usuario.getNombreusuario());%>"><%out.print(usuario.getNombre());%></option>
                                        <%
                                                } while (iterador_usuario.hasNext());
                                            }
                                        %>
                                    </select>
                                </div>
                            </td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>

                        </tr>
                        <tr>
                            <td colspan="4">
                                <div class="form-group">
                                    <label  class="control-label"for="cmbtiporpoveedor">Escribe tu mensaje*</label>
                                    <textarea name="txtdescripcionmensaje" required="required" class="form-control"></textarea>
                                </div>
                            </td>                             
                        </tr>
                        <tr>         
                            <td>
                                <div class="form-group">
                                    <input type="submit" value="Enviar Mensaje" class="form-control btn-info">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <input type="reset" class="form-control" value="Limpiar formulario">
                                </div>
                            </td>
                        </tr> 
                        <tr>
                            <td colspan="4">
                              <div id="area-mensajes">-</div>     
                            </td>
                        </tr>
                    </table>
                </div>
            </fieldset>
                              
        </form>
    </decorator:content>
</decorator:decorate>