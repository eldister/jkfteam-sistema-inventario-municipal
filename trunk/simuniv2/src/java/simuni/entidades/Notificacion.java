/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;




/**
 * Se realizan las notificación que se le mostrarán a los usuarios. 
 * @author FchescO
 */
public class Notificacion {
    private int idNotificacion;
    private java.util.Date fechaNotificacion;
    private String usuarioObjetivo;
    private String usuarioOrigen;
    private String estadoNotificacion;
    private String descripcionNotificacion;
    private String tipoNotificacion;

    /**
     * Obtener el id de la notificación 
     * @return the idNotificacion
     */
    public int getIdNotificacion() {
        return idNotificacion;
    }

    /**
     * poner el id de la notificación 
     * @param idNotificacion the idNotificacion to set
     */
    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /** 
     * obtener la fecha de la notificación
     * @return the fechaNotificacion
     */
    public java.util.Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * pone la fecha de la notificación 
     * @param fechaNotificacion the fechaNotificacion to set
     */
    public void setFechaNotificacion(java.sql.Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    /**
     * obtener el usario que se le enviarán las notificaciones 
     * @return the usuarioObjetivo
     */
    public String getUsuarioObjetivo() {
        return usuarioObjetivo;
    }

    /**
     * poner el usario que se le enviarán las notificaciones 
     * @param usuarioObjetivo the usuarioObjetivo to set
     */
    public void setUsuarioObjetivo(String usuarioObjetivo) {
        this.usuarioObjetivo = usuarioObjetivo;
    }

    /**
     * 
     * @return the usuarioOrigen
     */
    public String getUsuarioOrigen() {
        return usuarioOrigen;
    }

    /**
     * @param usuarioOrigen the usuarioOrigen to set
     */
    public void setUsuarioOrigen(String usuarioOrigen) {
        this.usuarioOrigen = usuarioOrigen;
    }

    /**
     * obtener el estado de la notificación 
     * @return the estadoNotificacion
     */
    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    /**
     * poner el estado de notificación 
     * @param estadoNotificacion the estadoNotificacion to set
     */
    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    /**
     * obtener la descripcion de de la notificación 
     * @return the descripcionNotificacion
     */
    public String getDescripcionNotificacion() {
        return descripcionNotificacion;
    }

    /**
     * poner la descripción de la notificación 
     * @param descripcionNotificacion the descripcionNotificacion to set
     */
    public void setDescripcionNotificacion(String descripcionNotificacion) {
        this.descripcionNotificacion = descripcionNotificacion;
    }

    /**
     * obterner que tipo de notificación es 
     * @return the tipoNotificacion
     */
    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    /**
     * poner el tipo de notificación 
     * @param tipoNotificacion the tipoNotificacion to set
     */
    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
    
    
}
