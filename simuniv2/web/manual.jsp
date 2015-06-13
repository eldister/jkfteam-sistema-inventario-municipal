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
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_de_registro_de_usuario.pdf">Registrar un nuevo usuario</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_actualizar_registro_de_usuario.pdf">Actualizar registro de usuario</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_asignar_permisos_a_usuarios.pdf">Asignar permisos a los usuarios</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_eliminar_usuario.pdf">Eliminar un usuario</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_para_cierrar_sesión.pdf">Cerrar sesión</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_para_enviar_un_mensaje.pdf">Enviar un mensaje</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_registro_reparación.pdf">Registro de una reparación de activo</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Eliminación_de_Proveedor.pdf">Eliminación de Proveedor</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Ingresar_Ventas.pdf">Ingreso de ventas de activos</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Ingreso_de_Nuevo_proveedor.pdf">Ingreso de Nuevo Proveedor</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Modificación_Proveedor.pdf">Modificación de Proveedor</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Registro_de_baja.pdf">Registro de Baja de un Activo</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Guía_Solicitud_Reparación_de_Activos.pdf">Solicitud de Reparación de un Activo</a>
                    </div>  
                </div>
                <div class='sm_div_manual_block'>   
                    <code class="sm_manual_fecha">Agregado el 06 de junio 2015</code>
                    <span class="sm_div_textomanualsender_block">
                        - Generado por Administrador de Sistema
                    </span>
                    <div class='sm_div_textomanual_block'>
                        <a target="_blank" href="modulos/ayuda/manual/Solicitud_de_Baja_de_activo.pdf">Solicitud de Baja de un Activo</a>
                    </div>  
                </div>
            </fieldset>
        </div>
    </decorator:content>
</decorator:decorate>