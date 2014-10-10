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
        encabezados = new Object[]{"Cédula Proveedor", "Nombre", "Primer Apellido", "Segundo Apellido", "Fecha de registro", "Estado"};

    }

    public String RenderizarProveedoresFisicos(ArrayList<ProveedorFisico> to_proveedores, int cantidadpaginas) {
        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        if(to_proveedores==null){
            to_proveedores=new ArrayList<ProveedorFisico>();
        }
        Iterator<ProveedorFisico> iter = to_proveedores.iterator();
        String estadoproveedorfisico = "";
        while (iter.hasNext()) {
            ProveedorFisico proveedorfisico = iter.next();
            switch (proveedorfisico.getPb_estadoprovedor()) {
                case 1:
                    estadoproveedorfisico = "Activo";
                    break;
                case 0:
                    estadoproveedorfisico = "Desactivo";
                    break;
            }
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
        gril.setUrlagregaropcionagregar("registro.jsp");
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
            switch (proveedorfisico.getPb_estadoprovedor()) {
                case 1:
                    estadoproveedorfisico = "Activo";
                    break;
                case 0:
                    estadoproveedorfisico = "Desactivo";
                    break;
            }
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
        gril.setUrlagregaropcionagregar("registro.jsp");
        return gril.renderizarSoloCuerpoTabla(encabezados, filas, cantidadpaginas);
    }    
}
