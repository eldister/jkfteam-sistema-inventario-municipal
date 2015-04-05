package simuni.clases.ln;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosBaja;
import simuni.entidades.Respuesta;
import simuni.entidades.Baja;
import simuni.intefaces.IReporteador;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Baja</strong> se
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
public class ManejadorBaja implements IReporteador{

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Baja</strong>.
     *
     * @param baja El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarBaja(Baja baja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosBaja mdbaja = new ManejadorDatosBaja();

        try {
            String msg = mdbaja.registrarBaja(baja);
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
     * <strong>Baja</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param baja El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarBaja(Baja baja) {
        Respuesta resp = new Respuesta();
        ManejadorDatosBaja mdbaja = new ManejadorDatosBaja();

        try {
            String msg = mdbaja.eliminarBaja(baja);
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
    public ResultSet listadoBaja(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosBaja mdBaja = new ManejadorDatosBaja();
        try {
            resp = mdBaja.listadoBaja(desplazamiento, paginacion);

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
    public ResultSet busquedaBaja(String query, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosBaja mdBaja = new ManejadorDatosBaja();
        try {
            resp = mdBaja.busquedaBaja(query, desplazamiento, paginacion);

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
            ManejadorDatosBaja mdbaja = new ManejadorDatosBaja();
            resp = mdbaja.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public Baja getBaja(int codigo) {
        Baja resp = null;
        ManejadorDatosBaja mdbaja = new ManejadorDatosBaja();
        try {
            resp = mdbaja.getBaja(codigo);
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
        ManejadorDatosBaja mdbaja = new ManejadorDatosBaja();
        try {
            ResultSet rs = mdbaja.ReporteGeneralReparacion();
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs != null&&rs.next()) {
                resp.add(new String[]{
                    rsmd.getColumnLabel(1),
                    rsmd.getColumnLabel(2),
                    rsmd.getColumnLabel(3),
                    rsmd.getColumnLabel(4),
                    rsmd.getColumnLabel(5)
                });
                
                do {
                resp.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
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
        ManejadorDatosBaja mbaja = new ManejadorDatosBaja();
        try {
            ResultSet rs = mbaja.ReporteGeneralBajas(fini, ffin);
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs != null&&rs.next()) {
                resp.add(new String[]{
                    rsmd.getColumnLabel(1),
                    rsmd.getColumnLabel(2),
                    rsmd.getColumnLabel(3),
                    rsmd.getColumnLabel(4),
                    rsmd.getColumnLabel(5)
                });
                
                do {
                resp.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5)
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
