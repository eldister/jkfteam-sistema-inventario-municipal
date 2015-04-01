
package simuni.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorActivo;
import simuni.clases.ln.ManejadorProveedor;
import simuni.clases.ln.ManejadorTipoReporte;
import simuni.clases.ln.ManejadorReparacion;
import simuni.clases.ln.ManejadorUsuario;
import simuni.intefaces.IReporteador;
import simuni.utils.UtilidadesServlet;

/**
 *
 * @author FchescO
 */
public class AccionesReportes extends HttpServlet {

    enum OpcionesDo {

        ReporteGeneralActivos,
        ReporteGeneralUsuarios,
        ReporteGeneralProveedores,
        ReporteGeneralReparaciones,
        ReporteGeneralBajas,
        ObtenerNotificaciones,
        ResponderMensaje,
        VerNotificaciones,
        AccionDefault

    }

    private OpcionesDo getOpcion(String key) {
        if (key == null || key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("rprt_gactivo")) {
            return OpcionesDo.ReporteGeneralActivos;
        } else if (key.equals("rprt_gusuario")) {
            return OpcionesDo.ReporteGeneralUsuarios;
        } else if (key.equals("rprt_gproveedor")) {
            return OpcionesDo.ReporteGeneralProveedores;
        } else if(key.equals("rprt_greparacion")){
            return OpcionesDo.ReporteGeneralReparaciones;
        } else if(key.equals("rprt_gbaja")){
            return OpcionesDo.ReporteGeneralBajas;
        }
        return OpcionesDo.AccionDefault;
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IReporteador reporteador;
        RequestDispatcher disp;
        switch (getOpcion(request.getParameter("proceso"))) {
            case ReporteGeneralActivos:
                reporteador = new ManejadorActivo();
                request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_activos.jsp");
                disp.forward(request, response);
                break;
            case ReporteGeneralUsuarios:
                reporteador = new ManejadorUsuario();
                request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_usuarios.jsp");
                disp.forward(request, response);
                break;
            case ReporteGeneralProveedores:
                reporteador = new ManejadorProveedor();
                request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_proveedores.jsp");
                disp.forward(request, response);
                break;
            default:

                disp = request.getRequestDispatcher("/modulos/reportes/index.jsp");
                request.setAttribute("TIPOS_REPORTE", new ManejadorTipoReporte().listadoTipoReporte());
                disp.forward(request, response);
                break;
        }
      switch (getOpcion(request.getParameter("proceso"))) {
          case ReporteGeneralActivos:
              reporteador=new ManejadorActivo();
              request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
              disp = request.getRequestDispatcher("/modulos/reportes/rprt_activos.jsp");
              disp.forward(request, response);
              break;
          case ReporteGeneralUsuarios:
              reporteador=new ManejadorUsuario();
              request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
              disp = request.getRequestDispatcher("/modulos/reportes/rprt_usuarios.jsp");
              disp.forward(request, response);
              break;
          case ReporteGeneralProveedores:
              reporteador=new ManejadorProveedor();
                            request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
              disp = request.getRequestDispatcher("/modulos/reportes/rprt_proveedores.jsp");
              disp.forward(request, response);
              break;
          case ReporteGeneralReparaciones:
              reporteador = new ManejadorReparacion();
              request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
              disp = request.getRequestDispatcher("/modulos/reportes/rprt_reparaciones.jsp");
              disp.forward(request, response);
              break;
          case ReporteGeneralBajas:
              reporteador = new ManejadorReparacion();
              request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
              disp = request.getRequestDispatcher("/modulos/reportes/rprt_reparaciones.jsp");
              disp.forward(request, response);
              break;
      }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        IReporteador reporteador = null;
        RequestDispatcher disp=null;
        int tiporeporte = UtilidadesServlet.getInt(request.getParameter("cmbtiporeporte"), -1);
        java.sql.Date fein=UtilidadesServlet.getFecha(request.getParameter("feinicial"), null);
        java.sql.Date fin=UtilidadesServlet.getFecha(request.getParameter("fefinal"), null);
        boolean usarFechas=request.getParameter("chkfechas")!=null;
        switch (tiporeporte) {
            case 1:
                reporteador = new ManejadorActivo();
                request.setAttribute("Rprt_Datos", usarFechas?
                        reporteador.obtenerDatosReporte(fein, fin):
                        reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_activos.jsp");
                request.setAttribute("Usa_Fechas", usarFechas);
                request.setAttribute("Fecha_Ini", fein);
                request.setAttribute("Fecha_Fin", fin);
                disp.forward(request, response);
                break;
            case 2:
                reporteador = new ManejadorProveedor();
                request.setAttribute("Rprt_Datos", usarFechas?
                        reporteador.obtenerDatosReporte(fein, fin):
                        reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_proveedores.jsp");
                request.setAttribute("Usa_Fechas", usarFechas);
                request.setAttribute("Fecha_Ini", fein);
                request.setAttribute("Fecha_Fin", fin);
                disp.forward(request, response);
                break;
            case 3:
                reporteador = new ManejadorUsuario();
                request.setAttribute("Rprt_Datos", reporteador.obtenerDatosReporte());
                disp = request.getRequestDispatcher("/modulos/reportes/rprt_usuarios.jsp");
                disp.forward(request, response);                
                break;
        }
        

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
