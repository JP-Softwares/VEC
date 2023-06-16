package com.jp.controle;
import com.jp.modelos.*;

import java.util.ArrayList;
import java.util.Iterator;

import com.jp.modelos.TipoDeGastos;

public interface ITipoDeGastosControle {
    void incluir(TipoDeGastos objeto) throws Exception;

    void alterar(TipoDeGastos objeto) throws Exception;


    ArrayList<TipoDeGastos> listar() throws Exception;

    TipoDeGastos buscar(String nome) throws Exception;

    TipoDeGastos buscar(int id) throws Exception;
}
