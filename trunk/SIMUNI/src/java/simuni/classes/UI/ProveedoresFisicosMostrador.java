/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.UI;

import java.util.ArrayList;
import java.util.Iterator;
import simuni.classes.EN.GrillaBase;
import simuni.classes.EN.ProveedorFisico;


/**
 *
 * @author FchescO
 */
public class ProveedoresFisicosMostrador {

    Object[] criteriofiltros;
    Object[] encabezados;

    public ProveedoresFisicosMostrador() {
        criteriofiltros = new Object[]{};
        encabezados = new Object[]{"Cédula Proveedor", "Nombre", "Primer Apellido", "Segundo Apellido", "Correo", "Tel. Celular"};

    }

    public String RenderizarProveedoresFisicos(ArrayList<ProveedorFisico> to_proveedores, int cantidadpaginas) {
        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        if(to_proveedores==null){
            to_proveedores=new ArrayList<ProveedorFisico>();
        }
        Iterator<ProveedorFisico> iter = to_proveedores.iterator();
     
        while (iter.hasNext()) {
            ProveedorFisico proveedorfisico = iter.next();
            Object[] obj = new Object[]{
                proveedorfisico.getPa_cedula(),
                proveedorfisico.getPa_nombre(),
                proveedorfisico.getPa_primerApellido(),
                proveedorfisico.getPa_segundoApellido(),
                proveedorfisico.getPa_correoElectronico(),
                proveedorfisico.getPa_telefonoMovil()
            };
            filas.add(obj);
        }
        GrillaBase gril = new GrillaBase();
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionver(false);
      gril.setUrlpaginacionlink("http://localhost:8080/SIMUNI/modulos/proveedores?proceso=verproveedoresfisicos");

        gril.setUrlagregaropcionagregar("/SIMUNI/modulos/proveedores?proceso=registroproveedorfisico");
        gril.setBusquedalabel("Buscar Proveedor");
        return gril.renderizar(criteriofiltros, encabezados, filas, cantidadpaginas);
    }
    public String RenderizarActualizacionProveedoresFisicos(ArrayList<ProveedorFisico> to_proveedores, int cantidadpaginas) {
        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        if(to_proveedores==null){
            to_proveedores=new ArrayList<ProveedorFisico>();
        }
        Iterator<ProveedorFisico> iter = to_proveedores.iterator();
        String estadoproveedorfisico = "";
        while (iter.hasNext()) {
            ProveedorFisico proveedorfisico = iter.next();
           estadoproveedorfisico=proveedorfisico.getPa_estadoprovedor();
            Object[] obj = new Object[]{
                proveedorfisico.getPa_cedula(),
                proveedorfisico.getPa_nombre(),
                proveedorfisico.getPa_primerApellido(),
                proveedorfisico.getPa_segundoApellido(),
                proveedorfisico.getPd_fecharegistro(),
                estadoproveedorfisico
            };
            filas.add(obj);
        }
        GrillaBase gril = new GrillaBase();
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionver(false);
        gril.setUrlpaginacionlink("http://localhost:8080/SIMUNI/modulos/proveedores?proceso=verproveedoresfisicos");
        gril.setUrlagregaropcionagregar("/SIMUNI/modulos/proveedores?proceso=registroproveedorfisico");
        return gril.renderizarSoloCuerpoTabla(encabezados, filas, cantidadpaginas);
    }    
}
