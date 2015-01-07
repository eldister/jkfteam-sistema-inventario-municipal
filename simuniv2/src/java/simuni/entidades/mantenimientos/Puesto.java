package simuni.entidades.mantenimientos;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FchescO
 */
public class Puesto {
    private int codigoPuesto;
    private String nombrePuesto;

    /**
     * @return the codigoPuesto
     */
    public int getCodigoPuesto() {
        return codigoPuesto;
    }

    /**
     * @param codigoPuesto the codigoPuesto to set
     */
    public void setCodigoPuesto(int codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }

    /**
     * @return the nombrePuesto
     */
    public String getNombrePuesto() {
        return nombrePuesto;
    }

    /**
     * @param nombrePuesto the nombrePuesto to set
     */
    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }
}
