package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosEstado;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.Estado;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Estado</strong> se encarga de las
 * operaciones de validación, solicitudes y respuestas, para hacer su ingreso,
 * modificación, eliminación de registros. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorEstado {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Estado</strong>.
     *
     * @param estado El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarEstado(Estado estado) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEstado mdestado = new ManejadorDatosEstado();

        try {
            String msg = mdestado.registrarEstado(estado);
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
     * <strong>Estado</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param estado El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarEstado(Estado estado) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEstado mdestado = new ManejadorDatosEstado();

        try {
            String msg = mdestado.eliminarEstado(estado);
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
     * <strong>Estado</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param estado El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarEstado(Estado estado) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEstado mdestado = new ManejadorDatosEstado();

        try {
            String msg = mdestado.modificarEstado(estado);
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
    public ResultSet listadoEstado(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosEstado mdEstado = new ManejadorDatosEstado();
        try {
            resp = mdEstado.listadoEstado(desplazamiento, paginacion);

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
    public ArrayList<Estado> listadoEstado() {

        ArrayList<Estado> estados = null;
        ManejadorDatosEstado mdEstado = new ManejadorDatosEstado();
        try {
            ResultSet resp = null;
            resp = mdEstado.listadoEstado();
            if (resp.next()) {
                estados = new ArrayList<Estado>();
                do {
                    Estado estado = new Estado();
                    estado.setIdestado(resp.getInt(1));
                    estado.setNombreestado(resp.getString(2));
                    estados.add(estado);
                } while (resp.next());
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return estados;
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
    public ResultSet busquedaEstado(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosEstado mdEstado = new ManejadorDatosEstado();
        try {
            resp = mdEstado.busquedaEstado(query, desplazamiento, paginacion);

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
            ManejadorDatosEstado mdestado = new ManejadorDatosEstado();
            resp = mdestado.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public Estado getEstado(int codigo) {
        Estado resp = null;
        ManejadorDatosEstado mdestado = new ManejadorDatosEstado();
        try {
            resp = mdestado.getEstado(codigo);
            System.out.println(resp.getNombreestado());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
