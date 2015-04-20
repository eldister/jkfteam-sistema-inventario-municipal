/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

/**
 * En esta clase se manejará los diferentes tipos de usuarios que tiene el sistema, con su respectivo nombre y contraseña
 * @author FchescO
 */
public class Usuario {
    //atributos de la clase
    private String cedula;//
    private String nombreusuario;//
    private String contrasena;
private String contrasena2;//
    private int tipousuario;
    private String menuusuario;
    private int areatrabajo;
    private int codigoPuesto;
    private String nombre;//
    private String apellido1;//
    private String apellido2;//
    private String email;

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

    /**
     * @return the menuusuario
     */
    public String getMenuusuario() {
        return menuusuario;
    }

    /**
     * @param menuusuario the menuusuario to set
     */
    public void setMenuusuario(String menuusuario) {
        this.menuusuario = menuusuario;
    }

    /**
     * @return the areatrabajo
     */
    public int getAreatrabajo() {
        return areatrabajo;
    }

    /**
     * @param areatrabajo the areatrabajo to set
     */
    public void setAreatrabajo(int areatrabajo) {
        this.areatrabajo = areatrabajo;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the apellido1
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * @param apellido1 the apellido1 to set
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * @return the apellido2
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * @param apellido2 the apellido2 to set
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

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
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the contrasena2
     */
    public String getContrasena2() {
        return contrasena2;
    }

    /**
     * @param contrasena2 the contrasena2 to set
     */
    public void setContrasena2(String contrasena2) {
        this.contrasena2 = contrasena2;
    }
}
