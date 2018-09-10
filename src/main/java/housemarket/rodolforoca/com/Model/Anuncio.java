package housemarket.rodolforoca.com.Model;

import housemarket.rodolforoca.com.Enums.TipoAnuncio;

import javax.persistence.*;

@Entity
public class Anuncio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "anuncio_id")
    private int id;

    @Column(nullable = false)
    private String titulo;

    private String observacoes;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Usuario anunciante;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "imovel_id")
    private Imovel imovel;

    private int tipo;

//    public String fotos;

    public Anuncio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Usuario getAnunciante() {
        return anunciante;
    }

    public void setAnunciante(Usuario anunciante) {
        this.anunciante = anunciante;
    }

    public Imovel getImovel() {
        return imovel;
    }

    public void setImovel(Imovel imovel) {
        this.imovel = imovel;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
}
