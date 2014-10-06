package simuni.classes.UI;

import java.util.ArrayList;
import java.util.Iterator;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.GrillaBase;


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

    public String RenderizarActivos(ArrayList<Activos_Articulos> to_articulo,int cantidadpaginas) {
        ArrayList<Object[]> filas = new ArrayList<Object[]>();
        Iterator<Activos_Articulos> iter = to_articulo.iterator();
        while (iter.hasNext()) {
            Activos_Articulos articulo = iter.next();
            Object[] obj = new Object[]{
                articulo.getPa_identificadorActivo(),
                articulo.getPa_tipoActivo(),
                articulo.getPa_marca(),
                articulo.getPa_modelo(),
                articulo.getPd_puestaOperacion(),
                articulo.getPa_Descripcion()
            };
            filas.add(obj);
        }
        GrillaBase gril = new GrillaBase();
       
        gril.setUrlagregaropcionagregar("registro.jsp");
        return gril.renderizar(criteriofiltros, encabezados, filas, cantidadpaginas);
    }
}
