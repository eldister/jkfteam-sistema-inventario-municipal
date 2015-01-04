<%@page import="simuni.entidades.ImagenActivo"%>
<%@page import="simuni.entidades.ActivoArticulo"%>
<%@page import="simuni.utils.UtilidadesServlet"%>
<%@page import="simuni.entidades.Activo"%>
<%@page import="simuni.entidades.Respuesta"%>
<%@page import="simuni.entidades.mantenimientos.TipoLlanta"%>
<%@page import="simuni.entidades.mantenimientos.TipoBateria"%>
<%@page import="simuni.entidades.mantenimientos.Estado"%>
<%@page import="simuni.entidades.mantenimientos.TipoPago"%>
<%@page import="simuni.entidades.mantenimientos.TipoActivo"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.enums.Recursos"%>
<%
    //evaluamos si viene de un prceso en curso para ver si fue correcto y si podemos guardar el estado.
    ActivoArticulo activo = null;
    ArrayList<Respuesta> respuesta = null;
    boolean error = false;
    boolean proceso = false;
    boolean eliminar_completo=false;
    
    try {
        activo = (ActivoArticulo) request.getAttribute("registro");
        respuesta = (ArrayList<Respuesta>) request.getAttribute("respuesta");
        eliminar_completo=request.getParameter("eliminar_completo")!=null;

        // 2 propiedades 3 moto 4 es el resto

        if (respuesta != null && activo != null) {
            proceso = true;
        }
        if (proceso) {
            Iterator<Respuesta> iresp = respuesta.iterator();
            if (iresp.hasNext()) {
                Respuesta r = iresp.next();
                if (r.getNivel() == 2) {
                    error = true;
                }
            }
        }
        if (activo == null) {
            activo = new ActivoArticulo();
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    //obtenemos los array list
    ArrayList<Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
    ArrayList<Estado> estados = (ArrayList<Estado>) request.getAttribute("estados");
    ArrayList<TipoActivo> tiposactivo = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");

    //tratamos de transofrmarlos en iterators
    Iterator<Departamento> iterador_departamento = null;
    Iterator<TipoPago> iterador_tipospago = null;
    Iterator<Estado> iterador_estado = null;
    Iterator<TipoActivo> iterador_tipoactivo = null;
    Iterator<ImagenActivo> iterador_imagenes = null;

    try {
        if (departamentos != null) {
            iterador_departamento = departamentos.iterator();
        }
        if (tipospago != null) {
            iterador_tipospago = tipospago.iterator();
        }
        if (estados != null) {
            iterador_estado = estados.iterator();
        }
        if (tiposactivo != null) {
            iterador_tipoactivo = tiposactivo.iterator();
        }
        if (activo != null && activo.getImagenes() != null) {
            iterador_imagenes = activo.getImagenes().iterator();
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }

    //------------------------------------------------------------------------  
    //varialbes "extras"
    String marca = "";
    String modelo = "";
    String txtdepreciacion = "";
    String txtrescate = "";

    String fechaCompra = "";
    String fechaInicioOperacion = "";

    String denominacion = "";

    int tipo_botones_requeridos = 4;
    try {

        //iniciar algunas variables para mayor facilidad
        marca = (activo != null) ? ((ActivoArticulo) activo).getMarca() : "";
        modelo = (activo != null) ? ((ActivoArticulo) activo).getModelo() : "";
        txtdepreciacion = (activo != null) ? ((ActivoArticulo) activo).getPorcentajeDepreciacion() + "" : "";
        txtrescate = (activo != null) ? ((ActivoArticulo) activo).getPorcentajeRescate() + "" : "";
        fechaCompra = (activo != null) ? (UtilidadesServlet.getFechaString(((ActivoArticulo) activo).getFechaAdquisicion())) : "";
        fechaInicioOperacion = (activo != null) ? (UtilidadesServlet.getFechaString(((ActivoArticulo) activo).getFechaInicioOperacion())) : "";
        denominacion = (activo != null) ? activo.getDenominacion() : "Colones";

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
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Modificación de Activos </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script charset="utf-8" src="<%=request.getContextPath()%>/js/script_paginas/script_eliminar_activo_articulo.js"></script>
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
            //se inicializan fechas
            inicializarValores(<%out.print("'" + fechaCompra + "','" + fechaInicioOperacion + "','" + denominacion + "','" + tipo_botones_requeridos + "'");%>);


        </script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/activo?proceso=listado">Activos</a></li>
            <li class="active">Eliminación/Baja de Activos</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form class="form"  id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/activo?proceso=eliminar_articulo" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Eliminación/Baja de Artículos</legend>
                
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <%if(!proceso||!eliminar_completo){%>
                        <tr>
                            <td><label  class="control-label"for="cmbtipoactivo">Tipo de Activo </label></td> 
                            <td>
                                <div class="form-group">
                                    <select class="form-control"  disabled="disabled" name="cmbtipoactivo" id="cmbtipoactivo">
                                        <%
                                            if (iterador_tipoactivo != null && iterador_tipoactivo.hasNext()) {
                                                do {
                                                    TipoActivo tipoactivo = iterador_tipoactivo.next();
                                        %>
                                        <option <% out.print((activo != null && activo.getCodigoTipoActivo() == tipoactivo.getIdtipoactivo()) ? "selected='selected'" : "");%> value="<%out.print(tipoactivo.getIdtipoactivo());%>"><%out.print(tipoactivo.getNombretipoactivo());%></option>
                                        <%
                                                } while (iterador_tipoactivo.hasNext());
                                            }
                                        %>
                                    </select>
                                </div>
                            </td>  
                            <td>*<small>Registro quedara Inactivo</small></td>

                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </td> 
                            <td>
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            </td>                             
                        </tr> 
                        <tr id="sm_contenedor_activo">
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                        Información de Activo
                                    </legend>
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtplacaactivo">Placa del Activo</label>
                                                    <input type="text" value="<%out.print((activo != null) ? activo.getPlacaActivo() : "");%>" class="form-control" name="txtplacaactivo"   disabled="disabled" id="txtplacaactivo" placeholder="Ej. 505550555-112qa">
                                                </div>
                                            </td>                                              
                                            <td >
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbdepartamento">Departamento *</label>
                                                    <select class="form-control"  disabled="disabled" name="cmbdepartamento" required="required" id="cmbdepartamento">
                                                        <%
                                                            if (iterador_departamento != null && iterador_departamento.hasNext()) {
                                                                do {
                                                                    Departamento depto = iterador_departamento.next();
                                                        %>
                                                        <option <%out.print((activo != null && activo.getCodigoDepto() == depto.getIddepartamento()) ? "selected='selected'" : "");%> value="<%out.print(depto.getIddepartamento());%>"><%out.print(depto.getNombredepartamento());%></option>
                                                        <%
                                                                } while (iterador_departamento.hasNext());
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>
                                            <td >
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipopago">Tipo de Pago</label>
                                                    <select class="form-control"  disabled="disabled" name="cmbtipopago" id="cmbtipopago">
                                                        <%
                                                            if (iterador_tipospago != null && iterador_tipospago.hasNext()) {
                                                                do {
                                                                    TipoPago tipopago = iterador_tipospago.next();
                                                        %>
                                                        <option <%out.print((activo != null && activo.getCodigoTipoPago() == tipopago.getIdtipopago()) ? "selected='selected'" : "");%> value="<%out.print(tipopago.getIdtipopago());%>"><%out.print(tipopago.getNombretipopago());%></option>
                                                        <%
                                                                } while (iterador_tipospago.hasNext());
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td> 
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbestadoactivo">Estado de Activo</label>
                                                    <select class="form-control"  disabled="disabled" name="cmbestadoactivo" id="cmbestadoactivo">
                                                        <%
                                                            if (iterador_estado != null && iterador_estado.hasNext()) {
                                                                do {
                                                                    Estado estado = iterador_estado.next();
                                                        %>
                                                        <option <%out.print((activo != null && activo.getCodigoEstado() == estado.getIdestado()) ? "selected='selected'" : "");%> value="<%out.print(estado.getIdestado());%>"><%out.print(estado.getNombreestado());%></option>
                                                        <%
                                                                } while (iterador_estado.hasNext());
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtfechacompra">Fecha Adquisición</label>
                                                    <input type="date" value=""  disabled="disabled" class="form-control" name="txtfechacompra" id="txtfechacompra" placeholder="12-05-2014">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtfechainicio">Fecha Inicio Operacion</label>
                                                    <input type="date" value=""  disabled="disabled" class="form-control" name="txtfechainicio"  id="txtfechainicio" placeholder="12-05-2014">
                                                </div>
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtprecio">Precio</label>
                                                    <input type="number"  disabled="disabled" value="<%out.print((activo != null) ? activo.getPrecio() : "");%>" min="0" class="form-control" name="txtprecio"   id="txtprecio" placeholder="Ej. 50000000">
                                                </div>
                                            </td>   
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbdenominacion">Denominación</label>
                                                    <select  disabled="disabled" class="form-control" name="cmbdenominacion" id="cmbdenominacion">
                                                        <option>Colones</option>
                                                        <option>Dolares</option>
                                                        <option>Euros</option>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtmarca">Marca</label>
                                                    <input  disabled="disabled" type="text" value="<%out.print(marca);%>" class="form-control" name="txtmarca" id="txtmarca" placeholder="Ej. Dell">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtmodelo">Modelo</label>
                                                    <input  disabled="disabled" type="text" value="<%out.print(modelo);%>" class="form-control" name="txtmodelo" id="txtmodelo" placeholder="N5010">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtdepreciacion">Porcentaje Depreciación</label>
                                                    <input  disabled="disabled" type="number" max="100"  value="<%out.print(txtdepreciacion);%>" min="0" class="form-control" name="txtdepreciacion" id="txtdepreciacion" placeholder="10">
                                                </div>
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtrescate">Porcentaje Rescate</label>
                                                    <input  disabled="disabled" type="number"  value="<%out.print(txtrescate);%>"  max="100" min="0" class="form-control" name="txtrescate"  id="txtrescate" placeholder="Ej. 10">
                                                </div>
                                            </td>                                              
                                        </tr>                                        
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtobservaciones">Observaciones</label>
                                                    <textarea  disabled="disabled" class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((activo != null) ? activo.getObservaciones() : "");%></textarea>
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group" id="sm_contenedor_activoarticulo">
                                                    <label  class="control-label"for="txtrescate">Proveedor</label><br>
                                                    <label id="lbl_inforproveedor"><%out.print((activo != null) ? (activo).getCodigoProveedor() + "Seleccionado" : "No seleccionado");%></label>
                                                    <input type="hidden" required="required" value="<%out.print((activo != null) ? (activo).getCodigoProveedor() : "");%>" name="hddproveedor" id="hddproveedor">
                                                </div>
                                            </td>                                              
                                        </tr>                                            
                                    </table>
                                </fieldset>                               
                            </td>                              
                        </tr>
                        <tr id="sm_contenedor_imagenes">
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                         Fotografía/s del activo
                                    </legend>
                                    <table>
                                        <tr>
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td> 
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>  
                                            <td>
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            </td>                              

                                        </tr>
                                        <tr>
                                            <td colspan="4">
                                                <div class="sm_imgcontainer" id="sm_imgcontainer">
                                                    <%
                                                        if (iterador_imagenes != null && iterador_imagenes.hasNext()) {
                                                            while (iterador_imagenes.hasNext()) {
                                                                ImagenActivo imgaux = iterador_imagenes.next();
                                                    %>  
                                                    <img class='sm_imgcontainer_item' width='300' height='300'  src='<%out.print(imgaux.getUrldocumento());%>' title='Vista Previa imagen del activo <%out.print(imgaux.getCodigoActivo());%>, Subido el <%out.print(imgaux.getFechaSubida());%>'/>
                                                    <%
                                                        }
                                                    } else {
                                                    %>
                                                    <center><strong>No tiene imagenes registradas.</strong></center>
                                                            <%
                                                                }
                                                            %>

                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>
                        </tr>
                <%}else if(proceso&&!error){%>
                <div>Solicitaste una eliminación completa del registro</div>
                <%}else{%>
                <div class="text-danger">Solicitud de eliminación completa no procesada.</div>
                <%}%>                        
                        <tr>
                            <%if (proceso) { %>
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                        Resultado de la Operación
                                    </legend>
                                    <details>
                                        <summary class="<%out.print((error && proceso) ? "text-danger" : "text-success");%>">Detalles de los resultados</summary>

                                        <table class="table table-condensed">
                                            <%if (respuesta != null) {
                                                    Iterator<Respuesta> iterador_respuestas = respuesta.iterator();
                                                    if (iterador_respuestas != null && iterador_respuestas.hasNext()) {
                                                        int numeroproceso = 1;
                                            %>
                                            <tr>
                                                <th>Numero de Proceso</th><th>Resultado</th>
                                            </tr>
                                            <%while (iterador_respuestas.hasNext()) {
                                                    Respuesta au = iterador_respuestas.next();

                                            %>

                                            <tr>
                                                <td>
                                                    <%out.print(numeroproceso++);%>
                                                </td>     
                                                <td>
                                                    <%
                                                        if (au.getNivel() == 2) {
                                                    %>
                                                    <div class="alert alert-danger" role="alert">
                                                        <span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
                                                        <span class="sr-only">Error:</span>
                                                        <%out.print(au.getMensaje());%>
                                                    </div>
                                                    <%} else {%>
                                                    <div class="alert alert-success" role="alert">
                                                        <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                                                        <span class="sr-only">Correcto:</span>
                                                        <%out.print(au.getMensaje());%>
                                                    </div>                        
                                                    <%}%>                                        

                                                </td> 
                                            </tr>
                                            <%}%>

                                            <%} else {%>
                                            <tr>
                                                <th>Proceso no devolvió nada!*</th>
                                            </tr>      
                                            <% }
                                                } else {%>
                                            <tr>
                                                <th>Proceso no devolvió nada!</th>
                                            </tr>                                       
                                            <%}%>
                                        </table>
                                    </details>
                                </fieldset>
                            </td> 
                            <%}%>
                        </tr>
                        <tr id="sm_contenedor_controles">
                            <td>&nbsp;</td>
                            <%if (!proceso) {%>  
                            <td  class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="submit" value="Eliminar Activo" class="form-control btn-info">
                                </div>
                            </td>  
                            <td  class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="checkbox" id="chk_eliminarcompleto" name="eliminar_completo">Eliminar Completamente
                                </div>
                            </td> 
                             <%}%>
                            <%if (proceso) {%>     
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Activos</button>
                                </div>
                            </td> 
                            <%}%>
                        </tr>  
                    </table>
                </div>
            </fieldset>
            <input type="hidden" id="registro" name="registro"  value="<%out.print((activo != null) ? activo.getPlacaActivo() : "");%>">
            <input type="hidden" id="registro_articulo" name="registro_articulo"  value="<%out.print((activo != null) ? activo.getCodigoActivoArticulo(): "");%>">
        </form>
        <div id="sm_body_ventanamodal">
            &nbsp;
        </div>
    </decorator:content>
</decorator:decorate>