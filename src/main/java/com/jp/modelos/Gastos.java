package com.jp.modelos;
import java.util.Date;

public class Gastos {

    private int id = 0;
    private String descricao = "";
    private double valor = 0.0;
    private Date data = null;

    public Gastos() {
    }

    public Gastos(int id, String descricao, double valor, Date data) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
