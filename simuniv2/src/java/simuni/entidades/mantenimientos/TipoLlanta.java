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
public class TipoLlanta {
    private int idtipollanta;
    private String nombretipollanta;
    private String descripcion;

    public TipoLlanta() {
    }

    public TipoLlanta(int idtipollanta,String descripcion) {
        this.idtipollanta = idtipollanta;
        this.descripcion=descripcion;
    }

    /**
     * @return the idtipollanta
     */
    public int getIdtipollanta() {
        return idtipollanta;
    }

    /**
     * @param idtipollanta the idtipollanta to set
     */
    public void setIdtipollanta(int idtipollanta) {
        this.idtipollanta = idtipollanta;
    }

    /**
     * @return the nombretipollanta
     */
    public String getNombretipollanta() {
        return nombretipollanta;
    }

    /**
     * @param nombretipollanta the nombretipollanta to set
     */
    public void setNombretipollanta(String nombretipollanta) {
        this.nombretipollanta = nombretipollanta;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
