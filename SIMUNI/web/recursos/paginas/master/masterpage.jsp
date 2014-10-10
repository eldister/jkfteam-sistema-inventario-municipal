<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/" %>

<!DOCTYPE HTML>

<html>
    <head>
        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/recursos/imagenes/favico/favicon.ico"/>
        <title> <decorator:placeholder name='sm_section_titulodepagina'/></title>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-ui.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/componentes/chesdev_userbox_v1.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_main.js"></script>

        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/temas/jquery_smoothness/style_jquery-ui.css">
        <decorator:placeholder name='sm_section_estilosyscriptssectioncontainer'/>
    </head>
    <body>

        <a name="sm_body_headanchor"></a>
        <header>
            <div id="sm_header_bannerandusercontainer">
                <div class="sm_header_bannercontainer">
                    <div id="sm_div_logomunicontainer">&nbsp;
                        <div id="sm_div_logomuni">&nbsp;</div>                
                    </div>
                    <div id="sm_div_logosimunicontainer">
                        <div id="sm_div_logosimuni">
                            <span id="sm_div_simunislogan">Sistema de Información para el Control de Inventario Municipal&nbsp;</span>
                        </div>   

                    </div>
                </div>
                <div id="sm_header_userprofilecontainer">
                    <div id="sm_div_userprofile">
                        <div class="sm_div_tablecontainer">
                            <div class="sm_div_rowcontainer" id="sm_div_usersettings">
                                <div class="sm_div_colcontainer">Nombre de Usuario Aqui</div>
                                <div class="sm_div_colcontainer" id="sm_div_configuracion" >&nbsp;</div>           
                            </div>
                        </div>
                    </div>
                </div>                            
            </div>
            <nav id="sm_header_menuprincipal">
                <ul id="sm_nav_menuelementscontainer">
                    <%
                        if (session.getAttribute("username") == null) {
                            session.setAttribute("username", "jajaj");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Inicio</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Activos</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Proveedores</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Reportes</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Soporte</a></li>");

                        } else {
                            out.print("<li class='sm_ul_menuitem'><a href=''>Inicio</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Activos</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Usuarios</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Reportes</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Ayuda</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Area Personal</a></li>");

                        }
                    %>

                </ul>
            </nav>
        </header>  
        <aside id="sm_body_barralateral" >
            <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemnotificacion">
                <fieldset>
                    <legend>Notificaciones</legend>
                    <div class="sm_fieldset_notificacionescontainer">
                        <div class="sm_div_notificacion">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            <b>User3</b> Se ha agregado un nuevo registro de activo con el codigo 62254454646.
                        </div>  
                        <hr>
                        <div class="sm_div_notificacion">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            <b>User2</b> Se ha agregado un nuevo registro de activo con el codigo 52254454646.
                        </div>  

                        <hr>
                        <div class="sm_div_notificacion">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            <b>User1</b> Se ha agregado un nuevo registro de activo con el codigo 52254454646.
                        </div>                        

                        <hr>
                        <div class="sm_div_notificacion">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            <b>User1</b> ha sido bloqueado
                        </div>  

                        <hr>
                        <div class="sm_div_notificacion">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            Reporte de activos proximo a realizarse.
                        </div>                     
                    </div>
                </fieldset>
            </div>
            <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemmensaje">
                <fieldset>
                    <legend>Mensajes</legend>
                    <div class="sm_fieldset_notificacionescontainer">
                        <div class="sm_div_mensaje">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString()); %>
                                <hr>
                            </div>
                            Buenas, podria enviarme a alguien para revisar la compu
                        </div>
                        <hr>
                        <div class="sm_div_mensaje">
                            <div class="sm_div_notificacionfechacontainer">
                                <% out.print(new Date().toLocaleString());%>
                                <hr>
                            </div>
                            Buenas, podria enviarme a alguien para revisar la compu, rapido porfis
                        </div>                                
                    </div>
                </fieldset>
            </div>
            <div class="sm_aside_barralateralitem">
                <fieldset>
                    <legend>Configuraciones</legend>
                    <div class="sm_fieldset_notificacionescontainer">
                        <div class="sm_div_edicionperfil">
                            Actualizar datos de perfil.
                        </div>
                        <div class="sm_div_ayudausuario">
                            Acceder a la ayuda.
                        </div>
                        <div class="sm_div_cerrarsesion">
                            Cerrar Sesión
                        </div>
                    </div>
                </fieldset>
            </div>
        </aside>
        <div id="sm_body_navegationbarmenu">

            <span class="sm_div_navmenuitem">
                <a href="">
                    Home
                </a>
            </span>
            <span class="sm_div_navmenuseparator">
                /
            </span>
            <span class="sm_div_navmenuitem">
                <a href="">
                    Registro
                </a>
            </span>
            <span class="sm_div_navmenuseparator">
                /
            </span> 
            <hr id="sm_div_separadormenunavegacion">
        </div>
        <section id="sm_body_mainsection">
            <!--esta es la seccion prinicipal-->
            <decorator:placeholder name='sm_section_mainsectioncontainer'/>

        </section>
        <section id="sm_body_notificacion">
            <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Sesión iniciada : </span><% out.print(new Date().toLocaleString()); %></div>
            <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usuario:</span> user1</div>
            <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usted se ha conectado desde</span> <% out.print(request.getRemoteAddr()); %> </div>
        </section>        
        <footer>
            <div class="sm_footer_tablecontainer">
                <div class="sm_div_rowcontainer">

                    <div class="sm_div_colcontainer">
                        <b>Páginas</b>
                        <ul>
                            <li><a href="">Acerca de</a></li>
                            <li><a href="">Mapa del sitio</a></li>
                            <li><a href="">Mantenimiento Activos</a></li>

                        </ul>
                    </div>

                    <div class="sm_div_colcontainer">
                        <b>SIMUNI</b><br/>
                        <ul> 
                            <li><a href="">Acerca del proyecto</a></li>
                            <li><a href="">Desarrolladores</a></li>                     
                        </ul>
                    </div>  

                    <div class="sm_div_colcontainer">
                        <b>Ayuda</b><br/>
                        <ul>
                            <li><a href="">FAQ</a></li>
                            <li><a href="">Manual de usuario</a></li>
                            <li><a href="">Contacto</a></li>
                        </ul>
                    </div>                     
                </div>
                <div class="sm_div_rowcontainer" id="sm_div_copyrigth_container">
                    <p>Copyright &#169 2015 SIMUNI<span id="sm_div_footerprojecticon">&nbsp;</span></p>
                </div>
            </div>            

        </footer>
    </body>
</html>






