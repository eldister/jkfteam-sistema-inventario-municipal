package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Proveedor;

/**
 * Esta clase de acceso a datos de <strong>Proveedor</strong> se encarga de las
 * operaciones directamente con la base de datos, para hacer su ingreso,
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
public class ManejadorDatosProveedor {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Proveedor</strong>.
     *
     * @param proveedor El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarProveedor(Proveedor proveedor) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_proveedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, proveedor.getCedula());
            cs.setString(2, proveedor.getNombre());
            cs.setString(3, proveedor.getPrimerApellido());
            cs.setString(4, proveedor.getSegundoApellido());
            System.out.println(proveedor.getSegundoApellido());
            cs.setString(5, proveedor.getNumeroCuenta());
            cs.setString(6, proveedor.getEmail());
            cs.setString(7, proveedor.getPaginaWeb());
            cs.setString(8, proveedor.getApartadoPostal());
            cs.setString(9, proveedor.getNomEmpresa());
            cs.setString(10, proveedor.getDirEmpresa());
            cs.setString(11, proveedor.getCedulaRepresentanteLegal());
            cs.setString(12, proveedor.getNombreRepresentanteLegal());
            cs.setString(13, proveedor.getPrimerApellidoRepresentanteLegal());
            cs.setString(14, proveedor.getSegundoApellidoRepresentanteLegal());
            cs.setString(15, proveedor.getEstado());
            java.sql.Date date = new java.sql.Date(proveedor.getFechaRegistro().getTime());
            cs.setDate(16, date);
            cs.setString(17, proveedor.getTelFijo());
            cs.setString(18, proveedor.getTelFax());
            cs.setString(19, proveedor.getTelMovil());
            cs.setString(20, proveedor.getTelOfic());
            cs.setString(21, proveedor.getTipoProveedor());
            cs.registerOutParameter(22, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(22);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>Proveedor</strong>.
     *
     * @param proveedor El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarProveedor(Proveedor proveedor) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_proveedor(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");
            cs.setString(1, proveedor.getCedula());
            cs.setString(2, proveedor.getNombre());
            cs.setString(3, proveedor.getPrimerApellido());
            cs.setString(4, proveedor.getSegundoApellido());
            cs.setString(5, proveedor.getNumeroCuenta());
            cs.setString(6, proveedor.getEmail());
            cs.setString(7, proveedor.getPaginaWeb());
            cs.setString(8, proveedor.getApartadoPostal());
            cs.setString(9, proveedor.getNomEmpresa());
            cs.setString(10, proveedor.getDirEmpresa());
            cs.setString(11, proveedor.getCedulaRepresentanteLegal());
            cs.setString(12, proveedor.getNombreRepresentanteLegal());
            cs.setString(13, proveedor.getPrimerApellidoRepresentanteLegal());
            cs.setString(14, proveedor.getSegundoApellidoRepresentanteLegal());
            cs.setString(15, proveedor.getEstado());
            java.sql.Date date = new java.sql.Date(proveedor.getFechaUltimaModificacion().getTime());                  
            cs.setDate(16, date);
            cs.setString(17, proveedor.getTelFijo());
            cs.setString(18, proveedor.getTelFax());
            cs.setString(19, proveedor.getTelMovil());
            cs.setString(20, proveedor.getTelOfic());
            cs.setString(21, proveedor.getTipoProveedor());
            cs.registerOutParameter(22, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(22);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Proveedor</strong> de la base de datos..
     *
     * @param proveedor El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarProveedor(Proveedor proveedor) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_proveedor(?,?)  }");
            cs.setString(1, proveedor.getCedula());
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
    public ResultSet listadoProveedor(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_proveedor  LIMIT " + desplazamiento + "," + paginacion + ";");
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
    public ResultSet listadoProveedor(int desplazamiento, int paginacion, boolean ocultos) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_listado_proveedores(?,?,?)  }");
            cs.setBoolean(1, ocultos);
            cs.setInt(2, desplazamiento);
            cs.setInt(3, paginacion);
            resp = cs.executeQuery();

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
     * @param cedula El código / identificador del registro a buscar.
     * @return Un objeto Proveedor con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public Proveedor getProveedor(String cedula) throws SQLException {
        Proveedor resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_proveedor(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, cedula);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new Proveedor();
            resp.setCedula(rs.getString(1));
            resp.setNombre(rs.getString(2));
            resp.setPrimerApellido(rs.getString(3));
            resp.setSegundoApellido(rs.getString(4));
            resp.setNumeroCuenta(rs.getString(5));
            resp.setEmail(rs.getString(6));
            resp.setPaginaWeb(rs.getString(7));
            resp.setNomEmpresa(rs.getString(8));
            resp.setDirEmpresa(rs.getString(9));
            resp.setRepresentanteLegal(
                    rs.getString(10),
                    rs.getString(11),
                    rs.getString(12),
                    rs.getString(13)
            );
            resp.setEstado(rs.getString(14));
            resp.setFechaRegistro(rs.getDate(15));
            resp.setTelFijo(rs.getString(16));
            resp.setTelFax(rs.getString(17));
            resp.setTelMovil(rs.getString(18));
            resp.setTelOfic(rs.getString(19));
            resp.setTipoProveedor(rs.getString(20));
            resp.setFechaUltimaModificacion(rs.getDate(21));
            resp.setApartadoPostal(rs.getString(22));
            rs.close();
        }
        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos, recibe como parámetro el identificador del registro y con eso hace
     * la operacion.
     *
     * @param cedula El código / identificador del registro a buscar.
     * @return Un objeto Proveedor con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public boolean existeProveedor(String cedula) throws SQLException {
       
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_existe_proveedor(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, cedula);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {       
            rs.close();
            return true;
        }
        return false;
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
    public int getCantidadFilas(String query,boolean ocultos) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reproveedor(?,?)}");
        st.setString(1, query);
        st.setBoolean(2, ocultos);
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
    public ResultSet busquedaProveedor(String query, int desplazamiento, int paginacion,boolean ocultos) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_proveedor(?,?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            st.setBoolean(4, ocultos);
            resp = st.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }
        return resp;

    }

}
