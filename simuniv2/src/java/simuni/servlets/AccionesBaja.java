package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorBaja;
import simuni.clases.ln.ManejadorBitacora;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.entidades.Respuesta;
import simuni.entidades.Baja;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Baja</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesBaja extends HttpServlet {

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

        Listado, Listado_Asinc, Query_Asinc, Nuevo, Eliminar, Modificar, Query, Nueva_Solicitud_Baja, AccionDefault
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
        } else if (key.equals("nueva_solicitud")) {
            return OpcionesDo.Nueva_Solicitud_Baja;
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
        ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
        RegistroBitacora registroBitacora;
        try {
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            int registro = 0;
            ManejadorBaja mbaja = new ManejadorBaja();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/bajas/nuevo.jsp");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da de baja el activo, solicitud formulario ",
                            "Solicitud formulario para dar de baja un activo.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;
                case Nueva_Solicitud_Baja:
                    disp = request.getRequestDispatcher("/modulos/bajas/nueva_solicitud.jsp");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da la solicitud  baja el activo, solicitud formulario ",
                            "Solicitud de formulario para solicitud baja un activo.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mbaja.busquedaBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/bajas/index.jsp");
                    request.setAttribute("paginacion", ((int) mbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da la solicitud, listado de bajas ",
                            "Listado de bajas.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Baja baja = mbaja.getBaja(registro);
                        request.setAttribute("registro", baja);
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                                "Se da la solicitud, eliinación de bajas ",
                                "Eliminación de bajas.");
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                    }
                    disp = request.getRequestDispatcher("/modulos/bajas/eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mbaja.busquedaBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/bajas/index.jsp");
                    request.setAttribute("paginacion", ((int) mbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da la solicitud, listado de bajas, default ",
                            "Listado de bajas, default");
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
            String nombrebaja = "";//campo txt
            ManejadorBaja mbaja = new ManejadorBaja();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;

            ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
            RegistroBitacora registroBitacora;
            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
            Notificacion notificacion = new Notificacion();
            ManejadorNotificaciones mnotif = new ManejadorNotificaciones();

            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:

                    Baja nuevobaja = generarBaja(request);
                    //falta general
                    respuesta = mbaja.registrarBaja(nuevobaja);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("registro", nuevobaja);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se da de baja el activo " + nuevobaja.getPlacaActivo(),
                            "Borrado de los datos de un activo");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            nuevobaja.getPlacaActivo() + " ha sido dado de baja de la base de datos.");
                    mnotif.agregarNNotificacion(notificacion);
                    disp = request.getRequestDispatcher("/modulos/bajas/nuevo.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Baja baja = mbaja.getBaja(registro);
                        respuesta = mbaja.eliminarBaja(baja);

                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                                "Se cancela la baja del activo " + baja.getPlacaActivo(),
                                "Restaurado los datos de un activo");
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario,
                                baja.getPlacaActivo() + " ha sido restaurado a la base de datos.");
                        mnotif.agregarNNotificacion(notificacion);
                        request.setAttribute("registro", baja);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/bajas/index.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mbaja.busquedaBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/bajas/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mbaja.getCantidadRegistros(query) / paginacion) + 1);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da la solicitud, asincrónico, listado de bajas ",
                            "Listado de bajas, asincrónico.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);

                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mbaja.busquedaBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/bajas/index.jsp");
                    request.setAttribute("paginacion", ((int) mbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Se da la solicitud, listado de bajas, default ",
                            "Listado de bajas, default");
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
     * Función que permite obtener un objeto de Baja a partir de la soliciitud
     * que el usuario realiza y que el servidor recibe, esto para la operacion
     * de registro. Si los campos no son correctos, se completaran con nulos o
     * con -1 en caso de ser numéricos.
     *
     * @param request el objeto que contiene el dato de la solicitud.
     * @return un objeto Baja para su uso.
     * @since 1.0
     */
    private Baja generarBaja(HttpServletRequest request) {
        Baja baja = new Baja();
        try {
            baja.setCodigoDocumentoRespaldo(request.getParameter("txtdocumentorespaldo"));
            baja.setFechaBaja(UtilidadesServlet.getFecha(request.getParameter("txtfechabaja"), null));
            baja.setObservaciones(request.getParameter("txtobservaciones"));
            baja.setRazonBaja(request.getParameter("txtrazonbaja"));
            baja.setPlacaActivo(request.getParameter("hddactivo"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return baja;
    }
}
