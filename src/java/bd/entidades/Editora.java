package bd.entidades;

public class Editora {
    private int edi_cod;
    private String edi_nome;

    public Editora() {
        this(0,"");
     }
     
    public Editora(int edi_cod, String edi_nome) {
        this.edi_cod = edi_cod;
        this.edi_nome = edi_nome;
    }

    public Editora(String edi_nome) {
        edi_cod = 0;
        this.edi_nome = edi_nome;
    }

    public int getEdi_cod() {
        return edi_cod;
    }

    public void setEdi_cod(int edi_cod) {
        this.edi_cod = edi_cod;
    }

    public String getEdi_nome() {
        return edi_nome;
    }

    public void setEdi_nome(String edi_nome) {
        this.edi_nome = edi_nome;
    }

    @Override
    public String toString() {
        return  edi_cod + "," + edi_nome;
    }
    
    
}
