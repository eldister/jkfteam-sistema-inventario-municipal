/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.EN;

/**
 *
 * @author FchescO
 */
public class Servidor {

    public static enum BD {

        SERVIDORMYSQLFRANCISCO("Server=localhost;Port=3306;Database=myDataBase;Uid=chescosimuni;Pwd=chescosimuni;");

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

    public static enum SSA {

        CARPETABASE("C:\\Users\\FchescO\\Downloads\\pruebas\\"),
        CARPETARAIZARCHIVOSACTIVOS(CARPETABASE + "Activos\\"),
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
        ARCHIVOSACTIVOSCONTEXT(ARCHIVOSBASECONTEXT + "Activos/"),
        ARCHIVOSPROVEEDORESCONTEXT(ARCHIVOSBASECONTEXT + "Proveedores/");

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

}
