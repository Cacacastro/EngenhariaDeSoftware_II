/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlles;

import bd.dal.EmprestimoDAL;
import bd.entidades.Emprestimo;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "TelaEmprestimo", urlPatterns = {"/TelaEmprestimo"})
public class TelaEmprestimo extends HttpServlet {
public String buscaEmprestimo(String filtro, Conexao con) {
        String res = "";
        ArrayList<Emprestimo> emprestimos = new EmprestimoDAL().getEmprestimos(filtro,true,con);
        for (Emprestimo u : emprestimos) {
          res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
              + "<td onclick='ApagaAlteraEmprestimo(\"apagar\",%s)'><img src='icones/apagar.png'/></td>"
              + "<td onclick='ApagaAlteraEmprestimo(\"alterar\",%s)'><img src='icones/alterar.png'/></a></td>"
              + "</tr>", "" + u.getCod(),"" + u.getUser_cod(), ""+u.getData(),""+u.getDuracao(), ""+ u.getValorTotal(), ""+u.getCod(),""+u.getCod());
        }
        
        return res;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            response.setContentType("text/html;charset=UTF-8");
            String erro = "";
            String acao = request.getParameter("acao");
            int Ecod;
            try {
                Ecod = Integer.parseInt(request.getParameter("cod"));
            } catch (Exception e) {
                Ecod = 0;
            }
            EmprestimoDAL ctr = new EmprestimoDAL();
            Conexao con=new Conexao();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "emp_data like '%" + filtro + "%'";
                    response.getWriter().print(buscaEmprestimo(filtro,con));
                    break;
                case "apagar":
                    if (!ctr.apagar(Ecod,con))
                       erro = "Erro ao apagar o empréstimo";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Emprestimo u = ctr.getEmprestimo(Ecod,con);
                    response.getWriter().print(u); // retorna todos os dados na forma de lista (,,,)
                    break;
                case "confirmar": //novo e alteração
                    erro="ok";
                    Date data = Date.valueOf(request.getParameter("emp_data"));
                    int duracao = Integer.parseInt(request.getParameter("emp_duracao"));
                    double ValorTotal = Double.parseDouble(request.getParameter("emp_valortotal"));
                    int usu = Integer.parseInt(request.getParameter("usu_cod"));
                    //VALIDAR AQUI SE EXISTE USER E BIB
                    Emprestimo user = new Emprestimo(Ecod,duracao,usu, data, ValorTotal);
                    response.getWriter().print(user);
                    if (Ecod == 0) 
                    {   if (!ctr.salvar(user,con)) erro = "Erro ao gravar o empréstimo";}
                    else 
                    {   if (!ctr.alterar(user,con)) erro = "Erro ao alterar o empréstimo";}
                    
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
