package com.mygamelist.model;

import jakarta.persistence.*;

@Entity
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String genero;
    private String plataforma;
    private Integer anoLancamento;
    private String capaUrl;

    // Construtor vazio exigido pelo JPA
    public Jogo() {}

    // Construtor com parâmetros (opcional, ajuda nos testes)
    public Jogo(String titulo, String genero, String plataforma, Integer anoLancamento, String capaUrl) {
        this.titulo = titulo;
        this.genero = genero;
        this.plataforma = plataforma;
        this.anoLancamento = anoLancamento;
        this.capaUrl = capaUrl;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

    public void setAnoLancamento(Integer anoLancamento) {
        this.anoLancamento = anoLancamento;
    }

    public String getCapaUrl() {
        return capaUrl;
    }

    public void setCapaUrl(String capaUrl) {
        this.capaUrl = capaUrl;
    }
}
