package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosTipoProveedor;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.TipoProveedor;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Tipo de Proveedor</strong> se
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
public class ManejadorTipoProveedor {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Tipo de Proveedor</strong>.
     *
     * @param tipoproveedor El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarTipoProveedor(TipoProveedor tipoproveedor) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoProveedor mdtipoproveedor = new ManejadorDatosTipoProveedor();

        try {
            String msg = mdtipoproveedor.registrarTipoProveedor(tipoproveedor);
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
     * <strong>Tipo de Proveedor</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipoproveedor El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarTipoProveedor(TipoProveedor tipoproveedor) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoProveedor mdtipoproveedor = new ManejadorDatosTipoProveedor();

        try {
            String msg = mdtipoproveedor.eliminarTipoProveedor(tipoproveedor);
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
     * <strong>Tipo de Proveedor</strong> de la base de datos.
     * No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param tipoproveedor El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarTipoProveedor(TipoProveedor tipoproveedor) {
        Respuesta resp = new Respuesta();
        ManejadorDatosTipoProveedor mdtipoproveedor = new ManejadorDatosTipoProveedor();

        try {
            String msg = mdtipoproveedor.modificarTipoProveedor(tipoproveedor);
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
    public ResultSet listadoTipoProveedor(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoProveedor mdTipoProveedor = new ManejadorDatosTipoProveedor();
        try {
            resp = mdTipoProveedor.listadoTipoProveedor(desplazamiento, paginacion);

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
    public ArrayList<TipoProveedor> listadoTipoProveedor() {

        ArrayList<TipoProveedor> tipoproveedors = null;
        ManejadorDatosTipoProveedor mdTipoProveedor = new ManejadorDatosTipoProveedor();
        try {
            ResultSet resp = null;
            resp = mdTipoProveedor.listadoTipoProveedor();
            if (resp.next()) {
                tipoproveedors = new ArrayList<TipoProveedor>();
                do {
                    TipoProveedor tipoproveedor = new TipoProveedor();
                    tipoproveedor.setCodigoTipoProveedor(resp.getInt(1));
                    tipoproveedor.setNombreTipoProveedor(resp.getString(2));
                    tipoproveedors.add(tipoproveedor);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tipoproveedors;
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
    public ResultSet busquedaTipoProveedor(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosTipoProveedor mdTipoProveedor = new ManejadorDatosTipoProveedor();
        try {
            resp = mdTipoProveedor.busquedaTipoProveedor(query, desplazamiento, paginacion);

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
            ManejadorDatosTipoProveedor mdtipoproveedor = new ManejadorDatosTipoProveedor();
            resp = mdtipoproveedor.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a los tipos de proveedores
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto TipoProveedor con los valores correspondientes
     * @since 1.0
     */
    public TipoProveedor getTipoProveedor(int codigo) {
        TipoProveedor resp = null;
        ManejadorDatosTipoProveedor mdtipoproveedor = new ManejadorDatosTipoProveedor();
        try {
            resp = mdtipoproveedor.getTipoProveedor(codigo);
            System.out.println(resp.getNombreTipoProveedor());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
