/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

import java.util.Date;

/**
 *
 * @author FchescO
 */
public class Venta {
    private int codigoVenta=0;
    private Date fechaVenta=new Date();
    private String nombreComprador="";
    private double montoVenta=0;
    private String estadoVenta="Activo";
    private String placaActivo="";
    private String direccionComprador="";
    private String codigoFactura="";
    private String observaciones="";
    private Date fechaRegistro=new Date();
    private Date fechaUltimaModificacion=new Date();
    private int codigoTipoPago=0;

    /**
     * @return the codigoVenta
     */
    public int getCodigoVenta() {
        return codigoVenta;
    }

    /**
     * @param codigoVenta the codigoVenta to set
     */
    public void setCodigoVenta(int codigoVenta) {
        this.codigoVenta = codigoVenta;
    }

    /**
     * @return the fechaVenta
     */
    public java.sql.Date getFechaVenta() {
        return new java.sql.Date(fechaVenta==null?null:fechaVenta.getTime());
    }

    /**
     * @param fechaVenta the fechaVenta to set
     */
    public void setFechaVenta(Date fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    /**
     * @return the nombreComprador
     */
    public String getNombreComprador() {
        return nombreComprador;
    }

    /**
     * @param nombreComprador the nombreComprador to set
     */
    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    /**
     * @return the montoVenta
     */
    public double getMontoVenta() {
        return montoVenta;
    }

    /**
     * @param montoVenta the montoVenta to set
     */
    public void setMontoVenta(double montoVenta) {
        this.montoVenta = montoVenta;
    }

    /**
     * @return the estadoVenta
     */
    public String getEstadoVenta() {
        return estadoVenta;
    }

    /**
     * @param estadoVenta the estadoVenta to set
     */
    public void setEstadoVenta(String estadoVenta) {
        this.estadoVenta = estadoVenta;
    }

    /**
     * @return the placaActivo
     */
    public String getPlacaActivo() {
        return placaActivo;
    }

    /**
     * @param placaActivo the placaActivo to set
     */
    public void setPlacaActivo(String placaActivo) {
        this.placaActivo = placaActivo;
    }

    /**
     * @return the direccionComprador
     */
    public String getDireccionComprador() {
        return direccionComprador;
    }

    /**
     * @param direccionComprador the direccionComprador to set
     */
    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    /**
     * @return the codigoFactura
     */
    public String getCodigoFactura() {
        return codigoFactura;
    }

    /**
     * @param codigoFactura the codigoFactura to set
     */
    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    /**
     * @return the observaciones
     */
    public String getObservaciones() {
        return observaciones;
    }

    /**
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaUltimaModificacion
     */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    /**
     * @param fechaUltimaModificacion the fechaUltimaModificacion to set
     */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }

    /**
     * @return the codigoTipoPago
     */
    public int getCodigoTipoPago() {
        return codigoTipoPago;
    }

    /**
     * @param codigoTipoPago the codigoTipoPago to set
     */
    public void setCodigoTipoPago(int codigoTipoPago) {
        this.codigoTipoPago = codigoTipoPago;
    }
}
