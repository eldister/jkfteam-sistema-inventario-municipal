package simuni.classes.EN.RE;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.OutputStream;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.Departamento;
import simuni.classes.EN.EstadoActivo;
import simuni.classes.EN.TipoActivo;
import simuni.classes.EN.TipoPago;

/**
 *
 * @author FchescO
 */
public class Reporte_ActivoArticuloParticular {

    private java.sql.Date fechaReporte;
    private String titulo = "Reporte  de Activo Particular";
    private String usuariocreador;
    private String nombreDepartamento;
    private String nombreTipoActivo;
    private String nombreTipoPago;
    private String nombreEstadoActivo;

    public boolean generarReporte(String usuario, Activos_Articulos activoarticulo,
            ArrayList<Departamento> deptos, ArrayList<TipoActivo> tiposactivo,
            ArrayList<TipoPago> tipopago, ArrayList<EstadoActivo> tiposestadoactivo,
            OutputStream responsestream) throws Exception {

        if (activoarticulo == null) {
            return false;
        }
        usuariocreador = usuario;
        fechaReporte = new Date(new java.util.Date().getTime());
        nombreDepartamento = activoarticulo.getPo_depto() != null ? activoarticulo.getPo_depto().getPa_nombre() : "";

        setNombreTipoActivo(tiposactivo, activoarticulo.getPa_tipoActivo());
        setNombreTipoPago(tipopago, activoarticulo.getPa_tipoPago());
        nombreEstadoActivo = activoarticulo.getPa_Estado();

        Document document = new Document(PageSize.A4, 50, 50, 100, 100);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, responsestream);
            Plantilla_ReporteVertical pl = new Plantilla_ReporteVertical();
            writer.setPageEvent(pl);
            document.open();

            HTMLWorker worker = new HTMLWorker(document);
            StringBuilder sb = new StringBuilder();
            String creador = "Emitido el " + fechaReporte + " por " + usuariocreador;
            Paragraph parrafo0 = new Paragraph();
            Paragraph parrafo = new Paragraph(titulo);
            Paragraph parrafo2 = new Paragraph(creador);
            parrafo.setAlignment(Element.ALIGN_CENTER);
            parrafo2.setAlignment(Element.ALIGN_CENTER);
            document.add(parrafo0);
            document.add(parrafo0);
            document.add(parrafo0);

            document.add(parrafo);
            document.add(parrafo2);
            document.add(parrafo0);
            document.add(parrafo0);

            sb.append("<table border1>");
            sb.append("	<tr>");
            sb.append("		<th colspan='2' style='text-align:center;'><span >Activo ");
            sb.append(activoarticulo.getPa_identificadorActivo());
            sb.append("</span></th>");
            sb.append("		<tr >");
            sb.append("	</tr>");
            sb.append("	<tr> ");
            sb.append("		<td>");
            sb.append("			<strong><span>Numero de placa</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPa_identificadorActivo());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Modelo</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPa_modelo());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Marca</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPa_marca());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Categoria</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(nombreTipoActivo);
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Fecha inicio de operacion</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPd_puestaOperacion().toString());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Departamento Destino</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(nombreDepartamento);
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Fecha Compra</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPd_fechaCompra());
            sb.append("</span>");
            sb.append("		</td>	");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Precio de Compra</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPd_precioCompra());
            sb.append("</span>");
            sb.append("		</td>	");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Porcentaje de deprecaiacio</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPb_porcentajeDepreciacion());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Porcentaje Rescate</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPb_porcentajeRescate());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Tipo de pago</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(nombreTipoPago);
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Estado Actual</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(nombreEstadoActivo);
            sb.append("</span>");
            sb.append("		</td>	");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Descripcion </span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPa_Descripcion());
            sb.append("</span>");
            sb.append("		</td>");
            sb.append("	</tr>	");
            sb.append("	<tr>");
            sb.append("		<td>");
            sb.append("			<strong><span>Observaciones</span></strong>");
            sb.append("		</td>");
            sb.append("		<td>");
            sb.append("			<span>");
            sb.append(activoarticulo.getPa_Observaciones());
            sb.append("</span>");
            sb.append("		</td>	");
            sb.append("	</tr>	");
            sb.append("</table>  ");
            String code = sb.toString();
            worker.newLine();
            worker.newLine();

            worker.parse(new StringReader(code));
        } catch (Exception e) {
            throw e;
        } finally {
            document.close();
        }
        return true;

    }

    /**
     * @param fechaReporte the fechaReporte to set
     */
    public void setFechaReporte(java.sql.Date fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @param usuariocreador the usuariocreador to set
     */
    public void setUsuariocreador(String usuariocreador) {
        this.usuariocreador = usuariocreador;
    }

    /**
     * @param nombreDepartamento the nombreDepartamento to set
     */
    public void setNombreDepartamento(ArrayList<Departamento> deptos, int coddepto) {
        nombreDepartamento = "Sin clasificacion";
        if (deptos != null) {
            Iterator<Departamento> iter = deptos.iterator();
            while (iter.hasNext()) {

                Departamento depto = iter.next();
                if (depto.getPn_codigo() == coddepto) {
                    nombreDepartamento = depto.getPa_nombre();
                    return;
                }
            }

        }
    }

    /**
     * @param nombreTipoActivo the nombreTipoActivo to set
     */
    public void setNombreTipoActivo(ArrayList<TipoActivo> tiposactivo, int codtipoactivo) {
        this.nombreTipoActivo = "Sin Clasificacion";
        if (tiposactivo != null) {
            Iterator<TipoActivo> iter = tiposactivo.iterator();
            while (iter.hasNext()) {

                TipoActivo tipoactivo = iter.next();
                if (tipoactivo.getCodigoTipoActivo() == codtipoactivo) {
                    nombreTipoActivo = tipoactivo.getNombreTipoActivo();
                    return;
                }
            }

        }
    }

    /**
     * @param nombreTipoPago the nombreTipoPago to set
     */
    public void setNombreTipoPago(ArrayList<TipoPago> tipospago, int codigopago) {
        this.nombreTipoPago = "No definido";
        if (tipospago != null) {
            Iterator<TipoPago> iter = tipospago.iterator();
            while (iter.hasNext()) {

                TipoPago tipoPago = iter.next();
                if (tipoPago.getCodigoTipoPago() == codigopago) {
                    nombreTipoPago = tipoPago.getNombreTipoPago();
                    return;
                }
            }

        }

    }

    /**
     * @param nombreEstadoActivo the nombreEstadoActivo to set
     */
    public void setNombreEstadoActivo(ArrayList<EstadoActivo> tiposestadoactivo, String estadoactivo) {
        /*this.nombreEstadoActivo = "No definido";
         if(tiposestadoactivo!=null){
         Iterator<EstadoActivo> iter=tiposestadoactivo.iterator();
         while(iter.hasNext()){
                
         EstadoActivo estadoactivo=iter.next();
         if(estadoactivo.getNombreEstado()==codigoestadoactivo){
         nombreTipoPago=tipoPago.getNombreTipoPago();
         return;
         }
         }
         */
    }
}
