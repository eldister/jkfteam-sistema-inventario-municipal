package simuni.entidades.daemons;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import simuni.clases.ln.ManejadorTareasRespaldo;
import simuni.entidades.archivos.ManejadorRespaldos;

public class Daemon1 implements ServletContextListener {

    private Timer timer;

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
       try{
      
            timer.cancel();
            timer.purge();
       
       }catch(Exception ex){
           ex.printStackTrace();
       }
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("I'm doing awesome stuff right now."+new Date(System.currentTimeMillis()));
                    ManejadorTareasRespaldo mtareasresp=new ManejadorTareasRespaldo();
                    if(mtareasresp.hacerRespaldoBaseDatos(true)){
                        System.out.println("Proceso supuestamente correcto");
                    }
            }
        };

        
        long delay = 5000;
        long period = 60 * 1000; // 10 seconds;
        timer.scheduleAtFixedRate(myTask, delay, period);
    }

}
