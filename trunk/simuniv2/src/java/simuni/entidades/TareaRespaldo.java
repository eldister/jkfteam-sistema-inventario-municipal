/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

/**
 *
 * @author FchescO
 */
public class TareaRespaldo {
    private int codigoTarea;
    private java.sql.Date fecha;
    private String estado;

    /**
     * @return the codigoTarea
     */
    public int getCodigoTarea() {
        return codigoTarea;
    }

    /**
     * @param codigoTarea the codigoTarea to set
     */
    public void setCodigoTarea(int codigoTarea) {
        this.codigoTarea = codigoTarea;
    }

    /**
     * @return the fecha
     */
    public java.sql.Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
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
}
