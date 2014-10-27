package simuni.classes.AD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import simuni.classes.AR.ManejadorArchivos;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.Departamento;
import simuni.classes.EN.Servidor;
import simuni.classes.EN.imagenActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosActivos {

    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) throws Exception {
        try {
            ManejadorArchivos manejadorarchivos = new ManejadorArchivos();
            ArrayList<imagenActivo> imagenes = to_articulo.getPo_imagenActivo();
            imagenActivo imagen = null;
            String nombreimagen = "";
            if (imagenes != null) {
                Iterator<imagenActivo> iter = imagenes.iterator();

                if (iter.hasNext()) {
                    imagen = iter.next();
                };
            }
            String sDate = "";
            if (imagen != null) {
                nombreimagen = imagen.getPa_nombreArchivo();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                sDate = sdf.format(date);
                sDate += ("-" + date.getHours() + "-" + date.getMinutes());
                String ruta = Servidor.SSA.CARPETARAIZARCHIVOSACTIVOS.toString() + to_articulo.getPa_identificadorActivo() + "\\" + sDate;
                manejadorarchivos.guardarArchivo(ruta, imagen.getPa_nombreArchivo(), imagen.getStreamarchivo());
                //registrarlo en bd
            } else {
                //registrar imagien "sin foto"
            }
            //registramos activo en bd
            Connection con = ConexionMYSQL.obtenerConexion();
            CallableStatement sp_ingresoarticulo
                    = con.prepareCall("{CALL sp_registrarActivo_Articulo(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            java.util.Date fechainiciooperacion = to_articulo.getPd_puestaOperacion();
            System.out.println("añooooo  " + fechainiciooperacion.getYear());
            System.out.println("mes  " + fechainiciooperacion.getMonth());

            System.out.println("dia  ");

            sp_ingresoarticulo.setString(1, to_articulo.getPa_identificadorActivo());
            sp_ingresoarticulo.setString(2, to_articulo.getPa_Observaciones());
            sp_ingresoarticulo.setDouble(3, to_articulo.getPd_precioCompra());
            sp_ingresoarticulo.setDate(4, new java.sql.Date(to_articulo.getPd_fechaCompra().getTime()));
            sp_ingresoarticulo.setString(5, to_articulo.getPa_Estado());
            sp_ingresoarticulo.setString(6, to_articulo.getPa_Descripcion());
            sp_ingresoarticulo.setInt(7, to_articulo.getPa_tipoActivo());
            sp_ingresoarticulo.setInt(8, to_articulo.getPo_depto().getPn_codigo());
            sp_ingresoarticulo.setInt(9, to_articulo.getPa_tipoPago());
            sp_ingresoarticulo.setString(10, to_articulo.getPa_marca());
            sp_ingresoarticulo.setString(11, to_articulo.getPa_modelo());
            sp_ingresoarticulo.setDouble(12, to_articulo.getPb_porcentajeDepreciacion());
            sp_ingresoarticulo.setDouble(13, to_articulo.getPb_porcentajeRescate());
            sp_ingresoarticulo.setDate(14, new java.sql.Date(fechainiciooperacion.getYear(), fechainiciooperacion.getMonth(), fechainiciooperacion.getDate()));
            System.out.println(to_articulo.getPd_puestaOperacion());
            sp_ingresoarticulo.setString(15, to_articulo.getPa_codigoProveedor());
            sp_ingresoarticulo.setDate(16, new java.sql.Date(new Date().getTime()));
            sp_ingresoarticulo.setString(17, Servidor.SSI.ARCHIVOSACTIVOSCONTEXT + to_articulo.getPa_identificadorActivo() + "/" + sDate);
            sp_ingresoarticulo.setString(18, nombreimagen);
            sp_ingresoarticulo.executeUpdate();
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) throws Exception {
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            ManejadorArchivos manejadorarchivos = new ManejadorArchivos();
            ArrayList<imagenActivo> imagenes = to_articulo.getPo_imagenActivo();
            imagenActivo imagen = null;
            if (imagenes != null) {
                Iterator<imagenActivo> iter = imagenes.iterator();
                if (iter.hasNext()) {
                    imagen = iter.next();
                    System.out.println("Siempre ubo imagen");
                }
            }
            if (imagen != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String sDate = sdf.format(date);
                sDate += ("-" + date.getHours() + "-" + date.getMinutes());
                String ruta = Servidor.SSA.CARPETARAIZARCHIVOSACTIVOS.toString() + to_articulo.getPa_identificadorActivo() + "\\" + sDate;
                manejadorarchivos.guardarArchivo(ruta, imagen.getPa_nombreArchivo(), imagen.getStreamarchivo());
                //registrarlo en bd
                // Servidor.SSI.ARCHIVOSACTIVOSCONTEXT.toString()+to_articulo.getPa_identificadorActivo()+sDate;
                CallableStatement sp_modificacionimagenarticulo
                        = con.prepareCall("{CALL sp_modificarimagenactivo(?,?,?)}");
                sp_modificacionimagenarticulo.setString(1, Servidor.SSI.ARCHIVOSACTIVOSCONTEXT + to_articulo.getPa_identificadorActivo() + "/" + sDate);
                sp_modificacionimagenarticulo.setString(2, imagen.getPa_nombreArchivo());
                sp_modificacionimagenarticulo.setString(3, to_articulo.getPa_identificadorActivo());
                sp_modificacionimagenarticulo.executeUpdate();

            }
            java.util.Date fechainiciooperacion = to_articulo.getPd_puestaOperacion();
            java.util.Date fechacompra = to_articulo.getPd_puestaOperacion();
            System.out.println("Fecha inicio operacion " + fechainiciooperacion.toGMTString());
            System.out.println(fechainiciooperacion.getYear());

            //modificacion de los activos en la bd 
            CallableStatement sp_modificacionarticulo
                    = con.prepareCall("{CALL sp_actualizarActivo_Articulo(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            sp_modificacionarticulo.setString(1, to_articulo.getPa_identificadorActivo());
            sp_modificacionarticulo.setString(2, to_articulo.getPa_Observaciones());
            sp_modificacionarticulo.setDouble(3, to_articulo.getPd_precioCompra());
            sp_modificacionarticulo.setDate(4, new java.sql.Date(fechacompra.getYear(), fechacompra.getMonth(), fechacompra.getDate()));
            sp_modificacionarticulo.setString(5, to_articulo.getPa_Estado());
            sp_modificacionarticulo.setString(6, to_articulo.getPa_Descripcion());
            sp_modificacionarticulo.setString(7, to_articulo.getPa_marca());
            sp_modificacionarticulo.setString(8, to_articulo.getPa_modelo());
            sp_modificacionarticulo.setDouble(9, to_articulo.getPb_porcentajeDepreciacion());
            sp_modificacionarticulo.setDouble(10, to_articulo.getPb_porcentajeRescate());
            sp_modificacionarticulo.setDate(11, new java.sql.Date(fechainiciooperacion.getYear(), fechainiciooperacion.getMonth(), fechainiciooperacion.getDate()));
            sp_modificacionarticulo.setInt(12, to_articulo.getPa_tipoActivo());
            System.out.println("El tipo de activo es "+to_articulo.getPa_tipoActivo());
            sp_modificacionarticulo.setInt(13, to_articulo.getPo_depto().getPn_codigo());
            sp_modificacionarticulo.setInt(14, to_articulo.getPa_tipoPago());

            sp_modificacionarticulo.executeUpdate();
            ConexionMYSQL.cerrarConexion(con);

        } catch (Exception ex) {
            throw ex;
        }

        return true;
    }

    public boolean desactivarActivoArticulo(String ta_codigoactivo) throws Exception {
        try {

            Connection con = ConexionMYSQL.obtenerConexion();
            CallableStatement sp_desactivarActivoArticulo
                    = con.prepareCall("{CALL sp_eliminarActivo_Articulo(?)}");
            sp_desactivarActivoArticulo.setString(1, ta_codigoactivo);
            sp_desactivarActivoArticulo.executeUpdate();
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    public ArrayList<Activos_Articulos> getListaArticulos(int desplazamiento, int paginacion) throws Exception {
        ArrayList<Activos_Articulos> to_articulo = new ArrayList<Activos_Articulos>();
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT SM00IDAC,SM00IDTA,SM01MAAR,SM02MDAR,SM05FEIO,SM04DEAC  FROM  sm_vista_listadoactivoarticulos  LIMIT " + desplazamiento + "," + paginacion + ";");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Activos_Articulos activoarticulo = new Activos_Articulos();
                activoarticulo.setPa_identificadorActivo(rs.getString("SM00IDAC"));
                activoarticulo.setPa_tipoActivo(rs.getInt("SM00IDTA"));
                activoarticulo.setPa_marca(rs.getString("SM01MAAR"));
                activoarticulo.setPa_modelo(rs.getString("SM02MDAR"));
                activoarticulo.setPd_puestaOperacion(rs.getDate("SM05FEIO"));
                activoarticulo.setPa_Descripcion(rs.getString("SM04DEAC"));
                to_articulo.add(activoarticulo);
                System.out.println("ENTREE Y SIRVOO");
            }
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return to_articulo;
    }

    public Activos_Articulos getActivoArticulo(String ta_codigoactivo) throws Exception {
        Activos_Articulos articuloejemplo = new Activos_Articulos();

        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            CallableStatement sp_obtenerActivo_Articulo
                    = con.prepareCall("{CALL sp_obtenerActivo_Articulo(?)}");
            sp_obtenerActivo_Articulo.setString(1, ta_codigoactivo);
            ResultSet rs = sp_obtenerActivo_Articulo.executeQuery();
            if (rs.next()) {
                /*
                 sm_08activo.SM00IDAC,SM01OBAC,SM02MOCO,SM03FECO,SM05ESAC,SM04DEAC,SM00IDTA,
                 SM00IDDP,SM00IDTP,SM00IDAR,SM01MAAR,SM02MDAR,SM03NUDR,SM04NURS,SM05FEIO,SM00IDPR                
                 */
                articuloejemplo.setPa_identificadorActivo(rs.getString("SM00IDAC"));
                articuloejemplo.setPa_Observaciones(rs.getString("SM01OBAC"));
                articuloejemplo.setPd_precioCompra(rs.getDouble("SM02MOCO"));
                articuloejemplo.setPd_fechaCompra(rs.getDate("SM03FECO"));
                articuloejemplo.setPa_Estado(rs.getString("SM05ESAC"));

                articuloejemplo.setPa_Descripcion(rs.getString("SM04DEAC"));
                articuloejemplo.setPa_tipoActivo(rs.getInt("SM00IDTA"));
                Departamento d = new Departamento();
                d.setPn_codigo(rs.getInt("SM00IDDP"));
                articuloejemplo.setPo_depto(d);
                articuloejemplo.setPa_tipoPago(rs.getInt("SM00IDTP"));
                System.out.println("Nuevamente veamos que sale "+articuloejemplo.getPa_tipoPago() );
                articuloejemplo.setPa_marca(rs.getString("SM01MAAR"));
                articuloejemplo.setPa_modelo(rs.getString("SM02MDAR"));
                articuloejemplo.setPb_porcentajeDepreciacion(rs.getDouble("SM03NUDR"));
                articuloejemplo.setPb_porcentajeRescate(rs.getDouble("SM04NURS"));
                articuloejemplo.setPa_codigoProveedor(rs.getString("SM00IDPR"));
                articuloejemplo.setPd_puestaOperacion(rs.getDate("SM05FEIO"));
//,SM02DIUR,SM04NOAR
                ArrayList<imagenActivo> imgs = new ArrayList<imagenActivo>();
                imagenActivo a = new imagenActivo();
                a.setPa_url(rs.getString("SM02DIUR"));
                a.setPa_nombreArchivo(rs.getString("SM04NOAR"));
                imgs.add(a);
                articuloejemplo.setPo_imagenActivo(imgs);
                //System.out.print(articuloejemplo.getPo_imagenActivo().size());

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        return articuloejemplo;
    }

    public ArrayList<imagenActivo> getListaImagenesActivo(String tn_codigoactivo) throws Exception { // cambiar tipo a string 
        ArrayList<imagenActivo> imagenes=new ArrayList<imagenActivo>();
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT SM02DIUR,SM04NOAR FROM sm_07imagenes WHERE SM00IDAC='"+tn_codigoactivo+"'");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
              imagenActivo imagen=new imagenActivo();
              imagen.setPa_nombreArchivo(rs.getString("SM04NOAR"));
              imagen.setPa_url(rs.getString("SM02DIUR"));
              imagenes.add(imagen);
            }
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            throw  ex;
        }        
        return imagenes;
    }

    public boolean isActivoExistente(String idactivo) {
        int resp = 0;
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT COUNT(*) as CANTIDADREGISTROS FROM sm_08activo WHERE SM00IDAC='" + idactivo + "'");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                resp = rs.getInt("CANTIDADREGISTROS");
            }
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }

        return resp > 0;
    }

    public int getNumeroActivosRegistrados() {
        return 0;
    }


    public ArrayList<Activos_Articulos> buscarActivosArticulos(String query,int desplazamiento, int paginacion) throws Exception {
        ArrayList<Activos_Articulos> to_articulo = new ArrayList<Activos_Articulos>();
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall("{CALL sp_busquedaactivoarticulos(?,?,?)}");
            st.setString(1, query);
            st.setInt(2, desplazamiento);
            st.setInt(3, paginacion);
            System.out.println("El query es "+query);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                            System.out.println("Resultado El query es "+query);

                Activos_Articulos activoarticulo = new Activos_Articulos();
                activoarticulo.setPa_identificadorActivo(rs.getString("SM00IDAC"));
                activoarticulo.setPa_tipoActivo(rs.getInt("SM00IDTA"));
                activoarticulo.setPa_marca(rs.getString("SM01MAAR"));
                activoarticulo.setPa_modelo(rs.getString("SM02MDAR"));
                activoarticulo.setPd_puestaOperacion(rs.getDate("SM05FEIO"));
                activoarticulo.setPa_Descripcion(rs.getString("SM04DEAC"));
                to_articulo.add(activoarticulo);
                System.out.println("Resultados de la busqueda");
            }
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            throw ex;
        }
        return to_articulo;
    }
    public int getCantidadRegistrosActivosArticulos() {
        int resp = 0;
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall(" SELECT COUNT(*) AS CANTIDADREGISTROS FROM sm_vista_listadoactivoarticulos;");
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                resp = rs.getInt("CANTIDADREGISTROS");
            }
            ConexionMYSQL.cerrarConexion(con);
        } catch (Exception ex) {
            return 0;
        }
        return resp;
    }
}
