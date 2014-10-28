/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.LN;

import simuni.classes.AD.ManejadorDatosUsuario;
import simuni.classes.EN.Usuario;

/**
 *
 * @author FchescO
 */
public class ManejadorUsuarios {
    
    /**
     * Este método realiza la autentificación de un usuario particular al sistema de inventario SIMUNI
     * @param usuario nombre de usuario
     * @param contrasena contraseña del usuario a autentificarse
     * @return El usuario que ha iniciado sesión para utilizar las funcionalidades 
     * del sistema según el tipo de privilegio que maneje ya sea administrador o normal,  devuelve
     * nulo en caso de que el usuario no este registrado en la base de datos
     * @since 1.0
     */
    public Usuario login(String usuario,String contrasena){
       Usuario usuarioresp=null;
        try{
        ManejadorDatosUsuario manejadordatosusuarios=new ManejadorDatosUsuario();
         usuarioresp=manejadordatosusuarios.login(usuario, contrasena);
        
        }catch(Exception ex){
            //registrar error
            ex.printStackTrace();
            return null;
        }
        return usuarioresp;
    }
    
    /**
     * Este método obtiene el menú acorde al tipo de privilegio que posea el usuario loggeado al sistema
     * @param idusuario identificación del usuario loggeado
     * @return El menú del usuario según su tipo de privilegio de uso, devuelve nulo en caso de que el usuario no 
     * este autentificado
     * @since 1.0
     */
    public String getMenuUsuario(String idusuario){
        String resp="";
        try{
        ManejadorDatosUsuario manejadordatosusuarios=new ManejadorDatosUsuario();
         resp=manejadordatosusuarios.getMenuUsuario(idusuario);
        
        }catch(Exception ex){
            //registrar error
            ex.printStackTrace();
            return null;
        }        
        return resp;
    }
}
