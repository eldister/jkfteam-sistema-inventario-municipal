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
import simuni.classes.EN.EstadoActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosEstadoActivo {
    public ArrayList<EstadoActivo> getListadoEstadosActivos() throws SQLException{
       

        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st=con.prepareCall("SELECT SM00NOTE FROM sm_24estadoactivo");
        ResultSet rs=st.executeQuery();
        ArrayList<EstadoActivo>departamentos=new ArrayList<EstadoActivo>();
        while(rs.next()){
            EstadoActivo ea=new EstadoActivo();
            ea.setNombreEstado(rs.getString("SM00NOTE"));
            
            departamentos.add(ea);
            System.out.println("ENTREE Y SIRVOO");
        }
        ConexionMYSQL.cerrarConexion(con);
        return departamentos;        
    }
}
