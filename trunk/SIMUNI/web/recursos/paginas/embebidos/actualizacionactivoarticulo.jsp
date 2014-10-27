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
<script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/jquery-registroactivos.js"></script>


<script>
    $(document).ready(function(){
          setEventoSeleccionarProveedor_b();
          $("#cmbtipopago").change(function(){
              $("#hiddenidTipoPago").val($(this).val());
              
          });
          
    });
    

    function setEventoSeleccionarProveedor_b() {
        $("#txtbtnseleccionarproveedor").click(function() {

            seleccionarProveedor_b();

        });
    }

    function mostrarventanamodal_b() {
        $("#sm_body_ventanamodal_b").show('slow');
        $("#sm_body_ventanamodal_b").dialog({
            modal: true,
            width: "70%",
            position: {my: "left-10 top", at: "left top", of: sm_formembebido_actualizacionarticulos},
            maxWidth: "768px"});


    }

    function seleccionarProveedor_b() {

        $.ajax({
            url: "/SIMUNI/modulos/proveedores?proceso=obtenerregistroproveedor",
            cache: false
        })
                .done(function(html) {
                    $("#sm_body_ventanamodal_b").html(html);
                    mostrarventanamodal_b();
                    setManejadorSeleccionProveedor();
                });


    }


    function setManejadorSeleccionProveedor() {
        $(".sm_popup_identificadorproveedor").click(function() {
            $("input[name=hiddenidProveedor]").val($(this).text());
            $("input[name=txtProveedor]").val($(this).text());


            $("#txtProveedor").val($(this).text());
            // alert(   $("input[name=hiddenidProveedor]").val())
            $("#sm_body_ventanamodal_b").dialog('close');

        });
    }


</script>
<div id="sm_formembebido_actualizacionarticulos">
    <form id="sm_div_formulario" method="POST" action="/SIMUNI/modulos/activos?proceso=modificacionarticulo" enctype="multipart/form-data">
        <fieldset id="sm_fs_articulos">
            <legend>Modificación de Activo</legend>
            <h1>Activo <%out.print(activo.getPa_identificadorActivo());%></h1>
            <div id="sm_form_registroinformacion">
                <table id="sm_tb_campos">
                    <tr>
                        <td>
                            <label>Número de placa </label>
                        </td>
                        <td>
                            <span><% out.print(activo.getPa_identificadorActivo()); %></span>
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
                            <label>Categoría </label>
                        </td>
                        <td>
                            <select name="cmbCategoria">
                                <%
                                    if (tiposactivos != null) {
                                        Iterator<TipoActivo> iter = tiposactivos.iterator();
                                        while (iter.hasNext()) {
                                            TipoActivo tactivo = iter.next();
                                            out.print("<option ");
                                            out.print(activo.getPa_tipoActivo() == tactivo.getCodigoTipoActivo() ? " selected='selected' " : "");
                                            out.print("value='");
                                            out.print(tactivo.getCodigoTipoActivo());
                                            out.print("'>");
                                            out.print(tactivo.getNombreTipoActivo());
                                            out.print("</option>");
                                        }
                                    }
                                %>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Fecha inicio operación </label>
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
                            <input type="text" value="<% out.print(activo.getPa_codigoProveedor()); %>"  name="txtProveedor">
                            <input type="button" id="txtbtnseleccionarproveedor" value="Seleccionar">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Departamento destino </label>
                        </td>
                        <td>
                            <!--<input type="text" name="txtDescripción">-->
                            <select name="cmbDepartamento">                            
                                <%
                                    if (deptos != null) {
                                        Iterator<Departamento> iter = deptos.iterator();
                                        while (iter.hasNext()) {
                                            Departamento depto = iter.next();
                                            out.print("<option ");
                                            out.print(activo.getPo_depto().getPn_codigo() == depto.getPn_codigo() ? " selected" : "");
                                            out.print(" value='");
                                            out.print(depto.getPn_codigo());
                                            out.print("'>");
                                            out.print(depto.getPa_nombre());
                                            out.print("</option>");
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
                            <input type="number" value="<% out.print(activo.getPd_precioCompra()); %>" name="txtPrecioCompra">
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Porcentaje Depreciación </label>
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
                            <select name="cmbTipoPago" id="cmbtipopago">
                                <%
                                    if (tipospago != null) {
                                        Iterator<TipoPago> iter = tipospago.iterator();
                                        while (iter.hasNext()) {
                                            TipoPago tpago = iter.next();
                                            out.print("<option ");
                                            out.print(activo.getPa_tipoPago() == tpago.getCodigoTipoPago() ? " selected='selected' " : "");
                                            out.print(" value='");
                                            out.print(tpago.getCodigoTipoPago());
                                            out.print("'>");
                                            out.print(tpago.getNombreTipoPago());
                                            out.print("</option>");
                                        }
                                    }
                                %>                                    
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label>Estado Actual </label>
                        </td>
                        <td>
                            <select name="cmbEstadoActivo">
                                <%
                                    if (tiposestadoactivo != null) {
                                        Iterator<EstadoActivo> iter = tiposestadoactivo.iterator();
                                        while (iter.hasNext()) {
                                            EstadoActivo testadoactivo = iter.next();
                                            out.print("<option ");
                                            out.print(activo.getPa_Estado() == testadoactivo.getNombreEstado() ? " selected" : "");
                                            out.print(" value='");
                                            out.print(testadoactivo.getNombreEstado());
                                            out.print("'>");
                                            out.print(testadoactivo.getNombreEstado());
                                            out.print("</option>");
                                        }
                                    }
                                %>                                    
                            </select>
                        </td>
                    </tr>                        
                    <tr>
                        <td>
                            <label>Descripción </label>
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
                            <textarea type="text" name="txtObservaciones"  placeholder="Indique aspectos generales de los activos que considere necesario" maxlength="300"><%out.print(activo.getPa_Observaciones());%></textarea>
                        </td>
                    </tr>                        
                    <tr>
                        <td colspan="2">
                            <input type="submit" id="btnRegistrar" value="Actualizar artículo" >
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

                                <%
                                    String urlimagen = "";
                                    ArrayList<imagenActivo> img = activo.getPo_imagenActivo();
                                    if (img != null) {
                                        imagenActivo imagen = img.get(0);
                                        urlimagen = imagen.getPa_url() + "/" + imagen.getPa_nombreArchivo();
                                    } else {
                                        out.print("noup" + img);
                                    }
                                %>
                                <img id="imgImagenCargada" src="<% out.print(urlimagen);%>" height="218" width="218" alt="Fotografía de activo">
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
            <input type="hidden"  value="<% out.print(activo.getPa_tipoPago()); %>" id="hiddenidTipoPago" name="hiddenidTipoPago">
            <input type="hidden"  value="<% out.print(activo.getPo_depto().getPn_codigo()); %>" name="hiddenidDepartamento">
            <input type='hidden'  value="<% out.print(activo.getPa_identificadorActivo());%>" name="txtNumeroPlaca">
        </div>
    </form>
    <div id='sm_body_ventanamodal_b'>&nbsp;</div>
</div>