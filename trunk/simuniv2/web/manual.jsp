<%@page import="simuni.enums.Recursos"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/"%>
<decorator:decorate filename='recursos/paginas/master/masterpage.jsp'>
    <decorator:content placeholder='sm_section_titulodepagina'>SIMUNI | Manual de Usuario</decorator:content>    
    <decorator:content placeholder='sm_section_estilosyscriptssectioncontainer'>
        <link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/css_paginas/style_manual.css"
        </decorator:content>
        <decorator:content placeholder='sm_div_navegationbarmenuitems'>
            <ol class="breadcrumb">
            <li><a href="<%out.print(Recursos.Servers.MAINSERVER);%>/">Inicio</a></li>   
            <li class="active">Manual de Usuario</li>
        </ol>
    </decorator:content>                  
    <decorator:content placeholder='sm_section_mainsectioncontainer'>
        <div id="simuni_div_manualcontainer">
            <fieldset>
                <legend>Manuales del Sistema</legend>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/ingresar un articulo.pdf">Ingresar un nuevo artículo a la base de datos</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/configuracionsistema.pdf">Configuración del sistema</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/eliminaractivo.pdf">Hacer la eliminación de un activo</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/grillageneral.pdf">Guía para la grilla del sistema SIMUNI</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/ingresaruntranpsortemoto.pdf">Ingresar un nuevo activo a la base de datos (moto)</a>
                    </div>  
                </div>  
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/mantenimientogeneral.pdf">Mantenimientos generales del Sistema</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/modificarunarticulo.pdf">Modificar un  artículo a la base de datos</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a href="modulos/ayuda/manual/reportes.pdf">Reportes del Sistema</a>
                    </div>  
                </div>                
            </fieldset>
        </div>
    </decorator:content>
</decorator:decorate>