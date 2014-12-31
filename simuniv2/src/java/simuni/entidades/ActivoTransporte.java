/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

import java.util.ArrayList;
import java.util.Date;
import simuni.entidades.mantenimientos.TipoLlanta;

/**
 *
 * @author FchescO
 */
public class ActivoTransporte extends Activo{
    private int codigoActivoTransporte;
    private String tipoVehiculo="A";
    private String placa;
    private String modelo;
    private String marca;
    private Date fechaInicio;
    private double porcentajeRescate;
    private double porcentajeDepreciacion;
    private String numeroChasis;
    private String numeroMotor;
    private Date anioFabrica;
    private String cilindros;
    private int codigoBipoBateria;
    private String observacionesTecnicas;
    private ArrayList<TipoLlanta> llantas;

    /**
     * @return the tipoVehiculo
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    /**
     * @param tipoVehiculo the tipoVehiculo to set
     */
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
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
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
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
     * @return the numeroChasis
     */
    public String getNumeroChasis() {
        return numeroChasis;
    }

    /**
     * @param numeroChasis the numeroChasis to set
     */
    public void setNumeroChasis(String numeroChasis) {
        this.numeroChasis = numeroChasis;
    }

    /**
     * @return the numeroMotor
     */
    public String getNumeroMotor() {
        return numeroMotor;
    }

    /**
     * @param numeroMotor the numeroMotor to set
     */
    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    /**
     * @return the anioFabrica
     */
    public Date getAnioFabrica() {
        return anioFabrica;
    }

    /**
     * @param anioFabrica the anioFabrica to set
     */
    public void setAnioFabrica(Date anioFabrica) {
        this.anioFabrica = anioFabrica;
    }

    /**
     * @return the cilindros
     */
    public String getCilindros() {
        return cilindros;
    }

    /**
     * @param cilindros the cilindros to set
     */
    public void setCilindros(String cilindros) {
        this.cilindros = cilindros;
    }

    /**
     * @return the codigoBipoBateria
     */
    public int getCodigoBipoBateria() {
        return codigoBipoBateria;
    }

    /**
     * @param codigoBipoBateria the codigoBipoBateria to set
     */
    public void setCodigoBipoBateria(int codigoBipoBateria) {
        this.codigoBipoBateria = codigoBipoBateria;
    }

    /**
     * @return the observacionesTecnicas
     */
    public String getObservacionesTecnicas() {
        return observacionesTecnicas;
    }

    /**
     * @param observacionesTecnicas the observacionesTecnicas to set
     */
    public void setObservacionesTecnicas(String observacionesTecnicas) {
        this.observacionesTecnicas = observacionesTecnicas;
    }

    /**
     * @return the llantas
     */
    public ArrayList<TipoLlanta> getLlantas() {
        return llantas;
    }

    /**
     * @param llantas the llantas to set
     */
    public void setLlantas(ArrayList<TipoLlanta> llantas) {
        this.llantas = llantas;
    }
    
    public boolean agregarTipoLlanta(TipoLlanta tipollanta){
        if(this.llantas==null){
            this.llantas=new ArrayList<TipoLlanta>();
        }
        return this.llantas.add(tipollanta);
    }

    /**
     * @return the codigoActivoTransporte
     */
    public int getCodigoActivoTransporte() {
        return codigoActivoTransporte;
    }

    /**
     * @param codigoActivoTransporte the codigoActivoTransporte to set
     */
    public void setCodigoActivoTransporte(int codigoActivoTransporte) {
        this.codigoActivoTransporte = codigoActivoTransporte;
    }
    
}
