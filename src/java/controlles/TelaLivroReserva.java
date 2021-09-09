/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlles;

import bd.dao.LivroDAO;
import bd.entidades.Livro;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "TelaLivroReserva", urlPatterns = {"/TelaLivroReserva"})
public class TelaLivroReserva extends HttpServlet {
public String buscaLivro(String filtro, Conexao con) throws SQLException {
        String res = "";
        ArrayList<Livro> Livros = new LivroDAO().getLivros(filtro,true,con);
        for (Livro l : Livros) {
            res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
                + "<td onclick='GeraReserva(%s)'><button class=\"form-control  mb-2 mr-sm-2 btn btn-primary\" style=\"background-color:  #FFEB3B; width:83px; color:black\">Reservar</button></td>"
                + "</tr>", "" + l.getCod(), l.getTitulo(),""+l.getNumPag(), ""+l.getGen_cod().getGen_genero(),
                          ""+l.getEdi_cod().getEdi_nome(),"" + l.getCod());
        }
        
        return res;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
            
            response.setContentType("text/html;charset=UTF-8");
            String erro = "";
            String acao = request.getParameter("acao");
            int cod;
            try {
                cod = Integer.parseInt(request.getParameter("cod"));
            } catch (Exception e) {
                cod = 0;
            }
            LivroDAO ctr = new LivroDAO();
            Conexao con=new Conexao();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "upper(liv_titulo) like '%" + filtro.toUpperCase() + "%'";
                    response.getWriter().print(buscaLivro(filtro,con));
                    break;
            }
            con.fecharConexao();

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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(TelaLivroReserva.class.getName()).log(Level.SEVERE, null, ex);
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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(TelaLivroReserva.class.getName()).log(Level.SEVERE, null, ex);
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
