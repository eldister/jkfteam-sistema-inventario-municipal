<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='../../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>Titulo de pagina</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
             <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/recursos/estilos/style_resgistrodeproveedores.css">
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <div id="staticMenu">
                <a>Inicio</a> / <a>Proveedores</a> / <a>Gesti�n de proveedores</a> / <a>Registro de proveedores</a>
        </div>
        <br/>
        <div id="informationProcess">
            <p>
                Este es el formulario para el ingreso o registro de nuevos proveedores a la base de datos 
                de la municipalidad a tr�ves del sistema de inventario <b>SIMUNI</b>.<br/>
            </p>
        </div>
        <form id="formulario" action="">
            <fieldset id="proveedores">
                <legend>Registro de proveedores</legend>
                <div id="registerInformation">
                    <table>
                        <tr>
                            <td>
                                <label>N�mero de indentificaci�n del proveedor </label>
                            </td>
                            <td>
                                <input type="text" name="txtIdentificadorProveedor" />
                            </td>                            
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del proveedor: </label>
                            </td>
                            <td>
                                <input type="text" name="txtNombreProveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Primer apellido del proveedor: </label>
                            </td>
                            <td>
                                <input type="text" name="txtApellido1Proveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Segundo apellido del proveedor: </label>
                            </td>
                            <td>
                                <input type="text" name="txtApellido2Proveedor">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Tel�fono habitaci�n: </label>
                            </td>
                            <td>
                                <input type="text" name="txtTelefonoHabitacion">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Tel�fono oficina: </label>
                            </td>
                            <td>
                                <input type="text" name="txtTelefonoOficina">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Tel�fono m�vil: </label>
                            </td>
                            <td>
                                <input type="text" name="txtTelefonoMovil">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <labe>N�mero de fax: </labe>
                            </td>
                            <td>
                                <input type="text" name="txtNumeroFax">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Correo electr�nico: </label>
                            </td>
                            <td>
                                <input type="text" name="txtEmail">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Direcci�n web: </label>
                            </td>
                            <td>
                                <input type="text" name="txtDireccionWeb">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Apartado Postal: </label>
                            </td>
                            <td>
                                <input type="text" name="txtApartadoPostal">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre de la compa��a: </label>
                            </td>
                            <td>
                                <input type="text" name="txtNombreCompa�ia">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Direcci�n de la compa��a: </label>
                            </td>
                            <td>
                                <input type="text" name="txtDireccionCompa�ia">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" name="txtNombreRepresentante">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Primer apellido del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" name="txtApellido1Representante">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Segundo apellido del representante legal: </label>
                            </td>
                            <td>
                                <input type="text" name="txtApellido2Representante">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nombre del banco: </label>
                            </td>
                            <td>
                                <input type="text" name="txtNombreBanco">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>N�mero de cuenta:</label>   
                            </td>
                            <td>
                                <input type="text" name="txtNumeroCuenta">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Cargar documentos requeridos: </label>
                            </td>
                            <td>
                                <input type="file" name="btnArchivos" accept="application/pdf">
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