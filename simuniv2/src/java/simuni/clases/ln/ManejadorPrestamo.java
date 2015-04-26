package simuni.clases.ln;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.clases.ad.ManejadorDatosPrestamo;
import simuni.entidades.Respuesta;
import simuni.entidades.Prestamo;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Prestamo</strong> se encarga de las
 * operaciones de validación, solicitudes y respuestas, para hacer su ingreso,
 * modificación, eliminación de registros. 
 *
 * @author Keylin
 */
public class ManejadorPrestamo {
        /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Prestamo</strong>.
     *
     * @param prestamo El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarPrestamo(Prestamo prestamo) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();

        try {
            String msg = mdprestamo.registrarPrestamo(prestamo);
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
     * <strong>Prestamo</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param prestamo El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarPrestamo(Prestamo prestamo) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();

        try {
            String msg = mdprestamo.eliminarPrestamo(prestamo);
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
     * <strong>Prestamo</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param prestamo El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarPrestamo(Prestamo prestamo) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();

        try {
            String msg = mdprestamo.modificarPrestamo(prestamo);
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
    public ResultSet listadoPrestamo(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosPrestamo mdprestamo= new ManejadorDatosPrestamo();
        try {
            resp = mdprestamo.listadoPrestamo(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return resp;
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
//     */
    public ResultSet busquedaPrestamo(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();
        try {
            resp = mdprestamo.busquedaPrestamo(query, desplazamiento, paginacion);

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
            ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();
            resp = mdprestamo.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a los préstamos
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto Prestamo con los valores correspondientes
     * @since 1.0
     */
    public Prestamo getPrestamo(int codigo) {
        Prestamo resp = null;
        ManejadorDatosPrestamo mdprestamo = new ManejadorDatosPrestamo();
        try {
            resp = mdprestamo.getPrestamo(codigo);
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
    
}
