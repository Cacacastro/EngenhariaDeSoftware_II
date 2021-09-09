package bd.dao;

import bd.entidades.Editora;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EditoraDAO {
    public EditoraDAO() {
    }
    
    public boolean salvar (Editora u, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Editora> ed = getEditoras("",true,con);
        int i = ed.get(ed.size()-1).getEdi_cod()+1;
        String sql="insert into editora (edi_cod,edi_nome) values ('"+i+"', '"
                +u.getEdi_nome()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    public boolean alterar (Editora u, Conexao con)
    {   
        String sql = "update editora set edi_nome='"+u.getEdi_nome()+"' where edi_cod="+u.getEdi_cod();
        
        boolean flag=con.manipular(sql);
        return flag;                       
    }
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from editora where user_cod="+cod);
        return flag;                      
    }
    public Editora getEditora(int cod, Conexao con)
    {   Editora u=new Editora();
        String sql="select * from editora where edi_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setEdi_cod(cod);
              u.setEdi_nome(rs.getString("edi_nome"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return u;
    }
    public ArrayList <Editora> getEditoras(String filtro,boolean flag, Conexao con)
    {   ArrayList <Editora> lista=new ArrayList();
        String sql="select * from editora";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by edi_nome";
        else
            sql+=" order by edi_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Editora(rs.getInt("edi_cod"),rs.getString("edi_nome")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
