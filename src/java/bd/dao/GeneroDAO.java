package bd.dao;

import bd.entidades.Genero;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class GeneroDAO {
    public GeneroDAO() {
    }
    
    public boolean salvar (Genero g, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Genero> generos = getGens("",true,con);
        int i = generos.get(generos.size()-1).getGen_cod()+1;
        String sql= "insert into genero (gen_cod,gen_genero) values ('"+i+"', '"+g.getGen_cod()+"', '"+g.getGen_genero()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    public boolean alterar (Genero g, Conexao con)
    {   
        String sql = "update genero set gen_genero='"+g.getGen_genero()+"' where gen_cod="+g.getGen_cod();
        
        boolean flag=con.manipular(sql);
        return flag;                       
    }
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from genero where gen_cod="+cod);
        return flag;                      
    }
    public Genero getGenero(int cod, Conexao con)
    {   Genero g=new Genero();
        String sql="select * from genero where gen_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              g.setGen_cod(cod);
              g.setGen_genero(rs.getString("gen_genero"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return g;
    }
    public ArrayList <Genero> getGens(String filtro,boolean flag, Conexao con)
    {   ArrayList <Genero> lista=new ArrayList();
        String sql="select * from genero";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by gen_genero";
        else
            sql+=" order by gen_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Genero(rs.getInt("gen_cod"),rs.getString("gen_genero")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
