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
import java.sql.Timestamp;
import java.util.ArrayList;
import simuni.clases.ln.ManejadorUsuario;

import simuni.entidades.Notificacion;
import simuni.entidades.Usuario;
import simuni.entidades.bd.Conexionmysql;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosNotificaciones {
//simuni_sp_registro_mensaje

    public String agregarMensaje(Notificacion notificacion) throws SQLException {
        String resp = "";
        Connection con = Conexionmysql.obtenerConexion();
        CallableStatement st = con.prepareCall("{CALL simuni_sp_registro_mensaje(?,?,?,?,?,?,?)}");

        st.setTimestamp(1, new Timestamp(notificacion.getFechaNotificacion().getTime()));
        st.setString(2, notificacion.getUsuarioObjetivo());
        st.setString(3, notificacion.getUsuarioOrigen());
        st.setString(4, notificacion.getEstadoNotificacion());
        st.setString(5, notificacion.getDescripcionNotificacion());
        st.setString(6, notificacion.getTipoNotificacion());
        st.registerOutParameter(7, java.sql.Types.VARCHAR);
        st.execute();
        resp = st.getString(7);
        Conexionmysql.cerrarConexion(con);
        return resp;
    }

    public boolean agregarNotificacion(Notificacion notificacion) throws SQLException {
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL sp_agregarNotificacion(?,?,?,?,?,?)}");

        st.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
        st.setString(2, notificacion.getUsuarioObjetivo());
        st.setString(3, notificacion.getUsuarioOrigen());
        st.setString(4, "Activo");
        st.setString(5, notificacion.getDescripcionNotificacion());
        st.setString(6, "Notificacion");
        st.executeUpdate();
        Conexionmysql.cerrarConexion(con);
        return true;
    }

    public ArrayList<Usuario> listadoUsuario() {
        ManejadorUsuario mdusuario = new ManejadorUsuario();
        return mdusuario.listadoUsuarios();
    }

    public ArrayList<Notificacion> obtenerNotificacionesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_notificacionesusuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt(1));
            notificacion.setFechaNotificacion(rs.getTimestamp(2));
            notificacion.setUsuarioObjetivo(rs.getString(3));
            notificacion.setUsuarioOrigen(rs.getString(4));
            notificacion.setEstadoNotificacion(rs.getString(5));
            notificacion.setDescripcionNotificacion(rs.getString(6));
            notificacion.setTipoNotificacion(rs.getString(7));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        Conexionmysql.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerMensajesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_mensajeusuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt(1));
            notificacion.setFechaNotificacion(rs.getTimestamp(2));
            notificacion.setUsuarioObjetivo(rs.getString(3));
            notificacion.setUsuarioOrigen(rs.getString(4));
            notificacion.setEstadoNotificacion(rs.getString(5));
            notificacion.setDescripcionNotificacion(rs.getString(6));
            notificacion.setTipoNotificacion(rs.getString(7));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        Conexionmysql.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerUltimasNotificacionesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_ultimas_notificacionesusuario(?)}");
        System.out.println(idusuario+"<<<<<<<<<<<<<<");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt(1));
            notificacion.setFechaNotificacion(rs.getTimestamp(2));
            notificacion.setUsuarioOrigen(rs.getString(3));
            notificacion.setEstadoNotificacion(rs.getString(4));
            notificacion.setDescripcionNotificacion(rs.getString(5));
            notificacion.setTipoNotificacion(rs.getString(6));
            notificaciones.add(notificacion);
            System.out.println("Notificacion");
        }
        Conexionmysql.cerrarConexion(con);

        return notificaciones;
    }

    public ArrayList<Notificacion> obtenerUltimosMensajesUsuario(String idusuario) throws SQLException {
        ArrayList<Notificacion> notificaciones = new ArrayList<Notificacion>();
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_ultimos_mensajeusuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Notificacion notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt(1));
            notificacion.setFechaNotificacion(rs.getTimestamp(2));
            notificacion.setUsuarioObjetivo(rs.getString(3));
            notificacion.setUsuarioOrigen(rs.getString(4));
            notificacion.setEstadoNotificacion(rs.getString(5));
            notificacion.setDescripcionNotificacion(rs.getString(6));
            notificacion.setTipoNotificacion(rs.getString(7));
            notificaciones.add(notificacion);
            System.out.println("Notificacion ultima");
        }
        Conexionmysql.cerrarConexion(con);

        return notificaciones;
    }
    
    public Notificacion obtenerMensaje(int codigo) throws SQLException{
        Notificacion notificacion=null;
         Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{CALL simuni_sp_obtener_mensaje(?)}");
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            notificacion = new Notificacion();
            notificacion.setIdNotificacion(rs.getInt(1));
            notificacion.setFechaNotificacion(rs.getTimestamp(2));
            notificacion.setUsuarioObjetivo(rs.getString(3));
            notificacion.setUsuarioOrigen(rs.getString(4));
            notificacion.setEstadoNotificacion(rs.getString(5));
            notificacion.setDescripcionNotificacion(rs.getString(6));
            notificacion.setTipoNotificacion(rs.getString(7));
            System.out.println("Notificacion");
        }
        Conexionmysql.cerrarConexion(con);
        return notificacion;
    }
}
