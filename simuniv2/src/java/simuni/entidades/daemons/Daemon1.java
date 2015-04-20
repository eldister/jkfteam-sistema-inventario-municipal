package simuni.entidades.daemons;

import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import simuni.clases.ln.ManejadorTareasRespaldo;
import simuni.enums.Recursos;

/**
 * Esta clase se encarga de la gestión de tareas de manera background o más
 * conocidos como Daemon, de allí su nombre. A partir de esta clase se deben
 * hacer tareas en background como respaldos automáticos, reportes automáticos.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class Daemon1 implements ServletContextListener {

    /**
     * El objeto Timer para poder hacer las tareas cada cierto tiempo, que por
     * defecto sera cada 20 segundos.
     *
     * @since 1.0
     */
    private Timer timer;

    /**
     * Evento cuando se cierra la aplicación web.
     *
     * @param sce un objeto del tipo ServletContextEvent
     * @since 1.0
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {

            timer.cancel();
            timer.purge();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Evento cuando se levanta la aplicación web.
     *
     * @param sce un objeto del tipo ServletContextEvent
     * @since 1.0
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer();
        TimerTask tarea_respaldo_automatico = new TimerTask() {
            @Override
            public void run() {
                ManejadorTareasRespaldo mtareasresp = new ManejadorTareasRespaldo();
                if (mtareasresp.hacerRespaldoBaseDatos(true)) {
                    System.out.println("Proceso  correcto");
                }
            }
        };


        timer.scheduleAtFixedRate(tarea_respaldo_automatico, Recursos.delay_respaldo_automatico,
                Recursos.intervalo_respaldo_automatico);
    }

}
