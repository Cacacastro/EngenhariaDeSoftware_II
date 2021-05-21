package bd.entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Exemplar {
    private int exe_cod;
    private Livro liv_cod;
    private String exe_est;
    private Date exe_dataent;
    private boolean exe_disp;

    public Exemplar() {
        this(0,new Livro(),"",Date.valueOf(LocalDate.now()),false);
    }

    public Exemplar(int exe_cod, Livro liv_cod, String exe_est, Date exe_dataent, boolean exe_disp) {
        this.exe_cod = exe_cod;
        this.liv_cod = liv_cod;
        this.exe_est = exe_est;
        this.exe_dataent = exe_dataent;
        this.exe_disp = exe_disp;
    }

    public Exemplar(Livro liv_cod, String exe_est, Date exe_dataent, boolean exe_disp) {
        exe_cod = 0;
        this.liv_cod = liv_cod;
        this.exe_est = exe_est;
        this.exe_dataent = exe_dataent;
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

    public String getExe_est() {
        return exe_est;
    }

    public void setExe_est(String exe_est) {
        this.exe_est = exe_est;
    }

    public Date getExe_dataent() {
        return exe_dataent;
    }

    public void setExe_dataent(Date exe_dataent) {
        this.exe_dataent = exe_dataent;
    }

    public boolean getExe_disp() {
        return exe_disp;
    }

    public void setExe_disp(boolean exe_disp) {
        this.exe_disp = exe_disp;
    }

    @Override
    public String toString() {
        return exe_cod + "," + liv_cod + "," + exe_est + "," + exe_dataent + "," + exe_disp;
    }
    
    

    
    
    
}