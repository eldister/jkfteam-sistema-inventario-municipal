/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades.mantenimientos;

/**
 *
 * @author FchescO
 */
public class TipoActivo {
    private int idtipoactivo;
    private String nombretipoactivo;

    /**
     * @return the idtipoactivo
     */
    public int getIdtipoactivo() {
        return idtipoactivo;
    }

    /**
     * @param idtipoactivo the idtipoactivo to set
     */
    public void setIdtipoactivo(int idtipoactivo) {
        this.idtipoactivo = idtipoactivo;
    }

    /**
     * @return the nombretipoactivo
     */
    public String getNombretipoactivo() {
        return nombretipoactivo;
    }

    /**
     * @param nombretipoactivo the nombretipoactivo to set
     */
    public void setNombretipoactivo(String nombretipoactivo) {
        this.nombretipoactivo = nombretipoactivo;
    }
}
