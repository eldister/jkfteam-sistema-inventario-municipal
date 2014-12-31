package simuni.classes.EN;
/**
 * La clase persona va a tener los datos personales de cada usuario que esté en el sistema 
 * @author Keylin
 */
public class Persona {
    private String pa_cedula;
    private String pa_nombre;
    private String pa_primerApellido;
    private String pa_segundoApellido;
    
    public Persona(){
        
    }
    
    /**
     * obtener todos los datos de la persona 
     * @return  persna 
     */
    public Persona getPersona(){
        return new Persona();
    }
    
    /**
     * poner los datos de la persana(cedula, nombre, primer apellido, segundo apellido )
     * @param ced
     * @param nomb
     * @param pap
     * @param sap 
     */
    public void setPersona(String ced, String nomb, String pap, String sap){
        this.pa_cedula = ced;
        this.pa_nombre = nomb;
        this.pa_primerApellido = pap;
        this.pa_segundoApellido = sap;
    }
    
    /**
     * obtener la cédula de la persona
     * @return 
     */
    public String getPa_cedula() {
        return pa_cedula;
    }
    
    /**
     * poner la cédula de la persona
     * @param pa_cedula 
     */
    public void setPa_cedula(String pa_cedula) {
        this.pa_cedula = pa_cedula;
    }
    
    /**
     * obtener el nombre de la persona 
     * @return 
     */
    public String getPa_nombre() {
        return pa_nombre;
    }
    
    /**
     * poner el nombre de la persona 
     * @param pa_nombre 
     */
    public void setPa_nombre(String pa_nombre) {
        this.pa_nombre = pa_nombre;
    }
    
    /**
     * obtener el primer apellido de la persona
     * @return 
     */
    public String getPa_primerApellido() {
        return pa_primerApellido;
    }
    /**
     * poner el primer apellido de la persona
     * @param pa_primerApellido 
     */
    public void setPa_primerApellido(String pa_primerApellido) {
        this.pa_primerApellido = pa_primerApellido;
    }
    
    /**
     * obtener el segundo apellido de la persona 
     * @return 
     */
    public String getPa_segundoApellido() {
        return pa_segundoApellido;
    }
    
    /**
     * poner el segundo apellido de la persona 
     * @param pa_segundoApellido 
     */
    public void setPa_segundoApellido(String pa_segundoApellido) {
        this.pa_segundoApellido = pa_segundoApellido;
    }
}
