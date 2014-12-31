/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import simuni.clases.ad.ManejadorDatosNotificaciones;
import simuni.entidades.Notificacion;

/**
 *
 * @author FchescO
 */
public class ManejadorNotificaciones {

    /**
     * Este método registra las notificaciones recibidas de alguna acción en
     * particular a la base de datos
     *
     * @param notificacion La notificación a registrar
     * @return true en caso de que la operación se halla realizado con éxito o
     * false en caso de que no
     * @since 1.0
     */
    public boolean agregarNotificacion(Notificacion notificacion) {
        ManejadorDatosNotificaciones manejadornotificaciones = new ManejadorDatosNotificaciones();
        boolean resp = false;
        try {
            resp = manejadornotificaciones.agregarNotificacion(notificacion);
            if (notificacion == null) {
                return false;
            }

            if (notificacion == null) {
                return false;
            } else if (notificacion.getFechaNotificacion() != null) {

                SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                formatoFecha.setLenient(false);
                formatoFecha.format(notificacion.getFechaNotificacion());
            }

            if (notificacion == null) {
                return false;
            } else if (notificacion.getUsuarioOrigen() == null) {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Este método obtiene una lista con todas las notificaciones enviadas a un
     * usuario en particular y que han sido registradas en la base de datos
     * dependiendo del usuario que las halla recibido
     *
     * @param idusuario identificación del usuario
     * @return Un ArrayList con todas las notificaciones que ha recibido un
     * usuario en particular o nulo en caso de que no tenga ninguna notificación
     * @since 1.0
     */
    public ArrayList<Notificacion> obtenerNotificacionesUsuario(String idusuario) {
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones = new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerNotificacionesUsuario(idusuario);

            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Este método obtiene una lista con todos los mensajes enviados a un
     * usuario en particular y que han sido registrados en la base de datos
     * dependiendo del usuario que los halla recibido
     *
     * @param idusuario identificación del usuario
     * @return Un ArrayList con todos los mensajes que ha recibido un usuario en
     * particular o nulo en caso de que no tenga ningun mensaje
     * @since 1.0
     */
    public ArrayList<Notificacion> obtenerMensajesUsuario(String idusuario) {
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones = new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerMensajesUsuario(idusuario);

            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Este método obtiene las recientes notificaciones enviadas a un usuario y
     * desactiva las que ha recibido previamente
     *
     * @param idusuario identificador del usuario
     * @return Un ArrayList con las notificaciones recientes enviadas a un
     * usuario en particular o nulo en caso de que no halla tenido ninguna
     * notificación recientemente
     */
    public ArrayList<Notificacion> obtenerUltimasNotificacionesUsuario(String idusuario) {
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones = new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerUltimasNotificacionesUsuario(idusuario);

            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }

    /**
     * Este método obtiene los recientes mensajes enviados a un usuario y
     * desactiva los que ha recibido previamente
     *
     * @param idusuario identificador del usuario
     * @return Un ArrayList con los mensajes recientes enviados a un usuario en
     * particular o nulo en caso de que no halla tenido ningun mensaje
     * recientemente
     */
    public ArrayList<Notificacion> obtenerUltimosMensajesUsuario(String idusuario) {
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones = new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerUltimosMensajesUsuario(idusuario);

            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

    }
}
