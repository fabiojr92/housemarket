package housemarket.rodolforoca.com.Model;


import housemarket.rodolforoca.com.Enums.TipoAnunciante;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    private String nome;

    private String email;

    private String senha;

    private int active;

    private int tipo;

    @OneToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    private String telefone;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @OneToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "user_anuncio", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "anuncio_id"))
    private Set<Anuncio> anuncios;


    public Usuario(String nome) {
        this.nome = nome;
    }

    public Usuario() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Anuncio> getAnuncios() {
        return anuncios;
    }

    public void setAnuncios(Set<Anuncio> anuncios) {
        this.anuncios = anuncios;
    }
}
