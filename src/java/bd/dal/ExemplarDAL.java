package bd.dal;

import bd.entidades.Exemplar;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExemplarDAL {
    public ExemplarDAL() {
    }
    
    public boolean salvar (Exemplar e, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Exemplar> exe = getExemplares("",true, con);
        int i = exe.get(exe.size()-1).getExe_cod()+1;
        String sql="insert into exemplar (exe_cod,liv_cod,exe_est,exe_disp,exe_dataent) values ('"+i+"', '"+e.getExe_cod()+"', '"+e.getLiv_cod()+"', '"+e.getExe_est()+"', '"+e.getExe_disp()+"', '"+e.getExe_dataent()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    
    public boolean alterar (Exemplar e, Conexao con)
    {   
        String sql = "update exemplar set exe_est='"+e.getExe_est()+"','"+e.getLiv_cod()+"',exe_disp='"+e.getExe_disp()+"', exe_dataent='"+e.getExe_dataent()+"' where exe_cod="+e.getExe_cod();

        boolean flag=con.manipular(sql);
        return flag;                       
    }
    
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from exemplar where exe_cod="+cod);
        return flag;                      
    }
    
    public Exemplar getExemplar(int cod, Conexao con)
    {   Exemplar e=new Exemplar();
        LivroDAL ldal = new LivroDAL(); 
        String sql="select * from exemplar where exe_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              e.setExe_cod(cod);
              int var = rs.getInt("liv_cod");
              e.setLiv_cod(ldal.getLivro(var, con));
              e.setExe_est(rs.getString("exe_est"));
              e.setExe_disp(rs.getBoolean("exe_disp")); 
              e.setExe_dataent(rs.getDate("exe_dataent"));
          }
        }
        catch(Exception er){System.out.println(er);}
        return e;
    }
    public ArrayList <Exemplar> getExemplares(String filtro,boolean flag, Conexao con)
    {   ArrayList <Exemplar> lista=new ArrayList();
        LivroDAL ldal = new LivroDAL(); 
        String sql="select * from exemplar";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by exe_disp";
        else
            sql+=" order by exe_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var = rs.getInt("liv_cod");
              lista.add(new Exemplar(rs.getInt("exe_cod"), ldal.getLivro(var, con), rs.getString("exe_est"), rs.getDate("ex_dataent"), rs.getBoolean("exe_disp")));
          }
             
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
