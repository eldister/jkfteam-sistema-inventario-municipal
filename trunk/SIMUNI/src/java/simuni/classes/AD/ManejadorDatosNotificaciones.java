/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.AD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.Notificacion;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosNotificaciones {

    public boolean agregarNotificacion(Notificacion notificacion) throws SQLException {
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_agregarNotificacion(?,?,?,?,?,?)}");

        st.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
        st.setInt(2, notificacion.getUsuarioObjetivo());
        st.setString(3, notificacion.getUsuarioOrigen());
        st.setString(4, "Activa");
        st.setString(5, notificacion.getDescripcionNotificacion());
        st.setString(6, "Notificacion");
        st.executeUpdate();
        ConexionMYSQL.cerrarConexion(con);
        return true;
    }

    public ArrayList<Notificacion> obtenerNotificacionesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_obtenerNotificacionesUsuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt("SM00IDNO"));
            notificacion.setFechaNotificacion(rs.getDate("SM01FENO"));
            notificacion.setUsuarioOrigen(rs.getString("SM03USOR"));
            notificacion.setEstadoNotificacion(rs.getString("SM04ESNO"));
            notificacion.setDescripcionNotificacion(rs.getString("SM05DENO"));
            notificacion.setTipoNotificacion(rs.getString("SM06TINO"));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        ConexionMYSQL.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerMensajesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_obtenerMensajesUsuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt("SM00IDNO"));
            notificacion.setFechaNotificacion(rs.getDate("SM01FENO"));
            notificacion.setUsuarioOrigen(rs.getString("SM03USOR"));
            notificacion.setEstadoNotificacion(rs.getString("SM04ESNO"));
            notificacion.setDescripcionNotificacion(rs.getString("SM05DENO"));
            notificacion.setTipoNotificacion(rs.getString("SM06TINO"));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        ConexionMYSQL.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerUltimasNotificacionesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_obtenerUltimasNotificacionesUsuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt("SM00IDNO"));
            notificacion.setFechaNotificacion(rs.getDate("SM01FENO"));
            notificacion.setUsuarioOrigen(rs.getString("SM03USOR"));
            notificacion.setEstadoNotificacion(rs.getString("SM04ESNO"));
            notificacion.setDescripcionNotificacion(rs.getString("SM05DENO"));
            notificacion.setTipoNotificacion(rs.getString("SM06TINO"));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        ConexionMYSQL.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerUltimosMensajesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_obtenerUltimosMensajesUsuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt("SM00IDNO"));
            notificacion.setFechaNotificacion(rs.getDate("SM01FENO"));
            notificacion.setUsuarioOrigen(rs.getString("SM03USOR"));
            notificacion.setEstadoNotificacion(rs.getString("SM04ESNO"));
            notificacion.setDescripcionNotificacion(rs.getString("SM05DENO"));
            notificacion.setTipoNotificacion(rs.getString("SM06TINO"));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        ConexionMYSQL.cerrarConexion(con);

        return notificaciones;
    }
}
