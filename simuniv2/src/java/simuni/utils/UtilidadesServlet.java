package simuni.utils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Part;
import simuni.clases.ln.ManejadorBitacoraSistema;

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

    public static String decimalToString(double value) {

        DecimalFormat df = new DecimalFormat("0", DecimalFormatSymbols.getInstance(Locale.ENGLISH));
        df.setMaximumFractionDigits(340); //340 = DecimalFormat.DOUBLE_FRACTION_DIGITS

        return df.format(value); //output: 0.00000021
    }

    public static boolean tryParseDouble(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static boolean tryParseDate(String value) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            formatter.parse(value).getTime();

            return true;
        } catch (Exception nfe) {
            return false;
        }
    }

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

    public static boolean registrarErrorSistema(String proceso, String mensaje) {
        ManejadorBitacoraSistema bitacora = new ManejadorBitacoraSistema();
        try {
            boolean resp = bitacora.registrarErrorSistema(proceso, mensaje);
            return resp;
        } catch (Exception e) {
            return false;
        }
    }

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
