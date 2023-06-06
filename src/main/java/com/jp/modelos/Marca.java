package com.jp.modelos;

public class Marca {
    private int id = 0;
    private String nome = "";
    private String url = "";

    public Marca() {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Marca(int id, String nome, String url) {
        this.id = id;
        this.nome = nome;
        this.url = url;
    }
}
