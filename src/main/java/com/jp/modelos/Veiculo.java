package com.jp.modelos;

public class Veiculo {
    private int id = 0;
    private String placa = "";
    private int anoFabricacao = 0;
    private int anoModelo = 0;
    private int kilometragem = 0;
    private TipoDoCombustivel combustivel = null;
    private SituacaoDoVeiculo situacao = null;
    private Modelo modelo = null;
    private Proprietario proprietario = null;







    public Veiculo() {
    }


    public Veiculo(int id, String placa, int anoFabricacao, int anoModelo, int kilometragem, TipoDoCombustivel combustivel, SituacaoDoVeiculo situacao, Modelo modelo, Proprietario proprietario) {
        this.id = id;
        this.placa = placa;
        this.anoFabricacao = anoFabricacao;
        this.anoModelo = anoModelo;
        this.kilometragem = kilometragem;
        this.combustivel = combustivel;
        this.situacao = situacao;
        this.modelo = modelo;
        this.proprietario = proprietario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(int anoModelo) {
        this.anoModelo = anoModelo;
    }

    public int getKilometragem() {
        return kilometragem;
    }

    public void setKilometragem(int kilometragem) {
        this.kilometragem = kilometragem;
    }

    public TipoDoCombustivel getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(TipoDoCombustivel combustivel) {
        this.combustivel = combustivel;
    }


    public SituacaoDoVeiculo getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoDoVeiculo situacao) {
        this.situacao = situacao;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }
}
