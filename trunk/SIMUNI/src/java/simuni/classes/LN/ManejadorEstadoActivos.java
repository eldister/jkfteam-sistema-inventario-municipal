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
