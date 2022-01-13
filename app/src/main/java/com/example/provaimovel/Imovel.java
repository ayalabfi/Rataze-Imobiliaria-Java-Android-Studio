package com.example.provaimovel;

import java.io.Serializable;

public class Imovel implements Serializable {

    private Integer id;
    private String bairro;
    private String nome;
    private String tamanho;
    private String quartos;
    private String suites;
    private String banheiros;
    private String vagas;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getQuartos() {
        return quartos;
    }

    public void setQuartos(String quartos) {
        this.quartos = quartos;
    }

    public String getSuites() {
        return suites;
    }

    public void setSuites(String suites) {
        this.suites = suites;
    }

    public String getBanheiros() {
        return banheiros;
    }

    public void setBanheiros(String banheiros) {
        this.banheiros = banheiros;
    }

    public String getVagas() {
        return vagas;
    }

    public void setVagas(String vagas) {
        this.vagas = vagas;
    }

    @Override

    public String toString() {return nome;}
}

