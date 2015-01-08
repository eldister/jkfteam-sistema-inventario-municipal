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
Notificacion mensaje=(Notificacion)request.getAttribute("mensaje");
boolean proceso=mensaje!=null;
    try {
 
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Responder Mensaje </decorator:content>    
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
            <li class="active">Mensajeria</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div>
            <h3>Mensaje original <small>De <%out.print(proceso?mensaje.getUsuarioOrigen():"");%> | El <%out.print(proceso?mensaje.getFechaNotificacion():"");%></small></h3>
            <details>
                <summary>Ver mensaje</summary>
                <p class="text-primary sm_contenido_mensaje"><%out.print(proceso?mensaje.getDescripcionNotificacion():"");%></p>
            </details>
        </div>
        <form id="formulario" class="form" method="POST">
            <fieldset id="notificaciones">
                <legend>Enviar Mensaje <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="cmbusuarioobjetivo">Selecciona el Usuario a Enviar</label>
                                    <input type="text" readonly="readonly" required="required" class="form-control" value="<%out.print(proceso?mensaje.getUsuarioOrigen():"");%>" name="cmbusuarioobjetivo" id="cmbusuarioobjetivo">
                                     
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