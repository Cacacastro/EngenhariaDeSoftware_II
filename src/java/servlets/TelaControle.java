package servlets;

import bd.dal.ControleDAL;
import bd.dal.UsuarioDAL;
import bd.entidades.Controle_Devolucao;
import bd.entidades.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "TelaControle", urlPatterns = {"/TelaControle"})
public class TelaControle extends HttpServlet {

    public String buscaControle(String filtro) {
        String res = "";
        ArrayList<Controle_Devolucao> controles = new ControleDAL().getControles(filtro,true);
        for (Controle_Devolucao u : controles) {
          String dev;
          if(u.getDevolucao())
              dev = "Devolvido";
          else
              dev = "Pendente";
          
          String multa;
          if(u.getMulta())
              multa = "<img src='icones/correto.png'/>";
          else
              multa = "<img src='icones/incorreto.png'/>";
          res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
              + "<td onclick='ApagaAlteraControle(\"apagar\",%s)'><img src='icones/apagar.png'/></td>"
              + "<td onclick='ApagaAlteraControle(\"alterar\",%s)'><img src='icones/alterar.png'/></a></td>"
              + "</tr>", "" + u.getCod(), ""+multa,""+u.getValorMulta(),
                        "" + u.getEmp_cod(), ""+dev, ""+u.getCod(), ""+u.getCod());
        }
        
        return res;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
            response.setContentType("text/html;charset=UTF-8");
            String erro = "";
            String acao = request.getParameter("acao");
            int cod;
            try {
                cod = Integer.parseInt(request.getParameter("cod"));
            } catch (Exception e) {
                cod = 0;
            }
            ControleDAL ctr = new ControleDAL();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "cdd_cod = " + filtro.toLowerCase();
                    response.getWriter().print(buscaControle(filtro));
                    break;
                case "apagar":
                    if (!ctr.apagar(cod))
                       erro = "Erro ao apagar o controle";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Controle_Devolucao u = ctr.getControle(cod);
                    response.getWriter().print(u); // retorna todos os dados na forma de lista (,,,)
                    break;
                case "confirmar": //novo e alteração
                    erro="ok";
                    boolean multa = Boolean.getBoolean(request.getParameter("multa"));
                    Double valor_multa = Double.parseDouble(request.getParameter("valor_multa"));
                    int emp_cod = Integer.parseInt(request.getParameter("emp_cod"));
                    boolean dev = Boolean.getBoolean(request.getParameter("dev"));
                    Controle_Devolucao user = new Controle_Devolucao(cod,emp_cod, dev, multa, valor_multa);
                    if (cod == 0) 
                    {   if (!ctr.salvar(user)) erro = "Erro ao gravar o controle";}
                    else 
                    {   if (!ctr.alterar(user)) erro = "Erro ao alterar o controle";}
                    response.getWriter().print(erro);
                    break;
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
