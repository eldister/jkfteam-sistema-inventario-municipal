package simuni.classes.EN;

import java.util.ArrayList;
import java.util.Date;
/**
 * 
 * @author Keylin
 * En la clase activos se estará todos los campos que se requieren para poder realizar la gestión de un activo al sistema, 
 * y posteriormentese realizarán los metodos get y set. 
 * 
 * @
 */
public class Activos {
    
    private String pa_identificadorActivo; 
    private String pa_Descripcion;
    private Date pd_fechaCompra;
    private double pd_precioCompra;
    private int pa_tipoPago;
    private String pa_Estado="ACTIVO";
    private int pa_tipoActivo;
    private ArrayList<imagenActivo> po_imagenActivo = new ArrayList<imagenActivo>();
    private String pa_Observaciones;
    
    public Activos(){
        
    }
    public String getPa_identificadorActivo() {
        return pa_identificadorActivo;
    }
    /**
     * 
     * poner el identificador de activo
     * @param pa_identificadorActivo
     * @since 1.0 
     */
    public void setPa_identificadorActivo(String pa_identificadorActivo) {
        this.pa_identificadorActivo = pa_identificadorActivo;
    }
    
    /**
     * Obtener la descripción del activo 
     * @return descripción del activo
     * @since 1.0
     */
    public String getPa_Descripcion() {
        return pa_Descripcion;
    }
    
    /**
     * poner la descripción
     * @param pa_Descripcion
     * @since 1.0
     */
    public void setPa_Descripcion(String pa_Descripcion) {
        this.pa_Descripcion = pa_Descripcion;
    }
    
    /**
     * obtener la fecha en la que se realizó la compra del activo
     * @return fecha de la compra
     */
    public Date getPd_fechaCompra() {
        return pd_fechaCompra;
    }
    
    /**
     * poner la fecha de la compra del activo 
     * @param pd_fechaCompra 
     * @since 1.0
     */
    public void setPd_fechaCompra(Date pd_fechaCompra) {
        this.pd_fechaCompra = pd_fechaCompra;
    }
    
    /**
     * obtiene el precio de la compra de un nuevo activo
     * @return precio de la compra
     * @since 1.0
     */
    public double getPd_precioCompra() {
        return pd_precioCompra;
    }
    
    /**
     * poner el precio de la compra de un nuevo activ 
     * @param pd_precioCompra 
     * @since 1.0
     */
    public void setPd_precioCompra(double pd_precioCompra) {
        this.pd_precioCompra = pd_precioCompra;
    }
    
    /**
     * obtener el tipo de pago en que se realizó la compra 
     * @return tipo pago 
     */
    public int getPa_tipoPago() {
        return pa_tipoPago;
    }
    
    /**
     * Poner el tipo de pago en que se realizó la compra 
     * @param pa_tipoPago 
     * since 1.0
     */
    public void setPa_tipoPago(int pa_tipoPago) {
        this.pa_tipoPago = pa_tipoPago;
    }
    
    /**
     * Obtener el estado en el cual se encuentra el activo 
     * @return  estado de activo
     * @since 1.0
     */
    public String getPa_Estado() {
        return pa_Estado;
    }
    
    /**
     * poner el estado en el que se encuentra el activo
     * @param pa_Estado 
     * @since  1.0
     */
    public void setPa_Estado(String pa_Estado) {
        this.pa_Estado = pa_Estado;
    }
    
    /**
     * Obtener cual es el tipo de activo 
     * @return tipo del activo 
     * @since 1.0
     */
    public int getPa_tipoActivo() {
        return pa_tipoActivo;
    }
    
    /**
     * poner cual tipo de activo es
     * @param pa_tipoActivo 
     * @since 1.0
     */
    public void setPa_tipoActivo(int pa_tipoActivo) {
        this.pa_tipoActivo = pa_tipoActivo;
    }
    
    /**
     * obtener la imagen del activo 
     * @return imagen del activo 
     * @since 1.0
     */
    public ArrayList<imagenActivo> getPo_imagenActivo() {
        return po_imagenActivo;
    }
    
    /**
     * poner la imagen del activo 
     * @param po_imagenActivo 
     * @since 1.0
     */
    public void setPo_imagenActivo(ArrayList<imagenActivo> po_imagenActivo) {
        this.po_imagenActivo = po_imagenActivo;
    }
    
    /**
     * Obtener las reservaciones del activo 
     * @return observaciones del activo
     * @since 1.0
     */
    public String getPa_Observaciones() {
        return pa_Observaciones;
    }
    
    /**
     * poner las observaciones del activo
     * @param pa_Observaciones 
     * @since 1.0
     */
    public void setPa_Observaciones(String pa_Observaciones) {
        this.pa_Observaciones = pa_Observaciones;
    }
    
    /**
     * agrega la imagen de un activo
     * @param imagen 
     * @since 1.0
     */
    public void agregarImagen(imagenActivo imagen){
        this.po_imagenActivo.add(imagen);
    }
}
