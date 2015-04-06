/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.clases.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.GrillaBase;
import simuni.enums.Recursos;

/**
 *
 * @author Keylin
 */
public class MostradorPrestamo {
        /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rsprestamo ResultSet de Prestamo
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String Renderizar(ResultSet rsprestamo, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/prestamo?proceso=listado");
        gril.setUrlagregaropcionagregar(Recursos.Servers.MAINSERVER + "/prestamo?proceso=nuevo");
        gril.setUsarAgregarself(false);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(true);
        //gril.setUsarMostrarOcultos(true);
        gril.setOpcionver(false);
        try {
            return gril.renderizar(rsprestamo, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
    
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rsprestamo ResultSet de Prestamo
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String Renderizar_Asinc(ResultSet rsprestamo, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/prestamo?proceso=listado_asinc");
        gril.setUsarControles(false);

        
      
        try {
            return gril.renderizar(rsprestamo, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }    
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido. En esta
     * ocasión solo devuelve el cuerpo puro de la tabla, y no asi sus controles
     * de busqueda, agregar, etc.
     *
     * @param rsprestamo ResultSet de Prestamo
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String RenderizarActualizacion(ResultSet rsprestamo, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/prestamo?proceso=listado");
        gril.setOpciommostrarfiltro(false);
        //gril.setOpcionimprimir(true);
        gril.setOpcionver(false);
        gril.setUsarMostrarOcultos(true);
        try {
            return gril.renderizarSoloCuerpoTabla(rsprestamo, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
    
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido. En esta
     * ocasión solo devuelve el cuerpo puro de la tabla, y no asi sus controles
     * de busqueda, agregar, etc.
     *
     * @param rsprestamo ResultSet de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String RenderizarActualizacion_Asinc(ResultSet rsprestamo, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/prestamo?proceso=listado_asinc");
        gril.setUsarControles(false);
        try {
            return gril.renderizarSoloCuerpoTabla(rsprestamo, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
    
}
