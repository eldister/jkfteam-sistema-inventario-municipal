package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorReparacion;
import simuni.entidades.Respuesta;
import simuni.entidades.Reparacion;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * Reparacion
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author Producjeffer
 * @since 1.0
 * @version 2.0
 */
public class AccionesReparacion extends HttpServlet {
    /**
     * Esta enumeración es particular al servelet para poder hacer mas facil y
     * exacto el control de operaciones solicitadas. Entre las operaciones
     * comunes que se solicitan estan agregar, modificar, eliminar, hacer un
     * query de busqueda y tambien hacer el listado por defecto que hay de los
     * datos ingresados. Si el usuario no elige una de las operaciones de la
     * enumeración por defecto se hara la operacion listado.
     *
     * @author Producjeffer
     * @since 1.0
     * @version 2.0
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
        Reparacion reparacion = null;
        try {
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            int registro = 0;
            
            ManejadorReparacion mreparacion = new ManejadorReparacion();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    
                    
                    
                    
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mreparacion.busquedaReparacion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/index.jsp");
                    request.setAttribute("paginacion", ((int) mreparacion.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        reparacion = mreparacion.getReparacion(registro);
                        request.setAttribute("registro", reparacion);
                    }
                    disp = request.getRequestDispatcher("/modulos/reparaciones/editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        reparacion = mreparacion.getReparacion(registro);
                        request.setAttribute("registro", reparacion);
                    }
                    disp = request.getRequestDispatcher("/modulos/reparaciones/eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mreparacion.busquedaReparacion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/index.jsp");
                    request.setAttribute("paginacion", ((int) mreparacion.getCantidadRegistros(query) / paginacion) + 1);
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
        Reparacion reparacion = null;
        try {
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            int registro = 0;//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            ManejadorReparacion mreparacion = new ManejadorReparacion();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;

            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    reparacion = generarReparacion(request);

                    respuesta = mreparacion.registrarReparacion(reparacion);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("registro", reparacion);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/nuevo.jsp");
                    break;
                case Modificar:
                    reparacion = generarReparacion(request);
                    respuesta = mreparacion.modificarReparacion(reparacion);
                    request.setAttribute("registro", reparacion);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        reparacion = mreparacion.getReparacion(registro);
                        respuesta = mreparacion.eliminarReparacion(reparacion);
                        request.setAttribute("registro", reparacion);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/reparaciones/eliminar.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mreparacion.busquedaReparacion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mreparacion.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mreparacion.busquedaReparacion(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/reparaciones/index.jsp");
                    request.setAttribute("paginacion", ((int) mreparacion.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
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

    private Reparacion generarReparacion(HttpServletRequest request) {
        Reparacion reparacion = new Reparacion();
        try {
            reparacion.setnombreReparador(request.getParameter("txtnombrereparador"));
            reparacion.setFechaReparacion(UtilidadesServlet.getFecha(request.getParameter("txtfechareparacion"), null));
            reparacion.setCostoReparacion(UtilidadesServlet.getDouble(request.getParameter("txtmontoreparacion"), -1));
            reparacion.setnombreSolicitante(request.getParameter("txtnombresolicitante"));
            reparacion.setObservacion(request.getParameter("txtobservaciones"));
            reparacion.setPlacaActivo(request.getParameter("hddactivo"));
            reparacion.setCodigoReparacion(UtilidadesServlet.getInt("hddreparacion", 0));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reparacion;
    }
}
