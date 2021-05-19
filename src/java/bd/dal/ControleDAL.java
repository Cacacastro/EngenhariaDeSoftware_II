package bd.dal;

import bd.entidades.Controle_Devolucao;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ControleDAL {
    public ControleDAL() {
    }
    
    public boolean salvar (Controle_Devolucao u)
    {
        //erro ao inserir sem o cod
        ArrayList <Controle_Devolucao> usuarios = getControles("",true);
        int i = usuarios.get(usuarios.size()-1).getCod()+1;
        String sql="insert into controle_devolucao (cdd_cod,cdd_multa,cdd_valormulta,emp_cod,cdd_dev) values ('"+i+"', '"
                +u.getMulta()+"', '"+u.getValorMulta()+"', '"+u.getEmp_cod()+"', '"+u.getDevolucao()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Controle_Devolucao u)
    {   
        String sql = "update controle_devolucao set cdd_multa='"+u.getMulta()+"', cdd_valormulta='"+u.getValorMulta()+"', emp_cod='"
                +u.getEmp_cod()+"', cdd_dev='"+u.getDevolucao()+"' where cdd_cod="+u.getCod();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int cod)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from controle_devolucao where cdd_cod="+cod);
        con.fecharConexao();
        return flag;                      
    }
    public Controle_Devolucao getControle(int cod)
    {   Controle_Devolucao u=new Controle_Devolucao();
        String sql="select * from controle_devolucao where cdd_cod="+cod;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setCod(cod);
              u.setMulta(rs.getBoolean("cdd_multa"));
              u.setValorMulta(rs.getDouble("cdd_valormulta"));
              u.setEmp_cod(rs.getInt("emp_cod"));
              u.setDevolucao(rs.getBoolean("cdd_dev"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return u;
    }
    public ArrayList <Controle_Devolucao> getControles(String filtro,boolean flag)
    {   ArrayList <Controle_Devolucao> lista=new ArrayList();
        String sql="select * from controle_devolucao";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by cdd_cod";
        else
            sql+=" order by cdd_cod";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Controle_Devolucao(rs.getInt("cdd_cod"),rs.getInt("emp_cod"),rs.getBoolean("cdd_dev"),rs.getBoolean("cdd_multa"),rs.getDouble("cdd_valormulta")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
