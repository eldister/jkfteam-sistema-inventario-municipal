package simuni.classes.AD;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import simuni.classes.AR.ManejadorArchivos;
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
            //registramos activo en bd

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
            proveedorfisico.setPb_estadoprovedor(0);
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
        proveedorfisico.setPb_estadoprovedor(0);
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
                proveedorfisico.setPb_estadoprovedor(0);
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
