<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
        <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Contáctenos</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_contacto.css">
            <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_validaciones.js"></script>
            <script type="text/javascript" src="<%=request.getContextPath()%>/recursos/scripts/script_plugins/maxLength/maxLength.js"></script>
            <script>
                $(function(){
                    $("#txtmsg").maxLength(300, { showNumber: "#contadorCaracteres"});
                 });
             </script>
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <div id="sm_body_pAcerca">
                <div id="sm_div_logoSection">
                    <div id="sm_div_logo">
                        <span id="sm_div_nombreSist">
                            <h1>Sistema de Información para el Control del Inventario Municipal</h1>
                        </span>
                    </div>
                </div>
                <div id="sm_div_contact">
                    <hr/>
                    <div id="sm_div_listaContactos">
                        <div id="sm_div_primerContacto">
                            <fieldset>
                                <legend>Contacte con</legend>
                                <div id="sm_div_pc">
                                    <h3>Francisco Coulon Ollivier</h3>
                                    <label>Teléfono: cualquiera</label><br/>
                                    <label>Correo electrónico: francisco.coulon.ollivier@est.una.ac.cr</label><br/>
                                    <label>Estudiante de la UNA</label>
                                </div>
                            </fieldset>
                        </div>
                        <div id="sm_div_segundoContacto">
                            <fieldset>
                                <legend>Contacte con</legend>
                                <div id="sm_div_sc">
                                    <h3>Keylin Pérez Quesada</h3>
                                    <label>Teléfono: cualquiera</label><br/>
                                    <label>Correo electrónico: key.pe23@gmail.com</label><br/>
                                    <label>Estudiante de la UNA</label>
                                </div>
                            </fieldset>
                        </div>
                        <div id="sm_div_tercerContacto">
                            <fieldset>
                                <legend>Contacte con</legend>
                                <div id="sm_div_sc">
                                    <h3>Jefferson Salazar Monge</h3>
                                    <label>Teléfono: cualquiera</label><br/>
                                    <label>Correo electrónico: pjingames@gmail.com</label><br/>
                                    <label>Estudiante de la UNA</label>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <hr/>
                    <div id="sm_div_mail">
                        <table id="sm_tb_contacto">
                            <tr>
                                <td>
                                    <label>Nombre: </label>
                                </td>
                                <td>
                                    <input type="text" id="txtNombC" name="txtNombreContacto" placeholder="Nombre completo"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Teléfono: </label>
                                </td>
                                <td>
                                    <input type="text" id="txtTelC" name="txtTelefonoContacto"/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Correo electrónico: </label>
                                </td>
                                <td>
                                    <input typ="text" id="txtMailC" name="txtMailContacto"/>
                                    <div id="mensaje"></div>
                                </td>
                            </tr>
                            <tr>
                                <td>Mensaje: </td>
                                <td>
                                    <textarea type="text" id="txtmsg" name="txtMensajeContacto" maxlength="300" placeholder="Introduzca un breve mensaje"></textarea>
                                    <br/>
                                    <label id="LetrasRestantes">Letras restantes: <div id="contadorCaracteres"></div></label>
                                </td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Enviar" onclick="validarMail();"/></td>
                            </tr>
                        </table>
                    </div>
                </div>
           </div>
        </decorator:content>
</decorator:decorate>