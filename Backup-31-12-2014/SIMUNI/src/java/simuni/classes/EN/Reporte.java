package simuni.classes.EN;

import java.util.ArrayList;
/**
 * En esta clase se tendrá los reportes que se harán por ejemplo de activos, proveedores 
 * @author Keylin
 */
public class Reporte {
    private int pn_codigoReporte;
    private String pa_nombreReporte;
    private ArrayList<String> po_destinatarios = new ArrayList<String>();
    private ArrayList<tareaReporte> po_tareaReporte = new ArrayList<tareaReporte>();
    private int pn_maxIntentos;
    private String pa_tipoReporte;
    private int pn_lapsoTiempo;
    
    public Reporte(){
        
    }
    
    /**
     * obtener el código del reporte 
     * @return código del reporte
     */
    public int getPn_codigoReporte() {
        return pn_codigoReporte;
    }
    
    /**
     * poner el código del reporte
     * @param pn_codigoReporte 
     */
    public void setPn_codigoReporte(int pn_codigoReporte) {
        this.pn_codigoReporte = pn_codigoReporte;
    }
    
    /**
     * obtener el nombre del reporte 
     * @return nombre del reporte
     */
    public String getPa_nombreReporte() {
        return pa_nombreReporte;
    }
    
    /**
     * poner el nombre del reporte
     * @param pa_nombreReporte 
     */
    public void setPa_nombreReporte(String pa_nombreReporte) {
        this.pa_nombreReporte = pa_nombreReporte;
    }
    
    /**
     * obtener los destinarios a quienes se le enviará el reporte 
     * @return destinarios 
     */
    public ArrayList<String> getPo_destinatarios() {
        return po_destinatarios;
    }
    
    /**
     * poner los destinarios a quienes se le enviará el reporte 
     * @param po_destinatarios 
     */
    public void setPo_destinatarios(ArrayList<String> po_destinatarios) {
        this.po_destinatarios = po_destinatarios;
    }
    
    /**
     * obtener la tarea de reporte
     * @return tarea de reporte
     */
    public ArrayList<tareaReporte> getPo_tareaReporte() {
        return po_tareaReporte;
    }
    
    /**
     * poner la tarea de reporte
     * @param po_tareaReporte 
     */
    public void setPo_tareaReporte(ArrayList<tareaReporte> po_tareaReporte) {
        this.po_tareaReporte = po_tareaReporte;
    }
    
    /**
     * obtener el número máximo de intentos para enviar reportes 
     * @return máximos de intentos 
     */
    public int getPn_maxIntentos() {
        return pn_maxIntentos;
    }
    
    /**
     * poner el número máximo de intentos para enviar reportes
     * @param pn_maxIntentos 
     */
    public void setPn_maxIntentos(int pn_maxIntentos) {
        this.pn_maxIntentos = pn_maxIntentos;
    }
    
    /**
     * obtener el tipo de reporte 
     * @return tipo de reporte 
     */
    public String getPa_tipoReporte() {
        return pa_tipoReporte;
    }
    
    /**
     * poner el tipo de reporte 
     * @param pa_tipoReporte 
     */
    public void setPa_tipoReporte(String pa_tipoReporte) {
        this.pa_tipoReporte = pa_tipoReporte;
    }
    
    /**
     * obtener el lapso de tiempo para enviar reportes
     * @return lapso de tiempo
     */
    public int getPn_lapsoTiempo() {
        return pn_lapsoTiempo;
    }
    
    /**
     * poner el lapso de tiempo para enviar reportes
     * @param pn_lapsoTiempo 
     */
    public void setPn_lapsoTiempo(int pn_lapsoTiempo) {
        this.pn_lapsoTiempo = pn_lapsoTiempo;
    }
}
