/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.intefaces;

import java.util.ArrayList;

/**
 *
 * @author FchescO
 */
public interface IReporteador {
    public ArrayList<String[]> obtenerDatosReporte();
    public ArrayList<String[]> obtenerDatosReporte(java.sql.Date fini,java.sql.Date ffin);

}
