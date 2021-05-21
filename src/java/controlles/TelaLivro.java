/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlles;

import bd.dal.EditoraDAL;
import bd.dal.GeneroDAL;
import bd.dal.LivroDAL;
import bd.entidades.Editora;
import bd.entidades.Genero;
import bd.entidades.Livro;
import bd.util.Conexao;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "TelaLivro", urlPatterns = {"/TelaLivro"})
public class TelaLivro extends HttpServlet {

    public String buscaLivro(String filtro, Conexao con) {
        String res = "";
        ArrayList<Livro> Livros = new LivroDAL().getLivros(filtro,true,con);
        for (Livro l : Livros) {
          res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
              + "<td onclick='ApagaAlteraLivro(\"apagar\",%s)'><img src='icones/apagar.png'/></td>"
              + "<td onclick='ApagaAlteraLivro(\"alterar\",%s)'><img src='icones/alterar.png'/></a></td>"
              + "</tr>", "" + l.getCod(), l.getTitulo(),""+l.getNumPag(), ""+l.getGen_cod().getGen_genero(),
                        ""+l.getEdi_cod().getEdi_nome(), ""+l.getCod(), ""+l.getCod());
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
            LivroDAL ctr = new LivroDAL();
            Conexao con=new Conexao();
            switch (acao.toLowerCase()) 
            {
                case "consultar":
                    String filtro = request.getParameter("filtro");
                    if (!filtro.isEmpty()) filtro = "upper(liv_titulo) like '%" + filtro.toUpperCase() + "%'";
                    response.getWriter().print(buscaLivro(filtro,con));
                    break;
                case "apagar":
                    if (!ctr.apagar(cod,con))
                       erro = "Erro ao apagar o livro";
                    response.getWriter().print(erro);
                    break;
                case "alterar":
                    Livro u = ctr.getLivro(cod,con);
                    response.getWriter().print(u); // retorna todos os dados na forma de lista (,,,)
                    break;
                case "confirmar": //novo e alteração
                    erro="ok";
                    String nome = request.getParameter("nome");
                    int paginas = Integer.parseInt(request.getParameter("paginas"));
                    int gen = Integer.parseInt(request.getParameter("gen"));
                    int editora = Integer.parseInt(request.getParameter("editora"));
                    //Verifica autores, genero e editora
                    EditoraDAL edal = new EditoraDAL();
                    Editora ed = edal.getEditora(editora, con);
                    GeneroDAL gdal = new GeneroDAL();
                    Genero g = gdal.getGenero(cod, con);
                    Livro liv = new Livro(cod, paginas,g,ed,nome);
                    if (cod == 0) 
                    {   if (!ctr.salvar(liv,con)) erro = "Erro ao gravar o livro";}
                    else 
                    {   if (!ctr.alterar(liv,con)) erro = "Erro ao alterar o livro";}
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
