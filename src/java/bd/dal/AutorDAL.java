package bd.dal;

import bd.entidades.Autor;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class AutorDAL {

    public AutorDAL() {
    }
    
    public boolean salvar (Autor u, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Autor> autores = getAutores("",true,con);
        int i = autores.get(autores.size()-1).getAut_cod()+1;
        String sql="insert into autor (aut_cod,aut_nome) values ('"+i+"', '"
                +u.getAut_nome()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    public boolean alterar (Autor u, Conexao con)
    {   
        String sql = "update autor set aut_nome='"+u.getAut_nome()+"' where aut_cod="+u.getAut_cod();
        
        boolean flag=con.manipular(sql);
        return flag;                       
    }
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from autor where aut_cod="+cod);
        return flag;                      
    }
    public Autor getAutor(int cod, Conexao con)
    {   
        Autor u = new Autor();
        String sql="select * from autor where aut_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setAut_cod(cod);
              u.setAut_nome(rs.getString("user_nome"));
          }
        }
        catch(Exception e){System.out.println(e);}
        return u;
    }
        
    public ArrayList <Autor> getAutores(String filtro,boolean flag, Conexao con)
    {   ArrayList <Autor> lista=new ArrayList();
        String sql="select * from autor";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by aut_nome";
        else
            sql+=" order by aut_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Autor(rs.getInt("aut_cod"),rs.getString("aut_nome")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
