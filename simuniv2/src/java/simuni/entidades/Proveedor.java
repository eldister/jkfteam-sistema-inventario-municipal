/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

import java.util.Date;

/**
 *
 * @author FchescO
 */
public class Proveedor extends Persona {

    private String telFijo="";
    private String telMovil="";
    private String telOfic="";
    private String telFax="";
    private String email="";
    private String paginaWeb="";
    private String apartadoPostal="";
    private String nombreBanco="";
    private String numeroCuenta="";
    private String nomEmpresa="";
    private String dirEmpresa="";
    private String tipoProveedor="";
    private String estado="";
    private Date fechaRegistro;  
    private Date fechaUltimaModificacion;
    private Persona representanteLegal;


public String getNombreRepresentanteLegal(){
    return this.representanteLegal!=null?this.representanteLegal.getNombre():"";
}
public String getPrimerApellidoRepresentanteLegal(){
    return this.representanteLegal!=null?this.representanteLegal.getPrimerApellido():"";
}
public String getSegundoApellidoRepresentanteLegal(){
    return this.representanteLegal!=null?this.representanteLegal.getSegundoApellido():"";
}

public String getCedulaRepresentanteLegal(){
    return this.representanteLegal!=null?this.representanteLegal.getCedula():"";
}
    /**
     * @return the telFijo
     */
    public String getTelFijo() {
        return telFijo;
    }

    /**
     * @param telFijo the telFijo to set
     */
    public void setTelFijo(String telFijo) {
        this.telFijo = telFijo;
    }

    /**
     * @return the telMovil
     */
    public String getTelMovil() {
        return telMovil;
    }

    /**
     * @param telMovil the telMovil to set
     */
    public void setTelMovil(String telMovil) {
        this.telMovil = telMovil;
    }

    /**
     * @return the telOfic
     */
    public String getTelOfic() {
        return telOfic;
    }

    /**
     * @param telOfic the telOfic to set
     */
    public void setTelOfic(String telOfic) {
        this.telOfic = telOfic;
    }

    /**
     * @return the telFax
     */
    public String getTelFax() {
        return telFax;
    }

    /**
     * @param telFax the telFax to set
     */
    public void setTelFax(String telFax) {
        this.telFax = telFax;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the paginaWeb
     */
    public String getPaginaWeb() {
        return paginaWeb;
    }

    /**
     * @param paginaWeb the paginaWeb to set
     */
    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    /**
     * @return the apartadoPostal
     */
    public String getApartadoPostal() {
        return apartadoPostal;
    }

    /**
     * @param apartadoPostal the apartadoPostal to set
     */
    public void setApartadoPostal(String apartadoPostal) {
        this.apartadoPostal = apartadoPostal;
    }

    /**
     * @return the nombreBanco
     */
    public String getNombreBanco() {
        return nombreBanco;
    }

    /**
     * @param nombreBanco the nombreBanco to set
     */
    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    /**
     * @return the numeroCuenta
     */
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    /**
     * @param numeroCuenta the numeroCuenta to set
     */
    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    /**
     * @return the nomEmpresa
     */
    public String getNomEmpresa() {
        return nomEmpresa;
    }

    /**
     * @param nomEmpresa the nomEmpresa to set
     */
    public void setNomEmpresa(String nomEmpresa) {
        this.nomEmpresa = nomEmpresa;
    }

    /**
     * @return the dirEmpresa
     */
    public String getDirEmpresa() {
        return dirEmpresa;
    }

    /**
     * @param dirEmpresa the dirEmpresa to set
     */
    public void setDirEmpresa(String dirEmpresa) {
        this.dirEmpresa = dirEmpresa;
    }

    /**
     * @return the representanteLegal
     */
    public Persona getRepresentanteLegal() {
        return representanteLegal;
    }

    /**
     * @param representanteLegal the representanteLegal to set
     */
    public void setRepresentanteLegal(Persona representanteLegal) {
        this.representanteLegal = representanteLegal;
    }
    /**
     * 
     * @param cedula
     * @param nombre
     * @param primerapellido
     * @param segundoapellido 
     */
    public void setRepresentanteLegal(String cedula,String nombre, String primerapellido,String segundoapellido){
       if(this.representanteLegal==null){
           this.representanteLegal=new Persona();
       }
        this.representanteLegal.setCedula(cedula);
        this.representanteLegal.setNombre(nombre);
        this.representanteLegal.setPrimerApellido(primerapellido);
        this.representanteLegal.setSegundoApellido(segundoapellido);
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the tipoProveedor
     */
    public String getTipoProveedor() {
        return tipoProveedor;
    }

    /**
     * @param tipoProveedor the tipoProveedor to set
     */
    public void setTipoProveedor(String tipoProveedor) {
        this.tipoProveedor = tipoProveedor;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the fechaUltimaModificacion
     */
    public Date getFechaUltimaModificacion() {
        return fechaUltimaModificacion;
    }

    /**
     * @param fechaUltimaModificacion the fechaUltimaModificacion to set
     */
    public void setFechaUltimaModificacion(Date fechaUltimaModificacion) {
        this.fechaUltimaModificacion = fechaUltimaModificacion;
    }
    
}
