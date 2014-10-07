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
}
