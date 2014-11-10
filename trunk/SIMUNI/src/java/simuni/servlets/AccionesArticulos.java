package simuni.servlets;

import java.io.IOException;
import java.io.InputStream;
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
import simuni.classes.EN.Notificacion;
import simuni.classes.EN.TipoActivo;
import simuni.classes.EN.TipoPago;
import simuni.classes.EN.imagenActivo;
import simuni.classes.LN.ManejadorActivos;
import simuni.classes.LN.ManejadorDepartamentos;
import simuni.classes.LN.ManejadorEstadoActivos;
import simuni.classes.LN.ManejadorNotificaciones;
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
        BusquedaArticulo,
        HacerReporteActivoArticuloParticular,
        ObtenerImagenesActivoAsinc

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
        //aqui se manejara los procesos  

        ManejadorActivos manejador = new ManejadorActivos();
        ManejadorDepartamentos manejadordeptos = new ManejadorDepartamentos();
        ManejadorTipoActivo manejadortactivos = new ManejadorTipoActivo();
        ManejadorTipoPago manejadortpago = new ManejadorTipoPago();
        ManejadorEstadoActivos manejadorestadoactivo = new ManejadorEstadoActivos();
        ArrayList<Departamento> deptos = null;
        ArrayList<TipoActivo> tiposactivos = null;
        ArrayList<TipoPago> tipospago = null;
        ArrayList<EstadoActivo> estadoactivos = null;
        ArrayList<Activos_Articulos> articulos = null;
        ArrayList<imagenActivo> imagenes = null;
        Activos_Articulos articulo = null;
        RequestDispatcher disp = null;
        int npagina = 0;
        int paginacion = 0;//obtener la paginacion y pagina actual      
        int desplazamiento = 0;
        String codigoactivo = "";
        switch (getOpcion(request.getParameter("proceso"))) {
            /*parte uno es para los activos articulos*/

            case ObtenerActivosArticulos:
                try {
                    npagina = getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);

                    desplazamiento = ((npagina) * paginacion);
                    System.out.println("Desplazamiento" + desplazamiento);
                    articulos = manejador.getListaArticulos(desplazamiento, paginacion);
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    disp = request.getRequestDispatcher("/modulos/activos/articulos/mantenimiento_1.jsp");
                    request.setAttribute("listadoarticulos", articulos);
                    request.setAttribute("listadotiposactivo", tiposactivos);
                    request.setAttribute("paginacion", ((int) manejador.getCantidadRegistrosActivosArticulos() / paginacion) + 1);

                    disp.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
                break;

            case ActualizarActivoArticulo:
                try {
                    deptos = manejadordeptos.getListaDepartamentos();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    tipospago = manejadortpago.getListaTipoPago();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    codigoactivo = request.getParameter("codigoactivo");
                    articulo = manejador.getActivoArticulo(codigoactivo);
                    //hay que crear la vista
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizacionactivoarticulo.jsp");
                    request.setAttribute("articulo", articulo);
                    disp.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
                break;

            case RegistrarActivoArticulo:
                try {
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
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
                break;
            case VerificarSiArticuloYaRegistrado:
                try {
                    manejador = new ManejadorActivos();
                    codigoactivo = request.getParameter("codigoactivo");
                    if (manejador.isActivoExistente(codigoactivo)) {
                        response.getOutputStream().print("Si Existe");

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
                break;
            case HacerReporteActivoArticuloParticular:
                try {
                    deptos = manejadordeptos.getListaDepartamentos();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    tipospago = manejadortpago.getListaTipoPago();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    codigoactivo = request.getParameter("codigoactivo");
                    articulo = manejador.getActivoArticulo(codigoactivo);
                    //hay que crear la vista
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/reportesgeneral_asinc.jsp?mod=1");
                    request.setAttribute("articulo", articulo);
                    disp.forward(request, response);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
                break;
            case ObtenerImagenesActivoAsinc:
                try {
                    codigoactivo = request.getParameter("codigoactivo");
                    imagenes = manejador.getListaImagenesActivo(codigoactivo);
                    //hay que crear la vista
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/vistaimagenesactivos.jsp");
                    request.setAttribute("imagenes", imagenes);
                    request.setAttribute("codigoactivo", codigoactivo);
                    disp.forward(request, response);
                    break;
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.print(ex.getMessage());
                    disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + ex.getMessage() + "&msg=4");
                    disp.forward(request, response);
                }
            default:
                //redirigir a pagina de error
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
        System.out.println("entre al post" + getOpcion(request.getParameter("proceso")));;
        InputStream filecontent = null;
        RequestDispatcher disp = null;
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
            switch (getOpcion(request.getParameter("proceso"))) {

                case RegistrarActivoArticulo:
                    imagenActivo imagenactivo = new imagenActivo();
                    ArrayList<imagenActivo> listadoimagen = new ArrayList<imagenActivo>();
                    activoarticulo = generarActivoArticulo(request);
                    filePart = request.getPart("fileimagenactivo"); // Retrieves <input type="file" name="file">                  
                    System.out.println("veamooooooooos  " + filePart.getSize());
                    if (filePart != null && filePart.getSize() > 0) {
                        filecontent = filePart.getInputStream();
                        filename = UtilidadesServlet.getFilename(filePart);
                        //instanciamos para las imagenes
                        imagenactivo = new imagenActivo();
                        imagenactivo.setPa_nombreArchivo(filename);
                        imagenactivo.setStreamarchivo(filecontent);
                        listadoimagen = new ArrayList<imagenActivo>();
                        listadoimagen.add(imagenactivo);
                        activoarticulo.setPo_imagenActivo(listadoimagen);

                    } else {
                        activoarticulo.setPo_imagenActivo(null);
                    }

                    //falta imagen
                    //hacer el proceso de registro
                    if (manejadoractivos.agregarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        agregarNotificacion(request.getSession().getAttribute("USERNAME").toString(), "Activo " + activoarticulo.getPa_identificadorActivo() + " agregado a la base de datos");
                        disp.forward(request, response);

                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=1");
                        disp.forward(request, response);
                    }
                    break;

                case ActualizarActivoArticulo:
                    activoarticulo = generarActivoArticulo(request);
                    filePart = request.getPart("fileimagenactivo"); // Retrieves <input type="file" name="file">                  
                    System.out.println("veamooooooooos  " + filePart.getSize());
                    if (filePart != null && filePart.getSize() > 0) {
                        filecontent = filePart.getInputStream();
                        filename = UtilidadesServlet.getFilename(filePart);
                        //instanciamos para las imagenes
                        imagenactivo = new imagenActivo();
                        imagenactivo.setPa_nombreArchivo(filename);
                        imagenactivo.setStreamarchivo(filecontent);
                        listadoimagen = new ArrayList<imagenActivo>();
                        listadoimagen.add(imagenactivo);
                        activoarticulo.setPo_imagenActivo(listadoimagen);

                    } else {
                        activoarticulo.setPo_imagenActivo(null);
                    }

                    //falta imagen
                    //hacer el proceso de registro
                    if (manejadoractivos.modificarActivoArticulo(activoarticulo)) {
                        //redirigir a una pagina de exito
                        System.out.println("Entre almenos");
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        agregarNotificacion(request.getSession().getAttribute("USERNAME").toString(), "Activo " + activoarticulo.getPa_identificadorActivo() + " modificado a la base de datos");
                        disp.forward(request, response);

                    } else {
                        System.out.println("Algo paso");
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + activoarticulo.getPa_identificadorActivo() + "&msg=2");
                        disp.forward(request, response);
                    }
                    break;

                case BajarActivoArticuloAsinc:

                    String codigoactivo = request.getParameter("codigoactivo");
                    if (manejadoractivos.desactivarActivoArticulo(codigoactivo)) {
                        //redirigir a una pagina de exito
                        System.out.println(codigoactivo);
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/exito_asinc.jsp?id=" + codigoactivo + "&msg=3");
                        agregarNotificacion(request.getSession().getAttribute("USERNAME").toString(), "Activo " + activoarticulo.getPa_identificadorActivo() + " bajado de la base de datos");
                        disp.forward(request, response);
                    } else {
                        //redirigir a pagina de error y/o recargar el formulario
                        disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + codigoactivo + "&msg=3");
                        disp.forward(request, response);
                    }
                    break;
                case ActualizarPropiedadPaginacionAsinc:
                    //vamos por aqui
                    String valorpaginacion = request.getParameter("valorpaginacion");
                    paginacion = getNumeroDePagina(valorpaginacion, 5);
                    //registrar error en base de datos
                    request.getSession().setAttribute("paginacion", paginacion);

                    break;
                case ObtenerActivosArticulosAsinc:
                    npagina = getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    request.getSession().setAttribute("paginacion", paginacion);
                    int desplazamiento = ((npagina - 1) * paginacion);
                    articulos = manejadoractivos.getListaArticulos(desplazamiento, paginacion);
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
                    npagina = getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    System.out.println("entre a la busqueda");
                    request.getSession().setAttribute("paginacion", paginacion);

                    String query = request.getParameter("query");
                    System.out.print(query);

                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/actualizaciongrillaasinc.jsp?mod=1");
                    deptos = manejadordeptos.getListaDepartamentos();
                    tipospago = manejadortpago.getListaTipoPago();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    articulos = manejadoractivos.buscarActivosArticulos(query, npagina, paginacion);
                    request.setAttribute("listadoarticulos", articulos);
                    disp.forward(request, response);
                    break;
                case HacerReporteActivoArticuloParticular:
                    deptos = manejadordeptos.getListaDepartamentos();
                    tiposactivos = manejadortactivos.getListaTiposActivos();
                    tipospago = manejadortpago.getListaTipoPago();
                    estadoactivos = manejadorestadoactivo.getListadoEstadosActivos();
                    request.setAttribute("departamentos", deptos);
                    request.setAttribute("tiposactivo", tiposactivos);
                    request.setAttribute("tipospago", tipospago);
                    request.setAttribute("estadoactivos", estadoactivos);
                    codigoactivo = request.getParameter("codigoactivo");
                    Activos_Articulos articulo = manejadoractivos.getActivoArticulo(codigoactivo);
                    //hay que crear la vista
                    disp = request.getRequestDispatcher("/recursos/paginas/embebidos/reportesgeneral_asinc.jsp?mod=1");
                    request.setAttribute("articulo", articulo);
                    disp.forward(request, response);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.print(ex.getMessage());
            disp = request.getRequestDispatcher("/recursos/paginas/notificaciones/error_asinc.jsp?id=" + ex.getMessage() + "&msg=4");
            disp.forward(request, response);
        }
    }

    private Activos_Articulos generarActivoArticulo(HttpServletRequest request) {
        //declarar e iniciar variables
        Activos_Articulos activoarticulo = new Activos_Articulos();
        java.sql.Date date = null;
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("El tipo de pago es " + idtipopago);
        //darles las caracteristicas al activo articulo, exceptuando las imagenes qeu solo en agregar y en modificar
        activoarticulo.setPa_Descripcion(descripcion);
        activoarticulo.setPa_Estado(estadoactivo);
        activoarticulo.setPa_Observaciones(observaciones);
        activoarticulo.setPa_identificadorActivo(numeroplaca);
        activoarticulo.setPa_modelo(modeloactivo);
        activoarticulo.setPa_marca(marcaactivo);
        activoarticulo.setPa_nombreproveedor("No definido");

        activoarticulo.setPa_codigoProveedor(idproveedor);
        activoarticulo.setPa_tipoActivo(Integer.parseInt(categoriaactivo));//corregir, aqui es int    
        activoarticulo.setPa_tipoPago(Integer.parseInt(idtipopago));//corregir, aqui es int

        activoarticulo.setPb_porcentajeDepreciacion(Double.parseDouble(porcentajedepreciacion));
        activoarticulo.setPb_depreciacion(Double.parseDouble(porcentajedepreciacion));
        activoarticulo.setPb_porcentajeRescate(Double.parseDouble(porcentajerescate));

        activoarticulo.setPd_precioCompra(Double.parseDouble(preciocompra));
        Departamento depto = new Departamento();
        //instanciamos depto
        depto.setPn_codigo(Integer.parseInt(iddepto));
        activoarticulo.setPo_depto(depto);
        try {
            //hacer validaciones si es entero.
            date = new java.sql.Date(formatter.parse(fechacompraactivo).getTime());
            activoarticulo.setPd_fechaCompra(date);
            date = new java.sql.Date(formatter.parse(fechainiciooperacion).getTime());
            activoarticulo.setPd_puestaOperacion(date);
            activoarticulo.setPn_anioFabricacion(2013);
            activoarticulo.setPn_aniosutilidadactivo(5);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        return activoarticulo;
    }

    private void agregarNotificacion(String usuarioorigen, String descripcion) {
        Notificacion notificacion = new Notificacion();
        notificacion.setDescripcionNotificacion(descripcion);
        notificacion.setEstadoNotificacion("Activo");
        notificacion.setUsuarioOrigen(usuarioorigen);
        notificacion.setUsuarioObjetivo(1);
        ManejadorNotificaciones manejadornotificaciones = new ManejadorNotificaciones();
        manejadornotificaciones.agregarNotificacion(notificacion);
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
        } else if (key.equals("verificarsiactivoexiste")) {
            return OpcionesDo.VerificarSiArticuloYaRegistrado;
        } else if (key.equals("busquedaarticulo")) {
            return OpcionesDo.BusquedaArticulo;
        } else if (key.equals("hacerreporteactivoparticular")) {
            return OpcionesDo.HacerReporteActivoArticuloParticular;
        } else if (key.equals("obtenerimagenesactivo")) {
            return OpcionesDo.ObtenerImagenesActivoAsinc;
        }
        return OpcionesDo.AccionDefault;
    }

    private int getNumeroDePagina(Object str, int respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseInt(str.toString())) {
            return Integer.parseInt(str.toString())-1;
        } else {
            return respaldo;
        }
    }

}
