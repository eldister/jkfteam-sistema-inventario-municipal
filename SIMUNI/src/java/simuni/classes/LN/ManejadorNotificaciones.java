/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.LN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.AD.ManejadorDatosNotificaciones;
import simuni.classes.EN.Notificacion;

/**
 *
 * @author FchescO
 */
public class ManejadorNotificaciones {
    public boolean agregarNotificacion(Notificacion notificacion){
        ManejadorDatosNotificaciones manejadornotificaciones=new ManejadorDatosNotificaciones();
        boolean resp=false;
        try {
           resp=manejadornotificaciones.agregarNotificacion(notificacion);
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return resp;
    }
    
    public ArrayList<Notificacion> obtenerNotificacionesUsuario(String idusuario){
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones=new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerNotificacionesUsuario(idusuario);
            
            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    
    }
    
    public ArrayList<Notificacion> obtenerMensajesUsuario(String idusuario){
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones=new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerMensajesUsuario(idusuario);
            
            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    
    }
    
    public ArrayList<Notificacion> obtenerUltimasNotificacionesUsuario(String idusuario){
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones=new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerUltimasNotificacionesUsuario(idusuario);
            
            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    
    }
    
    public ArrayList<Notificacion> obtenerUltimosMensajesUsuario(String idusuario){
        try {
            ManejadorDatosNotificaciones manejadordatosnotificaciones=new ManejadorDatosNotificaciones();
            ArrayList<Notificacion> notificaciones = manejadordatosnotificaciones.obtenerUltimosMensajesUsuario(idusuario);
            
            return notificaciones;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    
    }    
}
