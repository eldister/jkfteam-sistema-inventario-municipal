/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.sql.SQLException;
import simuni.clases.ad.ManejadorDatosConfiguracion;
import simuni.entidades.Configuracion;
import simuni.entidades.Respuesta;
import simuni.entidades.TareaRespaldo;

/**
 *
 * @author FchescO
 */
public class ManejadorConfiguracion {

    public Respuesta registrarConfiguracionRespaldoBD(Configuracion departamento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosConfiguracion mddepartamento = new ManejadorDatosConfiguracion();

        try {
            String msg = mddepartamento.registrarConfiguracionRespaldoBD(departamento);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }
    public Respuesta actualizarConfiguracionRespaldoBD(Configuracion departamento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosConfiguracion mddepartamento = new ManejadorDatosConfiguracion();

        try {
            String msg = mddepartamento.modificarConfiguracionRespaldoBD(departamento);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }

    public Configuracion getConfiguracionRespaldo(int codigo) {
        Configuracion resp = null;
        ManejadorDatosConfiguracion mdestado = new ManejadorDatosConfiguracion();
        try {
            resp = mdestado.getConfiguracionRespaldo(codigo);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
    public TareaRespaldo getConfiguracionRespaldo() {
        TareaRespaldo resp = null;
        ManejadorDatosConfiguracion mdestado = new ManejadorDatosConfiguracion();
        try {
            resp = mdestado.getTareaRespaldo();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }    
    
    public Respuesta actualizarTareaRespaldo(TareaRespaldo tarearespaldo) {
        Respuesta resp = new Respuesta();
        ManejadorDatosConfiguracion mddepartamento = new ManejadorDatosConfiguracion();

        try {
            String msg = mddepartamento.modificarTareaRespaldo(tarearespaldo);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }    
}
