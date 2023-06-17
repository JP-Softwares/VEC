package com.jp.teste;

import com.jp.controle.MarcaControle;
import com.jp.controle.ModeloControle;
import com.jp.modelos.Marca;
import com.jp.modelos.Modelo;
import com.jp.modelos.TipoDoVeiculo;

public class TesteBancoDeDados {

    public static void main(String[] args) throws Exception {
        //Marca marcateste = new Marca();
        MarcaControle marcaControle = new MarcaControle();
        //marcateste.setUrl("www.google.com.br");
        //marcateste.setNome("Santaninha");
        //marcaControle.incluir(marcateste);
        //System.out.println(marcaControle.buscar(2).getNome());


        Modelo modeloteste = new Modelo();
        modeloteste.setNome("Potente");
        modeloteste.setTipo(TipoDoVeiculo.SEDAN);
        modeloteste.setUrlModelo("www.kekw.com.br");
        modeloteste.setMarca(marcaControle.buscar(1));
        ModeloControle modeloControle = new ModeloControle();
        modeloControle.incluir(modeloteste);
    }
}
