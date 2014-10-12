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
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.agregarProveedorFisico(to_proveedorfisico);
        }catch(Exception ex){
            //registrar error
            ex.printStackTrace();
            return false;
        }
    }

    public boolean modificarProveedorFisico(ProveedorFisico to_proveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.modificarProveedorFisico(to_proveedorfisico);
        }catch(Exception ex){
            //registrar error
            ex.printStackTrace();
            return false;
        }
    }

    public boolean desactivarProveedorFisico(String tn_codigoproveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.desactivarProveedorFisico(tn_codigoproveedorfisico);
        }catch(Exception ex){
            //registrar error
            return false;
        }
    }

    public ArrayList<ProveedorFisico> getListaProveedoresFisicos(int npagina,int npaginacion) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.getListaProveedoresFisicos(npagina,npaginacion);
        }catch(Exception ex){
            //registrar error
            return null;
        }
    }

    public ProveedorFisico getProveedorFisico(String tn_codigoproveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.getProveedorFisico(tn_codigoproveedorfisico);
        }catch(Exception ex){
            //registrar error
            return null;
        }
    }
    public boolean isProveedorExistente(String codigoproveedor){
        return true;
    }
    
    public int getNumeroProveedoresRegistrados(){
        return 0;
    }

    public ArrayList<ProveedorFisico> buscarProveedoresFisicos(String query) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.buscarProveedoresFisicos(query);
        }catch(Exception ex){
            //registrar error
            return null;
        }
    }
}
