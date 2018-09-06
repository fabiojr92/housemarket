package housemarket.rodolforoca.com.Model;

import housemarket.rodolforoca.com.Enums.TipoImovel;

import javax.persistence.*;

@Entity
public class Imovel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "imovel_id")
    private int id;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @Column(nullable = false)
    private String tipo;

    @Column(name = "area_total")
    private String areaTotalM2;

    @Column(name = "area_construida")
    private String areaConstruidaM2;

    @Column(name = "qtd_quartos")
    private String qtdQuartos;

    @Column(name = "qtd_vagas")
    private String qtdVagasGaragem;

    @Column(name = "tem_piscina")
    private String temPiscina;

    @Column(name = "tem_churrasqueira")
    private String temChurrasqueira;
    
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAreaTotalM2() {
        return areaTotalM2;
    }

    public void setAreaTotalM2(String areaTotalM2) {
        this.areaTotalM2 = areaTotalM2;
    }

    public String getAreaConstruidaM2() {
        return areaConstruidaM2;
    }

    public void setAreaConstruidaM2(String areaConstruidaM2) {
        this.areaConstruidaM2 = areaConstruidaM2;
    }

    public String getQtdQuartos() {
        return qtdQuartos;
    }

    public void setQtdQuartos(String qtdQuartos) {
        this.qtdQuartos = qtdQuartos;
    }

    public String getQtdVagasGaragem() {
        return qtdVagasGaragem;
    }

    public void setQtdVagasGaragem(String qtdVagasGaragem) {
        this.qtdVagasGaragem = qtdVagasGaragem;
    }

    public String getTemPiscina() {
        return temPiscina;
    }

    public void setTemPiscina(String temPiscina) {
        this.temPiscina = temPiscina;
    }

    public String getTemChurrasqueira() {
        return temChurrasqueira;
    }

    public void setTemChurrasqueira(String temChurrasqueira) {
        this.temChurrasqueira = temChurrasqueira;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
