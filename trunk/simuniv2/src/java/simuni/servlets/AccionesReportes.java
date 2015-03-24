/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.clases.ln.ManejadorActivo;
import simuni.clases.ln.ManejadorProveedor;
import simuni.clases.ln.ManejadorUsuario;
import simuni.intefaces.IReporteador;

/**
 *
 * @author FchescO
 */
public class AccionesReportes extends HttpServlet {

    enum OpcionesDo {

        ReporteGeneralActivos,
        ReporteGeneralUsuarios,
        ReporteGeneralProveedores,
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
        }  else if (key.equals("rprt_gproveedor")) {
            return OpcionesDo.ReporteGeneralProveedores;
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
