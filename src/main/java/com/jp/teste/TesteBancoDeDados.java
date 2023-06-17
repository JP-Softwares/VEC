package com.jp.teste;

import com.jp.controle.*;
import com.jp.modelos.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;

public class TesteBancoDeDados {

    public static void main(String[] args) throws Exception {
        Marca marcateste = new Marca();
        MarcaControle marcaControle = new MarcaControle();
        marcateste.setUrl("www.google.com.br");
        marcateste.setNome("Santaninha");
        //marcaControle.incluir(marcateste);
        //System.out.println(marcaControle.buscar(2).getNome());


        Modelo modeloteste = new Modelo();
        modeloteste.setNome("camaro");
        modeloteste.setTipo(TipoDoVeiculo.SEDAN);
        modeloteste.setUrlModelo("www.kes.com.br");
        modeloteste.setMarca(marcaControle.buscar(1));
        ModeloControle modeloControle = new ModeloControle();
        //modeloControle.incluir(modeloteste);
        Iterator<Modelo> lista = modeloControle.listar().iterator();
        /*while(lista.hasNext()){
            Modelo aux = lista.next();
            System.out.println(aux.getNome());
        }*/

        ProprietarioControle proprietarioControle = new ProprietarioControle();
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
        //proprietarioControle.incluir(proprietarioteste);

        VeiculoControle veiculoControle = new VeiculoControle();
        Veiculo veiculoteste = new Veiculo();
        veiculoteste.setCombustivel(TipoDoCombustivel.ELETRICO);
        veiculoteste.setModelo(modeloControle.buscar(1));
        veiculoteste.setKilometragem(50);
        veiculoteste.setPlaca("ABC0765");
        veiculoteste.setProprietario(proprietarioControle.buscar(1));
        veiculoteste.setSituacao(SituacaoDoVeiculo.DISPONIVEL);
        veiculoteste.setAnoFabricacao(2009);
        veiculoteste.setAnoModelo(2007);
        //veiculoControle.incluir(veiculoteste);

        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        TipoDeGastos tipoDeGastosteste = new TipoDeGastos();
        tipoDeGastosteste.setIcone("Ai pai para");
        tipoDeGastosteste.setNome("IPVA");
        //tipoDeGastosControle.incluir(tipoDeGastosteste);



        GastosControle gastosControle = new GastosControle();
        Gastos gastosteste = new Gastos();
        gastosteste.setData(Date.valueOf("2023-09-13"));
        gastosteste.setDescricao("Apareço no fim?");
        gastosteste.setVeiculo(veiculoControle.buscar(1));
        gastosteste.setValor(13.45);
        gastosteste.setTipoDeGastos(tipoDeGastosControle.buscar(1));
        //gastosControle.incluir(gastosteste);

        ArrayList<Gastos> Janeiro = new ArrayList<>();
        Janeiro = gastosControle.listarPorMes().get(1);
        Iterator<Gastos> listaGastos = Janeiro.iterator();
        while(listaGastos.hasNext()){
            Gastos aux1 = listaGastos.next();
            System.out.println(aux1.getDescricao());
        }
    }
}
