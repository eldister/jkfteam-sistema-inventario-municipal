<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
     <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de activos</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodearticulos.css">
        </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id="staticMenu">
            <a>Inicio</a> / <a>Activos</a> / <a>Gestión de activos</a> / <a>Registro de activos</a>
        </div>
        <br/>
        <div id="sm_div_informacionproceso">
            <p>
                Este es el formulario para el ingreso o registro de nuevos activos a la base de datos 
                de la municipalidad a tráves del sistema de inventario <b>SIMUNI</b>.<br/>
                Seleccione el tipo de activo a registrar y se le desplegará el formulario que debe llenar.
            </p>
        </div>
        <form id="sm_div_formulario" action="">
            <fieldset id="sm_fs_articulos">
                <legend>Registro de activos</legend>
                <div id="sm_form_registroinformacion">
                    <table>
                        <tr>
                            <td>
                                <label>Tipo de activos </label>
                            </td>
                            <td>
                                <select name="cmbTipoActivos">
                                    <option>-- Seleccionar --</option>
                                    <option>Transporte</option>
                                    <option>Tecnológico</option>
                                    <option>Artículo</option>
                                </select>
                            </td>
                            <td>
                                <input type="button" value="Desplegar formulario" id="btnDesplegar" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Número placa de activo: </label>
                            </td>
                            <td>
                                <input type="text" name="txtNumeroPlaca">
                            </td>
                        </tr>    
                        <tr>
                            <td>
                                <label>Descripción del artículo: </label>
                            </td>
                            <td>
                                <input type="text" name="txtDescripción">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Modelo del artículo: </label>
                            </td>
                            <td>
                                <input type="text" name="txtModelo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Marca del artículo: </label>
                            </td>
                            <td>
                                <input type="text" name="txtMarca">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Estado del artículo: </label>
                            </td>
                            <td>
                                <select name="cmbEstadoActivo">
                                    <option>-- Seleccionar --</option>
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
                                    <option>Robado</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Categoría del activo </label>
                            </td>
                            <td>
                                <select name="cmbCategoria">
                                    <option>-- Seleccionar --</option>
                                    <option>Edificios</option>
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
                                    <option>Otros (Sin clasificación)</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Puesta en operación: </label>
                            </td>
                            <td>
                                <input type="date" name="dpPuestaOperacion">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del proveedor: </label>
                            </td>
                            <td>
                                <input type="text" name="txtProveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Departamento destino: </label>
                            </td>
                            <td>
                                <input type="text" name="txtDescripción">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Descripción del artículo: </label>
                            </td>
                            <td>
                                <input type="text" name="txtDescripción">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Fecha de compra: </label>
                            </td>
                            <td>
                                <input type="date" name="dpFechaCompra">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Precio de compra: </label>
                            </td>
                            <td>
                                <input type="text" name="txtPrecioCompra">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Tipo de pago: </label>
                            </td>
                            <td>
                                <select name="cmbTipoPago">
                                    <option>-- Seleccionar --</option>
                                    <option>Efectivo</option>
                                    <option>Crédito</option>
                                    <option>Debito</option>
                                    <option>Cheque</option>
                                    <option>Fiado</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" id="btnRegistrar" value="Registrar activo">
                            </td>
                            <td>
                                <input type="button" id="btnLimpiarFormulario" value="Limpiar formulario">
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="sm_div_registerImage">
                    <fieldset id="sm_fs_imagen">
                        <legend>Foto del activo</legend>
                        <img name="imgImagenCargada" src="#" alt="Fotografía de activo"><br/>
                        <input type="file" id="btnImagenActivo" accept="image/*" onchange="fnMostrarImagen(this);">
                    </fieldset>
                </div>
            </fieldset>
        </form>
    </decorator:content>

</decorator:decorate>