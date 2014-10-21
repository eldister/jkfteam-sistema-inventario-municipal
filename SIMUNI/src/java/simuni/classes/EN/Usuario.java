/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 *
 * @author FchescO
 */
public class Usuario {
    private String nombreusuario;
    private String contrasena;
    private int tipousuario;

    /**
     * @return the nombreusuario
     */
    public String getNombreusuario() {
        return nombreusuario;
    }

    /**
     * @param nombreusuario the nombreusuario to set
     */
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    /**
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * @return the tipousuario
     */
    public int getTipousuario() {
        return tipousuario;
    }

    /**
     * @param tipousuario the tipousuario to set
     */
    public void setTipousuario(int tipousuario) {
        this.tipousuario = tipousuario;
    }
}
