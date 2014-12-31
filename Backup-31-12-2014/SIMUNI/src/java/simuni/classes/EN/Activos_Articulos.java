package simuni.classes.EN;

import java.util.Date;
/**
 * Los activos se dividen en diferentes tipos uno de ellos son los artículos.
 * En la clase de Activos_Artículos se definiran todos los campos necesarios para poder registrar el articulo de un activo.
 * tambien se realizaron sus diferentes métodos.
 * @author Keylin
 */
public class Activos_Articulos extends Activos{
    private double pb_porcentajeRescate;
    private int pn_anioFabricacion;
    private String pa_modelo;
    private double pb_depreciacion;
    private Date pd_puestaOperacion;
    private Departamento po_depto;
    private String pa_codigoProveedor;
    private String pa_nombreproveedor;
    private String pa_marca;
    private int pn_aniosutilidadactivo;
    private double pb_porcentajeDepreciacion;
    
    public Activos_Articulos(){
        super();
    }
    
    /**
     * obtener el porcetaje de rescate que tiene un artículo 
     * @return porcentaje de rescate 
     * @since 1.0
     */
    public double getPb_porcentajeRescate() {
        return pb_porcentajeRescate;
    }
    
    /**
     * pone el porcentaje de rescate de un artículo
     * @param pb_porcentajeRescate 
     */
    public void setPb_porcentajeRescate(double pb_porcentajeRescate) {
        this.pb_porcentajeRescate = pb_porcentajeRescate;
    }
    
    /**
     * Obtener el año en que se fabricó el artículo 
     * @return año de fabricación 
     */
    public int getPn_anioFabricacion() {
        return pn_anioFabricacion;
    }
    
    /**
     * poner el año de fabricación de un artículo
     * @param pn_anioFabricacion 
     */
    public void setPn_anioFabricacion(int pn_anioFabricacion) {
        this.pn_anioFabricacion = pn_anioFabricacion;
    }
    
    /**
     * Obtener el modelo que tiene el artículo 
     * @return modelo 
     */
    public String getPa_modelo() {
        return pa_modelo;
    }
    
    /**
     * poner el respectivo modelo del artículo 
     * @param pa_modelo 
     */
    public void setPa_modelo(String pa_modelo) {
        this.pa_modelo = pa_modelo;
    }
    
    /**
     * Obtener la de depreciación de un artículo
     * @return depreciación
     */
    public double getPb_depreciacion() {
        return pb_depreciacion;
    }
    
    /**
     * poner la depreciación de un artículo
     * @param pb_depreciacion 
     */
    public void setPb_depreciacion(double pb_depreciacion) {
        this.pb_depreciacion = pb_depreciacion;
    }
    
    /**
     * Obtener la fecha en el que el artículo inicia su operación 
     * @return puesta en Operación 
     */
    public Date getPd_puestaOperacion() {
        return pd_puestaOperacion;
    }
    
    /**
     * poner la fecha en la que el activo va a iniciar su operación 
     * @param pd_puestaOperacion 
     */
    public void setPd_puestaOperacion(Date pd_puestaOperacion) {
        this.pd_puestaOperacion = pd_puestaOperacion;
    }
    
    /**
     * obtener el departamento donde se encuentra el artículo
     * @return departamento
     */
    public Departamento getPo_depto() {
        return po_depto;
    }
    
    /**
     * poner departamento donde se encuentra el artículo
     * @param po_depto 
     */
    public void setPo_depto(Departamento po_depto) {
        this.po_depto = po_depto;
    }
    
    /**
     * obtener el código del proveedor 
     * @return código proovedor
     */
    public String getPa_codigoProveedor() {
        return pa_codigoProveedor;
    }
    
    /**
     * poner el código del proveedor
     * @param pa_codigoProveedor 
     */
    public void setPa_codigoProveedor(String pa_codigoProveedor) {
        this.pa_codigoProveedor = pa_codigoProveedor;
    }
    
    /**
     * obtener la marca del artículo
     * @return marca
     */
    public String getPa_marca() {
        return pa_marca;
    }
    
    /**
     * poner la marca del artículo
     * @param pa_marca 
     */
    public void setPa_marca(String pa_marca) {
        this.pa_marca = pa_marca;
    }
    
    /**
     * obtener los años de utilidad que tiene un activo
     * @return años de utilidad
     */
    public int getPn_aniosutilidadactivo() {
        return pn_aniosutilidadactivo;
    }
    
    /**
     * poder los años de utilidad que tiene un activo 
     * @param pn_aniosutilidadactivo 
     */
    public void setPn_aniosutilidadactivo(int pn_aniosutilidadactivo) {
        this.pn_aniosutilidadactivo = pn_aniosutilidadactivo;
    }
    
    /**
     * Obtener el porcentaje de depreciación de un artículo
     * @return porcentaje de depreciación 
     */
    public double getPb_porcentajeDepreciacion() {
        return pb_porcentajeDepreciacion;
    }
    
    /**
     * poner el porcentaje de depreciación 
     * @param pb_porcentajeDepreciacion 
     */
    public void setPb_porcentajeDepreciacion(double pb_porcentajeDepreciacion) {
        this.pb_porcentajeDepreciacion = pb_porcentajeDepreciacion;
    }

    /**
     * obtener el nombre del Proveedor
     * @return the pa_nombreproveedor
     */
    public String getPa_nombreproveedor() {
        return pa_nombreproveedor;
    }

    /**
     * poner el nombre del proveedor
     * @param pa_nombreproveedor the pa_nombreproveedor to set
     */
    public void setPa_nombreproveedor(String pa_nombreproveedor) {
        this.pa_nombreproveedor = pa_nombreproveedor;
    }
}
