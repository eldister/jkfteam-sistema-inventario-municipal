package simuni.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.Part;
import simuni.clases.ln.ManejadorBitacoraSistema;

/**
 * Esta clase lo qeu se encarga de agrupar una serie de funcionalidades que se
 * ocupan a lo largo de la aplicación, es decir, se repiten. Entonces a través
 * de esta clase se haría un uso de las mismas mas estandar y facil.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class UtilidadesServlet {

    /**
     * Esta función lo que hace es obtener el nombre de un archivo a partir de
     * un objeto Part. El objeto Part se genera cuando se hace una solicitud
     * http y se envía archivos.
     *
     * @param part el objeto part obtenido de la solicitud http
     * @return el nombre del archivo original que el cliente envió o null
     * @since 1.0
     */
    public static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

    /**
     * Función que se encarga de indicar si un valor puede ser o no convertido a
     * int.
     *
     * @param value una cadena a evaluar
     * @return true si es int, false otherwise
     * @since 1.0
     */
    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Una función que convierte un double a un objeto String, lo particular es
     * que devuelve el valor double no en formato científico, sino que con las
     * decimales originales, en su representación "normal"
     *
     * @param value a convertir a String
     * @return una cadena String con la representación del double.
     * @since 1.0
     */
    public static String decimalToString(double value) {

        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

        return df.format(value); //output: 0.00000021
    }

    /**
     * Función que se encarga de indicar si un valor puede ser o no convertido a
     * double.
     *
     * @param value una cadena a evaluar
     * @return true si es double, false otherwise
     * @since 1.0
     */
    public static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Función que se encarga de indicar si un valor puede ser o no convertido a
     * Date.
     *
     * @param value una cadena a evaluar
     * @return true si es Date, false otherwise
     * @since 1.0
     */
    public static boolean tryParseDate(String value) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.parse(value).getTime();

            return true;
        } catch (Exception nfe) {
            return false;
        }
    }

    /**
     * Obtener un objeto java.util.Date a partir de un Objeto (normalmente
     * String), el cual se convierte.
     *
     * @param str el valor a convertir a java.util.Date
     * @param respaldo el valor retornado en caso de error
     * @return un objeto java.util.Date a partir del str convertido o el
     * parámetro respaldo.
     * @since 1.0
     */
    public static Date getFecha(Object str, Date respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseDate(str.toString())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return new Date(formatter.parse(str.toString()).getTime());
            } catch (ParseException ex) {
                return respaldo;
            }
        } else {
            return respaldo;
        }
    }

    /**
     * Obtener un objeto java.sql.Date a partir de un Objeto (normalmente
     * String), el cual se convierte.
     *
     * @param str el valor a convertir a java.sqlDate
     * @param respaldo el valor retornado en caso de error
     * @return un objeto java.sql.Date a partir del str convertido o el
     * parámetro respaldo.
     * @since 1.0
     */
    public static java.sql.Date getFecha(Object str, java.sql.Date respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseDate(str.toString())) {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                return new java.sql.Date(formatter.parse(str.toString()).getTime());
            } catch (ParseException ex) {
                return respaldo;
            }
        } else {
            return respaldo;
        }
    }

    /**
     * Función que se encarga de convertir un objeto java.util.Date a un objeto
     * String.
     *
     * @param fecha el valor a convertir
     * @return una representacion del parámetro fecha en String o vacío
     * @since 1.0
     */
    public static String getFechaString(Date fecha) {

        if (fecha == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return formatter.format(fecha);
        } catch (Exception ex) {
            return "";
        }

    }

    /**
     * Función que se encarga de convertir un objeto java.util.Date a un objeto
     * String. Se devuelve una cadena con Fecha y también con tiempo. Estilo
     * TimeStamp
     *
     * @param fecha el valor a convertir
     * @return una representacion del parámetro fecha en String o vacío
     * @since 1.0
     */
    public static String getFechaconTiempoString(Date fecha) {

        if (fecha == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
        try {
            return formatter.format(fecha);
        } catch (Exception ex) {
            return "";
        }

    }

    /**
     * Obtener un int a partir de un Objeto (normalmente String), el cual se
     * convierte.
     *
     * @param str el valor a convertir a int
     * @param respaldo el valor retornado en caso de error
     * @return un int a partir del str convertido o el parámetro respaldo.
     * @since 1.0
     */
    public static int getNumeroDePagina(Object str, int respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseInt(str.toString())) {
            return Integer.parseInt(str.toString()) - 1;
        } else {
            return respaldo;
        }
    }

    /**
     * Obtener un int a partir de un Objeto (normalmente String), el cual se
     * convierte.
     *
     * @param str el valor a convertir a int
     * @param respaldo el valor retornado en caso de error
     * @return un int a partir del str convertido o el parámetro respaldo.
     * @since 1.0
     */
    public static int getInt(Object str, int respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseInt(str.toString())) {
            return Integer.parseInt(str.toString());
        } else {
            return respaldo;
        }
    }

    /**
     * Obtener un double a partir de un Objeto (normalmente String), el cual se
     * convierte.
     *
     * @param str el valor a convertir a double
     * @param respaldo el valor retornado en caso de error
     * @return un double a partir del str convertido o el parámetro respaldo.
     * @since 1.0
     */
    public static double getDouble(Object str, double respaldo) {
        if (str == null) {
            return respaldo;
        }
        if (UtilidadesServlet.tryParseDouble(str.toString())) {
            return Double.parseDouble(str.toString());
        } else {
            return respaldo;
        }
    }

    /**
     * Función que Hace el registro en bitácora de un error de sistema.
     *
     * @param proceso por ejemplo Ventas
     * @param mensaje Ejemplo: Error general
     * @return un booleano indicando si se hizo o no "correcto" el proceso de
     * registro
     * @since 1.0
     */
    public static boolean registrarErrorSistema(String proceso, String mensaje) {
        ManejadorBitacoraSistema bitacora = new ManejadorBitacoraSistema();
        try {
            boolean resp = bitacora.registrarErrorSistema(proceso, mensaje);
            return resp;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Función que Hace el registro en bitácora de un proceso cualquiera de
     * sistema.
     *
     * @param proceso por ejemplo Ventas
     * @param mensaje Ejemplo: Venta de un Artículo
     * @return un booleano indicando si se hizo o no "correcto" el proceso de
     * registro
     * @since 1.0
     */
    public static boolean registrarProcesoSistema(String proceso, String mensaje) {
        ManejadorBitacoraSistema bitacora = new ManejadorBitacoraSistema();
        try {
            boolean resp = bitacora.registrarProcesoSistema(proceso, mensaje);
            return resp;
        } catch (Exception e) {
            return false;
        }
    }
}
