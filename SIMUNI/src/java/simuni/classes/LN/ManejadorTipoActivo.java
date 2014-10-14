/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.LN;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.AD.ManejadorDatosTipoActivo;
import simuni.classes.EN.TipoActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorTipoActivo {

    public ArrayList<TipoActivo> getListaTiposActivos() {
        try {
            return new ManejadorDatosTipoActivo().getListaTipoActivos();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorTipoActivo.class.getName()).log(Level.SEVERE, null, ex);
        }
            try{
                ManejadorDatosTipoActivo manejadortipoactivo= new ManejadorDatosTipoActivo();
                ArrayList<TipoActivo> tipoactivo = manejadortipoactivo.getListaTipoActivos();
                UtilidadesServlet.registrarProcesoSistema("getListaTiposActivos", "Se obtuvo la Lista de tipo de activo");
                return tipoactivo;
            }
            catch (Exception e){
                UtilidadesServlet.registrarErrorSistema("getListaTiposActivos", e.getMessage());
            }
        return null;
    }
}
