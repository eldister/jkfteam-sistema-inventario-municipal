package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorBaja;
import simuni.clases.ln.ManejadorSolicitudBaja;
import simuni.entidades.Baja;
import simuni.entidades.Respuesta;
import simuni.entidades.SolicitudBaja;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>SolicitudBaja</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesSolicitudBaja extends HttpServlet {

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

        Listado, Nuevo, Eliminar, Eliminar_Solicitud_Baja, Modificar, Query, ObtenerSolicitudesBaja, Solicitud_Baja, AccionDefault
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
        } else if (key.equals("obtener_solicitudes")) {
            return OpcionesDo.ObtenerSolicitudesBaja;
        } else if (key.equals("solicitud_baja")) {
            return OpcionesDo.Solicitud_Baja;
        } else if (key.equals("eliminar_solicitud")) {
            return OpcionesDo.Eliminar_Solicitud_Baja;
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
        String idusuario = request.getSession().getAttribute("USERNAME") == null
                ? null : request.getSession().getAttribute("USERNAME").toString();
        try {
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            int registro = 0;
            ManejadorSolicitudBaja msolicitudbaja = new ManejadorSolicitudBaja();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/nueva_solicitud.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = msolicitudbaja.busquedaSolicitudBajaUsuario(query, desplazamiento, paginacion, idusuario);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/index.jsp");
                    request.setAttribute("paginacion", ((int) msolicitudbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        SolicitudBaja solicitudbaja = msolicitudbaja.getSolicitudBajaUsuario(registro, idusuario);
                        request.setAttribute("registro", solicitudbaja);
                    }
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/editar_solicitud.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        SolicitudBaja solicitudbaja = msolicitudbaja.getSolicitudBajaUsuario(registro, idusuario);
                        request.setAttribute("registro", solicitudbaja);
                    }
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/eliminar_solicitud.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = msolicitudbaja.busquedaSolicitudBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/solicitudbajas/index.jsp");
                    request.setAttribute("paginacion", ((int) msolicitudbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case ObtenerSolicitudesBaja:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = msolicitudbaja.busquedaSolicitudBaja(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/solicitudes_index.jsp");
                    request.setAttribute("paginacion", ((int) msolicitudbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
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
        SolicitudBaja solicitudbaja = null;

        try {
            String idusuario = request.getSession().getAttribute("USERNAME") == null
                    ? null : request.getSession().getAttribute("USERNAME").toString();
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            int registro = 0;//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  

            ManejadorSolicitudBaja msolicitudbaja = new ManejadorSolicitudBaja();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:

                    solicitudbaja = generarSolicitudBaja(request);
                    respuesta = msolicitudbaja.registrarSolicitudBaja(solicitudbaja);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("registro", solicitudbaja);
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/nueva_solicitud.jsp");
                    break;
                case Modificar:

                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        solicitudbaja = generarSolicitudBaja(request);

                        respuesta = msolicitudbaja.modificarSolicitudBaja(solicitudbaja);
                        request.setAttribute("registro", solicitudbaja);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/solicitudbajas/editar_solicitud.jsp");
                    } else {
                        disp = request.getRequestDispatcher("/modulos/solicitudbajas/index.jsp");
                    }
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        solicitudbaja = msolicitudbaja.getSolicitudBajaUsuario(registro, idusuario);
                        respuesta = msolicitudbaja.eliminarSolicitudBaja(solicitudbaja);
                        request.setAttribute("registro", solicitudbaja);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/solicitudbajas/eliminar_solicitud.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    if (request.getParameter("mod") != null) {
                        resultset = msolicitudbaja.busquedaSolicitudBaja(query, desplazamiento, paginacion);
                        request.setAttribute("mod", request.getParameter("mod"));
                    } else {
                        resultset = msolicitudbaja.busquedaSolicitudBajaUsuario(query, desplazamiento, paginacion, idusuario);
                    }
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) msolicitudbaja.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = msolicitudbaja.busquedaSolicitudBajaUsuario(query, desplazamiento, paginacion, idusuario);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/solicitudbajas/index.jsp");
                    request.setAttribute("paginacion", ((int) msolicitudbaja.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Solicitud_Baja:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        solicitudbaja = msolicitudbaja.getSolicitudBaja(registro);
                        Baja baja = new Baja();
                        baja.setPlacaActivo(solicitudbaja.getPlacaActivo());
                        baja.setRazonBaja(solicitudbaja.getMotivo());
                        baja.setObservaciones(solicitudbaja.getObservaciones());
                        request.setAttribute("registro", baja);
                        disp = request.getRequestDispatcher("/modulos/bajas/nuevo.jsp");
                    } else {
                        disp = request.getRequestDispatcher("/modulos/solicitudbajas/index.jsp");
                    }
                    break;
                case Eliminar_Solicitud_Baja:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        solicitudbaja = msolicitudbaja.getSolicitudBaja(registro);
                        if (solicitudbaja != null) {
                            solicitudbaja.setEstado("Rechazada");
                        }
                        respuesta = msolicitudbaja.modificarEstadoSolicitudBaja(solicitudbaja);
                        //request.setAttribute("registro", solicitudbaja);
                        // request.setAttribute("respuesta", respuesta);
                        //  disp = request.getRequestDispatcher("/modulos/solicitudbajas/eliminar_solicitud.jsp");
                        // response.sendRedirect("/modulos/solicitudbajas/solicitudes_index.jsp");
                        response.getWriter().print("<script>window.location.assign('" + Recursos.Servers.MAINSERVER + "/solicitudbaja?proceso=obtener_solicitudes')</script>");
                        return;
                    }
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
     * metodo para la generacion de una nueva reparacion con la información obtenido
     * de los formularios html del lado del cliente
     * 
     * @param request solicitud al servlet
     * @return la nueva solicitud de baja enviada por algún usuario
     */
    private SolicitudBaja generarSolicitudBaja(HttpServletRequest request) {
        SolicitudBaja solicitud = new SolicitudBaja();
        try {
            String idusuario = request.getSession().getAttribute("USERNAME") == null
                    ? null : request.getSession().getAttribute("USERNAME").toString();
            solicitud.setPlacaActivo(request.getParameter("txtplacaactivo"));
            solicitud.setMotivo(request.getParameter("txtrazonbaja"));
            solicitud.setObservaciones(request.getParameter("txtobservaciones"));
            solicitud.setEstado("Enviada");
            solicitud.setUsuario(idusuario);
            solicitud.setCodigo(UtilidadesServlet.getInt(request.getParameter("registro"), 0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return solicitud;
    }
}
