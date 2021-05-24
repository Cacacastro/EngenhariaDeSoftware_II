package bd.dal;

import bd.entidades.Usuario;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsuarioDAL {

    public UsuarioDAL() {
    }
    
    public boolean salvar (Usuario u, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Usuario> usuarios = getUsers("",true,con);
        int i = usuarios.get(usuarios.size()-1).getCod()+1;
        String sql="insert into usuario (user_cod,user_nome,user_fone,user_end,user_email,user_senha, user_admin, user_ativo) values ('"+i+"', '"
                +u.getNome()+"', '"+u.getFone()+"', '"+u.getEnd()+"', '"+u.getEmail()+"', '"+u.getSenha()+"', '"+u.getAdmin()+"', '"+u.isAtivo()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    public boolean alterar (Usuario u, Conexao con)
    {   
        String sql = "update usuario set user_nome='"+u.getNome()+"', user_fone='"+u.getFone()+"', user_end='"+u.getEnd()+"', user_email='"
                +u.getEmail()+"', user_senha='"+u.getSenha()+"', user_admin='"+u.getAdmin()+"', user_ativo='"+u.isAtivo()+"' where user_cod="+u.getCod();
        
        boolean flag=con.manipular(sql);
        return flag;                       
    }
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from usuario where user_cod="+cod);
        return flag;                      
    }
    public Usuario getUser(int cod, Conexao con)
    {   Usuario u=new Usuario();
        String sql="select * from usuario where user_cod="+cod;
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
              u.setAtivo(rs.getBoolean("user_ativo"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return u;
    }
    public Usuario getUserByEmail(String email, Conexao con)
    {   Usuario u=new Usuario();
        String sql="select * from usuario where user_email='"+email+"'";
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              u.setCod(rs.getInt("user_cod"));
              u.setNome(rs.getString("user_nome"));
              u.setFone(rs.getString("user_fone"));
              u.setEnd(rs.getString("user_end"));
              u.setEmail(rs.getString("user_email"));
              u.setSenha(rs.getString("user_senha"));
              u.setAdmin(rs.getBoolean("user_admin"));
              u.setAtivo(rs.getBoolean("user_ativo"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return u;
    }
    public ArrayList <Usuario> getUsers(String filtro,boolean flag, Conexao con)
    {   ArrayList <Usuario> lista=new ArrayList();
        String sql="select * from usuario";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by user_nome";
        else
            sql+=" order by user_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
             lista.add(new Usuario(rs.getInt("user_cod"),rs.getString("user_nome"),rs.getString("user_fone"),rs.getString("user_end"),rs.getString("user_email"),rs.getString("user_senha"),rs.getBoolean("user_admin"),rs.getBoolean("user_ativo")));
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
