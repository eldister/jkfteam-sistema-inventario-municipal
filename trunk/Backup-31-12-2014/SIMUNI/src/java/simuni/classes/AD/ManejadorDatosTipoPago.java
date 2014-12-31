/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.classes.AD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.TipoPago;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosTipoPago {
    
    
    public ArrayList<TipoPago> getListaTipoPago() throws SQLException {
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("SELECT SM00IDTP,SM01NOTP FROM sm_11tipopago");
        ResultSet rs = st.executeQuery();
        ArrayList<TipoPago> tipopago = new ArrayList<TipoPago>();
        while (rs.next()) {
            TipoPago tpago = new TipoPago();
            tpago.setCodigoTipoPago(rs.getInt("SM00IDTP"));
            tpago.setNombreTipoPago(rs.getString("SM01NOTP"));
            tipopago.add(tpago);
            System.out.println("ENTREE Y SIRVOO");
        }
        ConexionMYSQL.cerrarConexion(con);
        return tipopago;

    }
}
