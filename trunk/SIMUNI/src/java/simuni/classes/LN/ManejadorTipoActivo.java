/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.LN;

import java.util.ArrayList;

import simuni.classes.AD.ManejadorDatosTipoActivo;
import simuni.classes.EN.TipoActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorTipoActivo {

    /**
     * Este método es para obtener todos los tipos de activos que tienen en la municipalidad
     * @return Un ArrayList con todos los tipos de pagos o nulo en caso de que no halla podido obtenerlos
     * @since 1.0
     */
    public ArrayList<TipoActivo> getListaTiposActivos() {

            try{
                ManejadorDatosTipoActivo manejadortipoactivo= new ManejadorDatosTipoActivo();
                ArrayList<TipoActivo> tipoactivo = manejadortipoactivo.getListaTipoActivos();
                UtilidadesServlet.registrarProcesoSistema("getListaTiposActivos", "Se obtuvo la Lista de tipo de activo");
                return tipoactivo;
            }
            catch (Exception e){
                UtilidadesServlet.registrarErrorSistema("getListaTiposActivos", e.getMessage());
                e.printStackTrace();
            }
        return null;
    }
}
