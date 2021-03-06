package controlles;

import bd.dal.UsuarioDAL;
import bd.entidades.Usuario;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "TelaUsuario", urlPatterns = {"/TelaUsuario"})
public class TelaUsuario extends HttpServlet {

     public String buscaUsuario(String filtro, Conexao con) {
        String res = "";
        ArrayList<Usuario> usuarios = new UsuarioDAL().getUsers(filtro,true,con);
        for (Usuario u : usuarios) {
          res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
              + "<td onclick='ApagaAlteraUser(\"apagar\",%s)'><img src='icones/apagar.png'/></td>"
              + "<td onclick='ApagaAlteraUser(\"alterar\",%s)'><img src='icones/alterar.png'/></a></td>"
              + "</tr>", "" + u.getCod(), u.getNome(),u.getFone(), u.getEnd(),
                        "" + u.getEmail(),"" + u.getAdmin(),""+u.isAtivo() ,""+u.getCod(), ""+u.getCod());
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
            UsuarioDAL ctr = new UsuarioDAL();
            Conexao con=new Conexao();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "upper(user_nome) like '%" + filtro.toUpperCase() + "%'";
                    response.getWriter().print(buscaUsuario(filtro,con));
                    break;
                case "apagar":
                    if (!ctr.apagar(cod,con))
                       erro = "Erro ao apagar o usu??rio";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Usuario u = ctr.getUser(cod,con);
                    response.getWriter().print(u); // retorna todos os dados na forma de lista (,,,)
                    break;
                case "confirmar": //novo e altera????o
                    erro="ok";
                    String nome = request.getParameter("nome");
                    String fone = request.getParameter("fone");
                    String end = request.getParameter("end");
                    String email = request.getParameter("email");
                    String senha = request.getParameter("senha");
                    String admin = (request.getParameter("admin"));
                    String ativo = (request.getParameter("ativo"));
                    Boolean ad = false;
                    Boolean at = false;
                    if(admin.contains("true"))
                        ad = true;
                    if(ativo.contains("true"))
                        at = true;
                    Usuario user = new Usuario(cod, nome, fone, end, email, senha, ad,at);
                    //response.getWriter().print(user.toString());
                    if (cod == 0) 
                    {   if (!ctr.salvar(user,con)) erro = "Erro ao gravar o usu??rio";}
                    else 
                    {   if (!ctr.alterar(user,con)) erro = "Erro ao alterar o usu??rio";}
                    response.getWriter().print(erro);
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
