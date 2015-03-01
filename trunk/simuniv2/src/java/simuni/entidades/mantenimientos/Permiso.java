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
public class Permiso {
    private int codigoPermiso;
    private String nombrePermiso;

    /**
     * @return the codigoPermiso
     */
    public int getCodigoPermiso() {
        return codigoPermiso;
    }

    /**
     * @param codigoPermiso the codigoPermiso to set
     */
    public void setCodigoPermiso(int codigoPermiso) {
        this.codigoPermiso = codigoPermiso;
    }

    /**
     * @return the nombrePermiso
     */
    public String getNombrePermiso() {
        return nombrePermiso;
    }

    /**
     * @param nombrePermiso the nombrePermiso to set
     */
    public void setNombrePermiso(String nombrePermiso) {
        this.nombrePermiso = nombrePermiso;
    }
    
}
