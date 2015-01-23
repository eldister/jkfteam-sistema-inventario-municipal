package simuni.entidades.daemons;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Daemon1 implements ServletContextListener {

    private Timer timer;

    public void contextDestroyed(ServletContextEvent sce) {
        if (timer != null) {
            timer.cancel();
        }
    }

    public void contextInitialized(ServletContextEvent sce) {
        Timer timer = new Timer();
        TimerTask myTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("I'm doing awesome stuff right now."+new Date(System.currentTimeMillis()));
            }
        };

        long delay = 1000;
        long period = 10 * 1000; // 10 seconds;
        timer.scheduleAtFixedRate(myTask, delay, period);
    }

}
