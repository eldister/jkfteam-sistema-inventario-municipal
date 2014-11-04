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
import simuni.classes.EN.TipoActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosTipoActivo {

    public ArrayList<TipoActivo> getListaTipoActivos() throws SQLException {
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st = con.prepareCall("SELECT SM00IDTA,SM01NOTA FROM sm_09tipoactivo;");
        ResultSet rs = st.executeQuery();
        ArrayList<TipoActivo> tipoactivo = new ArrayList<TipoActivo>();
        while (rs.next()) {
            TipoActivo tactivo = new TipoActivo();
            tactivo.setCodigoTipoActivo(rs.getInt("SM00IDTA"));
            tactivo.setNombreTipoActivo(rs.getString("SM01NOTA"));
            tipoactivo.add(tactivo);
            System.out.println("ENTREE Y SIRVOO Tipos de activo");
        }
        ConexionMYSQL.cerrarConexion(con);
        return tipoactivo;

    }
}
