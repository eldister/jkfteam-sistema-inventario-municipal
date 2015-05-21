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
import simuni.clases.ln.ManejadorTipoReporte;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoReporte;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Tipo de Reporte</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesTipoReporte extends HttpServlet {

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
            ManejadorTipoReporte mtiporeporte = new ManejadorTipoReporte();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_nuevo.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtiporeporte.busquedaTipoReporte(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/index.jsp");
                    request.setAttribute("paginacion", ((int) mtiporeporte.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoReporte tiporeporte = mtiporeporte.getTipoReporte(registro);
                        request.setAttribute("registro", tiporeporte);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoReporte tiporeporte = mtiporeporte.getTipoReporte(registro);
                        request.setAttribute("registro", tiporeporte);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtiporeporte.busquedaTipoReporte(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/index.jsp");
                    request.setAttribute("paginacion", ((int) mtiporeporte.getCantidadRegistros(query) / paginacion) + 1);
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
        try {
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            int registro = 0;//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String nombretiporeporte = "";//campo txt
            ManejadorTipoReporte mtiporeporte = new ManejadorTipoReporte();
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
                    nombretiporeporte = request.getParameter("txtnombretiporeporte");
                    TipoReporte nuevotiporeporte = new TipoReporte();
                    nuevotiporeporte.setNombretiporeporte(nombretiporeporte);
                    respuesta = mtiporeporte.registrarTipoReporte(nuevotiporeporte);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("nuevoregistro", nuevotiporeporte);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_nuevo.jsp");
                 

                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se registra un nuevo tipo de reporte " + nuevotiporeporte.getNombretiporeporte(),
                            "Se registra nuevo tipo de reporte.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha registrado a " + nuevotiporeporte.getNombretiporeporte()+" como tipo de reporte");
                    mnotif.agregarNNotificacion(notificacion);                    
                    
                    break;
                case Modificar:
                    nombretiporeporte = request.getParameter("txtnombretiporeporte");
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoReporte tiporeporte = new TipoReporte();
                        tiporeporte.setIdtiporeporte(registro);
                        tiporeporte.setNombretiporeporte(nombretiporeporte);
                        respuesta = mtiporeporte.modificarTipoReporte(tiporeporte);
                        request.setAttribute("registro", tiporeporte);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_editar.jsp");
                   
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuesta, request,
                            "Se modifica un  tipo de reporte " + tiporeporte.getNombretiporeporte(),
                            "Se modifica nuevo tipo de reporte.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario,
                            "Se ha modifica a " + tiporeporte.getNombretiporeporte()+" como tipo de reporte");
                    mnotif.agregarNNotificacion(notificacion);                    
                                        
                    
                    } else {
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/index.jsp");
                    }
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        TipoReporte tiporeporte = mtiporeporte.getTipoReporte(registro);
                        respuesta = mtiporeporte.eliminarTipoReporte(tiporeporte);
                        request.setAttribute("registro", tiporeporte);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_eliminar.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtiporeporte.busquedaTipoReporte(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mtiporeporte.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mtiporeporte.busquedaTipoReporte(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/tiposreporte/index.jsp");
                    request.setAttribute("paginacion", ((int) mtiporeporte.getCantidadRegistros(query) / paginacion) + 1);
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
}
