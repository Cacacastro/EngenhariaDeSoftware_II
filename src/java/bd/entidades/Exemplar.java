package bd.entidades;

public class Exemplar {
    private int exe_cod;
    private Livro liv_cod;
    private int exe_qtd;
    private int exe_disp;

    public Exemplar()
    {
        this(0,new Livro(),0,0);
    }

    public Exemplar(int exe_cod, Livro liv_cod, int exe_qtd, int exe_disp) {
        this.exe_cod = exe_cod;
        this.liv_cod = liv_cod;
        this.exe_qtd = exe_qtd;
        this.exe_disp = exe_disp;
    }

    public Exemplar(Livro liv_cod, int exe_qtd, int exe_disp) {
        exe_cod = 0;
        this.liv_cod = liv_cod;
        this.exe_qtd = exe_qtd;
        this.exe_disp = exe_disp;
    }

    public int getExe_cod() {
        return exe_cod;
    }

    public void setExe_cod(int exe_cod) {
        this.exe_cod = exe_cod;
    }

    public Livro getLiv_cod() {
        return liv_cod;
    }

    public void setLiv_cod(Livro liv_cod) {
        this.liv_cod = liv_cod;
    }

    public int getExe_qtd() {
        return exe_qtd;
    }

    public void setExe_qtd(int exe_qtd) {
        this.exe_qtd = exe_qtd;
    }

    public int getExe_disp() {
        return exe_disp;
    }

    public void setExe_disp(int exe_disp) {
        this.exe_disp = exe_disp;
    }

    @Override
    public String toString() {
        return exe_cod + "," + liv_cod + "," + exe_qtd + "," + exe_disp;
    }
    
    
}