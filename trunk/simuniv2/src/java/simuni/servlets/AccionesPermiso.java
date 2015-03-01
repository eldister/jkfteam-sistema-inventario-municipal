package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorPermiso;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.Permiso;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Tipo de Usuario</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesPermiso extends HttpServlet {

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

        Listado, Nuevo, Eliminar, Modificar, Query, AccionDefault,PermisosAsignados,PermisosDisponibles,AsignarPermisos
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
        } else if (key.equals("pernoasignados")) {
            return OpcionesDo.PermisosDisponibles;
        } else if (key.equals("perasignados")) {
            return OpcionesDo.PermisosAsignados;
        }else if (key.equals("asignar")) {
            return OpcionesDo.AsignarPermisos;
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
            ManejadorPermiso mpermiso = new ManejadorPermiso();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_nuevo.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mpermiso.busquedaPermiso(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/index.jsp");
                    request.setAttribute("paginacion", ((int) mpermiso.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Permiso permiso = mpermiso.getPermiso(registro);
                        request.setAttribute("registro", permiso);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Permiso permiso = mpermiso.getPermiso(registro);
                        request.setAttribute("registro", permiso);
                    }
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_eliminar.jsp");
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mpermiso.busquedaPermiso(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/index.jsp");
                    request.setAttribute("paginacion", ((int) mpermiso.getCantidadRegistros(query) / paginacion) + 1);
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
            String nombrepermiso = "";//campo txt
            ManejadorPermiso mpermiso = new ManejadorPermiso();
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            ResultSet resultset = null;
            String idusuario=null;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    nombrepermiso = request.getParameter("txtnombrepermiso");
                    Permiso nuevopermiso = new Permiso();
                    nuevopermiso.setNombrePermiso(nombrepermiso);
                    respuesta = mpermiso.registrarPermiso(nuevopermiso);
                    request.setAttribute("respuesta", respuesta);
                    request.setAttribute("nuevoregistro", nuevopermiso);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_nuevo.jsp");
                    break;
                case Modificar:
                    nombrepermiso = request.getParameter("txtnombrepermiso");
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Permiso permiso = new Permiso();
                        permiso.setCodigoPermiso(registro);
                        permiso.setNombrePermiso(nombrepermiso);
                        respuesta = mpermiso.modificarPermiso(permiso);
                        request.setAttribute("registro", permiso);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_editar.jsp");
                    } else {
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/index.jsp");
                    }
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        Permiso permiso = mpermiso.getPermiso(registro);
                        respuesta = mpermiso.eliminarPermiso(permiso);
                        request.setAttribute("registro", permiso);
                        request.setAttribute("respuesta", respuesta);
                        disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_eliminar.jsp");
                    }
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mpermiso.busquedaPermiso(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mpermiso.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mpermiso.busquedaPermiso(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/mantenimientos/permisos/index.jsp");
                    request.setAttribute("paginacion", ((int) mpermiso.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case PermisosAsignados:
                    idusuario=request.getParameter("cmbusuario");
                     if(idusuario!=null){
                         request.setAttribute("permisos", mpermiso.listadoPermiso_Asignados(idusuario));
                     }
                     System.out.println("sirviooooooo");
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_permisos_asignados.jsp"); 
                    break;
                case PermisosDisponibles:
                    idusuario=request.getParameter("cmbusuario");
                     if(idusuario!=null){
                         request.setAttribute("permisos", mpermiso.listadoPermiso_Disponibles(idusuario));
                     }
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_permisos_noasignados.jsp"); 
                    break;   
                case AsignarPermisos:
                    //validar qeu usuario no sea null
                    idusuario=request.getParameter("cmbusuario");
                    String permisosnuevos[]=request.getParameterValues("cmbpermisoasignados[]");
                    System.out.println(permisosnuevos!=null);
                    ArrayList<Respuesta>valor_respuesta=mpermiso.asignarPermisos(permisosnuevos, idusuario);
                    
                    request.setAttribute("respuesta", valor_respuesta);  
                    disp = request.getRequestDispatcher("/modulos/usuarios/_asinc/_asinc_asignacion_respuesta.jsp"); 
                       
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
