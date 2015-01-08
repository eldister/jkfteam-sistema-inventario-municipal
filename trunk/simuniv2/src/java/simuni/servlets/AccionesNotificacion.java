/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 *
 * @author FchescO
 */
public class AccionesNotificacion extends HttpServlet {

    enum OpcionesDo {

        ObtenerUltimosMensajes,
        ObtenerUltimasNotificaciones,
        ObtenerMensajes,
        ObtenerNotificaciones,
        ResponderMensaje,
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
                disp  = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_mensajes.jsp");
                disp.forward(request, response);
                break;
            case ObtenerUltimasNotificaciones:
                notificaciones = mnotificacion.obtenerUltimasNotificacionesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);                
                disp  = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_notificaciones.jsp");
                disp.forward(request, response);
                break;
            case ObtenerMensajes:
                notificaciones = mnotificacion.obtenerMensajesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);
                disp  = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_mensajes.jsp");
                disp.forward(request, response);                
                break;
            case ObtenerNotificaciones:
                notificaciones = mnotificacion.obtenerNotificacionesUsuario(idusuario);
                request.setAttribute("notificaciones", notificaciones);                
                disp  = request.getRequestDispatcher("/modulos/notificaciones/_asinc/_asinc_notificaciones.jsp");
                disp.forward(request, response);                
                break;
            case ResponderMensaje:
                if(UtilidadesServlet.tryParseInt(request.getParameter("id"))){
                    int idmensaje=Integer.parseInt(request.getParameter("id"));
                    Notificacion mensaje=mnotificacion.obtenerMensaje(idmensaje);
                    request.setAttribute("mensaje", mensaje);
                    disp  = request.getRequestDispatcher("/modulos/mensajes/responder.jsp");
                    disp.forward(request, response);
                }
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
        }
        
        return OpcionesDo.AccionDefault;
    }
}
