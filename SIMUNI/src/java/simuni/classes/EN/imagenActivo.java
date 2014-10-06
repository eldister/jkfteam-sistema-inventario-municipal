package simuni.classes.EN;

import java.io.InputStream;

public class imagenActivo {
    private String pa_nombreArchivo;
    private String pa_url;
    private InputStream streamarchivo;
    
    public imagenActivo(){
        
    }
    public String getPa_nombreArchivo() {
        return pa_nombreArchivo;
    }
    public void setPa_nombreArchivo(String pa_nombreArchivo) {
        this.pa_nombreArchivo = pa_nombreArchivo;
    }
    public String getPa_url() {
        return pa_url;
    }
    public void setPa_url(String pa_url) {
        this.pa_url = pa_url;
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
}
