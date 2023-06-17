package com.jp.teste;

import com.jp.controle.MarcaControle;
import com.jp.controle.ModeloControle;
import com.jp.controle.ProprietarioControle;
import com.jp.modelos.*;

import java.util.Iterator;

public class TesteBancoDeDados {

    public static void main(String[] args) throws Exception {
        /*Marca marcateste = new Marca();
        MarcaControle marcaControle = new MarcaControle();
        marcateste.setUrl("www.google.com.br");
        marcateste.setNome("Santaninha");
        marcaControle.incluir(marcateste);
        System.out.println(marcaControle.buscar(2).getNome());*/


        /*Modelo modeloteste = new Modelo();
        modeloteste.setNome("camaro");
        modeloteste.setTipo(TipoDoVeiculo.SEDAN);
        modeloteste.setUrlModelo("www.kes.com.br");
        modeloteste.setMarca(marcaControle.buscar(1));
        ModeloControle modeloControle = new ModeloControle();
        //modeloControle.incluir(modeloteste);
        Iterator<Modelo> lista = modeloControle.listar().iterator();
        while(lista.hasNext()){
            Modelo aux = lista.next();
            System.out.println(aux.getNome());
        }*/

        /*ProprietarioControle proprietarioControle = new ProprietarioControle();
        Proprietario proprietarioteste = new Proprietario();
        Telefone telefone = new Telefone();
        proprietarioteste.setCPF("70879415185");
        proprietarioteste.setCategoria(CategoriaCNH.A);
        proprietarioteste.setCNH("123456789");
        proprietarioteste.setEmail("jacinto@gmail.com");
        telefone.setDDD(062);
        telefone.setDDI(005);
        telefone.setNumero(96051345);
        proprietarioteste.setTelefone(telefone);
        proprietarioteste.setNome("Sucupira");
        proprietarioControle.incluir(proprietarioteste);*/

    }
}
