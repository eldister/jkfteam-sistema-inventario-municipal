<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Registro de proveedores</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
             <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodeproveedores.css">
             <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/script_plugins/maxLength/maxLength.js"></script>
             <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_validaciones.js"></script>
             <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_registroproveedor.js"></script>
             <script>
            $(function(){
                $("#txtDC").maxLength(300, { showNumber: "#contCaracteres"});
             });
             $(document).ready(function(){
                 var intervalo = function(){
                     $("#btnArchivo").html($("#btnSubirArchivo").val());  
                 };
                     $("#btnSubirArchivo").on("click", function(){
                         $("#btnArchivo").click();
                         setInterval(intervalo, 1);
                         $("#check").html('<img src="<%=request.getContextPath()%>/recursos/imagenes/sistema/sm_check.png" height="20" width="20"/>');
                         return false;
                     });
                     addEventosACamposDeTexto();
             });
             </script>
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario"  enctype="multipart/form-data" action="/SIMUNI/modulos/proveedores?proceso=registroproveedorfisico" method="POST">
            <fieldset id="proveedores">
                <legend>Registro de proveedores</legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <label>Número de indentificación: </label>
                            </td>
                            <td>
                                <input type="text" id="txtID" required="required" name="codigoproveedor" placeholder="Cédula #-####-####"/>
                                <div id="mensaje"></div>
                                <span id="codigoproveedorinfo" class="lblinfocontainer">&nbsp;</span>
                            </td>                            
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre: </label>
                            </td>
                            <td>
                                <input required="required" type="text" id="txtNP" name="txtNombreProveedor" placeholder="Primer nombre">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Primer apellido: </label>
                            </td>
                            <td>
                                <input type="text" required="required" id="txtPAP" name="txtApellido1Proveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Segundo apellido: </label>
                            </td>
                            <td>
                                <input type="text" id="txtSAP" name="txtApellido2Proveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Teléfono habitación: </label>
                            </td>
                            <td>
                                <input type="text" id="txtTel" name="txtTelefonoHabitacion" placeholder="formato: ####-####">
                                <div id="mensaje2"></div>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Teléfono oficina: </label>
                            </td>
                            <td>
                                <input type="text" id="txtTO" name="txtTelefonoOficina" placeholder="formato: ####-####">
                                <div id="mensaje3"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Teléfono móvil: </label>
                            </td>
                            <td>
                                <input type="text" required="required" id="txtTM" name="txtTelefonoMovil" placeholder="formato: ####-####">
                                <div id="mensaje4"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <labe>Número de fax: </labe>
                            </td>
                            <td>
                                <input type="text" id="txtTF" name="txtNumeroFax" placeholder="formato: ####-####">
                                <div id="mensaje5"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Correo electrónico: </label>
                            </td>
                            <td>
                                <input type="email" accept="" required="required" id="txtMail" name="txtEmail">
                                <div id="mensaje6"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Dirección web: </label>
                            </td>
                            <td>
                                <input type="text" id="txtDW" name="txtDireccionWeb">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Apartado Postal: </label>
                            </td>
                            <td>
                                <input type="text" id="txtAP" name="txtApartadoPostal">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre de la compañía: </label>
                            </td>
                            <td>
                                <input type="text" id="txtNC" name="txtNombreCompañia">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Dirección de la compañía: </label>
                            </td>
                            <td>
                                <textarea type="text" id="txtDC" name="txtDireccionCompañia" maxlength="300"></textarea>
                                <br/>
                                <label id="LetrasRestantes1">Letras restantes: <div id="contCaracteres"></label></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" id="txtNR" name="txtNombreRepresentante" placeholder="Nombre completo">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del banco: </label>
                            </td>
                            <td>
                                <input type="text" id="txtNB" name="txtNombreBanco">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Número de cuenta:</label>   
                            </td>
                            <td>
                                <input type="text" id="txtNumC" name="txtNumeroCuenta">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Cargar documentos requeridos: </label>
                            </td>
                            <td>
                                <input type="file" required="required" id="btnArchivo" name="filearchivosproveedor" accept="application/pdf"/>
                                <input type="button" id="btnSubirArchivo" value="Examinar" >
                                <div id="check"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" name="btnRegistrar" value="Registrar activo" onclick="validarTelefono();">
                            </td>
                            <td>
                                <input type="reset" name="btnLimpiarFormulario" value="Limpiar formulario">
                            </td>
                        </tr>    
                    </table>
                </div>
            </fieldset>
        </form>
        </decorator:content>
</decorator:decorate>