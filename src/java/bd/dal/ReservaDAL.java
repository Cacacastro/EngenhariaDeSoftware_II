package bd.dal;

import bd.entidades.Reserva;
import bd.util.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ReservaDAL {

    public ReservaDAL() {
    }
    
    public boolean salvar (Reserva r, Conexao con)
    {
        //erro ao inserir sem o cod
        ArrayList <Reserva> res = getReservas("",true, con);
        int i;
        if(res.size()==0)
            i = 1;
        else
            i = res.get(res.size()-1).getRes_cod()+1;
        String sql="insert into reserva (res_cod, liv_cod, user_cod, res_data, res_valortot, res_is_emp) values ('"+i+"', '"
                +r.getLiv_cod().getCod()+"', '"+r.getUser_cod().getCod()+"', '"+r.getRed_data()+"', '"+r.getRes_valorTot()+"', '"+r.getRes_in_emp()+"')";
        
        boolean flag=con.manipular(sql);
        return flag;                              
    }
    
    public boolean alterar (Reserva r, Conexao con)
    {   
        String sql = "update reserva set liv_cod='"+r.getLiv_cod().getCod()+"', user_cod='"+r.getUser_cod().getCod()+"',res_valortot='"+r.getRes_valorTot()+"',res_is_emp='"+r.getRes_in_emp()+"', res_data='"+r.getRed_data()+
                "' where res_cod="+r.getRes_cod();

        boolean flag=con.manipular(sql);
        return flag;                       
    }
    
    public boolean apagar(int cod, Conexao con)
    {
        boolean flag=con.manipular("delete from reserva where res_cod="+cod);
        return flag;                      
    }
    
    public Reserva getReserva(int cod, Conexao con)
    {   Reserva r=new Reserva();
        LivroDAL ldal = new LivroDAL(); 
        UsuarioDAL udal = new UsuarioDAL();
        String sql="select * from reserva where res_cod="+cod;
        ResultSet rs = con.consultar(sql);
        try
        {
          if (rs.next())
          {
              r.setRes_cod(cod);
              r.setRed_data(rs.getDate("res_data"));
              r.setRes_valorTot(rs.getDouble("res_valortot"));
              int var = rs.getInt("user_cod");
              int var1 = rs.getInt("liv_cod");
              r.setUser_cod(udal.getUser(var, con));
              r.setLiv_cod(ldal.getLivro(var1, con));
              r.setRes_in_emp(rs.getBoolean("res_is_emp"));
          }

        }
        catch(Exception e){System.out.println(e);}
        return r;
    }
    public ArrayList <Reserva> getReservas(String filtro,boolean flag, Conexao con)
    {   ArrayList <Reserva> lista=new ArrayList();
        UsuarioDAL udal = new UsuarioDAL(); 
        LivroDAL ldal = new LivroDAL(); 
        String sql="select * from reserva";
        if (!filtro.isEmpty())
            sql+=" where "+filtro;
        if(!flag)
            sql+=" order by liv_cod";
        else
            sql+=" order by res_cod";
        ResultSet rs = con.consultar(sql);
        try
        {
          while(rs.next())
          {
              int var = rs.getInt("user_cod");
              int var1 = rs.getInt("liv_cod");
              lista.add(new Reserva(rs.getBoolean("res_is_emp"),rs.getInt("res_cod"),rs.getDate("res_data"),rs.getDouble("res_valortot"),udal.getUser(var, con),ldal.getLivro(var1, con)));
          }
             
        }
        catch(Exception e){System.out.println(e);}
        return lista;
    }
    
    private CharSequence parseString(int ano) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}