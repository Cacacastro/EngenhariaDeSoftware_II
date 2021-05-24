package bd.dal;

import bd.entidades.Emprestimo;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmprestimoDAL {

    public EmprestimoDAL() {
    }
    
    public boolean salvar (Emprestimo u, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Emprestimo> emp = getEmprestimos("",true, con);
        int i;
        if(emp.size()==0)
            i =1;
        else
            i = emp.get(emp.size()-1).getCod()+1;
        String sql="insert into emprestimo (emp_cod,emp_duracao,emp_valortotal,user_cod,emp_data) values ('"+i+"', '"
                +u.getDuracao()+"', '"+u.getValorTotal()+"', '"+u.getUser_cod().getCod()+"', '"+u.getData()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    
    public boolean alterar (Emprestimo u, Conexao con)
    {   
        String sql = "update emprestimo set emp_duracao='"+u.getDuracao()+"', emp_valortotal='"+u.getValorTotal()+"', user_cod='"+u.getUser_cod().getCod()+
                "', emp_data='"+u.getData()+"' where emp_cod="+u.getCod();

        boolean flag=con.manipular(sql);
        return flag;                       
    }
    
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from emprestimo where emp_cod="+cod);
        return flag;                      
    }
    
    public Emprestimo getEmprestimo(int cod, Conexao con)
    {   Emprestimo u=new Emprestimo();
        UsuarioDAL udal = new UsuarioDAL(); 
        String sql="select * from emprestimo where emp_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setCod(cod);
              u.setDuracao(rs.getInt("emp_duracao"));
              u.setValorTotal(rs.getDouble("emp_valortotal"));
              int var = rs.getInt("user_cod");
              u.setUser_cod(udal.getUser(var, con));
              u.setData(rs.getDate("emp_data"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return u;
    }
    public ArrayList <Emprestimo> getEmprestimos(String filtro,boolean flag, Conexao con)
    {   ArrayList <Emprestimo> lista=new ArrayList();
        UsuarioDAL udal = new UsuarioDAL(); 
        String sql="select * from emprestimo";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by emp_data";
        else
            sql+=" order by emp_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var = rs.getInt("user_cod");
              lista.add(new Emprestimo(rs.getInt("emp_cod"),rs.getInt("emp_duracao"),udal.getUser(var, con),rs.getDate("emp_data"),rs.getDouble("emp_valorTotal")));
          }
             
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
