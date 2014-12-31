/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FchescO
 */
public class Activo {

    private int codigoDepto;
    private String placaActivo;
    private int codigoTipoActivo;
    private String observaciones;
    private double precio;
    private String denominacion;
    private Date fechaAdquisicion;
    private int codigoTipoPago;
    private int codigoEstado;
    private ArrayList<ImagenActivo> imagenes;

    /**
     * @return the codigoDepto
     */
    public int getCodigoDepto() {
        return codigoDepto;
    }

    /**
     * @param codigoDepto the codigoDepto to set
     */
    public void setCodigoDepto(int codigoDepto) {
        this.codigoDepto = codigoDepto;
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
     * @return the codigoTipoActivo
     */
    public int getCodigoTipoActivo() {
        return codigoTipoActivo;
    }

    /**
     * @param codigoTipoActivo the codigoTipoActivo to set
     */
    public void setCodigoTipoActivo(int codigoTipoActivo) {
        this.codigoTipoActivo = codigoTipoActivo;
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
     * @return the precio
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * @return the denominacion
     */
    public String getDenominacion() {
        return denominacion;
    }

    /**
     * @param denominacion the denominacion to set
     */
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    /**
     * @return the fechaAdquisicion
     */
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }

    /**
     * @param fechaAdquisicion the fechaAdquisicion to set
     */
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
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

    /**
     * @return the codigoEstado
     */
    public int getCodigoEstado() {
        return codigoEstado;
    }

    /**
     * @param codigoEstado the codigoEstado to set
     */
    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    /**
     * @return the imagenes
     */
    public ArrayList<ImagenActivo> getImagenes() {
        return imagenes;
    }

    /**
     * @param imagenes the imagenes to set
     */
    public void setImagenes(ArrayList<ImagenActivo> imagenes) {
        this.imagenes = imagenes;
    }

    public boolean agregarImagenActivo(ImagenActivo imagen) {
        if (this.imagenes == null) {
            this.imagenes = new ArrayList<ImagenActivo>();
        }
        return this.imagenes.add(imagen);
    }

}
