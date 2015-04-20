<%@page import="simuni.entidades.Reparacion"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluacion de los objetos para conocer si provienen de algún proceso
    Reparacion reparacion = null;
    Respuesta respuesta = null;
    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos = 1;
    try {
        reparacion = (Reparacion) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");

        if (respuesta != null) {
            proceso = true;
        }
        if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }

        }
        if (reparacion == null) {
            reparacion = new Reparacion();
        }
        if (proceso && error) {
            tipo_botones_requeridos = 2;
        } else if (proceso && !error) {
            tipo_botones_requeridos = 3;
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Actualizar reparación </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_editar_reparacion.js" charset="utf-8"></script>

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
            <li class="active">Actualizar reparación</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>

        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/reparacion?proceso=actualizar" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Información de la reparación <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr id="sm_contenedor_activo">
                            <td colspan="5">
                                <table>
                                    <tr>
                                        <td>
                                            <div class="form-group" id="sm_contenedor_activoarticulo">
                                                <label  class="control-label"for="txtrescate">Activo Seleccionado</label><br>
                                                <label id="lbl_inforactivo"><%out.print((reparacion != null) ? ((Reparacion) reparacion).getPlacaActivo() : "");%></label>
                                                <input type="hidden" required="required" value="<%out.print((reparacion != null) ? (reparacion).getPlacaActivo() : "");%>" name="hddactivo" id="hddactivo">
                                                <input type="hidden" id="sm_hidden_codigoreparacion" name="hddreparacion" value="<%out.print((reparacion != null) ? reparacion.getCodigoReparacion() : "");%>">                                           
                                            </div>
                                        </td>                                             
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label" for="txtfechareparacion">Fecha</label>
                                                <input type="date" value="<%out.print((reparacion != null) ? (reparacion).getFechaR() : "");%>" required="required" class="form-control" name="txtfechareparacion" id="txtfechareparacion" placeholder="12-05-2014">
                                            </div>
                                        </td>                                             
                                    </tr>
                                    <tr> 
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="txtnombrereparador">Reparador</label>
                                                <input type="text" required="required" value="<%out.print((reparacion != null) ? (reparacion).getnombreReparador(): "");%>" class="form-control" name="txtnombrereparador" id="txtnombrereparador" placeholder="00001">
                                            </div>
                                        </td>                                              
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label" for="txtmontoreparacion">Monto <small>*en colones</small></label>
                                                <input type="number" value="<%out.print((reparacion != null) ? (reparacion).getCostoReparacion() : "");%>" required="required" class="form-control" name="txtmontoreparacion" id="txtmontoreparacion" placeholder="5000000">
                                            </div>
                                        </td>  
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="cmbestadoventa">Estado del activo</label>
                                                <select class="form-control" required="required" name="cmbestadoreparacion">
                                                    <option>Activa</option>
                                                    <option>Inactiva</option>
                                                </select>
                                            </div>
                                        </td>                                            
                                    </tr>                                        
                                    <tr>                                           
                                        <td colspan="2">
                                            <div class="form-group">
                                                <label  class="control-label"for="txtmotivos">Motivos de la reparación</label>
                                                <textarea class="form-control" name="txtmotivos" id="txtmotivos" placeholder="Carmona, Nandayure"><%out.print((reparacion != null) ? reparacion.getMotivoReparacion() : "");%></textarea>
                                            </div>
                                        </td>                                               
                                    </tr> 
                                    <tr>
                                        <td colspan="3">
                                            <div class="form-group">
                                                <label  class="control-label"for="txtobservaciones">Observaciones</label>
                                                <textarea class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((reparacion != null) ? reparacion.getObservacion() : "");%></textarea>
                                            </div>
                                        </td>                                               
                                    </tr>                                            
                                </table>                          
                            </td>                              
                        </tr>
                        <!--Información de el equipo de transporte-->
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
                        <tr id="sm_contenedor_controles">
                            <td class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="submit" value="Actualizar Registro Reparación" class="form-control btn-info">
                                </div>
                            </td>
                            <td  class="btn_controles_sinprocesocontainer">
                            </td>  
                            <%if (proceso) {%>     
                            <td id="btn_controles_procesocorrecto">
                                <div class="form-group">
                                    <button id="sm_btn_reporte" class="form-control btn-success">Generar Reporte Reparación</button>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Reparación</button>
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
            inicializarValores(<%out.print("'" + tipo_botones_requeridos + "'");%>);
            
            
        </script>                        
    </decorator:content>
</decorator:decorate>