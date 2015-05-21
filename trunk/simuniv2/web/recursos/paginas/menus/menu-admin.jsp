<%-- 
    Document   : menu-admin
    Created on : 31 janv. 2015, 19:24:24
    Author     : FchescO
--%>

<%@page import="simuni.enums.Recursos"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Menú Administrador</title>
        <link rel="stylesheet" href="../../../css/style_menuprincipal_v3.css">
    </head>
    <body>
        <div  id="simuni_div_menu">
            <ul>
                <li><a href="%MAINSERVER%"  title="Información del usuario registrado en el sistema"><img src="/simuniv2/recursos/imagenes/menu/home.png" width="20" height="16" style="padding-right: 4px;">Inicio</a></li>
                <li class="active has-sub"><a href="#" title="Módulos del sistema">Secciones del Sistema<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Activo<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/activo?proceso=nuevo" title="Ingreso de un nuevo activo">Registro</a></li>
                                <li><a href="%MAINSERVER%/activo?proceso=listado" title="Lista de activos para: modificar,desactivar... activos">Listado</a></li>
                            </ul>
                        </li>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Configuraciones<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/configuracion?proceso=nuevo" title="Registrar una configuración">Registro</a></li>
                                <li><a href="%MAINSERVER%/configuracion?proceso=listado" title="Lista de configuraciones">Listado</a></li>
                                <li><a href="%MAINSERVER%/configuracion?proceso=actualizar_configuracion" title="Lista solicitudes de bajas">Editar</a></li>
                            </ul>
                        </li>  
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Prestamos<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/prestamo?proceso=nuevo" title="Registrar una préstamos">Registro</a></li>
                                <li><a href="%MAINSERVER%/prestamo?proceso=listado" title="Lista de préstamos">Listado</a></li>
                            </ul>
                        </li>                          
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Proveedores<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/proveedor?proceso=nuevo" title="Ingreso de un nuevo proveedor">Registrar</a></li>
                                <li><a href="%MAINSERVER%/proveedor?proceso=listado" title="Lista de proveedores">Listado</a></li>
                                <li><a href="%MAINSERVER%/proveedor?proceso=listado2" title="Lista de proveedores">Listado Correos</a></li>
                            </ul>
                        </li>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Reparaciones<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/reparacion?proceso=nuevo" title="Ingreso de una nueva reparación">Registrar</a></li>
                                <li><a href="%MAINSERVER%/reparacion?proceso=listado" title="Lista de reparaciones">Listado</a></li>
                            </ul>
                        </li>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Reportes<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/reporte?proceso=configuracion" title="Configuración de Reportes">Configuración de Reporte</a></li>
                                <li><a href="%MAINSERVER%/configuracion" title="Generar un reporte">Generar Reporte</a></li>
                            </ul>
                        </li>                        
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Solicitudes<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/solicitudbaja?proceso=nuevo" title="Solicitar baja de activo">Solicitud de baja de activo</a></li>
                                <li><a href="#" title="Solicitar reparación">Solicitud de reparación de activo</a></li>
                                <li><a href="%MAINSERVER%/solicitudbaja?proceso=listado" title="Ver mis solicitudes de baja">Mis solicitudes</a></li>
                            </ul>
                        </li>
                     
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Ventas<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/venta?proceso=nuevo" title="Ingreso de una nueva venta">Registrar</a></li>
                                <li><a href="%MAINSERVER%/venta?proceso=listado" title="Lista de ventas">Listado</a></li>
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="active has-sub"><a href="#" title="Desplegar para ver opciones">Panel de Control<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Mantenimientos <img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float:right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/departamento" title="Mantenimiento de Departamentos">Departamentos</a></li>
                                <li><a href="%MAINSERVER%/estado" title="Mantenimiento de estados">Estados de Activos</a></li>
                                <li><a href="%MAINSERVER%/permiso" title="Mantenimiento de los permisos de usuario">Permisos de Usuario</a></li>
                                <li><a href="%MAINSERVER%/tipoactivo" title="Mantenimiento de tipos de activo">Tipos de activo</a></li>
                                <li><a href="%MAINSERVER%/tipobateria" title="Mantenimiento de tipos de batería">Tipos de batería</a></li>
                                <li><a href="%MAINSERVER%/tipollanta" title="Mantenimiento de tipos de llanta">Tipos de llanta</a></li>
                                <li><a href="%MAINSERVER%/tipopago" title="Mantenimiento de tipos de pago">Tipos de pago</a></li>
                                <li><a href="%MAINSERVER%/tiporeporte" title="Mantenimiento de tipos de reporte">Tipos de reporte</a></li>
                                <li><a href="%MAINSERVER%/tipousuario" title="Mantenimiento de tipos de usuarios">Tipos de usuario</a></li>
                            </ul>                        
                        </li>
                    </ul>
                </li>
                <li class="active has-sub"><a href="#" Despliega el menú para ver opciones>Personal<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li><a href="%MAINSERVER%/mensaje?proceso=nuevo" title="Podrá enviar mensajes a otro usuario">Enviar mensaje</a></li>
                        <li><a href="%MAINSERVER%/mensaje?proceso=ver_mensajes" title="Podrá enviar mensajes a otro usuario">Mis Mensajes</a></li>
                        <li><a href="%MAINSERVER%/notificacion?proceso=ver_notificacion" title="Ver notificaciones">Mis Notificaciones</a></li>
                        <li><a href="%MAINSERVER%/usuario?proceso=renovacion" title="Renovación de clave">Renovación de Clave</a></li>
                    </ul>
                </li>
                <li class="active has-sub"><a href="#" title="Desplegar para ver opciones">Soporte<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li class="has-sub"><a href="#" title="Despliega el sub-menú">Respaldo<img src="/simuniv2/recursos/imagenes/menu/circle-right.png" width="16" height="12" style="float: right;"></a>
                            <ul>
                                <li><a href="%MAINSERVER%/soporte?proceso=backup" title="Realizar un respaldo no planificado">Hacer un respaldo</a></li>
                                <li><a href="%MAINSERVER%/configuracion?proceso=actualizar_cr" title="Configurar tarea respaldo">Configurar tarea respaldo</a></li>
                                <li><a href="%MAINSERVER%/configuracion?proceso=nuevo_cr" title="Nueva configuración de  tarea respaldo">Nueva Configuración de Respaldo</a></li>
                        
                            </ul>
                        </li>
                    </ul>
                </li>
                <li class="active has-sub"><a href="#" title="Desplegar para ver opciones">Ayuda<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li><a href="%MAINSERVER%/manual" title="Acceder al manual">Manual de usuario</a></li>
                        <li><a href="%MAINSERVER%/acerca" title="Acerca del Sistema">Acerca de...</a></li>
                        <li><a href="%MAINSERVER%/mapa" title="Mapa del Sitio">Mapa del sitio</a></li>
                        <li><a href="%MAINSERVER%/faq" title="Preguntas Frecuentes">Preguntas frecuentes (FAQ)</a></li>
                        <li><a href="%MAINSERVER%/contacto" title="Contactar a los creadores">Contacto</a></li>
                    </ul>
                </li>
                <li class="active has-sub"><a href="#" title="Acerca de">Informaciones<img src="/simuniv2/recursos/imagenes/menu/circle-down.png" width="20" height="16" style="padding-left: 4px;"></a>
                    <ul>
                        <li><a href="%MAINSERVER%/acercade" title="Acceder al manual">Acerca de</a></li>
           </ul>
                </li>                
            </ul>
        </div>        
        <!--    <nav>
                <ul id="simuni_div_menu">
                    <li class="sm_ulmenucontainer">
                        <span class="icomenu"></span>
                        <a href="%MAINSERVER%" class="menulinks" title="Página de Inicio del SIMUNI">
                            Pagina de Inicio
                        </a>
                    </li>
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="" class="menulinks" title="Módulos del sistema">
                            Secciones del Sistema
                        </a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Activos</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/activo?proceso=nuevo" class="menulinks" title="Ingreso de un nuevo activo">
                                            Registrar un Activo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/activo?proceso=listado" class="menulinks" title="Lista de activos para: modificar,desactivar... activos">
                                            Ver Activos
                                        </a>
                                    </li>
                                </ul>                                
                            </li>
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Bajas</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/baja?proceso=nuevo" class="menulinks" title="Registro de Baja">
                                            Dar de baja un Activo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/baja?proceso=listado" class="menulinks" title="Listado de las bajas">
                                            Ver Registros de Bajas
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/baja?proceso=obtener_solicitudes" class="menulinks" title="Ver solicitudes de baja">
                                            Solicitudes de  Baja
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li> 
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Configuraciones*</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/configuracion?proceso=nuevo" class="menulinks" title="Nueva configuración">
                                            Nueva Configuración
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/configuracion?proceso=nuevo" class="menulinks" title="Ver configuraciones">
                                            Ver Configuraciones
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/configuracion?proceso=actualizar_configuracion" class="menulinks" title="Ver solicitudes de baja">
                                            Editar Configuracion del sistema
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li>   
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Prestamos</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/activo?proceso=nuevo" class="menulinks" title="Registrar préstamo de activo">
                                            Registrar un Préstamo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/activo?proceso=listado" class="menulinks" title="Ver préstamos registrados ">
                                            Listado de Préstamos
                                        </a>
                                    </li>
                                </ul>                                
                            </li>                        
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Proveedores</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/proveedor?proceso=listado" class="menulinks" title="Lista de proveedores">
                                            Listado de Proveedores
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/proveedor?proceso=nuevo" class="menulinks" title="Ingreso de un nuevo proveedor">
                                            Registro  Proveedor
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/proveedor?proceso=listado2" class="menulinks" title="Obtener correos de proveedor por categorías.">
                                            Listado por Correo de Proveedores
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li> 
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Reparaciones</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/reparacion?proceso=nuevo" class="menulinks" title="Registrar una reparación">
                                            Registrar una Reparación
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/reparacion?proceso=listado" class="menulinks" title="Ver reparaciones">
                                            Ver Reparaciones
                                        </a>
                                    </li>
                                </ul>                                
                            </li>    
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Reportes</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/reporte?proceso=configuracion" class="menulinks" title="Configuración de reportes">
                                            Configurar Reportes
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/reporte" class="menulinks" title="Generar un reporte">
                                            Generar un Reporte
                                        </a>
                                    </li>
                                </ul>                                
                            </li> 
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Solicitudes</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/solicitudbaja?proceso=nuevo" class="menulinks" title="Nuevo baja">
                                            Nueva solicitud de Baja
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/solicitudbaja?proceso=listado" class="menulinks" title="Listado de bajas">
                                            Nueva solicitud de Reparación
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/solicitudbaja?proceso=listado" class="menulinks" title="Ver mis solicitudes de baja.">
                                            Mis solicitudes de baja
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li>                        
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Usuarios</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/usuario?proceso=listado" class="menulinks" title="Lista de usuarios">
                                            Listado de Usuarios
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/usuario?proceso=permisos" class="menulinks" title="Permisos de Usuario">
                                            Permisos de Usuarios
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/usuario?proceso=listado" class="menulinks" title="Resetear clave de Usuario">
                                            Resetear Clave
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li>  
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Ventas</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/venta?proceso=listado" class="menulinks" title="Lista de ventas">
                                            Listado de Ventas
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/venta?proceso=nuevo" class="menulinks" title="Ingreso de una nueva venta">
                                            Registro Nueva Venta
                                        </a>
                                    </li>
                                </ul>                                
                            </li>                           
                        </ul>                        
                    </li>
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="" class="menulinks" title="Módulo sistema">Panel de Control</a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer has-sub">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Desplegar para ver opciones">Mantenimientos</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/departamento" class="menulinks" title="Mantenimiento de Departamentos">
                                            Departamentos
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/estado" class="menulinks" title="Mantenimiento de estados">      
                                            Estados de Activos
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipoactivo" class="menulinks" title="Mantenimiento de tipos de activo">
                                            Permisos de Usuario
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/permiso" class="menulinks" title="Mantenimiento de tipos de activo">
                                            Tipos de Activo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipobateria" class="menulinks" title="Mantenimiento de tipos de batería">      
                                            Tipos de Batería
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipollanta" class="menulinks" title="Mantenimiento de tipos de llanta">
                                            Tipos de Llanta
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipopago" class="menulinks" title="Mantenimiento de tipos de pago">      
                                            Tipos de Pago
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipoactivo" class="menulinks" title="Mantenimiento de tipos de activo">
                                            Tipos de Proveedor
                                        </a>
                                    </li>                                
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tiporeporte" class="menulinks" title="Mantenimiento de tipos de reporte">
                                            Tipos de Reporte
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/tipousuario" class="menulinks" title="Mantenimiento de tipos de usuarios">      
                                            Tipos de Usuario
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li>                                                 
                        </ul>                        
                    </li>
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                            Personal
                        </a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/usuario?proceso=renovacion" class="menulinks" title="Renvación de contraseña">
                                    Cambio de Contraseña
                                </a>
                            </li>
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/mensaje?proceso=nuevo" class="menulinks" title="Podrá enviar mensajes a otro usuario">
                                    Enviar Mensaje
                                </a>
                            </li>
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/notificacion?proceso=ver_notificaciones" class="menulinks" title="Ver tus notificaciones">
                                    Ver Notificaciones
                                </a>
                            </li>                       
                        </ul>   
                    </li>  
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                            Soporte
                        </a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="#" class="menulinks" title="Despliega el sub-menú">Respaldo</a>
                                <ul class="submenu">
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/soporte?proceso=backup" class="menulinks" title="Realizar un respaldo no planificado">
                                            Hacer un respaldo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/configuracion?proceso=actualizar_cr" class="menulinks" title="Configurar tarea respaldo">
                                            Configurar Tarea Respaldo
                                        </a>
                                    </li>
                                    <li class="sm_ulsubmenucontainer">
                                        <span class="icomenu"></span>
                                        <a href="%MAINSERVER%/configuracion?proceso=nuevo_cr" class="menulinks" title="Nueva configuración de tarea respaldo">
                                            Nueva configuración de Respaldo
                                        </a>
                                    </li>                                
                                </ul>                                
                            </li>
                        </ul>
                    </li>  
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                            Ayuda
                        </a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/manual" class="menulinks" title="Acceder al manual">
                                    Manual de Usuario
                                </a>
                            </li> 
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/mapa" class="menulinks" title="Mapa del sitio">
                                    Mapa del Sitio
                                </a>
                            </li> 
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/contacto" class="menulinks" title="Contactenos">
                                    Contacto
                                </a>
                            </li> 
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/faq" class="menulinks" title="Preguntas Frecuentes">
                                    Preguntas Frecuentes
                                </a>
                            </li>                         
                        </ul>   
                    </li>  
                    <li class="sm_ulmenucontainer has-sub">
                        <span class="icomenu"></span>
                        <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                            Informaciones
                        </a>
                        <ul class="submenu">
                            <li class="sm_ulsubmenucontainer">
                                <span class="icomenu"></span>
                                <a href="%MAINSERVER%/acerca" class="menulinks" title="Acceder al manual">
                                    Acerca de ...
                                </a>
                            </li>                         
                        </ul>   
                    </li>                  
                </ul>
            </nav>-->
    </body>
</html>
