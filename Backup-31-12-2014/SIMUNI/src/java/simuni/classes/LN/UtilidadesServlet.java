package simuni.classes.LN;

import javax.servlet.http.Part;
import simuni.classes.AD.BitacoraSistema;

/**
 *
 * @author FchescO
 */
public class UtilidadesServlet {

    
    public static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean registrarErrorSistema(String proceso, String mensaje) {
        BitacoraSistema bitacora=new BitacoraSistema();
        try{
           boolean resp= bitacora.registrarErrorSistema(proceso, mensaje);
            return resp;
        }catch(Exception e){
            return false;
        }
    }

    public static boolean registrarProcesoSistema(String proceso, String mensaje) {
        BitacoraSistema bitacora=new BitacoraSistema();
        try{
           boolean resp= bitacora.registrarProcesoSistema(proceso, mensaje);
            return resp;
        }catch(Exception e){
            return false;
        }
    }
}
