/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import simuni.classes.EN.ProveedorFisico;
import simuni.classes.LN.ManejadorProveedores;

/**
 *
 * @author FchescO
 */
public class AccionesProveedoresF extends HttpServlet {

    enum OpcionesDo {

        ObtenerProveedoresFisicos,
        ObtenerProveedoresFisicosAsinc,
        ObtenerProveedorFisico,
        AccionDefault,
        RegistrarProveedorFisico,
        ActualizarProveedorFisico,
        ActualizarPropiedadPaginacionAsinc,
        BajarProveedorFisicoAsinc

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

        int npagina = 1;
        int paginacion = 1;
        ArrayList<ProveedorFisico> proveedores;
        ManejadorProveedores manejador = new ManejadorProveedores();
        RequestDispatcher disp = null;
        ProveedorFisico proveedor = null;
        switch (getOpcion(request.getParameter("proceso"))) {
            case ObtenerProveedoresFisicos:
                try {
                    npagina = Integer.parseInt(request.getParameter("pag"));
                } catch (NumberFormatException ex) {
                    //registrar en bitacora el error
                    //colocar los valores por defecto 
                    npagina = 1;
                }
                try {
                    paginacion = Integer.parseInt(request.getSession().getAttribute("paginacion").toString());
                } catch (Exception ex) {
                    //registrar en bitacora el error
                    //colocar los valores por defecto 
                    paginacion = 7;
                }
                proveedores = manejador.getListaProveedoresFisicos(npagina, paginacion);
                disp = request.getRequestDispatcher("/modulos/proveedores/fisicos/mantenimiento.jsp");
                request.setAttribute("listadoproveedoresfisicos", proveedores);
                disp.forward(request, response);

                break;

            case ActualizarProveedorFisico:
                String codigoproveedor = request.getParameter("codigoproveedor");
                proveedor = manejador.getProveedorFisico(codigoproveedor);
                disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizacionproveedorfisico.jsp");
                request.setAttribute("proveedor", proveedor);
                disp.forward(request, response);

                break;

            case RegistrarProveedorFisico:
                disp = request.getRequestDispatcher("/modulos/proveedores/fisicos/registro.jsp");
                disp.forward(request, response);
                break;

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
try{
        ProveedorFisico proveedor = null;
        RequestDispatcher disp = null;
        Date date = null;
        int npagina = 0;
        int paginacion = 0;//obtener la paginacion y pagina actual  
        Part filePart = null;
        ManejadorProveedores manejadorproveedores = null;
        ArrayList<ProveedorFisico> proveedores = null;
        switch (getOpcion(request.getParameter("proceso"))) {
            case RegistrarProveedorFisico:
                //obtener los archivos

                proveedor = generarproveedor(request);
                manejadorproveedores = new ManejadorProveedores();

                if (manejadorproveedores.agregarProveedorFisico(proveedor)) {
                    //redirigir a una pagina de exito
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + proveedor.getPa_cedula() + "&msg=3");
                    disp.forward(request, response);

                } else {
                    //redirigir a pagina de error y/o recargar el formulario
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + proveedor.getPa_cedula() + "&msg=3");
                    disp.forward(request, response);
                }

                break;

            case ActualizarProveedorFisico:

                proveedor = generarproveedor(request);
                manejadorproveedores = new ManejadorProveedores();

                if (manejadorproveedores.modificarProveedorFisico(proveedor)) {
                    //redirigir a una pagina de exito
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + proveedor.getPa_cedula() + "&msg=3");
                    disp.forward(request, response);

                } else {
                    //redirigir a pagina de error y/o recargar el formulario
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + proveedor.getPa_cedula() + "&msg=3");
                    disp.forward(request, response);
                }

                break;
            case BajarProveedorFisicoAsinc:
                String codigoproveedor = request.getParameter("codigoproveedor");
                manejadorproveedores = new ManejadorProveedores();
                if (manejadorproveedores.desactivarProveedorFisico(codigoproveedor)) {
                    //redirigir a una pagina de exito
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + codigoproveedor + "&msg=3");
                    disp.forward(request, response);

                } else {
                    //redirigir a pagina de error y/o recargar el formulario
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + codigoproveedor + "&msg=3");
                    disp.forward(request, response);
                }
                break;
            case ActualizarPropiedadPaginacionAsinc:
                //vamos por aqui
                try {
                    paginacion = 5;
                    String valorpaginacion = request.getParameter("valorpaginacion");
                    if (valorpaginacion != null) {
                        paginacion = Integer.parseInt(valorpaginacion);
                    }
                    request.getSession().setAttribute("paginacion", paginacion);
                    //modificar preferencias en base de datos.

                } catch (Exception ex) {
                    //registrar error en base de datos
                    request.getSession().setAttribute("paginacion", 5);
                    System.out.println(ex.getMessage());
                }
                break;

            case ObtenerProveedoresFisicosAsinc:
                try {
                    npagina = Integer.parseInt(request.getParameter("pag"));

                } catch (NumberFormatException ex) {
                        //registrar en bitacora el error
                    //colocar los valores por defecto 
                    npagina = 1;

                }
                try {
                    paginacion = Integer.parseInt(request.getSession().getAttribute("paginacion").toString());

                } catch (NumberFormatException ex) {
                        //registrar en bitacora el error
                    //colocar los valores por defecto 

                    paginacion = 5;
                    request.getSession().setAttribute("paginacion", paginacion);
                }
                manejadorproveedores = new ManejadorProveedores();
                proveedores = manejadorproveedores.getListaProveedoresFisicos(npagina, paginacion);
                request.setAttribute("proveedores", proveedores);
                disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizaciongrillaasinc.jsp?mod=2");
                disp.forward(request, response);
                break;

        }
}catch(Exception ex){
    ex.printStackTrace();
}
    }

    private ProveedorFisico generarproveedor(HttpServletRequest request) {
        ProveedorFisico proveedorfisico = new ProveedorFisico();
        proveedorfisico.setPa_cedula(request.getParameter("codigoproveedor"));
        proveedorfisico.setPa_nombre(request.getParameter("txtNombreProveedor"));
        proveedorfisico.setPa_primerApellido(request.getParameter("txtApellido1Proveedor"));
        proveedorfisico.setPa_segundoApellido(request.getParameter("txtApellido2Proveedor"));
        proveedorfisico.setPa_telefonoFijo(request.getParameter("txtTelefonoHabitacion"));
        proveedorfisico.setPa_telefonoFijo(request.getParameter("txtTelefonoOficina"));
        proveedorfisico.setPa_telefonoMovil(request.getParameter("txtTelefonoMovil"));
        proveedorfisico.setPa_fax(request.getParameter("txtNumeroFax"));
        proveedorfisico.setPa_correoElectronico(request.getParameter("txtEmail"));
        proveedorfisico.setPa_sitioWeb(request.getParameter("txtDireccionWeb"));
        System.out.println(request.getParameter("txtApartadoPostal"));
        proveedorfisico.setPn_apartadoPostal(Integer.parseInt(request.getParameter("txtApartadoPostal") == null ?  "1":request.getParameter("txtApartadoPostal")));
        proveedorfisico.setPa_nombreCompania(request.getParameter("txtNombreCompañia"));
        proveedorfisico.setPa_direccionCompania(request.getParameter("txtDireccionCompañia"));
        proveedorfisico.setPa_banco(request.getParameter("txtNombreBanco"));
        proveedorfisico.setPa_numeroCuenta(request.getParameter("txtNumeroCuenta"));
        return proveedorfisico;

    }

    private OpcionesDo getOpcion(String key) {
        if (key == null) {
            return OpcionesDo.AccionDefault;
        } else if (key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("verproveedoresfisicos")) {
            return OpcionesDo.ObtenerProveedoresFisicos;
        } else if (key.equals("verproveedorfisico")) {
            return OpcionesDo.ObtenerProveedorFisico;
        } else if (key.equals("registroproveedorfisico")) {
            return OpcionesDo.RegistrarProveedorFisico;
        } else if (key.equals("modificacionproveedorfisico")) {
            return OpcionesDo.ActualizarProveedorFisico;
        } else if (key.equals("bajaproveedorfisicoasinc")) {
            return OpcionesDo.BajarProveedorFisicoAsinc;
        } else if (key.equals("modificacionpaginacionasinc")) {
            return OpcionesDo.ActualizarPropiedadPaginacionAsinc;
        } else if (key.equals("ver_proveedorfisicoasinc")) {
            return OpcionesDo.ObtenerProveedoresFisicosAsinc;
        }
        return OpcionesDo.AccionDefault;
    }

}
