package simuni.classes.EN;

import java.util.Date;

public class tareaReporte {
    private int pn_idTareaReporte;
    private int pn_estado;
    private Date pd_fechaEjecucion;
    
    public tareaReporte(){
        
    }
    public int getPn_idTareaReporte() {
        return pn_idTareaReporte;
    }
    public void setPn_idTareaReporte(int pn_idTareaReporte) {
        this.pn_idTareaReporte = pn_idTareaReporte;
    }
    public int getPn_estado() {
        return pn_estado;
    }
    public void setPn_estado(int pn_estado) {
        this.pn_estado = pn_estado;
    }
    public Date getPd_fechaEjecucion() {
        return pd_fechaEjecucion;
    }
    public void setPd_fechaEjecucion(Date pd_fechaEjecucion) {
        this.pd_fechaEjecucion = pd_fechaEjecucion;
    }
}
