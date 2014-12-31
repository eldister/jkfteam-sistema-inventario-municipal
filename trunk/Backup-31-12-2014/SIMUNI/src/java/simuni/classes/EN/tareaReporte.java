package simuni.classes.EN;

import java.util.Date;
/**
 * se va a obtener la tarea de cada reporte 
 * @author Keylin
 */
public class tareaReporte {
    private int pn_idTareaReporte;
    private int pn_estado;
    private Date pd_fechaEjecucion;
    
    public tareaReporte(){
        
    }
    /**
     * obtener el id de la tarea de reporte 
     * @return  id de la tarea de reporte
     */
    public int getPn_idTareaReporte() {
        return pn_idTareaReporte;
    }
    
    /**
     * poner el id de la tarea de reporte 
     * @param pn_idTareaReporte 
     */
    public void setPn_idTareaReporte(int pn_idTareaReporte) {
        this.pn_idTareaReporte = pn_idTareaReporte;
    }
    
    /**
     * obtener el estado de la tarea de reporte 
     * @return estado
     */
    public int getPn_estado() {
        return pn_estado;
    }
    
    /**
     * poner el estado de la tarea de reporte 
     * @param pn_estado 
     */
    public void setPn_estado(int pn_estado) {
        this.pn_estado = pn_estado;
    }
    
    /**
     * obtener la fecha de ejecución 
     * @return fecha de ejecución
     */
    public Date getPd_fechaEjecucion() {
        return pd_fechaEjecucion;
    }
    
    /**
     * poner la fecha de ejecución
     * @param pd_fechaEjecucion 
     */
    public void setPd_fechaEjecucion(Date pd_fechaEjecucion) {
        this.pd_fechaEjecucion = pd_fechaEjecucion;
    }
}
