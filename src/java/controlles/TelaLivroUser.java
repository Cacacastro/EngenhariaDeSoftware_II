package controlles;

import bd.dal.LivroDAL;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "TelaLivroUser", urlPatterns = {"/TelaLivroUser"})
public class TelaLivroUser extends HttpServlet {
public String buscaLivro(String filtro, Conexao con) throws SQLException {
        String res = "";
        int qtd=0;
        ArrayList<Livro> Livros = new LivroDAL().getLivros(filtro,true,con);
        for (Livro l : Livros) {
          String sql = "select COUNT(ex.exe_cod) as resultado from exemplar ex inner join livros l on ex.liv_cod = l.liv_cod and ex.liv_cod ="+ l.getCod() +"where ex.exe_disp = 'true'";
          ResultSet rs = con.consultar(sql);
          while(rs.next())
              qtd = rs.getInt("resultado");
          if(qtd>0)
            res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td>"
                + "<td onclick='EmprestimoLivro(\"empresta\",%s)'><button class=\"form-control  mb-2 mr-sm-2 btn btn-primary\" style=\"background-color: #c1e2b3; width:100px; color:black\">Empréstimo</button></td>"
                + "<td onclick='ReservaLivro(\"reserva\",%s)'><button class=\"form-control  mb-2 mr-sm-2 btn btn-primary\" style=\"background-color: #FFEB3B; width:83px; color:black\">Reservar</button></td>"
                + "</tr>", "" + l.getCod(), l.getTitulo(),""+l.getNumPag(), ""+l.getGen_cod().getGen_genero(),
                          ""+l.getEdi_cod().getEdi_nome(),""+qtd,"" + l.getCod(),"" + l.getCod());
          else
            res += String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td></td>"
                + "<td onclick='ReservaLivro(\"reserva\",%s)'><button class=\"form-control  mb-2 mr-sm-2 btn btn-primary\" style=\"background-color: #FFEB3B; width:83px; color:black\">Reservar</button></td>"
                + "</tr>", "" + l.getCod(), l.getTitulo(),""+l.getNumPag(), ""+l.getGen_cod().getGen_genero(),
                          ""+l.getEdi_cod().getEdi_nome(),""+qtd,"" + l.getCod());
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
            LivroDAL ctr = new LivroDAL();
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
        Logger.getLogger(TelaLivroUser.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(TelaLivroUser.class.getName()).log(Level.SEVERE, null, ex);
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
