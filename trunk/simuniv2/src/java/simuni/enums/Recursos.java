package simuni.enums;

import java.net.InetAddress;

/**
 * Esta clase, se convierte como un "repositorio" de valores compartido a lo
 * largo de la aplicación para no tener qeu estar cambiando en cada una de las
 * partes o archivos.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class Recursos {

    public static String ip;
    public static final int intervalo_respaldo_automatico=60000;
    public static final int delay_respaldo_automatico=5000;

    static {
        try {
            ip = InetAddress.getByName(InetAddress.getLocalHost().getCanonicalHostName()).getHostAddress();
        } catch (Exception ex) {
            ip = "localhost";
        }
    }

    /**
     * Listado de servidores de Bases de Datos, sus cadenas de conexión
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    public static enum BD {

        SERVIDORMYSQLFRANCISCO("Server=localhost;Port=3306;Database=myDataBase;Uid=chescosimuni;Pwd=chescosimuni;"),
        SERVIDORMYSQLJEFFERSON("jdbc:mysql://localhost:3306/simuniv2?user=root&password=1234"),
        SERVIDORMYSQLKEYLIN("jdbc:mysql://localhost:3306/simuni_v1?user=adminSIMUNI&password=adminpass"),
        SERVIDORMYSQLDEBIAN("jdbc:mysql://192.168.56.101:3306/simuniv2?user=root&password=root&connectTimeout=30000&socketTimeout=30000");

        private final String texto;

        private BD(String s) {
            texto = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : texto.equals(otherName);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    /**
     * Listado de servidores web.
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    public static enum Servers {

        MAINSERVER("/simuniv2"),
        SERVER_ARCHIVOS("http://" + Recursos.ip + ":8080/archivos");

        private final String texto;

        private Servers(String s) {
            texto = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : texto.equals(otherName);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    /**
     * Listado de servidores para guardar archivos. Dentro del servidor
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    public static enum SSA {

        CARPETABASE("C:\\Users\\FchescO\\Downloads\\pruebas\\"),
        CARPETARAIZARCHIVOSACTIVOS(CARPETABASE + "Activos\\"),
        CARPETARAIZARCHIVOSDEFAULT(CARPETABASE + "Default\\"),
        CARPETAPROVEEDORES(CARPETABASE + "Proveedores\\");

        private final String texto;

        private SSA(String s) {
            texto = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : texto.equals(otherName);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    /**
     * Listado de servidores archivos, a nivel web
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    public static enum SSI {

        //ARCHIVOSBASECONTEXT("/archivos/"),
        ARCHIVOSACTIVOSCONTEXT("/Activos/"),
        //REMOTOARCHIVOSACTIVOSCONTEXT("http://localhost:8080"),
        ARCHIVOSPROVEEDORESCONTEXT("/Proveedores/");

        private final String texto;

        private SSI(String s) {
            texto = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : texto.equals(otherName);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

    /**
     * Listado de recursos compartidos a lo largo de la aplicación web.
     *
     * @author FchescO
     * @since 1.0
     * @version 1.0
     */
    public static enum R {

        LOGOSIMUNIURL("http://localhost:8080/simuniv2/recursos/imagenes/sistema/muni.jpg"),
        TITULOPRINCIPALACTIVOARTICULO("Módulo para el manejo de Activos");

        private final String texto;

        private R(String s) {
            texto = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : texto.equals(otherName);
        }

        @Override
        public String toString() {
            return texto;
        }
    }

}
