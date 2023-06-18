package com.jp.modelos;

public class Proprietario {
    private int id = 0;
    private String CPF = "";
    private String nome = "";
    private Telefone telefone = null;
    private String email = "";
    private String CNH = "";
    private CategoriaCNH  categoria = null;

    private int numeroDeCarros = 0;

    public Proprietario() {
    }

    public Proprietario(int id, String CPF, String nome, Telefone telefone, String email, String CNH, CategoriaCNH categoria, int numeroDeCarros) {
        this.id = id;
        this.CPF = CPF;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.CNH = CNH;
        this.categoria = categoria;
        this.numeroDeCarros = numeroDeCarros;
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

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public int getNumeroDeCarros() {
        return numeroDeCarros;
    }

    public void setNumeroDeCarros(int numeroDeCarros) {
        this.numeroDeCarros = numeroDeCarros;
    }
}