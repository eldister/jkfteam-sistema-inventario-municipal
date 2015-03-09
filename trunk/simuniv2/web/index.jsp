<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="simuni.clases.ln.ManejadorUsuario"%>
<%@page import="simuni.clases.ln.ManejadorDepartamento"%>
<%@page import="simuni.entidades.mantenimientos.Departamento"%>
<%@page import="simuni.entidades.mantenimientos.Puesto"%>
<%@page import="simuni.clases.ln.ManejadorPuesto"%>
<%@page import="simuni.entidades.mantenimientos.TipoUsuario"%>
<%@page import="simuni.clases.ln.ManejadorTipoUsuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <%
        ManejadorUsuario muInfoU = new ManejadorUsuario();
        Usuario user = new Usuario();
        user = muInfoU.obtenerUsuario(request.getSession().getAttribute("USERNAME").toString());
        ManejadorDepartamento mdDepto = new ManejadorDepartamento();
        Departamento depto = new Departamento();
        depto = mdDepto.getDepartamento(user.getAreatrabajo());
        ManejadorTipoUsuario mtuTipoU = new ManejadorTipoUsuario();
        TipoUsuario tuTipoU = new TipoUsuario();
        tuTipoU = mtuTipoU.getTipoUsuario(user.getTipousuario());
    %>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Inicio</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/css_paginas/style_pag_inicio.css">
        <script src='<%=request.getContextPath()%>/js/script_paginas/script_mantenimiento_usuarios.js' charset="utf-8"></script>
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>
        </ol>
    </decorator:content>                  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id="container" class="container">
            <div id="logocampo" name="logocampo" class="logocampo">
                <div id="logosys" class="logosys" name="logosys">
                    <img  src="/simuniv2/recursos/imagenes/sistema/sm_logo_simuni.png" width="80" height="80">
                </div>
                <div id="simunipa" class="simunipa">
                    <h3>SIMUNI</h3>
                </div>
            </div>
            <hr/>
            <div id="saludo" class="saludo" name="saludo">
                Bienvenido <strong><%=request.getSession().getAttribute("USERNAME")%></strong>
                <input type="hidden" id="id_usuario" name="id_usuario" class="id_usuario">
            </div>
            <div id="config" class="config" name="config">
                <a href="javascript:mostrarventanamodal();">Actualizar información</a>
            </div>
            <hr/>
            <center>
                <div id="userinfo" class="userinfo" name="userinfo">
                    <table style="border: black solid 1px">
                        <tr style="border: black solid 1px">
                            <td colspan="4" style="border: black solid 1px">Nombre completo: <%out.print(user.getNombre() + " " + user.getApellido1() + " " + user.getApellido2());%></td>
                        </tr>
                        <tr style="border: black solid 1px">
                            <td style="border: black solid 1px">Cédula: <%out.print(user.getCedula());%></td>
                            <td style="border: black solid 1px">Departamento: <%out.print(depto.getNombredepartamento());%></td>
                            <td style="border: black solid 1px">Puesto: <%out.print(user.getCodigoPuesto());%></td>
                        </tr>
                        <tr>
                            <td style="border: black solid 1px" colspan="4">Correo electrónico: <%out.print(user.getEmail());%></td>
                        </tr>
                    </table>
                </div>
            </center>
            <hr/>
            <center>
                <div id="modxuser" name="modxuser" class="modxuser">
                    <label>Los módulos para el tipo usuario:</label><small><%out.print(tuTipoU.getNombretipo());%></small>
                </div>
                <hr/>
                <div id="procesos" name="procesos" class="procesos">
                    proceso 1 proceso 2 etc...
                </div>
            </center>
        </div>
        <div id="sm_body_ventanamodal">&nbsp;</div>
    </decorator:content>
</decorator:decorate>