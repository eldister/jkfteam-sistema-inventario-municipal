
package simuni.entidades.bd;


import java.sql.Connection;
import java.sql.DriverManager;
import simuni.enums.Recursos;


/**
 *
 * @author FchescO
 */
public class Conexionmysql {

    public static Connection obtenerConexion() {

        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(Recursos.BD.SERVIDORMYSQLDEBIAN.toString());

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
