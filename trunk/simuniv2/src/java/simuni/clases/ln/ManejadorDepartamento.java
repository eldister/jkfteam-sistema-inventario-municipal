package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosDepartamento;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.Departamento;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Departamento</strong> se encarga
 * de las operaciones de validación, solicitudes y respuestas, para hacer su
 * ingreso, modificación, eliminación de registros. Entre las operaciones
 * comunes que se solicitan estan agregar, modificar, eliminar, hacer un query
 * de busqueda y tambien hacer el listado por defecto que hay de los datos
 * ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorDepartamento {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Departamento</strong>.
     *
     * @param departamento El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarDepartamento(Departamento departamento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosDepartamento mddepartamento = new ManejadorDatosDepartamento();

        try {
            String msg = mddepartamento.registrarDepartamento(departamento);
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
     * <strong>Departamento</strong> de la base de datos. No lanza excepciones,
     * y si las hay, las registra en bitácora.
     *
     * @param departamento El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarDepartamento(Departamento departamento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosDepartamento mddepartamento = new ManejadorDatosDepartamento();

        try {
            String msg = mddepartamento.eliminarDepartamento(departamento);
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
     * <strong>Departamento</strong> de la base de datos. No lanza excepciones,
     * y si las hay, las registra en bitácora.
     *
     * @param departamento El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarDepartamento(Departamento departamento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosDepartamento mddepartamento = new ManejadorDatosDepartamento();

        try {
            String msg = mddepartamento.modificarDepartamento(departamento);
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
    public ResultSet listadoDepartamento(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosDepartamento mdDepartamento = new ManejadorDatosDepartamento();
        try {
            resp = mdDepartamento.listadoDepartamento(desplazamiento, paginacion);

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
    public ArrayList<Departamento> listadoDepartamento() {

        ArrayList<Departamento> departamentos = null;
        ManejadorDatosDepartamento mdDepartamento = new ManejadorDatosDepartamento();
        try {
            ResultSet resp = null;
            resp = mdDepartamento.listadoDepartamento();
            if (resp.next()) {
                departamentos = new ArrayList<Departamento>();
                do {
                    Departamento depto = new Departamento();
                    depto.setIddepartamento(resp.getInt(1));
                    depto.setNombredepartamento(resp.getString(2));
                    departamentos.add(depto);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return departamentos;
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
    public ResultSet busquedaDepartamento(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosDepartamento mdDepartamento = new ManejadorDatosDepartamento();
        try {
            resp = mdDepartamento.busquedaDepartamento(query, desplazamiento, paginacion);

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
            ManejadorDatosDepartamento mddepartamento = new ManejadorDatosDepartamento();
            resp = mddepartamento.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a los departamentos.
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto Departamento con los valores correspondientes
     * @since 1.0
     */
    public Departamento getDepartamento(int codigo) {
        Departamento resp = null;
        ManejadorDatosDepartamento mddepartamento = new ManejadorDatosDepartamento();
        try {
            resp = mddepartamento.getDepartamento(codigo);
            System.out.println(resp.getNombredepartamento());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
