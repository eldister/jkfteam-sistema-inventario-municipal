package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.entidades.bd.Conexionmysql;
import simuni.entidades.Activo;
import simuni.entidades.ActivoArticulo;
import simuni.entidades.ActivoTransporte;
import simuni.entidades.ImagenActivo;
import simuni.entidades.archivos.ManejadorArchivos;
import simuni.entidades.mantenimientos.TipoLlanta;
import simuni.enums.Recursos;

/**
 * Esta clase de acceso a datos de <strong>Activo</strong> se encarga de las
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
public class ManejadorDatosActivo {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Activo</strong>.
     *
     * @param activoarticulo
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarActivoArticulo(ActivoArticulo activoarticulo) throws SQLException {
        String resp = "";
        int id_articulo = 0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_activoarticulo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//16
            cs.setString(1, activoarticulo.getPlacaActivo());
            cs.setString(2, activoarticulo.getObservaciones());
            cs.setDouble(3, activoarticulo.getPrecio());
            /*   java.sql.Date fecha = new java.sql.Date(activoarticulo.getFechaAdquisicion() == null
             ? null : activoarticulo.getFechaAdquisicion().getTime());*/
            java.sql.Date fecha = activoarticulo.getFechaAdquisicion() != null ? new java.sql.Date(activoarticulo.getFechaAdquisicion().getTime()) : null;

            cs.setDate(4, fecha);
            cs.setInt(5, activoarticulo.getCodigoEstado());
            cs.setInt(6, activoarticulo.getCodigoTipoActivo());
            cs.setInt(7, activoarticulo.getCodigoDepto());
            cs.setInt(8, activoarticulo.getCodigoTipoPago());
            cs.setString(9, activoarticulo.getDenominacion());
            cs.setString(10, activoarticulo.getMarca());
            cs.setString(11, activoarticulo.getModelo());
            cs.setDouble(12, activoarticulo.getPorcentajeDepreciacion());
            cs.setDouble(13, activoarticulo.getPorcentajeRescate());
            /* java.sql.Date fechainicio = new java.sql.Date(activoarticulo.getFechaInicioOperacion() == null
             ? null : activoarticulo.getFechaInicioOperacion().getTime());*/
            java.sql.Date fechainicio = activoarticulo.getFechaAdquisicion() != null ? new java.sql.Date(activoarticulo.getFechaAdquisicion().getTime()) : null;

            cs.setDate(14, fechainicio);
            cs.setString(15, activoarticulo.getCodigoProveedor());
            cs.registerOutParameter(16, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_articulo = rs.getInt(1);
            }
            activoarticulo.setCodigoActivoArticulo(id_articulo);

            resp = cs.getString(16);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            System.out.println("Error  " + ex.getMessage());
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de modificar el registro del
     * <strong>Activo</strong>. en este caso del tipo articulo.
     *
     * @param activoarticulo
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String actualizarActivoArticulo(ActivoArticulo activoarticulo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_activoarticulo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//16
            cs.setString(1, activoarticulo.getPlacaActivo());
            cs.setString(2, activoarticulo.getObservaciones());
            cs.setDouble(3, activoarticulo.getPrecio());
            /*   java.sql.Date fecha = new java.sql.Date(activoarticulo.getFechaAdquisicion() == null
             ? null : activoarticulo.getFechaAdquisicion().getTime());*/
            java.sql.Date fecha = activoarticulo.getFechaAdquisicion() != null ? new java.sql.Date(activoarticulo.getFechaAdquisicion().getTime()) : null;

            cs.setDate(4, fecha);
            cs.setInt(5, activoarticulo.getCodigoEstado());
            cs.setInt(6, activoarticulo.getCodigoTipoActivo());
            cs.setInt(7, activoarticulo.getCodigoDepto());
            cs.setInt(8, activoarticulo.getCodigoTipoPago());
            cs.setString(9, activoarticulo.getDenominacion());
            cs.setString(10, activoarticulo.getMarca());
            cs.setString(11, activoarticulo.getModelo());
            cs.setDouble(12, activoarticulo.getPorcentajeDepreciacion());
            cs.setDouble(13, activoarticulo.getPorcentajeRescate());
            /* java.sql.Date fechainicio = new java.sql.Date(activoarticulo.getFechaInicioOperacion() == null
             ? null : activoarticulo.getFechaInicioOperacion().getTime());*/
            java.sql.Date fechainicio = activoarticulo.getFechaAdquisicion() != null ? new java.sql.Date(activoarticulo.getFechaAdquisicion().getTime()) : null;

            cs.setDate(14, fechainicio);
            cs.setString(15, activoarticulo.getCodigoProveedor());
            cs.setInt(16, activoarticulo.getCodigoActivoArticulo());
            cs.registerOutParameter(17, java.sql.Types.VARCHAR);
            cs.execute();

            resp = cs.getString(17);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            System.out.println("Error  " + ex.getMessage());
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Activo</strong>. del tipo transporte
     *
     * @param activotransporte objeto del tipo ActivoTransporte
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarActivoTransporte(ActivoTransporte activotransporte) throws SQLException {
        String resp = "";
        int id_transporte = 0;
        String codigo_acttransporte = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_activotransporte(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//16
            cs.setString(1, activotransporte.getPlacaActivo());
            cs.setString(2, activotransporte.getObservaciones());
            cs.setDouble(3, activotransporte.getPrecio());
            java.sql.Date fecha = activotransporte.getFechaAdquisicion() != null ? new java.sql.Date(activotransporte.getFechaAdquisicion().getTime()) : null;
            cs.setDate(4, fecha);
            cs.setInt(5, activotransporte.getCodigoEstado());
            cs.setInt(6, activotransporte.getCodigoTipoActivo());
            cs.setInt(7, activotransporte.getCodigoDepto());
            cs.setInt(8, activotransporte.getCodigoTipoPago());
            cs.setString(9, activotransporte.getDenominacion());

            cs.setString(10, activotransporte.getTipoVehiculo());
            cs.setString(11, activotransporte.getPlaca());
            cs.setString(12, activotransporte.getModelo());
            cs.setString(13, activotransporte.getMarca());
            java.sql.Date fechainicio = activotransporte.getFechaInicio() != null ? new java.sql.Date(activotransporte.getFechaInicio().getTime()) : null;

            cs.setDate(14, fechainicio);
            cs.setDouble(15, activotransporte.getPorcentajeRescate());
            cs.setDouble(16, activotransporte.getPorcentajeDepreciacion());
            cs.setString(17, activotransporte.getNumeroChasis());
            cs.setString(18, activotransporte.getNumeroMotor());
            java.sql.Date fechafabricacion = activotransporte.getAnioFabrica() != null ? new java.sql.Date(activotransporte.getAnioFabrica().getTime()) : null;
            cs.setDate(19, fechafabricacion);
            cs.setString(20, activotransporte.getCilindros());
            cs.setInt(21, activotransporte.getCodigoBipoBateria());
            cs.setString(22, activotransporte.getObservacionesTecnicas());
            cs.registerOutParameter(23, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_transporte = rs.getInt(1);
                codigo_acttransporte = rs.getString(2);
            }
            System.out.println("poniendo el nuevo valor  aver  " + id_transporte);
            activotransporte.setCodigoActivoTransporte(id_transporte);
            activotransporte.setTipoVehiculo(codigo_acttransporte);

            resp = cs.getString(23);
            System.out.println("Directo de la bae de datos " + resp);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de actualizar el registro del
     * <strong>Activo</strong>. del tipo transporte
     *
     * @param activotransporte Objeto del tipo Transporte
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String actualizarActivoTransporte(ActivoTransporte activotransporte) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_actualizacion_activotransporte(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//16
            cs.setString(1, activotransporte.getPlacaActivo());
            cs.setString(2, activotransporte.getObservaciones());
            cs.setDouble(3, activotransporte.getPrecio());
            java.sql.Date fecha = activotransporte.getFechaAdquisicion() != null ? new java.sql.Date(activotransporte.getFechaAdquisicion().getTime()) : null;
            cs.setDate(4, fecha);
            cs.setInt(5, activotransporte.getCodigoEstado());
            cs.setInt(6, activotransporte.getCodigoTipoActivo());
            cs.setInt(7, activotransporte.getCodigoDepto());
            cs.setInt(8, activotransporte.getCodigoTipoPago());
            cs.setString(9, activotransporte.getDenominacion());

            cs.setString(10, activotransporte.getTipoVehiculo());
            cs.setString(11, activotransporte.getPlaca());
            cs.setString(12, activotransporte.getModelo());
            cs.setString(13, activotransporte.getMarca());
            java.sql.Date fechainicio = activotransporte.getFechaInicio() != null ? new java.sql.Date(activotransporte.getFechaInicio().getTime()) : null;

            cs.setDate(14, fechainicio);
            cs.setDouble(15, activotransporte.getPorcentajeRescate());
            cs.setDouble(16, activotransporte.getPorcentajeDepreciacion());
            cs.setString(17, activotransporte.getNumeroChasis());
            cs.setString(18, activotransporte.getNumeroMotor());
            java.sql.Date fechafabricacion = activotransporte.getAnioFabrica() != null ? new java.sql.Date(activotransporte.getAnioFabrica().getTime()) : null;
            cs.setDate(19, fechafabricacion);
            cs.setString(20, activotransporte.getCilindros());
            cs.setInt(21, activotransporte.getCodigoBipoBateria());
            cs.setString(22, activotransporte.getObservacionesTecnicas());
            cs.setInt(23, activotransporte.getCodigoActivoTransporte());
            cs.registerOutParameter(24, java.sql.Types.VARCHAR);
            cs.execute();
            System.out.println("valoooor es " + activotransporte.getCodigoActivoTransporte());

            resp = cs.getString(24);
            System.out.println("Directo de la bae de datos " + resp);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar el ingreso / registro de
     * <strong>Documento</strong>.
     *
     * @param imagenactivo
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String registrarImagenActivo(ImagenActivo imagenactivo) throws SQLException {
        String resp = "";
        int id_imagen = 0;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_imagen(?,?,?,?,?,?)}");
            //1 fecha
            java.sql.Date fecha = imagenactivo.getFechaSubida() != null ? new java.sql.Date(imagenactivo.getFechaSubida().getTime()) : null;
            cs.setDate(1, fecha);
            cs.setString(2, imagenactivo.getUrldocumento());
            cs.setString(3, imagenactivo.getCodigoActivo());
            cs.setString(4, imagenactivo.getNombreArchivo());
            cs.setString(5, imagenactivo.getServerArchivo());
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            if (rs.next()) {
                id_imagen = rs.getInt(1);
            }
            imagenactivo.setCodigoImagen(id_imagen);
            resp = cs.getString(6);

            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            System.out.println("Error  " + ex.getMessage());
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar la eliminación de todas las imagenes
     * del activo
     *
     * @param activo El numero de placa del activo a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarImagenActivo(String activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_eliminacion_imagenesactivo(?,?)}");
            //1 fecha
            cs.setString(1, activo);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            System.out.println("Error  " + ex.getMessage());
            throw ex;
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar la eliminación de las llantas de un
     * vehiculo en base de datos
     *
     * @param vehiculo
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarLlantaVehiculo(int vehiculo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_eliminacion_llantasvehiculo(?,?)}");
            //1 fecha
            cs.setInt(1, vehiculo);
            cs.registerOutParameter(2, java.sql.Types.VARCHAR);
            cs.execute();
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            System.out.println("Error  " + ex.getMessage());
            throw ex;
        }
        return resp;
    }

    /**
     * Proceso para poder hacer el registro de las llantas del activo.
     *
     * @param tipollanta el tipo de llanta a asignar
     * @param codigoVehiculo el vehiculo a quien se va a asignar
     * @return el resultado de la operación
     * @throws SQLException En caso de una excepción de sql
     */
    public String registrarLlantaActivo(TipoLlanta tipollanta, int codigoVehiculo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_llantavehiculo(?,?,?,?)}");
            //1 fecha
            cs.setInt(1, codigoVehiculo);
            cs.setInt(2, tipollanta.getIdtipollanta());
            cs.setString(3, tipollanta.getDescripcion());
            cs.registerOutParameter(4, java.sql.Types.VARCHAR);
            cs.execute();
            System.out.println("Registrando en " + codigoVehiculo + " " + tipollanta.getIdtipollanta() + tipollanta.getDescripcion());
            resp = cs.getString(4);

            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            ex.printStackTrace();
            throw ex;
        }
        return resp;
    }

    /**
     * Proceso para realizar el registro de una imagen y asignarla aun activo en
     * específico.
     *
     * @param imagenactivo la imagen a asignar
     * @return el mensaje de resultado de la operación
     * @since 1.0
     */
    public String guardarImagenActivo(ImagenActivo imagenactivo) {
        ManejadorArchivos marchivos = new ManejadorArchivos();
        String resp = "";
        try {
            resp = marchivos.guardarArchivo(imagenactivo.getPathDocumento(), imagenactivo.getNombreArchivo(),
                    imagenactivo.getStreamArchivo()) ? "Guardado " : "No completado";

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Chequea si existe la placa que se pasa por parámetro
     *
     * @param placactivo la placa del activo a buscar
     * @return true si existe, false otherwise
     * @throws SQLException si sucede algún tipo de excepción.
     * @since 1.0
     */
    public boolean existePlacaActivo(String placactivo) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_existe_placaactivo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, placactivo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        }
        return false;
    }

    /**
     * Chequea si existe la placa que se pasa por parámetro
     *
     * @param placavehiculo la placa del vehiculo a buscar
     * @return true si existe, false otherwise
     * @throws SQLException si sucede algún tipo de excepción.
     * @since 1.0
     */
    public boolean existePlacaVehiculo(String placavehiculo) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_existe_placavehiculo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, placavehiculo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        }
        return false;
    }

    /**
     * Esta función hace una búsqueda del consecutivo de un vehículo particular.
     *
     * @param consecutivotipovehiculo el valor a buscar
     * @return true si existe, false otherwise
     * @throws SQLException
     */
    public boolean existeConsecutivoTipoVehiculo(String consecutivotipovehiculo) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_existe_consecutivotipovehiculo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, consecutivotipovehiculo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        }
        return false;
    }

    /**
     * Operación que se encarga de realizar modificación del
     * <strong>Activo</strong>.
     *
     * @param activo El registro a modificar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String modificarActivo(Activo activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_actualizacion_activo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)  }");

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
     * <strong>Activo</strong> de la base de datos..
     *
     * @param activo El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarActivoArticulo(ActivoArticulo activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_activoarticulo(?,?)  }");
            cs.setString(1, activo.getPlacaActivo());
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
     * Operación que se encarga de realizar la descativación del
     * <strong>Activo</strong> de tipo Articulo de la base de datos..
     *
     * @param activo El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String desactivarActivoArticulo(ActivoArticulo activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_desactivacion_activoarticulo(?,?)  }");
            cs.setString(1, activo.getPlacaActivo());
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
     * Operación que se encarga de realizar la eliminación del
     * <strong>Activo</strong> de tipo Tranporte de la base de datos..
     *
     * @param activo El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarActivoTransporte(ActivoTransporte activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_activotransporte(?,?)  }");
            cs.setString(1, activo.getPlacaActivo());
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
     * Operación que se encarga de realizar la desactivación del
     * <strong>Activo</strong> de tipo Tranporte de la base de datos..
     *
     * @param activo El registro a desactivar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String desactivarActivoTranporte(ActivoTransporte activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_desactivacion_activotransporte(?,?)  }");
            cs.setString(1, activo.getPlacaActivo());
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
     * Operación que se encarga de realizar la eliminación del
     * <strong>Activo</strong> de la base de datos..
     *
     * @param activo El registro a eliminar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @throws SQLException Si ocurre una excepcion sql.
     * @since 1.0
     */
    public String eliminarActivo(Activo activo) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_eliminacion_activo(?,?)  }");

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
    public ResultSet listadoActivo(int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT *  FROM  simuni_vw_listado_activo  LIMIT " + desplazamiento + "," + paginacion + ";");
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
    public ResultSet listadoActivo(int desplazamiento, int paginacion, boolean ocultos) throws SQLException {
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_sp_listado_activoes(?,?,?)  }");
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
     * @return Un objeto Activo con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public Activo getActivo(String cedula) throws SQLException {
        Activo resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_activo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, cedula);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {

            rs.close();
        }
        return resp;
    }

    /**
     * Función que obtiene el estado actual de un activo específico.
     *
     * @param placaActivo la placa del actio.
     * @return el codigo de estad
     * @throws SQLException en caso de un error sql
     * @since 1.0
     */
    public int getEstadoActivo(String placaActivo) throws SQLException {
        int resp = -1;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_estadoactivo(?)}");
        st.setString(1, placaActivo);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = rs.getInt(1);
            rs.close();
        }
        return resp;
    }

    /**
     * Funcion que hace obtiene el activo del tipo artículo, recibe como
     * parámetro el número de placa y posteriormente se asignan valores para
     * poder devolver un objeto "lleno" con los valores de la placa solicitada.
     *
     * @param codigoRegistro el número de placa
     * @return el objeto ActivoArticulo o null
     * @throws SQLException si hay un error de sql
     * @since 1.0
     */
    public ActivoArticulo getActivoArticulo(String codigoRegistro) throws SQLException {
        ActivoArticulo resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_activoarticulo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, codigoRegistro);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new ActivoArticulo();
            resp.setPlacaActivo(rs.getString(1));
            resp.setObservaciones(rs.getString(2));
            resp.setPrecio(rs.getDouble(3));
            resp.setFechaAdquisicion(rs.getDate(4));
            resp.setCodigoEstado(rs.getInt(5));
            resp.setCodigoTipoActivo(rs.getInt(6));
            resp.setCodigoDepto(rs.getInt(7));
            resp.setCodigoTipoPago(rs.getInt(8));
            resp.setDenominacion(rs.getString(9));

            resp.setCodigoActivoArticulo(rs.getInt(10));
            resp.setMarca(rs.getString(11));
            resp.setModelo(rs.getString(12));
            resp.setPorcentajeDepreciacion(rs.getDouble(13));
            resp.setPorcentajeRescate(rs.getDouble(14));
            resp.setFechaInicioOperacion(rs.getDate(15));
            resp.setCodigoProveedor(rs.getString(16));
            rs.close();
        }
        return resp;
    }

    /**
     * Funcion que permite obtener las imagenes de un activo a partir de su
     * numero de placa.
     *
     * @param codigoRegistro el número de placa del activo.
     * @return un ArrayList con los objetos ImagenActivo o null
     * @throws SQLException si hay un error de SQL
     * @since 1.0
     */
    public ArrayList<ImagenActivo> getImagenesActivo(String codigoRegistro) throws SQLException {
        ArrayList<ImagenActivo> resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_imagenesactivo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, codigoRegistro);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new ArrayList<ImagenActivo>();
            do {
                ImagenActivo imaux = new ImagenActivo();
                imaux.setCodigoActivo(rs.getString(1));
                imaux.setCodigoImagen(rs.getInt(2));
                imaux.setFechaSubida(rs.getDate(3));
                imaux.setUrldocumento(Recursos.Servers.SERVER_ARCHIVOS + rs.getString(4));
                resp.add(imaux);
            } while (rs.next());
        }
        return resp;
    }

    /**
     * Funcion que hace obtiene el activo del tipo transporte, recibe como
     * parámetro el número de placa y posteriormente se asignan valores para
     * poder devolver un objeto "lleno" con los valores de la placa solicitada.
     *
     * @param codigoRegistro el número de placa
     * @return el objeto ActivoTransporte o null
     * @throws SQLException si hay un error de sql
     * @since 1.0
     */
    public ActivoTransporte getActivoTransporte(String codigoRegistro) throws SQLException {
        ActivoTransporte resp = null;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_obtener_activotransporte(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, codigoRegistro);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            resp = new ActivoTransporte();
            resp.setPlacaActivo(rs.getString(1));
            resp.setObservaciones(rs.getString(2));
            resp.setPrecio(rs.getDouble(3));
            resp.setFechaAdquisicion(rs.getDate(4));
            resp.setCodigoEstado(rs.getInt(5));
            resp.setCodigoTipoActivo(rs.getInt(6));
            resp.setCodigoDepto(rs.getInt(7));
            resp.setCodigoTipoPago(rs.getInt(8));
            resp.setDenominacion(rs.getString(9));

            resp.setCodigoActivoTransporte(rs.getInt(10));
            resp.setTipoVehiculo(rs.getString(11));
            resp.setPlaca(rs.getString(12));
            resp.setModelo(rs.getString(13));
            resp.setMarca(rs.getString(14));
            resp.setFechaInicio(rs.getDate(15));
            resp.setPorcentajeRescate(rs.getDouble(16));
            resp.setPorcentajeDepreciacion(rs.getDouble(17));
            resp.setNumeroChasis(rs.getString(18));
            resp.setNumeroMotor(rs.getString(19));
            resp.setAnioFabrica(rs.getDate(20));
            resp.setCilindros(rs.getString(21));
            resp.setCodigoBipoBateria(rs.getInt(22));
            resp.setObservacionesTecnicas(rs.getString(23));

            rs.close();
            st = con.prepareCall("{ call simuni_sp_obtener_llantasactivotransporte(?)}");
            st.setString(1, codigoRegistro);
            rs = st.executeQuery();
            while (rs.next()) {
                TipoLlanta tllantaux = new TipoLlanta();
                tllantaux.setIdtipollanta(rs.getInt(1));
                tllantaux.setDescripcion(rs.getString(2));
                resp.agregarTipoLlanta(tllantaux);
                System.out.println("ENTREEEEEEEEEEEEEEEE!!");
            }
        }
        rs.close();

        return resp;
    }

    /**
     * Funcion que se encarga de traer un registro específico de la base de
     * datos, recibe como parámetro el identificador del registro y con eso hace
     * la operacion.
     *
     * @param cedula El código / identificador del registro a buscar.
     * @return Un objeto Activo con los valores correspondientes
     * @throws SQLException si ocurre una excepción de SQL
     * @since 1.0
     */
    public boolean existeActivo(String cedula) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_existe_activo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, cedula);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        }
        return false;
    }

    /**
     * funcion que permite determinar si la placa que se pasa por parámetro es
     * del tipo Artículo o no.
     *
     * @param codigoRegistro la placa a buscar
     * @return true si es activo Articulo false otherwise
     * @throws SQLException si hay un error de SQL
     * @since 1.0
     */
    public boolean isRegistroArticulo(String codigoRegistro) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_chequear_esarticulo(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, codigoRegistro);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            rs.close();
            return true;
        }
        return false;
    }

    /**
     * funcion que permite determinar si la placa que se pasa por parámetro es
     * del tipo Transporte o no.
     *
     * @param codigoRegistro la placa a buscar
     * @return true si es activo Transporte false otherwise
     * @throws SQLException si hay un error de SQL
     * @since 1.0
     */
    public boolean isRegistroTransporte(String codigoRegistro) throws SQLException {

        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall("{ call simuni_sp_chequear_estransporte(?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        st.setString(1, codigoRegistro);
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
    public int getCantidadFilas(String query, boolean ocultos) throws SQLException {
        int resp = 0;
        Connection con = Conexionmysql.obtenerConexion();
        PreparedStatement st = con.prepareCall(" {call simuni_sp_obtener_cantidad_reactivo(?,?)}");
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
    public ResultSet busquedaActivo(String query, int desplazamiento, int paginacion, boolean ocultos) throws SQLException {
        ResultSet resp = null;
        try {
            Connection con = Conexionmysql.obtenerConexion();
            PreparedStatement st = con.prepareCall("{call simuni_sp_busqueda_activo(?,?,?,?)}");
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

    /**
     * Hacer un reporte general de activos, se obtienen todos menos los que
     * esten inactivos.
     *
     * @return un ResultSet que maneje la respuesta desde el Servidor MYSQL
     * @throws SQLException si hay una excepcion SQL
     * @since 1.0
     */
    public ResultSet ReporteGeneralActivos() throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_activos()  }");

            resp = cs.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }

    /**
     * Hacer un reporte general de activos con rango de fecha, se obtienen todos
     * menos los que esten inactivos.
     *
     * @param fein la fecha de inicio
     * @param fin la fecha final
     * @return un ResultSet que maneje la respuesta desde el Servidor MYSQL
     * @throws SQLException si hay un error de SQL
     * @since 1.0
     */
    public ResultSet ReporteGeneralActivos(java.sql.Date fein, java.sql.Date fin) throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_activos_xfechaingreso(?,?)  }");
            cs.setDate(1, fein);
            cs.setDate(2, fin);
            resp = cs.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }

    /**
     * Hacer un reporte general de activos pro departamento, se obtienen todos
     * menos los que esten inactivos.
     *
     * @param departamento código del departamento a obtener sus activos.
     * @return un ResultSet que maneje la respuesta desde el Servidor MYSQL
     * @throws SQLException si hay un error de SQL
     * @since 1.0
     */
    public ResultSet ReporteGeneralActivos(int departamento) throws SQLException {//solo los qeu no esten inactivos
        ResultSet resp = null;

        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{ call simuni_rprt_general_activos()  }");

            resp = cs.executeQuery();

        } catch (SQLException ex) {

            throw ex;
        }

        return resp;

    }

}
