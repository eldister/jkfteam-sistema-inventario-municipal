package simuni.classes.AD;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import simuni.classes.AR.ManejadorArchivos;
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.Documentos;
import simuni.classes.EN.ProveedorFisico;
import simuni.classes.EN.Servidor;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosProveedores {

    public boolean agregarProveedorFisico(ProveedorFisico to_proveedor) throws Exception {
        try {
            ManejadorArchivos manejadorarchivos = new ManejadorArchivos();
            ArrayList<Documentos> documentos = to_proveedor.getPo_documentos();
            Documentos documento = null;
            if (documentos != null) {
                Iterator<Documentos> iter = documentos.iterator();
                if (iter != null && iter.hasNext()) {
                    documento = iter.next();
                }
            }
            if (documento != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                String sDate = sdf.format(date);
                sDate += ("-" + date.getHours() + "-" + date.getMinutes());
                String ruta = Servidor.SSA.CARPETAPROVEEDORES.toString() + to_proveedor.getPa_cedula() + "\\" + sDate;
                manejadorarchivos.guardarArchivo(ruta, documento.getPa_nombreArchivo(), documento.getStreamarchivo());
                //registrarlo en bd
            } else {
                //registrar imagien "sin foto"
            }
            //registramos proveedores en bd
            Connection con = ConexionMYSQL.obtenerConexion();
            CallableStatement sp_ingresoproveedorfisico
                    = con.prepareCall("{CALL sp_registrarProveedor_fisico(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");

            sp_ingresoproveedorfisico.setString(1, to_proveedor.getPa_cedula());
            sp_ingresoproveedorfisico.setString(2, to_proveedor.getPa_nombre());
            sp_ingresoproveedorfisico.setString(3, to_proveedor.getPa_primerApellido());
            sp_ingresoproveedorfisico.setString(4, to_proveedor.getPa_segundoApellido());
            sp_ingresoproveedorfisico.setString(5, to_proveedor.getPa_numeroCuenta());
            sp_ingresoproveedorfisico.setString(6, to_proveedor.getPa_correoElectronico());
            sp_ingresoproveedorfisico.setString(7, to_proveedor.getPa_sitioWeb());
            sp_ingresoproveedorfisico.setInt(8, to_proveedor.getPn_apartadoPostal());
            sp_ingresoproveedorfisico.setString(9, to_proveedor.getPa_nombreCompania());
            sp_ingresoproveedorfisico.setString(10, to_proveedor.getPa_direccionCompania());
            sp_ingresoproveedorfisico.setString(11, "-");
            sp_ingresoproveedorfisico.setString(12, "-");
            sp_ingresoproveedorfisico.setString(13, "-");
            sp_ingresoproveedorfisico.setString(14, "-");
            sp_ingresoproveedorfisico.setString(15, to_proveedor.getPa_estadoprovedor());
            sp_ingresoproveedorfisico.setDate(16, to_proveedor.getPd_fecharegistro());
            sp_ingresoproveedorfisico.setString(17, to_proveedor.getPa_telefonoFijo());
            sp_ingresoproveedorfisico.setString(18, to_proveedor.getPa_fax());
            sp_ingresoproveedorfisico.setString(19, to_proveedor.getPa_telefonoMovil());
            sp_ingresoproveedorfisico.setString(20, to_proveedor.getPa_telefonoOficina());

            sp_ingresoproveedorfisico.executeUpdate();
            ConexionMYSQL.cerrarConexion(con);
// hacer vara aqui
        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    public boolean modificarProveedorFisico(ProveedorFisico to_proveedor) throws Exception {
        try {
            ManejadorArchivos manejadorarchivos = new ManejadorArchivos();
            ArrayList<Documentos> documentos = to_proveedor.getPo_documentos();
            Documentos documento = null;
            if (documentos != null) {
                Iterator<Documentos> iter = documentos.iterator();
                if (iter != null && iter.hasNext()) {
                    documento = iter.next();
                }
            }
            if (documento != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Date date = new Date();
                String sDate = sdf.format(date);
                sDate += ("-" + date.getHours() + "-" + date.getMinutes());
                String ruta = Servidor.SSA.CARPETAPROVEEDORES.toString() + to_proveedor.getPa_cedula() + "\\" + sDate;
                manejadorarchivos.guardarArchivo(ruta, documento.getPa_nombreArchivo(), documento.getStreamarchivo());
                //registrarlo en bd
            } else {
                //registrar imagien "sin foto"
            }
            //registramos activo en bd

        } catch (Exception ex) {
            throw ex;
        }
        return true;
    }

    public boolean desactivarProveedorFisico(String tn_codigoproveedorfisico) {
        return true;
    }

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos(int desplazamiento, int paginacion) throws SQLException {
        ArrayList<ProveedorFisico> proveedores = new ArrayList<ProveedorFisico>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall(" SELECT SM00IDPR, SM01NOPR, SM02PAPR, SM03SAPR, SM05EMPR, SM03NUCE FROM sm_01proveedores  LIMIT " + desplazamiento + "," + paginacion + ";");
        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            ProveedorFisico proveedorfisico = new ProveedorFisico();
            proveedorfisico.setPa_cedula(rs.getString("SM00IDPR"));
            proveedorfisico.setPa_nombre(rs.getString("SM01NOPR"));
            proveedorfisico.setPa_primerApellido(rs.getString("SM02PAPR"));
            proveedorfisico.setPa_segundoApellido(rs.getString("SM03SAPR"));
            proveedorfisico.setPa_correoElectronico(rs.getString("SM05EMPR"));
            proveedorfisico.setPa_telefonoMovil(rs.getString("SM03NUCE"));
            proveedores.add(proveedorfisico);
        }

        return proveedores;
    }

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos() throws SQLException {
        ArrayList<ProveedorFisico> proveedores = new ArrayList<ProveedorFisico>();
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement pst = con.prepareCall("SELECT * FROM sm_vista_listadoproveedoresseleccionador");
        ResultSet rs = pst.executeQuery();
        //   SM00IDPR,SM01NOPR,SM02PAPR,SM03SAPR,SM05EMPR,SM03NUCE
        while (rs.next()) {
            ProveedorFisico proveedorfisico = new ProveedorFisico();
            proveedorfisico.setPa_cedula(rs.getString("SM00IDPR"));
            proveedorfisico.setPa_correoElectronico(rs.getString("SM05EMPR"));
            proveedorfisico.setPa_nombre(rs.getString("SM01NOPR"));
            proveedorfisico.setPa_primerApellido(rs.getString("SM02PAPR"));
            proveedorfisico.setPa_segundoApellido(rs.getString("SM03SAPR"));
            proveedorfisico.setPa_telefonoMovil(rs.getString("SM03NUCE"));
            proveedores.add(proveedorfisico);
        }
        ConexionMYSQL.cerrarConexion(con);
        return proveedores;
    }

    public ProveedorFisico getProveedorFisico(String tn_codigoproveedorfisico) throws SQLException {
        Connection con = ConexionMYSQL.obtenerConexion();
        CallableStatement sp_obtenerActivo_Articulo
                = con.prepareCall("{CALL sp_obtenerProveedor_fisico(?)}");
        sp_obtenerActivo_Articulo.setString(1, tn_codigoproveedorfisico);
        ResultSet rs = sp_obtenerActivo_Articulo.executeQuery();
         ProveedorFisico proveedorfisico=null;
        if (rs.next()) {
            System.out.println("Entro, si hay");
             proveedorfisico = new ProveedorFisico();
/*
`SM00IDPR`,`SM01NOPR`,`SM02PAPR`,`SM03SAPR`,`SM04NUCU`,`SM05EMPR`,`SM06DIWB`,`SM07COPO`,`SM08NOCO`,`SM09DICO`,
`SM10IDRL`,`SM11NORL`,`SM12PARL`,`SM13SARL`,`SM14ESPR`,`SM15FEPR`,`SM01NUHA`,`SM02NUFA`,`SM03NUCE`,`SM04NUOF`             
             */           
            proveedorfisico.setPa_cedula(rs.getString("SM00IDPR"));
            proveedorfisico.setPa_nombre(rs.getString("SM01NOPR"));
            proveedorfisico.setPa_primerApellido(rs.getString("SM02PAPR"));
            proveedorfisico.setPa_segundoApellido(rs.getString("SM03SAPR"));
            proveedorfisico.setPa_numeroCuenta(rs.getString("SM04NUCU"));
            proveedorfisico.setPa_correoElectronico(rs.getString("SM05EMPR"));
            proveedorfisico.setPa_sitioWeb(rs.getString("SM06DIWB"));
            proveedorfisico.setPn_apartadoPostal(rs.getInt("SM07COPO"));
            proveedorfisico.setPa_nombreCompania(rs.getString("SM07COPO"));
            proveedorfisico.setPa_direccionCompania(rs.getString("SM09DICO"));
            proveedorfisico.setPa_estadoprovedor(rs.getString("SM14ESPR"));
            proveedorfisico.setPd_fecharegistro(rs.getDate("SM15FEPR"));
            proveedorfisico.setPa_telefonoFijo(rs.getString("SM01NUHA"));
            proveedorfisico.setPa_fax(rs.getString("SM02NUFA"));
            proveedorfisico.setPa_telefonoMovil(rs.getString("SM03NUCE"));
            proveedorfisico.setPa_telefonoOficina(rs.getString("SM04NUOF"));
        }
         

        return proveedorfisico;
    }

    public boolean isProveedorExistente(String codigoproveedor) {
        int resp = 0;
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall("SELECT COUNT(*) as CANTIDADREGISTROS  FROM sm_01proveedores WHERE SM00IDPR='" + codigoproveedor + "'");
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

    public int getNumeroProveedoresRegistrados() {
        int resp = 0;
        try {
            Connection con = ConexionMYSQL.obtenerConexion();
            PreparedStatement st = con.prepareCall(" SELECT COUNT(*) AS CANTIDADREGISTROS FROM sm_01proveedores;");
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

    public ArrayList<ProveedorFisico> buscarProveedoresFisicos(String query) {
        try {
            ArrayList<ProveedorFisico> proveedores = new ArrayList<ProveedorFisico>();
            for (int a = 0; a < 3; a++) {
                ProveedorFisico proveedorfisico = new ProveedorFisico();
                proveedorfisico.setPa_banco("nacional");
                proveedorfisico.setPa_cedula("5042303" + a);
                proveedorfisico.setPa_correoElectronico("uncorero@gmail.com");
                proveedorfisico.setPa_direccionCompania("Nicoya nandayure");
                proveedorfisico.setPa_fax("555555555");
                proveedorfisico.setPa_nombre(query);
                proveedorfisico.setPa_nombreCompania("Respuestos raros");
                proveedorfisico.setPa_numeroCuenta("5555" + a);
                proveedorfisico.setPa_primerApellido("Coulon");
                proveedorfisico.setPa_segundoApellido("Ollivier");
                proveedorfisico.setPa_sitioWeb("www.google.com");
                proveedorfisico.setPa_telefonoFijo("8520236" + a);
                proveedorfisico.setPa_telefonoMovil("8411" + a * 2);
                proveedorfisico.setPa_estadoprovedor("0");
                proveedorfisico.setPd_fecharegistro(new java.sql.Date(new java.util.Date().getTime()));
                proveedorfisico.setPn_apartadoPostal(a * 100);
                proveedores.add(proveedorfisico);
            }
            return proveedores;
        } catch (Exception ex) {
            //registrar error
            return null;
        }
    }
}
