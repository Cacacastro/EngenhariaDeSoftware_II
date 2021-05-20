package bd.entidades;

public class Genero {
    public int gen_cod;
    public String gen_genero;

    public Genero() {
        this(0,"");
    }

    public Genero(String gen_genero) {
        gen_cod =0;
        this.gen_genero = gen_genero;
    }

    
    public Genero(int gen_cod, String gen_genero) {
        this.gen_cod = gen_cod;
        this.gen_genero = gen_genero;
    }

    public int getGen_cod() {
        return gen_cod;
    }

    public void setGen_cod(int gen_cod) {
        this.gen_cod = gen_cod;
    }

    public String getGen_genero() {
        return gen_genero;
    }

    public void setGen_genero(String gen_genero) {
        this.gen_genero = gen_genero;
    }

    @Override
    public String toString() {
        return gen_cod + "," + gen_genero;
    }

    
}
