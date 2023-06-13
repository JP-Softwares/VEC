package com.jp.modelos;

public class Modelo {

    private int id = 0;
    private String nome = "";
    private String urlModelo = "";
    private TipoDoVeiculo tipo = null;

    private Marca marca = null;


    public Modelo() {
    }

    public Modelo(int id, String nome, String urlModelo, TipoDoVeiculo tipo, Marca marca) {
        this.id = id;
        this.nome = nome;
        this.urlModelo = urlModelo;
        this.tipo = tipo;
        this.marca = marca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlModelo() {
        return urlModelo;
    }

    public void setUrlModelo(String urlModelo) {
        this.urlModelo = urlModelo;
    }

    public TipoDoVeiculo getTipo() {
        return tipo;
    }

    public void setTipo(TipoDoVeiculo tipo) {
        this.tipo = tipo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
}