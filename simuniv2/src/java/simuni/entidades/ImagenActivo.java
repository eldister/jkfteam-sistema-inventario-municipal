/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author FchescO
 */
public class ImagenActivo {

    private int codigoImagen;
    private Date fechaSubida;
    private String urldocumento;
    private String codigoActivo;
    private String nombreArchivo;
    private String serverArchivo;
    private InputStream streamArchivo;
    private String pathDocumento;

    /**
     * @return un string de como se va a presentar la información
     */
    @Override
    public String toString() {
        return fechaSubida+urldocumento+codigoActivo+nombreArchivo+serverArchivo+pathDocumento;
    }

    /**
     * @return the codigoImagen
     */
    public int getCodigoImagen() {
        return codigoImagen;
    }

    /**
     * @param codigoImagen the codigoImagen to set
     */
    public void setCodigoImagen(int codigoImagen) {
        this.codigoImagen = codigoImagen;
    }

    /**
     * @return the fechaSubida
     */
    public Date getFechaSubida() {
        return fechaSubida;
    }

    /**
     * @param fechaSubida the fechaSubida to set
     */
    public void setFechaSubida(Date fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    /**
     * @return the urldocumento
     */
    public String getUrldocumento() {
        return urldocumento;
    }

    /**
     * @param urldocumento the urldocumento to set
     */
    public void setUrldocumento(String urldocumento) {
        this.urldocumento = urldocumento;
    }

    /**
     * @return the codigoActivo
     */
    public String getCodigoActivo() {
        return codigoActivo;
    }

    /**
     * @param codigoActivo the codigoActivo to set
     */
    public void setCodigoActivo(String codigoActivo) {
        this.codigoActivo = codigoActivo;
    }

    /**
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * @return the serverArchivo
     */
    public String getServerArchivo() {
        return serverArchivo;
    }

    /**
     * @param serverArchivo the serverArchivo to set
     */
    public void setServerArchivo(String serverArchivo) {
        this.serverArchivo = serverArchivo;
    }

    /**
     * @return the streamArchivo
     */
    public InputStream getStreamArchivo() {
        return streamArchivo;
    }

    /**
     * @param streamArchivo the streamArchivo to set
     */
    public void setStreamArchivo(InputStream streamArchivo) {
        this.streamArchivo = streamArchivo;
    }

    /**
     * @return the pathDocumento
     */
    public String getPathDocumento() {
        return pathDocumento;
    }

    /**
     * @param pathDocumento the pathDocumento to set
     */
    public void setPathDocumento(String pathDocumento) {
        this.pathDocumento = pathDocumento;
    }
}
