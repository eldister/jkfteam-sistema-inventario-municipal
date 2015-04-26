package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosTipoPago;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoPago;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Tipo de Pago</strong> se
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
public class ManejadorTipoPago {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Tipo de Pago</strong>.
     *
     * @param tipopago El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarTipoPago(TipoPago tipopago) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoPago mdtipopago = new ManejadorDatosTipoPago();

        try {
            String msg = mdtipopago.registrarTipoPago(tipopago);
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
     * <strong>Tipo de Pago</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipopago El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarTipoPago(TipoPago tipopago) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoPago mdtipopago = new ManejadorDatosTipoPago();

        try {
            String msg = mdtipopago.eliminarTipoPago(tipopago);
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
     * <strong>Tipo de Pago</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipopago El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarTipoPago(TipoPago tipopago) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoPago mdtipopago = new ManejadorDatosTipoPago();

        try {
            String msg = mdtipopago.modificarTipoPago(tipopago);
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
    public ResultSet listadoTipoPago(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoPago mdTipoPago = new ManejadorDatosTipoPago();
        try {
            resp = mdTipoPago.listadoTipoPago(desplazamiento, paginacion);

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
    public ArrayList<TipoPago> listadoTipoPago() {

        ArrayList<TipoPago> tipopagos = null;
        ManejadorDatosTipoPago mdTipoPago = new ManejadorDatosTipoPago();
        try {
            ResultSet resp = null;
            resp = mdTipoPago.listadoTipoPago();
            if (resp.next()) {
                tipopagos = new ArrayList<TipoPago>();
                do {
                    TipoPago tipopago = new TipoPago();
                    tipopago.setIdtipopago(resp.getInt(1));
                    tipopago.setNombretipopago(resp.getString(2));
                    tipopagos.add(tipopago);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tipopagos;
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
    public ResultSet busquedaTipoPago(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoPago mdTipoPago = new ManejadorDatosTipoPago();
        try {
            resp = mdTipoPago.busquedaTipoPago(query, desplazamiento, paginacion);

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
            ManejadorDatosTipoPago mdtipopago = new ManejadorDatosTipoPago();
            resp = mdtipopago.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a los tipos de pagos
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto TipoPago con los valores correspondientes
     * @since 1.0
     */
    public TipoPago getTipoPago(int codigo) {
        TipoPago resp = null;
        ManejadorDatosTipoPago mdtipopago = new ManejadorDatosTipoPago();
        try {
            resp = mdtipopago.getTipoPago(codigo);
            System.out.println(resp.getNombretipopago());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
