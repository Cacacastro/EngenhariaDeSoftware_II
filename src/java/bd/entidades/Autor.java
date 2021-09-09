/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.entidades;
import java.util.ArrayList;
import bd.dao.AutorDAO;
import bd.util.Conexao;

/**
 *
 * @author Carlos
 */
public class Autor
{
    private int aut_cod;
    private String aut_nome;
    
    public Autor(int cod, String nome)
    {
        this.aut_cod = cod;
        this.aut_nome = nome;
    }
    
    public Autor(String nome)
    {
        this(0,nome);
    }
    
    public Autor()
    {
        this(0,"");
    }

    public int getAut_cod() {
        return aut_cod;
    }

    public void setAut_cod(int aut_cod) {
        this.aut_cod = aut_cod;
    }

    public String getAut_nome() {
        return aut_nome;
    }

    public void setAut_nome(String aut_nome) {
        this.aut_nome = aut_nome;
    }

    public boolean salvar(Conexao con)
    {
        AutorDAO adao = new AutorDAO();
        return adao.salvar(this, con);
    }

    public boolean alterar(Conexao con)
    {
        AutorDAO adao = new AutorDAO();
        return adao.alterar(this, con);
    }

    public boolean apagar(Conexao con)
    {
        AutorDAO adao = new AutorDAO();
        return adao.apagar(this.aut_cod, con);
    }

    public Autor getAutor(Conexao con)
    {
        AutorDAO adao = new AutorDAO();
        return adao.getAutor(this.aut_cod, con);
    }

    public ArrayList <Autor> getAutores(String filtro,boolean flag,Conexao con)
    {
        AutorDAO adao = new AutorDAO();
        return adao.getAutores(filtro,flag,con);
    }

    @Override
    public String toString() {
        return aut_cod + "," + aut_nome;
    }
    
    
}
