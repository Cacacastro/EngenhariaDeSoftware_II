package bd.dao;

import bd.entidades.Exemplar;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExemplarDAO {
    public ExemplarDAO() {
    }
    
    public boolean salvar (Exemplar e, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Exemplar> exe = getExemplares("",true, con);
        int i = exe.get(exe.size()-1).getExe_cod()+1;
        String sql="insert into exemplar (exe_cod,liv_cod,exe_est,exe_disp,exe_dataent) values ('"+i+"', '"+e.getExe_cod()+"', '"+e.getLiv_cod().getCod()+"', '"+e.getExe_est()+"', '"+e.getExe_disp()+"', '"+e.getExe_dataent()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    
    public boolean alterar (Exemplar e, Conexao con)
    {   
        String sql = "update exemplar set exe_est='"+e.getExe_est()+"',liv_cod='"+e.getLiv_cod().getCod()+"',exe_disp='"+e.getExe_disp()+"', exe_dataent='"+e.getExe_dataent()+"' where exe_cod="+e.getExe_cod();
        System.out.println(sql);
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
        LivroDAO ldao = new LivroDAO(); 
        String sql="select * from exemplar where exe_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              e.setExe_cod(cod);
              int var = rs.getInt("liv_cod");
              e.setLiv_cod(ldao.getLivro(var, con));
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
        LivroDAO ldao = new LivroDAO(); 
        String sql="select * from exemplar";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by exe_disp";
        else
            sql+=" order by exe_cod";
        System.out.println(sql);
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var = rs.getInt("liv_cod");
              lista.add(new Exemplar(rs.getInt("exe_cod"), ldao.getLivro(var, con), rs.getString("exe_est"), rs.getDate("exe_dataent"), rs.getBoolean("exe_disp")));
          }
             
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    public Exemplar getExeLiv(int cod, Conexao con)
    {
        String sql = "select * from exemplar where liv_cod = '"+cod+"' and exe_disp = 'true'";
        LivroDAO ldao = new LivroDAO(); 
        ArrayList <Exemplar> lista=new ArrayList();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var = rs.getInt("liv_cod");
              lista.add(new Exemplar(rs.getInt("exe_cod"), ldao.getLivro(var, con), rs.getString("exe_est"), rs.getDate("exe_dataent"), rs.getBoolean("exe_disp")));
          }
   
        }
        catch(Exception e){System.out.println(e);}
        return lista.get(0);
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
