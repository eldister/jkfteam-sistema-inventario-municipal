/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosPuesto;
import simuni.entidades.mantenimientos.Puesto;

/**
 *
 * @author FchescO
 */
public class ManejadorPuesto {
    /**
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ArrayList<Puesto> listadoPuesto() {

        ArrayList<Puesto> puestos = null;
        ManejadorDatosPuesto mdPuesto = new ManejadorDatosPuesto();
        try {
            ResultSet resp = null;
            resp = mdPuesto.listadoPuesto();
            if (resp.next()) {
                puestos = new ArrayList<Puesto>();
                do {
                    Puesto puesto = new Puesto();
                    puesto.setCodigoPuesto(resp.getInt(1));
                    puesto.setNombrePuesto(resp.getString(2));
                    puestos.add(puesto);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return puestos;
    }
}
