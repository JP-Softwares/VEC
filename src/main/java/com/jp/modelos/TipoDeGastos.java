package com.jp.modelos;

public class TipoDeGastos {

    private int id = 0;

    private String nome = "";

    private String icone = "";

    public TipoDeGastos() {
    }

    public TipoDeGastos(int id, String nome, String icone) {
        this.id = id;
        this.nome = nome;
        this.icone = icone;
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

    public String getIcone() {
        return icone;
    }

    public void setIcone(String icone) {
        this.icone = icone;
    }
}
