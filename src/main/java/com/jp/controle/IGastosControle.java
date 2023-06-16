package com.jp.controle;
import com.jp.modelos.Gastos;
import java.util.ArrayList;
import java.util.Iterator;
import com.jp.modelos.Modelo;
import com.jp.modelos.Proprietario;
import com.jp.modelos.TipoDeGastos;

import java.util.Date;

public interface IGastosControle {
    void incluir(Gastos objeto) throws Exception;

    void alterar(Gastos objeto) throws Exception;

    ArrayList<Gastos> listar() throws Exception;

    Gastos buscar(int id) throws Exception;
    Gastos buscar(TipoDeGastos TipoDeGastos) throws Exception;
}
