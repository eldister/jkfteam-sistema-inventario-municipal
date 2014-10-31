package simuni.classes.LN;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.classes.AD.ManejadorDatosActivos;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.imagenActivo;

/**
 *estaclasde es una cosa de prueba
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorActivos {

    /**
     * Este método agrega un nuevo activo de articulo
     * @param to_articulo el articulo a ingresar
     * @return verdadero en caso de que se halla ingresado correctamente o false si no lo fue
     * @throws Exception Si hay error se registra en bd 
     * @since 1.0
     */
    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) throws Exception {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();

            manejadordatosactivos.agregarActivoArticulo(to_articulo);
            UtilidadesServlet.registrarProcesoSistema("agregarActivoArticulo", "Se inserto con éxito el activo");
       
        if(to_articulo == null)
        {
        return false;
        }else if(to_articulo.getPa_identificadorActivo()==null){
        return false;
        }
            //porcentaje
        if(to_articulo == null){
        return false;
        }else if(to_articulo.getPb_porcentajeDepreciacion() >= 0 && to_articulo.getPb_porcentajeDepreciacion()<=100)
        {
        return false;
        }
        
        //formato de la fecha 
        if(to_articulo == null)
        {
            return false;
        } else if (to_articulo.getPd_fechaCompra() != null){
         
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.format(to_articulo.getPd_fechaCompra());      
        }
         
        if(to_articulo == null)
        {
        return false;
        } else if (to_articulo.getPo_imagenActivo() == null){
        return false;
        }
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("agregarActivoArticulo", e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     *Este método es para la actualización de la información de los activos articulos almacenados en la base de datos
     * @param to_articulo el artículo al cual se le va a modificar los datos
     * @return true en caso de que el articulo halla sido actualizado con exito o false en caso de que no
     * @since 1.0
     */
    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            try {        
        if(to_articulo == null)
        {
        return false;
        }else if(to_articulo.getPa_identificadorActivo()==null){
        return false;
        }
            //porcentaje
        if(to_articulo == null){
        return false;
        }else if(to_articulo.getPb_porcentajeDepreciacion() >= 0 && to_articulo.getPb_porcentajeDepreciacion()<=100)
        {
        return false;
        }
        
        //formato de la fecha 
        if(to_articulo == null)
        {
            return false;
        } else if (to_articulo.getPd_fechaCompra() != null){
         
            SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            formatoFecha.setLenient(false);
            formatoFecha.format(to_articulo.getPd_fechaCompra());      
        }
         
        if(to_articulo == null)
        {
        return false;
        } else if (to_articulo.getPo_imagenActivo() == null){
        return false;
        }
                
                return manejadordatosactivos.modificarActivoArticulo(to_articulo);
            } catch (Exception ex) {
                Logger.getLogger(ManejadorActivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            UtilidadesServlet.registrarProcesoSistema("modificarActivoArticulo", "Se modificó con éxito el activo");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("modificarActivoArticulo", e.getMessage());
        }
        return false;
    }

    /**
     * Este método desactiva un registro en la base de datos especificado por el código del activo
     * @param ta_codigoactivo código de identificación del artículo activo
     * @return true cuando el artículo activo ha sido desactivado con éxito o false en caso de que no
     * @since 1.0
     */
    public boolean desactivarActivoArticulo(String ta_codigoactivo) {
        try {
            
            if(ta_codigoactivo == null)
            {
            return false;
            } 
            
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            manejadordatosactivos.desactivarActivoArticulo(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("desactivarActivoArticulo", "Se desactivo con éxito el activo");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("desactivarActivoArticulo", e.getMessage());
            e.printStackTrace();
        }
        return true;
    }

    /**
     * Este método es utilizado para obtener una lista completa de los artículos activos registrados en la base de datos
     * @param desplazamiento 
     * @param paginacion 
     * Los dos anteriores parametros son limitar el total de resultados obtenidos por la consulta a la base de datos
     * @return un ArrayList con los artículos activos
     * @since 1.0
     */
    public ArrayList<Activos_Articulos> getListaArticulos(int desplazamiento, int paginacion) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            ArrayList<Activos_Articulos> articulos = manejadordatosactivos.getListaArticulos(desplazamiento, paginacion);
            UtilidadesServlet.registrarProcesoSistema("getListaArticulos", "Se obtuvo la lista de Articulos");
            return articulos;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getListaArticulos", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Este método obtiene el total registros de los artículos activos contenidos en la base de datos
     * @return el número total de registros
     * @since 1.0
     */
    public int getCantidadRegistrosActivosArticulos() {
        try {
            return new ManejadorDatosActivos().getCantidadRegistrosActivosArticulos();

        } catch (Exception ex) {
            //registrar error
            return 0;
        }
    }

    /**
     * Este método obtiene un activo articulo en especifico definido por el identificador del activo
     * en otras palabras es una busqueda de un activo articulo en particular utilizando su codigo
     * @param ta_codigoactivo es el codigo del activo articulo
     * @return el articulo especificado o un nulo en caso de que no se este registrado
     * @since 1.0
     */
    public Activos_Articulos getActivoArticulo(String ta_codigoactivo) {
        try {
            if(ta_codigoactivo == null){
             return null;   
            }
            
            
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            Activos_Articulos articulo = manejadordatosactivos.getActivoArticulo(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("getActivoArticulo", "Se obtuvo el Articulo del Activo");
            return articulo;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getActivoArticulo", e.getMessage());
        }
        return null;
    }

    /**
     * Este método obtiene una lista de las fotografías o imagenes de todos los activos registrados en la base de datos
     * @param tn_codigoactivo codigo del artículo
     * @return un ArrayList con todas las urls y nombres de las imagenes contenidos en la base de datos
     * retorna nulo en caso de que no halla podido obtener las imagenes
     * @since 1.0
     */
    public ArrayList<imagenActivo> getListaImagenesActivo(String tn_codigoactivo) {
        try {

            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            // ArrayList<imagenActivo> articulos = manejadordatosactivos.getListaImagenesActivo(tn_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("getListaImagenesActivo", "Se obtuvo la lista de Imagenes");
            return manejadordatosactivos.getListaImagenesActivo(tn_codigoactivo);
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getListaImagenesActivo", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Este método hace una validación para comprobar si un artículo activo existe actualmente en la base de datos
     * @param ta_codigoactivo código del artículo activo
     * @return verdadero en caso de que el activo exista o false en caso de que no
     * @since 1.0
     */
    public boolean isActivoExistente(String ta_codigoactivo) {
        try {
            if(ta_codigoactivo == null){
            return false;
            }   
            boolean ActivoExiste = new ManejadorDatosActivos().isActivoExistente(ta_codigoactivo);
            UtilidadesServlet.registrarProcesoSistema("isActivoExistente", "ActivoExistente");
            return ActivoExiste;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("isActivoExistente", e.getMessage());
        }
        return false;
    }

    /**
     * Este método obtiene un numero entero positivo del total de los activos registrados en la base de datos
     * @return siempre va a retornar 0
     * @since 1.0
     */
    public int getNumeroActivosRegistrados() {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            manejadordatosactivos.getNumeroActivosRegistrados();
            UtilidadesServlet.registrarProcesoSistema("getNumeroActivosRegistrados", "Numero de activos registrados");
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("getNumeroActivosRegistrados", e.getMessage());
        }
        return 0;
    }

    /**
     * Este método realiza una búsqueda de más de un artículo activo
     * @param query consulta de búsqueda para el LIKE en MySQL
     * Los siguientes parámetros son para obtener y limitar el total de resultados obtenidos por la consulta 
     * @param desplazamiento
     * @param paginacion
     * @return un ArrayList con todos los artículos activos encontrados, devuelve nulo en caso de que no 
     * se halla encontrado nada
     * @since 1.0
     */
    public ArrayList<Activos_Articulos> buscarActivosArticulos(String query,int desplazamiento, int paginacion) {
        try {
            ManejadorDatosActivos manejadordatosactivos = new ManejadorDatosActivos();
            ArrayList<Activos_Articulos> activos = manejadordatosactivos.buscarActivosArticulos(query,desplazamiento,paginacion);
            UtilidadesServlet.registrarProcesoSistema("buscarActivosArticulos", "La busqueda ha sido exitosa");
            return activos;
        } catch (Exception e) {
            UtilidadesServlet.registrarErrorSistema("buscarActivosArticulos", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
