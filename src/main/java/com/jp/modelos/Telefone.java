package com.jp.modelos;

public class Telefone {

    private int DDD = 0;
    private int DDI = 0;
    private int numero = 0;

    public Telefone() {

    }

    public Telefone(int DDD, int DDI, int numero) {
        this.DDD = DDD;
        this.DDI = DDI;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return DDD + ";" + DDI + ";" + numero;
    }

    public int getDDD() {
        return DDD;
    }

    public void setDDD(int DDD) {
        this.DDD = DDD;
    }

    public int getDDI() {
        return DDI;
    }

    public void setDDI(int DDI) {
        this.DDI = DDI;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
}
