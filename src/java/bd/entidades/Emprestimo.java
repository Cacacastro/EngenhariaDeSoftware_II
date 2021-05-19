package bd.entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Emprestimo {
    private int cod,duracao,user_cod;
    private Date data;
    private double valorTotal;

    public Emprestimo() {
        this(Date.valueOf(LocalDate.now()),3,0,0);
    }
    
    public Emprestimo(Date data, int duracao, double valorTotal, int user_cod) {
        cod = 0;
        this.duracao = duracao;
        this.user_cod = user_cod;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    public Emprestimo(int cod, int duracao, int user_cod, Date data, double valorTotal) {
        this.cod = cod;
        this.duracao = duracao;
        this.user_cod = user_cod;
        this.data = data;
        this.valorTotal = valorTotal;
    }

    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        if(duracao>=0)
            this.duracao = duracao;
        else
            this.duracao = 0;
    }

    public int getUser_cod() {
        return user_cod;
    }

    public void setUser_cod(int user_cod) {
        this.user_cod = user_cod;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return cod + "," + duracao + "," + user_cod + "," + data + "," + valorTotal;
    }
    
    
    
}
