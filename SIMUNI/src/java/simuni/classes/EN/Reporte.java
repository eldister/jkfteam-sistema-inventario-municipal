package simuni.classes.EN;

import java.util.ArrayList;

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
    public int getPn_codigoReporte() {
        return pn_codigoReporte;
    }
    public void setPn_codigoReporte(int pn_codigoReporte) {
        this.pn_codigoReporte = pn_codigoReporte;
    }
    public String getPa_nombreReporte() {
        return pa_nombreReporte;
    }
    public void setPa_nombreReporte(String pa_nombreReporte) {
        this.pa_nombreReporte = pa_nombreReporte;
    }
    public ArrayList<String> getPo_destinatarios() {
        return po_destinatarios;
    }
    public void setPo_destinatarios(ArrayList<String> po_destinatarios) {
        this.po_destinatarios = po_destinatarios;
    }
    public ArrayList<tareaReporte> getPo_tareaReporte() {
        return po_tareaReporte;
    }
    public void setPo_tareaReporte(ArrayList<tareaReporte> po_tareaReporte) {
        this.po_tareaReporte = po_tareaReporte;
    }
    public int getPn_maxIntentos() {
        return pn_maxIntentos;
    }
    public void setPn_maxIntentos(int pn_maxIntentos) {
        this.pn_maxIntentos = pn_maxIntentos;
    }
    public String getPa_tipoReporte() {
        return pa_tipoReporte;
    }
    public void setPa_tipoReporte(String pa_tipoReporte) {
        this.pa_tipoReporte = pa_tipoReporte;
    }
    public int getPn_lapsoTiempo() {
        return pn_lapsoTiempo;
    }
    public void setPn_lapsoTiempo(int pn_lapsoTiempo) {
        this.pn_lapsoTiempo = pn_lapsoTiempo;
    }
}
