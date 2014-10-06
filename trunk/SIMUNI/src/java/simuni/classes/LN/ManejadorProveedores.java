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
            return false;
        }
    }

    public boolean modificarProveedorFisico(ProveedorFisico to_proveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.agregarProveedorFisico(to_proveedorfisico);
        }catch(Exception ex){
            //registrar error
            return false;
        }
    }

    public boolean desactivarProveedorFisico(int tn_codigoproveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.desactivarProveedorFisico(tn_codigoproveedorfisico);
        }catch(Exception ex){
            //registrar error
            return false;
        }
    }

    public ArrayList<ProveedorFisico> getListaProveedores() {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.getListaProveedoresFisicos();
        }catch(Exception ex){
            //registrar error
            return null;
        }
    }

    public ProveedorFisico getProveedorFisico(int tn_codigoproveedorfisico) {
        try{
            ManejadorDatosProveedores p= new ManejadorDatosProveedores();
            return p.getProveedorFisico(tn_codigoproveedorfisico);
        }catch(Exception ex){
            //registrar error
            return null;
        }
    }

}
