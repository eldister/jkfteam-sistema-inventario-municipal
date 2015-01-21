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
 * al <strong>SolicitudesBaja</strong>.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class MostradorSolicitudesBaja {
    
    private boolean isAdmin=false;
    /**
     * Esta funcion recibe como parámetro un ResultSet y cantidad de paginas y
     * devuelve una cadena con el texto a mostrar que esta predefinido.
     *
     * @param rssolicitudbaja ResultSet de SolicitudesBaja
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String Renderizar(ResultSet rssolicitudbaja, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/solicitudbaja?proceso=obtener_solicitudes");

        gril.setOpcionagregar(false);
        gril.setUsarAgregarself(false);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        gril.setOpcionactualizar(false);
        gril.setOpcioneliminar(true);
        gril.setOpcionverdetalles(true);
        
        
        try {
            return gril.renderizar(rssolicitudbaja, cantidadpaginas);
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
     * @param rssolicitudbaja ResultSet de SolicitudesBaja
     * @param cantidadpaginas Para efectos de paginación, la cantidad de
     * páginas.
     * @return El texto a mostrar al usuario.
     * @since 1.0
     */
    public String RenderizarActualizacion(ResultSet rssolicitudbaja, int cantidadpaginas) {
        GrillaBase gril = new GrillaBase();
        gril.setUrlpaginacionlink(Recursos.Servers.MAINSERVER + "/solicitudbaja?proceso=obtener_solicitudes");
        gril.setOpcionagregar(false);
        gril.setUsarAgregarself(false);
        gril.setOpciommostrarfiltro(false);
        gril.setOpcionimprimir(false);
        gril.setOpcionver(false);
        gril.setOpcionactualizar(false);
        gril.setOpcioneliminar(true);
        gril.setOpcionverdetalles(true);
        try {
            return gril.renderizarSoloCuerpoTabla(rssolicitudbaja, cantidadpaginas);
        } catch (SQLException ex) {
            return "error " + ex.getMessage();
        }
    }

    /**
     * @return the isAdmin
     */
    public boolean isIsAdmin() {
        return isAdmin;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}