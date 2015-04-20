/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.Usuario;
import simuni.entidades.bd.Conexionmysql;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosUsuario {
    
    /**
     *Método para el logeo o inicio de sesion de un usuario al sistema
     * @param usuario nombre de usuario a logearse
     * @param contrasena contraseña del usuario a logearse
     * @return un objeto tipo usuario con la informacion de la persona
     * que ha iniciado sesion en el sistema
     * @since 1.0
     */
    public Usuario login(String usuario, String contrasena) throws SQLException {

        Usuario usuarioresp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_login(?,?)}");
        st.setString(1, usuario);
        st.setString(2, contrasena);
        ResultSet rs = st.executeQuery();

        System.out.println("Pase por aqui lgoin");
        if (rs.next()) {
            usuarioresp = new Usuario();
            usuarioresp.setNombreusuario(rs.getString("SM00IDUS"));
            usuarioresp.setTipousuario(rs.getInt("SM02TIUS"));
            usuarioresp.setAreatrabajo(rs.getInt("SM10IDDE"));
            usuarioresp.setNombre(rs.getString("SM25NOUS"));
            usuarioresp.setApellido1(rs.getString("SM25A1US"));
            usuarioresp.setApellido1(rs.getString("SM25A2US"));

            System.out.println("USUARIO SI HAY");
        }
        Conexionmysql.cerrarConexion(con);
        return usuarioresp;
    }
    
    /**
     * Método que obtiene un menu deacuerdo al tipo de usuario que se ha logeado o
     * iniciado sesion
     * @param usuario objeto con toda la informacion de la persona o usuairo logeada
     * @return un objeto de tipo usuario con toda la infomarcion de una persona
     */
    public Usuario getMenuUsuario(Usuario usuario) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_menu_usuario(?)}");
        st.setString(1, usuario.getNombreusuario());
        ResultSet rs = st.executeQuery();

        if (rs.next()) {

            usuario.setMenuusuario(rs.getString("SM01TEME"));

            System.out.println("Menu SI HAY");
        }
        Conexionmysql.cerrarConexion(con);

        return usuario;
    }

    /**
     * Metodo para registrar un unevo usuario al sistema
     *
     * @param usuario El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarUsuario(Usuario usuario) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_registro_usuario(?,?,?,?,?,?,?,?,?,?,?)  }");
            cs.setString(1, usuario.getNombreusuario());
            cs.setString(2, usuario.getContrasena());
            cs.setInt(3, usuario.getTipousuario());
            cs.setInt(4, usuario.getAreatrabajo());
            cs.setString(5, usuario.getNombre());
            cs.setString(6, usuario.getApellido1());
            cs.setString(7, usuario.getApellido2());
            cs.setInt(8, usuario.getCodigoPuesto());
            cs.setString(9, usuario.getCedula());
            cs.setString(10, usuario.getEmail());
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(11);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    /**
     * Método para la modificación o cambios de la información de un usuario 
     * en particular
     *
     * @param usuario El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarUsuario(Usuario usuario) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_usuario(?,?,?,?,?,?,?,?,?,?,?)  }");
            cs.setString(1, usuario.getNombreusuario());
            cs.setString(2, usuario.getContrasena());
            cs.setInt(3, usuario.getTipousuario());
            cs.setInt(4, usuario.getAreatrabajo());
            cs.setString(5, usuario.getNombre());
            cs.setString(6, usuario.getApellido1());
            cs.setString(7, usuario.getApellido2());
            cs.setInt(8, usuario.getCodigoPuesto());
            cs.setString(9, usuario.getCedula());
            cs.setString(10, usuario.getEmail());
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(11);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    
    /**
     * Método para modificar o cambiar la contraseña de un usuario en particular
     * en caso de que el usuario asi lo desee
     *
     * @param usuario objeto tipo Usuario referente al individuo que va a cambiar su contraseña
     * contiene la informacion del usuario, ademas de la nueva contraseña
     * @param pass_actual la contraseña que posee actualmente el usuario
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarClaveUsuario(Usuario usuario, String pass_actual) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_clave_usuario(?,?,?,?)}");
            cs.setString(1, usuario.getNombreusuario());
            cs.setString(2, usuario.getContrasena());
            cs.setString(3, pass_actual);
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();

            resp = cs.getString(4);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    /**
     * Método para actualizar la contraseña de un usuario, se llama resetear puesto que 
     * se le puede colocar la que tenia anteriormente o cualquiera que considere poner
     * el encargado de las TICs
     *
     * @param usuario a quien se le hara el reseteo de la contraseña
     * contiene la información del usuario y la contraseña nueva
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String resetearClaveUsuario(Usuario usuario) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_reseteoclave_usuario(?,?,?)}");
            cs.setString(1, usuario.getNombreusuario());
            cs.setString(2, usuario.getContrasena());
            cs.registerOutParameter(3, java.sql.Types.VARCHAR);
            cs.execute();

            resp = cs.getString(3);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    /**
     * Método para la deshabilitacion de un registro de un usuario del sistema 
     *
     * @param usuario a quien se deshabilitara su registro del sistema
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarUsuario(Usuario usuario) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_usuario(?,?)  }");
            cs.setString(1, usuario.getNombreusuario());
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(2);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    /**
     * Método contrario al anterior, este método a un usuario cuyo registro en la base
     * de datos del sistema se encuentra en estado INACTIVO.
     *
     * @param usuario a quien se rehabilitara el registro en la base de datos
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws Exception Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String reactivarUsuario(Usuario usuario) throws Exception {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_reactivacion_usuario(?,?)  }");
            cs.setString(1, usuario.getNombreusuario());
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(2);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }

    /**
     * Método para obtener a un usuario en especifico de la base de datos
     *
     * @param usuario cuyos datos serán obtenidos de la base de datos
     * @return un objeto de tipo usuario
     * @since 1.0
     */
    public Usuario obtenerUsuario(String usuario) throws SQLException {

        Usuario usuarioresp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_usuario(?)}");
        st.setString(1, usuario);

        ResultSet rs = st.executeQuery();

        if (rs.next()) {
            usuarioresp = new Usuario();
            usuarioresp.setNombreusuario(rs.getString(1));
            usuarioresp.setContrasena(rs.getString(2));
            usuarioresp.setTipousuario(rs.getInt(3));
            usuarioresp.setAreatrabajo(rs.getInt(4));
            usuarioresp.setNombre(rs.getString(5));
            usuarioresp.setApellido1(rs.getString(6));
            usuarioresp.setApellido2(rs.getString(7));
            usuarioresp.setCodigoPuesto(rs.getInt(8));
            usuarioresp.setCedula(rs.getString(9));
            usuarioresp.setEmail(rs.getString(10));
            System.out.println("USUARIO SI HAY");
        }
        Conexionmysql.cerrarConexion(con);
        return usuarioresp;
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
    public ResultSet busquedaUsuario(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_usuario(?,?,?)}");
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
     * Método que realiza una búsqueda de un usuario que se encuentra inactivo
     * en el sistema
     * @param query sentencia para la búsqueda del usuario
     * @param desplazamiento de los registros
     * @param paginacion de los registros
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
    public ResultSet busquedaUsuarioInactivo(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_usuarioinactivo(?,?,?)}");
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
     * Método para obtener una vista de la información de los usuarios registrados
     * 
     * @return un resultset con los resultados obtenedos al ejecutar la vista
     * @throws SQLException si ocurre una excepción sql
     * @since 1.0
     */
    public ResultSet listadoUsuario() throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("select * from  simuni_vw_listado_usuarios2");

            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }

    /**
     *Método para conocer si un usuario posee un permiso valido.
     * 
     * @param idusuario identificación del usuario
     * @param codigopermiso código de permiso relacionado a un usuario
     * @return Resultset con los resultados al ejecutar el procedimiento almacenado
     * @throws SQLException si ocurre una excepsion sql
     * @since 1.0
     */
    public ResultSet usuarioTienePermiso(String idusuario, int codigopermiso) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_obtener_sipermisovalido(?,?)}");
            st.setString(1, idusuario);
            st.setInt(2, codigopermiso);

            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }

    /**
     * Método para obtener una vista de la información de los permisos que tienen
     * los usuarios registrados
     * 
     * @return un resultset con los resultados obtenedos al ejecutar la vista
     * @throws SQLException si ocurre una excepción sql
     * @since 1.0
     */
    public ResultSet listadoUsuarios_Permisos() throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT * FROM simuni_vw_listado_usuarios_parapermisos");

            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }

    public int getCantidadFilas(String query) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reusuario(?)}");
        st.setString(1, query);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
        }
        Conexionmysql.cerrarConexion(con);
        return resp;
    }

    /**
     * Método para obtener la cantidad total de los registros con relación a los
     * usuarios que se encuentran deshabilitados.
     * 
     * @param query sentencia de búsqueda en los registros de la base de datos
     * @return el total de los registros sobre los usuarios deshabilitados
     * @since 1.0
     */
    public int getCantidadFilasInactivas(String query) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reusuarioinactivo(?)}");
        st.setString(1, query);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
        }
        Conexionmysql.cerrarConexion(con);
        return resp;
    }
    
    /**
     * Método el cuál los datos para el reporte obtiene a través de un 
     * procedimiento almacenado, los datos registrados en la base de datos del sistema
     * @return un resultset con los resultados encontrados o recibidos por la llamada
     * de un procedimiento almacenado
     * @throws SQLException si ocurre una excepcion sql-
     * @since 1.0
     */
    public ResultSet ReporteGeneralUsuarios() throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_usuarios()  }");

            resp = cs.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }
}
