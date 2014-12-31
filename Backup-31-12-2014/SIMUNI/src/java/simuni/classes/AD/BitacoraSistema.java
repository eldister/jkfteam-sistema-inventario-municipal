/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.AD;

/**
 *
 * @author FchescO
 */
public class BitacoraSistema {
    
    public synchronized boolean registrarErrorSistema(String origen,String mensaje){
        //hacer registro en base de datos
        return true;
    }
    public synchronized boolean registrarProcesoSistema(String usuario,String mensaje){
        //hacer registro en base de datos
        return true;
    }   
    
    
}
