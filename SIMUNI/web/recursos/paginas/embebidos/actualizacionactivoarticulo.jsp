<%@page import="simuni.classes.EN.imagenActivo"%>
<%@page import="simuni.classes.EN.Activos_Articulos"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.classes.EN.EstadoActivo"%>
<%@page import="simuni.classes.EN.EstadoActivo"%>
<%@page import="simuni.classes.EN.TipoPago"%>
<%@page import="simuni.classes.EN.Departamento"%>
<%@page import="java.util.Iterator"%>
<%@page import="simuni.classes.EN.TipoActivo"%>
<%

    ArrayList<Departamento> deptos = (ArrayList<Departamento>) request.getAttribute("departamentos");
    ArrayList<TipoActivo> tiposactivos = (ArrayList<TipoActivo>) request.getAttribute("tiposactivo");
    ArrayList<TipoPago> tipospago = (ArrayList<TipoPago>) request.getAttribute("tipospago");
    ArrayList<EstadoActivo> tiposestadoactivo = (ArrayList<EstadoActivo>) request.getAttribute("estadoactivos");
    Activos_Articulos activo = (Activos_Articulos) request.getAttribute("articulo");
    if (activo == null) {
        activo = new Activos_Articulos();
    }
%>
<div id="sm_formembebido_actualizacionarticulos">
    <form id="sm_div_formulario" method="POST" action="/SIMUNI/modulos/activos?proceso=modificacionarticulo" enctype="multipart/form-data">
        <fieldset id="sm_fs_articulos">
            <legend>Modificaci�n de Activo</legend>
            <h1>Activo <%out.print(activo.getPa_identificadorActivo());%></h1>
            <div id="sm_form_registroinformacion">
                <table id="sm_tb_campos">
                    <!-- <tr>
                         <td>
                             <label>Tipo del activo </label>
                         </td>
                         <td>
                             <select name="cmbTipoActivos">
                                 <option>-- Seleccionar --</option>
                                 <option>Transporte</option>
                                 <option>Tecnol�gico</option>
                                 <option>Muebles</option>
                             </select>
                         </td>
                     </tr>-->
                    <tr>
                        <td>
                            <label>N�mero de placa </label>
                        </td>
                        <td>
                            <span><% out.print(activo.getPa_identificadorActivo()); %></span>
                            <!--<input type="text" required="required" value="" oninvalid="this.setCustomValidity('Este campo es requerido')" name="txtNumeroPlaca">-->
                        </td>
                    </tr>    
                    <tr>
                        <td>
                            <label>Modelo </label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPa_modelo()); %>" name="txtModelo">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Marca </label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPa_marca()); %>" name="txtMarca">                       
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Categor�a </label>
                        </td>
                        <td>
                            <select name="cmbCategoria">
                                <option value="-1">-- Seleccionar --</option>
                                <%
                                    if (tiposactivos != null) {
                                        Iterator<TipoActivo> iter = tiposactivos.iterator();
                                        while (iter.hasNext()) {
                                            TipoActivo tactivo = iter.next();
                                            out.print("option ");
                                            out.print(activo.getPa_tipoActivo() == tactivo.getCodigoTipoActivo() ? " selected" : "");
                                            out.print("value'");
                                            out.print(tactivo.getCodigoTipoActivo());
                                            out.print("'>");
                                            out.print(tactivo.getNombreTipoActivo());
                                            out.print("</option");
                                        }
                                    }
                                %>
                                <!--    <option>Edificios</option>
                                    <option>Multimedia</option>
                                    <option>Computaci�n</option>
                                    <option>Impresi�n</option>
                                    <option>Telefon�a</option>
                                    <option>M�quinaria</option>
                                    <option>M�quinaria pesada</option>
                                    <option>Oficina</option>
                                    <option>Recolecci�n de basura</option>
                                    <option>Refrigeraci�n</option>
                                    <option>Transporte</option>
                                    <option>Herramientas de trabajo</option>
                                    <option>Muebles</option>
                                    <option>Otros (Sin clasificaci�n)</option>-->
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Fecha inicio operaci�n </label>
                        </td>
                        <td>
                            <input type="date" value="<% out.print(new java.sql.Date(activo.getPd_puestaOperacion().getTime())); %>"  name="dpPuestaOperacion">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Nombre del proveedor </label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPa_nombreproveedor()); %>"  name="txtProveedor"><input type="button" value="Seleccionar">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Departamento destino </label>
                        </td>
                        <td>
                            <!--<input type="text" name="txtDescripci�n">-->
                            <select name="cmbDepartamento">
                                <option value="-1">-- Seleccionar --</option>                                
                                <%
                                    if (deptos != null) {
                                        Iterator<Departamento> iter = deptos.iterator();
                                        while (iter.hasNext()) {
                                            Departamento depto = iter.next();
                                            out.print("option ");
                                            out.print(activo.getPo_depto().getPn_codigo() == depto.getPn_codigo() ? " selected" : "");
                                            out.print(" value'");
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
                            <input type="date" value="<% out.print(new java.sql.Date(activo.getPd_fechaCompra().getTime())); %>" name="dpFechaCompra">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Precio de compra </label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPd_precioCompra()); %>" name="txtPrecioCompra">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Porcentaje Depreciaci�n </label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPb_porcentajeDepreciacion()); %>" name="txtPorcentajeDepreciacion">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Porcentaje Rescate</label>
                        </td>
                        <td>
                            <input type="text" value="<% out.print(activo.getPb_porcentajeRescate()); %>" name="txtPorcentajeRescate">
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
                                            out.print("option ");
                                            out.print(activo.getPa_tipoPago() == tpago.getCodigoTipoPago() ? " selected" : "");
                                            out.print(" value'");
                                            out.print(tpago.getCodigoTipoPago());
                                            out.print("'>");
                                            out.print(tpago.getNombreTipoPago());
                                            out.print("</option");
                                        }
                                    }
                                %>                                    

                                <!-- <option>Efectivo</option>
                                 <option>Cr�dito</option>
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
                                            out.print("option ");
                                            out.print(activo.getPa_Estado() == testadoactivo.getNombreEstado() ? " selected" : "");
                                            out.print(" value'");
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
                                <option>Pr�stado</option>
                                <option>En reparaci�n</option>
                                <option>Robado</option>-->

                            </select>
                        </td>
                    </tr>                        
                    <tr>
                        <td>
                            <label>Descripci�n </label>
                        </td>
                        <td>
                            <textarea type="text" name="txtDescripcion"  maxlength="300"><%out.print(activo.getPa_Descripcion()); %></textarea>
                        </td>
                    </tr>                  
                    <tr>
                        <td>
                            <label>Observaciones </label>
                        </td>
                        <td>
                            <textarea type="text" name="txtObservaciones"  placeholder="Indique aspectos generales de los activos que considere necesario" maxlength="300">
                                <%out.print(activo.getPa_Observaciones());%>
                            </textarea>
                        </td>
                    </tr>                        
                    <tr>
                        <td colspan="2">
                            <input type="submit" id="btnRegistrar" value="Actualizar art�culo" >
                        </td>
                    </tr>
                </table>
            </div>
            <div id="sm_div_registerImage">
                <fieldset id="sm_fs_imagen">
                    <legend>Fotograf�a</legend>
                    <table id="sm_tb_imagen">
                        <tr>
                            <td>

                                <%
                                    String urlimagen = "";
                                    ArrayList<imagenActivo> img = activo.getPo_imagenActivo();
                                    if (img != null) {
                                        imagenActivo imagen = img.get(0);
                                        urlimagen = imagen.getPa_url() + "/" + imagen.getPa_nombreArchivo();
                                    }
                                    else{
                                        out.print("noup"+img);
                                    }
                                %>
                                <img id="imgImagenCargada" src="<% out.print(urlimagen);%>" height="218" width="218" alt="Fotograf�a de activo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="file" title="Selecciona la imagen" id="btnImagenActivo" name="fileimagenactivo" value="Examinar" accept="image/*" onchange="fnMostrarImagen(this);">
                            </td>
                        </tr>
                    </table>
                </fieldset>
            </div>
        </fieldset>
        <div id="sm_form_extracontent">
            <input type="hidden"  value="<% out.print(activo.getPa_codigoProveedor()); %>" name="hiddenidProveedor">
            <input type="hidden"  value="<% out.print(activo.getPa_tipoActivo()); %>"name="hiddenidCategoria">
            <input type="hidden"  value="<% out.print(activo.getPa_tipoPago()); %>"name="hiddenidTipoPago">
            <input type="hidden"  value="<% out.print(activo.getPo_depto().getPn_codigo()); %>" name="hiddenidDepartamento">
            <input type='hidden'  value="<% out.print(activo.getPa_identificadorActivo()); %>" name="txtNumeroPlaca">
        </div>
    </form>
</div>