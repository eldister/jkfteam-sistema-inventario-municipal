package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorBitacora;
import simuni.clases.ln.ManejadorConfiguracion;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.entidades.Respuesta;
import simuni.entidades.Configuracion;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Configuracion</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesConfiguracion extends HttpServlet {

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

        Listado, Nuevo, Eliminar, Modificar, Query, AccionDefault, Modificar_ConfiguracionRespaldo, Nuevo_ConfiguracionRespaldo
    }

    /**
     * Se encarga de clasificar la operación solicitada por el cliente.
     *
     * @param key el valor enviado por el cliente.
     * @return Un elemento de la enumeración OpcionesDo
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
        } else if (key.equals("actualizar_cr")) {
            return OpcionesDo.Modificar_ConfiguracionRespaldo;
        } else if (key.equals("nuevo_cr")) {
            return OpcionesDo.Nuevo_ConfiguracionRespaldo;
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
            int registro = 0;
            Configuracion configuracion = null;
            ManejadorConfiguracion mconfiguracion = new ManejadorConfiguracion();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
            RegistroBitacora registroBitacora;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo_ConfiguracionRespaldo:
                    disp = request.getRequestDispatcher("/modulos/configuraciones/nuevo_cr.jsp");

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Solicitud de formulario para crear una nueva configuración",
                            "Se solicita formulario para nueva configuración");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;

                case Modificar_ConfiguracionRespaldo:
                    //obtener el activo

                    registro = UtilidadesServlet.getInt(request.getParameter("registro"), -1);

                    configuracion = mconfiguracion.getConfiguracionRespaldo(registro);
                    request.setAttribute("registro", configuracion);

                    disp = request.getRequestDispatcher("/modulos/configuraciones/editar_cr.jsp");

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Solicitud de formulario para edigar una  configuración de sistema",
                            "Se solicita formulario para  configuración de sistema");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        configuracion = mconfiguracion.getConfiguracionRespaldo(registro);
                        request.setAttribute("registro", configuracion);
                    }
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Solicitud de formulario para eliminar una  configuración de sistema",
                            "Se solicita formulario para eliminar configuración de sistema");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    disp = request.getRequestDispatcher("/modulos/configuraciones/eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    //resultset = mconfiguracion.busquedaConfiguracion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/configuraciones/index.jsp");
                    //  request.setAttribute("paginacion", ((int) mconfiguracion.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Acccion default configuración de sistema",
                            "configuración sistema default");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
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
            int registro = 0;//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            ManejadorConfiguracion mconfiguracion = new ManejadorConfiguracion();
            String query = request.getParameter("query");
            Configuracion configuracion = null;
            query = query == null ? "" : query;
            ResultSet resultset = null;

            ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
            RegistroBitacora registroBitacora;
            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
            Notificacion notificacion = new Notificacion();
            ManejadorNotificaciones mnotif = new ManejadorNotificaciones();

            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo_ConfiguracionRespaldo:

                    Configuracion nuevaconfiguracion = generarConfiguracionRespaldoBD(request);
                    respuesta = mconfiguracion.registrarConfiguracionRespaldoBD(nuevaconfiguracion);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("registro", nuevaconfiguracion);

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se registra una nueva configuración de respaldo del sistema ",
                            "Se registra nueva configuración");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha registrado una nueva configuración del sistema!.");
                    mnotif.agregarNNotificacion(notificacion);

                    disp = request.getRequestDispatcher("/modulos/configuraciones/nuevo_cr.jsp");
                    break;
                case Modificar_ConfiguracionRespaldo:
                    configuracion = generarConfiguracionRespaldoBD(request);
                    respuesta = mconfiguracion.actualizarConfiguracionRespaldoBD(configuracion);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("registro", configuracion);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se modificó una  configuración de respaldo del sistema (" + configuracion.getCodigoConfiguracion() + ")",
                            "Se modifica una configuración");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha modificado una  configuración del sistema!.");
                    mnotif.agregarNNotificacion(notificacion);

                    disp = request.getRequestDispatcher("/modulos/configuraciones/editar_cr.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        configuracion = mconfiguracion.getConfiguracionRespaldo(registro);
                        //  respuesta = mconfiguracion.eliminarConfiguracion(configuracion);
                        request.setAttribute("registro", configuracion);
                        request.setAttribute("respuesta", respuesta);
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                                "Se eliminó una  configuración de respaldo del sistema (" + configuracion.getCodigoConfiguracion() + ")",
                                "Se eliminó una configuración");
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario,
                                "Se ha eliminado una  configuración del sistema!.");
                        mnotif.agregarNNotificacion(notificacion);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/configuracions/_asinc/_asinc_eliminar.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    // resultset = mconfiguracion.busquedaConfiguracion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/configuracions/_asinc/_asinc_listar.jsp");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Listado de configuraciones, asinc",
                            "Listado de configuraciones, asinc");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    // request.setAttribute("paginacion", ((int) mconfiguracion.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    //  resultset = mconfiguracion.busquedaConfiguracion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/configuracions/index.jsp");
                    //  request.setAttribute("paginacion", ((int) mconfiguracion.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "configuracion de sistema, default post",
                            "configuracion de sistema, default post");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;
            }
            disp.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a pagian de error de sistema
        }
    }

    /**
     * Función que permite obtener un objeto de Configuracion a partir de la
     * soliciitud que el usuario realiza y que el servidor recibe, esto para la
     * operacion de registro. Si los campos no son correctos, se completaran con
     * nulos o con -1 en caso de ser numéricos.
     *
     * @param request el objeto que contiene el dato de la solicitud.
     * @return un objeto Configuración para su uso.
     * @since 1.0
     */
    private Configuracion generarConfiguracionRespaldoBD(HttpServletRequest request) {
        Configuracion configuracion = new Configuracion();
        try {
            configuracion.setCodigoConfiguracion(UtilidadesServlet.getInt(request.getParameter("txtcodigoconf"), 0));
            configuracion.setPathMysqlDump(request.getParameter("txtrutamysqldump"));
            configuracion.setPathBackup(request.getParameter("txtrutabackup"));
            configuracion.setLapsoTiempoBackup(UtilidadesServlet.getInt(request.getParameter("txtlapsotiempo"), 0));
            configuracion.setPrefijoBackup(request.getParameter("txtprefijo"));
            configuracion.setServerBaseDatos(request.getParameter("txtnombreserver"));
            configuracion.setNombreBaseDatos(request.getParameter("txtbd"));
            configuracion.setUsuarioBd(request.getParameter("txtusuariobd"));
            configuracion.setContraseniaBd(request.getParameter("txtcontrabd"));
            configuracion.setPuertoServer(request.getParameter("txtpuerto"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return configuracion;
    }
}
