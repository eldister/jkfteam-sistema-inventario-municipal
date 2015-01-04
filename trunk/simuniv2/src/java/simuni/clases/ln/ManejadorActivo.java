/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.clases.ln;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import simuni.clases.ad.ManejadorDatosActivo;
import simuni.entidades.Activo;
import simuni.entidades.ActivoArticulo;
import simuni.entidades.ActivoTransporte;
import simuni.entidades.ImagenActivo;
import simuni.entidades.Respuesta;
import simuni.entidades.archivos.ManejadorArchivos;
import simuni.entidades.mantenimientos.Departamento;
import simuni.entidades.mantenimientos.Estado;
import simuni.entidades.mantenimientos.TipoActivo;
import simuni.entidades.mantenimientos.TipoBateria;
import simuni.entidades.mantenimientos.TipoLlanta;
import simuni.entidades.mantenimientos.TipoPago;
import simuni.enums.Recursos;
import simuni.utils.UtilidadesServlet;

/**
 *
 * @author FchescO
 */
public class ManejadorActivo {

    /**
     * Función que se encarga de obtener un listado de los registros ya
     * ingreados. No lanza excepciones, y si las hay, las registra en bitácora.
     *
     * @return Un ResultSet que trae consigo los datos de la selección.
     * @since 1.0
     */
    public ArrayList<Departamento> listadoDepartamento() {
        ManejadorDepartamento mdepartamento = new ManejadorDepartamento();
        return mdepartamento.listadoDepartamento();
    }

    public ArrayList<TipoPago> listadoTipoPago() {
        ManejadorTipoPago mdtipopago = new ManejadorTipoPago();
        return mdtipopago.listadoTipoPago();
    }

    public ArrayList<Estado> listadoEstado() {
        ManejadorEstado mdestado = new ManejadorEstado();
        return mdestado.listadoEstado();
    }

    public ArrayList<TipoActivo> listadoTipoActivo() {
        ManejadorTipoActivo mtipoactivo = new ManejadorTipoActivo();
        return mtipoactivo.listadoTipoActivo();
    }

    public ArrayList<TipoBateria> listadoTipoBateria() {
        ManejadorTipoBateria mtipoactivo = new ManejadorTipoBateria();
        return mtipoactivo.listadoTipoBateria();
    }

    public ArrayList<TipoLlanta> listadoTipoLlanta() {
        ManejadorTipoLlanta mtipollanta = new ManejadorTipoLlanta();
        return mtipollanta.listadoTipoLlanta();
    }

    public ArrayList<Respuesta> registrarActivo(Activo activo, int tipoRegistro) {

        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        Respuesta defaul = new Respuesta();
        defaul.setMensaje("No se hizo nada");
        defaul.setNivel(2);
        try {
            switch (tipoRegistro) {

                case 1:
                    resp.add(registrarActivoArticulo(((ActivoArticulo) activo)));
                    break;
                case 2:
                    resp.add(defaul);
                    break;
                case 3:
                    resp = registrarActivoTransporte((ActivoTransporte) activo);
                    break;
                case 4:
                    resp = registrarActivoTransporte((ActivoTransporte) activo);
                    break;
                default:
                    resp.add(defaul);
                    break;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error  " + ex.getMessage());
        }

        return resp;

    }

    private Respuesta registrarActivoArticulo(ActivoArticulo activoarticulo) {
        Respuesta resp = new Respuesta();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        try {
            //registrar activo articulo
            String msg = mactivo.registrarActivoArticulo(activoarticulo);
            if (msg != null && msg.startsWith("2")) {
                resp.setNivel(2);
            } else {
                resp.setNivel(1);
            }
            if (activoarticulo.getCodigoActivoArticulo() > 0 && resp.getNivel() == 1) {
                if (activoarticulo.getImagenes() != null) {
                    Iterator<ImagenActivo> iteradorimagenes = activoarticulo.getImagenes().iterator();
                    if (iteradorimagenes != null && iteradorimagenes.hasNext()) {
                        ImagenActivo imaux = iteradorimagenes.next();

                        msg += mactivo.registrarImagenActivo(imaux);
                        System.out.println("La placa rara es esta " + imaux.toString());
                        if (imaux.getCodigoImagen() > 0) {
                            msg += "<br>Proceso de guardado de imagen " + mactivo.guardarImagenActivo(imaux);
                        } else {
                            System.out.println("No se puede registrar :D");
                        }
                    }
                }
            }
            resp.setMensaje(msg);
        } catch (SQLException ex) {
            resp.setNivel(2);
            resp.setMensaje("Error: " + ex.getMessage());
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<Respuesta> actualizarActivoArticulo(ActivoArticulo activoarticulo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        try {
            //actualizar activo articulo
            Respuesta ractualizarActivo = new Respuesta();
            String msg = mactivo.actualizarActivoArticulo(activoarticulo);//es actualizar
            if (msg != null && msg.startsWith("2")) {
                ractualizarActivo.setNivel(2);
            } else {
                ractualizarActivo.setNivel(1);
            }
            ractualizarActivo.setMensaje(msg);
            resp.add(ractualizarActivo);
            msg = "";
            if (activoarticulo.getCodigoActivoArticulo() > 0 && ractualizarActivo.getNivel() == 1) {
                if (activoarticulo.getImagenes() != null) {
                    Iterator<ImagenActivo> iteradorimagenes = activoarticulo.getImagenes().iterator();
                    if (iteradorimagenes != null && iteradorimagenes.hasNext()) {
                        System.out.println("Entreee imagens");
                        //eliminar imagenes actuales del activo
                        Respuesta reliminacionImagenes = new Respuesta();
                        msg = mactivo.eliminarImagenActivo(activoarticulo.getPlacaActivo());
                        if (msg != null && msg.startsWith("2")) {
                            reliminacionImagenes.setNivel(2);
                        } else {
                            reliminacionImagenes.setNivel(1);
                        }
                        reliminacionImagenes.setMensaje(msg);
                        resp.add(reliminacionImagenes);

                        ImagenActivo imaux = iteradorimagenes.next();
                        Respuesta ringresoImagen = new Respuesta();
                        msg = mactivo.registrarImagenActivo(imaux);
                        if (msg != null && msg.startsWith("2")) {
                            ringresoImagen.setNivel(2);
                        } else {
                            ringresoImagen.setNivel(1);
                        }
                        ringresoImagen.setMensaje(msg);
                        resp.add(ringresoImagen);

                        System.out.println("La placa rara es esta " + imaux.toString());
                        if (imaux.getCodigoImagen() > 0) {
                            msg += "<br>Proceso de guardado de imagen " + mactivo.guardarImagenActivo(imaux);
                        } else {
                            System.out.println("No se puede registrar :D");
                        }
                    }
                }
            }

        } catch (SQLException ex) {
            Respuesta rerror = new Respuesta();

            rerror.setNivel(2);
            rerror.setMensaje("Error: " + ex.getMessage());
            resp.add(rerror);
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<Respuesta> agregarImagenesActivo(Activo activo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        String msg = "";
        try {

            if (activo.getPlacaActivo() != null && activo.getPlacaActivo().length() > 0) {
                if (activo.getImagenes() != null) {
                    Iterator<ImagenActivo> iteradorimagenes = activo.getImagenes().iterator();
                    if (iteradorimagenes != null && iteradorimagenes.hasNext()) {
                        do {
                            System.out.println("Entreee imagens");
                            ImagenActivo imaux = iteradorimagenes.next();
                            Respuesta ringresoImagen = new Respuesta();
                            msg = mactivo.registrarImagenActivo(imaux);
                            if (msg != null && msg.startsWith("2")) {
                                ringresoImagen.setNivel(2);
                            } else {
                                ringresoImagen.setNivel(1);
                            }
                            ringresoImagen.setMensaje(msg);
                            resp.add(ringresoImagen);

                            System.out.println("La placa rara es esta " + imaux.toString());
                            if (imaux.getCodigoImagen() > 0) {
                                msg += "<br>Proceso de guardado de imagen " + mactivo.guardarImagenActivo(imaux);
                            } else {
                                System.out.println("No se puede registrar :D");
                            }
                        } while (iteradorimagenes.hasNext());
                    }
                }
            }

        } catch (SQLException ex) {
            Respuesta rerror = new Respuesta();

            rerror.setNivel(2);
            rerror.setMensaje("Error: " + ex.getMessage());
            resp.add(rerror);
            ex.printStackTrace();
        }
        return resp;
    }

    private ArrayList<Respuesta> registrarActivoTransporte(ActivoTransporte activoTransporte) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorArchivos marchivos = new ManejadorArchivos();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();

        try {
            //registrar lo de transporte
            String msg = mactivo.registrarActivoTransporte(activoTransporte);
            Respuesta respregtran = new Respuesta();
            if (msg != null && msg.startsWith("2")) {
                respregtran.setNivel(2);
            } else {
                respregtran.setNivel(1);
            }
            respregtran.setMensaje(msg);
            resp.add(respregtran);

            System.out.println("upss el respondio " + respregtran.getNivel());
            //si fue correcto entonces registrar las llantas
            if (activoTransporte.getCodigoActivoTransporte() > 0 && respregtran.getNivel() == 1) {
                System.out.println("Pase primer nivel" + activoTransporte.getCodigoActivoTransporte());
                if (activoTransporte.getLlantas() != null) {
                    System.out.println("Pase segundo  nivel");

                    Iterator<TipoLlanta> iteradortipollanta = activoTransporte.getLlantas().iterator();
                    if (iteradortipollanta != null) {
                        while (iteradortipollanta.hasNext()) {

                            TipoLlanta tllantaux = iteradortipollanta.next();
                            Respuesta respuesta_registrollanta = new Respuesta();
                            msg = mactivo.registrarLlantaActivo(tllantaux, activoTransporte.getCodigoActivoTransporte());
                            if (msg != null && msg.startsWith("2")) {
                                respuesta_registrollanta.setNivel(2);
                            } else {
                                respuesta_registrollanta.setNivel(1);
                            }
                            respuesta_registrollanta.setMensaje(tllantaux.getDescripcion() + "   " + msg);
                            resp.add(respuesta_registrollanta);
                        }
                    }
                }
            }
            /*empezamos con el registro de llantas e imagenes*/
            if (activoTransporte.getCodigoActivoTransporte() > 0 && respregtran.getNivel() == 1) {
                if (activoTransporte.getImagenes() != null) {
                    Iterator<ImagenActivo> iteradorimagenes = activoTransporte.getImagenes().iterator();
                    if (iteradorimagenes != null) {
                        while (iteradorimagenes.hasNext()) {
                            //registrar en base de datos las imagenes
                            Respuesta respaux = new Respuesta();
                            ImagenActivo imaux = iteradorimagenes.next();
                            imaux.setCodigoActivo(activoTransporte.getPlacaActivo());
                            msg = mactivo.registrarImagenActivo(imaux);
                            if (msg != null && msg.startsWith("2")) {
                                respaux.setNivel(2);
                            } else {
                                respaux.setNivel(1);
                            }
                            respaux.setMensaje(msg);
                            resp.add(respaux);

                            //registrar la imagen en el disco si se realizo de manera correcta
                            if (imaux.getCodigoImagen() > 0) {
                                Respuesta resparchivos = new Respuesta();
                                if (marchivos.guardarArchivo(imaux.getPathDocumento(), imaux.getNombreArchivo(), imaux.getStreamArchivo())) {
                                    resparchivos.setNivel(1);
                                    resparchivos.setMensaje("Imagen " + imaux.getNombreArchivo() + ", guardado correctamente");
                                } else {
                                    resparchivos.setNivel(2);
                                    resparchivos.setMensaje("Imagen " + imaux.getNombreArchivo() + ", no guardado. Ocurrio un error");
                                }
                                resp.add(resparchivos);
                            }

                        }
                    }
                }

            }

        } catch (Exception ex) {
            Respuesta resperror = new Respuesta();
            resperror.setNivel(2);
            resperror.setMensaje("Error: " + ex.getMessage());
            resp.add(resperror);
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<Respuesta> actualizarActivoTransporte(ActivoTransporte activoTransporte) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorArchivos marchivos = new ManejadorArchivos();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();

        try {
            //registrar lo de transporte
            String msg = mactivo.actualizarActivoTransporte(activoTransporte);
            Respuesta respregtran = new Respuesta();
            if (msg != null && msg.startsWith("2")) {
                respregtran.setNivel(2);
            } else {
                respregtran.setNivel(1);
            }
            respregtran.setMensaje(msg);
            resp.add(respregtran);

            System.out.println("upss el respondio " + respregtran.getNivel() + ";" + activoTransporte.getCodigoActivoTransporte());
            //si fue correcto entonces registrar las llantas
            if (activoTransporte.getCodigoActivoTransporte() > 0 && respregtran.getNivel() == 1) {
                System.out.println("Pase primer nivel" + activoTransporte.getCodigoActivoTransporte());
                if (activoTransporte.getLlantas() != null) {
                    System.out.println("Pase segundo  nivel");

                    Iterator<TipoLlanta> iteradortipollanta = activoTransporte.getLlantas().iterator();
                    if (iteradortipollanta != null) {
                        if (iteradortipollanta.hasNext()) {
                            //eliminar llantas activo
                            mactivo.eliminarLlantaVehiculo(activoTransporte.getCodigoActivoTransporte());
                            do {
                                TipoLlanta tllantaux = iteradortipollanta.next();
                                Respuesta respuesta_registrollanta = new Respuesta();
                                msg = mactivo.registrarLlantaActivo(tllantaux, activoTransporte.getCodigoActivoTransporte());
                                if (msg != null && msg.startsWith("2")) {
                                    respuesta_registrollanta.setNivel(2);
                                } else {
                                    respuesta_registrollanta.setNivel(1);
                                }
                                respuesta_registrollanta.setMensaje(tllantaux.getDescripcion() + "   " + msg);
                                resp.add(respuesta_registrollanta);

                            } while (iteradortipollanta.hasNext());
                        }
                    }
                }
                /*empezamos con el registro de llantas e imagenes*/
                if (activoTransporte.getCodigoActivoTransporte() > 0 && respregtran.getNivel() == 1) {
                    if (activoTransporte.getImagenes() != null) {
                        Iterator<ImagenActivo> iteradorimagenes = activoTransporte.getImagenes().iterator();
                        if (iteradorimagenes != null) {
                            if (iteradorimagenes.hasNext()) {
                                //eliminar imagenes
                                mactivo.eliminarImagenActivo(activoTransporte.getPlacaActivo());
                                do {
                                    //registrar en base de datos las imagenes
                                    Respuesta respaux = new Respuesta();
                                    ImagenActivo imaux = iteradorimagenes.next();
                                    imaux.setCodigoActivo(activoTransporte.getPlacaActivo());
                                    msg = mactivo.registrarImagenActivo(imaux);
                                    if (msg != null && msg.startsWith("2")) {
                                        respaux.setNivel(2);
                                    } else {
                                        respaux.setNivel(1);
                                    }
                                    respaux.setMensaje(msg);
                                    resp.add(respaux);

                                    //registrar la imagen en el disco si se realizo de manera correcta
                                    if (imaux.getCodigoImagen() > 0) {
                                        Respuesta resparchivos = new Respuesta();
                                        if (marchivos.guardarArchivo(imaux.getPathDocumento(), imaux.getNombreArchivo(), imaux.getStreamArchivo())) {
                                            resparchivos.setNivel(1);
                                            resparchivos.setMensaje("Imagen " + imaux.getNombreArchivo() + ", guardado correctamente");
                                        } else {
                                            resparchivos.setNivel(2);
                                            resparchivos.setMensaje("Imagen " + imaux.getNombreArchivo() + ", no guardado. Ocurrio un error");
                                        }
                                        resp.add(resparchivos);
                                    }
                                } while (iteradorimagenes.hasNext());

                            }
                        }
                    }

                }

            }
        } catch (Exception ex) {
            Respuesta resperror = new Respuesta();
            resperror.setNivel(2);
            resperror.setMensaje("Error: " + ex.getMessage());
            resp.add(resperror);
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean existePlacaActivo(String placaactivo) {
        boolean resp = false;
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        try {
            resp = mdactivo.existePlacaActivo(placaactivo);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean existePlacaVehiculo(String placavehiculo) {
        boolean resp = false;
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        try {
            resp = mdactivo.existePlacaVehiculo(placavehiculo);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean isRegistroArticulo(String codigoRegistro) {
        boolean resp = false;
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        try {
            resp = mdactivo.isRegistroArticulo(codigoRegistro);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean isRegistroTransporte(String codigoRegistro) {
        boolean resp = false;
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        try {
            resp = mdactivo.isRegistroTransporte(codigoRegistro);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<ImagenActivo> getImagenesActivo(String codigoRegistro) {
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        ArrayList<ImagenActivo> resp = null;
        try {
            resp = mdactivo.getImagenesActivo(codigoRegistro);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public boolean existeConsecutivoTipoVehiculo(String consecutivotipovehiculo) {
        boolean resp = false;
        ManejadorDatosActivo mdactivo = new ManejadorDatosActivo();
        try {
            resp = mdactivo.existeConsecutivoTipoVehiculo(consecutivotipovehiculo);

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
    public ResultSet busquedaActivo(String query, int desplazamiento, int paginacion, boolean ocultos) {
        ResultSet resp = null;
        ManejadorDatosActivo mdActivo = new ManejadorDatosActivo();
        try {
            resp = mdActivo.busquedaActivo(query, desplazamiento, paginacion, ocultos);

        } catch (SQLException ex) {

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
     * @param ocultos
     * @return Un entero con la cantidad de registros.
     * @since 1.0
     */
    public int getCantidadRegistros(String query, boolean ocultos) {
        int resp = 0;
        try {
            ManejadorDatosActivo mdproveedor = new ManejadorDatosActivo();
            resp = mdproveedor.getCantidadFilas(query, ocultos);

        } catch (SQLException ex) {
            UtilidadesServlet.registrarErrorSistema("getCantidadRegistrosActivosArticulos", ex.getMessage());
            ex.printStackTrace();

        }
        return resp;
    }

    public ActivoArticulo getActivoArticulo(String codigoRegistro) {
        ActivoArticulo resp = null;
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        try {
            resp = mactivo.getActivoArticulo(codigoRegistro);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ActivoTransporte getActivoTransporte(String codigoRegistro) {
        ActivoTransporte resp = null;
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        try {
            resp = mactivo.getActivoTransporte(codigoRegistro);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return resp;
    }

    public ArrayList<Respuesta> eliminarActivoArticulo(ActivoArticulo activo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        String msg = "";
       Respuesta rproceso=new Respuesta();
       try {
           
            msg = mactivo.eliminarActivoArticulo(activo);
            if (msg != null && msg.startsWith("2")) {
                rproceso.setNivel(2);
            } else {
                rproceso.setNivel(1);
            }
            rproceso.setMensaje(msg);
        } catch (SQLException ex) {
            rproceso.setMensaje(ex.getMessage());
            rproceso.setNivel(2);
        }
       resp.add(rproceso);
        return resp;
    }
    public ArrayList<Respuesta> desactivarActivoArticulo(ActivoArticulo activo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        String msg = "";
       Respuesta rproceso=new Respuesta();
       try {
           
            msg = mactivo.desactivarActivoArticulo(activo);
            if (msg != null && msg.startsWith("2")) {
                rproceso.setNivel(2);
            } else {
                rproceso.setNivel(1);
            }
            rproceso.setMensaje(msg);
        } catch (SQLException ex) {
            rproceso.setMensaje(ex.getMessage());
            rproceso.setNivel(2);
        }
       resp.add(rproceso);
        return resp;
    }
    
        public ArrayList<Respuesta> eliminarActivoTransporte(ActivoTransporte activo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        String msg = "";
       Respuesta rproceso=new Respuesta();
       try {
           
            msg = mactivo.eliminarActivoTransporte(activo);
            if (msg != null && msg.startsWith("2")) {
                rproceso.setNivel(2);
            } else {
                rproceso.setNivel(1);
            }
            rproceso.setMensaje(msg);
        } catch (SQLException ex) {
            rproceso.setMensaje(ex.getMessage());
            rproceso.setNivel(2);
        }
       resp.add(rproceso);
        return resp;
    }
    public ArrayList<Respuesta> desactivarActivoTransporte(ActivoTransporte activo) {
        ArrayList<Respuesta> resp = new ArrayList<Respuesta>();
        ManejadorDatosActivo mactivo = new ManejadorDatosActivo();
        String msg = "";
       Respuesta rproceso=new Respuesta();
       try {
           
            msg = mactivo.desactivarActivoTranporte(activo);
            if (msg != null && msg.startsWith("2")) {
                rproceso.setNivel(2);
            } else {
                rproceso.setNivel(1);
            }
            rproceso.setMensaje(msg);
        } catch (SQLException ex) {
            rproceso.setMensaje(ex.getMessage());
            rproceso.setNivel(2);
        }
       resp.add(rproceso);
        return resp;
    }
}
