/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.LN;

import java.util.ArrayList;
import simuni.classes.AD.ManejadorDatosEstadoActivo;
import simuni.classes.EN.EstadoActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorEstadoActivos {
    /**
     * Este método es para obtener los tipos de estados en los que se puede encontrar 
     * un artículo activoya sea bueno, malo, excelente etc
     * @return Un ArrayList con todos los estados o nulo en caso de que no halla podido obtenerlos
     * @since 1.0
     */
        public ArrayList<EstadoActivo> getListadoEstadosActivos(){
            try{
            ManejadorDatosEstadoActivo manejadorestadosactivos = new ManejadorDatosEstadoActivo();
            ArrayList<EstadoActivo> estadoactivo = manejadorestadosactivos.getListadoEstadosActivos();
            UtilidadesServlet.registrarProcesoSistema("getListadoEstadosActivos","Se obtuvo el listado de los estados de activos");
            return estadoactivo;
            }
            catch(Exception e){
            UtilidadesServlet.registrarErrorSistema("getListadoEstadosActivos", e.getMessage());
            }           
        return null;

    }
}
