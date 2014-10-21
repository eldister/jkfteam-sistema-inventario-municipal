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
import simuni.classes.EN.BD.ConexionMYSQL;
import simuni.classes.EN.Usuario;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosUsuario {

    public Usuario login(String usuario, String contrasena) throws SQLException {
        Usuario usuarioresp = null;
        Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st=con.prepareCall("{CALL sp_login(?,?)");
        st.setString(1, usuario);
        st.setString(2, contrasena);
        ResultSet rs=st.executeQuery();
        
        System.out.println("Pase por aqui lgoin");
        if(rs.next()){
            usuarioresp=new Usuario();
            usuarioresp.setNombreusuario(rs.getString("SM00IDUS"));
            usuarioresp.setTipousuario(rs.getInt("SM02TIUS"));
            
           
            System.out.println("USUARIO SI HAY");
        }
        ConexionMYSQL.cerrarConexion(con);
        return usuarioresp;
    }
    
        public String getMenuUsuario(String idusuario){
        String resp="";
        try{

         Connection con = ConexionMYSQL.obtenerConexion();
        PreparedStatement st=con.prepareCall("{CALL sp_obtenermenuusuario(?)}");
        st.setString(1, idusuario);
        ResultSet rs=st.executeQuery();
       
        if(rs.next()){
         
            resp=rs.getString("SM01TEME");
         
            
           
            System.out.println("Menu SI HAY");
        }
        ConexionMYSQL.cerrarConexion(con);
        
        }catch(Exception ex){
            //registrar error
            ex.printStackTrace();
            return null;
        }        
        return resp;
    }
}
