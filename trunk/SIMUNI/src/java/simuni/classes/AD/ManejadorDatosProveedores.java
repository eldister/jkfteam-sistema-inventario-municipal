package simuni.classes.AD;

import java.sql.CallableStatement;
import java.sql.Connection;
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
            sp_ingresoproveedorfisico.setString(11, "123P123");
            sp_ingresoproveedorfisico.setString(12, "Ramón");
            sp_ingresoproveedorfisico.setString(13, "Valdez");
            sp_ingresoproveedorfisico.setString(14, "Sánchez");
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

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos(int npagina, int npaginacion) {
        ArrayList<ProveedorFisico> proveedores = new ArrayList<ProveedorFisico>();
        for (int a = 0; a < npaginacion; a++) {
            ProveedorFisico proveedorfisico = new ProveedorFisico();
            proveedorfisico.setPa_banco("nacional");
            proveedorfisico.setPa_cedula("5042303" + a);
            proveedorfisico.setPa_correoElectronico("uncorero@gmail.com");
            proveedorfisico.setPa_direccionCompania("Nicoya nandayure");
            proveedorfisico.setPa_fax("555555555");
            proveedorfisico.setPa_nombre("Francisco");
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
    }

    public ProveedorFisico getProveedorFisico(String tn_codigoproveedorfisico) {
        ProveedorFisico proveedorfisico = new ProveedorFisico();
        proveedorfisico.setPa_banco("nacional");
        proveedorfisico.setPa_cedula(tn_codigoproveedorfisico);
        proveedorfisico.setPa_correoElectronico("uncorero@gmail.com");
        proveedorfisico.setPa_direccionCompania("Nicoya nandayure");
        proveedorfisico.setPa_fax("555555555");
        proveedorfisico.setPa_nombre("Juan");
        proveedorfisico.setPa_nombreCompania("Respuestos raros");
        proveedorfisico.setPa_numeroCuenta("555555555");
        proveedorfisico.setPa_primerApellido("Coulon");
        proveedorfisico.setPa_segundoApellido("Ollivier");
        proveedorfisico.setPa_sitioWeb("www.google.com");
        proveedorfisico.setPa_telefonoFijo("852023656");
        proveedorfisico.setPa_telefonoMovil("84110967");
        proveedorfisico.setPa_estadoprovedor("0");
        proveedorfisico.setPd_fecharegistro(new java.sql.Date(new java.util.Date().getTime()));
        proveedorfisico.setPn_apartadoPostal(44844);
        return proveedorfisico;
    }

    public boolean isProveedorExistente(String codigoproveedor) {
        return true;
    }

    public int getNumeroProveedoresRegistrados() {
        return 0;
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
