package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorBitacora;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.clases.ln.ManejadorTipoActivo;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoActivo;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Tipo de Activo</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesTipoActivo extends HttpServlet {

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

        Listado, Nuevo, Eliminar, Modificar, Query, AccionDefault
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
            ManejadorTipoActivo mtipoactivo = new ManejadorTipoActivo();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_nuevo.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtipoactivo.busquedaTipoActivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/index.jsp");
                    request.setAttribute("paginacion", ((int) mtipoactivo.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoActivo tipoactivo = mtipoactivo.getTipoActivo(registro);
                        request.setAttribute("registro", tipoactivo);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoActivo tipoactivo = mtipoactivo.getTipoActivo(registro);
                        request.setAttribute("registro", tipoactivo);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtipoactivo.busquedaTipoActivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/index.jsp");
                    request.setAttribute("paginacion", ((int) mtipoactivo.getCantidadRegistros(query) / paginacion) + 1);
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
        RegistroBitacora registroBitacora;
        ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
        try {
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            int registro = 0;//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String nombretipoactivo = "";//campo txt
            ManejadorTipoActivo mtipoactivo = new ManejadorTipoActivo();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;

            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
            Notificacion notificacion = new Notificacion();
            ManejadorNotificaciones mnotif = new ManejadorNotificaciones();

            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    nombretipoactivo = request.getParameter("txtnombretipoactivo");
                    TipoActivo nuevotipoactivo = new TipoActivo();
                    nuevotipoactivo.setNombretipoactivo(nombretipoactivo);
                    respuesta = mtipoactivo.registrarTipoActivo(nuevotipoactivo);

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se registro un nuevo tipo de Activo", "Se registró el registro  "
                            + nuevotipoactivo.getNombretipoactivo());
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha registrado a " + nombretipoactivo + " como tipo de activo");
                    mnotif.agregarNNotificacion(notificacion);

                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("nuevoregistro", nuevotipoactivo);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_nuevo.jsp");
                    break;
                case Modificar:
                    nombretipoactivo = request.getParameter("txtnombretipoactivo");
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoActivo tipoactivo = new TipoActivo();
                        tipoactivo.setIdtipoactivo(registro);
                        tipoactivo.setNombretipoactivo(nombretipoactivo);
                        respuesta = mtipoactivo.modificarTipoActivo(tipoactivo);
                        request.setAttribute("registro", tipoactivo);
                        request.setAttribute("respuesta", respuesta);
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                                "Se modificó un nuevo tipo de Activo", "Se modificó el registro  "
                                + tipoactivo.getIdtipoactivo());
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario,
                                "Se ha modificado a " + nombretipoactivo + " como tipo de activo");
                        mnotif.agregarNNotificacion(notificacion);

                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_editar.jsp");
                    } else {
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/index.jsp");
                    }
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoActivo tipoactivo = mtipoactivo.getTipoActivo(registro);
                        respuesta = mtipoactivo.eliminarTipoActivo(tipoactivo);
                        request.setAttribute("registro", tipoactivo);
                        request.setAttribute("respuesta", respuesta);
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                                "Se eliminó un  tipo de Activo", "Se eliminó el registro  "
                                + tipoactivo.getIdtipoactivo());
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario,
                                "Se ha eliminado a " + tipoactivo.getIdtipoactivo() + " como tipo de activo");
                        mnotif.agregarNNotificacion(notificacion);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_eliminar.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtipoactivo.busquedaTipoActivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mtipoactivo.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtipoactivo.busquedaTipoActivo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposactivos/index.jsp");
                    request.setAttribute("paginacion", ((int) mtipoactivo.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
            }
            disp.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a pagian de error de sistema
            registroBitacora = manejadorBitacora.generarRegistroBitacora(new Respuesta(), request,
                    "Error de procesamiento en el post", ex.getMessage());
            manejadorBitacora.registrarEnBitacora(registroBitacora);
        }
    }

}
