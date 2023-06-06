package com.jp.modelos;

public class Proprietario {
    private int id = 0;
    private String CPF = "";
    private String nome = "";
    private telefone = null;
    private String email = "";
    private String CNH = "";
    private CategoriaCNH  categoria = null;

    public Proprietario() {
    }

    public Proprietario(int id, String CPF, String nome, String email, String CNH, CategoriaCNH categoria) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.email = email;
        this.CNH = CNH;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCNH() {
        return CNH;
    }

    public void setCNH(String CNH) {
        this.CNH = CNH;
    }

    public CategoriaCNH getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaCNH categoria) {
        this.categoria = categoria;
    }
}