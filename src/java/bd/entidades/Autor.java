/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd.entidades;

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

    @Override
    public String toString() {
        return aut_cod + "," + aut_nome;
    }
    
    
}
