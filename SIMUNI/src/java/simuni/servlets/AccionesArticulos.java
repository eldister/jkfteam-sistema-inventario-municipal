package simuni.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
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
import simuni.classes.LN.UtilidadesServlet;

/**
 *
 * @author FchescO
 */
@MultipartConfig
public class AccionesArticulos extends HttpServlet {

    enum OpcionesDo {

        ObtenerActivosArticulos,
        ObtenerActivosArticulosAsinc,
        ObtenerActivoArticulo,
        AccionDefault,
        RegistrarActivoArticulo,
        ActualizarActivoArticulo,
        ActualizarPropiedadPaginacionAsinc,
        BajarActivoArticuloAsinc,
        VerificarSiArticuloYaRegistrado,
        BusquedaArticulo

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
        ArrayList<Departamento> deptos = null;
        ArrayList<TipoActivo> tiposactivos = null;
        ArrayList<TipoPago> tipospago = null;
        ArrayList<EstadoActivo> estadoactivos = null;
        ArrayList<Activos_Articulos> articulos = null;
        int npagina = 0;
        int paginacion = 0;//obtener la paginacion y pagina actual      
        RequestDispatcher disp = null;
        switch (getOpcion(request.getParameter("proceso"))) {
            /*parte uno es para los activos articulos*/

            case ObtenerActivosArticulos:
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
                articulos = manejador.getListaArticulos(npagina, paginacion);
                tiposactivos = manejadortactivos.getListaTiposActivos();
                disp = request.getRequestDispatcher("/modulos/activos/articulos/mantenimiento_1.jsp");
                request.setAttribute("listadoarticulos", articulos);
                request.setAttribute("listadotiposactivo", tiposactivos);
                disp.forward(request, response);
                break;

            case ActualizarActivoArticulo:
                deptos = manejadordeptos.getListaDepartamentos();
                tiposactivos = manejadortactivos.getListaTiposActivos();
                tipospago = manejadortpago.getListaTipoPago();
                estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                request.setAttribute("departamentos", deptos);
                request.setAttribute("tiposactivo", tiposactivos);
                request.setAttribute("tipospago", tipospago);
                request.setAttribute("estadoactivos", estadoactivos);
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
                deptos = manejadordeptos.getListaDepartamentos();
                tipospago = manejadortpago.getListaTipoPago();
                tiposactivos = manejadortactivos.getListaTiposActivos();
                estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                request.setAttribute("departamentos", deptos);
                request.setAttribute("tiposactivo", tiposactivos);
                request.setAttribute("tipospago", tipospago);
                request.setAttribute("estadoactivos", estadoactivos);
                disp = request.getRequestDispatcher("/modulos/activos/articulos/registro.jsp");
                disp.forward(request, response);
                break;
            case VerificarSiArticuloYaRegistrado:
                manejador=new ManejadorActivos();
                codigoactivo=request.getParameter("codigoactivo");
                if(manejador.isActivoExistente(codigoactivo)){
                    response.getOutputStream().print("Si Existe");
                }
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
        Departamento depto = new Departamento();
        DateFormat formatter = null;
        InputStream filecontent = null;
        RequestDispatcher disp = null;
        Date date = null;
        String filename = "";
        int npagina = 0;
        int paginacion = 0;//obtener la paginacion y pagina actual  
        Activos_Articulos activoarticulo = new Activos_Articulos();
        Part filePart = null;
        ManejadorActivos manejadoractivos = new ManejadorActivos();
        ManejadorTipoActivo manejadortactivos = new ManejadorTipoActivo();
        ManejadorDatosDepartamento manejadordeptos = new ManejadorDatosDepartamento();
        ManejadorEstadoActivos manejadorestadoactivo = new ManejadorEstadoActivos();
        ManejadorTipoPago manejadortpago = new ManejadorTipoPago();
        ArrayList<Departamento> deptos = null;
        ArrayList<TipoActivo> tiposactivos = null;
        ArrayList<TipoPago> tipospago = null;
        ArrayList<EstadoActivo> estadoactivos = null;
        ArrayList<Activos_Articulos> articulos = null;

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
            String descripcion = request.getParameter("txtDescripcion");
            String observaciones = request.getParameter("txtObservaciones");

            switch (getOpcion(request.getParameter("proceso"))) {
                
                
                case RegistrarActivoArticulo:
                    filePart = request.getPart("fileimagenactivo"); // Retrieves <input type="file" name="file">
                    if (filePart != null) {
                        System.out.println("archivo presente ayuda nombres iii " + filePart.getSize());
                     
                    }
                    filecontent = filePart.getInputStream();
                    filename = UtilidadesServlet.getFilename(filePart);
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
                    if (manejadoractivos.agregarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        disp.forward(request, response);
                    }
                    break;

                case ActualizarActivoArticulo:
                    filePart = request.getPart("fileimagenactivo"); // Retrieves <input type="file" name="file">                  
                    if (filePart != null) {
                        filecontent = filePart.getInputStream();
                        filename = UtilidadesServlet.getFilename(filePart);
                    }
                    formatter = new SimpleDateFormat("dd-mm-yyyy");
                    date = new java.sql.Date(formatter.parse(fechainiciooperacion).getTime());

                    ///VALIDAR SI LOS CAMPOS REQUERIDOS SON VALIDOS O NO (NULOS) y que la imagen sea png o jpg
                    //validar que la placa no este registrada en base de datos.
                    //se debe hacer en la logica de negocios.
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
                    if (manejadoractivos.modificarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        disp.forward(request, response);
                    }

                    break;

                case BajarActivoArticuloAsinc:

                    String codigoactivo = request.getParameter("codigoactivo");
                    if (manejadoractivos.desactivarActivoArticulo(codigoactivo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + codigoactivo + "&msg=3");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + codigoactivo + "&msg=3");
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

                    } catch (Exception  ex) {
                        //registrar error en base de datos
                        request.getSession().setAttribute("paginacion", 5);
                        System.out.println(ex.getMessage());
                    }
                    break;
                case ObtenerActivosArticulosAsinc:
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

                    articulos = manejadoractivos.getListaArticulos(npagina, paginacion);
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizaciongrillaasinc.jsp?mod=1");
                    deptos = manejadordeptos.getListaDepartamentos();
                    tipospago = manejadortpago.getListaTipoPago();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    request.setAttribute("listadoarticulos", articulos);
                    request.setAttribute("listadotiposactivo", tiposactivos);
                    disp.forward(request, response);
                    break;
                    
                case BusquedaArticulo:
                   String query=request.getParameter("query");
                    articulos = manejadoractivos.buscarActivosArticulos(query);
                   
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizaciongrillaasinc.jsp?mod=1");
                    deptos = manejadordeptos.getListaDepartamentos();
                    tipospago = manejadortpago.getListaTipoPago();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    request.setAttribute("listadoarticulos", articulos);
                    disp.forward(request, response);                    
                    break;

            }
        } catch (Exception ex) {
            System.out.print(ex.getMessage());
        }
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
        } else if (key.equals("bajaarticuloasinc")) {
            return OpcionesDo.BajarActivoArticuloAsinc;
        } else if (key.equals("modificacionpaginacionasinc")) {
            return OpcionesDo.ActualizarPropiedadPaginacionAsinc;
        } else if (key.equals("ver_activosarticulosasinc")) {
            return OpcionesDo.ObtenerActivosArticulosAsinc;
        }else if(key.equals("verificarsiactivoexiste")){
            return OpcionesDo.VerificarSiArticuloYaRegistrado;
        }else if(key.equals("busquedaarticulo")){
            return OpcionesDo.BusquedaArticulo;
        }
        return OpcionesDo.AccionDefault;
    }
}
