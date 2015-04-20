package simuni.intefaces;

import java.util.ArrayList;

/**
 * Interface para poder hacer los reportes. Contiene establecidos los métodos
 * por defecto para hacer los reportes en la clase cliente.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public interface IReporteador {

    /**
     * El reporte por defecto.
     *
     * @return Devuelve un ArrayList de valores en un vector String
     * @since 1.0
     */
    public ArrayList<String[]> obtenerDatosReporte();

    /**
     * El reporte que recibe un rango de fechas.
     *
     * @param fini fecha de inicio
     * @param ffin fecha final
     * @return Devuelve un ArrayList de valores en un vector String
     * @since 1.0
     */
    public ArrayList<String[]> obtenerDatosReporte(java.sql.Date fini, java.sql.Date ffin);

}
