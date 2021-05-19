
package bd.dal;

import bd.entidades.Livro;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LivroDAL {
    public LivroDAL() {
    }
    
    public boolean salvar (Livro l)
    {
        //erro ao inserir sem o cod
        ArrayList <Livro> Livros = getLivros("",true);
        int i = Livros.get(Livros.size()-1).getCod()+1;
        
        //Pegar os cod de genero, autor e entidade
        String sql="insert into livros (liv_cod,liv_titulo,liv_numpag,gen_cod,edi_cod) values ('"+i+"', '"
                +l.getTitulo()+"', '"+l.getNumPag()+"', '"+l.getGen_cod()+"', '"+l.getEdi_cod()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Livro l)
    {   
        String sql = "update livros set liv_titulo='"+l.getTitulo()+"', liv_numpag='"+l.getNumPag()+"', gen_cod='"+l.getGen_cod()+
                "', edi_cod='"+l.getEdi_cod()+"' where liv_cod="+l.getCod();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int cod)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from livros where liv_cod="+cod);
        con.fecharConexao();
        return flag;                      
    }
    public Livro getUser(int cod)
    {   Livro l=new Livro();
        String sql="select * from livros where liv_cod="+cod;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              l.setCod(cod);
              l.setTitulo(rs.getString("liv_titulo"));
              l.setNumPag(rs.getInt("liv_numpag"));
              l.setGen_cod(rs.getInt("gen_cod"));
              l.setEdi_cod(rs.getInt("edi_cod"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return l;
    }
    public ArrayList <Livro> getLivros(String filtro,boolean flag)
    {   ArrayList <Livro> lista=new ArrayList();
        String sql="select * from livros";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by liv_titulo";
        else
            sql+=" order by liv_cod";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Livro(rs.getInt("liv_cod"),rs.getInt("liv_numpag"),rs.getInt("gen_cod"),rs.getInt("edi_cod"),rs.getString("liv_titulo")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
