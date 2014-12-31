package simuni.clases.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.GrillaBase;
import simuni.enums.Recursos;
/**
 * Esta clase se encarga de preparar lo que se va a mostrar al usuario,
 * específicamente en la parte de la grilla o tabla de datos. La mayoría de las
 * operaciones reciben un ResultSet para poder preparar su respuesta. Esos
 * ResultSet vienen de la base de datos con el nombre de las comlumnas ya
 * preparado. En este caso Esta clase se encarga de manejar lo correspondiente
 * al <strong>Proveedor</strong>.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class MostradorProveedor {
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rsproveedor ResultSet de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String Renderizar(ResultSet rsproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
        gril.setUrlagregaropcionagregar(Recursos.Servers.MAINSERVER + "/proveedor?proceso=nuevo");
        gril.setUsarAgregarself(false);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(true);
        gril.setOpcionverdocumentos(true);
        gril.setUsarMostrarOcultos(true);
        gril.setOpcionver(false);
        try {
            return gril.renderizar(rsproveedor, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
    
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rsproveedor ResultSet de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String Renderizar_Asinc(ResultSet rsproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado_asinc");
        gril.setUsarControles(false);

        
      
        try {
            return gril.renderizar(rsproveedor, cantidadpaginas);
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
     * @param rsproveedor ResultSet de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String RenderizarActualizacion(ResultSet rsproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado");
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(true);
        gril.setOpcionverdocumentos(true);
        gril.setOpcionver(false);
        gril.setUsarMostrarOcultos(true);
        try {
            return gril.renderizarSoloCuerpoTabla(rsproveedor, cantidadpaginas);
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
     * @param rsproveedor ResultSet de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String RenderizarActualizacion_Asinc(ResultSet rsproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/proveedor?proceso=listado_asinc");
        gril.setUsarControles(false);
        try {
            return gril.renderizarSoloCuerpoTabla(rsproveedor, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }    
}