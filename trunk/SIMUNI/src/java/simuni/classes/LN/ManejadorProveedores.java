package simuni.classes.LN;

import java.util.ArrayList;
import simuni.classes.AD.ManejadorDatosProveedores;
import simuni.classes.EN.ProveedorFisico;

/**
 *
 * @author FchescO
 */
public class ManejadorProveedores {

    public boolean agregarProveedorFisico(ProveedorFisico to_proveedorfisico) {
        try {
            boolean agregarproveedor = new ManejadorDatosProveedores().agregarProveedorFisico(to_proveedorfisico);
            UtilidadesServlet.registrarProcesoSistema("agregarProveedorFisico", "Se agrego proveedor Fisico");
            return agregarproveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("agregarProveedorFisico", ex.getMessage());//registrar error
            ex.printStackTrace();
            return false;
        }
    }

    public boolean modificarProveedorFisico(ProveedorFisico to_proveedorfisico) {
        try {
            boolean modificarproveedor = new ManejadorDatosProveedores().modificarProveedorFisico(to_proveedorfisico);
            UtilidadesServlet.registrarProcesoSistema("modificarProveedorFisico", "Se modifico el proveedor");
            return modificarproveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("modificarProveedorFisico", ex.getMessage());//registrar error
            ex.printStackTrace();
            return false;
        }
    }

    public boolean desactivarProveedorFisico(String tn_codigoproveedorfisico) {
        try {
            boolean desactivarproveedor = new ManejadorDatosProveedores().desactivarProveedorFisico(tn_codigoproveedorfisico);
            UtilidadesServlet.registrarProcesoSistema("desactivarProveedorFisico", "Se de desactivo proveedor fisico");
            return desactivarproveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("desactivarProveedorFisico", ex.getMessage());//registrar error
            return false;
        }
    }

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos(int npagina, int npaginacion) {
        try {
            ManejadorDatosProveedores p = new ManejadorDatosProveedores();
            ArrayList<ProveedorFisico> proveedorfisico = p.getListaProveedoresFisicos(npagina, npaginacion);
            UtilidadesServlet.registrarProcesoSistema("getListaProveedoresFisicos", "Se obtuvo la lista de los proveedores Fisicos");
            return proveedorfisico;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("getListaProveedoresFisicos", ex.getMessage()); //registrar error
            ex.printStackTrace();
            return null;
        }
    }

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos() {
        try {
            ManejadorDatosProveedores p = new ManejadorDatosProveedores();
            ArrayList<ProveedorFisico> proveedorfisico = p.getListaProveedoresFisicos();
            UtilidadesServlet.registrarProcesoSistema("getListaProveedoresFisicos", "Se obtuvo la lista de los proveedores Fisicos");
            return proveedorfisico;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("getListaProveedoresFisicos", ex.getMessage()); //registrar error
            return null;
        }
    }

    public ProveedorFisico getProveedorFisico(String tn_codigoproveedorfisico) {
        try {
            ManejadorDatosProveedores p = new ManejadorDatosProveedores();
            ProveedorFisico proveedor = p.getProveedorFisico(tn_codigoproveedorfisico);
            UtilidadesServlet.registrarProcesoSistema("getProveedorFisicos", "Se obtuvo el Proveedor fisico");
            return proveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("getProveedorFisico", ex.getMessage());//registrar error
            ex.printStackTrace();
            return null;
        }
    }

    public boolean isProveedorExistente(String codigoproveedor) {
        try {
            boolean proveedorExiste = new ManejadorDatosProveedores().isProveedorExistente(codigoproveedor);
            UtilidadesServlet.registrarProcesoSistema("isProveedorExistente", "El proveedor Existe");
            return proveedorExiste;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("isProveedorExistente", e.getMessage());
        }
        return true;
    }

    public int getNumeroProveedoresRegistrados() {
        int cantidad = 0;
        try {

            ManejadorDatosProveedores manejadorproveedor = new ManejadorDatosProveedores();
            cantidad = manejadorproveedor.getNumeroProveedoresRegistrados();
            UtilidadesServlet.registrarProcesoSistema("getNumeroProveedoresRegistrados", "Total de Proveedores Registrados");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getNumeroProveedoresRegistrados", e.getMessage());
        }
        return cantidad;
    }

    public ArrayList<ProveedorFisico> buscarProveedoresFisicos(String query,int desplazamiento,int paginacion) {
        try {
            ManejadorDatosProveedores p = new ManejadorDatosProveedores();
            ArrayList<ProveedorFisico> proveedor = p.buscarProveedoresFisicos(query,desplazamiento,paginacion);
            UtilidadesServlet.registrarProcesoSistema("buscarProveedoresFisicos", "Busqueda de Proveedor con exito");
            return proveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("buscarProveedoresFisicos", ex.getMessage());//registrar error
            return null;
        }
    }
}
