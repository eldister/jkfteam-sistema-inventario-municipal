/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import simuni.clases.ad.ManejadorDatosBitacora;
import simuni.entidades.RegistroBitacora;
import simuni.entidades.Respuesta;

/**
 *
 * @author FchescO
 */
public class ManejadorBitacora {

    private static ManejadorBitacora instance;

    private ManejadorBitacora() {
    }

    public static ManejadorBitacora getInstance() {
        if (instance == null) {
            instance = new ManejadorBitacora();
        }
        return instance;
    }

    public synchronized boolean registrarEnBitacora(RegistroBitacora registroBitacora) {
        boolean resp = true;
        ManejadorDatosBitacora mdbitacora = new ManejadorDatosBitacora();

        try {
            String msg = mdbitacora.registrarEnBitacora(registroBitacora);
            if (msg != null && msg.startsWith("2")) {
                System.out.println("Hubo un error");
            } else {
                System.out.println("Correcto");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return resp;
    }

    public RegistroBitacora generarRegistroBitacora(Respuesta respuesta, HttpServletRequest request,
            String descripcion, String observaciones) {

        RegistroBitacora registro = new RegistroBitacora();
        registro.setUsuario((String) request.getSession().getAttribute("USERNAME"));
        registro.setEstado(respuesta!=null&&respuesta.getNivel() == 2 ? "Incompleto" : "Completo");
        registro.setDescripcion(descripcion);
        //cambiar observaciones
        observaciones=observaciones + "Desde la dirección "+request.getRemoteAddr()+". Otros datos: Autenticación"+
                request.getAuthType()+ " Protocolo: "+request.getProtocol()+" Id sesión: "+request.getRequestedSessionId();
        registro.setObservaciones(observaciones);
        return registro;
    }
}
