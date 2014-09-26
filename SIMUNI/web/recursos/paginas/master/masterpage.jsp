<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/" %>

<!DOCTYPE HTML>

<html>
    <head>
        <title></title>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-ui.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/componentes/chesdev_userbox_v1.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_main.js"></script>

        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_main.css">

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
                            out.print("<li class='sm_ul_menuitem'><a href=''>Your menu</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Your menu</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Your menu</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Your menu</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Your menu</a></li>");

                        } else {
                            out.print("<li class='sm_ul_menuitem'><a href=''>Permiso para 1</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Permiso para 2</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Permiso para3</a></li>");
                            out.print("<li class='sm_ul_menuitem'><a href=''>Permiso para 4</a></li>");

                        }
                    %>

                </ul>
            </nav>
        </header>  
        <aside id="sm_body_barralateral" >
            <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemnotificacion">
                <fieldset>
                    <legend>Notificaciones</legend>
                    <div id="sm_fieldset_notificacionescontainer">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo
                        </div>
                        <hr class="sm_div_separadorhr">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo
                        </div>
                        <hr class="sm_div_separadorhr">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                        </div>                        
                        <hr class="sm_div_separadorhr">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo
                        </div>
                        <hr class="sm_div_separadorhr">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo
                        </div>
                        <hr class="sm_div_separadorhr">
                        <div class="sm_div_notificacion">
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                            Notificacion primera que se hizo8787sfasdfasdfqwefaasa asdfasdf
                        </div>                        
                    </div>
                </fieldset>
            </div>
            <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemmensaje">
                <fieldset>
                    <legend>Mensajes</legend>
                    <div id="sm_fieldset_notificacionescontainer">
                        <div class="sm_div_mensaje">
                            Mensaje de user1  
                        </div>
                    </div>
                </fieldset>
            </div>
            <div class="sm_aside_barralateralitem">
                <fieldset>
                    <legend>Configuraciones</legend>
                    <div id="sm_fieldset_notificacionescontainer">
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
            <div class="sm_aside_barralateralitem">
                <fieldset>
                    <legend>Reloj</legend>

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
                    Patitos
                </a>
            </span>
            <span class="sm_div_navmenuseparator">
                /
            </span>            
        </div>
        <section id="sm_body_mainsection">
            esta es la seccion principal.
            <decorator:placeholder name='sm_section_mainsectioncontainer'/>
            
        </section>
        <section id="sm_body_notificacion">
            la barra de estado del sistema :D
        </section>        
        <footer>
            <div class="sm_footer_tablecontainer">
                <div class="sm_div_rowcontainer">
                    <div class="sm_div_colcontainer">
                        88
                    </div>
                    <div class="sm_div_colcontainer">
                        88
                    </div>  
                    <div class="sm_div_colcontainer">
                        9898
                    </div>                     
                </div>
                <div class="sm_div_rowcontainer" id="sm_div_copyrigth_container">
                    <p>Copyright 2015 SIMUNI</p>
                </div>
            </div>            

        </footer>
                    <a href="#uno">vaya arriba</a>
    </body>
</html>






