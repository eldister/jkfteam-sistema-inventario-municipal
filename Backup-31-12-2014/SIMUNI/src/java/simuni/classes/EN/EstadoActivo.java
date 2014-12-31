/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 * En la clase EstadoActivo se va mostrar en que estado se encuentra el activo
 * @author FchescO
 */
public class EstadoActivo {
    private String nombreEstado;

    /** Obtener el estado del activo
     * @return the nombreEstado
     */
    public String getNombreEstado() {
        return nombreEstado;
    }

    /**
     * poner el estado del activo
     * @param nombreEstado the nombreEstado to set
     */ 
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }
    
}
