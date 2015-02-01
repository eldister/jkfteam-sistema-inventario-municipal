<%@page import="simuni.enums.Recursos"%>
<%@page import="simuni.entidades.Usuario"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="simuni.entidades.Notificacion"%>
<%@page import="simuni.clases.ln.ManejadorNotificaciones"%>
<%@page import="simuni.clases.ln.ManejadorUsuario"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="decorator" uri="http://claudiushauptmann.com/jsp-decorator/" %>
<%
    String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
    String tipousuario = request.getSession().getAttribute("TIPOUSUARIO") == null ? null : request.getSession().getAttribute("TIPOUSUARIO").toString();
    String loginpage = request.getSession().getAttribute("LOGINPAGE") == null ? null : request.getSession().getAttribute("LOGINPAGE").toString();
    String horainicio = request.getSession().getAttribute("HORAINICIO") == null ? null : request.getSession().getAttribute("HORAINICIO").toString();
    String error = request.getSession().getAttribute("ERROR") == null ? null : request.getSession().getAttribute("ERROR").toString();
    Usuario user = new Usuario();
    if ((idusuario == null || tipousuario == null) && (loginpage == null && error == null)) {
        out.print("<script>window.location.replace('/simuniv2/login');</script>");
        return;
    }
    if (loginpage == null && error == null) {
        ManejadorUsuario manejadorusuarios = new ManejadorUsuario();
        user = manejadorusuarios.getMenuUsuario(idusuario);
    }
%>
<!DOCTYPE HTML>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="icon" type="image/png" href="<%=request.getContextPath()%>/recursos/imagenes/favico/favicon.ico"/>
        <title> <decorator:placeholder name='sm_section_titulodepagina'/></title>
        <script src="<%=request.getContextPath()%>/js/script_jquery-2.1.1.min.js"></script>
        <script src="<%=request.getContextPath()%>/js/script_jquery-ui.js"></script>    
        <script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>     
        <script src="<%=request.getContextPath()%>/js/script_chesdev_userbox_v1.js"></script>
        <script src="<%=request.getContextPath()%>/js/script_main.js"></script>
        <script src="<%=request.getContextPath()%>/js/script_utils.js"></script>
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/temas/jquery_smoothness/style_jquery-ui.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style_main.css">
        <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style_menuprincipal2.css">
        <script>
            SIMUNI_SERVER = "<%out.print(Recursos.Servers.MAINSERVER);%>";
            $(document).ready(function() {
                var desplazado = 220;
                var duracion = 500;
             //alert($("#volverTop").html())
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
                initMensajesNotificaciones();
            });
        </script>

        <decorator:placeholder name='sm_section_estilosyscriptssectioncontainer'/>
    </head>
    <body>
        <div id="maincontainer">
            <div class="SIMUNI_ROW">
                <a name="sm_body_headanchor"></a>
                <header>
                    <div id="sm_header_bannerandusercontainer">
                        <div class="sm_header_bannercontainer">
                            <div id="sm_div_logomunicontainer">
                                <a href="http://www.nandayure.go.cr/" title="Acceder a la página oficial de la municipalidad" target="_blank">
                                    <div id="sm_div_logomuni">
                                        <img src="/simuniv2/recursos/imagenes/sistema/sm_logo_muni.jpg" width="100" height="100">
                                    </div>
                                </a> 
                            </div>
                            <div id="sm_div_logosimunicontainer">
                                <a href="<%out.print(Recursos.Servers.MAINSERVER);%>" id="enlaceInicio" title="Ir a la página de Inicio">
                                    <div id="sm_div_logosimuni">
                                        <img  src="/simuniv2/recursos/imagenes/sistema/sm_logo_simuni.png" width="100" height="100">
                                        <span id="sm_div_simunislogan">Sistema de Información para el Control de Inventario Municipal</span>
                                    </div>
                                </a>

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
                <%
                        String menu_usuario= user.getMenuusuario(); 
                        if(menu_usuario!=null){
                            menu_usuario=menu_usuario.replace("%MAINSERVER%", Recursos.Servers.MAINSERVER.toString());
                        }
                        out.print(menu_usuario);
                 } %>
                </header>  
            </div>
            <div class="SIMUNI_ROW SIMUNI_MAINROW"> 
                <%  if (loginpage == null && error == null) {%>
                <div class="SIMUNI_COL SIMUNI_LATERALMENU">
                    <aside id="sm_body_barralateral" >
                        <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemnotificacion">
                            <fieldset>
                                <legend>Notificaciones</legend>
                                <div class="sm_fieldset_notificacionescontainer" id="sm_notificacionescontainer">
                                   <strong>No tiene mensajes!</strong> 
                                </div>
                            </fieldset>
                        </div>
                        <div class="sm_aside_barralateralitem" id="sm_aside_barralateralitemmensaje">
                            <fieldset>
                                <legend>Mensajes</legend>
                                <div class="sm_fieldset_notificacionescontainer" id="sm_mensajescontainer">
                                    <strong>No tiene mensajes!</strong>                                                       
                                </div>
                            </fieldset>
                        </div>
                        <div class="sm_aside_barralateralitem">
                            <fieldset>
                                <legend>Configuraciones</legend>
                                <div class="sm_fieldset_notificacionescontainer">
                                    <div class="sm_div_edicionperfil">
                                        <a class="sm_div_configuracionlink" title="Editar tus datos" href="#">Perfil</a>
                                    </div>
                                    <div class="sm_div_ayudausuario">
                                        <a class="sm_div_configuracionlink" title="Acceder a la ayuda" href="#">Ayuda.</a>
                                    </div>
                                    <div class="sm_div_cerrarsesion">
                                        <a class="sm_div_configuracionlink" title="Cerrar Sesión actual"  href="/SIMUNI/AccionesUsuarios?proceso=logout">Salir</a>
                                    </div>
                                </div>
                            </fieldset>
                        </div>

                    </aside>
                </div>

                <% }%>
                <div class="SIMUNI_COL SIMUNI_PRINCIPAL">
                    <div id="sm_body_navegationbarmenu"><decorator:placeholder name='sm_div_navegationbarmenuitems'/></div>                     <section id="sm_body_mainsection">
                        <!--esta es la seccion prinicipal-->
                        <decorator:placeholder name='sm_section_mainsectioncontainer'/>

                    </section>
                </div>
            </div>
            <% if (loginpage == null && error == null) {%>
            <div class="SIMUNI_ROW">
                <section id="sm_body_notificacion">
                    <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Sesión iniciada : </span><% out.print(horainicio); %></div>
                    <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usuario:</span> <%out.print(idusuario);%></div>
                    <div class="sm_section_notificacioncontainer"><span class="sm_div_notiftag">Usted se ha conectado desde</span> <% out.print(request.getRemoteAddr());%> </div>
                </section> 
            </div>
            <%}%>
            <div class="SIMUNI_ROW">
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
                            <p>Copyright &#169 2015 <abbr title="Sistema de Información para el control de Activos Municipal">SIMUNI</abbr><span id="sm_div_footerprojecticon">&nbsp;</span></p>
                        </div>
                    </div>            
                </footer>
                <a href="#" id="volverTop" style="display: inline;"><img border="0" id="imgVolverArriba" src="<%=request.getContextPath()%>/recursos/imagenes/sistema/sm_volver_arriba.png" title="Ir arriba"/></a>
            </div>   
        </div>
    </body>
</html>
<%  request.getSession().setAttribute("ERROR", null);%>
<%  request.getSession().setAttribute("LOGINPAGE", null);%>