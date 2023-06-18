package com.jp.controle;
import com.jp.modelos.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import java.util.Date;

public interface IGastosControle {
    void incluir(Gastos objeto) throws Exception;

    void alterar(Gastos objeto) throws Exception;

    ArrayList<Gastos> listar(Veiculo objeto) throws Exception;
    HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto) throws Exception;

    Gastos buscar(int id) throws Exception;
    Gastos buscar(TipoDeGastos TipoDeGastos) throws Exception;
}
