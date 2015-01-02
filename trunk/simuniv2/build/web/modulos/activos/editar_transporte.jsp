<%@page import="simuni.entidades.ImagenActivo"%>
<%@page import="simuni.entidades.ActivoTransporte"%>
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
    ActivoTransporte activo = null;
    ArrayList<Respuesta> respuesta = null;

    
    boolean error = false;
    boolean proceso = false;

    try {
        activo = (ActivoTransporte) request.getAttribute("registro");
        respuesta = (ArrayList<Respuesta>) request.getAttribute("respuesta");

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
    } catch (Exception ex) {
        ex.printStackTrace();
    }

    //obtenemos los array list
    ArrayList<Departamento> departamentos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
    ArrayList<Estado> estados = (ArrayList<Estado>) request.getAttribute("estados");
    ArrayList<TipoActivo> tiposactivo = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");
    ArrayList<TipoBateria> tiposbateria = (ArrayList<TipoBateria>) request.getAttribute("tiposbateria");
    ArrayList<TipoLlanta> tiposllanta = (ArrayList<TipoLlanta>) request.getAttribute("tiposllanta");

    //tratamos de transofrmarlos en iterators
    Iterator<Departamento> iterador_departamento = null;
    Iterator<TipoPago> iterador_tipospago = null;
    Iterator<Estado> iterador_estado = null;
    Iterator<TipoActivo> iterador_tipoactivo = null;
    Iterator<TipoBateria> iterador_tipobateria = null;
    Iterator<TipoLlanta> iterador_tipollanta = null;
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
        if (tiposbateria != null) {
            iterador_tipobateria = tiposbateria.iterator();
        }
        if (tiposllanta != null) {
            iterador_tipollanta = tiposllanta.iterator();
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
    int tipollanta_delantera = 0;
    int tipollanta_trasera = 0;
    int tipollanta_delanterade = 0;
    int tipollanta_delanteraiz = 0;
    int tipollanta_traserade = 0;
    int tipollanta_traseraiz = 0;
    String fechaCompra = "";
    String fechaInicioOperacion = "";
    String fechaFabricacion = "";
    String denominacion = "";
    String tipoVehiculo = "A";
    String consecutivoVehiculo = "";
    int tipo_botones_requeridos = 4;
    try {

        //iniciar algunas variables para mayor facilidad
        marca = (activo != null) ? ((ActivoTransporte) activo).getMarca() : "";
        modelo = (activo != null) ? ((ActivoTransporte) activo).getModelo() : "";
        txtdepreciacion = (activo != null) ? ((ActivoTransporte) activo).getPorcentajeDepreciacion() + "" : "";
        txtrescate = (activo != null) ? ((ActivoTransporte) activo).getPorcentajeRescate() + "" : "";

        fechaCompra = (activo != null) ? (UtilidadesServlet.getFechaString(((ActivoTransporte) activo).getFechaAdquisicion())) : "";
        fechaInicioOperacion = (activo != null) ? (UtilidadesServlet.getFechaString(((ActivoTransporte) activo).getFechaInicio())) : "";
        denominacion = (activo != null) ? activo.getDenominacion() : "Colones";

    //para los tipos de llantas en caso de que sea un vehiculo o moto
        //moto
        tipollanta_delantera = UtilidadesServlet.getInt(request.getParameter("cmbtipollantad"), 0);
        tipollanta_trasera = UtilidadesServlet.getInt(request.getParameter("cmbtipollantat"), 0);
        //vehiculo
        tipollanta_delanterade = UtilidadesServlet.getInt(request.getParameter("cmbtipollantadd"), 0);
        tipollanta_delanteraiz = UtilidadesServlet.getInt(request.getParameter("cmbtipollantadi"), 0);
        tipollanta_traserade = UtilidadesServlet.getInt(request.getParameter("cmbtipollantatd"), 0);
        tipollanta_traseraiz = UtilidadesServlet.getInt(request.getParameter("cmbtipollantati"), 0);

        //inicializar las fecha de frabricacion
        fechaFabricacion = UtilidadesServlet.getFechaString(((ActivoTransporte) activo).getAnioFabrica());
        tipoVehiculo = (activo).getTipoVehiculo()!=null?activo.getTipoVehiculo().charAt(0)+"":('A'+"");
        consecutivoVehiculo = request.getAttribute("consecutivovehiculo") != null ? request.getAttribute("consecutivovehiculo").toString() : "";
        System.out.println(consecutivoVehiculo);

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
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Activos </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src="<%=request.getContextPath()%>/js/script_paginas/script_editar_activo_transporte.js" charset="utf-8"></script>

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
        <script>
            //se inicializan fechas
            inicializarValores(<%out.print("'" + fechaCompra + "','" + fechaInicioOperacion + "','" + fechaFabricacion + "','" + denominacion + "','" + tipoVehiculo + "','" + tipo_botones_requeridos + "'");%>);


        </script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/activo?proceso=listado">Activos</a></li>
            <li class="active">Modificación de Activos</li>
        </ol>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>

        <form class="form" enctype="multipart/form-data" id="formulario" action="<%out.print(Recursos.Servers.MAINSERVER);%>/activo?proceso=actualizar_transporte" method="POST">
            <fieldset id="activos">
                <legend style="<%out.print((error && proceso) ? "color:red;" : "");%>">Registro de activos <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td><label  class="control-label"for="cmbtipoactivo">Seleccione tipo de Activo</label></td> 
                            <td>
                                <div class="form-group">
                                    <select class="form-control" name="cmbtipoactivo" id="cmbtipoactivo">
                                        <%
                                            if (iterador_tipoactivo != null && iterador_tipoactivo.hasNext()) {
                                                do {
                                                    TipoActivo tipoactivo = iterador_tipoactivo.next();
                                        %>
                                        <option <%out.print((activo != null && activo.getCodigoTipoActivo() == tipoactivo.getIdtipoactivo()) ? "selected='selected'" : "");%> value="<%out.print(tipoactivo.getIdtipoactivo());%>"><%out.print(tipoactivo.getNombretipoactivo());%></option>
                                        <%
                                                } while (iterador_tipoactivo.hasNext());
                                            }
                                        %>
                                    </select>
                                </div>
                            </td>  
                            <td>*<small> Cambiar el tipo, no variará el formulario </small></td>

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
                                                    <input type="text" readonly="readonly" value="<%out.print((activo != null) ? activo.getPlacaActivo() : "");%>" class="form-control" name="txtplacaactivo" required="required" id="txtplacaactivo" placeholder="Ej. 505550555-112qa">
                                                </div>
                                            </td>                                              
                                            <td >
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbdepartamento">Departamento *</label>
                                                    <select class="form-control" name="cmbdepartamento" required="required" id="cmbdepartamento">
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
                                                    <select class="form-control" name="cmbtipopago" id="cmbtipopago">
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
                                                    <select class="form-control" name="cmbestadoactivo" id="cmbestadoactivo">
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
                                                    <input type="date" value="" class="form-control" name="txtfechacompra" id="txtfechacompra" placeholder="12-05-2014">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtfechainicio">Fecha Inicio Operacion</label>
                                                    <input type="date" value="" class="form-control" name="txtfechainicio"  id="txtfechainicio" placeholder="12-05-2014">
                                                </div>
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtprecio">Precio</label>
                                                    <input type="number" value="<%out.print((activo != null) ? activo.getPrecio() : "");%>" min="0" class="form-control" name="txtprecio"   id="txtprecio" placeholder="Ej. 50000000">
                                                </div>
                                            </td>   
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbdenominacion">Denominación</label>
                                                    <select class="form-control" name="cmbdenominacion" id="cmbdenominacion">
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
                                                    <input type="text" value="<%out.print(marca);%>" class="form-control" name="txtmarca" id="txtmarca" placeholder="Ej. Dell">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtmodelo">Modelo</label>
                                                    <input type="text" value="<%out.print(modelo);%>" class="form-control" name="txtmodelo" id="txtmodelo" placeholder="N5010">
                                                </div>
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtdepreciacion">Porcentaje Depreciación</label>
                                                    <input type="number" max="100"  value="<%out.print(txtdepreciacion);%>" min="0" class="form-control" name="txtdepreciacion" id="txtdepreciacion" placeholder="10">
                                                </div>
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtrescate">Porcentaje Rescate</label>
                                                    <input type="number"  value="<%out.print(txtrescate);%>"  max="100" min="0" class="form-control" name="txtrescate"  id="txtrescate" placeholder="Ej. 10">
                                                </div>
                                            </td>                                              
                                        </tr>                                        
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtobservaciones">Observaciones</label>
                                                    <textarea class="form-control" name="txtobservaciones" id="txtobservaciones" placeholder="Ej. Activo de Segunda"><%out.print((activo != null) ? activo.getObservaciones() : "");%></textarea>
                                                </div>
                                            </td>                                              
                                        </tr>                                            
                                    </table>
                                </fieldset>                               
                            </td>                              
                        </tr>
                        <!--Información de el equipo de transporte-->
                        <tr id="sm_contenedor_transporte">
                            <td colspan="5">
                                <fieldset >
                                    <legend>
                                        Información detallada del Equipo de Transporte
                                    </legend>
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtplacavehiculo">Placa del Vehículo *</label>
                                                    <input  value="<%out.print((activo != null) ? ((ActivoTransporte) activo).getPlaca() : "");%>" type="text"  class="form-control" name="txtplacavehiculo" readonly="readonly" required="required" id="txtplacavehiculo" placeholder="Ej. 505550555-112qa">
                                                </div>
                                            </td>                                              
                                            <td >
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipovehiculo">Tipo de Vehículo</label>
                                                    <select class="form-control"  name="cmbtipovehiculo" id="cmbtipovehiculo">
                                                        <option value="A">Carro</option>
                                                        <option value="B">Moto</option>
                                                        <option value="C">Maquinaria</option>
                                                    </select>
                                                </div> 
                                            </td>
                                            <td >
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtnumerochasis">Número de Chasis</label>
                                                    <input type="text" readonly="readonly"  value="<%out.print((activo != null) ? ((ActivoTransporte) activo).getNumeroChasis() : "");%>" class="form-control" name="txtnumerochasis" required="required" id="txtnumerochasis" placeholder="11122545444">
                                                </div>
                                            </td> 
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtnumeromotor">Número de Motor</label>
                                                    <input type="text"  value="<%out.print((activo != null) ? ((ActivoTransporte) activo).getNumeroMotor() : "");%>" class="form-control" name="txtnumeromotor"  id="txtnumeromotor" placeholder="123486">
                                                </div> 
                                            </td>                                              
                                        </tr>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtfechafabricacion">Fecha Fabricación</label>
                                                    <input type="date" value="" class="form-control" name="txtfechafabricacion" id="txtfechafabricacion" placeholder="12-05-2014">
                                                </div>
                                            </td>                                                
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtcilindros">Cilindros</label>
                                                    <input type="number" value="<%out.print((activo != null) ? ((ActivoTransporte) activo).getCilindros() : "");%>" min="0" class="form-control" name="txtcilindros"  id="txtcilindros" placeholder="Ej. 12">
                                                </div>
                                            </td>   
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipobateria">Tipo de Baterías</label>
                                                    <select class="form-control" name="cmbtipobateria" id="cmbtipobateria">
                                                        <%
                                                            if (iterador_tipobateria != null && iterador_tipobateria.hasNext()) {
                                                                do {
                                                                    TipoBateria tipobateria = iterador_tipobateria.next();
                                                        %>
                                                        <option value="<%out.print(tipobateria.getIdtipobateria());%>"><%out.print(tipobateria.getNombretipobateria());%></option>
                                                        <%
                                                                } while (iterador_tipoactivo.hasNext());
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtconsecutivovehiculo">Consecutivo</label>
                                                    <input type="text" min="0" required="required" readonly="readonly" value="<%out.print((activo!=null)?activo.getTipoVehiculo():"");%>" min="1" class="form-control" name="txtconsecutivovehiculo"  id="txtconsecutivovehiculo">
                                                </div>
                                            </td>
                                        </tr>
                                        <tr id="tipollantas_vehiculo">
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantadd">Tipo de Llanta Delantera Derecha</label>
                                                    <select class="form-control" name="cmbtipollantadd" id="cmbtipollantadd">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option <%out.print(tipollanta_delanterade == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantadi">Tipo de Llanta Delantera Izquierda</label>
                                                    <select class="form-control" name="cmbtipollantadi" id="cmbtipollantadi">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option  <%out.print(tipollanta_delanteraiz == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>  
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantatd">Tipo de Llanta Trasera Derecha</label>
                                                    <select class="form-control" name="cmbtipollantatd" id="cmbtipollantatd">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option <%out.print(tipollanta_traserade == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantati">Tipo de Llanta Trasera Izquierda</label>
                                                    <select class="form-control" name="cmbtipollantati" id="cmbtipollantati">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option  <%out.print(tipollanta_traseraiz == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                        </tr>  
                                        <tr id="tipollantas_moto">
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantad">Tipo de Llanta Delantera </label>
                                                    <select class="form-control" name="cmbtipollantad" id="cmbtipollantad">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option  <%out.print(tipollanta_delantera == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="cmbtipollantat">Tipo de Llanta Trasera </label>
                                                    <select class="form-control" name="cmbtipollantat" id="cmbtipollantat">
                                                        <%
                                                            if (iterador_tipollanta != null && iterador_tipollanta.hasNext()) {
                                                                do {
                                                                    TipoLlanta tipollanta = iterador_tipollanta.next();
                                                        %>
                                                        <option  <%out.print(tipollanta_trasera == tipollanta.getIdtipollanta() ? "selected='selected'" : "");%> value="<%out.print(tipollanta.getIdtipollanta());%>"><%out.print(tipollanta.getNombretipollanta());%></option>
                                                        <%
                                                                } while (iterador_tipollanta.hasNext());
                                                            }
                                                            if (tiposllanta != null) {
                                                                iterador_tipollanta = tiposllanta.iterator();
                                                            }
                                                        %>
                                                    </select>
                                                </div> 
                                            </td>                                              
                                        </tr>
                                        <tr>
                                            <td colspan="3">
                                                <div class="form-group">
                                                    <label  class="control-label"for="txtobservacionestecnicas">Observaciones Técnicas Extras</label>
                                                    <textarea class="form-control" name="txtobservacionestecnicas" id="txtobservacionestecnicas" placeholder="Ej. Utiliza Diesel"><%out.print((activo != null) ? ((ActivoTransporte) activo).getObservacionesTecnicas() : "");%></textarea>
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
                                        Fotografías del activo
                                    </legend>
                                    <table>
                                        <tr>
                                            <td>
                                                <div class="form-group">
                                                    <label  class="control-label"for="filearchivos">Seleccionar</label>
                                                    <input type="file" class="form-control" multiple="multiple" name="filearchivos" id="filearchivos">
                                                </div>                                                 
                                            </td>
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
                                                    <img class='sm_imgcontainer_item' width='200' height='200'  src='<%out.print(imgaux.getUrldocumento());%>' title='Vista Previa imagen del activo <%out.print(imgaux.getCodigoActivo());%>, Subido el <%out.print(imgaux.getFechaSubida());%>'/>
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
                            <td class="btn_controles_sinprocesocontainer">
                                <div class="form-group">
                                    <input type="submit" value="Actualizar Activo" class="form-control btn-info">
                                </div>
                            </td> 
                            <%if (proceso) {//falta campo hidden con el codigo activo%>     
                            <td id="btn_controles_procesocorrecto">
                                <input type="hidden" id="sm_hidden_codigoactivo" value="<%out.print(( activo != null) ? activo.getPlacaActivo() : "");%>">
                                <div class="form-group">
                                    <button id="sm_btn_reporte" class="form-control btn-success">Generar Reporte Activo</button>
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <button id="sm_btn_iractivos" class="form-control btn-info">Ir a  Activos</button>
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
            <input type="hidden" id="registro" name="registro"  value="<%out.print((activo != null) ? activo.getPlacaActivo() : "");%>">
            <input type="hidden" id="registro_transporte" name="registro_transporte"  value="<%out.print((activo != null) ?activo.getCodigoActivoTransporte() : "");%>">
                               
        </form>

        <div id="sm_body_ventanamodal">
            &nbsp;
        </div>
    </decorator:content>
</decorator:decorate>