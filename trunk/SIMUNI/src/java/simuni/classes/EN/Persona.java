package simuni.classes.EN;

public class Persona {
    private String pa_cedula;
    private String pa_nombre;
    private String pa_primerApellido;
    private String pa_segundoApellido;
    
    public Persona(){
        
    }
    public Persona getPersona(){
        return new Persona();
    }
    public void setPersona(String ced, String nomb, String pap, String sap){
        this.pa_cedula = ced;
        this.pa_nombre = nomb;
        this.pa_primerApellido = pap;
        this.pa_segundoApellido = sap;
    }
    public String getPa_cedula() {
        return pa_cedula;
    }
    public void setPa_cedula(String pa_cedula) {
        this.pa_cedula = pa_cedula;
    }
    public String getPa_nombre() {
        return pa_nombre;
    }
    public void setPa_nombre(String pa_nombre) {
        this.pa_nombre = pa_nombre;
    }
    public String getPa_primerApellido() {
        return pa_primerApellido;
    }
    public void setPa_primerApellido(String pa_primerApellido) {
        this.pa_primerApellido = pa_primerApellido;
    }
    public String getPa_segundoApellido() {
        return pa_segundoApellido;
    }
    public void setPa_segundoApellido(String pa_segundoApellido) {
        this.pa_segundoApellido = pa_segundoApellido;
    }
}
