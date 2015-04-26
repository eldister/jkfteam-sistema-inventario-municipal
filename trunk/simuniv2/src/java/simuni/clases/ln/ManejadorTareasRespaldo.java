/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import simuni.entidades.Configuracion;
import simuni.entidades.Respuesta;
import simuni.entidades.TareaRespaldo;
import simuni.entidades.archivos.ManejadorRespaldos;

/**
 *
 * @author FchescO
 */
public class ManejadorTareasRespaldo {
    
    //variable global del manejador de respaldos para uso en los métodos de la clase
ManejadorRespaldos mrespaldos = new ManejadorRespaldos();

    /**
     * Método que se encarga de la configuración, la creación y la actualización
     * de un respaldo de base de datos. 
     * 
     * @param planificado parametro booleano para las distintas operaciones 
     * del respaldo de la base de datos. 
     * @return un valor booleano
     * true para la configuración del respaldo de la base de datos.
     * false para la creación del respaldo de base de datos.
     * @since 1.0
     */
    public boolean hacerRespaldoBaseDatos(boolean planificado) {
        boolean respuesta = false;
        TareaRespaldo tarearespaldo = null;
        
        Configuracion conf = null;
        Respuesta r = null;
        ManejadorConfiguracion mconfiguracion = new ManejadorConfiguracion();
        try {

            //obtener tarea respaldo 
            if (planificado) {
                tarearespaldo = mconfiguracion.getConfiguracionRespaldo();
                if (tarearespaldo == null) {
                    System.out.println("La tarea de respaldo es nulo ");
                    return false;
                }
            }
            System.out.println("Voy por aquiiiiiiiiiiiii");
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.sql.Date hoy = new java.sql.Date(System.currentTimeMillis());
            if (!planificado || (dateFormat.format(hoy) != null && dateFormat.format(tarearespaldo.getFecha()) != null
                    && dateFormat.format(hoy).equals(dateFormat.format(tarearespaldo.getFecha())))) {
                System.out.println("Entreeeeeeeeeeeeeeeee");

                conf = mconfiguracion.getConfiguracionRespaldo(-1);
                if (conf == null) {
                    return false;
                }

                respuesta = mrespaldos.respaldarBaseDatos(conf.getPathMysqlDump(), conf.getServerBaseDatos(),
                        conf.getPuertoServer(), conf.getUsuarioBd(), conf.getContraseniaBd(), conf.getPrefijoBackup(),
                        conf.getNombreBaseDatos(), conf.getPathBackup());

            } else {
                System.out.println("Algo con las fechsa" + dateFormat.format(hoy) + dateFormat.format(tarearespaldo.getFecha()));
                return false;
            }

            //si es hoy obtener configuracion
            //hacer respaldo
            //si se pudo esta bien y si no tambien.. registrar
            if (respuesta && tarearespaldo.getFecha() != null&&planificado) {
                //actualizar tarea reporte

                java.sql.Date proximo = new java.sql.Date(tarearespaldo.getFecha().getTime() + conf.getLapsoTiempoBackup() * 24 * 60 * 60 * 1000);
                tarearespaldo.setFecha(proximo);
                r = mconfiguracion.actualizarTareaRespaldo(tarearespaldo);
                if (r != null) {
                    //registrar en bitacora
                    System.out.println("El resultado final pro aqui es " + r.getMensaje());
                }
            }
            else{
                System.out.println("No lo hizo o es una tarea no planificada");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return respuesta;
    }
    
    /**
     * Método que hace una llamada a otro de los metodos de la clase para la creación,
     * configuración y actualización de los respaldos de la base de datos.
     * 
     * @param planificado parametro booleano para las distintas operaciones 
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta hacerRespaldoBD(boolean planificado){
        Respuesta resp=new Respuesta();
        if(hacerRespaldoBaseDatos(planificado)){
            resp.setNivel(1);
            resp.setMensaje("La creación de la tarea de respaldo se hizo de manera satisfactoria. Revisa el path de salida. (Archivo "+mrespaldos.getFilePath()+" )");
        }
        else{
            resp.setNivel(2);
            resp.setMensaje("La creación de la tarea de respaldo no se hizo.");
              
        }
        return resp;
    }

}
