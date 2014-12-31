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
public class TipoReporte {
    private int idtiporeporte;
    private String nombretiporeporte;

    /**
     * @return the idtiporeporte
     */
    public int getIdtiporeporte() {
        return idtiporeporte;
    }

    /**
     * @param idtiporeporte the idtiporeporte to set
     */
    public void setIdtiporeporte(int idtiporeporte) {
        this.idtiporeporte = idtiporeporte;
    }

    /**
     * @return the nombretiporeporte
     */
    public String getNombretiporeporte() {
        return nombretiporeporte;
    }

    /**
     * @param nombretiporeporte the nombretiporeporte to set
     */
    public void setNombretiporeporte(String nombretiporeporte) {
        this.nombretiporeporte = nombretiporeporte;
    }
}
