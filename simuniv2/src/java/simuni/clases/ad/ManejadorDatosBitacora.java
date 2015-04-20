/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ad;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.bd.Conexionmysql;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosBitacora {
    
    /**
     * Método para el registro de los eventos acontecidos en el sistema en una tabla
     * en la base de datos que servira como bitacora.
     * 
     * @param registroBitacora objeto con que obtiene información que sera registrada
     * en la base de datos
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
    public String registrarEnBitacora(RegistroBitacora registroBitacora) throws SQLException {
        String resp = "";
        try {
            Connection con = Conexionmysql.obtenerConexion();
            CallableStatement cs = con.prepareCall("{call simuni_sp_registro_enbitacora(?,?,?,?,?)}");
            cs.setString(1, registroBitacora.getUsuario());
            cs.setString(2, registroBitacora.getDescripcion());
            cs.setString(3, registroBitacora.getEstado());
            cs.setString(4, registroBitacora.getObservaciones());
            cs.registerOutParameter(5, java.sql.Types.VARCHAR);
            ResultSet rs = cs.executeQuery();
            resp = cs.getString(5);
            Conexionmysql.cerrarConexion(con);
        } catch (SQLException ex) {
            resp = ex.getMessage();
            throw ex;
        }
        return resp;
    }
}
