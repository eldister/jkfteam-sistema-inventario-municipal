
package simuni.classes.EN.BD;


import java.sql.Connection;
import java.sql.DriverManager;
import simuni.classes.EN.Servidor;

/**
 *
 * @author FchescO
 */
public class ConexionMYSQL {

    public static Connection obtenerConexion() {

        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(Servidor.BD.SERVIDORMYSQLDEBIAN.toString());

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static boolean cerrarConexion(Connection con){
        try{
            con.close();
        }catch(Exception ex){
            return false;
        }
        
        return true;
    }

}
