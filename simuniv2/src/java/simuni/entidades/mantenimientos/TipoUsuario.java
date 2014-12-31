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
public class TipoUsuario {
    private int idtipousuario;
    private String nombretipo;

    /**
     * @return the idtipousuario
     */
    public int getIdtipousuario() {
        return idtipousuario;
    }

    /**
     * @param idtipousuario the idtipousuario to set
     */
    public void setIdtipousuario(int idtipousuario) {
        this.idtipousuario = idtipousuario;
    }

    /**
     * @return the nombretipo
     */
    public String getNombretipo() {
        return nombretipo;
    }

    /**
     * @param nombretipo the nombretipo to set
     */
    public void setNombretipo(String nombretipo) {
        this.nombretipo = nombretipo;
    }
}
