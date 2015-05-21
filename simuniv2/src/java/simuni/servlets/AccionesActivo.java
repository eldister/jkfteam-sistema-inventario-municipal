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
import simuni.clases.ln.ManejadorActivo;
import simuni.clases.ln.ManejadorBitacora;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.entidades.Activo;
import simuni.entidades.ActivoArticulo;
import simuni.entidades.ActivoTransporte;
import simuni.entidades.ImagenActivo;
import simuni.entidades.Notificacion;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoLlanta;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 * Este servelet controla las acciones que tienen qeu ver con el catálogo de
 * <strong>Activo</strong>
 * En este se controlan los get y post correspondientes a las solicitudes del
 * usuario y tambien la preparación de lo necesario para dar respuesta a la
 * solicitud. Entre las operaciones qeu se contemplan estan las básicas de
 * ingreso, modificación y eliminacion.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
@MultipartConfig
public class AccionesActivo extends HttpServlet {

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

        Listado, Existe_Activo, Existe_Placa, Existe_Consecutivo,
        Nuevo, Eliminar, Modificar, Modificar_Articulo,
        Modificar_Transporte, Query, Ver_Imagenes, Subida_Imagenes, Reporte_Activo,
        Eliminar_Articulo, Eliminar_Transporte, AccionDefault, Listado_Asinc, Query_Asinc
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
            ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
            RegistroBitacora registroBitacora;
            Respuesta respuesta = null;//objeto respuesta al usuario
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            String registro = "";//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            String nombreactivo = "";//campo txt
            ResultSet resultset = null;
            ManejadorActivo mactivo = new ManejadorActivo();
            boolean mostrar_inactivos = request.getParameter("mostrar_inactivos") != null;
            String query = request.getParameter("query");
            query = query == null ? "" : query;
            Activo activo_registro = null;
            switch (getOpcion(request.getParameter("proceso"))) {
                case Listado:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    //falta la solicitud
                    resultset = mactivo.busquedaActivo(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/activos/index.jsp");
                    request.setAttribute("paginacion", ((int) mactivo.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    request.setAttribute("query", query);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Listado de Activos", 
                            "Listado de Activo ");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    
                    break;
                case Listado_Asinc:

                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mactivo.busquedaActivo(query, desplazamiento, paginacion, mostrar_inactivos);//provisional
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/activos/_asinc/_asinc_index.jsp");
                    request.setAttribute("paginacion", ((int) mactivo.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("query", query);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Listado Asincrónico de Activos", 
                            "Listado Asincrónico de Activo ");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);                    
                    break;
                case Nuevo:

                    request.setAttribute("departamentos", mactivo.listadoDepartamento());
                    request.setAttribute("tipospago", mactivo.listadoTipoPago());
                    request.setAttribute("estados", mactivo.listadoEstado());
                    request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                    request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                    request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());

                    disp = request.getRequestDispatcher("/modulos/activos/nuevo.jsp");
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Cargar formulario para nuevo registro de Activo", 
                            "Nuevo registro de Activo (get)");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);                    
                    break;
                case Modificar:
                    registro = request.getParameter("registro");
                    if (mactivo.isRegistroArticulo(registro)) {
                        activo_registro = mactivo.getActivoArticulo(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoArticulo) activo_registro));
                        disp = request.getRequestDispatcher("/modulos/activos/editar_articulo.jsp");
                    }//el else debe tener su if
                    else if (mactivo.isRegistroTransporte(registro)) {
                        activo_registro = mactivo.getActivoTransporte(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoTransporte) activo_registro));
                        request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                        request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());
                        disp = request.getRequestDispatcher("/modulos/activos/editar_transporte.jsp");
                    }

                    request.setAttribute("departamentos", mactivo.listadoDepartamento());
                    request.setAttribute("tipospago", mactivo.listadoTipoPago());
                    request.setAttribute("estados", mactivo.listadoEstado());
                    request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Cargar formulario para modiicar registro de Activo", 
                            "Modificación de  registro de Activo (get)");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);         
                    break;
                case Reporte_Activo:
                    registro = request.getParameter("registro");
                    if (mactivo.isRegistroArticulo(registro)) {
                        System.out.println("Eaqui es articulo" + registro);
                        activo_registro = mactivo.getActivoArticulo(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoArticulo) activo_registro));
                        disp = request.getRequestDispatcher("/modulos/activos/reporte_articulo.jsp");
                        System.out.println("Entreeee aqui");
                                            registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Realizar un reporte de activo artículo", 
                            "Reporte de activo artículo");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);         
                        //disp.forward(request, response);
                    }//el else debe tener su if
                    else if (mactivo.isRegistroTransporte(registro)) {
                        activo_registro = mactivo.getActivoTransporte(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoTransporte) activo_registro));
                        request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                        request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());
                        disp = request.getRequestDispatcher("/modulos/activos/reporte_transporte.jsp");
                                            registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Reporte de activo transporte", 
                            "Reporte de activo transporte.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);         
                    }

                    request.setAttribute("departamentos", mactivo.listadoDepartamento());
                    request.setAttribute("tipospago", mactivo.listadoTipoPago());
                    request.setAttribute("estados", mactivo.listadoEstado());
                    request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());

                    break;
                case Eliminar:
                    registro = request.getParameter("registro");
                    if (mactivo.isRegistroArticulo(registro)) {
                        activo_registro = mactivo.getActivoArticulo(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoArticulo) activo_registro));
                        disp = request.getRequestDispatcher("/modulos/activos/eliminar_articulo.jsp");
                             registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Eliminación de activo artículo", 
                            "Eliminación de activo artículo.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);      
                    }//el else debe tener su if
                    else if (mactivo.isRegistroTransporte(registro)) {
                        activo_registro = mactivo.getActivoTransporte(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoTransporte) activo_registro));
                        request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                        request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());
                        disp = request.getRequestDispatcher("/modulos/activos/eliminar_transporte.jsp");
                                                     registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Eliminación de activo transporte", 
                            "Eliminación de activo transporte.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora); 
                    }

                    request.setAttribute("departamentos", mactivo.listadoDepartamento());
                    request.setAttribute("tipospago", mactivo.listadoTipoPago());
                    request.setAttribute("estados", mactivo.listadoEstado());
                    request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                    break;
                case AccionDefault:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mactivo.busquedaActivo(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/activos/index.jsp");
                    request.setAttribute("paginacion", ((int) mactivo.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("query", query);
                       registroBitacora = manejadorBitacora.generarRegistroBitacora(null, request,
                            "Listado de activos, default", 
                            "Listado de activos, default.");
                    manejadorBitacora.registrarEnBitacora(registroBitacora); 
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
        } else if (key.equals("chequeo_activo")) {
            return OpcionesDo.Existe_Activo;
        } else if (key.equals("chequeo_placa")) {
            return OpcionesDo.Existe_Placa;
        } else if (key.equals("chequeo_consecutivo")) {
            return OpcionesDo.Existe_Consecutivo;
        } else if (key.equals("actualizar_articulo")) {
            return OpcionesDo.Modificar_Articulo;
        } else if (key.equals("actualizar_transporte")) {
            return OpcionesDo.Modificar_Transporte;
        } else if (key.equals("ver_imagen")) {
            return OpcionesDo.Ver_Imagenes;
        } else if (key.equals("subida_imagen")) {
            return OpcionesDo.Subida_Imagenes;
        } else if (key.equals("reporte_activo")) {
            return OpcionesDo.Reporte_Activo;
        } else if (key.equals("eliminar_articulo")) {
            return OpcionesDo.Eliminar_Articulo;
        } else if (key.equals("eliminar_transporte")) {
            return OpcionesDo.Eliminar_Transporte;
        } else if (key.equals("listado_asinc")) {
            return OpcionesDo.Listado_Asinc;
        } else if (key.equals("query_asinc")) {
            return OpcionesDo.Query_Asinc;
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
        RequestDispatcher disp = null;
        ManejadorBitacora manejadorBitacora = ManejadorBitacora.getInstance();
        RegistroBitacora registroBitacora;
        try {
            String idusuario = request.getSession().getAttribute("USERNAME") == null ? null : request.getSession().getAttribute("USERNAME").toString();
            boolean mostrar_inactivos = request.getParameter("mostrar_inactivos") != null;
            int desplazamiento = 0;//movimiento hacia adelante en los queries
            int npagina = 0;//para la paginacion
            String registro = "";//codigo de registro a buscar
            int paginacion = 0;//obtener la paginacion y pagina actual  
            int registro_activo = 0;
            ResultSet resultset = null;
            ManejadorActivo mactivo = new ManejadorActivo();
            ManejadorNotificaciones mnotif = new ManejadorNotificaciones();
            Notificacion notificacion = null;
            ArrayList<Respuesta> respuetas = null;
            String query = request.getParameter("query");
            query = query == null ? "" : query;

            switch (getOpcion(request.getParameter("proceso"))) {
                case Nuevo:
                    Activo activo_registro = null;
                    boolean esVehiculo = false;
                    int tipo_registro = UtilidadesServlet.getInt(request.getParameter("sm_registroactivo_tipoproceso"), 0);
                    System.out.println("tipo de registro " + tipo_registro);
                    switch (tipo_registro) {
                        case 1:
                            activo_registro = generarActivoArticulo(request);
                            break;
                        case 2:
                            activo_registro = null;
                            break;
                        case 3:
                            activo_registro = generarActivoTransporte(request, response);
                            esVehiculo = true;
                            break;
                        case 4:
                            activo_registro = generarActivoTransporte(request, response);
                            esVehiculo = true;
                            break;
                        default:
                            activo_registro = null;
                            break;
                    }
                    respuetas = mactivo.registrarActivo(activo_registro, tipo_registro);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                            "Nuevo registro de activo del tipo " + tipo_registro, "Las respuestas vienen en un List");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se registra el activo " + activo_registro.getPlacaActivo() + " a la base de datos.");
                    mnotif.agregarNNotificacion(notificacion);
                    request.setAttribute("respuesta", respuetas);//resultado de las operaciones
                    request.setAttribute("registro", activo_registro);//el nuevo registro.
                    request.setAttribute("tipo_registro", tipo_registro);//dici si es carro, moto, normal

                    request.setAttribute("departamentos", mactivo.listadoDepartamento());
                    request.setAttribute("tipospago", mactivo.listadoTipoPago());
                    request.setAttribute("estados", mactivo.listadoEstado());
                    request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                    request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                    request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());

                    if (request.getAttribute("consecutivovehiculo") == null && esVehiculo) {
                        System.out.println("Lo pondre entonces ");
                        request.setAttribute("consecutivovehiculo", activo_registro != null ? ((ActivoTransporte) activo_registro).getCodigoActivoTransporte() : 0);

                    }
                    disp = request.getRequestDispatcher("/modulos/activos/nuevo.jsp");
                    disp.forward(request, response);
                    break;
                case Query_Asinc:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mactivo.busquedaActivo(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/activos/_asinc/_asinc_actualizarlistado.jsp");
                    request.setAttribute("paginacion", ((int) mactivo.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    disp.forward(request, response);
                    break;
                case Existe_Activo:
                    registro = request.getParameter("registro");
                    if (mactivo.existePlacaActivo(registro)) {
                        response.getWriter().print("Existe " + registro);
                    }
                    break;
                case Existe_Placa://placa vehiculo
                    registro = request.getParameter("registro");
                    if (mactivo.existePlacaVehiculo(registro)) {
                        response.getWriter().print("Existe " + registro);
                    }
                    break;

                case Existe_Consecutivo:
                    registro = request.getParameter("registro");
                    if (mactivo.existeConsecutivoTipoVehiculo(registro)) {
                        response.getWriter().print("Existe " + registro);
                    }
                    break;

                case Query:
                    npagina = UtilidadesServlet.getNumeroDePagina(request.getParameter("pag"), 0);
                    paginacion = UtilidadesServlet.getNumeroDePagina(request.getSession().getAttribute("paginacion"), 7);
                    desplazamiento = ((npagina) * paginacion);
                    resultset = mactivo.busquedaActivo(query, desplazamiento, paginacion, mostrar_inactivos);
                    request.setAttribute("listado", resultset);
                    disp = request.getRequestDispatcher("/modulos/activos/_asinc/_asinc_listar.jsp");
                    request.setAttribute("paginacion", ((int) mactivo.getCantidadRegistros(query, mostrar_inactivos) / paginacion) + 1);
                    request.setAttribute("mostrar_inactivos", mostrar_inactivos ? "checked" : "");
                    disp.forward(request, response);
                    break;
                case Modificar_Articulo:
                    registro = request.getParameter("registro");
                    registro_activo = UtilidadesServlet.getInt(request.getParameter("registro_articulo"), 0);
                    System.out.println(registro + " <<< este es");
                    if (mactivo.isRegistroArticulo(registro)) {
                        System.out.println("Entreeee");
                        activo_registro = generarActivoArticulo(request);
                        activo_registro.setPlacaActivo(registro);
                        ((ActivoArticulo) activo_registro).setCodigoActivoArticulo(registro_activo);
                        //hacer la actualizacion***
                        respuetas = mactivo.actualizarActivoArticulo(((ActivoArticulo) activo_registro));
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                "Modificación de registro de activo del tipo artículo", "Las respuestas vienen en un List");
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se modificar el activo " + activo_registro.getPlacaActivo() + " (art) de la base de datos.");
                        mnotif.agregarNNotificacion(notificacion);
                        activo_registro = mactivo.getActivoArticulo(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoArticulo) activo_registro));
                        request.setAttribute("respuesta", respuetas);//resultado de las operaciones

                        request.setAttribute("departamentos", mactivo.listadoDepartamento());
                        request.setAttribute("tipospago", mactivo.listadoTipoPago());
                        request.setAttribute("estados", mactivo.listadoEstado());
                        request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());

                        disp = request.getRequestDispatcher("/modulos/activos/editar_articulo.jsp");
                        disp.forward(request, response);
                    }//el else debe tener su if
                    else {
                        System.out.println("`No entreeee");
                    }

                    break;
                case Modificar_Transporte:
                    registro = request.getParameter("registro");
                    registro_activo = UtilidadesServlet.getInt(request.getParameter("registro_transporte"), 0);
                    System.out.println(registro + " <<< este es");
                    if (mactivo.isRegistroTransporte(registro)) {
                        System.out.println("Entreeee");
                        activo_registro = generarActivoTransporte(request, response);
                        activo_registro.setPlacaActivo(registro);
                        ((ActivoTransporte) activo_registro).setCodigoActivoTransporte(registro_activo);
                        //hacer la actualizacion*** cambiar
                        respuetas = mactivo.actualizarActivoTransporte(((ActivoTransporte) activo_registro));
                        registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                "Modificación de registro de activo del tipo transporte", "Las respuestas vienen en un List");
                        manejadorBitacora.registrarEnBitacora(registroBitacora);
                        notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se actualiza  el activo " + activo_registro.getPlacaActivo() + " (tra) de la base de datos.");
                        mnotif.agregarNNotificacion(notificacion);
                        activo_registro = mactivo.getActivoTransporte(registro);
                        activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                        request.setAttribute("registro", ((ActivoTransporte) activo_registro));
                        request.setAttribute("respuesta", respuetas);//resultado de las operaciones

                        request.setAttribute("departamentos", mactivo.listadoDepartamento());
                        request.setAttribute("tipospago", mactivo.listadoTipoPago());
                        request.setAttribute("estados", mactivo.listadoEstado());
                        request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                        request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                        request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());
                        disp = request.getRequestDispatcher("/modulos/activos/editar_transporte.jsp");
                        disp.forward(request, response);
                    }//el else debe tener su if                                     
                    break;
                case Ver_Imagenes:
                    registro = request.getParameter("registro");
                    request.setAttribute("imagenes", mactivo.getImagenesActivo(registro));
                    request.setAttribute("registro", registro);
                    disp = request.getRequestDispatcher("/modulos/activos/_asinc/_asinc_verimagenes.jsp");
                    disp.forward(request, response);
                    break;
                case Subida_Imagenes:

                    activo_registro = generarActivo(request);
                    respuetas = mactivo.agregarImagenesActivo(activo_registro);
                    registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                            "Agregar imágenes de activo", "Las respuestas vienen en un List. Se registraron "
                            + (activo_registro.getImagenes()) != null ? activo_registro.getImagenes().size() + "" : "0");
                    manejadorBitacora.registrarEnBitacora(registroBitacora);
                    notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se registra imagenes del activo  " + activo_registro.getPlacaActivo() + " a la base de datos.");
                    mnotif.agregarNNotificacion(notificacion);
                    registro = activo_registro.getPlacaActivo();
                    request.setAttribute("respuesta", respuetas);//resultado de las operaciones
                    request.setAttribute("registro", registro);
                    request.setAttribute("imagenes", mactivo.getImagenesActivo(registro));
                    disp = request.getRequestDispatcher("/modulos/activos/_asinc/_asinc_verimagenes.jsp");
                    disp.forward(request, response);
                    break;
                case Eliminar_Articulo:
                    registro = request.getParameter("registro");
                    registro_activo = UtilidadesServlet.getInt(request.getParameter("registro_articulo"), 0);
                    System.out.println(registro + " <<< este es");
                    if (mactivo.isRegistroArticulo(registro)) {
                        System.out.println("Entreeee");
                        activo_registro = new ActivoArticulo();

                        activo_registro.setPlacaActivo(registro);
                        ((ActivoArticulo) activo_registro).setCodigoActivoArticulo(registro_activo);
                        //hacer la eliminacion***
                        if (request.getParameter("eliminar_completo") != null) {
                            respuetas = mactivo.eliminarActivoArticulo(((ActivoArticulo) activo_registro));
                            request.setAttribute("eliminar_completo", true);
                            registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                    "Eliminar Activo Articulo", "La eliminación fue completa de la placa " + activo_registro.getPlacaActivo());
                            manejadorBitacora.registrarEnBitacora(registroBitacora);
                            notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se elimina el activo " + activo_registro.getPlacaActivo() + " (art) de la base de datos.");
                            mnotif.agregarNNotificacion(notificacion);
                        } else {
                            respuetas = mactivo.desactivarActivoArticulo(((ActivoArticulo) activo_registro));
                            activo_registro = mactivo.getActivoArticulo(registro);
                            activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                            registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                    "Eliminar Activo Articulo", "La eliminación no fue completa de la placa " + activo_registro.getPlacaActivo());
                            manejadorBitacora.registrarEnBitacora(registroBitacora);
                            notificacion = mnotif.generarRegistroNotificacion(idusuario, "Eliminación fallida del activo " + activo_registro.getPlacaActivo() + " de la base de datos.");
                            mnotif.agregarNNotificacion(notificacion);
                        }

                        request.setAttribute("registro", ((ActivoArticulo) activo_registro));
                        request.setAttribute("respuesta", respuetas);//resultado de las operaciones

                        request.setAttribute("departamentos", mactivo.listadoDepartamento());
                        request.setAttribute("tipospago", mactivo.listadoTipoPago());
                        request.setAttribute("estados", mactivo.listadoEstado());
                        request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());

                        disp = request.getRequestDispatcher("/modulos/activos/eliminar_articulo.jsp");
                        disp.forward(request, response);
                    }//el else debe tener su if
                    else {
                        System.out.println("`No entreeee");
                    }
                    break;
                case Eliminar_Transporte:
                    registro = request.getParameter("registro");
                    registro_activo = UtilidadesServlet.getInt(request.getParameter("registro_transporte"), 0);
                    System.out.println(registro + " <<< este es");
                    if (mactivo.isRegistroTransporte(registro)) {
                        System.out.println("Entreeee");
                        activo_registro = generarActivoTransporte(request, response);
                        activo_registro.setPlacaActivo(registro);
                        ((ActivoTransporte) activo_registro).setCodigoActivoTransporte(registro_activo);
                        //hacer la eliminacion*** cambiar
                        //  respuetas = mactivo.actualizarActivoTransporte(((ActivoTransporte) activo_registro));
                        if (request.getParameter("eliminar_completo") != null) {
                            respuetas = mactivo.eliminarActivoTransporte(((ActivoTransporte) activo_registro));
                            request.setAttribute("eliminar_completo", true);
                            registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                    "Eliminar Activo Transporte", "La eliminación  fue completa de la placa " + activo_registro.getPlacaActivo());
                            manejadorBitacora.registrarEnBitacora(registroBitacora);
                            notificacion = mnotif.generarRegistroNotificacion(idusuario, "Se elimina el activo " + activo_registro.getPlacaActivo() + " (tra) de la base de datos.");
                            mnotif.agregarNNotificacion(notificacion);

                        } else {
                            respuetas = mactivo.desactivarActivoTransporte(((ActivoTransporte) activo_registro));
                            activo_registro = mactivo.getActivoTransporte(registro);
                            activo_registro.setImagenes(mactivo.getImagenesActivo(registro));
                            registroBitacora = manejadorBitacora.generarRegistroBitacora(respuetas.get(0), request,
                                    "Eliminar Activo Transporte", "La eliminación no fue completa de la placa " + activo_registro.getPlacaActivo());
                            manejadorBitacora.registrarEnBitacora(registroBitacora);
                            notificacion = mnotif.generarRegistroNotificacion(idusuario, "Fallida la eliminación el activo " + activo_registro.getPlacaActivo() + " (tra) de la base de datos.");
                            mnotif.agregarNNotificacion(notificacion);
                        }

                        request.setAttribute("registro", ((ActivoTransporte) activo_registro));
                        request.setAttribute("respuesta", respuetas);//resultado de las operaciones

                        request.setAttribute("departamentos", mactivo.listadoDepartamento());
                        request.setAttribute("tipospago", mactivo.listadoTipoPago());
                        request.setAttribute("estados", mactivo.listadoEstado());
                        request.setAttribute("tiposactivo", mactivo.listadoTipoActivo());
                        request.setAttribute("tiposbateria", mactivo.listadoTipoBateria());
                        request.setAttribute("tiposllanta", mactivo.listadoTipoLlanta());
                        disp = request.getRequestDispatcher("/modulos/activos/eliminar_transporte.jsp");
                        disp.forward(request, response);
                    }//el else debe tener su if                       
                    break;

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            disp = request.getRequestDispatcher("/recursos/paginas/error/errorpage.jsp");
            disp.forward(request, response);
            //redirigir a pagian de error de sistema
            registroBitacora = manejadorBitacora.generarRegistroBitacora(new Respuesta(), request,
                    "Error de procesamiento en el post", ex.getMessage());
            manejadorBitacora.registrarEnBitacora(registroBitacora);
        }

    }

    /**
     * Función que permite obtener un objeto de Activo a partir de la soliciitud
     * que el usuario realiza y que el servidor recibe, esto para la operacion
     * de registro. Si los campos no son correctos, se completaran con nulos o
     * con -1 en caso de ser numéricos.
     *
     * @param request el objeto que contiene el dato de la solicitud.
     * @return un objeto Activo para su uso.
     * @since 1.0
     */
    private Activo generarActivo(HttpServletRequest request) {
        Activo activo = new Activo();
        try {

            activo.setPlacaActivo(request.getParameter("registro"));

            //parte de los archivos
            Collection<Part> parts = request.getParts();
            if (parts != null) {
                Iterator<Part> iteradorparts = parts.iterator();
                if (iteradorparts != null) {
                    long fechaActual = System.currentTimeMillis();
                    while (iteradorparts.hasNext()) {
                        Part parte = iteradorparts.next();
                        String nombrearchivo = UtilidadesServlet.getFilename(parte);
                        if (parte != null && nombrearchivo != null && nombrearchivo.length() > 1) {
                            ImagenActivo doc = new ImagenActivo();
                            doc.setNombreArchivo(nombrearchivo);
                            doc.setServerArchivo(Recursos.Servers.SERVER_ARCHIVOS.toString());
                            doc.setStreamArchivo(parte.getInputStream());
                            doc.setFechaSubida(new Date());
                            //se le indica el nombre de el archivo imagen. esto para colocar el path y el url.

                            String ruta = Recursos.SSA.CARPETARAIZARCHIVOSACTIVOS.toString() + activo.getPlacaActivo() + "\\" + fechaActual;
                            String url = Recursos.SSI.ARCHIVOSACTIVOSCONTEXT.toString() + activo.getPlacaActivo() + "/" + fechaActual + "/";
                            doc.setPathDocumento(ruta);
                            doc.setUrldocumento(url);
                            doc.setCodigoActivo(activo.getPlacaActivo());//la placa del activo o su id
                            //se agrega la imagen a la coleccion
                            activo.agregarImagenActivo(doc);
                            System.out.println("Uno a ver si entre" + nombrearchivo);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
        return activo;
    }

    /**
     * Función que permite obtener un objeto de ActivoArticulo a partir de la
     * soliciitud que el usuario realiza y que el servidor recibe, esto para la
     * operacion de registro. Si los campos no son correctos, se completaran con
     * nulos o con -1 en caso de ser numéricos.
     *
     * @param request el objeto que contiene el dato de la solicitud.
     * @return un objeto ActivoArticulo para su uso.
     * @since 1.0
     */
    private ActivoArticulo generarActivoArticulo(HttpServletRequest request) {
        ActivoArticulo activo_articulo = new ActivoArticulo();
        try {
            activo_articulo.setCodigoDepto(UtilidadesServlet.getInt(request.getParameter("cmbdepartamento"), 0));
            activo_articulo.setCodigoEstado(UtilidadesServlet.getInt(request.getParameter("cmbestadoactivo"), 0));
            activo_articulo.setCodigoProveedor(request.getParameter("hddproveedor"));
            activo_articulo.setCodigoTipoActivo(UtilidadesServlet.getInt(request.getParameter("cmbtipoactivo"), 0));
            activo_articulo.setCodigoTipoPago(UtilidadesServlet.getInt(request.getParameter("cmbtipopago"), 0));
            activo_articulo.setDenominacion(request.getParameter("cmbdenominacion"));
            activo_articulo.setFechaAdquisicion(UtilidadesServlet.getFecha(request.getParameter("txtfechacompra"), new Date()));
            activo_articulo.setFechaInicioOperacion(UtilidadesServlet.getFecha(request.getParameter("txtfechainicio"), new Date()));

            activo_articulo.setMarca(request.getParameter("txtmarca"));
            activo_articulo.setModelo(request.getParameter("txtmodelo"));
            activo_articulo.setObservaciones(request.getParameter("txtobservaciones"));
            activo_articulo.setPlacaActivo(request.getParameter("txtplacaactivo"));
            activo_articulo.setPorcentajeDepreciacion(UtilidadesServlet.getDouble(request.getParameter("txtdepreciacion"), 0));//que sea double
            activo_articulo.setPorcentajeRescate(UtilidadesServlet.getDouble(request.getParameter("txtrescate"), 0));
            activo_articulo.setPrecio(UtilidadesServlet.getDouble(request.getParameter("txtprecio"), 0));

            //parte de los archivos
            Collection<Part> parts = request.getParts();
            if (parts != null) {
                Iterator<Part> iteradorparts = parts.iterator();
                if (iteradorparts != null) {
                    while (iteradorparts.hasNext()) {
                        Part parte = iteradorparts.next();
                        String nombrearchivo = UtilidadesServlet.getFilename(parte);
                        if (parte != null && nombrearchivo != null && nombrearchivo.length() > 1) {
                            ImagenActivo doc = new ImagenActivo();
                            doc.setNombreArchivo(nombrearchivo);
                            doc.setServerArchivo(Recursos.Servers.SERVER_ARCHIVOS.toString());
                            doc.setStreamArchivo(parte.getInputStream());
                            doc.setFechaSubida(new Date());
                            //se le indica el nombre de el archivo imagen. esto para colocar el path y el url.
                            long fechaActual = System.currentTimeMillis();
                            String ruta = Recursos.SSA.CARPETARAIZARCHIVOSACTIVOS.toString() + activo_articulo.getPlacaActivo() + "\\" + fechaActual;
                            String url = Recursos.SSI.ARCHIVOSACTIVOSCONTEXT.toString() + activo_articulo.getPlacaActivo() + "/" + fechaActual + "/";
                            doc.setPathDocumento(ruta);
                            doc.setUrldocumento(url);
                            doc.setCodigoActivo(activo_articulo.getPlacaActivo());//la placa del activo o su id
                            //se agrega la imagen a la coleccion
                            activo_articulo.agregarImagenActivo(doc);
                            System.out.println("Uno a ver si entre" + nombrearchivo);
                        }
                    }
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return activo_articulo;
    }

    /**
     * Función que permite obtener un objeto de ActivoTransporte a partir de la
     * soliciitud que el usuario realiza y que el servidor recibe, esto para la
     * operacion de registro. Si los campos no son correctos, se completaran con
     * nulos o con -1 en caso de ser numéricos.
     *
     * @param request el objeto que contiene el dato de la solicitud.
     * @return un objeto ActivoTrasporte para su uso.
     * @since 1.0
     */
    private ActivoTransporte generarActivoTransporte(HttpServletRequest request, HttpServletResponse response) {
        ActivoTransporte activo_transporte = new ActivoTransporte();
        try {
            activo_transporte.setAnioFabrica(UtilidadesServlet.getFecha(request.getParameter("txtfechafabricacion"), null));
            activo_transporte.setCilindros(request.getParameter("txtcilindros"));
            activo_transporte.setCodigoBipoBateria(UtilidadesServlet.getInt(request.getParameter("cmbtipobateria"), 0));
            activo_transporte.setCodigoDepto(UtilidadesServlet.getInt(request.getParameter("cmbdepartamento"), 0));
            activo_transporte.setCodigoEstado(UtilidadesServlet.getInt(request.getParameter("cmbestadoactivo"), 0));
            activo_transporte.setCodigoTipoActivo(UtilidadesServlet.getInt(request.getParameter("cmbtipoactivo"), 0));
            activo_transporte.setCodigoTipoPago(UtilidadesServlet.getInt(request.getParameter("cmbtipopago"), 0));
            activo_transporte.setDenominacion(request.getParameter("cmbdenominacion"));
            activo_transporte.setFechaAdquisicion(UtilidadesServlet.getFecha(request.getParameter("txtfechacompra"), null));
            activo_transporte.setFechaInicio(UtilidadesServlet.getFecha(request.getParameter("txtfechainicio"), null));//inicio de operacion
            activo_transporte.setImagenes(null);
            activo_transporte.setLlantas(null);
            activo_transporte.setMarca(request.getParameter("txtmarca"));
            activo_transporte.setModelo(request.getParameter("txtmodelo"));
            activo_transporte.setNumeroChasis(request.getParameter("txtnumerochasis"));
            activo_transporte.setNumeroMotor(request.getParameter("txtnumeromotor"));
            activo_transporte.setObservaciones(request.getParameter("txtobservaciones"));
            activo_transporte.setObservacionesTecnicas(request.getParameter("txtobservacionestecnicas"));
            activo_transporte.setPlaca(request.getParameter("txtplacavehiculo"));
            activo_transporte.setPlacaActivo(request.getParameter("txtplacaactivo"));
            activo_transporte.setPorcentajeDepreciacion(UtilidadesServlet.getDouble(request.getParameter("txtdepreciacion"), 0));//que sea double
            activo_transporte.setPorcentajeRescate(UtilidadesServlet.getDouble(request.getParameter("txtrescate"), 0));
            activo_transporte.setPrecio(UtilidadesServlet.getDouble(request.getParameter("txtprecio"), 0));
            activo_transporte.setTipoVehiculo(request.getParameter("cmbtipovehiculo"));

            //parte de auto generar o no tipo de vehiculo.
            if (request.getParameter("sm_idvehiculoautogenerado") == null) {
                //no auto generado       
                activo_transporte.setTipoVehiculo(activo_transporte.getTipoVehiculo() + request.getParameter("txtconsecutivovehiculo"));
                request.setAttribute("consecutivovehiculo", request.getParameter("txtconsecutivovehiculo"));
                System.out.println("el consecutivo elegido es " + request.getAttribute("consecutivovehiculo"));
                //si no entra entonces se auto generarara
            }

            //generar imagenes del activo si las hay 
            //parte de los archivos
            Collection<Part> parts = request.getParts();
            if (parts != null) {
                Iterator<Part> iteradorparts = parts.iterator();
                if (iteradorparts != null) {
                    while (iteradorparts.hasNext()) {
                        Part parte = iteradorparts.next();
                        String nombrearchivo = UtilidadesServlet.getFilename(parte);
                        if (parte != null && nombrearchivo != null && nombrearchivo.length() > 1) {
                            ImagenActivo doc = new ImagenActivo();
                            doc.setNombreArchivo(nombrearchivo);
                            doc.setServerArchivo(Recursos.Servers.SERVER_ARCHIVOS.toString());
                            doc.setStreamArchivo(parte.getInputStream());
                            doc.setFechaSubida(new Date());
                            long fechaActual = System.currentTimeMillis();
                            String ruta = Recursos.SSA.CARPETARAIZARCHIVOSACTIVOS.toString() + activo_transporte.getPlacaActivo() + "\\" + fechaActual;
                            String url = Recursos.SSI.ARCHIVOSACTIVOSCONTEXT.toString() + activo_transporte.getPlacaActivo() + "/" + fechaActual + "/";
                            doc.setPathDocumento(ruta);
                            doc.setUrldocumento(url);
                            doc.setCodigoActivo(activo_transporte.getPlacaActivo());//la placa del activo o su id
                            activo_transporte.agregarImagenActivo(doc);

                            System.out.println("Uno a ver si entre" + nombrearchivo);
                        }
                    }
                }
            }

            //generar lo del tipo de ruedas o llantas
            int tipovehiculo = UtilidadesServlet.getInt(request.getParameter("sm_registroactivo_tipoproceso"), -1);
            if (tipovehiculo == 3) {
                System.out.println("Es una motooooo");
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantad"), 0), "Delantera"));
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantat"), 0), " Trasera"));
                //hacemos la respectiva devolucion al response para recuperacion en errores.
                request.setAttribute("cmbtipollantad", request.getParameter("cmbtipollantad"));
                request.setAttribute("cmbtipollantat", request.getParameter("cmbtipollantat"));
            } else {
                System.out.println("Es un vehiculo normal " + tipovehiculo);
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantadd"), 0), "Delantera Derecha"));
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantadi"), 0), " Delantera Izquierda"));
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantatd"), 0), "Trasera Derecha"));
                activo_transporte.agregarTipoLlanta(new TipoLlanta(UtilidadesServlet.getInt(request.getParameter("cmbtipollantati"), 0), "Trasera Izquierda"));
                //hacemos la respectiva devolucion al response para recuperacion en errores. 
                request.setAttribute("cmbtipollantadd", request.getParameter("cmbtipollantadd"));
                request.setAttribute("cmbtipollantadi", request.getParameter("cmbtipollantadi"));
                request.setAttribute("cmbtipollantatd", request.getParameter("cmbtipollantatd"));
                request.setAttribute("cmbtipollantati", request.getParameter("cmbtipollantati"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return activo_transporte;
    }

}
