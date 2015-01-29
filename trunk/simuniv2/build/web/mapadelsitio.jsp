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
                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/acerca"));%>">&#8226; Simuni</a>
                    <ul>
                        <li>
                            <a href="<%out.print(Recursos.Servers.MAINSERVER);%>">&#8226; Inicio</a>
                        </li>
                        <li>&#8226; Módulos
                            <ul>
                                <li>&#8226; Activos
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/activo?proceso=listado"));%>">&#8226; Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/activo?proceso=nuevo"));%>">&#8226; Nuevo</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/tipoactivo"));%>">&#8226; Tipo de activos</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/estado"));%>">&#8226; Estado de activos</a></li>
                                    </ul>
                                </li>
                                <li>&#8226; Proveedores
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/proveedor?proceso=listado"));%>">&#8226; Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/proveedor?proceso=nuevo"));%>">&#8226; Nuevo</a></li>
                                    </ul>
                                </li>
                                <li>&#8226; Reparaciones
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/reparacion?proceso=listado"));%>">&#8226; Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/reparacion?proceso=nuevo"));%>">&#8226; Nuevo</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/"));%>">&#8226; Solicitar reparación</a></li>
                                    </ul>
                                </li>
                                <li>&#8226; Bajas
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/baja"));%>">&#8226; Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/baja?proceso=nuevo"));%>">&#8226; Nuevo</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/solicitudbaja"));%>">&#8226; Solicitar baja</a></li>
                                    </ul>
                                </li>
                                <li>&#8226; Ventas
                                    <ul>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/venta?proceso=listado"));%>">&#8226; Listado</a></li>
                                        <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/venta?proceso=nuevo"));%>">&#8226; Nuevo</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li>&#8226; Ayuda
                            <ul>
                                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/faq"));%>">&#8226; Preguntas frecuentes</a></li>
                                <li><a href="#">&#8226; Manual del usuario</a></li>
                                <li><a href="https://www.youtube.com/channel/UCXB2sRb8aOrUk85PstolE4Q">&#8226; Tutoriales</a>
                                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/contacto"));%>">&#8226; Contacto</a></li>
                                <li><a href="<%out.print(Recursos.Servers.MAINSERVER.toString().concat("/"));%>">&#8226; Mapa del simuni</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </decorator:content>
</decorator:decorate>