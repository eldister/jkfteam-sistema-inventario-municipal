package simuni.entidades;

import java.util.Date;

public class Reparacion {
    private String placaActivo;
    private String nombreSolicitante;
    private String motivoReparacion;
    private String nombreReparador;
    private Date fechaReparacion;
    private double costoReparacion;
    private String Observacion;
    private int codigoEstado;
    private int codigoReparacion = 0;

    public String getPlacaActivo() {
        return placaActivo;
    }
    
    public void setPlacaActivo(String placaActivo){
        this.placaActivo = placaActivo;
    }

    public void setCodigoTipoActivo(String placaActivo) {
        this.placaActivo = placaActivo;
    }

    public String getnombreSolicitante() {
        return nombreSolicitante;
    }

    public void setnombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getMotivoReparacion() {
        return motivoReparacion;
    }

    public void setMotivoReparacion(String motivoReparacion) {
        this.motivoReparacion = motivoReparacion;
    }

    public String getnombreReparador() {
        return nombreReparador;
    }

    public void setnombreReparador(String nombreReparador) {
        this.nombreReparador = nombreReparador;
    }

    public Date getFechaReparacion() {
        return fechaReparacion;
    }

    public void setFechaReparacion(Date fechaReparacion) {
        this.fechaReparacion = fechaReparacion;
    }

    public double getCostoReparacion() {
        return costoReparacion;
    }

    public void setCostoReparacion(double costoReparacion) {
        this.costoReparacion = costoReparacion;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String Observacion) {
        this.Observacion = Observacion;
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(int codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public int getCodigoReparacion() {
        return codigoReparacion;
    }

    public void setCodigoReparacion(int codigoReparacion) {
        this.codigoReparacion = codigoReparacion;
    }
    
    
}
