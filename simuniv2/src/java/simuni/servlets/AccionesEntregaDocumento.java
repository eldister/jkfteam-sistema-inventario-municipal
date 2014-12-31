/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import simuni.clases.ln.ManejadorEntregaDocumento;
import simuni.clases.ln.ManejadorProveedor;
import simuni.entidades.Documento;
import simuni.entidades.EntregaDocumento;
import simuni.entidades.Proveedor;
import simuni.entidades.Respuesta;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 *
 * @author FchescO
 */
@MultipartConfig
public class AccionesEntregaDocumento extends HttpServlet {

    enum OpcionesDo {

        Listado, Nuevo, Eliminar, EliminarDocumento, Modificar, Existe, Query, ReintentoNuevo, AccionDefault, VerDetalle
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
        String proveedor = "";
        RequestDispatcher disp = null;
        int desplazamiento = 0;
        int npagina = 0;
        int paginacion = 0;//obtener la paginacion y pagina actual  
        int registro = 0;
        ManejadorEntregaDocumento mentregadocumento = new ManejadorEntregaDocumento();
        EntregaDocumento entregadocumento = null;
        String query = request.getParameter("query");
        ResultSet resultset = null;
        query = query == null ? "" : query;

        try {
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_nuevo.jsp");
                    break;
                case Listado:
                    proveedor = request.getParameter("registro");
                    if (proveedor == null || proveedor.length() == 0) {
                        response.sendRedirect(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
                        return;
                    } else {
                        Proveedor proveedor1 = new ManejadorProveedor().getProveedor(proveedor);
                        npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                        paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                        desplazamiento = ((npagina) * paginacion);
                        resultset = mentregadocumento.busquedaEntregaDocumento(query, proveedor, desplazamiento, paginacion);//provisional
                        request.setAttribute("listado", resultset);
                        request.setAttribute("registro", proveedor1);
                        disp = request.getRequestDispatcher("/modulos/proveedores/documentos/index.jsp");
                        request.setAttribute("paginacion", ((int) mentregadocumento.getCantidadRegistros(query, proveedor) / paginacion) + 1);
                        request.setAttribute("query", query);
                    }

                    break;
                case Modificar:
                    //obtener el activo
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {

                        System.out.println("Ahora si entre");
                        registro = Integer.parseInt(request.getParameter("registro"));
                        entregadocumento = mentregadocumento.getEntregaDocumento(registro);
                        System.out.println(entregadocumento.getObseravaciones());
                        request.setAttribute("registro", entregadocumento);
                    }
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_editar.jsp");
                    break;
                case Eliminar:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        registro = Integer.parseInt(request.getParameter("registro"));
                        entregadocumento = mentregadocumento.getEntregaDocumento(registro);
                        request.setAttribute("registro", entregadocumento);
                    }
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_eliminar.jsp");
                    break;
                case VerDetalle:
                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {

                        System.out.println("Ahora si entre");
                        registro = Integer.parseInt(request.getParameter("registro"));
                        resultset = mentregadocumento.getDocumentosEntrega(registro);
                        request.setAttribute("listado", resultset);
                    }
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_listardocumentos.jsp");

                    break;
                case AccionDefault:
                    proveedor = request.getParameter("proveedor");
                    if (proveedor == null || proveedor.length() == 0) {
                        response.sendRedirect("/proveedor?proceso=listado");
                    } else {
                        npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                        paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                        desplazamiento = ((npagina) * paginacion);
                        resultset = mentregadocumento.busquedaEntregaDocumento(query, proveedor, desplazamiento, paginacion);//provisional
                        request.setAttribute("listado", resultset);
                        disp = request.getRequestDispatcher("/modulos/proveedores/index.jsp");
                        request.setAttribute("paginacion", ((int) mentregadocumento.getCantidadRegistros(query, proveedor) / paginacion) + 1);
                        request.setAttribute("query", query);
                    }
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
        } else if (key.equals("detalle")) {
            return OpcionesDo.VerDetalle;
        } else if (key.equals("eliminardoc")) {
            return OpcionesDo.EliminarDocumento;
        }
        return OpcionesDo.AccionDefault;
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
        EntregaDocumento entregadocumento = null;
        ArrayList<Respuesta> respuestas = null;
        RequestDispatcher disp = null;
        int desplazamiento = 0;//movimiento hacia adelante en los queries
        int npagina = 0;//para la paginacion
        int registro = 0;//codigo de registro a buscar
        int paginacion = 0;//obtener la paginacion y pagina actual  
        ResultSet resultset = null;
        String proveedor = "";
        String query = request.getParameter("query");
        query = query == null ? "" : query;
        int codigoentrega = 0;
        ManejadorEntregaDocumento mentregadocumento = new ManejadorEntregaDocumento();
        try {
            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:

                    entregadocumento = generarEntregaDocumento(request);
                    respuestas = mentregadocumento.registrarEntregaDocumento2(entregadocumento);
                    request.setAttribute("respuesta", respuestas);
                    request.setAttribute("nuevoregistro", entregadocumento);
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_nuevo.jsp");
                    System.out.println("Pase por aquiiiiii y veamos" + request.getParameter("registro") + (respuestas != null));
                    disp.forward(request, response);
                    break;
                case Modificar:

                    if (UtilidadesServlet.tryParseInt(request.getParameter("registroentrega"))) {
                        codigoentrega = Integer.parseInt(request.getParameter("registroentrega"));
                        entregadocumento = generarEntregaDocumento(request);
                        entregadocumento.setCodigo(codigoentrega);
                        respuestas = mentregadocumento.modificarEntregaDocumento2(entregadocumento);
                        request.setAttribute("respuesta", respuestas);
                        request.setAttribute("registro", entregadocumento);
                        disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_editar.jsp");
                        System.out.println("Pase por aquiiiiii y veamos" + request.getParameter("registro") + (respuestas != null));
                        disp.forward(request, response);
                    } else {
                        response.sendRedirect(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
                    }

                    break;
                case Query:
                    System.out.println("bueno query");
                    proveedor = request.getParameter("registro");
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mentregadocumento.busquedaEntregaDocumento(query, proveedor, desplazamiento, paginacion);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mentregadocumento.getCantidadRegistros(query, proveedor) / paginacion) + 1);
                    disp.forward(request, response);
                    break;
                case Eliminar:

                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))) {
                        Respuesta respuesta = null;
                        boolean eliminardocumentos = request.getParameter("chkeliminardocumentos") != null;
                        codigoentrega = Integer.parseInt(request.getParameter("registro"));
                        entregadocumento = generarEntregaDocumento(request);
                        entregadocumento.setCodigo(codigoentrega);
                        respuesta = mentregadocumento.eliminarEntregaDocumento(entregadocumento, eliminardocumentos);
                        request.setAttribute("respuesta", respuesta);
                        request.setAttribute("registro", entregadocumento);
                        disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_eliminar.jsp");
                        System.out.println("Pase por aquiiiiii y veamos" + request.getParameter("registro") + (respuestas != null));
                        disp.forward(request, response);
                    } else {
                        response.sendRedirect(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
                    }

                    break;
                case EliminarDocumento:

                    if (UtilidadesServlet.tryParseInt(request.getParameter("registro"))&&UtilidadesServlet.tryParseInt(request.getParameter("entrega"))) {
                        Respuesta respuesta = null;
                        registro = Integer.parseInt(request.getParameter("registro"));
                        codigoentrega=Integer.parseInt(request.getParameter("entrega"));
                        Documento documento = new Documento();
                        documento.setCodigodocumento(registro);
                        documento.setCodigoentregadocumento(codigoentrega);
                        respuesta = mentregadocumento.eliminarDocumento(documento);
                        resultset=mentregadocumento.getDocumentosEntrega(codigoentrega);
                        request.setAttribute("respuesta", respuesta);
                        request.setAttribute("listado", resultset);
                        disp = request.getRequestDispatcher("/modulos/proveedores/documentos/_asinc/_asinc_listardocumentos.jsp");

                        disp.forward(request, response);
                    } else {
                        response.sendRedirect(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
                    }

                    break;

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private EntregaDocumento generarEntregaDocumento(HttpServletRequest request) {
        EntregaDocumento entrega = new EntregaDocumento();
        try {
            String estadoentrega = request.getParameter("cmbestadoentrega");
            System.out.println(estadoentrega);
            String observaciones = request.getParameter("txtobservaciones");
            String registro = request.getParameter("registro");
            entrega.setEstadoentrega(estadoentrega);
            entrega.setObseravaciones(observaciones);
            entrega.setCedulaproveedor(registro);
            entrega.setFecha(new Date());
            Collection<Part> parts = request.getParts();
            if (parts != null) {
                Iterator<Part> iteradorparts = parts.iterator();
                if (iteradorparts != null) {
                    while (iteradorparts.hasNext()) {
                        Part parte = iteradorparts.next();
                        String nombrearchivo = UtilidadesServlet.getFilename(parte);
                        if (parte != null && nombrearchivo != null && nombrearchivo.length() > 1) {
                            Documento doc = new Documento();
                            doc.setNombredocumento(nombrearchivo);
                            doc.setServerdocumento(Recursos.Servers.SERVER_ARCHIVOS.toString());
                            doc.setStreamarchivo(parte.getInputStream());
                            entrega.addDocumento(doc);
                            System.out.println("Uno a ver si entre" + nombrearchivo);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return entrega;
    }
}
