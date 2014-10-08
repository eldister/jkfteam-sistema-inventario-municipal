package simuni.classes.UI;

import java.util.ArrayList;
import java.util.Iterator;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.GrillaBase;
import simuni.classes.EN.TipoActivo;

/**
 *
 * @author FchescO
 */
public class ActivosMostrador {

    Object[] criteriofiltros;
    Object[] encabezados;

    public ActivosMostrador() {
        criteriofiltros = new Object[]{"Muebles", "Tecnológicos"};
        encabezados = new Object[]{"Identificador Activo", "Tipo de Activo", "Marca", "Modelo", "Puesta en Operación", "Descripción"};
    }

    public String RenderizarActivos(ArrayList<TipoActivo> tiposactivo, ArrayList<Activos_Articulos> to_articulo, int cantidadpaginas) {
        if (tiposactivo == null) {
            tiposactivo = new ArrayList<TipoActivo>();
        }
        if (to_articulo == null) {
            to_articulo = new ArrayList<Activos_Articulos>();
        }

        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        Iterator<Activos_Articulos> iter = to_articulo.iterator();
        String nombretipo = "";
        while (iter.hasNext()) {
            Activos_Articulos articulo = iter.next();
            Iterator<TipoActivo> iteradortipos = tiposactivo.iterator();
            while (iteradortipos.hasNext()) {
                TipoActivo tipo = iteradortipos.next();
                if (tipo.getCodigoTipoActivo() == articulo.getPa_tipoActivo()) {
                    nombretipo = tipo.getNombreTipoActivo();
                }

            }
            if (nombretipo.length() == 0) {
                nombretipo = "Sin clasificacion";
            }
            Object[] obj = new Object[]{
                articulo.getPa_identificadorActivo(),
                nombretipo,
                articulo.getPa_marca(),
                articulo.getPa_modelo(),
                articulo.getPd_puestaOperacion(),
                articulo.getPa_Descripcion()
            };

            filas.add(obj);
            nombretipo = "";
        }
        GrillaBase gril = new GrillaBase();

        gril.setUrlagregaropcionagregar("registro.jsp");
        return gril.renderizar(criteriofiltros, encabezados, filas, cantidadpaginas);
    }

    public String RenderizarActualizacionArticulos(ArrayList<TipoActivo> tiposactivo, ArrayList<Activos_Articulos> to_articulo, int cantidadpaginas) {
        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        if (to_articulo == null) {
            to_articulo = new ArrayList<Activos_Articulos>();
        }
        Iterator<Activos_Articulos> iter = to_articulo.iterator();
        String nombretipo = "";

        while (iter.hasNext()) {
            Activos_Articulos articulo = iter.next();
            Iterator<TipoActivo> iteradortipos = tiposactivo.iterator();
            while (iteradortipos.hasNext()) {
                TipoActivo tipo = iteradortipos.next();
                if (tipo.getCodigoTipoActivo() == articulo.getPa_tipoActivo()) {
                    nombretipo = tipo.getNombreTipoActivo();
                }

            }
            if (nombretipo.length() == 0) {
                nombretipo = "Sin clasificacion";
            }
            Object[] obj = new Object[]{
                articulo.getPa_identificadorActivo(),
                nombretipo,
                articulo.getPa_marca(),
                articulo.getPa_modelo(),
                articulo.getPd_puestaOperacion(),
                articulo.getPa_Descripcion()
            };
            filas.add(obj);
            nombretipo = "";
        }
        GrillaBase gril = new GrillaBase();

        gril.setUrlagregaropcionagregar("registro.jsp");//por mejorar
        return gril.renderizarSoloCuerpoTabla(encabezados, filas, cantidadpaginas);
    }

}
