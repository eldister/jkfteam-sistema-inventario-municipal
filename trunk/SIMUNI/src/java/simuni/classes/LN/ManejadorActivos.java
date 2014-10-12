package simuni.classes.LN;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.AD.ManejadorDatosActivos;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.imagenActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorActivos {

    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) throws Exception {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        manejadordatosactivos.agregarActivoArticulo(to_articulo);
        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        try {
            return manejadordatosactivos.modificarActivoArticulo(to_articulo);
        } catch (Exception ex) {
            Logger.getLogger(ManejadorActivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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
        return new ManejadorDatosActivos().isActivoExistente(ta_codigoactivo);
    }

    public int getNumeroActivosRegistrados() {
        return 0;
    }

    public ArrayList<Activos_Articulos> buscarActivosArticulos(String query) {
        ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
        return manejadordatosactivos.buscarActivosArticulos(query);
    }

}
