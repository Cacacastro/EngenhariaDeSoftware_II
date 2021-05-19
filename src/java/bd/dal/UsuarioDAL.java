package bd.dal;

import bd.entidades.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAL {

    public UsuarioDAL() {
    }
    
    public boolean salvar (Usuario u)
    {
        //erro ao inserir sem o cod
        ArrayList <Usuario> usuarios = getUsers("",true);
        int i = usuarios.get(usuarios.size()-1).getCod()+1;
        String sql="insert into usuario (user_cod,user_nome,user_fone,user_end,user_email,user_senha, user_admin) values ('"+i+"', '"
                +u.getNome()+"', '"+u.getFone()+"', '"+u.getEnd()+"', '"+u.getEmail()+"', '"+u.getSenha()+"', '"+u.getAdmin()+"')";
        
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                              
    }
    public boolean alterar (Usuario u)
    {   
        String sql = "update usuario set user_nome='"+u.getNome()+"', user_fone='"+u.getFone()+"', user_end='"+u.getEnd()+"', user_email='"
                +u.getEmail()+"', user_senha='"+u.getSenha()+"' where user_cod="+u.getCod();
        Conexao con=new Conexao();
        boolean flag=con.manipular(sql);
        con.fecharConexao();
        return flag;                       
    }
    public boolean apagar(int cod)
    {
        Conexao con=new Conexao();
        boolean flag=con.manipular("delete from usuario where user_cod="+cod);
        con.fecharConexao();
        return flag;                      
    }
    public Usuario getUser(int cod)
    {   Usuario u=new Usuario();
        String sql="select * from usuario where user_cod="+cod;
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setCod(cod);
              u.setNome(rs.getString("user_nome"));
              u.setFone(rs.getString("user_fone"));
              u.setEnd(rs.getString("user_end"));
              u.setEmail(rs.getString("user_email"));
              u.setSenha(rs.getString("user_senha"));
              u.setAdmin(rs.getBoolean("user_admin"));
          }

        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return u;
    }
    public ArrayList <Usuario> getUsers(String filtro,boolean flag)
    {   ArrayList <Usuario> lista=new ArrayList();
        String sql="select * from usuario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by user_nome";
        else
            sql+=" order by user_cod";
        Conexao con=new Conexao();
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Usuario(rs.getInt("user_cod"),rs.getString("user_nome"),rs.getString("user_fone"),rs.getString("user_end"),rs.getString("user_email"),rs.getString("user_senha"),rs.getBoolean("user_admin")));
        }
        catch(Exception e){System.out.println(e);}
        con.fecharConexao();
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
