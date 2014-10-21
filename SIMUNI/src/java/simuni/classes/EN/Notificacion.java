/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 *
 * @author FchescO
 */
public class Notificacion {
    private int idNotificacion;
    private java.sql.Date fechaNotificacion;
    private int usuarioObjetivo;
    private String usuarioOrigen;
    private String estadoNotificacion;
    private String descripcionNotificacion;
    private String tipoNotificacion;

    /**
     * @return the idNotificacion
     */
    public int getIdNotificacion() {
        return idNotificacion;
    }

    /**
     * @param idNotificacion the idNotificacion to set
     */
    public void setIdNotificacion(int idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    /**
     * @return the fechaNotificacion
     */
    public java.sql.Date getFechaNotificacion() {
        return fechaNotificacion;
    }

    /**
     * @param fechaNotificacion the fechaNotificacion to set
     */
    public void setFechaNotificacion(java.sql.Date fechaNotificacion) {
        this.fechaNotificacion = fechaNotificacion;
    }

    /**
     * @return the usuarioObjetivo
     */
    public int getUsuarioObjetivo() {
        return usuarioObjetivo;
    }

    /**
     * @param usuarioObjetivo the usuarioObjetivo to set
     */
    public void setUsuarioObjetivo(int usuarioObjetivo) {
        this.usuarioObjetivo = usuarioObjetivo;
    }

    /**
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
     * @return the estadoNotificacion
     */
    public String getEstadoNotificacion() {
        return estadoNotificacion;
    }

    /**
     * @param estadoNotificacion the estadoNotificacion to set
     */
    public void setEstadoNotificacion(String estadoNotificacion) {
        this.estadoNotificacion = estadoNotificacion;
    }

    /**
     * @return the descripcionNotificacion
     */
    public String getDescripcionNotificacion() {
        return descripcionNotificacion;
    }

    /**
     * @param descripcionNotificacion the descripcionNotificacion to set
     */
    public void setDescripcionNotificacion(String descripcionNotificacion) {
        this.descripcionNotificacion = descripcionNotificacion;
    }

    /**
     * @return the tipoNotificacion
     */
    public String getTipoNotificacion() {
        return tipoNotificacion;
    }

    /**
     * @param tipoNotificacion the tipoNotificacion to set
     */
    public void setTipoNotificacion(String tipoNotificacion) {
        this.tipoNotificacion = tipoNotificacion;
    }
    
    
}
