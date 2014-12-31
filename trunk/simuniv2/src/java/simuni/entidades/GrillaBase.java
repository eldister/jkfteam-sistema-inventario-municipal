/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.entidades;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import static java.sql.Types.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author FchescO
 */
public class GrillaBase {

    private boolean opcionagregar = true;
    private boolean opcionactualizar = true;
    private boolean opcionimprimir = true;
    private boolean opcionver = true;
    private boolean opcioneliminar = true;
    private boolean opcionverpaginacion = true;
    private boolean opciommostrarfiltro = true;
    private boolean opcionverdocumentos = false;
    private boolean opcionverdetalles = false;
    private boolean opcionseleccionarpaginacion = false;
    private boolean usarAgregarself = false;
    private boolean usarMostrarOcultos = false;
    private int paginaciondefault = 5;
    private String mensajeplaceholderbusqueda = "Ingrese su criterio de busqueda";
    private String urlpaginacionlink = "";
    private String urlagregaropcionagregar = "";
    private String busquedalabel = "Buscar Activo";
    private String mostrarOcultosMsg = "Ocultos";
    private boolean usarControles = true;

    public String renderizar(Object[] criteriofiltro, Object[] encabezados, ArrayList<Object[]> filas, int cantidadpag) {
        StringBuilder resp = new StringBuilder();
        resp.append("<div id='sm_grilla_maincontainer'>");

        resp.append("<div class='sm_div_operacionesbusquedacontainer'>");
        resp.append("   <div class='sm_div_rowsim'>");

        //SECCION DE FILTROO POR CATEGORIAS  
        if (isOpciommostrarfiltro()) {
            resp.append("    <div class='sm_div_colsim'>");
            resp.append("     <span class='sm_div_label'>Filtar por</span>");
            resp.append("         <select id='sm_div_grillageneral_filtrobusqueda'>");
            resp.append("           <option>-- Seleccionar --</option> ");
            for (int a = 0; a < criteriofiltro.length; a++) {
                resp.append("           <option> ");
                resp.append(criteriofiltro[a]);
                resp.append("           </option> ");
            }
            resp.append("</select>");
            resp.append("</div> ");
        }
        //SECCION DE BUSQUEDA POR TEXTO
        resp.append(" <div class='sm_div_colsim'>");

        resp.append("    <div id='sm_div_busquedacontainer'> ");
        resp.append("       <span class='sm_div_label'>");
        resp.append(busquedalabel);
        resp.append("</span>");
        resp.append("      <input id='sm_div_txtcriteriobusquedagrilla' type='text' placeholder='");
        resp.append(getMensajeplaceholderbusqueda());
        resp.append("'>");

        //SECCION BOTON BUSQUDA
        resp.append("      <div id='sm__divgrillageneral_botonbusqueda'>&nbsp;</div>");
        resp.append("  </div>");
        resp.append("  </div> ");

        //SECCION ETIQUETA AGREGAR BOTON 'AGREGAR'
        resp.append("   <div class='sm_div_colsim'>");
        resp.append("      <div id='sm_div_agregaritemcontainer'>");
        resp.append("          <a href='");
        resp.append(getUrlagregaropcionagregar());
        resp.append("'>Agregar</a>");
        resp.append("     </div>");
        resp.append("  </div> ");

        //FIN DE FILA BUSQUEDA
        resp.append(" </div>");
        //FIN DE LA SECCION BUSQEUDA
        resp.append(" </div>");
        resp.append("<div class='sm_div_puratablecontainer' id='sm_div_puratablecontainertarget'>");///EESTE CAMBIO DE ULTIMA HORA
        resp.append(" <table class='sm_div_tablemain' >");
        resp.append("  <thead><!--seccion solo encabezado-->");
        resp.append("     <tr class='sm_thead_filaencabezado'>");

        for (int a = 0; a < encabezados.length; a++) {
            resp.append("<th class='sm_tr_columnaencabezado'>");
            resp.append(encabezados[a]);
            resp.append("</th>");
        }

        resp.append(" <th class='sm_tr_columnaencabezado'>Procesos</th>");
        resp.append("        </tr>");
        resp.append("   </thead>");
        resp.append("    <tbody>");

        //SECCION CUERPO DE LA GRILLA.. LAS FILAS
        Iterator<Object[]> iter = filas.iterator();
        if (iter.hasNext()) {
            do {
                resp.append("<tr class='sm_tbody_filadatos'>");
                Object[] row = iter.next();
                for (int a = 0; a < row.length; a++) {
                    resp.append("<td class='sm_tr_columnadatos'>");
                    resp.append(row[a]);

                    //si estamos en la primer fila se guarda en una variable el identificador de la fila
                    if (a == 0) {
                        resp.append(" <input type='hidden' value='");
                        resp.append(row[a]);
                        resp.append("'>");
                    }
                    resp.append("</td>");

                    //SECCION DE OPERACIONES SOBRE ACTIVOS
                    //si estamos en la ultima fila agregamos los procesos si se requiere
                    if (a == row.length - 1) {
                        resp.append("  <td class='sm_tr_columnadatos'>");
                        resp.append("          <div class='sm_td_operacionescontainer'>");
                        resp.append("   <div class='sm_div_rowsim'>");

                        if (isOpcionactualizar()) {

                            resp.append("             <div class='sm_div_colsim sm_div_grillamodificar'  title='Modificar información'>");
                            resp.append("              &nbsp;");
                            resp.append("        </div>  ");
                        }

                        if (isOpcionimprimir()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaimprimir' title='Imprimir reporte'>");
                            resp.append("&nbsp;");
                            resp.append("</div>");
                        }

                        if (isOpcionver()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaverimagen' title='ver imagen de activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }

                        if (isOpcioneliminar()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaeliminar' title='Eliminar activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }

                        resp.append("</div>");
                        resp.append("</div>");
                        resp.append("</td>");
                        resp.append("</tr>");

                    }

                }
            } while (iter.hasNext());
        } else {
            resp.append("<tr class='sm_tbody_filadatos'>");
            resp.append("<div style='text-align:center;'>No se encontró nada!!</div>");

            resp.append("</tr>");
        }

        resp.append("    </tbody>  ");
        resp.append("   </table>");
        //find e seccion de tablas
        resp.append("</div>");//CAMBIO DE ULTIMA HORA CIERRE LLAVE
        //SECCION PAGINACION
        resp.append(" <div class='sm_div_paginaciongrillacontainer'>");
        resp.append("    <div id='sm_div_paginacionelementscontainer'>");
        for (int a = 1; a <= cantidadpag; a++) {
            resp.append("  <span class='sm_div_elementpagcontainer'>");
            resp.append("    <a href='");
            resp.append(getUrlpaginacionlink());
            resp.append("&pag=");
            resp.append(a);
            resp.append("'>");
            resp.append(a);
            resp.append(" </a>");
            resp.append("   </span> ");
        }

        resp.append("     </div>");

        //SECCION DONDE SE DEFINE SI SE QUIERE O NO MOSTRAR LA DEFINICION DE PAGINACION
        resp.append("   <div id='sm_divpaginaciondefiner'>");
        resp.append("     Pag.<input id='sm_grilla_div_txtpaginacion' placeholder='#' type='text'>");
        resp.append("   </div>");
        resp.append("   </div>");

        resp.append(" </div>  "); //FIN DE maincontainer          
        return resp.toString();

    }

    /**
     * @return the opcionagregar
     */
    public boolean isOpcionagregar() {
        return opcionagregar;
    }

    /**
     * @param opcionagregar the opcionagregar to set
     */
    public void setOpcionagregar(boolean opcionagregar) {
        this.opcionagregar = opcionagregar;
    }

    /**
     * @return the opcionactualizar
     */
    public boolean isOpcionactualizar() {
        return opcionactualizar;
    }

    /**
     * @param opcionactualizar the opcionactualizar to set
     */
    public void setOpcionactualizar(boolean opcionactualizar) {
        this.opcionactualizar = opcionactualizar;
    }

    /**
     * @return the opcionimprimir
     */
    public boolean isOpcionimprimir() {
        return opcionimprimir;
    }

    /**
     * @param opcionimprimir the opcionimprimir to set
     */
    public void setOpcionimprimir(boolean opcionimprimir) {
        this.opcionimprimir = opcionimprimir;
    }

    /**
     * @return the opcionver
     */
    public boolean isOpcionver() {
        return opcionver;
    }

    /**
     * @param opcionver the opcionver to set
     */
    public void setOpcionver(boolean opcionver) {
        this.opcionver = opcionver;
    }

    /**
     * @return the opcioneliminar
     */
    public boolean isOpcioneliminar() {
        return opcioneliminar;
    }

    /**
     * @param opcioneliminar the opcioneliminar to set
     */
    public void setOpcioneliminar(boolean opcioneliminar) {
        this.opcioneliminar = opcioneliminar;
    }

    /**
     * @return the opcionverpaginacion
     */
    public boolean isOpcionverpaginacion() {
        return opcionverpaginacion;
    }

    /**
     * @param opcionverpaginacion the opcionverpaginacion to set
     */
    public void setOpcionverpaginacion(boolean opcionverpaginacion) {
        this.opcionverpaginacion = opcionverpaginacion;
    }

    /**
     * @return the paginaciondefault
     */
    public int getPaginaciondefault() {
        return paginaciondefault;
    }

    /**
     * @param paginaciondefault the paginaciondefault to set
     */
    public void setPaginaciondefault(int paginaciondefault) {
        this.paginaciondefault = paginaciondefault;
    }

    /**
     * @return the mensajeplaceholderbusqueda
     */
    public String getMensajeplaceholderbusqueda() {
        return mensajeplaceholderbusqueda;
    }

    /**
     * @param mensajeplaceholderbusqueda the mensajeplaceholderbusqueda to set
     */
    public void setMensajeplaceholderbusqueda(String mensajeplaceholderbusqueda) {
        this.mensajeplaceholderbusqueda = mensajeplaceholderbusqueda;
    }

    /**
     * @return the urlpaginacionlink
     */
    public String getUrlpaginacionlink() {
        return urlpaginacionlink;
    }

    /**
     * @param urlpaginacionlink the urlpaginacionlink to set
     */
    public void setUrlpaginacionlink(String urlpaginacionlink) {
        this.urlpaginacionlink = urlpaginacionlink;
    }

    /**
     * @return the urlagregaropcionagregar
     */
    public String getUrlagregaropcionagregar() {
        return urlagregaropcionagregar;
    }

    /**
     * @param urlagregaropcionagregar the urlagregaropcionagregar to set
     */
    public void setUrlagregaropcionagregar(String urlagregaropcionagregar) {
        this.urlagregaropcionagregar = urlagregaropcionagregar;
    }

    /**
     * @return the opciommostrarfiltro
     */
    public boolean isOpciommostrarfiltro() {
        return opciommostrarfiltro;
    }

    /**
     * @param opciommostrarfiltro the opciommostrarfiltro to set
     */
    public void setOpciommostrarfiltro(boolean opciommostrarfiltro) {
        this.opciommostrarfiltro = opciommostrarfiltro;
    }

    public String renderizarSoloCuerpoTabla(Object[] encabezados, ArrayList<Object[]> filas, int cantidadpaginas) {
        StringBuilder resp = new StringBuilder();
        resp.append("<div class='sm_div_puratablecontainer'>");///EESTE CAMBIO DE ULTIMA HORA
        resp.append(" <table class='sm_div_tablemain' >");
        resp.append("  <thead><!--seccion solo encabezado-->");
        resp.append("     <tr class='sm_thead_filaencabezado'>");

        for (int a = 0; a < encabezados.length; a++) {
            resp.append("<th class='sm_tr_columnaencabezado'>");
            resp.append(encabezados[a]);
            resp.append("</th>");
        }
        resp.append(" <th class='sm_tr_columnaencabezado'>Procesos</th>");
        resp.append("        </tr>");
        resp.append("   </thead>");
        resp.append("    <tbody>");

        //SECCION CUERPO DE LA GRILLA.. LAS FILAS
        Iterator<Object[]> iter = filas.iterator();
        if (iter.hasNext()) {
            do {
                resp.append("<tr class='sm_tbody_filadatos'>");
                Object[] row = iter.next();
                for (int a = 0; a < row.length; a++) {
                    resp.append("<td class='sm_tr_columnadatos'>");
                    resp.append(row[a]);

                    //si estamos en la primer fila se guarda en una variable el identificador de la fila
                    if (a == 0) {
                        resp.append(" <input type='hidden' value='");
                        resp.append(row[a]);
                        resp.append("'>");
                    }
                    resp.append("</td>");

                    //SECCION DE OPERACIONES SOBRE ACTIVOS
                    //si estamos en la ultima fila agregamos los procesos si se requiere
                    if (a == row.length - 1) {
                        resp.append("  <td class='sm_tr_columnadatos'>");
                        resp.append("          <div class='sm_td_operacionescontainer'>");
                        resp.append("   <div class='sm_div_rowsim'>");

                        if (isOpcionactualizar()) {

                            resp.append("             <div class='sm_div_colsim sm_div_grillamodificar'  title='Modificar información'>");
                            resp.append("              &nbsp;");
                            resp.append("        </div>  ");
                        }

                        if (isOpcionimprimir()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaimprimir' title='Imprimir reporte'>");
                            resp.append("&nbsp;");
                            resp.append("</div>");
                        }

                        if (isOpcionver()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaverimagen' title='ver imagen de activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }

                        if (isOpcioneliminar()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaeliminar' title='Eliminar activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }

                        resp.append("</div>");
                        resp.append("</div>");
                        resp.append("</td>");
                        resp.append("</tr>");

                    }

                }
            } while (iter.hasNext());
        } else {
            resp.append("<tr class='alguna'>");
            resp.append("No se encontró nada aquí!!");

            resp.append("</tr>");
        }

        resp.append("    </tbody>  ");
        resp.append("   </table>");
        //find e seccion de tablas
        resp.append("</div>");///EESTE CAMBIO DE ULTIMA HORA CIERRE DE LLAVE

        return resp.toString();

    }

    /**
     * @return the busquedalabel
     */
    public String getBusquedalabel() {
        return busquedalabel;
    }

    /**
     * @param busquedalabel the busquedalabel to set
     */
    public void setBusquedalabel(String busquedalabel) {
        this.busquedalabel = busquedalabel;
    }

    /**
     * @return the usarAgregarself
     */
    public boolean isUsarAgregarself() {
        return usarAgregarself;
    }

    /**
     * @param usarAgregarself the usarAgregarself to set
     */
    public void setUsarAgregarself(boolean usarAgregarself) {
        this.usarAgregarself = usarAgregarself;
    }

    public String renderizar(ResultSet resultset, int cantidadpag) throws SQLException {
        StringBuilder resp = new StringBuilder();
        resp.append("<div id='sm_grilla_maincontainer'>");

        resp.append("<div class='sm_div_operacionesbusquedacontainer'>");
        resp.append("   <div class='sm_div_rowsim'>");

        //SECCION DE BUSQUEDA POR TEXTO
        resp.append(" <div class='sm_div_colsim'>");

        resp.append("    <div id='sm_div_busquedacontainer'> ");
        resp.append("       <span class='sm_div_label'>");
        resp.append(busquedalabel);
        resp.append("</span>");
        resp.append("      <input id='sm_div_txtcriteriobusquedagrilla' class='form-control' type='text' placeholder='");
        resp.append(getMensajeplaceholderbusqueda());
        resp.append("'>");

        //SECCION BOTON BUSQUDA
        resp.append("      <div id='sm__divgrillageneral_botonbusqueda'>&nbsp;</div>");
        if (usarMostrarOcultos) {
            resp.append("<input type='checkbox' id='sm_checkgrillageneral_mostrarinactivos'>");
            resp.append(mostrarOcultosMsg);
        }
        resp.append("  </div>");
        resp.append("  </div> ");

        //SECCION ETIQUETA AGREGAR BOTON 'AGREGAR'
        if (isUsarControles()) {
            resp.append("   <div class='sm_div_colsim'>");
            resp.append("      <div id='sm_div_agregaritemcontainer'>");
            if (isUsarAgregarself()) {
                resp.append("          <span class='sm_div_grillaagregar'>Agregar</span>");
            } else {
                resp.append("          <a href='");
                resp.append(getUrlagregaropcionagregar());
                resp.append("'>Agregar</a>");
            }

            resp.append("     </div>");
            resp.append("  </div> ");
        }
        //FIN DE FILA BUSQUEDA
        resp.append(" </div>");
        //FIN DE LA SECCION BUSQEUDA
        resp.append(" </div>");
        resp.append("<div class='sm_div_puratablecontainer' id='sm_div_puratablecontainertarget'>");///EESTE CAMBIO DE ULTIMA HORA

        if (resultset != null) {
            //INICIA SECCION TABLAS cabecera
            ResultSetMetaData metadatos = resultset.getMetaData();
            int cantidadcolumnas = metadatos.getColumnCount();

            resp.append(" <table class=' table table-hover table-bordered table-striped' >");
            resp.append("  <thead><!--seccion solo encabezado-->");
            resp.append("     <tr class='sm_thead_filaencabezado'>");

            for (int a = 1; a <= cantidadcolumnas; a++) {
                resp.append("<th class='sm_tr_columnaencabezado'>");
                resp.append(metadatos.getColumnLabel(a));
                resp.append("</th>");
            }
            if (isUsarControles()) {
                resp.append(" <th class='sm_tr_columnaencabezado'>Procesos</th>");
            }
            resp.append("        </tr>");
            resp.append("   </thead>");

            //inicia cuerpo de la tabla
            resp.append("    <tbody>");

            //SECCION CUERPO DE LA GRILLA.. LAS FILAS
            if (resultset.next()) {
                do {
                    resp.append("<tr class='sm_tbody_filadatos'>");

                    for (int a = 1; a <= cantidadcolumnas; a++) {
                        resp.append("<td class='sm_tr_columnadatos'>");
                        System.out.println(metadatos.getColumnType(a));
                        switch (metadatos.getColumnType(a)) {
                            case VARCHAR:
                                resp.append(resultset.getString(a));
                                break;
                            case INTEGER:
                                resp.append(resultset.getInt(a));
                                break;
                            case DECIMAL:
                                resp.append(resultset.getFloat(a));
                                break;
                            case DATE:
                                resp.append(resultset.getDate(a));
                                break;
                            case DOUBLE:
                                resp.append(resultset.getDouble(a));
                                break;
                            case TIMESTAMP:
                                resp.append(resultset.getTimestamp(a));
                                break;
                            case CHAR:
                                resp.append(resultset.getString(a));
                                break;
                            default:
                                resp.append(resultset.getInt(a));
                                break;
                        }

                        //si estamos en la primer fila se guarda en una variable el identificador de la fila
                        if (a == 1) {
                            resp.append(" <input type='hidden' value='");

                            switch (metadatos.getColumnType(a)) {
                                case VARCHAR:
                                    resp.append(resultset.getString(a));
                                    break;
                                case INTEGER:
                                    resp.append(resultset.getInt(a));
                                    break;
                                case DECIMAL:
                                    resp.append(resultset.getFloat(a));
                                    break;
                                case DATE:
                                    resp.append(resultset.getDate(a));
                                    break;
                                case DOUBLE:
                                    resp.append(resultset.getDouble(a));
                                    break;
                                case TIMESTAMP:
                                    resp.append(resultset.getTimestamp(a));
                                    break;
                                case CHAR:
                                    resp.append(resultset.getString(a));
                                    break;
                                default:
                                    resp.append(resultset.getInt(a));
                                    break;
                            }
                            resp.append("'>");
                        }

                        resp.append("</td>");
                    }
                    //SECCION DE OPERACIONES SOBRE ACTIVOS
                    //si estamos en la ultima fila agregamos los procesos si se requiere
                    if (isUsarControles()) {
                        resp.append("  <td class='sm_tr_columnadatos'>");
                        resp.append(" <div class='sm_td_operacionescontainer'>");
                        resp.append("   <div class='sm_div_rowsim'>");

                        if (isOpcionactualizar()) {

                            resp.append("             <div class='sm_div_colsim sm_div_grillamodificar'  title='Modificar información'>");
                            resp.append("              &nbsp;");
                            resp.append("        </div>  ");
                        }

                        if (isOpcionimprimir()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaimprimir' title='Imprimir reporte'>");
                            resp.append("&nbsp;");
                            resp.append("</div>");
                        }

                        if (isOpcionver()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaverimagen' title='ver imagen de activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }
                        if (isOpcionverdocumentos()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaverdocumentos' title='Ver / Cargar documentos de este registro'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }
                        if (isOpcionverdetalles()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaverdetalle' title='Ver Detalles del Registro'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }
                        if (isOpcioneliminar()) {

                            resp.append("<div class='sm_div_colsim sm_div_grillaeliminar' title='Eliminar activo'>");
                            resp.append("&nbsp;");
                            resp.append("</div>  ");
                        }

                        resp.append("</div>");
                        resp.append("</div>");
                        resp.append("</td>");
                        resp.append("</tr>");
                    }
                } while (resultset.next());
            } else {
                resp.append("<tr class='sm_tbody_filanoresult'>");
                resp.append("<td colspan=");
                resp.append(cantidadcolumnas + 1);
                resp.append(">");
                resp.append("<div class='div_noresultcontainer'>No se encontró nada!!</div>");
                resp.append("</td>");
                resp.append("</tr>");
            }

            resp.append("    </tbody>  ");
            resp.append("   </table>");
            //find e seccion de tablas
            resultset.close();
        }
        //SECCION PAGINACION
        resp.append(" <div class='sm_div_paginaciongrillacontainer'>");
        resp.append("    <div id='sm_div_paginacionelementscontainer'>");
        for (int a = 1; a <= cantidadpag; a++) {
            resp.append("  <span class='sm_div_elementpagcontainer'>");
            resp.append("    <a class='sm_div_elementlinkpag'  href='");
            resp.append(getUrlpaginacionlink());
            resp.append("&pag=");
            resp.append(a);
            resp.append("'>");
            resp.append(a);
            resp.append(" </a>");
            resp.append("   </span> ");
        }

        resp.append("     </div>");

        //SECCION DONDE SE DEFINE SI SE QUIERE O NO MOSTRAR LA DEFINICION DE PAGINACION
        if (isOpcionseleccionarpaginacion()) {
            resp.append("   <div id='sm_divpaginaciondefiner'>");
            resp.append("     Pag.<input id='sm_grilla_div_txtpaginacion' placeholder='#' type='text'>");
            resp.append("   </div>");
        }
        resp.append("   </div>");

        resp.append("</div>");//CAMBIO DE ULTIMA HORA CIERRE LLAVE

        resp.append(" </div>  "); //FIN DE maincontainer          
        return resp.toString();

    }

    public String renderizarSoloCuerpoTabla(ResultSet resultset, int cantidadpaginas) throws SQLException {
        StringBuilder resp = new StringBuilder();

        ResultSetMetaData metadatos = resultset.getMetaData();
        int cantidadcolumnas = metadatos.getColumnCount();

        resp.append(" <table class='sm_div_tablemain' >");
        resp.append("  <thead><!--seccion solo encabezado-->");
        resp.append("     <tr class='sm_thead_filaencabezado'>");

        for (int a = 1; a <= cantidadcolumnas; a++) {
            resp.append("<th class='sm_tr_columnaencabezado'>");
            resp.append(metadatos.getColumnLabel(a));
            resp.append("</th>");
        }
        if (isUsarControles()) {
            resp.append(" <th class='sm_tr_columnaencabezado'>Procesos</th>");
        }
        resp.append("        </tr>");
        resp.append("   </thead>");

        //inicia cuerpo de la tabla
        resp.append("    <tbody>");

        //SECCION CUERPO DE LA GRILLA.. LAS FILAS
        if (resultset.next()) {
            do {
                resp.append("<tr class='sm_tbody_filadatos'>");

                for (int a = 1; a <= cantidadcolumnas; a++) {
                    resp.append("<td class='sm_tr_columnadatos'>");
                    switch (metadatos.getColumnType(a)) {
                        case VARCHAR:
                            resp.append(resultset.getString(a));
                            break;
                        case INTEGER:
                            resp.append(resultset.getInt(a));
                            break;
                        case DECIMAL:
                            resp.append(resultset.getFloat(a));
                            break;
                        case DATE:
                            resp.append(resultset.getDate(a));
                            break;
                        case DOUBLE:
                            resp.append(resultset.getDouble(a));
                            break;
                        case TIMESTAMP:
                            resp.append(resultset.getTimestamp(a));
                            break;
                        case CHAR:
                            resp.append(resultset.getString(a));
                            break;
                        default:
                            resp.append(resultset.getInt(a));
                            break;
                    }

                    //si estamos en la primer fila se guarda en una variable el identificador de la fila
                    if (a == 1) {
                        resp.append(" <input type='hidden' value='");
                        switch (metadatos.getColumnType(a)) {
                            case VARCHAR:
                                resp.append(resultset.getString(a));
                                break;
                            case INTEGER:
                                resp.append(resultset.getInt(a));
                                break;
                            case DECIMAL:
                                resp.append(resultset.getFloat(a));
                                break;
                            case DATE:
                                resp.append(resultset.getDate(a));
                                break;
                            case DOUBLE:
                                resp.append(resultset.getDouble(a));
                                break;
                            case TIMESTAMP:
                                resp.append(resultset.getTimestamp(a));
                                break;
                            case CHAR:
                                resp.append(resultset.getString(a));
                                break;
                            default:
                                resp.append(resultset.getInt(a));
                                break;
                        }
                        resp.append("'>");
                    }

                    resp.append("</td>");
                }
                //SECCION DE OPERACIONES SOBRE ACTIVOS
                //si estamos en la ultima fila agregamos los procesos si se requiere
                if (isUsarControles()) {
                    resp.append("  <td class='sm_tr_columnadatos'>");
                    resp.append("          <div class='sm_td_operacionescontainer'>");
                    resp.append("   <div class='sm_div_rowsim'>");

                    if (isOpcionactualizar()) {

                        resp.append("             <div class='sm_div_colsim sm_div_grillamodificar'  title='Modificar información'>");
                        resp.append("              &nbsp;");
                        resp.append("        </div>  ");
                    }

                    if (isOpcionimprimir()) {

                        resp.append("<div class='sm_div_colsim sm_div_grillaimprimir' title='Imprimir reporte'>");
                        resp.append("&nbsp;");
                        resp.append("</div>");
                    }

                    if (isOpcionver()) {

                        resp.append("<div class='sm_div_colsim sm_div_grillaverimagen' title='ver imagen de activo'>");
                        resp.append("&nbsp;");
                        resp.append("</div>  ");
                    }
                    if (isOpcionverdocumentos()) {

                        resp.append("<div class='sm_div_colsim sm_div_grillaverdocumentos' title='Ver / Cargar documentos de este registro'>");
                        resp.append("&nbsp;");
                        resp.append("</div>  ");
                    }
                    if (isOpcionverdetalles()) {

                        resp.append("<div class='sm_div_colsim sm_div_grillaverdetalle' title='Ver Detalles del Registro'>");
                        resp.append("&nbsp;");
                        resp.append("</div>  ");
                    }
                    if (isOpcioneliminar()) {

                        resp.append("<div class='sm_div_colsim sm_div_grillaeliminar' title='Eliminar activo'>");
                        resp.append("&nbsp;");
                        resp.append("</div>  ");
                    }

                    resp.append("</div>");
                    resp.append("</div>");
                    resp.append("</td>");
                    resp.append("</tr>");
                }
            } while (resultset.next());
        } else {
            resp.append("<tr class='sm_tbody_filanoresult'>");
            resp.append("<td colspan=");
            resp.append(cantidadcolumnas + 1);
            resp.append(">");
            resp.append("<div class='div_noresultcontainer'>No se encontró nada!!</div>");
            resp.append("</td>");
            resp.append("</tr>");
        }

        resp.append("    </tbody>  ");
        resp.append("   </table>");
        //find e seccion de tablas

        //SECCION PAGINACION
        resp.append(" <div class='sm_div_paginaciongrillacontainer'>");
        resp.append("    <div id='sm_div_paginacionelementscontainer'>");
        for (int a = 1; a <= cantidadpaginas; a++) {
            resp.append("  <span class='sm_div_elementpagcontainer'>");
            resp.append("    <a class='sm_div_elementlinkpag' href='");
            resp.append(getUrlpaginacionlink());
            resp.append("&pag=");
            resp.append(a);
            resp.append("'>");
            resp.append(a);
            resp.append(" </a>");
            resp.append("   </span> ");
        }

        resp.append("     </div>");

        //SECCION DONDE SE DEFINE SI SE QUIERE O NO MOSTRAR LA DEFINICION DE PAGINACION
        if (isOpcionseleccionarpaginacion()) {
            resp.append("   <div id='sm_divpaginaciondefiner'>");
            resp.append("     Pag.<input id='sm_grilla_div_txtpaginacion' placeholder='#' type='text'>");
            resp.append("   </div>");
        }
        resp.append("   </div>");
        resp.append("</div>");//CAMBIO DE ULTIMA HORA CIERRE LLAVE

        resultset.close();
        return resp.toString();
    }

    /**
     * @return the opcionseleccionarpaginacion
     */
    public boolean isOpcionseleccionarpaginacion() {
        return opcionseleccionarpaginacion;
    }

    /**
     * @param opcionseleccionarpaginacion the opcionseleccionarpaginacion to set
     */
    public void setOpcionseleccionarpaginacion(boolean opcionseleccionarpaginacion) {
        this.opcionseleccionarpaginacion = opcionseleccionarpaginacion;
    }

    /**
     * @return the usarMostrarOcultos
     */
    public boolean isUsarMostrarOcultos() {
        return usarMostrarOcultos;
    }

    /**
     * @param usarMostrarOcultos the usarMostrarOcultos to set
     */
    public void setUsarMostrarOcultos(boolean usarMostrarOcultos) {
        this.usarMostrarOcultos = usarMostrarOcultos;
    }

    /**
     * @return the mostrarOcultosMsg
     */
    public String getMostrarOcultosMsg() {
        return mostrarOcultosMsg;
    }

    /**
     * @param mostrarOcultosMsg the mostrarOcultosMsg to set
     */
    public void setMostrarOcultosMsg(String mostrarOcultosMsg) {
        this.mostrarOcultosMsg = mostrarOcultosMsg;
    }

    /**
     * @return the opcionverdocumentos
     */
    public boolean isOpcionverdocumentos() {
        return opcionverdocumentos;
    }

    /**
     * @param opcionverdocumentos the opcionverdocumentos to set
     */
    public void setOpcionverdocumentos(boolean opcionverdocumentos) {
        this.opcionverdocumentos = opcionverdocumentos;
    }

    /**
     * @return the opcionverdetalles
     */
    public boolean isOpcionverdetalles() {
        return opcionverdetalles;
    }

    /**
     * @param opcionverdetalles the opcionverdetalles to set
     */
    public void setOpcionverdetalles(boolean opcionverdetalles) {
        this.opcionverdetalles = opcionverdetalles;
    }

    /**
     * @return the usarControles
     */
    public boolean isUsarControles() {
        return usarControles;
    }

    /**
     * @param usarControles the usarControles to set
     */
    public void setUsarControles(boolean usarControles) {
        this.usarControles = usarControles;
    }

}
