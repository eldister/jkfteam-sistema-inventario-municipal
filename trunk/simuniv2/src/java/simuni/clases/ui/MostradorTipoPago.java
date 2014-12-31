package simuni.clases.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import simuni.entidades.GrillaBase;
import simuni.enums.Recursos;
/**
 * Esta clase se encarga de preparar lo que se va a mostrar al pago,
 * específicamente en la parte de la grilla o tabla de datos. La mayoría de las
 * operaciones reciben un ResultSet para poder preparar su respuesta. Esos
 * ResultSet vienen de la base de datos con el nombre de las comlumnas ya
 * preparado. En este caso Esta clase se encarga de manejar lo correspondiente
 * al <strong>Tipo de Pago</strong>.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class MostradorTipoPago {
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rstipospago ResultSet de Tipo de Pago
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al pago.
     * @since 1.0
     */
    public String Renderizar(ResultSet rstipospago, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/tipopago?proceso=listado");
        gril.setUrlagregaropcionagregar("#");
        gril.setUsarAgregarself(true);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizar(rstipospago, cantidadpaginas);
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
     * @param rstipospago ResultSet de Tipo de Pago
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al pago.
     * @since 1.0
     */
    public String RenderizarActualizacion(ResultSet rstipospago, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/tipopago?proceso=listado");
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        try {
            return gril.renderizarSoloCuerpoTabla(rstipospago, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }
}