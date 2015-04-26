package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.clases.ad.ManejadorDatosSolicitudBaja;
import simuni.entidades.Respuesta;
import simuni.entidades.SolicitudBaja;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>SolicitudBaja</strong> se encarga de las
 * operaciones de validación, solicitudes y respuestas, para hacer su ingreso,
 * modificación, eliminación de registros. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorSolicitudBaja {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>SolicitudBaja</strong>.
     *
     * @param solicitudbaja El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarSolicitudBaja(SolicitudBaja solicitudbaja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();

        try {
            String msg = mdsolicitudbaja.registrarSolicitudBaja(solicitudbaja);
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
     * <strong>SolicitudBaja</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param solicitudbaja El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarSolicitudBaja(SolicitudBaja solicitudbaja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();

        try {
            String msg = mdsolicitudbaja.eliminarSolicitudBaja(solicitudbaja);
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
     * <strong>SolicitudBaja</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param solicitudbaja El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarSolicitudBaja(SolicitudBaja solicitudbaja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();

        try {
            String msg = mdsolicitudbaja.modificarSolicitudBaja(solicitudbaja);
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
     * Operación que se encarga de realizar la modificación del estado de la
     * <strong>SolicitudBaja</strong> de la base de datos.
     *
     * @param solicitudbaja El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarEstadoSolicitudBaja(SolicitudBaja solicitudbaja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();

        try {
            String msg = mdsolicitudbaja.modificarEstadoSolicitudBaja(solicitudbaja);
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
    public ResultSet listadoSolicitudBaja(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosSolicitudBaja mdSolicitudBaja = new ManejadorDatosSolicitudBaja();
        try {
            resp = mdSolicitudBaja.listadoSolicitudBaja(desplazamiento, paginacion);

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
     */
    public ResultSet busquedaSolicitudBaja(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosSolicitudBaja mdSolicitudBaja = new ManejadorDatosSolicitudBaja();
        try {
            resp = mdSolicitudBaja.busquedaSolicitudBaja(query, desplazamiento, paginacion);

        } catch (SQLException ex) {

            System.out.println("Debes registrar algo");
        }

        return resp;

    }
    
    /**
     * Realiza una busqueda en la base de datos sobre las solicitudes de baja.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @param usuario identificador del usuario
     * @return Un ResultSet con los resultados de la busqueda
     * @since 1.0
     */
    public ResultSet busquedaSolicitudBajaUsuario(String query, int desplazamiento, int paginacion,String usuario) {
        ResultSet resp = null;
        ManejadorDatosSolicitudBaja mdSolicitudBaja = new ManejadorDatosSolicitudBaja();
        try {
            resp = mdSolicitudBaja.busquedaSolicitudBajaUsuario(query, desplazamiento, paginacion,usuario);

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
            ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();
            resp = mdsolicitudbaja.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }
    
    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro. 
     *
     * @param query La cadena con la busqueda a evaluar.
     * @param usuario identificacion del usuario del sistema
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
    public int getCantidadRegistrosUsuario(String query,String usuario) {
        int resp = 0;
        try {
            ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();
            resp = mdsolicitudbaja.getCantidadFilasUsuario(query,usuario);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }
    
    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a las solicitudes de baja
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto solicitudes de baja con los valores correspondientes
     * @since 1.0
     */
    public SolicitudBaja getSolicitudBaja(int codigo) {
        SolicitudBaja resp = null;
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();
        try {
            resp = mdsolicitudbaja.getSolicitudBaja(codigo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
    
    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos con relacion a las solicitudes de bajas por usuario.
     *
     * @param codigo El código / identificador del registro a buscar.
     * @param usuario identificador del usuario del sistema.
     * @return Un objeto de solicitud de baja con los valores correspondientes
     * por usuario.
     * @since 1.0
     */
    public SolicitudBaja getSolicitudBajaUsuario(int codigo,String usuario) {
        SolicitudBaja resp = null;
        ManejadorDatosSolicitudBaja mdsolicitudbaja = new ManejadorDatosSolicitudBaja();
        try {
            resp = mdsolicitudbaja.getSolicitudBajaUsuario(codigo,usuario);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }    
    
}
