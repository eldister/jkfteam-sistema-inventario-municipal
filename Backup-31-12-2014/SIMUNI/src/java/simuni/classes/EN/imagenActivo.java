package simuni.classes.EN;

import java.io.InputStream;
/**
 * Esta clase se va a encargar de las imagenes que se tienen que guardar de los activos  
 * @author Keylin
 */
public class imagenActivo {
    private String pa_nombreArchivo;
    private String pa_url;
    private InputStream streamarchivo;
    
    public imagenActivo(){
        
    }
    /**
     * obtener el nombre del archivo 
     * @return nombre del archivo
     */
    public String getPa_nombreArchivo() {
        return pa_nombreArchivo;
    }
    /**
     * poner el nombre del archivo
     * @param pa_nombreArchivo 
     */
    public void setPa_nombreArchivo(String pa_nombreArchivo) {
        this.pa_nombreArchivo = pa_nombreArchivo;
    }
    
    /**
     * obtener la url de la imagen 
     * @return url 
     */
    public String getPa_url() {
        return pa_url;
    }
    
    /**
     * poner la url de la imagen 
     * @param pa_url 
     */
    public void setPa_url(String pa_url) {
        this.pa_url = pa_url;
    }

    /**
     * 
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
