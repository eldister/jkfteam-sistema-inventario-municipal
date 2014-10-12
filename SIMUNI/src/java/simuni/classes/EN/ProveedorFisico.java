package simuni.classes.EN;

import java.sql.Date;
import java.util.ArrayList;

public class ProveedorFisico extends Persona{
    private String pa_telefonoFijo;
    private String pa_telefonoMovil;
    private String pa_fax;
    private String pa_correoElectronico;
    private String pa_sitioWeb;
    private int pn_apartadoPostal;
    private String pa_numeroCuenta;
    private String pa_banco;
    private String pa_nombreCompania;
    private String pa_direccionCompania;
    private int pb_estadoprovedor;
    private Date pd_fecharegistro;
    
    private ArrayList<Documentos>po_documentos ;
    
    public ProveedorFisico(){
        super();
    }
    public String getPa_telefonoFijo() {
        return pa_telefonoFijo;
    }
    public void setPa_telefonoFijo(String pa_telefonoFijo) {
        this.pa_telefonoFijo = pa_telefonoFijo;
    }
    public String getPa_telefonoMovil() {
        return pa_telefonoMovil;
    }
    public void setPa_telefonoMovil(String pa_telefonoMovil) {
        this.pa_telefonoMovil = pa_telefonoMovil;
    }
    public String getPa_fax() {
        return pa_fax;
    }
    public void setPa_fax(String pa_fax) {
        this.pa_fax = pa_fax;
    }
    public String getPa_correoElectronico() {
        return pa_correoElectronico;
    }
    public void setPa_correoElectronico(String pa_correoElectronico) {
        this.pa_correoElectronico = pa_correoElectronico;
    }
    public String getPa_sitioWeb() {
        return pa_sitioWeb;
    }
    public void setPa_sitioWeb(String pa_sitioWeb) {
        this.pa_sitioWeb = pa_sitioWeb;
    }
    public int getPn_apartadoPostal() {
        return pn_apartadoPostal;
    }
    public void setPn_apartadoPostal(int pn_apartadoPostal) {
        this.pn_apartadoPostal = pn_apartadoPostal;
    }
    public String getPa_numeroCuenta() {
        return pa_numeroCuenta;
    }
    public void setPa_numeroCuenta(String pa_numeroCuenta) {
        this.pa_numeroCuenta = pa_numeroCuenta;
    }
    public String getPa_banco() {
        return pa_banco;
    }
    public void setPa_banco(String pa_banco) {
        this.pa_banco = pa_banco;
    }
    public String getPa_nombreCompania() {
        return pa_nombreCompania;
    }
    public void setPa_nombreCompania(String pa_nombreCompania) {
        this.pa_nombreCompania = pa_nombreCompania;
    }
    public String getPa_direccionCompania() {
        return pa_direccionCompania;
    }
    public void setPa_direccionCompania(String pa_direccionCompania) {
        this.pa_direccionCompania = pa_direccionCompania;
    }
    public ArrayList<Documentos> getPo_documentos() {
        return po_documentos;
    }
    public void setPo_documentos(ArrayList<Documentos> po_documentos) {
        this.po_documentos = po_documentos;
    }

    /**
     * @return the pb_estadoprovedor
     */
    public int getPb_estadoprovedor() {
        return pb_estadoprovedor;
    }

    /**
     * @param pb_estadoprovedor the pb_estadoprovedor to set
     */
    public void setPb_estadoprovedor(int pb_estadoprovedor) {
        this.pb_estadoprovedor = pb_estadoprovedor;
    }

    /**
     * @return the pd_fecharegistro
     */
    public Date getPd_fecharegistro() {
        return pd_fecharegistro;
    }

    /**
     * @param pd_fecharegistro the pd_fecharegistro to set
     */
    public void setPd_fecharegistro(Date pd_fecharegistro) {
        this.pd_fecharegistro = pd_fecharegistro;
    }
    
}
