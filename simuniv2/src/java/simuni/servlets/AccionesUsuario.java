package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorBitacora;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.clases.ln.ManejadorUsuario;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.Respuesta;
import simuni.entidades.Usuario;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Tipo de Pago</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesUsuario extends HttpServlet {

    /**
     * Esta enumeración es particular al servelet para poder hacer mas facil y
     * exacto el control de operaciones solicitadas. Entre las operaciones
     * comunes que se solicitan estan agregar, modificar, eliminar, hacer un
     * query de busqueda y tambien hacer el listado por defecto que hay de los
     * datos ingresados. Si el usuario no elige una de las operaciones de la
     * enumeración por defecto se hara la operacion listado.
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    enum OpcionesDo {

        Reactivar_Usuario, Reseteo_Clave, Listado, Nuevo, Eliminar,
        Modificar, Query, AccionDefault, Login, Logout, Permisos, Actualizacion_Clave,
        Query_Inactivos
    }

    /**
     * Se encarga de clasificar la operación solicitada por el cliente.
     *
     * @param key el valor enviado por el cliente.
     * @return Un elemento de la enumeración OpcionesDo
     * @since 1.0
     */
    private OpcionesDo getOpcion(String key) {
        if (key == null || key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("listado")) {
            return OpcionesDo.Listado;
        } else if (key.equals("nuevo")) {
            return OpcionesDo.Nuevo;
        } else if (key.equals("eliminar")) {
            return OpcionesDo.Eliminar;
        } else if (key.equals("actualizar")) {
            return OpcionesDo.Modificar;
        } else if (key.equals("query")) {
            return OpcionesDo.Query;
        } else if (key.equals("login")) {
            return OpcionesDo.Login;
        } else if (key.equals("logout")) {
            return OpcionesDo.Logout;
        } else if (key.equals("permisos")) {
            return OpcionesDo.Permisos;
        } else if (key.equals("renovacion")) {
            return OpcionesDo.Actualizacion_Clave;
        } else if (key.equals("resetear")) {
            return OpcionesDo.Reseteo_Clave;
        } else if (key.equals("reactivar")) {
            return OpcionesDo.Reactivar_Usuario;
        } else if (key.equals("query_inactivos")) {
            return OpcionesDo.Query_Inactivos;
        }

        return OpcionesDo.AccionDefault;
    }

    /**
     * Maneja las solicitudes HTTP <code>GET</code>.
     *
     * @param request la solicitud al servlet
     * @param response la respuesta del servlet
     * @throws ServletException Si ocurre una excepción en el servlet
     * @throws IOException Si ocurre una excepción de I/O
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher disp = null;
        try {
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String registro = "";
            ManejadorUsuario musuario = new ManejadorUsuario();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            Usuario usuario = null;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    request.setAttribute("departamentos", musuario.listadoDepartamento());
                    request.setAttribute("tiposusuario", musuario.listadoTipoUsuario());
                    request.setAttribute("puestos", musuario.listadoPuesto());
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_nuevo.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuario(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/index.jsp");
                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Reactivar_Usuario:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuarioInactivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/index_inactivos.jsp");
                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistrosInactivos(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    registro = request.getParameter("registro");
                    usuario = musuario.obtenerUsuario(registro);
                    // System.out.println("registoooooooooo "+registro);
                    request.setAttribute("registro", usuario);
                    request.setAttribute("departamentos", musuario.listadoDepartamento());
                    request.setAttribute("tiposusuario", musuario.listadoTipoUsuario());
                    request.setAttribute("puestos", musuario.listadoPuesto());
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_editar.jsp");
                    break;
                case Eliminar:
                    //obtener el activo
                    registro = request.getParameter("registro");
                    usuario = musuario.obtenerUsuario(registro);
                    // System.out.println("registoooooooooo "+registro);
                    request.setAttribute("registro", usuario);
                    request.setAttribute("departamentos", musuario.listadoDepartamento());
                    request.setAttribute("tiposusuario", musuario.listadoTipoUsuario());
                    request.setAttribute("puestos", musuario.listadoPuesto());
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuario(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/index.jsp");
                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Logout:
                    request.getSession().setAttribute("USERNAME", null);
                    request.getSession().setAttribute("TIPOUSUARIO", null);
                    request.getSession().setAttribute("HORAINICIO", null);
                    request.getSession().setAttribute("LOGINPAGE", null);
                    response.getWriter().print("<script>window.location.assign('" + Recursos.Servers.MAINSERVER + "/login')</script>");
                    return;
                case Permisos:
                    request.setAttribute("usuarios", musuario.listadoUsuarios_Permisos());
                    disp = request.getRequestDispatcher("/modulos/usuarios/permisos.jsp");
                    break;
                case Reseteo_Clave:
                    request.setAttribute("usuarios", musuario.listadoUsuarios_Permisos());
                    disp = request.getRequestDispatcher("/modulos/usuarios/reseteo_clave.jsp");
                    break;
                case Actualizacion_Clave:
                    disp = request.getRequestDispatcher("/modulos/usuarios/cambio_clave.jsp");
                    break;

            }
            disp.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a  pagina de error de sistema
        }
    }

    /**
     * Maneja las solicitudes HTTP <code>POST</code>.
     *
     * @param request solicitud al servelet
     * @param response respuesta del servlet
     * @throws ServletException si ocurre una excepción en el servleet
     * @throws IOException si ocurre un error de I/O
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher disp = null;//para envio de respuestas y redirecciones 
        try {
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            String registro = "";//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            ManejadorUsuario musuario = new ManejadorUsuario();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;
            Usuario usuario = null;
            

            ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
            RegistroBitacora registroBitacora;
            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
            Notificacion notificacion = new Notificacion();
            ManejadorNotificaciones mnotif = new ManejadorNotificaciones();
            
            
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:

                    Usuario nuevousuario = generarUsuario(request);
                    respuesta = musuario.registrarUsuario(nuevousuario);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("nuevoregistro", nuevousuario);
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_nuevo.jsp");
                    

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se registra un usuario " + usuario.getNombreusuario(),
                            "Se registra un usuario.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha registrado a " + usuario.getNombreusuario()+" como usuario");
                    mnotif.agregarNNotificacion(notificacion);
                    
                    disp.forward(request, response);
                    break;
                case Modificar:

                    if (request.getParameter("registro") != null) {
                        usuario = generarUsuario(request);
                        respuesta = musuario.modificarUsuario(usuario);
                        request.setAttribute("registro", usuario);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_editar.jsp");
                        

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se modifica un usuario " + usuario.getNombreusuario(),
                            "Se modifica  un usuario.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha modificado  a " + usuario.getNombreusuario()+" como usuario");
                    mnotif.agregarNNotificacion(notificacion);                        
                        disp.forward(request, response);
                    }
                    break;
                case Eliminar:
                    if (request.getParameter("registro") != null) {
                        registro = request.getParameter("registro");
                        usuario = new Usuario();
                        usuario.setNombreusuario(registro);
                        respuesta = musuario.eliminarUsuario(usuario);
                        usuario = musuario.obtenerUsuario(registro);
                        request.setAttribute("registro", usuario);
                        request.setAttribute("respuesta", respuesta);
                        request.setAttribute("departamentos", musuario.listadoDepartamento());
                        request.setAttribute("tiposusuario", musuario.listadoTipoUsuario());
                        request.setAttribute("puestos", musuario.listadoPuesto());
                        disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_eliminar.jsp");
                        

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se elimina  un usuario " + usuario.getNombreusuario(),
                            "Se elimina  un usuario.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha eliminado  a " + usuario.getNombreusuario()+" como usuario");
                    mnotif.agregarNNotificacion(notificacion);   
                    
                        disp.forward(request, response);
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuario(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_listar.jsp");

                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistros(query) / paginacion) + 1);
                    disp.forward(request, response);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuario(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/index.jsp");
                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    disp.forward(request, response);
                    break;
                case Login:

                    String nombreusuario = request.getParameter("txtNombreUsuario");
                    String contrasena = request.getParameter("txtPassword");

                    if (nombreusuario == null || contrasena == null) {
                        request.getSession().setAttribute("USERNAME", null);
                        request.getSession().setAttribute("TIPOUSUARIO", null);
                        request.getSession().setAttribute("HORAINICIO", null);
                        response.getWriter().print("<script>window.location.assign('" + Recursos.Servers.MAINSERVER + "/login')</script>");

                    } else {
                        ManejadorUsuario manejadorusuarios = new ManejadorUsuario();
                        usuario = manejadorusuarios.login(nombreusuario, contrasena);
                        if (usuario == null) {
                            request.getSession().setAttribute("USERNAME", null);
                            request.getSession().setAttribute("TIPOUSUARIO", null);
                            request.getSession().setAttribute("HORAINICIO", null);
                            response.getWriter().print("<script>window.location.assign('" + Recursos.Servers.MAINSERVER + "/login')</script>");

                        } else {
                            request.getSession().setAttribute("USERNAME", usuario.getNombreusuario());
                            request.getSession().setAttribute("TIPOUSUARIO", usuario.getTipousuario());
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            request.getSession().setAttribute("HORAINICIO", sdf.format(new Date()));
                            request.getSession().setAttribute("LOGINPAGE", null);
                            response.getWriter().print("<script>window.location.assign('" + Recursos.Servers.MAINSERVER + "')</script>");

                        }
                    }

                    break;
                case Actualizacion_Clave:
                    usuario = generarUsuario(request);
                    String contrasena_actual = request.getParameter("txtpassactual");
                    respuesta = musuario.modificarClaveUsuario(usuario, contrasena_actual);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/usuarios/cambio_clave.jsp");
                    disp.forward(request, response);
                    break;
                case Reseteo_Clave:
                    usuario = generarUsuario(request);
                    respuesta = musuario.resetearClaveUsuario(usuario);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("usuarios", musuario.listadoUsuarios_Permisos());
                    request.setAttribute("idusuario", usuario.getNombreusuario());
                    disp = request.getRequestDispatcher("/modulos/usuarios/reseteo_clave.jsp");
                    disp.forward(request, response);
                    break;
                case Query_Inactivos:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = musuario.busquedaUsuarioInactivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_listar_inactivos.jsp");

                    request.setAttribute("paginacion", ((int) musuario.getCantidadRegistrosInactivos(query) / paginacion) + 1);
                    disp.forward(request, response);
                    break;
                case Reactivar_Usuario:
                    if (request.getParameter("registro") != null) {
                        registro = request.getParameter("registro");
                        usuario = new Usuario();
                        usuario.setNombreusuario(registro);
                        System.out.println("El que se va a reacviar es " + registro);
                        musuario.reactivarUsuario(usuario);
                        response.getWriter().print("Usuario Reactivado");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se reactiva  un usuario " + usuario.getNombreusuario(),
                            "Se reactiva  un usuario.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha reactiva  a " + usuario.getNombreusuario()+" como usuario");
                    mnotif.agregarNNotificacion(notificacion);                           
                        
                    }
                    break;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a pagian de error de sistema
        }
    }

    /**
     * Función que permite generar un objeto Usuario a partir de la solicitud
     * que realiza el usuario a nivel web y que el servidor recibe.
     *
     * @param request la solicitud del usuario a descodificar.
     * @return un objeto Usuario a partir de la solicitud.
     * @since 1.0
     */
    private Usuario generarUsuario(HttpServletRequest request) {
        Usuario usuario = new Usuario();
        try {
            usuario.setCedula(request.getParameter("txtcedulausuario"));
            usuario.setNombre(request.getParameter("txtnombre"));
            usuario.setApellido1(request.getParameter("txtapellido1"));
            usuario.setApellido2(request.getParameter("txtapellido2"));
            usuario.setEmail(request.getParameter("txtemail"));
            usuario.setAreatrabajo(UtilidadesServlet.getInt(request.getParameter("cmbdepartamento"), 0));
            usuario.setCodigoPuesto(UtilidadesServlet.getInt(request.getParameter("cmbpuesto"), 0));
            usuario.setTipousuario(UtilidadesServlet.getInt(request.getParameter("cmbtipousuario"), 0));
            usuario.setNombreusuario(request.getParameter("txtnombreusuario_l"));
            usuario.setContrasena(request.getParameter("txtpassword1"));
            usuario.setContrasena2(request.getParameter("txtpassword2"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return usuario;
    }
}
