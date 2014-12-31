/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.LN;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.AD.ManejadorDatosTipoPago;
import simuni.classes.EN.TipoPago;

/**
 *
 * @author FchescO
 */
public class ManejadorTipoPago {

    /**
     * Este método es para obtener todos los tipos de pagos en las que puede pagar un comprador de 
     * alguno de los artículos activos o servicios que ofrezca la municipalidad, de los cuales puede
     * pagar ya sea a crédito, contado etc
     * @return Un ArrayList con todos los tipos de pagos o nulo en caso de que no halla podido obtenerlos
     * @since 1.0
     */
    public ArrayList<TipoPago> getListaTipoPago() {
        try {
            return new ManejadorDatosTipoPago().getListaTipoPago();
        } catch (SQLException ex) {
            Logger.getLogger(ManejadorTipoPago.class.getName()).log(Level.SEVERE, null, ex);
        }
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
