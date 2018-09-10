package housemarket.rodolforoca.com.Model;

import housemarket.rodolforoca.com.Enums.TipoImovel;

import javax.persistence.*;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imovel_id")
    private int id;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(nullable = false)
    private int tipo;

    @Column(name = "area_total")
    private int areaTotalM2;

    @Column(name = "area_construida")
    private int areaConstruidaM2;

    @Column(name = "qtd_quartos")
    private int qtdQuartos;

    @Column(name = "qtd_vagas")
    private int qtdVagasGaragem;

    @Column(name = "tem_piscina")
    private boolean temPiscina;

    @Column(name = "tem_churrasqueira")
    private boolean temChurrasqueira;
    
    private String descricao;

    public Imovel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getAreaTotalM2() {
        return areaTotalM2;
    }

    public void setAreaTotalM2(int areaTotalM2) {
        this.areaTotalM2 = areaTotalM2;
    }

    public int getAreaConstruidaM2() {
        return areaConstruidaM2;
    }

    public void setAreaConstruidaM2(int areaConstruidaM2) {
        this.areaConstruidaM2 = areaConstruidaM2;
    }

    public int getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(int qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public int getQtdVagasGaragem() {
        return qtdVagasGaragem;
    }

    public void setQtdVagasGaragem(int qtdVagasGaragem) {
        this.qtdVagasGaragem = qtdVagasGaragem;
    }

    public boolean isTemPiscina() {
        return temPiscina;
    }

    public void setTemPiscina(boolean temPiscina) {
        this.temPiscina = temPiscina;
    }

    public boolean isTemChurrasqueira() {
        return temChurrasqueira;
    }

    public void setTemChurrasqueira(boolean temChurrasqueira) {
        this.temChurrasqueira = temChurrasqueira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
