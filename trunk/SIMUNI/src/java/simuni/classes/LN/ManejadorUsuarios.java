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
