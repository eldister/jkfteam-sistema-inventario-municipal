/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

import java.io.InputStream;

/**
 *
 * @author FchescO
 */
public class Documento {
    private int codigodocumento;
    private String nombredocumento;
    private String serverdocumento;
    private String urldocumento;
    private int codigoentregadocumento;
    private InputStream streamarchivo;
    private String pathdocumento;
    /**
     * @return the codigodocumento
     */
    public int getCodigodocumento() {
        return codigodocumento;
    }

    /**
     * @param codigodocumento the codigodocumento to set
     */
    public void setCodigodocumento(int codigodocumento) {
        this.codigodocumento = codigodocumento;
    }

    /**
     * @return the nombredocumento
     */
    public String getNombredocumento() {
        return nombredocumento;
    }

    /**
     * @param nombredocumento the nombredocumento to set
     */
    public void setNombredocumento(String nombredocumento) {
        this.nombredocumento = nombredocumento;
    }

    /**
     * @return the serverdocumento
     */
    public String getServerdocumento() {
        return serverdocumento;
    }

    /**
     * @param serverdocumento the serverdocumento to set
     */
    public void setServerdocumento(String serverdocumento) {
        this.serverdocumento = serverdocumento;
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
     * @return the codigoentregadocumento
     */
    public int getCodigoentregadocumento() {
        return codigoentregadocumento;
    }

    /**
     * @param codigoentregadocumento the codigoentregadocumento to set
     */
    public void setCodigoentregadocumento(int codigoentregadocumento) {
        this.codigoentregadocumento = codigoentregadocumento;
    }

    /**
     * @return the streamarchivo
     */
    public InputStream getStreamarchivo() {
        return streamarchivo;
    }

    /**
     * @param streamarchivo the streamarchivo to set
     */
    public void setStreamarchivo(InputStream streamarchivo) {
        this.streamarchivo = streamarchivo;
    }

    /**
     * @return the pathdocumento
     */
    public String getPathdocumento() {
        return pathdocumento;
    }

    /**
     * @param pathdocumento the pathdocumento to set
     */
    public void setPathdocumento(String pathdocumento) {
        this.pathdocumento = pathdocumento;
    }
    
}
