package simuni.classes.AD;

import java.util.ArrayList;
import java.util.Date;
import simuni.classes.EN.Activos_Articulos;
import simuni.classes.EN.Departamento;
import simuni.classes.EN.imagenActivo;

/**
 *
 * @author FchescO
 */
public class ManejadorDatosActivos {

    public boolean agregarActivoArticulo(Activos_Articulos to_articulo) {
        return true;
    }

    public boolean modificarActivoArticulo(Activos_Articulos to_articulo) {
        return true;
    }

    public boolean desactivarActivoArticulo(int tn_codigoactivo) {
        return true;
    }

    public ArrayList<Activos_Articulos> getListaArticulos(int npagina, int paginacion) {

        ArrayList<Activos_Articulos> to_articulo = new ArrayList<Activos_Articulos>();

        for (int a = 0; a < paginacion; a++) {
            Activos_Articulos articulo = new Activos_Articulos();
            articulo.setPa_identificadorActivo("123456asdf");
            articulo.setPa_tipoActivo(2);
            articulo.setPa_marca("patito 1");
            articulo.setPa_modelo("modelo a");
            articulo.setPd_puestaOperacion(new Date());
            articulo.setPa_Descripcion("Una descripcion rara");
            to_articulo.add(articulo);
        }

        return to_articulo;
    }

    public Activos_Articulos getActivoArticulo(String ta_codigoactivo) {
        Activos_Articulos articuloejemplo = new Activos_Articulos();
        articuloejemplo.setPa_Descripcion("Una descripcion rara");
        articuloejemplo.setPa_Estado("Prestado");
        articuloejemplo.setPa_Observaciones("observaciones reales");
        articuloejemplo.setPa_codigoProveedor("504230366");
        articuloejemplo.setPa_nombreproveedor("Francisco Coulon");
        articuloejemplo.setPa_identificadorActivo(ta_codigoactivo);
        articuloejemplo.setPa_marca("Patitos");
        articuloejemplo.setPa_modelo("50");
        articuloejemplo.setPa_tipoActivo(2);
        articuloejemplo.setPa_tipoPago(1);
        articuloejemplo.setPb_depreciacion(5);
        articuloejemplo.setPb_porcentajeDepreciacion(6);
        articuloejemplo.setPb_porcentajeRescate(10);
        articuloejemplo.setPd_fechaCompra(new Date());
        articuloejemplo.setPd_precioCompra(500000);
        articuloejemplo.setPd_puestaOperacion(new Date());
        articuloejemplo.setPn_anioFabricacion(2014);
        articuloejemplo.setPn_aniosutilidadactivo(1);
        ArrayList<imagenActivo> imgs = new ArrayList<imagenActivo>();
        imagenActivo a = new imagenActivo();
        a.setPa_url("http://dailydropcap.com/images");
        a.setPa_nombreArchivo("J-1.jpg");
        imgs.add(a);
        articuloejemplo.setPo_imagenActivo(imgs);
        System.out.print(articuloejemplo.getPo_imagenActivo().size());
        Departamento d = new Departamento();
        d.setPa_nombre("Depto feo");
        d.setPn_codigo(1);
        articuloejemplo.setPo_depto(d);

        return articuloejemplo;
    }

    public ArrayList<imagenActivo> getListaImagenesActivo(int tn_codigoactivo) {
        return null;
    }

    public boolean isActivoExistente(String idactivo) {
        return false;
    }

}
