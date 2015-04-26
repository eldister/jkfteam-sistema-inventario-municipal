/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author FchescO
 */
public class EntregaDocumento {

    private int codigo=0;
    private String cedulaproveedor="";
    private Date fecha;
    private String obseravaciones="";
    private String estadoentrega="Aceptada";
    private ArrayList<Documento> documentos;

    public EntregaDocumento() {
        
    }

    
    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the cedulaproveedor
     */
    public String getCedulaproveedor() {
        return cedulaproveedor;
    }

    /**
     * @param cedulaproveedor the cedulaproveedor to set
     */
    public void setCedulaproveedor(String cedulaproveedor) {
        this.cedulaproveedor = cedulaproveedor;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the obseravaciones
     */
    public String getObseravaciones() {
        return obseravaciones;
    }

    /**
     * @param obseravaciones the obseravaciones to set
     */
    public void setObseravaciones(String obseravaciones) {
        this.obseravaciones = obseravaciones;
    }

    /**
     * @return the documentos
     */
    public ArrayList<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * @param documentos the documentos to set
     */
    public void setDocumentos(ArrayList<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * @param documento the document to add
     * @return bool value
     */
    public boolean addDocumento(Documento documento) {
        if (this.documentos == null) {
            this.documentos = new ArrayList<Documento>();
        }

        return this.documentos.add(documento);

    }

    /**
     * 
     * @param documento the document to remove
     * @return bool value
     */
    public boolean removeDocumento(Documento documento) {
        if (this.documentos == null) {
            return false;
        }
        return this.documentos.remove(documento);
    }

    /**
     * @return the estadoentrega
     */
    public String getEstadoentrega() {
        return estadoentrega;
    }

    /**
     * @param estadoentrega the estadoentrega to set
     */
    public void setEstadoentrega(String estadoentrega) {
        this.estadoentrega = estadoentrega;
    }

}
