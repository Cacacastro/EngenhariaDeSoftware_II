package controlles;

import bd.dal.EmprestimoDAL;
import bd.dal.ExemplarDAL;
import bd.dal.UsuarioDAL;
import bd.entidades.Emprestimo;
import bd.entidades.Exemplar;
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
@WebServlet(name = "TelaEmprestimoUser", urlPatterns = {"/TelaEmprestimoUser"})
public class TelaEmprestimoUser extends HttpServlet {

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
        int cod,dias;
        try {
            cod = Integer.parseInt(request.getParameter("cod"));
        } catch (Exception e) {
            cod = 0;
        }
        try {
            dias = Integer.parseInt(request.getParameter("emp_duracao"));
        } catch (Exception e) {
            dias = 3;
        }
        HttpSession sessao = request.getSession(true);
        Usuario usu = (Usuario) sessao.getAttribute("usuario");
        
        Conexao con=new Conexao();
        
        EmprestimoDAL edal = new EmprestimoDAL();
        Emprestimo emp = new Emprestimo(Date.valueOf(LocalDate.now()),dias,0,usu);
        edal.salvar(emp, con);
        
        ExemplarDAL exdal = new ExemplarDAL();
        Exemplar exe = exdal.getExeLiv(cod, con);
        exe.setExe_disp(false);
        exdal.alterar(exe, con);
        response.getWriter().print("Parabéns "+usu.getNome()+", seu empréstimo foi realizado com sucesso! Retirar ainda hoje o livro: "+exe.getLiv_cod().getTitulo());
 
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
