package bd.entidades;

public class Livro {
    private int cod, numPag, gen_cod,edi_cod;
    private String titulo;

    public Livro() {
        this("",0,0,0);
    }

    public Livro( String titulo, int numPag, int gen_cod,int edi_cod) {
        this.cod = 0;
        this.numPag = numPag;
        this.gen_cod = gen_cod;
        this.edi_cod = edi_cod;
        this.titulo = titulo;
    }

    public Livro(int cod, int numPag, int gen_cod, int edi_cod, String titulo) {
        this.cod = cod;
        this.numPag = numPag;
        this.gen_cod = gen_cod;
        this.edi_cod = edi_cod;
        this.titulo = titulo;
    }
    
    

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public int getNumPag() {
        return numPag;
    }

    public void setNumPag(int numPag) {
        if(numPag>=0)
             this.numPag = numPag;
        else
            this.numPag = 0;
    }

    public int getGen_cod() {
        return gen_cod;
    }

    public void setGen_cod(int gen_cod) {
        this.gen_cod = gen_cod;
    }
    
    public int getEdi_cod() {
        return edi_cod;
    }

    public void setEdi_cod(int edi_cod) {
        this.edi_cod = edi_cod;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return cod + "," +numPag + "," + gen_cod + "," + edi_cod + "," + titulo;
    }
    
    
}
