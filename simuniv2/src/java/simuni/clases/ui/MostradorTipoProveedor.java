package simuni.clases.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.GrillaBase;
import simuni.enums.Recursos;
/**
 * Esta clase se encarga de preparar lo que se va a mostrar al proveedor,
 * específicamente en la parte de la grilla o tabla de datos. La mayoría de las
 * operaciones reciben un ResultSet para poder preparar su respuesta. Esos
 * ResultSet vienen de la base de datos con el nombre de las comlumnas ya
 * preparado. En este caso Esta clase se encarga de manejar lo correspondiente
 * al <strong>Tipo de Proveedor</strong>.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class MostradorTipoProveedor {
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rstiposproveedor ResultSet de Tipo de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al proveedor.
     * @since 1.0
     */
    public String Renderizar(ResultSet rstiposproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/tipoproveedor?proceso=listado");
        gril.setUrlagregaropcionagregar("#");
        gril.setUsarAgregarself(true);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizar(rstiposproveedor, cantidadpaginas);
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
     * @param rstiposproveedor ResultSet de Tipo de Proveedor
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al proveedor.
     * @since 1.0
     */
    public String RenderizarActualizacion(ResultSet rstiposproveedor, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/tipoproveedor?proceso=listado");
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizarSoloCuerpoTabla(rstiposproveedor, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
}