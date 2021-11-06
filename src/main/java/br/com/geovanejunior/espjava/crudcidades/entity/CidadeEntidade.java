package br.com.geovanejunior.espjava.crudcidades.entity;

import javax.persistence.*;

@Entity
@Table(name = "cidade")
public class CidadeEntidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String nome;
    @Column(length = 02)
    private String estado;

    public CidadeEntidade() {
    }

    public CidadeEntidade(String nome, String estado) {
        this.nome = nome;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
