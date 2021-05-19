package bd.dal;

import bd.entidades.Emprestimo;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class EmprestimoDAL {

    public EmprestimoDAL() {
    }
    
    public boolean salvar (Emprestimo u)
    {
        //erro ao inserir sem o cod
        ArrayList <Emprestimo> emp = getEmprestimos("",true);
        int i = emp.get(emp.size()-1).getCod()+1;
        String sql="insert into emprestimo (emp_cod,emp_duracao,emp_valortotal,user_cod,emp_data) values ('"+i+"', '"
                +u.getDuracao()+"', '"+u.getValorTotal()+"', '"+u.getUser_cod()+"', '"+u.getData()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Emprestimo u)
    {   
        String sql = "update emprestimo set emp_duracao='"+u.getDuracao()+"', emp_valortotal='"+u.getValorTotal()+"', user_cod='"+u.getUser_cod()+
                "', emp_data='"+u.getData()+"' where emp_cod="+u.getCod();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int cod)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from emprestimo where emp_cod="+cod);
        con.fecharConexao();
        return flag;                      
    }
    public Emprestimo getEmprestimo(int cod)
    {   Emprestimo u=new Emprestimo();
        String sql="select * from emprestimo where emp_cod="+cod;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setCod(cod);
              u.setDuracao(rs.getInt("emp_duracao"));
              u.setValorTotal(rs.getDouble("emp_valortotal"));
              u.setUser_cod(rs.getInt("user_cod"));
              u.setData(rs.getDate("emp_data"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return u;
    }
    public ArrayList <Emprestimo> getEmprestimos(String filtro,boolean flag)
    {   ArrayList <Emprestimo> lista=new ArrayList();
        String sql="select * from emprestimo";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by emp_data";
        else
            sql+=" order by emp_cod";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Emprestimo(rs.getInt("emp_cod"),rs.getInt("emp_duracao"),rs.getInt("user_cod"),rs.getDate("emp_data"),rs.getDouble("emp_valorTotal")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
