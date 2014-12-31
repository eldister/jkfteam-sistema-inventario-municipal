/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.enums;

/**
 *
 * @author FchescO
 */
public class Recursos {

    public static enum BD {

        SERVIDORMYSQLFRANCISCO("Server=localhost;Port=3306;Database=myDataBase;Uid=chescosimuni;Pwd=chescosimuni;"),
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

    public static enum Servers {

        MAINSERVER("/simuniv2"),
        SERVER_ARCHIVOS("/archivos");
        
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

    public static enum SSI {

        ARCHIVOSBASECONTEXT("/archivos/"),
        ARCHIVOSACTIVOSCONTEXT( "/Activos/"),
        REMOTOARCHIVOSACTIVOSCONTEXT("http://localhost:8080"),
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

    public static enum R {

        LOGOSIMUNIURL("http://localhost:8080/SIMUNI/recursos/imagenes/sistema/logomuni.jpg"),
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
