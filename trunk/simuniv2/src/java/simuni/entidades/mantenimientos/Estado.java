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
public class Estado {
    private int idestado;
    private String nombreestado;

    /**
     * @return the idestado
     */
    public int getIdestado() {
        return idestado;
    }

    /**
     * @param idestado the idestado to set
     */
    public void setIdestado(int idestado) {
        this.idestado = idestado;
    }

    /**
     * @return the nombreestado
     */
    public String getNombreestado() {
        return nombreestado;
    }

    /**
     * @param nombreestado the nombreestado to set
     */
    public void setNombreestado(String nombreestado) {
        this.nombreestado = nombreestado;
    }
}
