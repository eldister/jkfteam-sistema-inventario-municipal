package simuni.classes.EN;

import java.io.InputStream;

public class Documentos {
    private String pa_nombreArchivo;
    private String pa_fileUrl;
        private InputStream streamarchivo;
    public Documentos(){
        
    }
    public String getPa_nombreArchivo() {
        return pa_nombreArchivo;
    }
    public void setPa_nombreArchivo(String pa_nombreArchivo) {
        this.pa_nombreArchivo = pa_nombreArchivo;
    }
    public String getPa_fileUrl() {
        return pa_fileUrl;
    }
    public void setPa_fileUrl(String pa_fileUrl) {
        this.pa_fileUrl = pa_fileUrl;
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
