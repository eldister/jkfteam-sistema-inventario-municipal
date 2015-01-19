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
public class Baja {
    private int codigoBaja=0;
    private String razonBaja="";
    private Date fechaBaja=new Date();
    private Date fechaRegistro=new Date();
    private String codigoDocumentoRespaldo="";
    private String observaciones="";
    private String placaActivo="";

    /**
     * @return the codigoBaja
     */
    public int getCodigoBaja() {
        return codigoBaja;
    }

    /**
     * @param codigoBaja the codigoBaja to set
     */
    public void setCodigoBaja(int codigoBaja) {
        this.codigoBaja = codigoBaja;
    }

    /**
     * @return the razonBaja
     */
    public String getRazonBaja() {
        return razonBaja;
    }

    /**
     * @param razonBaja the razonBaja to set
     */
    public void setRazonBaja(String razonBaja) {
        this.razonBaja = razonBaja;
    }

    /**
     * @return the fechaBaja
     */
    public Date getFechaBaja() {
        return new java.sql.Date(fechaBaja==null?null:fechaBaja.getTime());
    }

    /**
     * @param fechaBaja the fechaBaja to set
     */
    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    /**
     * @return the codigoDocumentoRespaldo
     */
    public String getCodigoDocumentoRespaldo() {
        return codigoDocumentoRespaldo;
    }

    /**
     * @param codigoDocumentoRespaldo the codigoDocumentoRespaldo to set
     */
    public void setCodigoDocumentoRespaldo(String codigoDocumentoRespaldo) {
        this.codigoDocumentoRespaldo = codigoDocumentoRespaldo;
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
    
}
