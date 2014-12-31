package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosTipoLlanta;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoLlanta;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Tipo de Llanta</strong> se
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
public class ManejadorTipoLlanta {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Tipo de Llanta</strong>.
     *
     * @param tipollanta El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarTipoLlanta(TipoLlanta tipollanta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoLlanta mdtipollanta = new ManejadorDatosTipoLlanta();

        try {
            String msg = mdtipollanta.registrarTipoLlanta(tipollanta);
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
     * <strong>Tipo de Llanta</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipollanta El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarTipoLlanta(TipoLlanta tipollanta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoLlanta mdtipollanta = new ManejadorDatosTipoLlanta();

        try {
            String msg = mdtipollanta.eliminarTipoLlanta(tipollanta);
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
     * <strong>Tipo de Llanta</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipollanta El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarTipoLlanta(TipoLlanta tipollanta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoLlanta mdtipollanta = new ManejadorDatosTipoLlanta();

        try {
            String msg = mdtipollanta.modificarTipoLlanta(tipollanta);
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
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param desplazamiento Registros que se ha de brincar o pasar por alto.
     * @param paginacion Cantidad de registros a traer.
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ResultSet listadoTipoLlanta(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoLlanta mdTipoLlanta = new ManejadorDatosTipoLlanta();
        try {
            resp = mdTipoLlanta.listadoTipoLlanta(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ArrayList<TipoLlanta> listadoTipoLlanta() {
        ArrayList<TipoLlanta> tiposllantas = null;
        ManejadorDatosTipoLlanta mdTipoLlanta = new ManejadorDatosTipoLlanta();
        try {
            ResultSet resp = null;
            resp = mdTipoLlanta.listadoTipoLlanta();
            if (resp.next()) {
                tiposllantas = new ArrayList<TipoLlanta>();
                do {
                    TipoLlanta tipollantaa = new TipoLlanta();
                    tipollantaa.setIdtipollanta(resp.getInt(1));
                    tipollantaa.setNombretipollanta(resp.getString(2));
                    tiposllantas.add(tipollantaa);
                } while (resp.next());
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tiposllantas;
    }

    /**
     * Realiza una busqueda en la base de datos. No lanza excepciones, y si las
     * hay, las registra en bitácora.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @since 1.0
     */
    public ResultSet busquedaTipoLlanta(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoLlanta mdTipoLlanta = new ManejadorDatosTipoLlanta();
        try {
            resp = mdTipoLlanta.busquedaTipoLlanta(query, desplazamiento, paginacion);

        } catch (SQLException ex) {

            System.out.println("Debes registrar algo");
        }

        return resp;

    }

    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro. No lanza excepciones, y si las hay,
     * las registra en bitácora.
     *
     * @param query La cadena con la busqueda a evaluar.
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
    public int getCantidadRegistros(String query) {
        int resp = 0;
        try {
            ManejadorDatosTipoLlanta mdtipollanta = new ManejadorDatosTipoLlanta();
            resp = mdtipollanta.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public TipoLlanta getTipoLlanta(int codigo) {
        TipoLlanta resp = null;
        ManejadorDatosTipoLlanta mdtipollanta = new ManejadorDatosTipoLlanta();
        try {
            resp = mdtipollanta.getTipoLlanta(codigo);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
