package bd.entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Reserva {
    public boolean res_in_emp;
    public int res_cod;
    public Date red_data;
    public double res_valorTot;
    public Usuario user_cod;
    public Livro liv_cod;

    public Reserva() {
        this(false,0,Date.valueOf(LocalDate.now()),0,new Usuario(), new Livro());
    }

    public Reserva(boolean res_in_emp, Date red_data, double res_valorTot, Usuario user_cod, Livro liv_cod) {
        this.res_in_emp = res_in_emp;
        this.red_data = red_data;
        this.res_valorTot = res_valorTot;
        this.user_cod = user_cod;
        this.liv_cod = liv_cod;
    }

    public Reserva(boolean res_in_emp, int res_cod, Date red_data, double res_valorTot, Usuario user_cod, Livro liv_cod) {
        this.res_in_emp = res_in_emp;
        this.res_cod = res_cod;
        this.red_data = red_data;
        this.res_valorTot = res_valorTot;
        this.user_cod = user_cod;
        this.liv_cod = liv_cod;
    }

    public boolean getRes_in_emp() {
        return res_in_emp;
    }

    public void setRes_in_emp(boolean res_in_emp) {
        this.res_in_emp = res_in_emp;
    }

    public int getRes_cod() {
        return res_cod;
    }

    public void setRes_cod(int res_cod) {
        this.res_cod = res_cod;
    }

    public Date getRed_data() {
        return red_data;
    }

    public void setRed_data(Date red_data) {
        this.red_data = red_data;
    }

    public double getRes_valorTot() {
        return res_valorTot;
    }

    public void setRes_valorTot(double res_valorTot) {
        this.res_valorTot = res_valorTot;
    }

    public Usuario getUser_cod() {
        return user_cod;
    }

    public void setUser_cod(Usuario user_cod) {
        this.user_cod = user_cod;
    }

    public Livro getLiv_cod() {
        return liv_cod;
    }

    public void setLiv_cod(Livro liv_cod) {
        this.liv_cod = liv_cod;
    }

    @Override
    public String toString() {
        return res_in_emp + "," + res_cod + "," + red_data + "," + res_valorTot + "," + user_cod + "," + liv_cod;
    }

    
}
