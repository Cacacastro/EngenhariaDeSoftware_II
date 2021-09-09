
package bd.dao;

import bd.entidades.Livro;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LivroDAO {
    public LivroDAO() {
    }
    
    public boolean salvar (Livro l, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Livro> Livros = getLivros("",true,con);
        int i = Livros.get(Livros.size()-1).getCod()+1;
        
        //Pegar os cod de genero, autor e entidade
        String sql="insert into livros (liv_cod,liv_titulo,liv_numpag,gen_cod,edi_cod) values ('"+i+"', '"
                +l.getTitulo()+"', '"+l.getNumPag()+"', '"+l.getGen_cod().getGen_cod()+"', '"+l.getEdi_cod().getEdi_cod()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    public boolean alterar (Livro l, Conexao con)
    {   
        String sql = "update livros set liv_titulo='"+l.getTitulo()+"', liv_numpag='"+l.getNumPag()+"', gen_cod='"+l.getGen_cod().getGen_cod()+
                "', edi_cod='"+l.getEdi_cod().getEdi_cod()+"' where liv_cod="+l.getCod();

        boolean flag=con.manipular(sql);
        return flag;                       
    }
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from livros where liv_cod="+cod);
        return flag;                      
    }
    public Livro getLivro(int cod, Conexao con)
    {   Livro l=new Livro();
        GeneroDAO gdao = new GeneroDAO();
        EditoraDAO edao = new EditoraDAO();
        String sql="select * from livros where liv_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              l.setCod(cod);
              l.setTitulo(rs.getString("liv_titulo"));
              l.setNumPag(rs.getInt("liv_numpag"));
              int var = rs.getInt("gen_cod");
              l.setGen_cod(gdao.getGenero(var, con));
              var = rs.getInt("edi_cod");
              l.setEdi_cod(edao.getEditora(var, con));
          }

        }
        catch(Exception e){System.out.println(e);}
        return l;
    }
    public ArrayList <Livro> getLivros(String filtro,boolean flag, Conexao con)
    {   ArrayList <Livro> lista=new ArrayList();
        GeneroDAO gdao = new GeneroDAO();
        EditoraDAO edao = new EditoraDAO();
        String sql="select * from livros";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by liv_titulo";
        else
            sql+=" order by liv_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var1 = rs.getInt("gen_cod");
              int var2 = rs.getInt("edi_cod");
              lista.add(new Livro(rs.getInt("liv_cod"),rs.getInt("liv_numpag"),gdao.getGenero(var1, con),edao.getEditora(var2, con),rs.getString("liv_titulo")));
          }
             
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
