package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Venta;

/**
 * Esta clase de acceso a datos de <strong>Venta</strong> se encarga
 * de las operaciones directamente con la base de datos, para hacer su ingreso,
 * modificación, eliminacino del mismo. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados. Esta
 * clase busca que la separación entre código y base de datos directamente sea
 * alta, y no este tanto código embebido en el sistema.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorDatosVenta {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Venta</strong>.
     *
     * @param venta El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarVenta(Venta venta) throws SQLException {
        String resp = "";
        int id_venta=0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_venta(?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, venta.getFechaVenta());
            cs.setString(2, venta.getNombreComprador());
            cs.setDouble(3, venta.getMontoVenta());
            cs.setString(4, venta.getEstadoVenta());
            cs.setString(5, venta.getPlacaActivo());
            cs.setString(6, venta.getDireccionComprador());
            cs.setString(7, venta.getCodigoFactura());
            cs.setString(8, venta.getObservaciones());
            cs.setInt(9, venta.getCodigoTipoPago());
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_venta = rs.getInt(1);
            }
            venta.setCodigoVenta(id_venta);

            resp = cs.getString(10);
            
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>Venta</strong>.
     *
     * @param venta El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarVenta(Venta venta) throws SQLException {
 String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_venta(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setDate(1, venta.getFechaVenta());
            cs.setString(2, venta.getNombreComprador());
            cs.setDouble(3, venta.getMontoVenta());
            cs.setString(4, venta.getEstadoVenta());
            cs.setString(5, venta.getPlacaActivo());
            cs.setString(6, venta.getDireccionComprador());
            cs.setString(7, venta.getCodigoFactura());
            cs.setString(8, venta.getObservaciones());
            cs.setInt(9, venta.getCodigoTipoPago());
            cs.setInt(10, venta.getCodigoVenta());
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
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
     * <strong>Venta</strong> de la base de datos..
     *
     * @param venta El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarVenta(Venta venta) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_venta(?,?)  }");
            cs.setInt(1, venta.getCodigoVenta());
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
    public ResultSet listadoVenta(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_venta  LIMIT " + desplazamiento + "," + paginacion + ";");
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
    public ResultSet listadoVenta() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_venta;");
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
    public Venta getVenta(int codigo) throws SQLException {
        Venta resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_venta(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new Venta();
            resp.setCodigoVenta(rs.getInt(1));
            resp.setFechaVenta(rs.getDate(2));
            resp.setNombreComprador(rs.getString(3));
            resp.setMontoVenta(rs.getDouble(4));
            resp.setEstadoVenta(rs.getString(5));
            resp.setPlacaActivo(rs.getString(6));
            resp.setDireccionComprador(rs.getString(7));
            resp.setCodigoFactura(rs.getString(8));
            resp.setObservaciones(rs.getString(9));
            resp.setFechaRegistro(rs.getTimestamp(10));
            resp.setFechaUltimaModificacion(rs.getTimestamp(11));
            resp.setCodigoTipoPago(rs.getInt(12));
            
            
            
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
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reventa(?)}");
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
    public ResultSet busquedaVenta(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_venta(?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }

    /**
     * Método el cuál obtiene a través de un procedimiento almacenado los datos
     * almacenados en la base de datos del sistema
     * @return un resultset con los resultados encontrados o recibidos por la llamada
     * de un procedimiento almacenado
     */
    public ResultSet ReporteGeneralVenta() throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_venta()  }");
            resp = cs.executeQuery();
        } catch (SQLException ex) {
            throw ex;
        }
        return resp;
    }
    
    /**
     * Método el cuál obtiene a través de un procedimiento almacenado los datos
     * almacenados en la base de datos del sistema
     * @return un resultset con los resultados encontrados o recibidos por la llamada
     * de un procedimiento almacenado
     */
    public ResultSet ReporteGeneralVenta(java.sql.Date fein, java.sql.Date fin) throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_ventas_xfechaingreso(?,?)  }");
            cs.setDate(1, fein);
            cs.setDate(2, fin);
            resp = cs.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }
    
}
