<%@page import="simuni.classes.EN.ProveedorFisico"%>
<%@page import="java.util.ArrayList"%>
<%

    ProveedorFisico proveedor = (ProveedorFisico) request.getAttribute("proveedor");
    if (proveedor == null) {
        proveedor = new ProveedorFisico();
    }
%>
<form id="sm_div_formulario" method="POST" action="/SIMUNI/modulos/proveedores?proceso=modificacionproveedorfisico">
    <fieldset id="proveedores">
        <legend>Registro de proveedores</legend>
        <div id="registerInformation">
            <table id="sm_tb_campos">
                <tr>
                    <td>
                        <label>Número de indentificación: </label>
                    </td>
                    <td>
                        <span><%out.print(proveedor.getPa_cedula()); %></span>
                    </td>                            
                </tr>
                <tr>
                    <td>
                        <label>Nombre: </label>
                    </td>
                    <td>
                        <input type="text" id="txtNP" value="<%out.print(proveedor.getPa_nombre()); %>" name="txtNombreProveedor">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Primer apellido: </label>
                    </td>
                    <td>
                        <input type="text" id="txtPAP" value="<%out.print(proveedor.getPa_primerApellido()); %>" name="txtApellido1Proveedor">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Segundo apellido: </label>
                    </td>
                    <td>
                        <input type="text" id="txtSAP" value="<%out.print(proveedor.getPa_segundoApellido()); %>" name="txtApellido2Proveedor">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Teléfono habitación: </label>
                    </td>
                    <td>
                        <input type="text" id="txtTel" value="<%out.print(proveedor.getPa_telefonoFijo()); %>" name="txtTelefonoHabitacion">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Teléfono oficina: </label>
                    </td>
                    <td>
                        <input type="text" id="txtTO" value="<%out.print(proveedor.getPa_fax()); %>" name="txtTelefonoOficina">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Teléfono móvil: </label>
                    </td>
                    <td>
                        <input type="text" id="txtTM" value="<%out.print(proveedor.getPa_telefonoMovil()); %>" name="txtTelefonoMovil">
                    </td>
                </tr>
                <tr>
                    <td>
                <labe>Número de fax: </labe>
                </td>
                <td>
                    <input type="text" id="txtTF" value="<%out.print(proveedor.getPa_fax()); %>" name="txtNumeroFax">
                </td>
                </tr>
                <tr>
                    <td>
                        <label>Correo electrónico: </label>
                    </td>
                    <td>
                        <input type="text" id="txtMail" value="<%out.print(proveedor.getPa_correoElectronico()); %>" name="txtEmail">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Dirección web: </label>
                    </td>
                    <td>
                        <input type="text" id="txtDW" value="<%out.print(proveedor.getPa_sitioWeb()); %>" name="txtDireccionWeb">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Apartado Postal: </label>
                    </td>
                    <td>
                        <input type="text" id="txtAP" <%out.print(proveedor.getPn_apartadoPostal()); %> name="txtApartadoPostal">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nombre de la compañía: </label>
                    </td>
                    <td>
                        <input type="text" id="txtNC" <%out.print(proveedor.getPa_nombreCompania()); %> name="txtNombreCompañia">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Dirección de la compañía: </label>
                    </td>
                    <td>
                        <textarea type="text" id="txtDC" name="txtDireccionCompañia" maxlength="300">
                            <%out.print(proveedor.getPa_direccionCompania()); %>
                        </textarea>
                        <br/>
                        <div><label id="LetrasRestantes1">Letras restantes: </div><div id="contCaracteres"></div>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nombre del representante legal: </label>
                    </td>
                    <td>
                        <input type="text" value="" id="txtNR" name="txtNombreRepresentante">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Nombre del banco: </label>
                    </td>
                    <td>
                        <input type="text" id="txtNB" value="<%out.print(proveedor.getPa_banco()); %>" name="txtNombreBanco">
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Número de cuenta:</label>   
                    </td>
                    <td>
                        <input type="text" value="<%out.print(proveedor.getPa_numeroCuenta()); %>" id="txtNumC" name="txtNumeroCuenta">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="submit" name="btnRegistrar" value="Actualizar activo">
                    </td>
                    <td>
                        
                    </td>
                </tr>    
            </table>
        </div>
    </fieldset>
                            <div id="sm_form_extracontent">
            <input type="hidden"  value="<% out.print(proveedor.getPa_cedula()); %>" name="hiddenidProveedor">
           </div>
</form>