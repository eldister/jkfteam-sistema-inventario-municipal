/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.LN;

import java.util.ArrayList;
import simuni.classes.AD.ManejadorDatosDepartamento;
import simuni.classes.EN.Departamento;

/**
 *
 * @author FchescO
 */
public class ManejadorDepartamentos {
       public ArrayList<Departamento> getListaDepartamentos() {

        return new ManejadorDatosDepartamento().getListaDepartamentos();

           try{ return new ManejadorDatosDepartamento().getListaDepartamentos();
               ManejadorDatosDepartamento manejadordepartamentos = new ManejadorDatosDepartamento();
               ArrayList<Departamento> departamento = manejadordepartamentos.getListaDepartamentos();
               UtilidadesServlet.registrarProcesoSistema("getListaDepartamentos", "Se obtuvo la lista de Departamentos");
               return departamento;
           }
           catch (Exception e){
               UtilidadesServlet.registrarErrorSistema("getListaDepartamentos", e.getMessage());
           }
        return null;

    }
}
