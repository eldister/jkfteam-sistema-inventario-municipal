/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.LN;

import java.util.ArrayList;
import simuni.classes.AD.ManejadorDatosTipoPago;
import simuni.classes.EN.TipoPago;

/**
 *
 * @author FchescO
 */
public class ManejadorTipoPago {
        public ArrayList<TipoPago> getListaTipoPago() {
            try{
                ManejadorDatosTipoPago manejadortipopago = new ManejadorDatosTipoPago();
                ArrayList<TipoPago> tipopago = manejadortipopago.getListaTipoPago();
                UtilidadesServlet.registrarProcesoSistema("getListaTipoPago", "Se obtuvo la lista de Tipo de Pago");
                return tipopago;
            }
            catch (Exception e){
                UtilidadesServlet.registrarErrorSistema("getListaTipoPago", e.getMessage());
            }
        return null;
    }
}
