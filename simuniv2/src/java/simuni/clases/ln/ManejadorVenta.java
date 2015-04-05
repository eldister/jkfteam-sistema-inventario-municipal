package simuni.clases.ln;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosBaja;
import simuni.clases.ad.ManejadorDatosVenta;
import simuni.entidades.Respuesta;
import simuni.entidades.Venta;
import simuni.entidades.mantenimientos.TipoPago;
import simuni.utils.UtilidadesServlet;
import simuni.intefaces.IReporteador;

/**
 * Esta clase de lógica de negocios de <strong>Venta</strong> se encarga de las
 * operaciones de validación, solicitudes y respuestas, para hacer su ingreso,
 * modificación, eliminación de registros. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorVenta implements IReporteador {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Venta</strong>.
     *
     * @param venta El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarVenta(Venta venta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosVenta mdventa = new ManejadorDatosVenta();

        try {
            String msg = mdventa.registrarVenta(venta);
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
     * <strong>Venta</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param venta El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarVenta(Venta venta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosVenta mdventa = new ManejadorDatosVenta();

        try {
            String msg = mdventa.eliminarVenta(venta);
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
     * <strong>Venta</strong> de la base de datos. No lanza excepciones, y si
     * las hay, las registra en bitácora.
     *
     * @param venta El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarVenta(Venta venta) {
        Respuesta resp = new Respuesta();
        ManejadorDatosVenta mdventa = new ManejadorDatosVenta();

        try {
            String msg = mdventa.modificarVenta(venta);
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
    public ResultSet listadoVenta(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosVenta mdVenta = new ManejadorDatosVenta();
        try {
            resp = mdVenta.listadoVenta(desplazamiento, paginacion);

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
    public ResultSet busquedaVenta(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosVenta mdVenta = new ManejadorDatosVenta();
        try {
            resp = mdVenta.busquedaVenta(query, desplazamiento, paginacion);

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
            ManejadorDatosVenta mdventa = new ManejadorDatosVenta();
            resp = mdventa.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }
    public ArrayList<TipoPago> listadoTipoPago() {
        ManejadorTipoPago mdtipopago = new ManejadorTipoPago();
        return mdtipopago.listadoTipoPago();
    }

    public Venta getVenta(int codigo) {
        Venta resp = null;
        ManejadorDatosVenta mdventa = new ManejadorDatosVenta();
        try {
            resp = mdventa.getVenta(codigo);
         
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Método abstracto implementado de la interfaz IReporteador para obtener los datos 
     * que serán utilizados en el reporte
     * @return resp un arraylist con los datos recupertados de la base de datos para 
     * que seran utilizados en el reporte
     */
    

    @Override
    public ArrayList<String[]> obtenerDatosReporte() {
        ArrayList<String[]> resp = new ArrayList<String[]>();
        ManejadorDatosBaja mdventa = new ManejadorDatosBaja();
        try {
            ResultSet rs = mdventa.ReporteGeneralReparacion();
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs != null&&rs.next()) {
                resp.add(new String[]{
                    rsmd.getColumnLabel(1),
                    rsmd.getColumnLabel(2),
                    rsmd.getColumnLabel(3),
                    rsmd.getColumnLabel(4),
                    rsmd.getColumnLabel(5),
                    rsmd.getColumnLabel(6)
                });
                
                do {
                resp.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rsmd.getColumnLabel(6)
                });
                } while (rs.next());
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return resp;
    }

    @Override
    public ArrayList<String[]> obtenerDatosReporte(Date fini, Date ffin) {
        ArrayList<String[]> resp = new ArrayList<String[]>();
        ManejadorDatosBaja mventa = new ManejadorDatosBaja();
        try {
            ResultSet rs = mventa.ReporteGeneralBajas(fini, ffin);
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs != null&&rs.next()) {
                resp.add(new String[]{
                    rsmd.getColumnLabel(1),
                    rsmd.getColumnLabel(2),
                    rsmd.getColumnLabel(3),
                    rsmd.getColumnLabel(4),
                    rsmd.getColumnLabel(5),
                    rsmd.getColumnLabel(6)
                });
                
                do {
                resp.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rsmd.getColumnLabel(6)
                });
                } while (rs.next());
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return resp;
    }
}
