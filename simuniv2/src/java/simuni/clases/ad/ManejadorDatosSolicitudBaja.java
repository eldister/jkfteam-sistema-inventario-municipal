package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.SolicitudBaja;

/**
 * Esta clase de acceso a datos de <strong>SolicitudBaja</strong> se encarga
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
public class ManejadorDatosSolicitudBaja {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>SolicitudBaja</strong>.
     *
     * @param solicitudbaja El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarSolicitudBaja(SolicitudBaja solicitudbaja) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_solicitudbaja(?,?,?,?,?,?)}");
            cs.setString(1, solicitudbaja.getPlacaActivo());
            cs.setString(2, solicitudbaja.getUsuario());
            cs.setString(3, solicitudbaja.getMotivo());
            cs.setString(4, solicitudbaja.getObservaciones());
            cs.setString(5, solicitudbaja.getEstado());
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(6);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>SolicitudBaja</strong>.
     *
     * @param solicitudbaja El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarSolicitudBaja(SolicitudBaja solicitudbaja) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_solicitudbaja(?,?,?,?,?,?,?)  }");
            cs.setString(1, solicitudbaja.getPlacaActivo());
            cs.setString(2, solicitudbaja.getUsuario());
            cs.setString(3, solicitudbaja.getMotivo());
            cs.setString(4, solicitudbaja.getObservaciones());
            cs.setString(5, solicitudbaja.getEstado());
            cs.setInt(6, solicitudbaja.getCodigo());
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(7);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }
    
    public String modificarEstadoSolicitudBaja(SolicitudBaja solicitudbaja) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_estado_solicitudbaja(?,?,?)  }");
            cs.setString(1, solicitudbaja.getEstado());
            cs.setInt(2, solicitudbaja.getCodigo());
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(3);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }    

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>SolicitudBaja</strong> de la base de datos..
     *
     * @param solicitudbaja El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarSolicitudBaja(SolicitudBaja solicitudbaja) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_solicitudbaja(?,?)  }");
            cs.setInt(1, solicitudbaja.getCodigo());
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
    public ResultSet listadoSolicitudBaja(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_solicitudbaja  LIMIT " + desplazamiento + "," + paginacion + ";");
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
    public ResultSet listadoSolicitudBaja() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_solicitudbaja;");
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
     * @return Un objeto SolicitudBaja con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public SolicitudBaja getSolicitudBaja(int codigo) throws SQLException {
        SolicitudBaja resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_solicitudbaja(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new SolicitudBaja();
            resp.setCodigo(rs.getInt(1));
            resp.setPlacaActivo(rs.getString(2));
            resp.setUsuario(rs.getString(3));
            resp.setMotivo(rs.getString(4));
            resp.setObservaciones(rs.getString(5));
            resp.setFechaRegistro(rs.getDate(6));
            resp.setFechaUltimaModificacion(rs.getDate(7));
            resp.setEstado(rs.getString(8));
            rs.close();
        }
        return resp;
    }
    
    public SolicitudBaja getSolicitudBajaUsuario(int codigo,String usuario) throws SQLException {
        SolicitudBaja resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_solicitudbajausuario(?,?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        st.setString(2, usuario);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new SolicitudBaja();
            resp.setCodigo(rs.getInt(1));
            resp.setPlacaActivo(rs.getString(2));
            resp.setUsuario(rs.getString(3));
            resp.setMotivo(rs.getString(4));
            resp.setObservaciones(rs.getString(5));
            resp.setFechaRegistro(rs.getDate(6));
            resp.setFechaUltimaModificacion(rs.getDate(7));
            resp.setEstado(rs.getString(8));
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
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_resolicitudbaja(?)}");
        st.setString(1, query);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
        }
        Conexionmysql.cerrarConexion(con);
        return resp;
    }
    public int getCantidadFilasUsuario(String query,String usuario) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_resolicitudbajausuario(?,?)}");
        st.setString(1, query);
        st.setString(2, usuario);
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
    public ResultSet busquedaSolicitudBaja(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_solicitudbaja(?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }
    
    public ResultSet busquedaSolicitudBajaUsuario(String query, int desplazamiento, int paginacion,String usuario) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_solicitudbajausuario(?,?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            st.setString(4, usuario);
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }    

}
