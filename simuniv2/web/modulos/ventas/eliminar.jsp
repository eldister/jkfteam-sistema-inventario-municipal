<%
    request.setAttribute("permiso_requerido", 51);
%>
<%@page import="simuni.entidades.mantenimientos.TipoPago"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.entidades.Venta"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluamos si viene de un prceso en curso para ver si fue correcto y si podemos guardar el estado.

    Venta venta = null;
    Respuesta respuesta = null;
    ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
    Iterator<TipoPago> iterador_tipospago = null;
    boolean error = false;
    boolean proceso = false;
    int tipo_botones_requeridos = 1;
    try {
        venta = (Venta) request.getAttribute("registro");
        respuesta = (Respuesta) request.getAttribute("respuesta");

        if (respuesta != null) {
            proceso = true;
        }
        if (proceso) {
            if (respuesta.getNivel() == 2) {
                error = true;
            }

        }
        if (venta == null) {
            venta = new Venta();
        }
        if (proceso && error) {
            tipo_botones_requeridos = 2;
        } else if (proceso && !error) {
            tipo_botones_requeridos = 3;
        }
        if (tipospago != null) {
            iterador_tipospago = tipospago.iterator();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
%>

<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Activos </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_eliminar_venta.js" charset="utf-8"></script>

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
            <li class="active">Activos***</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>

        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/venta?proceso=eliminar" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Registro de activos <small></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr id="sm_contenedor_activo">
                            <td colspan="5">
                                <table>
                                    <tr>
                                        <td>
                                            <div class="form-group" id="sm_contenedor_activoarticulo">
                                                <label  class="control-label"for="txtrescate">Activo Seleccionado</label><br>
                                                <label id="lbl_inforactivo"><%out.print((venta != null) ? ((Venta) venta).getPlacaActivo() : "");%></label>
                                                <input type="hidden" required="required" value="<%out.print((venta != null) ? (venta).getPlacaActivo() : "");%>" name="hddactivo" id="hddactivo">
                                                <input type="hidden" id="sm_hidden_codigoventa" name="registro" value="<%out.print((venta != null) ? venta.getCodigoVenta() : "");%>">                                           
                                            </div>
                                        </td>                                             
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label" for="txtfechaventa">Fecha de la Venta</label>
                                                <input disabled="disabled" type="date" value="<%out.print((venta != null) ? (venta).getFechaVenta() : "");%>" required="required" class="form-control" name="txtfechaventa" id="txtfechaventa" placeholder="12-05-2014">
                                            </div>
                                        </td>  
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="txtfactura">Consecutivo de Factura</label>
                                                <input disabled="disabled"  type="text" required="required" value="<%out.print((venta != null) ? (venta).getCodigoFactura() : "");%>" class="form-control" name="txtfactura" id="txtfactura" placeholder="00001">
                                            </div>
                                        </td>                                            
                                    </tr>
                                    <tr> 
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="txtnombrecomprador">Comprador</label>
                                                <input disabled="disabled"  type="text" required="required" value="<%out.print((venta != null) ? (venta).getNombreComprador() : "");%>" class="form-control" name="txtnombrecomprador" id="txtnombrecomprador" placeholder="00001">
                                            </div>
                                        </td>                                              
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label" for="txtmontoventa">Monto de la Venta <small>*en colones</small></label>
                                                <input disabled="disabled"  type="number" value="<%out.print((venta != null) ? (venta).getMontoVenta() : "");%>" required="required" class="form-control" name="txtmontoventa" id="txtmontoventa" placeholder="5000000">
                                            </div>
                                        </td>  
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="cmbestadoventa">Estado de la Venta</label>
                                                <select disabled="disabled"  class="form-control" required="required" name="cmbestadoventa">
                                                    <option <%out.print((venta != null && venta.getEstadoVenta().equals("Activa")) ? "selected='selected'" : "");%>>Activa</option>
                                                    <option <%out.print((venta != null && venta.getEstadoVenta().equals("Inactiva")) ? "selected='selected'" : "");%>>Inactiva</option>
                                                </select>
                                            </div>
                                        </td>                                            
                                    </tr>                                        
                                    <tr>
                                        <td>
                                            <div class="form-group">
                                                <label  class="control-label"for="cmbtipopago">Tipo de Venta</label>
                                                <select disabled="disabled"  class="form-control" required="required" name="cmbtipopago" id="cmbtipopago">
                                                    <%
                                                        if (iterador_tipospago != null && iterador_tipospago.hasNext()) {
                                                            do {
                                                                TipoPago tipopago = iterador_tipospago.next();
                                                    %>
                                                    <option <%out.print((venta.getCodigoTipoPago() == tipopago.getIdtipopago()) ? "selected='selected'" : "");%> value="<%out.print(tipopago.getIdtipopago());%>"><%out.print(tipopago.getNombretipopago());%></option>
                                                    <%
                                                            } while (iterador_tipospago.hasNext());
                                                        }
                                                    %>
                                                </select>
                                            </div>
                                        </td>                                            
                                        <td colspan="2">
                                            <div class="form-group">
                                                <label  class="control-label"for="txtdireccioncomprador">Dirección del Comprador</label>
                                                <textarea disabled="disabled"  class="form-control" name="txtdireccioncomprador" id="txtdireccioncomprador" placeholder="Carmona, Nandayure"><%out.print((venta != null) ? venta.getDireccionComprador() : "");%></textarea>
                                            </div>
                                        </td>                                               
                                    </tr> 
                                    <tr>
                                        <td colspan="3">
                                            <div class="form-group">
                                                <label  class="control-label"for="txtobservaciones">Observaciones</label>
                                                <textarea disabled="disabled"  class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((venta != null) ? venta.getObservaciones() : "");%></textarea>
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
                                    <input type="submit" value="Eliminar Registro Venta" class="form-control btn-danger">
                                </div>
                            </td>
                            <td  class="btn_controles_sinprocesocontainer">
                            </td>  
                            <%if (proceso) {%>     
                            <td id="btn_controles_procesocorrecto">
                            </td>
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Ventas</button>
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