package simuni.classes.LN;

import java.util.ArrayList;
import simuni.classes.AD.ManejadorDatosActivos;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.imagenActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorActivos {

    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) {
        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        return manejadordatosactivos.modificarActivoArticulo(to_articulo);
    }

    public boolean desactivarActivoArticulo(String ta_codigoactivo) {
        return true;
    }

    public ArrayList<Activos_Articulos> getListaArticulos(int npagina, int paginacion) {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        return manejadordatosactivos.getListaArticulos(npagina, paginacion);
    }

    public Activos_Articulos getActivoArticulo(String ta_codigoactivo) {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        return manejadordatosactivos.getActivoArticulo(ta_codigoactivo);

    }

    public ArrayList<imagenActivo> getListaImagenesActivo(String ta_codigoactivo) {
        return null;
    }

    public boolean isActivoExistente(String ta_codigoactivo) {
        return new ManejadorActivos().isActivoExistente(ta_codigoactivo);
    }

}
