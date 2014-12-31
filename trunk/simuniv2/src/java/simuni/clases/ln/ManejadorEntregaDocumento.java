package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import simuni.clases.ad.ManejadorDatosEntregaDocumento;
import simuni.entidades.Documento;
import simuni.entidades.Respuesta;
import simuni.entidades.EntregaDocumento;
import simuni.entidades.archivos.ManejadorArchivos;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 * Esta clase de lógica de negocios de <strong>Entrega de Documento</strong> se
 * encarga de las operaciones de validación, solicitudes y respuestas, para
 * hacer su ingreso, modificación, eliminación de registros. Entre las
 * operaciones comunes que se solicitan estan agregar, modificar, eliminar,
 * hacer un query de busqueda y tambien hacer el listado por defecto que hay de
 * los datos ingresados.
 *
 * @author FchescO
 * @since 1.0
 * @version 1.0
 */
public class ManejadorEntregaDocumento {

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Entrega de Documento</strong>.
     *
     * @param entregadocumento El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta registrarEntregaDocumento(EntregaDocumento entregadocumento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();

        try {
            String msg = mdentregadocumento.registrarEntregaDocumento(entregadocumento);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Entrega de Documento</strong>.
     *
     * @param entregadocumento El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public ArrayList<Respuesta> registrarEntregaDocumento2(EntregaDocumento entregadocumento) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        Respuesta respuestaregentrega = new Respuesta();
        ManejadorArchivos marchivos = new ManejadorArchivos();
        String msg = "";
        try {
            /*esta es para la parte de registro de la entrega. */
            msg = mdentregadocumento.registrarEntregaDocumento(entregadocumento);
            if (msg != null && msg.startsWith("2")) {

                respuestaregentrega.setNivel(2);
            } else {
                respuestaregentrega.setNivel(1);
            }
            respuestaregentrega.setMensaje(msg);
            resp.add(respuestaregentrega);
            /*Aqui empezamos la parte de los archivos verificmos si se ha registrado algo*/
            if (entregadocumento.getCodigo() > 0) {//si se registro la entrada, entonces procedemos a los documentos
                if (entregadocumento.getDocumentos() != null) {
                    //si hay documentos
                    Iterator<Documento> iteradordocumentos = entregadocumento.getDocumentos().iterator();
                    if (iteradordocumentos != null) {
                        System.out.println("No es nulo");
                        while (iteradordocumentos.hasNext()) {
                            //cada documentos
                            Respuesta respaux = new Respuesta();
                            Documento docaux = iteradordocumentos.next();
                            docaux.setCodigoentregadocumento(entregadocumento.getCodigo());
                            String ruta = Recursos.SSA.CARPETAPROVEEDORES.toString() + entregadocumento.getCedulaproveedor() + "\\" + entregadocumento.getCodigo();
                            String url = Recursos.SSI.ARCHIVOSPROVEEDORESCONTEXT + entregadocumento.getCedulaproveedor() + "/" + entregadocumento.getCodigo();
                            docaux.setPathdocumento(ruta);
                            docaux.setUrldocumento(url);
                            msg = mdentregadocumento.registrarDocumento(docaux);
                            if (msg != null && msg.startsWith("2")) {
                                respaux.setNivel(2);
                            } else {
                                respaux.setNivel(1);
                            }
                            respaux.setMensaje(msg);
                            resp.add(respaux);
                            Respuesta resparchivos = new Respuesta();
                            if (marchivos.guardarArchivo(ruta, docaux.getNombredocumento(), docaux.getStreamarchivo())) {
                                resparchivos.setNivel(1);
                                resparchivos.setMensaje("Archivo " + docaux.getNombredocumento() + ", guardado correctamente");
                            } else {
                                resparchivos.setNivel(2);
                                resparchivos.setMensaje("Archivo " + docaux.getNombredocumento() + ", no guardado. Ocurrio un error");
                            }
                            resp.add(resparchivos);
                        }
                    } else {
                        System.out.println("No se, creo que nest nulo");
                    }
                }
            }

        } catch (Exception ex) {
            respuestaregentrega.setNivel(2);
            respuestaregentrega.setMensaje("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return resp;
    }

    /**
     * Operación que se encarga de realizar el ingreso / registro del
     * <strong>Entrega de Documento</strong>.
     *
     * @param entregadocumento El nuevo registro a ingresar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public ArrayList<Respuesta> modificarEntregaDocumento2(EntregaDocumento entregadocumento) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        Respuesta respuestaregentrega = new Respuesta();
        ManejadorArchivos marchivos = new ManejadorArchivos();
        String msg = "";
        try {
            /*esta es para la parte de registro de la entrega. */
            msg = mdentregadocumento.modificarEntregaDocumento(entregadocumento);
            if (msg != null && msg.startsWith("2")) {

                respuestaregentrega.setNivel(2);
            } else {
                respuestaregentrega.setNivel(1);
            }
            respuestaregentrega.setMensaje(msg);
            resp.add(respuestaregentrega);
            /*Aqui empezamos la parte de los archivos verificmos si se ha registrado algo*/
            if (entregadocumento.getCodigo() > 0) {//si se registro la entrada, entonces procedemos a los documentos
                if (entregadocumento.getDocumentos() != null) {
                    //si hay documentos
                    Iterator<Documento> iteradordocumentos = entregadocumento.getDocumentos().iterator();
                    if (iteradordocumentos != null) {
                        System.out.println("No es nulo");
                        while (iteradordocumentos.hasNext()) {
                            //cada documentos
                            Respuesta respaux = new Respuesta();
                            Documento docaux = iteradordocumentos.next();
                            docaux.setCodigoentregadocumento(entregadocumento.getCodigo());
                            String ruta = Recursos.SSA.CARPETAPROVEEDORES.toString() + entregadocumento.getCedulaproveedor() + "\\" + entregadocumento.getCodigo();
                            String url = Recursos.SSI.ARCHIVOSPROVEEDORESCONTEXT + entregadocumento.getCedulaproveedor() + "/" + entregadocumento.getCodigo();
                            docaux.setPathdocumento(ruta);
                            docaux.setUrldocumento(url);
                            msg = mdentregadocumento.registrarDocumento(docaux);
                            if (msg != null && msg.startsWith("2")) {
                                respaux.setNivel(2);
                            } else {
                                respaux.setNivel(1);
                            }
                            respaux.setMensaje(msg);
                            resp.add(respaux);
                            Respuesta resparchivos = new Respuesta();
                            if (marchivos.guardarArchivo(ruta, docaux.getNombredocumento(), docaux.getStreamarchivo())) {
                                resparchivos.setNivel(1);
                                resparchivos.setMensaje("Archivo " + docaux.getNombredocumento() + ", guardado correctamente");
                            } else {
                                resparchivos.setNivel(2);
                                resparchivos.setMensaje("Archivo " + docaux.getNombredocumento() + ", no guardado. Ocurrio un error");
                            }
                            resp.add(resparchivos);
                        }
                    } else {
                        System.out.println("No se, creo que nest nulo");
                    }
                }
            }

        } catch (Exception ex) {
            respuestaregentrega.setNivel(2);
            respuestaregentrega.setMensaje("Error: " + ex.getMessage());
            ex.printStackTrace();
        }

        return resp;
    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Entrega de Documento</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param entregadocumento El registro a eliminar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarEntregaDocumento(EntregaDocumento entregadocumento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();

        try {
            String msg = mdentregadocumento.eliminarEntregaDocumento(entregadocumento);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }

        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Entrega de Documento</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param entregadocumento El registro a eliminar.
     * @param eliminardocumentos
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarEntregaDocumento(EntregaDocumento entregadocumento, boolean eliminardocumentos) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        String respuestadocumentos = "";

        try {
            if (eliminardocumentos) {
                respuestadocumentos = mdentregadocumento.eliminarDocumento(entregadocumento);
            }

            String msg = mdentregadocumento.eliminarEntregaDocumento(entregadocumento);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        resp.setMensaje(resp.getMensaje() + "<br>" + respuestadocumentos);
        return resp;

    }

    /**
     * Operación que se encarga de realizar la eliminación del
     * <strong>Entrega de Documento</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param documento
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta eliminarDocumento(Documento documento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        try {
            String msg = mdentregadocumento.eliminarDocumento(documento);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Operación que se encarga de realizar la modificación del
     * <strong>Entrega de Documento</strong> de la base de datos. No lanza
     * excepciones, y si las hay, las registra en bitácora.
     *
     * @param entregadocumento El registro a modificar.
     * @return Un objeto Respuesta que indica el resultado de la operación.
     * @since 1.0
     */
    public Respuesta modificarEntregaDocumento(EntregaDocumento entregadocumento) {
        Respuesta resp = new Respuesta();
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();

        try {
            String msg = mdentregadocumento.modificarEntregaDocumento(entregadocumento);
            if (msg != null && !msg.startsWith("2")) {
                resp.setNivel(1);
            } else {
                resp.setNivel(2);
            }
            resp.setMensaje(msg);

        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
        }

        return resp;

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
    public ResultSet listadoEntregaDocumento(int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosEntregaDocumento mdEntregaDocumento = new ManejadorDatosEntregaDocumento();
        try {
            resp = mdEntregaDocumento.listadoEntregaDocumento(desplazamiento, paginacion);

        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return resp;
    }

    /**
     * Realiza una busqueda en la base de datos. No lanza excepciones, y si las
     * hay, las registra en bitácora.
     *
     * @param query El criterio a buscar.
     * @param desplazamiento Cantidad de registros que se deben de pasar.
     * @param paginacion La cantidad de registros a devolver.
     * @return Un ResultSet con los resultados de la busqueda
     * @since 1.0
     */
    public ResultSet busquedaEntregaDocumento(String query, String cedulaproveedor, int desplazamiento, int paginacion) {
        ResultSet resp = null;
        ManejadorDatosEntregaDocumento mdEntregaDocumento = new ManejadorDatosEntregaDocumento();
        try {
            resp = mdEntregaDocumento.busquedaEntregaDocumento(query, cedulaproveedor, desplazamiento, paginacion);

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Debes registrar algo");
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
            ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
            resp = mdentregadocumento.getCantidadFilas(query);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    /**
     * Obtiene la cantidad de registros que hay en la base de datos, con el
     * criterio qeu se pasa por parámetro. No lanza excepciones, y si las hay,
     * las registra en bitácora.
     *
     * @param query La cadena con la busqueda a evaluar.
     * @param proveedor
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
    public int getCantidadRegistros(String query, String proveedor) {
        int resp = 0;
        try {
            ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
            resp = mdentregadocumento.getCantidadFilas(query, proveedor);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public EntregaDocumento getEntregaDocumento(int codigo) {
        EntregaDocumento resp = null;
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        try {
            resp = mdentregadocumento.getEntregaDocumento(codigo);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ResultSet getDocumentosEntrega(int codigo) {
        ResultSet resp = null;
        ManejadorDatosEntregaDocumento mdentregadocumento = new ManejadorDatosEntregaDocumento();
        try {
            resp = mdentregadocumento.getDocumentosEntrega(codigo);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }
}
