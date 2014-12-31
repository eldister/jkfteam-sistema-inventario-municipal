package simuni.classes.EN;

import java.sql.Date;
import java.util.ArrayList;

/**
 * En esta clase estarán los datos correspondientes al proveedor físico de la municipalidad, 
 * con todos los campos necesirios para su debido registro y mantenimiento
 * @author Keylin
 */
public class ProveedorFisico extends Persona{
    private String pa_telefonoFijo;
    private String pa_telefonoMovil;
    private String pa_telefonoOficina;
    private String pa_fax;
    private String pa_correoElectronico;
    private String pa_sitioWeb;
    private int pn_apartadoPostal;
    private String pa_numeroCuenta;
    private String pa_banco;
    private String pa_nombreCompania;
    private String pa_direccionCompania;
    private String pa_estadoprovedor;
    private Date pd_fecharegistro;
    
    private ArrayList<Documentos>po_documentos ;
    
    public ProveedorFisico(){
        super();
    }
    
    /**
     * obtener el numero de télefono fijo o de oficina 
     * @return telefono fijo
     */
    public String getPa_telefonoFijo() {
        return pa_telefonoFijo;
    }
    
    /**
     * poner el numero de télefono fijo o de oficina
     * @param pa_telefonoFijo 
     */
    public void setPa_telefonoFijo(String pa_telefonoFijo) {
        this.pa_telefonoFijo = pa_telefonoFijo;
    }
    
    /**
     * obtener el número de teléfono movil 
     * @return télefono movil
     */
    public String getPa_telefonoMovil() {
        return pa_telefonoMovil;
    }
    
    /**
     * poner el número de teléfono movil
     * @param pa_telefonoMovil 
     */
    public void setPa_telefonoMovil(String pa_telefonoMovil) {
        this.pa_telefonoMovil = pa_telefonoMovil;
    }
    
    /**
     * obtener el fax del proveedor
     * @return fax
     */
    public String getPa_fax() {
        return pa_fax;
    }
    
    /**
     * poner el fax del proveedor
     * @param pa_fax 
     */
    public void setPa_fax(String pa_fax) {
        this.pa_fax = pa_fax;
    }
    
    /**
     * obtener el correo electrónico 
     * @return correo electrónico
     */
    public String getPa_correoElectronico() {
        return pa_correoElectronico;
    }
    
    /**
     * poner el correo electrónico
     * @param pa_correoElectronico 
     */
    public void setPa_correoElectronico(String pa_correoElectronico) {
        this.pa_correoElectronico = pa_correoElectronico;
    }
    
    /**
     * obtener el sitio web 
     * @return sitioWeb
     */
    public String getPa_sitioWeb() {
        return pa_sitioWeb;
    }
    
    /**
     * poner el sitio web
     * @param pa_sitioWeb 
     */
    public void setPa_sitioWeb(String pa_sitioWeb) {
        this.pa_sitioWeb = pa_sitioWeb;
    }
    
    /**
     * obtener el apartado postal
     * @return apartado postal 
     */
    public int getPn_apartadoPostal() {
        return pn_apartadoPostal;
    }
    
    /**
     * poner el apartado postal
     * @param pn_apartadoPostal 
     */
    public void setPn_apartadoPostal(int pn_apartadoPostal) {
        this.pn_apartadoPostal = pn_apartadoPostal;
    }
    
    /**
     * obtener el número de cuenta 
     * @return número de cuenta
     */
    public String getPa_numeroCuenta() {
        return pa_numeroCuenta;
    }
    
    /**
     * poner el número de cuenta 
     * @param pa_numeroCuenta 
     */
    public void setPa_numeroCuenta(String pa_numeroCuenta) {
        this.pa_numeroCuenta = pa_numeroCuenta;
    }
    
    /**
     * obtener el nombre del banco
     * @return nombre del banco
     */
    public String getPa_banco() {
        return pa_banco;
    }
    
    /**
     * poner el nombre del banco
     * @param pa_banco 
     */
    public void setPa_banco(String pa_banco) {
        this.pa_banco = pa_banco;
    }
    
    /**
     * obtener el nombre de la compañia 
     * @return nombre de la compañia
     */
    public String getPa_nombreCompania() {
        return pa_nombreCompania;
    }
    
    /**
     * poner el nombre de la compañia
     * @param pa_nombreCompania 
     */
    public void setPa_nombreCompania(String pa_nombreCompania) {
        this.pa_nombreCompania = pa_nombreCompania;
    }
    
    /**
     * obtener la dirección de la compañía 
     * @return dirección de la compañía 
     */
    public String getPa_direccionCompania() {
        return pa_direccionCompania;
    }
    
    /**
     * poner la dirección de la compañía 
     * @param pa_direccionCompania 
     */
    public void setPa_direccionCompania(String pa_direccionCompania) {
        this.pa_direccionCompania = pa_direccionCompania;
    }
    
    /**
     * obtener los documentos 
     * @return documentos 
     */
    public ArrayList<Documentos> getPo_documentos() {
        return po_documentos;
    }
    
    /**
     * poner los documentos 
     * @param po_documentos 
     */
    public void setPo_documentos(ArrayList<Documentos> po_documentos) {
        this.po_documentos = po_documentos;
    }

    /** obtener el estado del proveedor
     * @return the pb_estadoprovedor
     */
    public String getPa_estadoprovedor() {
        return pa_estadoprovedor;
    }

    /**
     * poner el estado del proveedor
     * @param pa_estadoprovedor 
     */
    public void setPa_estadoprovedor(String pa_estadoprovedor) {
        this.pa_estadoprovedor = pa_estadoprovedor;
    }

    /**
     * obtener la fecha de registro de proveedor
     * @return the pd_fecharegistro
     */
    public Date getPd_fecharegistro() {
        return pd_fecharegistro;
    }

    /**
     * poner la fecha de registro de proveedor
     * @param pd_fecharegistro the pd_fecharegistro to set
     */
    public void setPd_fecharegistro(Date pd_fecharegistro) {
        this.pd_fecharegistro = pd_fecharegistro;
    }
    
    /**
     * obtener el número de  teléfono de oficina
     * @return número de  teléfono de oficina
     */
    public String getPa_telefonoOficina(){
        return this.pa_telefonoOficina;
    }
    
    /**
     * poner el número de  teléfono de oficina
     * @param pa_telefonoOficina 
     */
    public void setPa_telefonoOficina(String pa_telefonoOficina){
        this.pa_telefonoOficina = pa_telefonoOficina;
    }
}
