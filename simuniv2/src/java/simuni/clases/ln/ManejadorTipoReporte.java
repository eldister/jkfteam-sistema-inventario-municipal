package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosTipoReporte;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoReporte;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Tipo de Reporte</strong> se
 * encarga de las operaciones de validación, solicitudes y respuestas, para
 * hacer su ingreso, modificación, eliminación de registros. Entre las
 * operaciones comunes que se solicitan estan agregar, modificar, eliminar,
 * hacer un query de busqueda y tambien hacer el listado por defecto que hay de
 * los datos ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorTipoReporte {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Tipo de Reporte</strong>.
     *
     * @param tiporeporte El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarTipoReporte(TipoReporte tiporeporte) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoReporte mdtiporeporte = new ManejadorDatosTipoReporte();

        try {
            String msg = mdtiporeporte.registrarTipoReporte(tiporeporte);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }
    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Tipo de Reporte</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tiporeporte El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarTipoReporte(TipoReporte tiporeporte) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoReporte mdtiporeporte = new ManejadorDatosTipoReporte();

        try {
            String msg = mdtiporeporte.eliminarTipoReporte(tiporeporte);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }

        return resp;

    }
    /**
     * Operación que se encarga de realizar la modificación del
     * <strong>Tipo de Reporte</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tiporeporte El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarTipoReporte(TipoReporte tiporeporte) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoReporte mdtiporeporte = new ManejadorDatosTipoReporte();

        try {
            String msg = mdtiporeporte.modificarTipoReporte(tiporeporte);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }

        return resp;

    }
    /**
     * Función que se encarga de obtener un listado de los registros ya ingreados.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param desplazamiento Registros que se ha de brincar o pasar por alto.
     * @param paginacion Cantidad de registros a traer.
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ResultSet listadoTipoReporte(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoReporte mdTipoReporte = new ManejadorDatosTipoReporte();
        try {
            resp = mdTipoReporte.listadoTipoReporte(desplazamiento, paginacion);

        } catch (SQLException ex) {

           ex.printStackTrace();
        }
        return resp;
    }
    /**
     * Realiza una busqueda en la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @since 1.0
     */
    public ResultSet busquedaTipoReporte(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoReporte mdTipoReporte = new ManejadorDatosTipoReporte();
        try {
            resp = mdTipoReporte.busquedaTipoReporte(query, desplazamiento, paginacion);

        } catch (SQLException ex) {

            System.out.println("Debes registrar algo");
        }

        return resp;

    }
    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param query La cadena con la busqueda a evaluar.
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
    public int getCantidadRegistros(String query) {
        int resp = 0;
        try {
            ManejadorDatosTipoReporte mdtiporeporte = new ManejadorDatosTipoReporte();
            resp = mdtiporeporte.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a los tipos de reportes
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto TipoReporte con los valores correspondientes
     * @since 1.0
     */
    public TipoReporte getTipoReporte(int codigo) {
        TipoReporte resp = null;
        ManejadorDatosTipoReporte mdtiporeporte = new ManejadorDatosTipoReporte();
        try {
            resp = mdtiporeporte.getTipoReporte(codigo);
            System.out.println(resp.getNombretiporeporte());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
    
    /**
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados con relación a los tipos de reportes. No lanza excepciones, y si 
     * las hay, las registra en bitácora.
     *
     * @return Un arraylist que trae consigo los datos de la selección.
     * @since 1.0
     */
 public ArrayList<TipoReporte> listadoTipoReporte() {

        ArrayList<TipoReporte> tiporeportes = null;
        ManejadorDatosTipoReporte mdTipoReporte = new ManejadorDatosTipoReporte();
        try {
            ResultSet resp = null;
            resp = mdTipoReporte.listadoTipoReporte();
            if (resp.next()) {
                tiporeportes = new ArrayList<TipoReporte>();
                do {
                    TipoReporte tiporeporte = new TipoReporte();
                    tiporeporte.setIdtiporeporte(resp.getInt(1));
                    tiporeporte.setNombretiporeporte(resp.getString(2));
                    tiporeportes.add(tiporeporte);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tiporeportes;
    }    
}
