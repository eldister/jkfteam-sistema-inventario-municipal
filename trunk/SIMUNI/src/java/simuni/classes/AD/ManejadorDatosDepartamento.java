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
import java.sql.Statement;
import java.util.ArrayList;
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.Departamento;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosDepartamento {
    
    public ArrayList<Departamento> getListaDepartamentos() throws SQLException {
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st=con.prepareCall("SELECT SM00IDDP,SM01NODP FROM sm_10departamentos");
        ResultSet rs=st.executeQuery();
        ArrayList<Departamento>departamentos=new ArrayList<Departamento>();
        while(rs.next()){
            Departamento depto=new Departamento();
            depto.setPn_codigo(rs.getInt("SM00IDDP"));
            depto.setPa_nombre(rs.getString("SM01NODP"));
            departamentos.add(depto);
            System.out.println("ENTREE Y SIRVOO");
        }
        ConexionMYSQL.cerrarConexion(con);
        return departamentos;
    }
}
