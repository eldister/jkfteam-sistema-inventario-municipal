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

    /**
     * Método que registrar en la base de datos una configuración de respaldo
     * configurada por el usuario.
     * 
     * @param departamento objeto con la configuración de respaldo.
     * @return la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
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
    
    /**
     * Método con la cual se actualiza o se renueva una configuración de respaldo
     * de la base de datos configurada por el usuario.
     * 
     * @param departamento objeto con la nueva configuración del respaldo.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
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

    /**
     * Método que obtiene una configuración de respaldo desde la base de datos.
     * 
     * @param codigo identificador de la configuración de respaldo en la base
     * de datos
     * @return objeto con la configuración solicitada.
     * @since 1.0
     */
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
    
    /**
     * Método que obtiene la última tarea de respaldo realizada de la base de datos 
     * del sistema
     * 
     * @return un objeto con la tarea de respaldo obtenida de la base de datos
     * @since 1.0
     */
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
    
    /**
     * Método que realiza la actualización de una tarea programada de respaldo
     * 
     * @param tarearespaldo tarea a la cual se va a actualizar.
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
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
