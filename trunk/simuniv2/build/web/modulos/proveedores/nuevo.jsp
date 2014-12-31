<%@page import="simuni.entidades.Proveedor"%>
<%@page import="simuni.enums.Recursos"%>
<%@ page language='java' contentType='text/html; charset=ISO-8859-1' pageEncoding='ISO-8859-1'%>
<%@ taglib prefix='decorator' uri='http://claudiushauptmann.com/jsp-decorator/'%>
<%
    Proveedor proveedor = null;

    try {
        proveedor = (Proveedor) request.getAttribute("registro");

        if (proveedor == null) {
            proveedor = new Proveedor();
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }

%>
<decorator:decorate filename='../../recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Nuevo Proveedor </decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <script src='<%=request.getContextPath()%>/js/script_validator.js' charset="utf-8"></script>
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_nuevo_proveedor.js' charset="utf-8"></script>

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
            //cmbtipoproveedor
            cmbtipoproveedor = "<%out.print(proveedor.getTipoProveedor()); %>";
            //cmbestadoproveedor
            cmbestadoproveedor = "<%out.print(proveedor.getEstado()); %>";
            //cmbnombrebanco
            cmbnombrebanco = "<%out.print(proveedor.getNombreBanco()); %>";
     
            //cmbtipoproveedor="Jurídico";
        </script>
    </decorator:content>
    <decorator:content placeholder='sm_div_navegationbarmenuitems'>
        <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li> 
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso?listado">Proveedores</a></li> 
            <li class="active">Nuevo</li>
        </ol>

    </decorator:content>
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <form id="formulario" class="form" method="POST" action="<%out.print(Recursos.Servers.MAINSERVER);%>/proveedor?proceso=nuevo">
            <fieldset id="proveedores">
                <legend>Registro de proveedores <small><sup>* Campos requeridos</sup></small></legend>
                <div id="registerInformation">
                    <table id="sm_tb_campos">
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="cmbtiporpoveedor">Tipo de Proveedor</label>
                                    <select class="form-control" name="cmbtipoproveedor" id="cmbtiporpoveedor">
                                        <option>Físico</option>
                                        <option>Jurídico</option>
                                    </select>
                                </div>
                            </td>  
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="cmbestadoproveedor">Estado del Proveedor</label>
                                    <select class="form-control" name="cmbestadoproveedor" id="cmbestadoproveedor">
                                        <option>Activo</option>
                                        <option>Inactivo</option>
                                    </select>
                                </div>
                            </td>                              
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtcedulaproveedor">Cédula *</label>
                                    <input type="text" value="<%out.print(proveedor.getCedula()); %>" class="form-control" name="txtcedulaproveedor" required="required" id="txtcedulaproveedor" placeholder="Ej. 505550555">
                                </div>
                            </td>  
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtnombreproveedor">Nombre *</label>
                                    <input type="text" value="<%out.print(proveedor.getNombre()); %>" class="form-control" required="required" name="txtnombreproveedor" id="txtnombreproveedor" placeholder="Ej. Juan">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtapellido1proveedor">Primer Apellido *</label>
                                    <input type="text" value="<%out.print(proveedor.getPrimerApellido()); %>" class="form-control" required="required"  name="txtapellido1proveedor" id="txtappellido1proveedor" placeholder="Ej. Ramírez">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtapellido2proveedor">Segundo Apellido</label>
                                    <input type="text" value="<%out.print(proveedor.getSegundoApellido()); %>" class="form-control" id="txtappellido2proveedor" name="txtapellido2proveedor" placeholder="Ej. Ramírez">
                                </div>
                            </td>                            
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txttelefonofijo">Teléfono Fijo</label>
                                    <input type="text" value="<%out.print(proveedor.getTelFijo()); %>" class="form-control" id="txttelefonofijo" name="txttelefonofijo" placeholder="Ej. 88888888">
                                </div>
                            </td>   
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txttelefonomovil">Teléfono Movil *</label>
                                    <input type="text" value="<%out.print(proveedor.getTelMovil()); %>" class="form-control" required="required"  name="txttelefonomovil"  id="txttelefonomovil" placeholder="Ej. 88888888">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txttelefonooficina">Teléfono Oficina</label>
                                    <input type="text" value="<%out.print(proveedor.getTelOfic()); %>" class="form-control" id="txttelefonooficina" name="txttelefonooficina"  placeholder="Ej. 88888888">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtfax">Fax</label>
                                    <input type="text" value="<%out.print(proveedor.getTelFax()); %>" class="form-control" id="txtfax"  name="txtfax"  placeholder="Ej. 88888888">
                                </div>
                            </td>
                        </tr> 
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtemail">Correo Electrónico *</label>
                                    <input type="email" value="<%out.print(proveedor.getEmail()); %>" class="form-control" required="required" name="txtemail" id="txtemail" placeholder="alguien@algo.com">
                                </div>
                            </td>  
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtweb">Página Web</label>
                                    <input type="text" value="<%out.print(proveedor.getPaginaWeb()); %>" class="form-control" id="txtweb" name="txtweb" placeholder="www.alguien.com">
                                </div>
                            </td> 
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtapartadopostal">Apartado Postal</label>
                                    <input type="text" value="<%out.print(proveedor.getApartadoPostal()); %>" class="form-control" id="txtapartadopostal" name="txtapartadopostal" placeholder="900145">
                                </div>
                            </td>   
                        </tr>

                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtcedularepresentante">Cédula <small><sup> * Representante legal</sup></small></label>
                                    <input type="text" value="<%out.print(proveedor.getCedulaRepresentanteLegal()); %>" class="form-control" id="txtcedularepresentante" name="txtcedularepresentante" placeholder="Ej. Juan">
                                </div>
                            </td>   
                            <td>

                                <div class="form-group">
                                    <label  class="control-label"for="txtnombrerepresentante">Nombre  <small><sup> * Representante legal</sup></small></label>
                                    <input type="text" value="<%out.print(proveedor.getNombreRepresentanteLegal()); %>" class="form-control" id="txtnombrerepresentante" name="txtnombrerepresentante" placeholder="Ej. Juan">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtappellido1representante">Primer Apellido <small><sup> * Representante legal</sup></small></label>
                                    <input type="text" value="<%out.print(proveedor.getPrimerApellidoRepresentanteLegal()); %>" class="form-control" id="txtappellido1representante" name="txtappellido1representante" placeholder="Ej. Ramírez">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtappellido2representante">Segundo Apellido <small><sup> * Representante legal</sup></small></label>
                                    <input type="text" value="<%out.print(proveedor.getSegundoApellidoRepresentanteLegal()); %>" class="form-control" id="txtappellido2representante" name="txtappellido2representante" placeholder="Ej. Ramírez">
                                </div>
                            </td>                            
                        </tr>  
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="cmbnombrebanco">Nombre  Banco</label>
                                    <select class="form-control" id="cmbnombrebanco" name="cmbnombrebanco">
                                        <option>Banco Nacional</option>
                                        <option>Banco Costa Rica</option>
                                        <option>Banco HSBC</option>
                                    </select>
                                </div>
                            </td>   
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtnumcuenta">Número de Cuenta</label>
                                    <input type="text" value="<%out.print(proveedor.getNumeroCuenta()); %>" class="form-control" name="txtnumcuenta" id="txtnumcuenta" placeholder="Ej. 200-01-062-154">
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtnombreempresa">Nombre de la Empresa</label>
                                    <input type="text" value="<%out.print(proveedor.getNomEmpresa()); %>" class="form-control" name="txtnombreempresa" id="txtnombreempresa" placeholder="Ej. Construcciones XYZ">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <label  class="control-label"for="txtdireccionempresa">Dirección de la empresa</label>
                                    <textarea class="form-control"  id="txtdireccionempresa" name="txtdireccionempresa" placeholder="Ej. 500 metros sur de la escuela"><%out.print(proveedor.getDirEmpresa());%></textarea>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td>&nbsp;</td>
                            <td>
                                <div class="form-group">
                                    <input type="submit" value="Registrar Proveedor" class="form-control btn-info">
                                </div>
                            </td>
                            <td>
                                <div class="form-group">
                                    <input type="reset" class="form-control" value="Limpiar formulario">
                                </div>
                            </td>
                        </tr>    
                    </table>
                </div>
            </fieldset>
        </form>
    </decorator:content>
</decorator:decorate>