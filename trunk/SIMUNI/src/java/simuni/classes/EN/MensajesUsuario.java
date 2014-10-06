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
public class MensajesUsuario {

    public static enum MensajesExito {

        EXITOREGISTROACTIVOARTICULO("El proceso de registro de activo ha sido correcto"),
        EXITOREGISTROACTIVOTRANSPORTE("");
       
        private final String mensaje;
        private MensajesExito(String s) {
            mensaje = s;
        }

        public boolean equalsName(String otherName) {
            return (otherName == null) ? false : mensaje.equals(otherName);
        }

        @Override
        public String toString() {
            return mensaje;
        }
    }
}
