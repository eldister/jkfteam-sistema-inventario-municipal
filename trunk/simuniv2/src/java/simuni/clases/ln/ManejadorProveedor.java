package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosProveedor;
import simuni.entidades.Respuesta;
import simuni.entidades.Proveedor;
import simuni.entidades.mantenimientos.TipoProveedor;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Proveedor</strong> se encarga de
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
public class ManejadorProveedor {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Proveedor</strong>.
     *
     * @param proveedor El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarProveedor(Proveedor proveedor) {
        Respuesta resp = new Respuesta();
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();

        try {
            //throw new SQLException("Es un error de prueba, ojala se arregle jajaj");
            String msg = mdproveedor.registrarProveedor(proveedor);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
                try {
                    mdproveedor.registrarServiciosProveedor(proveedor);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    msg += " No se registraron todos los servicios del proveedor";
                }
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
     * <strong>Proveedor</strong> de la base de datos. No lanza excepciones, y
     * si las hay, las registra en bitácora.
     *
     * @param proveedor El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarProveedor(Proveedor proveedor) {
        Respuesta resp = new Respuesta();
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();

        try {
            String msg = mdproveedor.eliminarProveedor(proveedor);
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
     * <strong>Proveedor</strong> de la base de datos. No lanza excepciones, y
     * si las hay, las registra en bitácora.
     *
     * @param proveedor El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarProveedor(Proveedor proveedor) {
        Respuesta resp = new Respuesta();
        boolean error = false;
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();

        try {
            String msg = mdproveedor.eliminarServiciosProveedor(proveedor);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
                error = true;
                resp.setMensaje("No se podrá modificar el proveedor. "+msg);
            }
            if (!error) {
                msg = mdproveedor.modificarProveedor(proveedor);
                if (msg != null && !msg.startsWith("2")) {
                    resp.setNivel(1);
                    try {
                        mdproveedor.registrarServiciosProveedor(proveedor);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        msg += " No se registraron todos los servicios del proveedor";
                    }                    
                } else {
                    resp.setNivel(2);
                }
                resp.setMensaje(msg);
            }

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
    public ResultSet listadoProveedor(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosProveedor mdProveedor = new ManejadorDatosProveedor();
        try {
            resp = mdProveedor.listadoProveedor(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
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
    public ResultSet listadoProveedor(int desplazamiento, int paginacion, boolean ocultos) {
        ResultSet resp = null;
        ManejadorDatosProveedor mdProveedor = new ManejadorDatosProveedor();
        try {
            resp = mdProveedor.listadoProveedor(desplazamiento, paginacion, ocultos);

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
    public ResultSet busquedaProveedor(String query, int desplazamiento, int paginacion, boolean ocultos) {
        ResultSet resp = null;
        ManejadorDatosProveedor mdProveedor = new ManejadorDatosProveedor();
        try {
            resp = mdProveedor.busquedaProveedor(query, desplazamiento, paginacion, ocultos);

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
    public int getCantidadRegistros(String query, boolean ocultos) {
        int resp = 0;
        try {
            ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();
            resp = mdproveedor.getCantidadFilas(query, ocultos);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public Proveedor getProveedor(String cedula) {
        Proveedor resp = null;
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();
        try {
            resp = mdproveedor.getProveedor(cedula);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
     public ArrayList<Proveedor> getProveedoresXTipoServicio(int tiposervicio){
         ArrayList<Proveedor> resp = null;
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();
        try {
            resp = mdproveedor.getProveedoresXTipoServicio(tiposervicio);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;         
     }

    public boolean existeProveedor(String cedula) {
        boolean resp = false;
        ManejadorDatosProveedor mdproveedor = new ManejadorDatosProveedor();
        try {
            resp = mdproveedor.existeProveedor(cedula);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<TipoProveedor> listadoTipoProveedor() {
        ManejadorTipoProveedor mdtipopago = new ManejadorTipoProveedor();
        return mdtipopago.listadoTipoProveedor();
    }
}
