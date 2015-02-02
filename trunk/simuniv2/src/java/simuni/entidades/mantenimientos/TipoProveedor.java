/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades.mantenimientos;

/**
 *
 * @author FchescO
 */
public class TipoProveedor {
    private int codigoTipoProveedor;
    private String nombreTipoProveedor;

    /**
     * @return the codigoTipoProveedor
     */
    public int getCodigoTipoProveedor() {
        return codigoTipoProveedor;
    }

    /**
     * @param codigoTipoProveedor the codigoTipoProveedor to set
     */
    public void setCodigoTipoProveedor(int codigoTipoProveedor) {
        this.codigoTipoProveedor = codigoTipoProveedor;
    }

    /**
     * @return the nombreTipoProveedor
     */
    public String getNombreTipoProveedor() {
        return nombreTipoProveedor;
    }

    /**
     * @param nombreTipoProveedor the nombreTipoProveedor to set
     */
    public void setNombreTipoProveedor(String nombreTipoProveedor) {
        this.nombreTipoProveedor = nombreTipoProveedor;
    }
}
