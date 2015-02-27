package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Reparacion;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de acceso a datos de <strong>Venta</strong> se encarga
 * de las operaciones directamente con la base de datos, para hacer su ingreso,
 * modificación, eliminacino del mismo. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados. Esta
 * clase busca que la separación entre código y base de datos directamente sea
 * alta, y no este tanto código embebido en el sistema.
 *
 * @author Producjeffer
 * @since 1.0
 * @version 2.0
 */
public class ManejadorDatosReparacion {
    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * Reparacion
     *
     * @param reparacion El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarReparacion(Reparacion reparacion) throws SQLException {
        String resp = "";
        int id_reparacion=0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_reparacion(?, ?, ?, ?)}");
            
            cs.setString(1, reparacion.getObservacion());
            cs.setInt(2, reparacion.getCodigoEstado());
            cs.setString(3, reparacion.getPlacaActivo());
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_reparacion = rs.getInt(1);
            }
            reparacion.setCodigoReparacion(id_reparacion);

            resp = cs.getString(4);
            
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }
    public String registrarDetalleReparacion(Reparacion reparacion) throws SQLException {
        String resp = "";
        int id_reparacion=0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_detalle_reparacion(?, ?, ?, ?, ?, ?, ?)}");
            
            cs.setString(1, reparacion.getIdUsuario());
            cs.setString(2, reparacion.getMotivoReparacion());
            cs.setString(3, reparacion.getnombreReparador());
            cs.setDate(4, (Date) reparacion.getFechaReparacion());
            cs.setDouble(5, reparacion.getCostoReparacion());
            cs.setDouble(6, reparacion.getCodigoReparacion());
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
             cs.executeQuery();

            resp = cs.getString(7);
            
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }
    /**
     * Operación que se encarga de realizar modificación del
     * reparacion.
     *
     * @param reparacion El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarReparacion(Reparacion reparacion) throws SQLException {
 String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_reparacion(?, ?, ?, ?, ?, ?)}");
            
            cs.setString(1, reparacion.getMotivoReparacion());
            cs.setString(2, reparacion.getnombreReparador());
            cs.setDate(3, (Date) reparacion.getFechaReparacion());
            cs.setDouble(4, reparacion.getCostoReparacion());
            cs.setInt(5, reparacion.getCodigoReparacion());
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(11);
            
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    

    }
    /**
     * Operación que se encarga de realizar la eliminación del
     * Reparacion de la base de datos..
     *
     * @param reparacion El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarReparacion(Reparacion reparacion) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_reparacion(?,?)  }");
            cs.setInt(1, reparacion.getCodigoReparacion());
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(2);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Función que se encarga de obtener un listado de los datos en la base de
     * datos. Todo trabaja a traves de vistas de la base de datos.
     *
     * @param desplazamiento Registros que se ha de brincar o pasar por alto.
     * @param paginacion Cantidad de registros a traer.
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @throws SQLException Si ocurre un error SQL
     * @since 1.0
     */
    public ResultSet listadoReparacion(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_reparacion  LIMIT " + desplazamiento + "," + paginacion + ";");
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }
  /**
     * Función que se encarga de obtener un listado de los datos en la base de
     * datos. Todo trabaja a traves de vistas de la base de datos. 
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @throws SQLException Si ocurre un error SQL
     * @since 1.0
     */
    public ResultSet listadoReparacion() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_reparacion;");
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos, recibe como parámetro el identificador del registro y con eso hace
     * la operacion.
     *
     * @param codigo El código / identificador del registro a buscar.
     * @return Un objeto Venta con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public Reparacion getReparacion(int codigo) throws SQLException {
        Reparacion resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_reparacion(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            
            resp = new Reparacion();
            resp.setCodigoReparacion(rs.getInt(1));
            resp.setObservacion(rs.getString(2));
            resp.setCodigoEstado(rs.getInt(3));
            resp.setPlacaActivo(rs.getString(4));
            
            rs.close();
        }
        return resp;
    }

    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro
     *
     * @param query La cadena con la busqueda a evaluar.
     * @return Un entero con la cantidad de registros.
     * @throws SQLException En caso de que lance una excepción de SQL.
     * @since 1.0
     */
    public int getCantidadFilas(String query) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reparacion(?)}");
        st.setString(1, query);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
        }
        Conexionmysql.cerrarConexion(con);
        return resp;
    }

    /**
     * Realiza una busqueda en la base de datos.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @throws SQLException Si ocurre una excepcion de SQL.
     * @since 1.0
     */
    public ResultSet busquedaReparacion(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_reparacion(?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }
    
}
