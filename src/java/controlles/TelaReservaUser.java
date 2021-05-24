package controlles;

import bd.dal.EmprestimoDAL;
import bd.dal.ExemplarDAL;
import bd.dal.LivroDAL;
import bd.dal.ReservaDAL;
import bd.entidades.Emprestimo;
import bd.entidades.Exemplar;
import bd.entidades.Livro;
import bd.entidades.Reserva;
import bd.entidades.Usuario;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "TelaReservaUser", urlPatterns = {"/TelaReservaUser"})
public class TelaReservaUser extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int cod;
        Date data;
        try {
            cod = Integer.parseInt(request.getParameter("cod"));
        } catch (Exception e) {
            cod = 0;
        }
        try {
            data = Date.valueOf(request.getParameter("res_data"));
        } catch (Exception e) {
            data = Date.valueOf(LocalDate.now());
        }
        HttpSession sessao = request.getSession(true);
        Usuario usu = (Usuario) sessao.getAttribute("usuario");
        
        Conexao con=new Conexao();
        
        LivroDAL ldal = new LivroDAL();
        Livro liv = ldal.getLivro(cod, con);
        
        ReservaDAL rdal = new ReservaDAL();
        Reserva res = new Reserva(true,data,0,usu, liv);
        rdal.salvar(res, con);
        
        response.getWriter().print("Parabéns "+usu.getNome()+", sua reserva foi programada com sucesso! Retirar na data: "+data+", o livro: "+liv.getTitulo());
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
