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

}
