/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.EN;

/**
 * La clase muestra los mensajes de exito o de error al momento de hacer el registro de un activo 
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
    
    public static enum MensajesError{
        ERRORENREGISTROACTIVOARTICULO("El proceso de registro ha presentado un error"), 
        ERRORENREGISTROACTIVOTRANSPORTE("");
       
        private final String mensaje;
        private MensajesError(String s) {
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
