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
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();

            manejadordatosactivos.agregarActivoArticulo(to_articulo);
            UtilidadesServlet.registrarProcesoSistema("agregarActivoArticulo", "Se inserto con éxito el activo");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("agregarActivoArticulo", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            try {
                return manejadordatosactivos.modificarActivoArticulo(to_articulo);
            } catch (Exception ex) {
                Logger.getLogger(ManejadorActivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            UtilidadesServlet.registrarProcesoSistema("modificarActivoArticulo", "Se modificó con éxito el activo");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("modificarActivoArticulo", e.getMessage());
        }

        return false;
    }

    public boolean desactivarActivoArticulo(String ta_codigoactivo) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            manejadordatosactivos.desactivarActivoArticulo(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("desactivarActivoArticulo", "Se desactivo con éxito el activo");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("desactivarActivoArticulo", e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Activos_Articulos> getListaArticulos(int desplazamiento, int paginacion) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            ArrayList<Activos_Articulos> articulos = manejadordatosactivos.getListaArticulos(desplazamiento, paginacion);
            UtilidadesServlet.registrarProcesoSistema("getListaArticulos", "Se obtuvo la lista de Articulos");
            return articulos;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getListaArticulos", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public int getCantidadRegistrosActivosArticulos() {
        try {

            return new ManejadorDatosActivos().getCantidadRegistrosActivosArticulos();

        } catch (Exception ex) {
            //registrar error
            return 0;
        }
    }

    public Activos_Articulos getActivoArticulo(String ta_codigoactivo) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            Activos_Articulos articulo = manejadordatosactivos.getActivoArticulo(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("getActivoArticulo", "Se obtuvo el Articulo del Activo");
            return articulo;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getActivoArticulo", e.getMessage());
        }
        return null;
    }

    public ArrayList<imagenActivo> getListaImagenesActivo(String tn_codigoactivo) {
        try {

            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            // ArrayList<imagenActivo> articulos = manejadordatosactivos.getListaImagenesActivo(tn_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("getListaImagenesActivo", "Se obtuvo la lista de Imagenes");
            return manejadordatosactivos.getListaImagenesActivo(tn_codigoactivo);
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getListaImagenesActivo", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean isActivoExistente(String ta_codigoactivo) {
        try {
            boolean ActivoExiste = new ManejadorDatosActivos().isActivoExistente(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("isActivoExistente", "ActivoExistente");
            return ActivoExiste;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("isActivoExistente", e.getMessage());
        }
        return false;
    }

    public int getNumeroActivosRegistrados() {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            manejadordatosactivos.getNumeroActivosRegistrados();
            UtilidadesServlet.registrarProcesoSistema("getNumeroActivosRegistrados", "Numero de activos registrados");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getNumeroActivosRegistrados", e.getMessage());
        }
        return 0;
    }

    public ArrayList<Activos_Articulos> buscarActivosArticulos(String query,int desplazamiento, int paginacion) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            ArrayList<Activos_Articulos> activos = manejadordatosactivos.buscarActivosArticulos(query,desplazamiento,paginacion);
            UtilidadesServlet.registrarProcesoSistema("buscarActivosArticulos", "La busqueda ha sido exitosa");
            return activos;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("buscarActivosArticulos", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
