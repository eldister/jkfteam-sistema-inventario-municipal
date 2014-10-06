
package simuni.classes.AD;

import java.util.ArrayList;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.imagenActivo;


/**
 *
 * @author FchescO
 */
public class ManejadorDatosActivos {

    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) {
        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        return true;
    }

    public boolean desactivarActivoArticulo(int tn_codigoactivo) {
        return true;
    }

    public ArrayList<Activos_Articulos> getListaArticulos() {
        return null;
    }

    public Activos_Articulos getActivoArticulo(int tn_codigoactivo) {
        return null;
    }

    public ArrayList<imagenActivo> getListaImagenesActivo(int tn_codigoactivo) {
        return null;
    }
    
    public boolean isActivoExistente(String idactivo){
        return false;
    }

}
