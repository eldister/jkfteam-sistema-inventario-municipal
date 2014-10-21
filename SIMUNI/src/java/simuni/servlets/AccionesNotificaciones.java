/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simuni.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FchescO
 */
public class AccionesNotificaciones extends HttpServlet {

    enum OpcionesDo {

        ObtenerUltimosMensajes,
        ObtenerUltimasNotificaciones,
        AccionDefault

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccionesNotificaciones</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccionesNotificaciones at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
          RequestDispatcher disp=null;
        switch (getOpcion(request.getParameter("proceso"))) {
            case ObtenerUltimosMensajes:
                  disp= disp = request.getRequestDispatcher("/recursos/paginas/embebidos/notificaciones_asinc.jsp?mod=2");
                  disp.forward(request, response);
                break;
            case ObtenerUltimasNotificaciones:
                  disp= disp = request.getRequestDispatcher("/recursos/paginas/embebidos/notificaciones_asinc.jsp?mod=1");
                  disp.forward(request, response);                
                break;
            default:
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
        processRequest(request, response);
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

    private OpcionesDo getOpcion(String key) {
        if (key == null) {
            return OpcionesDo.AccionDefault;
        } else if (key.length() == 0) {
            return OpcionesDo.AccionDefault;
        } else if (key.equals("obtenerultimosmensajes")) {
            return OpcionesDo.ObtenerUltimosMensajes;
        } else if (key.equals("obtenerultimasnotificaciones")) {
            return OpcionesDo.ObtenerUltimasNotificaciones;
        }
        return OpcionesDo.AccionDefault;
    }
}
