/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package simuni.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import simuni.classes.EN.Usuario;
import simuni.classes.LN.ManejadorUsuarios;

/**
 *
 * @author FchescO
 */
public class AccionesUsuarios extends HttpServlet {

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
            out.println("<title>Servlet AccionesUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccionesUsuarios at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
       String nombreusuario=request.getParameter("nombreusuario");
       String contrasena=request.getParameter("contrasena");
        Usuario usuario=null;
        nombreusuario="fCoulon";
        contrasena="fCoulonpass";
        if(nombreusuario==null||contrasena==null){
            request.getSession().setAttribute("USERNAME", null);
            request.getSession().setAttribute("TIPOUSUARIO", null);
            request.getSession().setAttribute("HORAINICIO", null);
        }
        else{
            ManejadorUsuarios manejadorusuarios=new ManejadorUsuarios();
            usuario=manejadorusuarios.login(nombreusuario, contrasena);
            if(usuario==null){
            request.getSession().setAttribute("USERNAME", null);
            request.getSession().setAttribute("TIPOUSUARIO", null);
            request.getSession().setAttribute("HORAINICIO", null);                
            }else{
            request.getSession().setAttribute("USERNAME", usuario.getNombreusuario());
            request.getSession().setAttribute("TIPOUSUARIO", usuario.getTipousuario());
            request.getSession().setAttribute("HORAINICIO", new Date().toLocaleString());
            }
            
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
