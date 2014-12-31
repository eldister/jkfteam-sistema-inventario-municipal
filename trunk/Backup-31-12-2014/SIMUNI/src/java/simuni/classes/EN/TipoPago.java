/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.EN;

/**
 * En esta clase estará los tipo de pagos en que se hará las cancelanción de la compra de nuevos activos 
 * @author FchescO
 */
public class TipoPago {
 private int codigoTipoPago;
 private String nombreTipoPago;

    /**
     * obtener el código de tipo de pago
     * @return the codigoTipoPago
     */
    public int getCodigoTipoPago() {
        return codigoTipoPago;
    }

    /**
     * poner el código de tipo de pago
     * @param codigoTipoPago the codigoTipoPago to set
     */
    public void setCodigoTipoPago(int codigoTipoPago) {
        this.codigoTipoPago = codigoTipoPago;
    }

    /**
     * obtener el nombre de tipo de pago
     * @return the nombreTipoPago
     */
    public String getNombreTipoPago() {
        return nombreTipoPago;
    }

    /**
     * el nombre de tipo de pago
     * @param nombreTipoPago the nombreTipoPago to set
     */
    public void setNombreTipoPago(String nombreTipoPago) {
        this.nombreTipoPago = nombreTipoPago;
    }
 
}
