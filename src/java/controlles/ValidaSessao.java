/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlles;

import bd.dal.UsuarioDAL;
import bd.entidades.Usuario;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author silvi
 */
@WebServlet(name = "ValidaSessao", urlPatterns = {"/ValidaSessao"})
public class ValidaSessao extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int logou=0;
        String login=request.getParameter("login");
        String senha=request.getParameter("senha");
        Conexao con=new Conexao();
        if (!senha.isEmpty() && !login.isEmpty())
        {
            UsuarioDAL dal = new UsuarioDAL();
            Usuario user = dal.getUserByEmail(login, con);
            if(user!=null)
            {
                if(senha.equals(user.getSenha()))
                {
                    HttpSession sessao = request.getSession();
                    sessao.setAttribute(user.getNome(), user);
                    if(user.getAdmin())
                        logou =2;
                    else
                        logou =1;
                }
            }
        }
        con.fecharConexao();
        if (logou == 2)
            response.sendRedirect("admin.html");
        else
        {
            if (logou == 1)
                response.sendRedirect("user.html");
            else
                response.sendRedirect("index.html");
        }
        return;
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

}
