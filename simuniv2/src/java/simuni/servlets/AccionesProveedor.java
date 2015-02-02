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
import simuni.clases.ln.ManejadorProveedor;
import simuni.entidades.Proveedor;
import simuni.entidades.Respuesta;
import simuni.utils.UtilidadesServlet;

/**
 *
 * @author FchescO
 */
public class AccionesProveedor extends HttpServlet {

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

        Listado,Listado2,Listado_Asinc, Nuevo, Eliminar, Modificar, Existe, Query,Query_Asinc, ReintentoNuevo, AccionDefault
    }

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
        } else if (key.equals("chequeo")) {
            return OpcionesDo.Existe;
        } else if (key.equals("rnuevo")) {
            return OpcionesDo.ReintentoNuevo;
        } else if (key.equals("listado_asinc")) {
            return OpcionesDo.Listado_Asinc;
        }else if (key.equals("query_asinc")) {
            return OpcionesDo.Query_Asinc;
        }else if (key.equals("listado2")) {
            return OpcionesDo.Listado2;
        } 
        return OpcionesDo.AccionDefault;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher disp = null;
        try {
            boolean mostrar_inactivos = request.getParameter("mostrar_inactivos") != null;
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String registro = "";
            ManejadorProveedor mproveedor = new ManejadorProveedor();
            Proveedor proveedor = null;
            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    request.setAttribute("tipoproveedores", mproveedor.listadoTipoProveedor());
                    disp = request.getRequestDispatcher("/modulos/proveedores/nuevo.jsp");
                    break;
                case Listado2:
                    request.setAttribute("tipoproveedores", mproveedor.listadoTipoProveedor());
                    disp = request.getRequestDispatcher("/modulos/proveedores/listado_proveedores.jsp");
                    break;
                case Listado:
                    
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mproveedor.busquedaProveedor(query, desplazamiento, paginacion, mostrar_inactivos);//provisional
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/index.jsp");
                    request.setAttribute("paginacion", ((int) mproveedor.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;
                case Listado_Asinc:
                    
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mproveedor.busquedaProveedor(query, desplazamiento, paginacion, mostrar_inactivos);//provisional
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/_asinc/_asinc_index.jsp");
                    request.setAttribute("paginacion", ((int) mproveedor.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("query", query);
                    break;                    
                case Modificar:
                    registro = request.getParameter("registro");
                    proveedor = mproveedor.getProveedor(registro);
                    request.setAttribute("registro", proveedor);
                    request.setAttribute("tipoproveedores", mproveedor.listadoTipoProveedor());    
                    disp = request.getRequestDispatcher("/modulos/proveedores/editar.jsp");
                    break;
                case Eliminar:
                    registro = request.getParameter("registro");
                    proveedor = mproveedor.getProveedor(registro);
                    request.setAttribute("registro", proveedor);
                    disp = request.getRequestDispatcher("/modulos/proveedores/eliminar.jsp");                    
                    break;
            }
            request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "1" : "");
            disp.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a  pagina de error de sistema
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher disp = null;
        Proveedor proveedor = null;
        Respuesta respuesta = null;
        try {
            boolean mostrar_inactivos = request.getParameter("mostrar_inactivos") != null;
            int desplazamiento = 0;
            int npagina = 0;
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String registro = "";
            ManejadorProveedor mproveedor = new ManejadorProveedor();

            String query = request.getParameter("query");
            ResultSet resultset = null;
            query = query == null ? "" : query;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    proveedor = generarProveedor(request);
                    respuesta = mproveedor.registrarProveedor(proveedor);
                    request.setAttribute("registro", proveedor);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/proveedores/msgs/notificacion_registro.jsp");
                    disp.forward(request, response);

                    break;
                case Existe:
                    String cedula = request.getParameter("cedula");
                    if (mproveedor.existeProveedor(cedula)) {
                        response.getWriter().print("Existe " + cedula);
                    }
                    break;
                case ReintentoNuevo:
                    proveedor = generarProveedor(request);
                    request.setAttribute("registro", proveedor);
                    disp = request.getRequestDispatcher("/modulos/proveedores/nuevo.jsp");
                    disp.forward(request, response);
                    break;
                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mproveedor.busquedaProveedor(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mproveedor.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    disp.forward(request, response);
                    break;
                case Query_Asinc:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mproveedor.busquedaProveedor(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/_asinc/_asinc_actualizarlistado.jsp");
                    request.setAttribute("paginacion", ((int) mproveedor.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    disp.forward(request, response);
                    break;                    
                case Modificar:
                    proveedor = generarProveedor(request);
                    respuesta = mproveedor.modificarProveedor(proveedor);
                    request.setAttribute("registro", proveedor);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/proveedores/msgs/notificacion_edicion.jsp");
                    disp.forward(request, response);                    
                    break;
                case Eliminar:
                    registro = request.getParameter("registro");
                    proveedor = mproveedor.getProveedor(registro);
                    respuesta = mproveedor.eliminarProveedor(proveedor);
                    request.setAttribute("registro", proveedor);
                    request.setAttribute("respuesta", respuesta);
                    disp = request.getRequestDispatcher("/modulos/proveedores/msgs/notificacion_eliminacion.jsp");
                    disp.forward(request, response);                        
                    break;
                case Listado2:
                    int tipoServicio=UtilidadesServlet.getInt(request.getParameter("tiposervicio"), 1);
                    request.setAttribute("registros", mproveedor.getProveedoresXTipoServicio(tipoServicio));
                    disp = request.getRequestDispatcher("/modulos/proveedores/_asinc/_asinc_listar2.jsp");
                    disp.forward(request, response);                      
                    break;  
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a  pagina de error de sistema
        }
    }

    private Proveedor generarProveedor(HttpServletRequest request) {
        Proveedor resp = new Proveedor();
        String tipoproveedor = request.getParameter("cmbtipoproveedor");
        String estadoproveedor = request.getParameter("cmbestadoproveedor");
        String cedula = request.getParameter("txtcedulaproveedor");
        String nombreproveedor = request.getParameter("txtnombreproveedor");
        String apellido1proveedor = request.getParameter("txtapellido1proveedor");
        String apellido2proveedor = request.getParameter("txtapellido2proveedor");
        String telefonofijo = request.getParameter("txttelefonofijo");
        String telefonomovil = request.getParameter("txttelefonomovil");
        String telefonooficina = request.getParameter("txttelefonooficina");
        String fax = request.getParameter("txtfax");
        String email = request.getParameter("txtemail");
        String web = request.getParameter("txtweb");
        String apartadopostal = request.getParameter("txtapartadopostal");
        String cedularepresentante = request.getParameter("txtcedularepresentante");
        String nombrerepresentante = request.getParameter("txtnombrerepresentante");
        String apellido1representante = request.getParameter("txtappellido1representante");
        String apellido2representante = request.getParameter("txtappellido2representante");
        String nombrebanco = request.getParameter("cmbnombrebanco");
        String numcuenta = request.getParameter("txtnumcuenta");
        String nombreempresa = request.getParameter("txtnombreempresa");
        String direccionempresa = request.getParameter("txtdireccionempresa");
       
        resp.setTipoProveedor(tipoproveedor);
        resp.setEstado(estadoproveedor);
        resp.setCedula(cedula);
        resp.setNombre(nombreproveedor);
        resp.setPrimerApellido(apellido1proveedor);
        resp.setSegundoApellido(apellido2proveedor);
        resp.setTelFijo(telefonofijo);
        resp.setTelMovil(telefonomovil);
        resp.setTelOfic(telefonooficina);
        resp.setTelFax(fax);
        resp.setEmail(email);
        resp.setPaginaWeb(web);
        resp.setApartadoPostal(apartadopostal);
        resp.setRepresentanteLegal(cedularepresentante, nombrerepresentante, apellido1representante, apellido2representante);
        resp.setNombreBanco(nombrebanco);
        resp.setNumeroCuenta(numcuenta);
        resp.setNomEmpresa(nombreempresa);
        resp.setDirEmpresa(direccionempresa);
        resp.setFechaRegistro(new Date());
        resp.setFechaUltimaModificacion(new Date());
        String[]tiposservicios=request.getParameterValues("cmbserviciosproveedor");
        if(tiposservicios!=null){
            for (int i = 0; i < tiposservicios.length; i++) {
                System.out.println("Servicio >>>>>> "+tiposservicios[i]);
                resp.agregarTipoServicio(UtilidadesServlet.getInt(tiposservicios[i], -1));
            }
        }

        return resp;
    }

}
