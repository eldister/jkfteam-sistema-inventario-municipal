/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 * En esta clase se manejará los diferentes tipos de usuarios que tiene el sistema, con su respectivo nombre y contraseña
 * @author FchescO
 */
public class Usuario {
    private String nombreusuario;
    private String contrasena;
    private int tipousuario;

    /**
     * obtener el nombre de usuario
     * @return the nombreusuario
     */
    public String getNombreusuario() {
        return nombreusuario;
    }

    /**
     * poner el nombre de usuario
     * @param nombreusuario the nombreusuario to set
     */
    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    /**
     * obtener la contraseña del usuario 
     * @return the contrasena
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * poner la contraseña del usuario
     * @param contrasena the contrasena to set
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * obtener el tipo de usuario
     * @return the tipousuario
     */
    public int getTipousuario() {
        return tipousuario;
    }

    /**
     * poner el tipo de usuario
     * @param tipousuario the tipousuario to set
     */
    public void setTipousuario(int tipousuario) {
        this.tipousuario = tipousuario;
    }
}
