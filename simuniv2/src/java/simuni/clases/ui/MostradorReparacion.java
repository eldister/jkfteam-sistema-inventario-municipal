package simuni.clases.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.GrillaBase;
import simuni.enums.Recursos;
/***
 * Esta es la clase para preparar la UI al usuario
 * con las datos de las reparaciones, recibiendo un resultset 
 * como respuesta los resultados obtenidos de la base de datos
 * @author ProducJeffer
 * @since 1.0
 * @version 2.0
 */

public class MostradorReparacion {
    
    /**
     * Función que devuelve una cadena con el texto que será mostrado al usuario
     * es una funcion recursiva que recibe por parametro las variables descritas a continuacion
     * @param rsreparacion resultset con las reparaciones
     * @param paginas cantidad de paginas que seran mostradas en la paginación
     * @return 
     * since 2.0
     */
    public String Renderizar(ResultSet rsreparacion, int paginas){
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/reparacion?proceso=listado");
        gril.setUrlagregaropcionagregar(Recursos.Servers.MAINSERVER + "/reparacion?proceso=nuevo");
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizar(rsreparacion, paginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
    
    /**
     * Función que devuelve una cadena con el texto que será mostrado al usuario
     * es una funcion recursiva que recibe por parametro las variables descritas a continuacion
     * @param rsreparacion resultset con las reparaciones
     * @param paginas cantidad de paginas que seran mostradas en la paginación
     * @return 
     * since 2.0
     */
    public String RenderizarActualizacion(ResultSet rsreparacion, int paginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/reparacion?proceso=listado");
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizarSoloCuerpoTabla(rsreparacion, paginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
}
