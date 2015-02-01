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
        <link rel="stylesheet" href="../../css/style_menuprincipal2.css">
    </head>
    <body>
        <nav>
            <ul id="sm_navmenuprincipal">
                <li class="sm_ulmenucontainer">
                    <span class="icomenu"></span>
                    <a href="%MAINSERVER%" class="menulinks" title="Información del usuario registrado en el sistema">
                        Pagina Inicial
                    </a>
                </li>
                <li class="sm_ulmenucontainer">
                    <span class="icomenu"></span>
                    <a href="" class="menulinks" title="Módulos del sistema">
                        Módulos del Sistema
                    </a>
                    <ul class="submenu">
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Activos</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/activo?proceso=listado" class="menulinks" title="Lista de activos para: modificar,desactivar... activos">
                                        Listado de Activos
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/activo?proceso=nuevo" class="menulinks" title="Ingreso de un nuevo activo">
                                        Registro Nuevo Activo
                                    </a>
                                </li>
                            </ul>                                
                        </li>
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Bajas de Activos</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/baja?proceso=listado" class="menulinks" title="Lista de bajas">
                                        Listado de Bajas
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/baja?proceso=nuevo" class="menulinks" title="Registrar una baja">
                                        Registro Nueva Baja
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/baja?proceso=solicitud_baja" class="menulinks" title="Ver solicitudes de baja">
                                        Solicitudes de  Baja
                                    </a>
                                </li>                                
                            </ul>                                
                        </li> 
                        <li class="sm_ulsubmenucontainer">
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
                                        Registro Nuevo Proveedor
                                    </a>
                                </li>
                            </ul>                                
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Usuarios</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/usuario?proceso=listado" class="menulinks" title="Lista de usuarios">
                                        Listado de Usuarios
                                    </a>
                                </li>
                            </ul>                                
                        </li>  
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Solicitudes</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/solicitudbaja?proceso=nuevo" class="menulinks" title="Solicitar baja de activo">
                                        Solicitud de baja de activo
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/proveedor?proceso=nuevo" class="menulinks" title="Solicitar reparación">
                                        Solicitud Reparación
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/solicitudbaja?proceso=listado" class="menulinks" title="Ver mis solicitudes de baja">
                                        Mis Solicitudes Baja
                                    </a>
                                </li>                                
                            </ul>                                
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Reparaciones</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/reparacion?proceso=listado" class="menulinks" title="Lista de reparaciones">
                                        Listado de Reparaciones
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/reparacion?proceso=nuevo" class="menulinks" title="Ingreso de un nuevo proveedor">
                                        Registro Nueva Reparación
                                    </a>
                                </li>
                            </ul>                                
                        </li>   
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Despliega el sub-menú">Ventas</a>
                            <ul class="submenu">
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/venta?proceso=listado" class="menulinks" title="Lista de reparaciones">
                                        Listado de Ventas
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/venta?proceso=nuevo" class="menulinks" title="Ingreso de un nuevo proveedor">
                                        Registro Nueva Venta
                                    </a>
                                </li>
                            </ul>                                
                        </li>                           
                    </ul>                        
                </li>
                <li class="sm_ulmenucontainer">
                    <span class="icomenu"></span>
                    <a href="" class="menulinks" title="Módulo sistema">Sistema</a>
                    <ul class="submenu">
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Desplegar para ver opciones">Extras</a>
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
                                        Estados
                                    </a>
                                </li>
                                <li class="sm_ulsubmenucontainer">
                                    <span class="icomenu"></span>
                                    <a href="%MAINSERVER%/tipoactivo" class="menulinks" title="Mantenimiento de tipos de activo">
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
                <li class="sm_ulmenucontainer">
                    <span class="icomenu"></span>
                    <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                        Usuario
                    </a>
                    <ul class="submenu">
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="%MAINSERVER%/mensaje?proceso=nuevo" class="menulinks" title="Podrá enviar mensajes a otro usuario">
                                Enviar Mensaje
                            </a>
                        </li>
                    </ul>   
                </li>  
                <li class="sm_ulmenucontainer">
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
                            </ul>                                
                        </li>
                    </ul>
                </li>  
                <li class="sm_ulmenucontainer">
                    <span class="icomenu"></span>
                    <a href="#" class="menulinks" title="Despliega el menú para ver opciones">
                        Ayuda
                    </a>
                    <ul class="submenu">
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Acceder al manual">
                                Manual de Usuario
                            </a>
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="#" class="menulinks" title="Informaciones Técnicas">
                                Informaciones Técnicas
                            </a>
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="%MAINSERVER%/acerca" class="menulinks" title="Acerca del Sistema">
                                Acerca de...
                            </a>
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="%MAINSERVER%/mapa" class="menulinks" title="Mapa del Sitio">
                                Mapa del Sitio
                            </a>
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="%MAINSERVER%/faq" class="menulinks" title="Preguntas Frecuentes">
                                Preguntas Frecuentas (FAQ)
                            </a>
                        </li> 
                        <li class="sm_ulsubmenucontainer">
                            <span class="icomenu"></span>
                            <a href="%MAINSERVER%/contacto" class="menulinks" title="Contactar a los creadores">
                                Contacto
                            </a>
                        </li>                         
                    </ul>   
                </li>                 
            </ul>
        </nav>

        <h1>Hello World!</h1>
    </body>
</html>
