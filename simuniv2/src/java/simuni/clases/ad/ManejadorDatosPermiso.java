package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.mantenimientos.Permiso;

/**
 * Esta clase de acceso a datos de <strong>Permiso de </strong> se encarga
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
public class ManejadorDatosPermiso {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Permiso de </strong>.
     *
     * @param permiso El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarPermiso(Permiso permiso) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_permiso(?,?)}");
            cs.setString(1, permiso.getNombrePermiso());
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
     * Método que registra el momento cuando se asigna el permiso a un usuario
     * @param usuario a quien se le asignara el permiso de usuario
     * @param codigopermiso el id del permiso que se le otorgara a dicho usuario
     * @throws SQLException en caso de que ocurra una excepsion sql 
     * @since 1.0
     */
    public String registrarAsignarPermiso(String usuario,int codigopermiso) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_asignar_permisosusuario(?,?,?)}");
            cs.setInt(1, codigopermiso);
            cs.setString(2, usuario);
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
     * Método para eliminar los permisos asignados a un usuario
     * @param usuario a quien se le removeran los permisos de usuario
     * @throws SQLException en caso de que ocurra una excepsion sql 
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
    public String eliminarAsignacionPermiso(String usuario) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_eliminacion_permisosusuario(?,?)}");
            cs.setString(1, usuario);
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
     * Operación que se encarga de realizar modificación del
     * <strong>Permiso de </strong>.
     *
     * @param permiso El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarPermiso(Permiso permiso) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_permiso(?,?,?)  }");
            cs.setString(1, permiso.getNombrePermiso());
            cs.setInt(2, permiso.getCodigoPermiso());
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
     * <strong>Permiso de </strong> de la base de datos..
     *
     * @param permiso El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarPermiso(Permiso permiso) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_permiso(?,?)  }");
            cs.setInt(1, permiso.getCodigoPermiso());
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
    public ResultSet listadoPermiso(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_permisos  LIMIT " + desplazamiento + "," + paginacion + ";");
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }
    
    /**
     * Método que obtiene todo los resultados de los permisos a través de una
     * vista de la base de datos
     * @throws SQLException en caso de que ocurra una excepcion 
     * @return un resultset con el resultado al ejecutar el query sql
     */
        public ResultSet listadoPermiso() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_permisos;");
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    } 
   /**
     * Método que obtiene todo los permisos que han sido asignados a los usuarios del
     * sistema
     * @param nombre_usuario usuarios de quien se obtendran la lista de permisos
     * @throws SQLException en caso de que ocurra una excepcion 
     * @return un resultset con el listado de los permisos asignados a dicho usuario
     */
   public ResultSet listadoPermiso_Asignados(String nombre_usuario) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            System.out.println("");
            PreparedStatement st = con.prepareCall("call simuni_sp_obtener_permisosusuario('" +nombre_usuario + "')");
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        System.out.println("Listooo "+resp!=null);
        return resp;

    } 
   /**
     * Método que obtiene todo los permisos que tiene disponibles un usuario del
     * sistema
     * @param nombre_usuario usuarios de quien se obtendran la lista de permisos disponibles
     * @throws SQLException en caso de que ocurra una excepcion 
     * @return un resultset con el listado de los permisos disponibles de dicho usuario
     */
   public ResultSet listadoPermiso_Disponibles(String nombre_usuario) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("call simuni_sp_obtener_nopermisosusuario('" +nombre_usuario + "')");
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
     * @return Un objeto Permiso con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public Permiso getPermiso(int codigo) throws SQLException {
        Permiso resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_permiso(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new Permiso();
            resp.setCodigoPermiso(rs.getInt(1));
            resp.setNombrePermiso(rs.getString(2));
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
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_repermisos(?)}");
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
    public ResultSet busquedaPermiso(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_permiso(?,?,?)}");
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
