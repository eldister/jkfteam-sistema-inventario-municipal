<%
    request.setAttribute("permiso_requerido", 9);
%>
<%@page import="simuni.entidades.Baja"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluamos si viene de un prceso en curso para ver si fue correcto y si podemos guardar el estado.

    Baja baja = null;
    Respuesta respuesta = null;

    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos=1;
    try {
        baja = (Baja) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");

        if (respuesta != null && baja != null) {
            proceso = true;
        }
        if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }
            
        }
        if (baja == null) {
            baja = new Baja();
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
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Bajas - Nuevo </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_nuevo_baja.js" charset="utf-8"></script>

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
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/baja?proceso=listado">Listado de Bajas</a></li> 
            <li class="active">Dar de Baja</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>

        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/baja?proceso=nuevo" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Registro de Bajas de Activos  <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr id="sm_contenedor_activo">
                            <td colspan="5">
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="form-group" id="sm_contenedor_activoarticulo">
                                                    <label  class="control-label"for="txtrescate">Seleccionar Activo *</label><br>
                                                    <button class="btn  btn-primary glyphicon glyphicon-search" id="btnseleccionaractivo"> Seleccionar</button><br>
                                                    <label id="lbl_inforactivo"><%out.print((baja != null) ? ((Baja) baja).getPlacaActivo() + "Seleccionado" : "No seleccionado");%></label>
                                                    <input type="hidden" required="required" value="<%out.print((baja != null) ? (baja).getPlacaActivo() : "");%>" name="hddactivo" id="hddactivo">
                                                </div>
                                            </td>                                             
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label" for="txtfechabaja">Fecha Baja</label>
                                                    <input type="date" value="<%out.print((baja != null) ? (baja).getFechaBaja(): "");%>" required="required" class="form-control" name="txtfechabaja" id="txtfechabaja" placeholder="12-05-2014">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtdocumentorespaldo">Documento de Respaldo</label>
                                                    <input type="text" required="required" value="<%out.print((baja != null) ? (baja).getCodigoDocumentoRespaldo(): "");%>" class="form-control" name="txtdocumentorespaldo" id="txtdocumentorespaldo" placeholder="00001">
                                                </div>
                                            </td>                                            
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtrazonbaja">Razón de la Baja</label>
                                                    <textarea class="form-control" name="txtrazonbaja" id="txtrazonbaja" placeholder="Ej. Daño Irreparable"><%out.print((baja != null) ? baja.getRazonBaja(): "");%></textarea>
                                                </div>
                                            </td>                                               
                                        </tr> 
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtobservaciones">Observaciones</label>
                                                    <textarea class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((baja != null) ? baja.getObservaciones() : "");%></textarea>
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
                                    <input type="submit" value="Registrar Baja" class="form-control btn-info">
                                </div>
                            </td>
                            <td  class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="reset" class="form-control" value="Limpiar formulario">
                                </div>
                            </td>  
                            <%if (proceso) {%>     
                            <td id="btn_controles_procesocorrecto">
                                <input type="hidden" id="sm_hidden_codigobaja" value="<%out.print((baja != null) ? baja.getCodigoBaja() : "");%>">
                                <div class="form-group">
                                    <button id="sm_btn_reporte" class="form-control btn-success">Generar Reporte Baja</button>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Bajas</button>
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