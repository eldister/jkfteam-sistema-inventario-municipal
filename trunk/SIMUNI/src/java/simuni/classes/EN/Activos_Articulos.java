package simuni.classes.EN;

import java.util.Date;

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
    public double getPb_porcentajeRescate() {
        return pb_porcentajeRescate;
    }
    public void setPb_porcentajeRescate(double pb_porcentajeRescate) {
        this.pb_porcentajeRescate = pb_porcentajeRescate;
    }
    public int getPn_anioFabricacion() {
        return pn_anioFabricacion;
    }
    public void setPn_anioFabricacion(int pn_anioFabricacion) {
        this.pn_anioFabricacion = pn_anioFabricacion;
    }
    public String getPa_modelo() {
        return pa_modelo;
    }
    public void setPa_modelo(String pa_modelo) {
        this.pa_modelo = pa_modelo;
    }
    public double getPb_depreciacion() {
        return pb_depreciacion;
    }
    public void setPb_depreciacion(double pb_depreciacion) {
        this.pb_depreciacion = pb_depreciacion;
    }
    public Date getPd_puestaOperacion() {
        return pd_puestaOperacion;
    }
    public void setPd_puestaOperacion(Date pd_puestaOperacion) {
        this.pd_puestaOperacion = pd_puestaOperacion;
    }
    public Departamento getPo_depto() {
        return po_depto;
    }
    public void setPo_depto(Departamento po_depto) {
        this.po_depto = po_depto;
    }
    public String getPa_codigoProveedor() {
        return pa_codigoProveedor;
    }
    public void setPa_codigoProveedor(String pa_codigoProveedor) {
        this.pa_codigoProveedor = pa_codigoProveedor;
    }
    public String getPa_marca() {
        return pa_marca;
    }
    public void setPa_marca(String pa_marca) {
        this.pa_marca = pa_marca;
    }
    public int getPn_aniosutilidadactivo() {
        return pn_aniosutilidadactivo;
    }
    public void setPn_aniosutilidadactivo(int pn_aniosutilidadactivo) {
        this.pn_aniosutilidadactivo = pn_aniosutilidadactivo;
    }
    public double getPb_porcentajeDepreciacion() {
        return pb_porcentajeDepreciacion;
    }
    public void setPb_porcentajeDepreciacion(double pb_porcentajeDepreciacion) {
        this.pb_porcentajeDepreciacion = pb_porcentajeDepreciacion;
    }

    /**
     * @return the pa_nombreproveedor
     */
    public String getPa_nombreproveedor() {
        return pa_nombreproveedor;
    }

    /**
     * @param pa_nombreproveedor the pa_nombreproveedor to set
     */
    public void setPa_nombreproveedor(String pa_nombreproveedor) {
        this.pa_nombreproveedor = pa_nombreproveedor;
    }
}
