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
    
       /**
       *Este método obtiene todos los departamentes que se encuentran registrados en la base de datos 
       * @return Un ArrayList de todos los departamentos registrados o nulo en caso de que no halla ninguno
       * @since 1.0
       */
       public ArrayList<Departamento> getListaDepartamentos() {

           try{ 
             
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
