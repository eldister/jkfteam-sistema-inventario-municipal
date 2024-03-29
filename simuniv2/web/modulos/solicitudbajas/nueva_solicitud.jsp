<%@page import="simuni.entidades.SolicitudBaja"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluamos si viene de un prceso en curso para ver si fue correcto y si podemos guardar el estado.

    SolicitudBaja solicitudbaja = null;
    Respuesta respuesta = null;

    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos=1;
    try {
        solicitudbaja = (SolicitudBaja) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");

        if (respuesta != null && solicitudbaja != null) {
            proceso = true;
        }
        if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }
            
        }
        if (solicitudbaja == null) {
            solicitudbaja = new SolicitudBaja();
        }
        if(proceso&&error){
            tipo_botones_requeridos=2;
        }
        else if(proceso&&!error){
            tipo_botones_requeridos=3;
        }    
        System.out.println("listo no se que es"+tipo_botones_requeridos);
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Tus solicitudes de Baja - Nuevo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
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

            /*imagenes*/
            .sm_imgcontainer_item{
                display: inline-block;
                padding: 5px;
                box-shadow: 0 0 5px rgba(0,0,0,0.5);
            }
            .sm_imgcontainer{
                text-align: center;

            }
        </style>

    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>  
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/solicitudbaja?proceso=listado">Tus Solicitudes de Baja</a></li> 
            <li class="active">Nueva Solicitud</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_nuevo_solicitud_baja.js" charset="utf-8"></script>

        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/solicitudbaja?proceso=nuevo" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Registro de Solicitud de Baja <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr id="sm_contenedor_activo">
                            <td colspan="5">
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label" for="txtplacaactivo">Placa del Activo *</label>
                                                    <input type="text" value="<%out.print((solicitudbaja != null) ? (solicitudbaja).getPlacaActivo(): "");%>" required="required" class="form-control" name="txtplacaactivo" id="txtplacaactivo" placeholder="000000">
                                                </div>
                                            </td>                                                                                        
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtrazonbaja">Raz�n de la Solicitud de Baja *</label>
                                                    <textarea class="form-control" name="txtrazonbaja" id="txtrazonbaja" placeholder="Ej. Da�o Irreparable"><%out.print((solicitudbaja != null) ? solicitudbaja.getMotivo(): "");%></textarea>
                                                </div>
                                            </td>                                               
                                        </tr> 
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtobservaciones">Observaciones *</label>
                                                    <textarea class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((solicitudbaja != null) ? solicitudbaja.getObservaciones() : "");%></textarea>
                                                </div>
                                            </td>                                               
                                        </tr>                                            
                                    </table>                          
                            </td>                              
                        </tr>
                        <!--Informaci�n de el equipo de transporte-->
                        <tr>
                            <%if (proceso) { %>
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                        Resultado de la Operaci�n
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
                                    <div>Proceso no devolvi� nada!*</div>

                                    <% }%>
                                    <%}%>  
                                </fieldset>
                            </td> 
                        </tr>
                        <tr id="sm_contenedor_controles">
                            <td class="btn_controles_sinprocesocontainer" colspan="2">
                                <div class="form-group">
                                    <input type="submit" value="Enviar Solicitud" class="form-control btn-info">
                                </div>
                            </td>
                            <td  class="btn_controles_sinprocesocontainer" colspan="2">
                                <div class="form-group">
                                    <input type="reset" class="form-control" value="Limpiar formulario">
                                </div>
                            </td>  
                            <%if (proceso) {%>     
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Solicitudes de Bajas</button>
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
            </fieldset>
        </form>

        <div id="sm_body_ventanamodal">
            &nbsp;
        </div>
        <script>
            //se inicializan fechas
            inicializarValores(<%out.print("'"+tipo_botones_requeridos+"'");%>);
            

        </script>                        
    </decorator:content>
</decorator:decorate>