/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.entidades;

/**
 *
 * @author FchescO
 */
public class Configuracion {
    private int codigoConfiguracion=0;//-
    private String nombreConfiguracion="";//-
    private String pathMysqlDump="";//-
    private String pathBackup="";//-
    private String nombreBaseDatos="";//-
    private String prefijoBackup="";//-
    private String serverBaseDatos="";//-
    private String serverWeb="";
    private String serverArchivos="";
    private String pathBaseArchivos="";
    private String pathImagenesArchivos="";
    private String pathImagenesDefault="";
    private String pathImagenesProveedores="";
    private String contextoWeb="";
    private String contextoArchivosActivos="";
    private String contextoArchivoProveedores="";
    private int lapsoTiempoBackup=1;//en dias
    private int lapsoTimpoReporteActivo=0;
    private int lapsoTimpoReporteProveedores=0;
    private int lapsoTimpoReporteGeneral=0;
    private String puertoServer="";
    private String usuarioBd="";
    private String contraseniaBd="";

    /**
     * @return the nombreConfiguracion
     */
    public String getNombreConfiguracion() {
        return nombreConfiguracion;
    }

    /**
     * @param nombreConfiguracion the nombreConfiguracion to set
     */
    public void setNombreConfiguracion(String nombreConfiguracion) {
        this.nombreConfiguracion = nombreConfiguracion;
    }

    /**
     * @return the pathMysqlDump
     */
    public String getPathMysqlDump() {
        return pathMysqlDump;
    }

    /**
     * @param pathMysqlDump the pathMysqlDump to set
     */
    public void setPathMysqlDump(String pathMysqlDump) {
        this.pathMysqlDump = pathMysqlDump;
    }

    /**
     * @return the pathBackup
     */
    public String getPathBackup() {
        return pathBackup;
    }

    /**
     * @param pathBackup the pathBackup to set
     */
    public void setPathBackup(String pathBackup) {
        this.pathBackup = pathBackup;
    }

    /**
     * @return the nombreBaseDatos
     */
    public String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    /**
     * @param nombreBaseDatos the nombreBaseDatos to set
     */
    public void setNombreBaseDatos(String nombreBaseDatos) {
        this.nombreBaseDatos = nombreBaseDatos;
    }

    /**
     * @return the prefijoBackup
     */
    public String getPrefijoBackup() {
        return prefijoBackup;
    }

    /**
     * @param prefijoBackup the prefijoBackup to set
     */
    public void setPrefijoBackup(String prefijoBackup) {
        this.prefijoBackup = prefijoBackup;
    }

    /**
     * @return the serverBaseDatos
     */
    public String getServerBaseDatos() {
        return serverBaseDatos;
    }

    /**
     * @param serverBaseDatos the serverBaseDatos to set
     */
    public void setServerBaseDatos(String serverBaseDatos) {
        this.serverBaseDatos = serverBaseDatos;
    }

    /**
     * @return the serverWeb
     */
    public String getServerWeb() {
        return serverWeb;
    }

    /**
     * @param serverWeb the serverWeb to set
     */
    public void setServerWeb(String serverWeb) {
        this.serverWeb = serverWeb;
    }

    /**
     * @return the serverArchivos
     */
    public String getServerArchivos() {
        return serverArchivos;
    }

    /**
     * @param serverArchivos the serverArchivos to set
     */
    public void setServerArchivos(String serverArchivos) {
        this.serverArchivos = serverArchivos;
    }

    /**
     * @return the pathBaseArchivos
     */
    public String getPathBaseArchivos() {
        return pathBaseArchivos;
    }

    /**
     * @param pathBaseArchivos the pathBaseArchivos to set
     */
    public void setPathBaseArchivos(String pathBaseArchivos) {
        this.pathBaseArchivos = pathBaseArchivos;
    }

    /**
     * @return the pathImagenesArchivos
     */
    public String getPathImagenesArchivos() {
        return pathImagenesArchivos;
    }

    /**
     * @param pathImagenesArchivos the pathImagenesArchivos to set
     */
    public void setPathImagenesArchivos(String pathImagenesArchivos) {
        this.pathImagenesArchivos = pathImagenesArchivos;
    }

    /**
     * @return the pathImagenesDefault
     */
    public String getPathImagenesDefault() {
        return pathImagenesDefault;
    }

    /**
     * @param pathImagenesDefault the pathImagenesDefault to set
     */
    public void setPathImagenesDefault(String pathImagenesDefault) {
        this.pathImagenesDefault = pathImagenesDefault;
    }

    /**
     * @return the pathImagenesProveedores
     */
    public String getPathImagenesProveedores() {
        return pathImagenesProveedores;
    }

    /**
     * @param pathImagenesProveedores the pathImagenesProveedores to set
     */
    public void setPathImagenesProveedores(String pathImagenesProveedores) {
        this.pathImagenesProveedores = pathImagenesProveedores;
    }

    /**
     * @return the contextoWeb
     */
    public String getContextoWeb() {
        return contextoWeb;
    }

    /**
     * @param contextoWeb the contextoWeb to set
     */
    public void setContextoWeb(String contextoWeb) {
        this.contextoWeb = contextoWeb;
    }

    /**
     * @return the contextoArchivosActivos
     */
    public String getContextoArchivosActivos() {
        return contextoArchivosActivos;
    }

    /**
     * @param contextoArchivosActivos the contextoArchivosActivos to set
     */
    public void setContextoArchivosActivos(String contextoArchivosActivos) {
        this.contextoArchivosActivos = contextoArchivosActivos;
    }

    /**
     * @return the contextoArchivoProveedores
     */
    public String getContextoArchivoProveedores() {
        return contextoArchivoProveedores;
    }

    /**
     * @param contextoArchivoProveedores the contextoArchivoProveedores to set
     */
    public void setContextoArchivoProveedores(String contextoArchivoProveedores) {
        this.contextoArchivoProveedores = contextoArchivoProveedores;
    }

    /**
     * @return the lapsoTiempoBackup
     */
    public int getLapsoTiempoBackup() {
        return lapsoTiempoBackup;
    }

    /**
     * @param lapsoTiempoBackup the lapsoTiempoBackup to set
     */
    public void setLapsoTiempoBackup(int lapsoTiempoBackup) {
        this.lapsoTiempoBackup = lapsoTiempoBackup;
    }

    /**
     * @return the lapsoTimpoReporteActivo
     */
    public int getLapsoTimpoReporteActivo() {
        return lapsoTimpoReporteActivo;
    }

    /**
     * @param lapsoTimpoReporteActivo the lapsoTimpoReporteActivo to set
     */
    public void setLapsoTimpoReporteActivo(int lapsoTimpoReporteActivo) {
        this.lapsoTimpoReporteActivo = lapsoTimpoReporteActivo;
    }

    /**
     * @return the lapsoTimpoReporteProveedores
     */
    public int getLapsoTimpoReporteProveedores() {
        return lapsoTimpoReporteProveedores;
    }

    /**
     * @param lapsoTimpoReporteProveedores the lapsoTimpoReporteProveedores to set
     */
    public void setLapsoTimpoReporteProveedores(int lapsoTimpoReporteProveedores) {
        this.lapsoTimpoReporteProveedores = lapsoTimpoReporteProveedores;
    }

    /**
     * @return the lapsoTimpoReporteGeneral
     */
    public int getLapsoTimpoReporteGeneral() {
        return lapsoTimpoReporteGeneral;
    }

    /**
     * @param lapsoTimpoReporteGeneral the lapsoTimpoReporteGeneral to set
     */
    public void setLapsoTimpoReporteGeneral(int lapsoTimpoReporteGeneral) {
        this.lapsoTimpoReporteGeneral = lapsoTimpoReporteGeneral;
    }

    /**
     * @return the codigoConfiguracion
     */
    public int getCodigoConfiguracion() {
        return codigoConfiguracion;
    }

    /**
     * @param codigoConfiguracion the codigoConfiguracion to set
     */
    public void setCodigoConfiguracion(int codigoConfiguracion) {
        this.codigoConfiguracion = codigoConfiguracion;
    }

    /**
     * @return the puertoServer
     */
    public String getPuertoServer() {
        return puertoServer;
    }

    /**
     * @param puertoServer the puertoServer to set
     */
    public void setPuertoServer(String puertoServer) {
        this.puertoServer = puertoServer;
    }

    /**
     * @return the usuarioBd
     */
    public String getUsuarioBd() {
        return usuarioBd;
    }

    /**
     * @param usuarioBd the usuarioBd to set
     */
    public void setUsuarioBd(String usuarioBd) {
        this.usuarioBd = usuarioBd;
    }

    /**
     * @return the contraseniaBd
     */
    public String getContraseniaBd() {
        return contraseniaBd;
    }

    /**
     * @param contraseniaBd the contraseniaBd to set
     */
    public void setContraseniaBd(String contraseniaBd) {
        this.contraseniaBd = contraseniaBd;
    }
    
    
}
