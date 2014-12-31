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
public class ActivoArticulo extends Activo {
    private int codigoActivoArticulo;
    private String codigoProveedor;
    private String modelo;
    private String marca;
    private Date fechaInicioOperacion;
    private double  porcentajeRescate;
    private double porcentajeDepreciacion;

    /**
     * @return the codigoProveedor
     */
    public String getCodigoProveedor() {
        return codigoProveedor;
    }

    /**
     * @param codigoProveedor the codigoProveedor to set
     */
    public void setCodigoProveedor(String codigoProveedor) {
        this.codigoProveedor = codigoProveedor;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the fechaInicioOperacion
     */
    public Date getFechaInicioOperacion() {
        return fechaInicioOperacion;
    }

    /**
     * @param fechaInicioOperacion the fechaInicioOperacion to set
     */
    public void setFechaInicioOperacion(Date fechaInicioOperacion) {
        this.fechaInicioOperacion = fechaInicioOperacion;
    }

    /**
     * @return the porcentajeRescate
     */
    public double getPorcentajeRescate() {
        return porcentajeRescate;
    }

    /**
     * @param porcentajeRescate the porcentajeRescate to set
     */
    public void setPorcentajeRescate(double porcentajeRescate) {
        this.porcentajeRescate = porcentajeRescate;
    }

    /**
     * @return the porcentajeDepreciacion
     */
    public double getPorcentajeDepreciacion() {
        return porcentajeDepreciacion;
    }

    /**
     * @param porcentajeDepreciacion the porcentajeDepreciacion to set
     */
    public void setPorcentajeDepreciacion(double porcentajeDepreciacion) {
        this.porcentajeDepreciacion = porcentajeDepreciacion;
    }

    /**
     * @return the codigoActivoArticulo
     */
    public int getCodigoActivoArticulo() {
        return codigoActivoArticulo;
    }

    /**
     * @param codigoActivoArticulo the codigoActivoArticulo to set
     */
    public void setCodigoActivoArticulo(int codigoActivoArticulo) {
        this.codigoActivoArticulo = codigoActivoArticulo;
    }
 
}
