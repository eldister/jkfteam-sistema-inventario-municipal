/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;
import java.util.Date;

/**
 *
 * @author Keylin
 */
public class Prestamo {
    private int idPrestamo=0;
    private Date fechaRegistro;
    private Date fechaDevolucion;
    private String observaciones="";
    private String estado="";
    private String departamentoSolicitante="";
    private String idActivo="";
    
    
        /**
     * @return the Idprestamo
     */
    public int getIdPrestamo() {
        return idPrestamo;
    }

    /**
     * @param idPrestamo the idPrestamo to set
     */
    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo= idPrestamo;
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
     * @return the fechaDevolucion
     */
    public Date getFechaDevolucion() {
        return fechaDevolucion;
    }

    /**
     * @param fechaDevolucion the fechaDevolucion to set
     */
    public void setFechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
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
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
     /**
     * @return the departamentoSolicitante
     */
    public String getDepartamentoSolicitante() {
        return departamentoSolicitante;
    }

    /**
     * @param departamentoSolicitante the departamentoSolicitante to set
     */
    public void setDepartamentoSolicitante(String departamentoSolicitante) {
        this.departamentoSolicitante = departamentoSolicitante;
    }
    
     /**
     * @return the idActivo
     */
    public String getIdActivo() {
        return idActivo;
    }

    /**
     * @param idActivo the idActivo to set
     */
    public void setIdActivo(String idActivo) {
        this.idActivo = idActivo;
    }
    

}