/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 * En esta clase estarán los diferentes tipos de activos existentes 
 * @author FchescO
 */
public class TipoActivo {
    private int codigoTipoActivo;
    private String nombreTipoActivo;

    /** obtener el códido del tipo de activo
     * @return the codigoTipoActivo
     */
    public int getCodigoTipoActivo() {
        return codigoTipoActivo;
    }

    /**
     * poner el códido del tipo de activo
     * @param codigoTipoActivo the codigoTipoActivo to set
     */
    public void setCodigoTipoActivo(int codigoTipoActivo) {
        this.codigoTipoActivo = codigoTipoActivo;
    }

    /**
     * obtener el nombre del tipo de activo
     * @return the nombreTipoActivo
     */
    public String getNombreTipoActivo() {
        return nombreTipoActivo;
    }

    /**
     * poner el nombre del tipo de activo
     * @param nombreTipoActivo the nombreTipoActivo to set
     */
    public void setNombreTipoActivo(String nombreTipoActivo) {
        this.nombreTipoActivo = nombreTipoActivo;
    }
    
}
