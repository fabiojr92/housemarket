package housemarket.rodolforoca.com.Model;

public class Relatorio {

    private String tipo;
    private String anunciante;
    private Iterable<Anuncio> anuncios;
    private double total;

    public Iterable<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Iterable<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(String anunciante) {
        this.anunciante = anunciante;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(Iterable<Anuncio> anuncios) {
        double total = 0;
        for (Anuncio anuncio: anuncios) {
            total += anuncio.getPreco();
        }

        this.total = total;
    }
}
