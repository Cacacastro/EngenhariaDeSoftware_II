package bd.entidades;

public class Controle_Devolucao {
    private int cod,emp_cod;
    private boolean multa, devolucao;
    private double valorMulta;

    public Controle_Devolucao() {
        this(false, false,0,0,0);
    }

    public Controle_Devolucao(boolean devolucao, boolean multa, double valorMulta, int user_cod, int emp_cod) {
        cod = 0;
        this.emp_cod = emp_cod;
        this.multa = multa;
        this.valorMulta = valorMulta;
    }

    public Controle_Devolucao(int cod,int emp_cod,boolean devolucao, boolean multa, double valorMulta) {
        this.cod = cod;
        this.emp_cod = emp_cod;
        this.devolucao = devolucao;
        this.multa = multa;
        this.valorMulta = valorMulta;
    }

    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getEmp_cod() {
        return emp_cod;
    }

    public void setEmp_cod(int emp_cod) {
        this.emp_cod = emp_cod;
    }

    public boolean getMulta() {
        return multa;
    }

    public boolean getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(boolean devolucao) {
        this.devolucao = devolucao;
    }

    public void setMulta(boolean multa) {
        this.multa = multa;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        if(valorMulta<=0)
            this.valorMulta = 0;
        if(valorMulta>0)
        {
            this.valorMulta = valorMulta;
            multa = true;
        }
            
    }

    @Override
    public String toString() {
        return cod + "," + emp_cod + "," + multa + "," + valorMulta + "," + devolucao;
    }

    
    
    
}
