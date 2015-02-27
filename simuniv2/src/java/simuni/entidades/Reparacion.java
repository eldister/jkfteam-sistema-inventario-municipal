package simuni.entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reparacion {
    private String placaActivo = "";
    private String idUsuario = "";
    private String nombreSolicitante = "";
    private String motivoReparacion = "";
    private String nombreReparador = "";
    private Date fechaReparacion = new Date();
    private double costoReparacion = 0;
    private String Observacion = "";
    private int codigoEstado = 0;
    private int codigoReparacion = 0;
    private int codigoDetalleReparacion = 0;

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
        return new Date(fechaReparacion == null ? null : fechaReparacion.getTime());
    }
    
    public String getFechaR(){
        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        return sdp.format(this.getFechaReparacion());
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

    public int getCodigoDetalleReparacion() {
        return codigoDetalleReparacion;
    }

    public void setCodigoDetalleReparacion(int codigoDetalleReparacion) {
        this.codigoDetalleReparacion = codigoDetalleReparacion;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    
}
