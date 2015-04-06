/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.servlets;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import simuni.clases.ln.ManejadorPrestamo;
import simuni.entidades.Prestamo;
import simuni.entidades.Respuesta;
import simuni.utils.UtilidadesServlet;

/**
 *
 * @author Keylin
 */
public class AccionesPrestamo extends HttpServlet {

    enum OpcionesDo {

        Listado, Nuevo, Eliminar, Modificar, Query, AccionDefault, Listado_Asinc, Query_Asinc
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
        } else if (key.equals("listado_asinc")) {
            return OpcionesDo.Listado_Asinc;
        } else if (key.equals("nuevo")) {
            return OpcionesDo.Nuevo;
        } else if (key.equals("eliminar")) {
            return OpcionesDo.Eliminar;
        } else if (key.equals("actualizar")) {
            return OpcionesDo.Modificar;
        } else if (key.equals("query")) {
            return OpcionesDo.Query;
        } else if (key.equals("query_asinc")) {
            return OpcionesDo.Query_Asinc;
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
        Prestamo prestamo=null;
        try {
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            int registro = 0;
            ManejadorPrestamo mprestamo = new ManejadorPrestamo();
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/prestamos/nuevo.jsp");
                    break;
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    //disp = request.getRequestDispatcher("/modulos/prestamos/nuevo.jsp");
                    disp = request.getRequestDispatcher("/modulos/prestamos/index.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Listado_Asinc:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);//provisional
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/prestamos/_asinc/_asinc_index.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Modificar:
                    registro = Integer.parseInt(request.getParameter("registro"));
                    prestamo = mprestamo.getPrestamo(registro);
                    request.setAttribute("registro", prestamo);
                    disp = request.getRequestDispatcher("/modulos/prestamos/editar.jsp");
                    break;
                case Eliminar:
                    registro = Integer.parseInt(request.getParameter("registro"));
                    prestamo = mprestamo.getPrestamo(registro);
                    request.setAttribute("registro", prestamo);
                    disp = request.getRequestDispatcher("/modulos/prestamos/eliminar.jsp"); 
                    
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/prestamos/nuevo.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
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
       RequestDispatcher disp = null;
        Prestamo prestamo = null;
        Respuesta respuesta = null;
        try {
            boolean mostrar_inactivos = request.getParameter("mostrar_inactivos") != null;
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String registro = "";
            ManejadorPrestamo mprestamo = new ManejadorPrestamo();

            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    prestamo = generarPrestamo(request);
                    respuesta = mprestamo.registrarPrestamo(prestamo);
                    request.setAttribute("registro", prestamo);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/prestamos/nuevo.jsp");
                    break;
               case Modificar:
                    prestamo = generarActualizacionPrestamo(request);
                    respuesta = mprestamo.modificarPrestamo(prestamo);
                    request.setAttribute("registro", prestamo);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/prestamos/editar.jsp");
                    break;
                case Eliminar:
                    registro = request.getParameter("registro");
                    prestamo = mprestamo.getPrestamo(Integer.parseInt(registro));
                    respuesta = mprestamo.eliminarPrestamo(prestamo);
                    request.setAttribute("registro", prestamo);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/prestamos/eliminar.jsp");                 
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/prestamos/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case Query_Asinc:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/_asinc/_asinc_actualizarlistado.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mprestamo.busquedaPrestamo(query, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/prestamos/index.jsp");
                    request.setAttribute("paginacion", ((int) mprestamo.getCantidadRegistros(query) / paginacion) + 1);
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
    private Prestamo generarPrestamo(HttpServletRequest request) {
        Prestamo resp = new Prestamo();
        
        String departamentosolicitante = request.getParameter("txtdepartamento");
        Date fechadevolucion = UtilidadesServlet.getFecha(request.getParameter("txtfechaDevolucion"), null);
        String idactivo = request.getParameter("txtidActivo");
        String observacion = request.getParameter("txtobservaciones");
        String estado = "Solicitud";

        resp.setDepartamentoSolicitante(departamentosolicitante);
        resp.setEstado(estado);
        resp.setFechaRegistro(new Date());
        resp.setFechaDevolucion(fechadevolucion);
        resp.setIdActivo(idactivo);
        resp.setObservaciones(observacion);
        
        return resp;
    }
    
    private Prestamo generarActualizacionPrestamo(HttpServletRequest request) {
        Prestamo resp = new Prestamo();
        
        int idPrestamo = Integer.parseInt(request.getParameter("txtprestamo"));
        String departamentosolicitante = request.getParameter("txtdepartamento");
        Date fechadevolucion = UtilidadesServlet.getFecha(request.getParameter("txtfechaDevolucion"), null);
        String idactivo = request.getParameter("txtidActivo");
        String observacion = request.getParameter("txtobservaciones");
        String estado = "Solicitud";

        resp.setIdPrestamo(idPrestamo);
        resp.setDepartamentoSolicitante(departamentosolicitante);
        resp.setEstado(estado);
        resp.setFechaRegistro(new Date());
        resp.setFechaDevolucion(fechadevolucion);
        resp.setIdActivo(idactivo);
        resp.setObservaciones(observacion);
        
        return resp;
    }
}
