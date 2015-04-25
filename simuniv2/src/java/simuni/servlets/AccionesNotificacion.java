package simuni.servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorNotificaciones;
import simuni.entidades.Notificacion;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase se encarga de realizar el manejo de las solicitudes del modulo de
 * Notificaciones. Acepta solicitudes del tipo POST o GET. Se encarga de dar una
 * respuesta a esas demandas.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class AccionesNotificacion extends HttpServlet {

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

        ObtenerUltimosMensajes,
        ObtenerUltimasNotificaciones,
        ObtenerMensajes,
        ObtenerNotificaciones,
        ResponderMensaje,
        VerNotificaciones,
        AccionDefault

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
        ArrayList<Notificacion> notificaciones;
        ManejadorNotificaciones mnotificacion = new ManejadorNotificaciones();

        String idusuario = (String) request.getSession().getAttribute("USERNAME");
        switch (getOpcion(request.getParameter("proceso"))) {
            case ObtenerUltimosMensajes:
                notificaciones = mnotificacion.obtenerUltimosMensajesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_mensajes.jsp");
                disp.forward(request, response);
                break;
            case ObtenerUltimasNotificaciones:
                notificaciones = mnotificacion.obtenerUltimasNotificacionesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_notificaciones.jsp");
                disp.forward(request, response);
                break;
            case ObtenerMensajes:
                notificaciones = mnotificacion.obtenerMensajesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_mensajes.jsp");
                disp.forward(request, response);
                break;
            case ObtenerNotificaciones:
                notificaciones = mnotificacion.obtenerNotificacionesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_notificaciones.jsp");
                disp.forward(request, response);
                break;
            case ResponderMensaje:
                if (UtilidadesServlet.tryParseInt(request.getParameter("id"))) {
                    int idmensaje = Integer.parseInt(request.getParameter("id"));
                    Notificacion mensaje = mnotificacion.obtenerMensaje(idmensaje);
                    request.setAttribute("mensaje", mensaje);
                    disp = request.getRequestDispatcher("/modulos/mensajes/responder.jsp");
                    disp.forward(request, response);
                }
                break;
            case VerNotificaciones:
                notificaciones = mnotificacion.obtenerNotificacionesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp = request.getRequestDispatcher("/modulos/notificaciones/index.jsp");
                disp.forward(request, response);
                break;
            default:
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

    }

    /**
     * Se encarga de clasificar la operación solicitada por el cliente.
     *
     * @param key el valor enviado por el cliente.
     * @return Un elemento de la enumeración OpcionesDo
     * @since 1.0
     */
    private OpcionesDo getOpcion(String key) {
        if (key == null) {
            return OpcionesDo.AccionDefault;
        } else if (key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("u_mensajes")) {
            return OpcionesDo.ObtenerUltimosMensajes;
        } else if (key.equals("u_notificaciones")) {
            return OpcionesDo.ObtenerUltimasNotificaciones;
        } else if (key.equals("mensajes")) {
            return OpcionesDo.ObtenerMensajes;
        } else if (key.equals("notificaciones")) {
            return OpcionesDo.ObtenerNotificaciones;
        } else if (key.equals("ver_mensaje")) {
            return OpcionesDo.ResponderMensaje;
        } else if (key.equals("ver_notificaciones")) {
            return OpcionesDo.VerNotificaciones;
        }

        return OpcionesDo.AccionDefault;
    }
}
