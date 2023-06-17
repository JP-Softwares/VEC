package com.jp.modelos;

import com.jp.controle.IMarcaControle;
import com.jp.controle.MarcaControle;

public class Marca {
    private int id = 0;
    private String nome = "";
    private String url = "";

    public Marca() {
    }

    /*public Marca buscar(String descricao) throws Exception{

        try {
            IMarcaControle marcaControle = new MarcaControle();
            Marca marca = marcaControle.buscar(descricao);
            return marca;
        } catch (Exception e) {
            throw e;
        }

    }

    public Marca buscar(int id) throws Exception{
        try {
            IMarcaControle marcaControle = new MarcaControle();
            Marca marca = marcaControle.buscar(id);
            return marca;
        } catch (Exception e) {
            throw e;
        }
    }*/

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