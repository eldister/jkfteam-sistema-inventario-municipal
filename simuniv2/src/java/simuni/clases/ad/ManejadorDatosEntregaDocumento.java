package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.Documento;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.EntregaDocumento;
import simuni.entidades.archivos.ManejadorArchivos;

/**
 * Esta clase de acceso a datos de <strong>EntregaDocumento</strong> se encarga
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
public class ManejadorDatosEntregaDocumento {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>EntregaDocumento</strong>.
     *
     * @param entregadocumento El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarEntregaDocumento(EntregaDocumento entregadocumento) throws SQLException {
        String resp = "";
        int id_entrega = 0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_entregadocumento(?,?,?,?,?)}");
            java.sql.Date date = new java.sql.Date(entregadocumento.getFecha().getTime());
            cs.setDate(1, date);
            cs.setString(2, entregadocumento.getCedulaproveedor());
            cs.setString(3, entregadocumento.getObseravaciones());
            cs.setString(4, entregadocumento.getEstadoentrega());
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_entrega = rs.getInt(1);
            }
            entregadocumento.setCodigo(id_entrega);
            resp = cs.getString(5);

            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>EntregaDocumento</strong>.
     *
     * @param entregadocumento El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarEntregaDocumento(EntregaDocumento entregadocumento) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_entregadocumento(?,?,?,?)  }");
            cs.setInt(1, entregadocumento.getCodigo());
            cs.setString(2, entregadocumento.getObseravaciones());
            cs.setString(3, entregadocumento.getEstadoentrega());
            System.out.println("En datos tenemos " + entregadocumento.getEstadoentrega());
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();
            resp = cs.getString(4);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }

        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>EntregaDocumento</strong> de la base de datos..
     *
     * @param entregadocumento El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarEntregaDocumento(EntregaDocumento entregadocumento) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_entregadocumento(?,?)  }");
            cs.setInt(1, entregadocumento.getCodigo());
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
    public ResultSet listadoEntregaDocumento(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_entregadocumento  LIMIT " + desplazamiento + "," + paginacion + ";");
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
     * @return Un objeto EntregaDocumento con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public EntregaDocumento getEntregaDocumento(int codigo) throws SQLException {
        EntregaDocumento resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_entregadocumento(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setInt(1, codigo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new EntregaDocumento();
            resp.setCodigo(rs.getInt(1));
            resp.setFecha(rs.getDate(2));
            resp.setCedulaproveedor(rs.getString(3));
            resp.setObseravaciones(rs.getString(4));
            resp.setEstadoentrega(rs.getString(5));
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
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reentregadocumento(?)}");
        st.setString(1, query);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
        }
        Conexionmysql.cerrarConexion(con);
        return resp;
    }

    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro
     *
     * @param query La cadena con la busqueda a evaluar.
     * @param proveedor
     * @return Un entero con la cantidad de registros.
     * @throws SQLException En caso de que lance una excepción de SQL.
     * @since 1.0
     */
    public int getCantidadFilas(String query, String proveedor) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reentregadocumentoproveeedor(?,?)}");
        st.setString(1, query);
        st.setString(2, proveedor);
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
     * @param cedulaproveedor
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @throws SQLException Si ocurre una excepcion de SQL.
     * @since 1.0
     */
    public ResultSet busquedaEntregaDocumento(String query, String cedulaproveedor, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_entregadocumento(?,?,?,?)}");
            st.setString(1, query);
            st.setString(2, cedulaproveedor);
            st.setInt(3, desplazamiento);
            st.setInt(4, paginacion);
            resp = st.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return resp;

    }

    /**
     * Realiza una busqueda en la base de datos.
     *
     * @param codigoentrega
     * @return Un ResultSet con los resultados de la busqueda
     * @throws SQLException Si ocurre una excepcion de SQL.
     * @since 1.0
     */
    public ResultSet getDocumentosEntrega(int codigoentrega) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_obtener_documentosentrega(?)}");
            st.setInt(1, codigoentrega);
            resp = st.executeQuery();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw ex;
        }
        return resp;

    }

    public String guardarDocumento(Documento documento) {
        ManejadorArchivos marchivos = new ManejadorArchivos();
        String resp = "";
        try {
            resp = marchivos.guardarArchivo(documento.getPathdocumento(), documento.getNombredocumento(),
                    documento.getStreamarchivo()) ? "Guardado " : "No completado";

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar el ingreso / registro de
     * <strong>Documento</strong>.
     *
     * @param documento El nuevo registro a ingresar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarDocumento(Documento documento) throws SQLException {
        String resp = "";
        int id_doc = 0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_documento(?,?,?,?,?)}");
            cs.setString(1, documento.getNombredocumento());
            cs.setString(2, documento.getUrldocumento());
            cs.setInt(3, documento.getCodigoentregadocumento());
            cs.setString(4, documento.getServerdocumento());
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_doc = rs.getInt(1);
            }
            documento.setCodigodocumento(id_doc);
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
     * <strong>EntregaDocumento</strong> de la base de datos..
     *
     * @param documento
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarDocumento(Documento documento) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_documento(?,?,?)  }");
            cs.setInt(1, documento.getCodigodocumento());
            cs.setInt(2, documento.getCodigoentregadocumento());
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
     * <strong>EntregaDocumento</strong> de la base de datos..
     *
     * @param entregadocumento
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarDocumento(EntregaDocumento entregadocumento) throws SQLException {
        String resp = " ";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_documentosentrega(?,?)  }");
            cs.setInt(1, entregadocumento.getCodigo());
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
}
