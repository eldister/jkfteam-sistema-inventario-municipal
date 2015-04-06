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
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Prestamo;
/**
 *  Esta clase de acceso a datos de <strong>Prestamo</strong> se encarga de las
 * operaciones directamente con la base de datos, para hacer su ingreso,
 * modificación, eliminacino del mismo. Entre las operaciones comunes que se
 * solicitan estan agregar, modificar, eliminar, hacer un query de busqueda y
 * tambien hacer el listado por defecto que hay de los datos ingresados. Esta
 * clase busca que la separación entre código y base de datos directamente sea
 * alta, y no este tanto código embebido en el sistema.
 *
 * @author Keylin
 */
public class ManejadorDatosPrestamo {
        /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Prestamo</strong>.
     *
     * @param prestamo El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarPrestamo(Prestamo prestamo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_prestamo(?,?,?,?,?,?,?)}");
           java.sql.Date date = new java.sql.Date(prestamo.getFechaRegistro().getTime());
           cs.setDate(1, date);
           java.sql.Date date2 = new java.sql.Date(prestamo.getFechaDevolucion().getTime());
           cs.setDate(2, date2);
            cs.setString(3, prestamo.getObservaciones());
            cs.setString(4, prestamo.getEstado());
            cs.setString(5, prestamo.getDepartamentoSolicitante());
            cs.setString(6, prestamo.getIdActivo());
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

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>Prestamo</strong>.
     *
     * @param prestamo El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarPrestamo(Prestamo prestamo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
             CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_prestamo(?,?,?,?,?)}");
             cs.setInt(1, prestamo.getIdPrestamo());
    java.sql.Date date2 = new java.sql.Date(prestamo.getFechaDevolucion().getTime());
    cs.setDate(2, date2);
            cs.setString(3, prestamo.getObservaciones());
            cs.setString(4, prestamo.getEstado());
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(5);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Prestamo</strong> de la base de datos..
     *
     * @param prestamo El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarPrestamo(Prestamo prestamo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_prestamo(?,?)  }");
            cs.setInt(1, prestamo.getIdPrestamo());// campo a cambiar
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
    public ResultSet listadoPrestamo(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_prestamo  LIMIT " + desplazamiento + "," + paginacion + ";");
            resp = st.executeQuery();

        } catch (SQLException ex) {

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
     * @param ocultos Mostrar / Ocultar los registros que estan inactivos
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @throws SQLException Si ocurre un error SQL
     * @since 1.0
     */
    public ResultSet listadoPrestamo() throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_prestamo;");
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
    public Prestamo getPrestamo(int codigo) throws SQLException {
        Prestamo resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_prestamo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new Prestamo();
            resp.setIdPrestamo(rs.getInt(1));
            resp.setFechaRegistro(rs.getDate(2));
            resp.setFechaDevolucion(rs.getDate(3));
            resp.setDepartamentoSolicitante(rs.getString(6));
            resp.setEstado(rs.getString(5));
            resp.setIdActivo(rs.getString(7));
            resp.setObservaciones(rs.getString(4));     

            rs.close();
        }
        return resp;
    }

    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro
     *
     * @param query La cadena con la busqueda a evaluar.
     * @param ocultos
     * @return Un entero con la cantidad de registros.
     * @throws SQLException En caso de que lance una excepción de SQL.
     * @since 1.0
     */
    public int getCantidadFilas(String query) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_represtamo(?)}");
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
     * @param ocultos
     * @return Un ResultSet con los resultados de la busqueda
     * @throws SQLException Si ocurre una excepcion de SQL.
     * @since 1.0
     */
    public ResultSet busquedaPrestamo(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_prestamo(?,?,?)}");
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
