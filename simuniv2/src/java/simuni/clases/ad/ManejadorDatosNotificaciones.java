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

    /**
     * Método para el registro o la agregación a la base de datos de un
     * mensaje enviado por un usuario a otro.
     * 
     * @param notificacion un objeto con la información del mensaje enviado
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
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
        //resp = st.getString(7);
        Conexionmysql.cerrarConexion(con);
        return resp;
    }

    /**
     * Método para el registro o la agregación a la base de datos de una
     * notificación.
     * 
     * @param notificacion un objeto con la notificación que será registrada
     * @return un valor booleano true
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public boolean agregarNotificacion(Notificacion notificacion) throws SQLException {
        Connection con = Conexionmysql.obtenerConexion();
        CallableStatement st = con.prepareCall("{CALL simuni_sp_registro_notificacion(?,?,?)}");

        st.setString(1, notificacion.getUsuarioOrigen());
        st.setString(2, notificacion.getDescripcionNotificacion());
        st.registerOutParameter(3, java.sql.Types.VARCHAR);
        st.executeUpdate();
        Conexionmysql.cerrarConexion(con);
        return true;
    }

    /**
     * Método que obtiene un listado de los usuarios registrados en el sistema
     * @return un arraylist de tipo usuario con todo el listado de los usuarios
     * @since 1.0
     */
    public ArrayList<Usuario> listadoUsuario() {
        ManejadorUsuario mdusuario = new ManejadorUsuario();
        return mdusuario.listadoUsuarios();
    }

    /**
     * Método que obtiene las notificaciones correspondientes a un usuario en 
     * particular.
     * 
     * @param idusuario identificación del usuario
     * @return un arraylist de tipo notificación con todas las notificaciones
     * que le corresponden a un usuario.
     * @throws SQLException en caso de que ocurra una excepcion.
     * @since 1.0
     */
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

    /**
     * Método que obtiene los mensajes correspondientes a un usuario en 
     * particular.
     * 
     * @param idusuario identificación del usuario
     * @return un arraylist de tipo notificación con todas los mensajes que le
     * corresponden a un usuario.
     * @throws SQLException en caso de que ocurra una excepcion.
     * @since 1.0
     */
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

    /**
     * Método que obtiene las ultimas notificaciones que ha recibido un usuario
     *  en particular.
     * 
     * @param idusuario identificación del usuario
     * @return un arraylist de tipo notificación con todas las notificaciones
     * que le corresponden a un usuario.
     * @throws SQLException en caso de que ocurra una excepcion.
     * @since 1.0
     */
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

    /**
     * Método que obtiene los ultimos mensajes que ha recibido un usuario en 
     * particular por parte de otro.
     * 
     * @param idusuario identificación del usuario
     * @return un arraylist de tipo notificación con todos los mensajes que le
     * fueron enviados al usuario.
     * @throws SQLException en caso de que ocurra una excepcion.
     * @since 1.0
     */
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
    
    /**
     * Método que obtiene un mensaje en particular
     * @param codigo el codigo del mensaje que se desea obtener
     * @return un objeto tipo noficiacion con el mensaje encontrado
     * @throws SQLException en caso de que ocurra una excepsion sql
     * @since 1.0
     */
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
