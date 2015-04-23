package simuni.entidades.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import simuni.enums.Recursos;

/**
 * Esta clase se encarga de abrir y cerrar la conexión a la base de datos de
 * MYSQL.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class Conexionmysql {

    /**
     * Función que se encarga de abrir la conexión de bases de datos.
     *
     * @return un objeto conexión.
     * @since 1.0
     */
    public static Connection obtenerConexion() {

        String driver = "com.mysql.jdbc.Driver";

        try {
            Class.forName(driver).newInstance();

            Connection conn = DriverManager.getConnection(Recursos.BD.SERVIDORMYSQLJEFFERSON.toString());

            return conn;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Función que se encarga de cerrar la conexión de bases de datos.
     *
     * @return un objeto conexión.
     * @since 1.0
     */
    public static boolean cerrarConexion(Connection con) {
        try {
            con.close();
        } catch (Exception ex) {
            return false;
        }

        return true;
    }

}
