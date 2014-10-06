package simuni.classes.EN;

import java.util.ArrayList;
import java.util.Date;

public class Activos {
    private String pa_identificadorActivo;
    private String pa_Descripcion;
    private Date pd_fechaCompra;
    private double pd_precioCompra;
    private String pa_tipoPago;
    private int pa_Estado;
    private int pa_tipoActivo;
    private ArrayList<imagenActivo> po_imagenActivo = new ArrayList<imagenActivo>();
    private String pa_Observaciones;
    
    public Activos(){
        
    }
    public String getPa_identificadorActivo() {
        return pa_identificadorActivo;
    }
    public void setPa_identificadorActivo(String pa_identificadorActivo) {
        this.pa_identificadorActivo = pa_identificadorActivo;
    }
    public String getPa_Descripcion() {
        return pa_Descripcion;
    }
    public void setPa_Descripcion(String pa_Descripcion) {
        this.pa_Descripcion = pa_Descripcion;
    }
    public Date getPd_fechaCompra() {
        return pd_fechaCompra;
    }
    public void setPd_fechaCompra(Date pd_fechaCompra) {
        this.pd_fechaCompra = pd_fechaCompra;
    }
    public double getPd_precioCompra() {
        return pd_precioCompra;
    }
    public void setPd_precioCompra(double pd_precioCompra) {
        this.pd_precioCompra = pd_precioCompra;
    }
    public String getPa_tipoPago() {
        return pa_tipoPago;
    }
    public void setPa_tipoPago(String pa_tipoPago) {
        this.pa_tipoPago = pa_tipoPago;
    }
    public int getPa_Estado() {
        return pa_Estado;
    }
    public void setPa_Estado(int pa_Estado) {
        this.pa_Estado = pa_Estado;
    }
    public int getPa_tipoActivo() {
        return pa_tipoActivo;
    }
    public void setPa_tipoActivo(int pa_tipoActivo) {
        this.pa_tipoActivo = pa_tipoActivo;
    }
    public ArrayList<imagenActivo> getPo_imagenActivo() {
        return po_imagenActivo;
    }
    public void setPo_imagenActivo(ArrayList<imagenActivo> po_imagenActivo) {
        this.po_imagenActivo = po_imagenActivo;
    }
    public String getPa_Observaciones() {
        return pa_Observaciones;
    }
    public void setPa_Observaciones(String pa_Observaciones) {
        this.pa_Observaciones = pa_Observaciones;
    }
}
