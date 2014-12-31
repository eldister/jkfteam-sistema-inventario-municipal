package simuni.classes.LN;

import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.http.Part;
import simuni.classes.AD.ManejadorDatosProveedores;
import simuni.classes.EN.ProveedorFisico;

/**
 *
 * @author FchescO
 */
public class ManejadorProveedores {

    /**
     * Este método agrega a los registros de la base de datos a un nuevo proveedor físico
     * @param to_proveedorfisico Proveedor nuevo a registrar
     * @return verdadero en caso de que se halla ingresado correctamente o false si no lo fue 
     * @since 1.0
     */
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

    /**
     *Este método es para la actualización de la información de los proveedores físicos almacenados en la base de datos
     * @param to_proveedorfisico Proveedor al cual se le va a modificar la información
     * @return true en caso de que el articulo halla sido actualizado con exito o false en caso de que no
     * @since 1.0
     */
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

    /**
     * Este método desactiva un registro en la base de datos especificado por el código del proveedor físico
     * @param tn_codigoproveedorfisico Código de identificación del proveedor físico
     * @return true cuando el artículo activo ha sido desactivado con éxito o false en caso de que no
     * @since 1.0
     */
    public boolean desactivarProveedorFisico(String tn_codigoproveedorfisico) {
        try {
            boolean desactivarproveedor = new ManejadorDatosProveedores().desactivarProveedorFisico(tn_codigoproveedorfisico);
            UtilidadesServlet.registrarProcesoSistema("desactivarProveedorFisico", "Se de desactivo proveedor fisico");
            return desactivarproveedor;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("desactivarProveedorFisico", ex.getMessage());//registrar error
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Este método es utilizado para obtener una lista completa de los proveedores físicos registrados en la base de datos
     * @param npagina
     * @param npaginacion
     * Los anteriores parámetros es para limitar el número de proveedores en la consulta a la base de datos
     * @return un ArrayList con los artículos activos o nulo en caso de que no haya obtenido ninguno
     * @since 1.0
     */
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

    /**
     * Este método es utilizado para obtener una lista completa de los proveedores físicos registrados en la base de datos
     * sin ninguna restricción y ningún parámetro
     * @return Un ArrayList con todos los proveedores físicos registrados o nulo en caso de que no los halla obtenido
     * @since 1.0
     */
    public ArrayList<ProveedorFisico> getListaProveedoresFisicos() {
        try {
            ManejadorDatosProveedores p = new ManejadorDatosProveedores();
            ArrayList<ProveedorFisico> proveedorfisico = p.getListaProveedoresFisicos();
            UtilidadesServlet.registrarProcesoSistema("getListaProveedoresFisicos", "Se obtuvo la lista de los proveedores Fisicos");
            return proveedorfisico;
        } catch (Exception ex) {
            UtilidadesServlet.registrarErrorSistema("getListaProveedoresFisicos", ex.getMessage()); //registrar error
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Este método obtiene un proveedor físico en especifico definido por el identificador o código de proveedor
     * en otras palabras es una búsqueda de un proveedor físico en particular utilizando su código de identificación
     * @param tn_codigoproveedorfisico Código de identificación del proveedor físico
     * @return El proveedor físico especificado o un nulo en caso de que no se este registrado
     * @since 1.0
     */
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

    /**
     * Este método hace una validación para comprobar si un proveedor físico existe actualmente en la base de datos
     * @param codigoproveedor Código o identificación del proveedor
     * @return verdadero en caso de que el activo exista o false en caso de que no
     * @since 1.0
     */
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

    /**
     * Este método obtiene el número total de los proveedores físicos registrados en la base de datos
     * @return La cantidad total de los proveedores físicos registrados
     * @since 1.0
     */
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

     /**
     * Este método realiza una búsqueda de más de un proveedore físico
     * @param query consulta de búsqueda para el LIKE en MySQL
     * Los siguientes parámetros son para obtener y limitar el total de resultados obtenidos por la consulta 
     * @param desplazamiento
     * @param paginacion
     * @return un ArrayList con todos los proveedores físicos encontrados, devuelve nulo en caso de que no 
     * se halla encontrado nada
     * @since 1.0
     */
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
    
    public boolean guardarProveedor(ProveedorFisico prove,Collection<Part> parts){
        boolean bo=new ManejadorDatosProveedores().guardarProveedor(prove, parts);
        return bo;
    }
}
