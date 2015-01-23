/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades.archivos;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author FchescO
 */
public class ManejadorRespaldos {

    /**
     * This method will create a backup from Specified database with create and
     * drop database commands When your going to restore the DB you don’t need
     * to create the DB it will handle by the script
     *
     * @param dumpExePath
     * @param host
     * @param port
     * @param user
     * @param password
     * @param backupprefix
     * @param database
     * @param backupPath
     * @return
     */
    public boolean respaldarBaseDatos(String dumpExePath, String host,
            String port, String user, String password,
            String backupprefix, String database, String backupPath) {
        boolean status = false;
        try {
            Process p = null;
            
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String filepath = backupprefix + database + "-" + host + "-(" + dateFormat.format(date) + ").sql";

            String batchCommand = "";
            if (password != "") {
                //Backup with database
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --add-drop-database -B " + database + " -r \"" + backupPath + "" + filepath + "\"";
            }

            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();

            if (processComplete == 0) {
                status = true;
                System.out.println("Backup created successfully for with DB " + database + " in " + host + ":" + port);
            } else {
                status = false;
                System.out.println("Could not create the backup for with DB " + database + " in " + host + ":" + port);
            }

        } catch (IOException ioe) {
            System.out.println(ioe.getCause());
        } catch (Exception e) {
            System.out.println(e.getCause());
        }
        return status;
    }
    

}
