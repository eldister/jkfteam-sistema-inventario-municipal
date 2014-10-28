<%  request.getSession().setAttribute("LOGINPAGE", "login"); %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
        <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Inicio de sesión</decorator:content>    
        <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
            <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_login.css">
        </decorator:content>
        <decorator:content placeholder='sm_section_mainsectioncontainer'>
            <form id="sm_form_login" action="/SIMUNI/AccionesUsuarios" method="POST">
                <div id="sm_body_IS">
                    <fieldset>
                        <legend>Identifícate</legend>
                        <div id="sm_div_login">
                            <table id="sm_tb_credentials">
                                <tr>
                                    <td><label>Nombre de usuario: </label></td>
                                    <td>
                                        <input id="txtNombreUsuario" name="txtNombreUsuario" type="text" onfocus="this.value=''"/>
                                        <div id="imgIconoUsuario"></div>
                                    </td>
                                </tr>
                                <tr>
                                    <td><label>Contraseña:</label></td>
                                    <td>
                                        <input id="txtPassword" name="txtPassword" type="password" value="password" onfocus="this.value=''"/>
                                        <div id="imgIconoPassword">
                                    </td>
                                </tr>
                                <tr>
                                    <td><input type="checkbox"/><label>Recordar usuario</label></td>
                                </tr>
                                <tr>
                                    <td><input id="btnLogin" type="submit" value="Iniciar Sesión"/></td>
                                    <td><a id="sm_a_olvCont" href="#">Olvido su contraseña?</a></td>
                                </tr>
                            </table>
                        </div>
                    </fieldset>
                </div>
              </form>
        </decorator:content>
</decorator:decorate>