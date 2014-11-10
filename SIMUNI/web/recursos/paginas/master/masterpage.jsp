<%@page import="java.util.Iterator"%>
<%@page import="simuni.classes.LN.ManejadorNotificaciones"%>
<%@page import="simuni.classes.EN.Notificacion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="simuni.classes.LN.ManejadorUsuarios"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/" %>
<%
    String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
    String tipousuario = request.getSession().getAttribute("TIPOUSUARIO") == null ? null : request.getSession().getAttribute("TIPOUSUARIO").toString();
    String loginpage = request.getSession().getAttribute("LOGINPAGE") == null ? null : request.getSession().getAttribute("LOGINPAGE").toString();
    String horainicio = request.getSession().getAttribute("HORAINICIO") == null ? null : request.getSession().getAttribute("HORAINICIO").toString();
    String error = request.getSession().getAttribute("ERROR") == null ? null : request.getSession().getAttribute("ERROR").toString();
    String menuusuario = "";
    //   idusuario="fCoulon";
    //    tipousuario="1";
    if ((idusuario == null || tipousuario == null) && (loginpage == null && error == null)) {
        out.print("<script>window.location.replace('/SIMUNI/login.jsp');</script>");
        return;

    }
    if (loginpage == null && error == null) {
        ManejadorUsuarios manejadorusuarios = new ManejadorUsuarios();
        menuusuario = manejadorusuarios.getMenuUsuario(idusuario);

    }

%>

<!DOCTYPE HTML>

<html>
    <head>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/recursos/imagenes/favico/favicon.ico"/>
        <title> <decorator:placeholder name='sm_section_titulodepagina'/></title>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/jquery-ui.js"></script>     
        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/temas/jquery_smoothness/style_jquery-ui.css">
        <script src="<%=request.getContextPath()%>/recursos/scripts/componentes/chesdev_userbox_v1.js"></script>
        <script src="<%=request.getContextPath()%>/recursos/scripts/js_paginas/script_main.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/recursos/estilos/style_menuprincipal.css">


        <script>
            $(document).ready(function() {
                var desplazado = 220;
                var duracion = 500;

                $("#volverTop").fadeOut(0);

                $("#volverTop").fadeOut(0);

                $(window).scroll(function() {
                    if ($(this).scrollTop() > desplazado) {
                        $("#volverTop").fadeIn(duracion);
                    } else {
                        $("#volverTop").fadeOut(duracion);
                    }
                });

                $("#volverTop").click(function(event) {
                    event.preventDefault();
                    $("html, body").animate({scrollTop: 0}, duracion);
                    return false;
                });
            });
        </script>

        <decorator:placeholder name='sm_section_estilosyscriptssectioncontainer'/>
    </head>
    <body>
        <div id="maincontainer">
            <a name="sm_body_headanchor"></a>
            <header>
                <div id="sm_header_bannerandusercontainer">
                    <div class="sm_header_bannercontainer">
                        <div id="sm_div_logomunicontainer">&nbsp;
                            <a href="http://www.nandayure.go.cr/" target="_blank"><div id="sm_div_logomuni">&nbsp;</div></a> 
                        </div>
                        <div id="sm_div_logosimunicontainer">
                            <a href="/SIMUNI" id="enlaceInicio"><div id="sm_div_logosimuni">
                                    <span id="sm_div_simunislogan">Sistema de Información para el Control de Inventario Municipal&nbsp;</span>
                                </div></a>

                        </div>
                    </div>  
                    <%  if (loginpage == null && error == null) {%>
                    <div id="sm_header_userprofilecontainer">
                        <div id="sm_div_userprofile">
                            <div class="sm_div_tablecontainer">
                                <div class="sm_div_rowcontainer" id="sm_div_usersettings">
                                    <div class="sm_div_colcontainer"><%out.print(idusuario);%></div>
                                    <div class="sm_div_colcontainer" id="sm_div_configuracion" >&nbsp;</div>           
                                </div>
                            </div>
                        </div>
                    </div>                            
                </div>
                <%out.print(menuusuario);
                } %>
            </header>  
            <%  if (loginpage == null && error == null) {%>
            <aside id="sm_body_barralateral" >
                <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemnotificacion">
                    <fieldset>
                        <legend>Notificaciones</legend>
                        <div class="sm_fieldset_notificacionescontainer" id="sm_notificacionescontainer">
                            <%
                                ManejadorNotificaciones manejadornotif = new ManejadorNotificaciones();
                                ArrayList<Notificacion> notificaciones = manejadornotif.obtenerNotificacionesUsuario(idusuario);
                                if (notificaciones == null) {
                                    out.print("<strong>No tiene notificaciones!</strong>");
                                } else {
                                    Iterator<Notificacion> iter = notificaciones.iterator();
                                    if (iter.hasNext()) {
                                        while (iter.hasNext()) {
                                            Notificacion notificacion = iter.next();
                                            out.print("<div class='sm_div_notificacion'>");
                                            out.print("<div class='sm_div_notificacionfechacontainer'>");
                                            out.print(notificacion.getFechaNotificacion().toGMTString());
                                            out.print("        <hr>");
                                            out.print("      </div>");
                                            out.print("<strong>");
                                            out.print(notificacion.getUsuarioOrigen());
                                            out.print("</strong>");
                                            out.print(notificacion.getDescripcionNotificacion());
                                            out.print("</div> ");
                                            out.print("<hr>");
                                        }
                                    } else {
                                        out.print("<strong>No tiene mensajes!</strong>");
                                    }

                                }
                            %>
                        </div>
                    </fieldset>
                </div>
                <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemmensaje">
                    <fieldset>
                        <legend>Mensajes</legend>
                        <div class="sm_fieldset_notificacionescontainer" id="sm_mensajescontainer">
                            <%
                                notificaciones = manejadornotif.obtenerMensajesUsuario(idusuario);
                                if (notificaciones == null) {
                                    out.print("<strong>No tiene mensajes!</strong>");
                                } else {
                                    Iterator<Notificacion> iter = notificaciones.iterator();
                                    if (iter.hasNext()) {
                                        while (iter.hasNext()) {
                                            Notificacion notificacion = iter.next();
                                            out.print("<div class='sm_div_mensaje'>");
                                            out.print("<div class='sm_div_notificacionfechacontainer'>");
                                            out.print(notificacion.getFechaNotificacion().toLocaleString());
                                            out.print("        <hr>");
                                            out.print("      </div>");
                                            out.print("<strong>");
                                            out.print(notificacion.getUsuarioOrigen());
                                            out.print("</strong>");
                                            out.print(notificacion.getDescripcionNotificacion());
                                            out.print("</div> ");
                                            out.print("<hr>");
                                        }
                                    } else {
                                        out.print("<strong>No tiene mensajes!</strong>");
                                    }

                                }
                            %>                                                           
                        </div>
                    </fieldset>
                </div>
                <div class="sm_aside_barralateralitem">
                    <fieldset>
                        <legend>Configuraciones</legend>
                        <div class="sm_fieldset_notificacionescontainer">
                            <div class="sm_div_edicionperfil">
                                <a class="sm_div_configuracionlink" title="Editar tus datos" href="#">Actualizar datos de perfil.</a>
                            </div>
                            <div class="sm_div_ayudausuario">
                                <a class="sm_div_configuracionlink" title="Acceder a la ayuda" href="#"> Acceder a la ayuda.</a>
                            </div>
                            <div class="sm_div_cerrarsesion">
                                <a class="sm_div_configuracionlink" title="Cerrar Sesión actual"  href="/SIMUNI/AccionesUsuarios?proceso=logout">Cerrar Sesión</a>
                            </div>
                        </div>
                    </fieldset>
                </div>
            </aside>

            <div id="sm_body_navegationbarmenu">
                <decorator:placeholder name='sm_div_navegationbarmenuitems'/>

            </div> 
            <% }%>
            <section id="sm_body_mainsection">
                <!--esta es la seccion prinicipal-->
                <decorator:placeholder name='sm_section_mainsectioncontainer'/>

            </section>
            <% if (loginpage == null && error == null) {%>
            <section id="sm_body_notificacion">
                <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Sesión iniciada : </span><% out.print(horainicio); %></div>
                <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usuario:</span> <%out.print(idusuario);%></div>
                <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usted se ha conectado desde</span> <% out.print(request.getRemoteAddr());%> </div>
            </section> 
            <%}%>
            <footer>
                <div class="sm_footer_tablecontainer">
                    <div class="sm_div_rowcontainer">

                        <div class="sm_div_colcontainer">
                            <b>Páginas</b>
                            <ul>
                                <li><a href="/SIMUNI/acercade.jsp">Acerca de</a></li>
                                <li><a href="#" title="Ver el sistema desde arriba">Mapa del sitio</a></li>
                                <li><a href="#" title="#">Mantenimiento Activos</a></li>

                            </ul>
                        </div>

                        <div class="sm_div_colcontainer">
                            <b>SIMUNI</b><br/>
                            <ul> 
                                <li><a href="/SIMUNI/acercade.jsp" title="Información del proyecto">Acerca del proyecto</a></li>
                                <li><a href="#" title="Creadores del sistema">Desarrolladores</a></li>                     
                            </ul>
                        </div>  

                        <div class="sm_div_colcontainer">
                            <b>Ayuda</b><br/>
                            <ul>
                                <li><a href="" title="Preguntas Frecuentes">FAQ</a></li>
                                <li><a href="#" title="Ir al manual">Manual de usuario</a></li>
                                <li><a href="/SIMUNI/contacto.jsp" title="Contactar al administrador">Contacto</a></li>
                            </ul>
                        </div>                     
                    </div>
                    <div class="sm_div_rowcontainer" id="sm_div_copyrigth_container">
                        <p>Copyright &#169 2015 SIMUNI<span id="sm_div_footerprojecticon">&nbsp;</span></p>
                    </div>
                </div>            
            </footer>
            <a href="#" id="volverTop" style="display: inline;"><img border="0" id="imgVolverArriba" src="<%=request.getContextPath()%>/recursos/imagenes/sistema/sm_volver_arriba.png" title="Ir arriba"/></a>
        </div>   
    </body>
</html>
<%  request.getSession().setAttribute("ERROR", null);%>
<%  request.getSession().setAttribute("LOGINPAGE", null);%>


