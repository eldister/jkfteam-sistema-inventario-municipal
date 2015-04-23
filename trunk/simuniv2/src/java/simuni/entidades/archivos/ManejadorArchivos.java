/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades.archivos;

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

    /**
     * Método que obtiene la información de un cualquier tipo de archivo que se requiera guardar
     * aunque anteriormente valida no exista o no el archivo que se va a guardar
     * 
     * @param rutaarchivo la ruta o directorio del archivo a guardar
     * @param nombrearchivo nombre del archivo a guarduar
     * @param in representa el flujo de entrada de bytes del archivo a guardar
     * @throws Exception en caso de que ocurra una excepcion
     * @return un valor booleano true al terminar el proceso de guardado
     * @since 1.0
     */
    public boolean guardarArchivo(String rutaarchivo, String nombrearchivo, InputStream in) throws Exception {

        OutputStream os = null;
        InputStream is = in;
        try {
            File archivo = new File(rutaarchivo);
            if (!archivo.exists()) {
                archivo.mkdirs();
            }

            System.out.println("Aqui vamos viendoooo " + rutaarchivo + " ---------- " + nombrearchivo);
            os = new FileOutputStream(new File(archivo, nombrearchivo));

            byte[] buffer = new byte[4096];
            for (int n; (n = is.read(buffer)) != -1;) {
                os.write(buffer, 0, n);
            }
            System.out.println("SALIIIIIIIIIIIIIIIIIIII");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw ex;
        } finally {
            if (os != null) {
                os.close();
            }
            if (is != null) {
                is.close();
            }
        }
        return true;
    }

    /**
     * Método que devuelve el flujo de bytes de un archivo según la ruta del mismo
     * 
     * @param rutaarchivo ruta o directorio del archivo
     * @throws Exception en caso de que ocurra un error
     * @return un objeto de tipo InputStream
     * @since 1.0
     */
    public InputStream obtenerArchivo(String rutaarchivo) throws Exception {

        //variable que no se esta utilizando
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

    /**
     * Método que valida si un archivo es existente o no 
     * 
     * @param pathfile diretorio o ubicación del archivo
     * @return valores boolenos
     * true en caso de que exista 
     * false en caso de que no exista
     */
    public boolean isArchivoExistente(String pathfile) {
        try {
            File f = new File(pathfile);

            return (f.exists() && !f.isDirectory());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
