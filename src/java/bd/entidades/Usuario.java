package bd.entidades;

public class Usuario {
    private int cod;
    private String nome,fone,end,email,senha;
    private boolean admin,ativo;

    public Usuario() {
        this("","","","","",false,true);
    }

    public Usuario(String nome, String fone, String end, String email, String senha, boolean admin, boolean ativo) {
        cod = 0;
        this.nome = nome;
        this.fone = fone;
        this.end = end;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
        this.ativo = ativo;
    }

    public Usuario(int cod, String nome, String fone, String end, String email, String senha, boolean admin, boolean ativo) {
        this.cod = cod;
        this.nome = nome;
        this.fone = fone;
        this.end = end;
        this.email = email;
        this.senha = senha;
        this.admin = admin;
        this.ativo = ativo;
    }

    
    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    
    @Override
    public String toString() {
        return cod + "," + nome + "," + fone + "," + end + "," + email + "," + senha+ "," + admin + "," + ativo;
    }
    
    
    
}
