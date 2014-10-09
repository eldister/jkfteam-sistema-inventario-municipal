<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>Titulo de pagina</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
             <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodeproveedores.css">
             <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/script_plugins/maxLength/maxLength.js"></script>
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
                         return false;
                     });
             });
             </script>
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id="informationProcess">
            <p>
                Este es el formulario para el ingreso o registro de nuevos proveedores a la base de datos 
                de la municipalidad a tráves del sistema de inventario <b>SIMUNI</b>.<br/>
            </p>
        </div>
        <form id="formulario" action="">
            <fieldset id="proveedores">
                <legend>Registro de proveedores</legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <label>Número de indentificación: </label>
                            </td>
                            <td>
                                <input type="text" id="txtID" name="txtIdentificadorProveedor" />
                            </td>                            
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre: </label>
                            </td>
                            <td>
                                <input type="text" id="txtNP" name="txtNombreProveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Primer apellido: </label>
                            </td>
                            <td>
                                <input type="text" id="txtPAP" name="txtApellido1Proveedor">
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
                                <input type="text" id="txtTel" name="txtTelefonoHabitacion">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Teléfono oficina: </label>
                            </td>
                            <td>
                                <input type="text" id="txtTO" name="txtTelefonoOficina">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Teléfono móvil: </label>
                            </td>
                            <td>
                                <input type="text" id="txtTM" name="txtTelefonoMovil">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <labe>Número de fax: </labe>
                            </td>
                            <td>
                                <input type="text" id="txtTF" name="txtNumeroFax">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Correo electrónico: </label>
                            </td>
                            <td>
                                <input type="text" id="txtMail" name="txtEmail">
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
                                <div><label id="LetrasRestantes1">Letras restantes: </div><div id="contCaracteres"></div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" id="txtNR" name="txtNombreRepresentante">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Primer apellido del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" id="txtPAR" name="txtApellido1Representante">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Segundo apellido del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" id="txtSAR" name="txtApellido2Representante">
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
                                <input type="file" id="btnArchivo" name="btnArchivos" accept="application/pdf">
                                <input type="button" id="btnSubirArchivo" value="Examinar">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input type="submit" name="btnRegistrar" value="Registrar activo">
                            </td>
                            <td>
                                <input type="reset" name="btnLimpiarFormulario" value="Limpiar formulario">
                            </td>
                        </tr>    
                    </table>
                </div>
        </form>
        </decorator:content>
               
</decorator:decorate>