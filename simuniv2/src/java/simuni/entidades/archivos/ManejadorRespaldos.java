/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades.archivos;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author FchescO
 */
public class ManejadorRespaldos {

    private String filePath="";
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

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            Date date = new Date();
            setFilePath(backupprefix + "-" + host + "-(" + dateFormat.format(date) + ").sql");

            
            String batchCommand = "";

            System.out.println("Listoooooooo a ver " + password);
            /* String command =  dumpExePath + "/mysqldump -u " + user + " -p" + password + " " + database + " -r " + backupPath;
             System.out.println(command);
             batchCommand = dumpExePath + " -h " + host + ":"+port+" -u " + user + " -p" + password +" " + database + " > \"" + backupPath  + filepath + "\" --set-gtid-purged=OFF";
             */
            if (password != "") {
                //Backup with database

                System.out.println("Listoooooooo a ver " + password);
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + " --password=" + password + "  " + database + " -r \"" + backupPath + ""
                        + getFilePath() + "\"  --set-gtid-purged=OFF";
            } else {
                batchCommand = dumpExePath + " -h " + host + " --port " + port + " -u " + user + "  " + database + " -r \"" + backupPath + "" + getFilePath() + "\"  --set-gtid-purged=OFF";
            }
            
            Runtime runtime = Runtime.getRuntime();
            p = runtime.exec(batchCommand);
            int processComplete = p.waitFor();
            InputStream error = p.getErrorStream();
            for (int i = 0; i < error.available(); i++) {
                System.out.print("" + (char) error.read());
            }
            System.out.println("Exit value es :"+p.exitValue());

            System.out.println(batchCommand + " salidaaaaaa ");
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

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

}
