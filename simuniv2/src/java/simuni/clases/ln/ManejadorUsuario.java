/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import simuni.clases.ad.ManejadorDatosPuesto;
import simuni.clases.ad.ManejadorDatosUsuario;
import simuni.entidades.Respuesta;
import simuni.entidades.Usuario;
import simuni.entidades.mantenimientos.Departamento;
import simuni.entidades.mantenimientos.Puesto;
import simuni.entidades.mantenimientos.TipoUsuario;
import simuni.intefaces.IReporteador;

/**
 *
 * @author FchescO
 */
public class ManejadorUsuario implements IReporteador {

    /**
     * Este método realiza la autentificación de un usuario particular al
     * sistema de inventario SIMUNI
     *
     * @param usuario nombre de usuario
     * @param contrasena contraseña del usuario a autentificarse
     * @return El usuario que ha iniciado sesión para utilizar las
     * funcionalidades del sistema según el tipo de privilegio que maneje ya sea
     * administrador o normal, devuelve nulo en caso de que el usuario no este
     * registrado en la base de datos
     * @since 1.0
     */
    public Usuario login(String usuario, String contrasena) {
        Usuario usuarioresp = null;
        try {
            ManejadorDatosUsuario manejadordatosusuarios = new ManejadorDatosUsuario();
            usuarioresp = manejadordatosusuarios.login(usuario, contrasena);

            if (usuario == null || contrasena == null) {
                return null;
            }
        } catch (Exception ex) {
            //registrar error
            ex.printStackTrace();
            return null;
        }
        return usuarioresp;
    }

    /**
     * Este método obtiene el menú acorde al tipo de privilegio que posea el
     * usuario loggeado al sistema
     *
     * @param idusuario identificación del usuario loggeado
     * @return El menú del usuario según su tipo de privilegio de uso, devuelve
     * nulo en caso de que el usuario no este autentificado
     * @since 1.0
     */
    public Usuario getMenuUsuario(String idusuario) {

        ManejadorDatosUsuario manejadordatosusuarios = new ManejadorDatosUsuario();
        Usuario usu = null;

        try {
            usu = obtenerUsuario(idusuario);
            System.out.println("es nulooooooooooooooo "+usu==null);
            usu = manejadordatosusuarios.getMenuUsuario(usu);

        } catch (Exception ex) {
            //registrar error
            ex.printStackTrace();
            return null;
        }
        return usu;
    }
    
    /**
     * Método que válida si un usuario tiene o no permisos de usuario
     * 
     * 
     * @param idusuario identificación del usuario
     * @param codigopermiso código de permiso relacionado a un usuario
     * @return un valor booleano
     * true en caso de que tiene permisos
     * false en caso de que no tenga permisos
     * @since 1.0
     */
    public boolean usuarioTienePermiso(int codigopermiso,String idusuario){
        boolean resp=false;
         ManejadorDatosUsuario manejadordatosusuarios = new ManejadorDatosUsuario();
        try{
            ResultSet aux = null;
             aux = manejadordatosusuarios.usuarioTienePermiso(idusuario, codigopermiso);
             if(aux!=null&&aux.next()){
                 resp=true;
             }
             
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return resp;
    }
    
    /**
     * Método para obtener a un usuario en especifico de la base de datos
     *
     * @param usuario cuyos datos serán obtenidos de la base de datos
     * @return un objeto de tipo usuario
     * @since 1.0
     */
    public Usuario obtenerUsuario(String usuario) {
        Usuario usuarioresp = null;
        try {

            ManejadorDatosUsuario manejadordatosusuarios = new ManejadorDatosUsuario();
            System.out.println("el usuario es "+usuario);
            usuarioresp = manejadordatosusuarios.obtenerUsuario(usuario);

        } catch (Exception ex) {
            //registrar error
            ex.printStackTrace();
            return null;
        }
        return usuarioresp;
    }

    /**
     * Método para obtener un listado de los puestos
     * 
     * @return un arraylist con los resultados obtenidos
     * @since 1.0
     */
    public ArrayList<Puesto> listadoPuesto() {
        ManejadorPuesto mdpuesto = new ManejadorPuesto();
        return mdpuesto.listadoPuesto();
    }
    
    /**
     * Método para obtener un listado de los tipos de usuario
     * 
     * @return un arraylist con los resultados obtenidos
     * @since 1.0
     */
    public ArrayList<TipoUsuario> listadoTipoUsuario() {
        ManejadorTipoUsuario mdtiposusuario = new ManejadorTipoUsuario();
        return mdtiposusuario.listadoTipoUsuarios();
    }  
    
    /**
     * Método para obtener un listado de los departamentos
     * 
     * @return un arraylist con los resultados obtenidos
     * @since 1.0
     */
    public ArrayList<Departamento> listadoDepartamento() {
        ManejadorDepartamento mdepartamento = new ManejadorDepartamento();
        return mdepartamento.listadoDepartamento();
    }    

    /**
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param desplazamiento Registros que se ha de brincar o pasar por alto.
     * @param paginacion Cantidad de registros a traer.
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ResultSet listadoPuesto(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosPuesto mdPuesto = new ManejadorDatosPuesto();
        try {
            resp = mdPuesto.listadoPuesto(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Realiza una busqueda en la base de datos.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @throws SQLException Si ocurre una excepcion de SQL.
     * @since 1.0
     */
    public ResultSet busquedaUsuario(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        ManejadorDatosUsuario mdUsuario = new ManejadorDatosUsuario();
        try {
            resp = mdUsuario.busquedaUsuario(query, desplazamiento, paginacion);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Debes registrar algo");
        }
        return resp;

    }
    
    /**
     * Método que realiza una búsqueda de un usuario que se encuentra inactivo
     * en el sistema
     * 
     * @param query sentencia para la búsqueda del usuario
     * @param desplazamiento de los registros
     * @param paginacion de los registros
     * @return Un resultset con los resultados de la operación.
     * @since 1.0
     */
     public ResultSet busquedaUsuarioInactivo(String query, int desplazamiento, int paginacion) throws SQLException {
        ResultSet resp = null;
        ManejadorDatosUsuario mdUsuario = new ManejadorDatosUsuario();
        try {
            resp = mdUsuario.busquedaUsuarioInactivo(query, desplazamiento, paginacion);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Debes registrar algo");
        }
        return resp;

    }   
    
     /**
     * Método para obtener un listado de los usuarios.
     * 
     * @return un arraylist con los resultados obtenidos
     * @since 1.0
     */
    public ArrayList<Usuario> listadoUsuarios(){
        ArrayList<Usuario> usuarios = null;
        ManejadorDatosUsuario mdUsuario = new ManejadorDatosUsuario();
        try {
            ResultSet resp = null;
            resp = mdUsuario.listadoUsuario();
            if (resp.next()) {
                usuarios = new ArrayList<Usuario>();
                do {
                    Usuario usuario = new Usuario();
                    usuario.setNombreusuario(resp.getString(1));
                    usuario.setNombre(resp.getString(2));
                    usuarios.add(usuario);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return usuarios;        
    }
    
    /**
     * Método para obtener un listado de los permisos de usuario
     * 
     * @return un arraylist con los resultados obtenidos
     * @since 1.0
     */
    public ArrayList<Usuario> listadoUsuarios_Permisos(){
        ArrayList<Usuario> usuarios = null;
        ManejadorDatosUsuario mdUsuario = new ManejadorDatosUsuario();
        try {
            ResultSet resp = null;
            resp = mdUsuario.listadoUsuarios_Permisos();
            if (resp.next()) {
                usuarios = new ArrayList<Usuario>();
                do {
                    Usuario usuario = new Usuario();
                    usuario.setNombreusuario(resp.getString(1));
                    usuario.setNombre(resp.getString(2));
                    usuario.setCedula(resp.getString(3));
                    usuarios.add(usuario);
                } while (resp.next());
            }

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return usuarios;        
    }    
    
    /**
     * Metodo para registrar un unevo usuario al sistema
     *
     * @param usuario El nuevo registro a ingresar.
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta registrarUsuario(Usuario usuario) {
        Respuesta resp = new Respuesta();
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.registrarUsuario(usuario);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    /**
     * Método contrario al anterior, este método a un usuario cuyo registro en la base
     * de datos del sistema se encuentra en estado INACTIVO.
     *
     * @param usuario a quien se rehabilitara el registro en la base de datos
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta reactivarUsuario(Usuario usuario) {
        Respuesta resp = new Respuesta();
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.reactivarUsuario(usuario);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }    

    /**
     * Método para la modificación o cambios de la información de un usuario 
     * en particular
     *
     * @param usuario El nuevo registro a ingresar.
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta modificarUsuario(Usuario usuario) {
        Respuesta resp = new Respuesta();
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.modificarUsuario(usuario);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }

    /**
     * Método para la deshabilitacion de un registro de un usuario del sistema 
     *
     * @param usuario a quien se deshabilitara su registro del sistema
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta eliminarUsuario(Usuario usuario) {
        Respuesta resp = new Respuesta();
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.eliminarUsuario(usuario);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro. No lanza excepciones, y si las hay,
     * las registra en bitácora.
     *
     * @param query La cadena con la busqueda a evaluar.
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
        public int getCantidadRegistros(String query) {
        int resp = 0;
        try {
            ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();
            resp = mdusuario.getCantidadFilas(query);

        } catch (SQLException ex) {
            
            ex.printStackTrace();

        }
        return resp;
    }
        /**
     * Obtiene la cantidad de registros de los usuarios que se encuentran
     * inactivos que hay en la base de datos, con el criterio qeu se pasa por 
     * parámetro. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @param query La cadena con la busqueda a evaluar.
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
        public int getCantidadRegistrosInactivos(String query) {
        int resp = 0;
        try {
            ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();
            resp = mdusuario.getCantidadFilasInactivas(query);

        } catch (SQLException ex) {
            
            ex.printStackTrace();

        }
        return resp;
    }        
        
        /**
     * Método para modificar o cambiar la contraseña de un usuario en particular
     * en caso de que el usuario asi lo desee
     *
     * @param usuario objeto tipo Usuario referente al individuo que va a cambiar su contraseña
     * contiene la informacion del usuario, ademas de la nueva contraseña
     * @param pass_actual la contraseña que posee actualmente el usuario
     * @return un objeto con la respuesta de la operación.
     * @since 1.0
     */
    public Respuesta modificarClaveUsuario(Usuario usuario,String pass_actual) {
        Respuesta resp = new Respuesta();
       
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.modificarClaveUsuario(usuario,pass_actual);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }
    
    /**
     * Método para actualizar la contraseña de un usuario, se llama resetear puesto que 
     * se le puede colocar la que tenia anteriormente o cualquiera que considere poner
     * el encargado de las TICs
     *
     * @param usuario a quien se le hara el reseteo de la contraseña
     * contiene la información del usuario y la contraseña nueva
     * @return Un string con la respuesta directamente del servidor de base de
     * datos.
     * @since 1.0
     */
        public Respuesta resetearClaveUsuario(Usuario usuario) {
        Respuesta resp = new Respuesta();
       
        ManejadorDatosUsuario mdusuario = new ManejadorDatosUsuario();

        try {
            String msg = mdusuario.resetearClaveUsuario(usuario);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (Exception ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }

        /**
     * Método para generar un reporte general de todos los usuarios registrados 
     * en la base de datos del sistema.
     * 
     * @return un arraylist con la información para la generación de un reporte
     * @since 1.0
     */
    @Override
    public ArrayList<String[]> obtenerDatosReporte() {
 ArrayList<String[]> resp = new ArrayList<String[]>();
        ManejadorDatosUsuario musuario = new ManejadorDatosUsuario();
        try {
            ResultSet rs = musuario.ReporteGeneralUsuarios();
            ResultSetMetaData rsmd = rs.getMetaData();

            if (rs != null&&rs.next()) {
                resp.add(new String[]{
                    rsmd.getColumnLabel(1),
                    rsmd.getColumnLabel(2),
                    rsmd.getColumnLabel(3),
                    rsmd.getColumnLabel(4),
                    rsmd.getColumnLabel(5),
                    rsmd.getColumnLabel(6),
                    rsmd.getColumnLabel(7)
                });
                
                do {
                resp.add(new String[]{
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7)
                });
                } while (rs.next());
            }
            rs.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return resp;}

    /**
     * Método para generar un reporte general de acuerdo a un rango de fechas con
     * todos los usuarios registrados en la base de datos del sistema.
     * 
     * @return un arraylist con la información para la generación de un reporte
     * @since 1.0
     */
    @Override
    public ArrayList<String[]> obtenerDatosReporte(Date fini, Date ffin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
