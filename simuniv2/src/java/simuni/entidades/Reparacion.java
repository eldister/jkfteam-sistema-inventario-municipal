package simuni.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PJ
 */

public class Reparacion {
    private String placaActivo = "";
    private String idUsuario = "";
    private String motivoReparacion = "";
    private String nombreReparador = "";
    private Date fechaReparacion = new Date();
    private double costoReparacion = 0;
    private String Observacion = "";
    private int codigoEstado = 0;
    private int codigoReparacion = 0;
    private int codigoDetalleReparacion = 0;

    /**
     * @return placaActivo es el identificador del activo
     */
    public String getPlacaActivo() {
        return placaActivo;
    }
    
    /**
     * @param placaActivo parámetro para el registro del 
     * identificador del activo
     */
    public void setPlacaActivo(String placaActivo){
        this.placaActivo = placaActivo;
    }

    /**
     * @return motivoReparacion devuelve el mótivo por la cual el activo fue reparado
    */
    public String getMotivoReparacion() {
        return motivoReparacion;
    }

    /**
     * @param motivoReparacion parámetro para el registro del
     * motivo de reparacion
     */
    public void setMotivoReparacion(String motivoReparacion) {
        this.motivoReparacion = motivoReparacion;
    }

    /**
     * @return nombreReparador devuelve el nombre de la persona o encargado
     * de haber realizado la reparación sobre el activo dañado
     */
    public String getnombreReparador() {
        return nombreReparador;
    }

    /**
     * @param nombreReparador parámetro para el registro del 
     * nombre de la persona o encargado de haber hecho la 
     * reparacion sobre el activo dañado
     */
    public void setnombreReparador(String nombreReparador) {
        this.nombreReparador = nombreReparador;
    }

    /**
     * @return fechaReparacion devuelve la actual
     */
    public Date getFechaReparacion() {
        return new Date(fechaReparacion == null ? null : fechaReparacion.getTime());
    }
    
    /**
     * @return sdp devuelve el formato en que se mostrará y/o ingresará la 
     * fecha actual a la base de datos
     */
    public String getFechaR(){
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        return sdp.format(this.getFechaReparacion());
    }

    /**
     * @param fechaReparacion parámetro para el registro de 
     * la fecha en que se ha realizado la reparación por defecto
     * pasa la fecha actual
     */
    public void setFechaReparacion(Date fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    /**
     * @return  costoReparacion devuelve el total que se debe pagar por 
     * la reparacion realizada por el tecnico o encargado
     */
    public double getCostoReparacion() {
        return costoReparacion;
    }

    /**
     * @param costoReparacion parámetro para el registro del costo o el monto
     * que se debe pagar o que se pago por la reparacion
     */
    public void setCostoReparacion(double costoReparacion) {
        this.costoReparacion = costoReparacion;
    }

    /**
     * @return Observacion devuelve la observacion realizada por el
     * encargado de registrar la reparacion sobre algun aspecto o evento
     * que tenga relevancia o no sobre la reparacion realizada
     */
    public String getObservacion() {
        return Observacion;
    }

    /**
     * @param Observacion parámetro para el registro de la
     * observacion de la reparacion realizada por el tecnico o encargado
     */
    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    /**
     * @return codigoEstado devuelve el codigo sobre el estado en el
     * que se encuentra el activo despues de la reparacion realizada
     * sobre el activo.
     */
    public int getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param codigoEstado parámetro para el registro del codigo del estado
     * del activo al cual se le hizo una reparacion
     */
    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     * @return codigoReparacion devuelve el codigo de la 
     * reparacion realizada sobre un activo
     */
    public int getCodigoReparacion() {
        return codigoReparacion;
    }

    /**
     * @param codigoReparacion parámetro para el registro del codigo
     * de la reparacion realizada sobre un activo
     */
    public void setCodigoReparacion(int codigoReparacion) {
        this.codigoReparacion = codigoReparacion;
    }

    /**
     * @return CodigoDetalleReparacion devuelve el codigo del detalle
     * de la reparacion realizada sobre un activo
     */
    public int getCodigoDetalleReparacion() {
        return codigoDetalleReparacion;
    }

    /**
     * @param codigoDetalleReparacion parámetro para el registro del 
     * detalle de la reparacion realizada sobre un activo
     */
    public void setCodigoDetalleReparacion(int codigoDetalleReparacion) {
        this.codigoDetalleReparacion = codigoDetalleReparacion;
    }

    /**
     * @return idUsuario devuelve la identificacion del usuario que 
     * tiene el usuario en el sistema
     */
    public String getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario parámetro para el registro del usuario.
     */
    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
