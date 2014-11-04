/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.classes.AR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author FchescO
 */
public class ManejadorArchivos {

    public boolean guardarArchivo(String rutaarchivo, String nombrearchivo, InputStream in) throws Exception {

        OutputStream os = null;
        InputStream is = in;
        try {
            File archivo = new File(rutaarchivo);
            archivo.mkdirs();
            os = new FileOutputStream(new File(archivo, nombrearchivo));

            byte[] buffer = new byte[4096];
            for (int n; (n = is.read(buffer)) != -1;) {
                os.write(buffer, 0, n);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            os.close();
            is.close();
        }
        return true;
    }

    public InputStream obtenerArchivo(String rutaarchivo) throws Exception {

        OutputStream os = null;

        try {
            File archivo = new File(rutaarchivo);
            InputStream is = new FileInputStream(archivo);
            return is;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
