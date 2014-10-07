/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import simuni.classes.AD.ManejadorDatosDepartamento;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.Departamento;
import simuni.classes.EN.EstadoActivo;
import simuni.classes.EN.TipoActivo;
import simuni.classes.EN.TipoPago;
import simuni.classes.EN.imagenActivo;
import simuni.classes.LN.ManejadorActivos;
import simuni.classes.LN.ManejadorEstadoActivos;
import simuni.classes.LN.ManejadorTipoActivo;
import simuni.classes.LN.ManejadorTipoPago;

/**
 *
 * @author FchescO
 */
@MultipartConfig
public class AccionesActivos extends HttpServlet {

    enum OpcionesDo {

        ObtenerActivosArticulos,
        ObtenerActivoArticulo,
        AccionDefault,
        RegistrarActivoArticulo,
        ActualizarActivoArticulo,
        BajarActivoArticuloAsinc

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccionesActivos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccionesActivos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
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
        //aqui se manejara los procesos  
        ManejadorActivos manejador = new ManejadorActivos();
        ManejadorDatosDepartamento manejadordeptos = new ManejadorDatosDepartamento();
        ManejadorTipoActivo manejadortactivos = new ManejadorTipoActivo();
        ManejadorTipoPago manejadortpago = new ManejadorTipoPago();
        ManejadorEstadoActivos manejadorestadoactivo = new ManejadorEstadoActivos();

        RequestDispatcher disp = null;
        switch (getOpcion(request.getParameter("proceso"))) {
            /*parte uno es para los activos articulos*/
            case ObtenerActivosArticulos:
                ArrayList<Activos_Articulos> articulos = manejador.getListaArticulos();
                disp = request.getRequestDispatcher("/modulos/activos/articulos/mantenimiento_1.jsp");
                request.setAttribute("listadoarticulos", articulos);
                disp.forward(request, response);
                break;
            case ActualizarActivoArticulo:
                String codigoactivo = request.getParameter("codigoactivo");
                Activos_Articulos articulo = manejador.getActivoArticulo(codigoactivo);
                //hay que crear la vista
                disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizacionactivoarticulo.jsp");
                request.setAttribute("articulo", articulo);
                disp.forward(request, response);
                break;
            case RegistrarActivoArticulo:
                //carga formulario
                //obtener departamentos
                //obtener tipos de pago
                //obtener clasificaciones de activos
                ArrayList<Departamento> deptos = manejadordeptos.getListaDepartamentos();
                ArrayList<TipoActivo> tiposactivos = manejadortactivos.getListaTiposActivos();
                ArrayList<TipoPago> tipospago = manejadortpago.getListaTipoPago();
                ArrayList<EstadoActivo> estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                request.setAttribute("departamentos", deptos);
                request.setAttribute("tiposactivo", tiposactivos);
                request.setAttribute("tipospago", tipospago);
                request.setAttribute("estadoactivos", estadoactivos);
                disp = request.getRequestDispatcher("/modulos/activos/articulos/registro.jsp");
                disp.forward(request, response);

                break;

            /*fini de parte uno*/
            default:
                ArrayList<Activos_Articulos> articulos2 = manejador.getListaArticulos();
                RequestDispatcher disp2 = request.getRequestDispatcher("/modulos/activos/articulos/mantenimiento_1.jsp");
                request.setAttribute("listadoarticulos", articulos2);
                disp2.forward(request, response);
                break;
        }

        // processRequest(request, response);
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Departamento depto = new Departamento();
        DateFormat formatter = null;
        InputStream filecontent = null;
        RequestDispatcher disp = null;
        Date date = null;
        String filename = "";
        Activos_Articulos activoarticulo = new Activos_Articulos();

        boolean errores = false;
        ManejadorActivos manejadoractivos = new ManejadorActivos();
        try {
            String numeroplaca = request.getParameter("txtNumeroPlaca");
            String modeloactivo = request.getParameter("txtModelo");
            String marcaactivo = request.getParameter("txtMarca");
            String categoriaactivo = request.getParameter("hiddenidCategoria");
            String fechainiciooperacion = request.getParameter("dpPuestaOperacion");
            String idproveedor = request.getParameter("hiddenidProveedor");//FALTAA
            String iddepto = request.getParameter("hiddenidDepartamento");//FALTAA-
            String fechacompraactivo = request.getParameter("dpFechaCompra");
            String preciocompra = request.getParameter("txtPrecioCompra");
            String porcentajedepreciacion = request.getParameter("txtPorcentajeDepreciacion");
            String porcentajerescate = request.getParameter("txtPorcentajeRescate");
            String idtipopago = request.getParameter("hiddenidTipoPago");//FALTAA-
            String estadoactivo = request.getParameter("cmbEstadoActivo");
            String descripcion = request.getParameter("txtDescripcion"); // Retrieves <input type="text" name="description">
            String observaciones = request.getParameter("txtObservaciones");
            Part filePart = request.getPart("fileimagenactivo"); // Retrieves <input type="file" name="file">
            if(filePart!=null){
                System.out.println("archivo presente ayuda nombres iii "+filePart.getSize());
            }
            switch (getOpcion(request.getParameter("proceso"))) {
                case RegistrarActivoArticulo:
                    filecontent = filePart.getInputStream();
                    filename = getFilename(filePart);
                    formatter = new SimpleDateFormat("dd-mm-yyyy");
                    date = new java.sql.Date(formatter.parse(fechainiciooperacion).getTime());

                    ///VALIDAR SI LOS CAMPOS REQUERIDOS SON VALIDOS O NO (NULOS) y que la imagen sea png o jpg
                    //validar que la placa no este registrada en base de datos.
                    
                    
                    //INSTANCIAR EL OBJETO
                    activoarticulo.setPa_identificadorActivo(numeroplaca);
                    activoarticulo.setPa_modelo(modeloactivo);
                    activoarticulo.setPa_marca(marcaactivo);
                    activoarticulo.setPd_puestaOperacion(date);
                    activoarticulo.setPa_codigoProveedor(idproveedor);

                    //instanciamos depto
                    depto.setPn_codigo(Integer.parseInt(iddepto));
                    activoarticulo.setPo_depto(depto);

                    //instanciamos para las imagenes
                    imagenActivo imagenactivo = new imagenActivo();
                    imagenactivo.setPa_nombreArchivo(filename);
                    imagenactivo.setStreamarchivo(filecontent);
                    ArrayList<imagenActivo> listadoimagen = new ArrayList<imagenActivo>();
                    listadoimagen.add(imagenactivo);
                    activoarticulo.setPo_imagenActivo(listadoimagen);

                    //hacer validaciones si es entero.
                    date = new java.sql.Date(formatter.parse(fechacompraactivo).getTime());
                    activoarticulo.setPd_fechaCompra(date);
                    activoarticulo.setPd_precioCompra(Double.parseDouble(preciocompra));
                    activoarticulo.setPb_porcentajeDepreciacion(Double.parseDouble(porcentajedepreciacion));
                    activoarticulo.setPb_porcentajeRescate(Double.parseDouble(porcentajerescate));
                    activoarticulo.setPa_tipoPago(Integer.parseInt(idtipopago));//corregir, aqui es int
                    activoarticulo.setPa_tipoActivo(Integer.parseInt(categoriaactivo));//corregir, aqui es int
                    activoarticulo.setPa_Estado(estadoactivo);
                    activoarticulo.setPa_Descripcion(descripcion);
                    activoarticulo.setPa_Observaciones(observaciones);

                    //falta imagen
                    //hacer el proceso de registro
                    if (!errores&&manejadoractivos.agregarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        disp.forward(request, response);
                    }

                    /*                 out.println("<!DOCTYPE html>");
                     out.println("<html>");
                     out.println("<head>");
                     out.println("<title>Servlet AccionesActivos</title>");
                     out.println("</head>");
                     out.println("<body>");
                     out.println("<h1>Servlet AccionesActivos at " + request.getContextPath() + "</h1>");
                     out.print(filename);
                     out.println("</body>");
                     out.println("</html>");*/
                    // ... (do your job here)
                    break;
                case ActualizarActivoArticulo:
                   /* filecontent = filePart.getInputStream();
                    filename = getFilename(filePart);*/
                    formatter = new SimpleDateFormat("dd-mm-yyyy");
                    System.out.println("el valoooor es "+fechainiciooperacion);
                    date = new java.sql.Date(formatter.parse(fechainiciooperacion).getTime());

                    ///VALIDAR SI LOS CAMPOS REQUERIDOS SON VALIDOS O NO (NULOS) y que la imagen sea png o jpg
                    //validar que la placa no este registrada en base de datos.
                    //
                    //INSTANCIAR EL OBJETO
                    activoarticulo = new Activos_Articulos();
                    activoarticulo.setPa_identificadorActivo(numeroplaca);
                    activoarticulo.setPa_modelo(modeloactivo);
                    activoarticulo.setPa_marca(marcaactivo);
                    activoarticulo.setPd_puestaOperacion(date);
                    activoarticulo.setPa_codigoProveedor(idproveedor);

                    //instanciamos depto
                    depto = new Departamento();
                    depto.setPn_codigo(Integer.parseInt(iddepto));
                    activoarticulo.setPo_depto(depto);

                    //instanciamos para las imagenes
                    imagenactivo = new imagenActivo();
                    imagenactivo.setPa_nombreArchivo(filename);
                  imagenactivo.setStreamarchivo(filecontent);
                    listadoimagen = new ArrayList<imagenActivo>();
                    listadoimagen.add(imagenactivo);
                    activoarticulo.setPo_imagenActivo(listadoimagen);

                    //hacer validaciones si es entero.
                    date = new java.sql.Date(formatter.parse(fechacompraactivo).getTime());
                    activoarticulo.setPd_fechaCompra(date);
                    activoarticulo.setPd_precioCompra(Double.parseDouble(preciocompra));
                    activoarticulo.setPb_porcentajeDepreciacion(Double.parseDouble(porcentajedepreciacion));
                    activoarticulo.setPb_porcentajeRescate(Double.parseDouble(porcentajerescate));
                    activoarticulo.setPa_tipoPago(Integer.parseInt(idtipopago));//corregir, aqui es int
                    activoarticulo.setPa_tipoActivo(Integer.parseInt(categoriaactivo));//corregir, aqui es int
                    activoarticulo.setPa_Estado(estadoactivo);
                    activoarticulo.setPa_Descripcion(descripcion);
                    activoarticulo.setPa_Observaciones(observaciones);

                    //falta imagen
                    //hacer el proceso de registro
                    if (!errores&&manejadoractivos.modificarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        disp.forward(request, response);
                    }

                    break;
                    
                case BajarActivoArticuloAsinc:
                    //AQUIII
                    break;

            }
        } catch (ParseException ex) {
            System.out.print(ex.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    private OpcionesDo getOpcion(String key) {
        if (key == null) {
            return OpcionesDo.AccionDefault;
        } else if (key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("veractivosarticulos")) {
            return OpcionesDo.ObtenerActivosArticulos;
        } else if (key.equals("veractivoarticulo")) {
            return OpcionesDo.ObtenerActivoArticulo;
        } else if (key.equals("registroactivoarticulo")) {
            return OpcionesDo.RegistrarActivoArticulo;
        } else if (key.equals("modificacionarticulo")) {
            return OpcionesDo.ActualizarActivoArticulo;
        } if (key.equals("bajaarticuloasinc")) {
            return OpcionesDo.BajarActivoArticuloAsinc;
        }
        
        
        return OpcionesDo.AccionDefault;
    }
}
