package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosPermiso;
import simuni.entidades.Respuesta;
import simuni.entidades.mantenimientos.Permiso;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Permiso</strong> se encarga de
 * las operaciones de validación, solicitudes y respuestas, para hacer su
 * ingreso, modificación, eliminación de registros. Entre las operaciones
 * comunes que se solicitan estan agregar, modificar, eliminar, hacer un query
 * de busqueda y tambien hacer el listado por defecto que hay de los datos
 * ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorPermiso {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Permiso</strong>.
     *
     * @param permiso El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarPermiso(Permiso permiso) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();

        try {
            String msg = mdpermiso.registrarPermiso(permiso);
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

    public ArrayList<Respuesta> asignarPermisos(String permisos[], String idusuario) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();
        //validar si idusuario es nulo 
        try {
            String ms = mdpermiso.eliminarAsignacionPermiso(idusuario);
            Respuesta aux2 = new Respuesta();
            if (ms != null && ms.startsWith("2")) {
                aux2.setNivel(2);
            } else {
                aux2.setNivel(1);
            }
            aux2.setMensaje(ms);
            resp.add(aux2);
            if (permisos != null) {
                for (String permiso : permisos) {
                    //validar si permiso es numero
                    String msg = mdpermiso.registrarAsignarPermiso(idusuario, Integer.parseInt(permiso));
                    Respuesta aux = new Respuesta();
                    if (msg != null && msg.startsWith("2")) {
                        aux.setNivel(2);
                    } else {
                        aux.setNivel(1);
                    }
                    aux.setMensaje(msg);
                    resp.add(aux);
                }
            }

        } catch (SQLException ex) {
            Respuesta aux = new Respuesta();
            aux.setNivel(2);
            aux.setMensaje("Error: " + ex.getMessage());
            resp.add(aux);
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Permiso</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param permiso El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarPermiso(Permiso permiso) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();

        try {
            String msg = mdpermiso.eliminarPermiso(permiso);
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
     * <strong>Permiso</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param permiso El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarPermiso(Permiso permiso) {
        Respuesta resp = new Respuesta();
        ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();

        try {
            String msg = mdpermiso.modificarPermiso(permiso);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);
            System.out.println(msg);
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
    public ResultSet listadoPermiso(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosPermiso mdPermiso = new ManejadorDatosPermiso();
        try {
            resp = mdPermiso.listadoPermiso(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<Permiso> listadoPermisos() {

        ArrayList<Permiso> tiposusuario = null;
        ManejadorDatosPermiso mdPermiso = new ManejadorDatosPermiso();
        try {
            ResultSet resp = null;
            resp = mdPermiso.listadoPermiso();
            if (resp.next()) {
                tiposusuario = new ArrayList<Permiso>();
                do {
                    Permiso permiso = new Permiso();
                    permiso.setCodigoPermiso(resp.getInt(1));
                    permiso.setNombrePermiso(resp.getString(2));
                    tiposusuario.add(permiso);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tiposusuario;
    }

    public ArrayList<Permiso> listadoPermiso_Asignados(String idusuario) {

        ArrayList<Permiso> tiposusuario = null;
        ManejadorDatosPermiso mdPermiso = new ManejadorDatosPermiso();
        try {
            ResultSet resp = null;
            resp = mdPermiso.listadoPermiso_Asignados(idusuario);
            if (resp.next()) {
                tiposusuario = new ArrayList<Permiso>();
                do {
                    Permiso permiso = new Permiso();
                    permiso.setCodigoPermiso(resp.getInt(1));
                    permiso.setNombrePermiso(resp.getString(2));
                    tiposusuario.add(permiso);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tiposusuario;
    }

    public ArrayList<Permiso> listadoPermiso_Disponibles(String idusuario) {

        ArrayList<Permiso> tiposusuario = null;
        ManejadorDatosPermiso mdPermiso = new ManejadorDatosPermiso();
        try {
            ResultSet resp = null;
            resp = mdPermiso.listadoPermiso_Disponibles(idusuario);
            if (resp.next()) {
                tiposusuario = new ArrayList<Permiso>();
                do {
                    Permiso permiso = new Permiso();
                    permiso.setCodigoPermiso(resp.getInt(1));
                    permiso.setNombrePermiso(resp.getString(2));
                    tiposusuario.add(permiso);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return tiposusuario;
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
    public ResultSet busquedaPermiso(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosPermiso mdPermiso = new ManejadorDatosPermiso();
        try {
            resp = mdPermiso.busquedaPermiso(query, desplazamiento, paginacion);

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
            ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();
            resp = mdpermiso.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public Permiso getPermiso(int codigo) {
        Permiso resp = null;
        ManejadorDatosPermiso mdpermiso = new ManejadorDatosPermiso();
        try {
            resp = mdpermiso.getPermiso(codigo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
