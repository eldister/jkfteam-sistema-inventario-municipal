package simuni.classes.EN;

/**
 * En la clase departamento se va a mostrar el nombre y el código de todos los apartamentos que existen.
 * Con sus respectivos métodos
 * @author Keylin
 */

public class Departamento {
    private String pa_nombre;
    private int pn_codigo;
    
    public Departamento(){
        
    }
    
    /**
     * obtener el nombre del departamento 
     * @return nombre de departamento
     */
    public String getPa_nombre() {
        return pa_nombre;
    }
    
    /**
     * poner el nombre del departamento 
     * @param pa_nombre 
     */
    public void setPa_nombre(String pa_nombre) {
        this.pa_nombre = pa_nombre;
    }
    
    /**
     * obtener el código del departamento 
     * @return codigo del departamento 
     */
    public int getPn_codigo() {
        return pn_codigo;
    }
    
    /**
     * poner el código del departamento
     * @param pn_codigo 
     */
    public void setPn_codigo(int pn_codigo) {
        this.pn_codigo = pn_codigo;
    }
}
