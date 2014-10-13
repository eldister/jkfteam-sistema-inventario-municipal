<%@page import="simuni.classes.EN.EstadoActivo"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.classes.EN.TipoPago"%>
<%@page import="simuni.classes.EN.TipoActivo"%>
<%@page import="simuni.classes.EN.Departamento"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
     <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de activos</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodearticulos.css">
            <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/jquery-registroactivos.js"></script>
        </decorator:content>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de activos</decorator:content>    
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de activos</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodearticulos.css">
        <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/jquery-registroactivos.js"></script>
        <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/script_plugins/maxLength/maxLength.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/script_plugins/webShim/minified/polyfiller.js"></script>
        <script>
            webshims.setOptions('waitReady', false);
            webshims.setOptions('forms-ext', {types: 'date'});
            webshims.polyfill('forms forms-ext');
        </script>
        <script>
            $(function() {
                $("#txtDes").maxLength(300, {showNumber: "#contadorCaracteres1"});
            });
            $(function() {
                $("#txtOb").maxLength(300, {showNumber: "#contadorCaracteres2"});
            });
            $(document).ready(function() {
                var intervalo = function() {
                    $("#btnImagenActivo").html($("#btnImagenActivo").val());
                };
                $("#btnsubirImagen").on("click", function() {
                    $("#btnImagenActivo").click();
                    setInterval(intervalo, 1);
                    return false;
                });
                addEventosACamposDeTexto();
            });
        </script>
        <script type="text/javascript">
            
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <%
            ArrayList<Departamento> deptos = (ArrayList<Departamento>) request.getAttribute("departamentos");
            ArrayList<TipoActivo> tiposactivos = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");
            ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
            ArrayList<EstadoActivo> tiposestadoactivo = (ArrayList<EstadoActivo>) request.getAttribute("estadoactivos");
        %>
        <form id="sm_div_formulario" method="POST" action="/SIMUNI/modulos/activos?proceso=registroactivoarticulo" enctype="multipart/form-data">
            <fieldset id="sm_fs_articulos">
                <legend>Registro de activos</legend>
                <div id="sm_form_registroinformacion">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <label>Número de placa </label>
                            </td>
                            <td>
                                <input type="text" id="txtnumplaca" required="required" oninvalid="this.setCustomValidity('Este campo es requerido')" name="txtNumeroPlaca">
                                <span id="txtnumplacainfo" class="lblinfocontainer">&nbsp;</span>
                            </td>
                        </tr>    
                        <tr>
                            <td>
                                <label>Modelo </label>
                            </td>
                            <td>
                                <input type="text" name="txtModelo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Marca </label>
                            </td>
                            <td>
                                <input type="text" name="txtMarca">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Categoría </label>
                            </td>
                            <td>
                                <select name="cmbCategoria">
                                    <option value="-1">-- Seleccionar --</option>
                                    <%
                                        if (tiposactivos != null) {
                                            Iterator<TipoActivo> iter = tiposactivos.iterator();
                                            while (iter.hasNext()) {
                                                TipoActivo tactivo = iter.next();
                                                out.print("option value'");
                                                out.print(tactivo.getCodigoTipoActivo());
                                                out.print("'>");
                                                out.print(tactivo.getNombreTipoActivo());
                                                out.print("</option");
                                            }
                                        }
                                    %>
                                    <!--    <option>Edificios</option>
                                        <option>Multimedia</option>
                                        <option>Computación</option>
                                        <option>Impresión</option>
                                        <option>Telefonía</option>
                                        <option>Máquinaria</option>
                                        <option>Máquinaria pesada</option>
                                        <option>Oficina</option>
                                        <option>Recolección de basura</option>
                                        <option>Refrigeración</option>
                                        <option>Transporte</option>
                                        <option>Herramientas de trabajo</option>
                                        <option>Muebles</option>
                                        <option>Otros (Sin clasificación)</option>-->
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Fecha inicio operación </label>
                            </td>
                            <td>
                                <input type="date" name="dpPuestaOperacion" id="dpPO" value="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del proveedor </label>
                            </td>
                            <td>
                                <input type="text" name="txtProveedor"  placeholder="Nombre completo">&nbsp;&nbsp;<input type="button" value="Seleccionar">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Departamento destino </label>
                            </td>
                            <td>
                                <!--<input type="text" name="txtDescripción">-->
                                <select name="cmbDepartamento">
                                    <option value="-1">-- Seleccionar --</option>                                
                                    <%
                                        if (deptos != null) {
                                            Iterator<Departamento> iter = deptos.iterator();
                                            while (iter.hasNext()) {
                                                Departamento depto = iter.next();
                                                out.print("option value'");
                                                out.print(depto.getPn_codigo());
                                                out.print("'>");
                                                out.print(depto.getPa_nombre());
                                                out.print("</option");
                                            }
                                        }
                                    %>  
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Fecha de compra </label>
                            </td>
                            <td>
                                <input type="date" name="dpFechaCompra" id="dpFechaCompra">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Precio de compra </label>
                            </td>
                            <td>
                                <input type="text" name="txtPrecioCompra">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Porcentaje Depreciación </label>
                            </td>
                            <td>
                                <input type="text" name="txtPorcentajeDepreciacion">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Porcentaje Rescate</label>
                            </td>
                            <td>
                                <input type="text" name="txtPorcentajeRescate">
                            </td>
                        </tr>                        
                        <tr>
                            <td>
                                <label>Tipo de pago </label>
                            </td>
                            <td>
                                <select name="cmbTipoPago">
                                    <option value="-1">-- Seleccionar --</option>
                                    <%
                                        if (tipospago != null) {
                                            Iterator<TipoPago> iter = tipospago.iterator();
                                            while (iter.hasNext()) {
                                                TipoPago tpago = iter.next();
                                                out.print("option value'");
                                                out.print(tpago.getCodigoTipoPago());
                                                out.print("'>");
                                                out.print(tpago.getNombreTipoPago());
                                                out.print("</option");
                                            }
                                        }
                                    %>                                    

                                    <!-- <option>Efectivo</option>
                                     <option>Crédito</option>
                                     <option>Debito</option>
                                     <option >Cheque</option>
                                     <option>Fiado</option>-->
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Estado Actual </label>
                            </td>
                            <td>
                                <select name="cmbEstadoActivo">
                                    <option value="-1">-- Seleccionar --</option>
                                    <%
                                        if (tiposestadoactivo != null) {
                                            Iterator<EstadoActivo> iter = tiposestadoactivo.iterator();
                                            while (iter.hasNext()) {
                                                EstadoActivo testadoactivo = iter.next();
                                                out.print("option value'");
                                                out.print(testadoactivo.getNombreEstado());
                                                out.print("'>");
                                                out.print(testadoactivo.getNombreEstado());
                                                out.print("</option");
                                            }
                                        }
                                    %>                                    
                                    <!--
                                    <option>Excelente</option>
                                    <option>Bueno</option>
                                    <option>Regular</option>
                                    <option>Malo</option>
                                    <option>Irreparable</option>
                                    <option>Alquilado</option>
                                    <option>Depreciado</option>
                                    <option>Desechado</option>
                                    <option>Donado</option>
                                    <option>Préstado</option>
                                    <option>En reparación</option>
                                    <option>Robado</option>-->

                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Descripción </label>
                            </td>
                            <td>
                                <textarea type="text" name="txtDescripcion" maxlength="300" id="txtDes" placeholder="Escriba una breve descripción sobre las características que considere importantes del artículo"></textarea>
                                <br/>
                                <label id="LetrasRestantes1">Letras restantes: <div id="contadorCaracteres1"></div></label>
                            </td>
                        </tr>                  
                        <tr>
                            <td>
                                <label>Observaciones </label>
                            </td>
                            <td>
                                <textarea type="text" id="txtOb" name="txtObservaciones" placeholder="Indique aspectos generales de los activos que considere necesario" maxlength="300"></textarea>
                                <br/>
                                <label id="LetrasRestantes2">Letras restantes: <div id="contadorCaracteres2"></div></label>
                            </td>
                        </tr>                        
                        <tr>
                            <td>
                                <input type="submit" id="btnRegistrar" value="Registrar activo">
                            </td>
                            <td>
                                <input type="reset" id="btnLimpiarFormulario" value="Limpiar formulario">
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="sm_div_registerImage">
                    <fieldset id="sm_fs_imagen">
                        <legend>Fotografía</legend>
                        <table id="sm_tb_imagen">
                            <tr>
                                <td>
                                    <img id="imgImagenCargada" src="#" alt="Fotografía de activo">
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="file" id="btnImagenActivo" required="required" name="fileimagenactivo" accept="image/*" onchange="fnMostrarImagen(this);">
                                    <input type="button" id="btnsubirImagen" value="Examinar">
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
            </fieldset>
            <div id="sm_form_extracontent">
                <input type="hidden" value="1" name="hiddenidProveedor">
                <input type="hidden" value="1" name="hiddenidCategoria">
                <input type="hidden" value="1" name="hiddenidTipoPago">
                <input type="hidden" value="1" name="hiddenidDepartamento">

            </div>
        </form>
    </decorator:content>
</decorator:decorate>