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
            CallableStatement cs = con.prepareCall("{ call simuni_sp_registrar_usuario(?,?,?,?,?,?,?,?)  }");
            cs.setString(1, usuario.getNombreusuario());
            cs.setString(2, usuario.getContrasena());
            cs.setInt(3, usuario.getTipousuario());
            cs.setInt(4, usuario.getAreatrabajo());
            cs.setString(5, usuario.getNombre());
            cs.setString(6, usuario.getApellido1());
            cs.setString(7, usuario.getApellido2());
            cs.registerOutParameter(8, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(8);
            Conexionmysql.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return resp;
    }
    
    public Usuario obtenerUsuario(String usuario) throws SQLException {

        Usuario usuarioresp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtenerusuario(?)}");
        st.setString(1, usuario);
      
        ResultSet rs = st.executeQuery();

    
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
    
}
