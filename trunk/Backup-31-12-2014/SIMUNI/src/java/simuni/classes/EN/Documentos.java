package simuni.classes.EN;

import java.io.InputStream;
/**
 * En la clase documentos se va a capturar todos los documentos que un proveedor debe de llevar al momento de registrarse
 * y se implementaron sus métodos 
 * @author Keylin
 */
public class Documentos {
    private String pa_nombreArchivo;
    private String pa_fileUrl;
        private InputStream streamarchivo;
    public Documentos(){
        
    }
    
    /**
     * obtener el nombre específico del archivo
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
     * obtener la dirección url de donde se encuentra el archivo 
     * @return dirección del archivo
     */
    public String getPa_fileUrl() {
        return pa_fileUrl;
    }
    
    /**
     * poner donde se va a ubicar el archivo
     * @param pa_fileUrl 
     */
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
