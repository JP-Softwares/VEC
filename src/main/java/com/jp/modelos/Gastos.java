package com.jp.modelos;
import java.sql.Date;

public class Gastos {

    private int id = 0;
    private String descricao = "";
    private double valor = 0.0;
    private Date data = null;
    private TipoDeGastos tipoDeGastos = null;
    private Veiculo veiculo = null;
    public Gastos() {
    }

    public Gastos(int id, String descricao, double valor, Date data, TipoDeGastos tipoDeGastos, Veiculo veiculo) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
        this.tipoDeGastos = tipoDeGastos;
        this.veiculo = veiculo;
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

    public TipoDeGastos getTipoDeGastos() {
        return tipoDeGastos;
    }

    public void setTipoDeGastos(TipoDeGastos tipoDeGastos) {
        this.tipoDeGastos = tipoDeGastos;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}
