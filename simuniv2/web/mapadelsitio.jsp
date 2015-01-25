<%@page import="simuni.enums.Recursos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI - Mapa del sitio</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/css_paginas/style_mapadelsitio.css">
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Mapa del sitio</li>
        </ol>
    </decorator:content>                  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div class="sm_div_mapasitio">
            <ul>
                <li>Simuni
                    <ul>
                        <li>
                            <a href="<%out.print(Recursos.Servers.MAINSERVER);%>">Inicio</a>
                        </li>
                        <li>Módulos
                            <ul>
                                <li>Activos
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/activo?proceso=listado"));%>">Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/activo?proceso=nuevo"));%>">Nuevo</a></li>
                                    </ul>
                                </li>
                                <li>Proveedores
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/proveedor?proceso=listado"));%>">Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/proveedor?proceso=nuevo"));%>">Nuevo</a></li>
                                    </ul>
                                </li>
                                <li>Reparaciones
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/reparacion?proceso=listado"));%>">Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/reparacion?proceso=nuevo"));%>">Nuevo</a></li>
                                    </ul>
                                </li>
                                <li>Bajas
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/baja"));%>">Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/baja?proceso=nuevo"));%>">Nuevo</a></li>
                                    </ul>
                                </li>
                                <li>Ventas
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/venta?proceso=listado"));%>">Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/venta?proceso=nuevo"));%>">Nuevo</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>Ayuda
                            <ul>
                                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/faq"));%>">Preguntas frecuentes</a></li>
                                <li><a href="https://www.youtube.com/channel/UCXB2sRb8aOrUk85PstolE4Q">Tutoriales</a>
                                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/contacto"));%>">Contacto</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </decorator:content>
</decorator:decorate>