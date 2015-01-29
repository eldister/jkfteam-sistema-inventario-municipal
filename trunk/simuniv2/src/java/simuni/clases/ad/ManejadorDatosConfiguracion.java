package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Configuracion;

/**
 * Esta clase de acceso a datos de <strong>Configuracion</strong> se encarga
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
public class ManejadorDatosConfiguracion {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Configuracion</strong>.
     *
     * @param configuracion El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarConfiguracion(Configuracion configuracion) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_configuracion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, configuracion.getNombreConfiguracion());
            cs.setString(2, configuracion.getPathMysqlDump());
            cs.setString(3, configuracion.getPathBackup());
            cs.setString(4, configuracion.getPrefijoBackup());
            cs.setString(5, configuracion.getServerBaseDatos());
            cs.setString(6, configuracion.getNombreBaseDatos());
            cs.setString(7, configuracion.getServerWeb());
            cs.setString(8, configuracion.getServerArchivos());
            cs.setString(9, configuracion.getPathBaseArchivos());
            cs.setString(10, configuracion.getPathImagenesArchivos());//activos
            cs.setString(11, configuracion.getPathImagenesDefault());
            cs.setString(12, configuracion.getPathImagenesProveedores());
            cs.setString(13, configuracion.getContextoWeb());
            cs.setString(14, configuracion.getContextoArchivosActivos());
            cs.setString(15, configuracion.getContextoArchivoProveedores());
            cs.setInt(16, configuracion.getLapsoTiempoBackup());
            cs.setInt(17, configuracion.getLapsoTimpoReporteActivo());
            cs.setInt(18, configuracion.getLapsoTimpoReporteProveedores());
            cs.setInt(19, configuracion.getLapsoTimpoReporteGeneral());
            cs.registerOutParameter(20, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(20);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>Configuracion</strong>.
     *
     * @param configuracion El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarConfiguracion(Configuracion configuracion) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_configuracion(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
            cs.setString(1, configuracion.getNombreConfiguracion());
            cs.setString(2, configuracion.getPathMysqlDump());
            cs.setString(3, configuracion.getPathBackup());
            cs.setString(4, configuracion.getPrefijoBackup());
            cs.setString(5, configuracion.getServerBaseDatos());
            cs.setString(6, configuracion.getNombreBaseDatos());
            cs.setString(7, configuracion.getServerWeb());
            cs.setString(8, configuracion.getServerArchivos());
            cs.setString(9, configuracion.getPathBaseArchivos());
            cs.setString(10, configuracion.getPathImagenesArchivos());//activos
            cs.setString(11, configuracion.getPathImagenesDefault());
            cs.setString(12, configuracion.getPathImagenesProveedores());
            cs.setString(13, configuracion.getContextoWeb());
            cs.setString(14, configuracion.getContextoArchivosActivos());
            cs.setString(15, configuracion.getContextoArchivoProveedores());
            cs.setInt(16, configuracion.getLapsoTiempoBackup());
            cs.setInt(17, configuracion.getLapsoTimpoReporteActivo());
            cs.setInt(18, configuracion.getLapsoTimpoReporteProveedores());
            cs.setInt(19, configuracion.getLapsoTimpoReporteGeneral());
            cs.setInt(20, configuracion.getCodigoConfiguracion());
            cs.registerOutParameter(21, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(21);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Configuracion</strong> de la base de datos..
     *
     * @param configuracion El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarConfiguracion(Configuracion configuracion) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_configuracion(?,?)  }");
            cs.setInt(1, configuracion.getCodigoConfiguracion());
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
    public ResultSet listadoConfiguracion(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_configuracion  LIMIT " + desplazamiento + "," + paginacion + ";");
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
    public ResultSet listadoConfiguracion() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_configuracion;");
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
     * @return Un objeto Configuracion con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public Configuracion getConfiguracion(int codigo) throws SQLException {
        Configuracion resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_configuracion(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new Configuracion();
          /*  resp.setIdconfiguracion(rs.getInt(1));
            resp.setNombreconfiguracion(rs.getString(2));*/
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
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reconfiguracion(?)}");
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
    public ResultSet busquedaConfiguracion(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_configuracion(?,?,?)}");
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
