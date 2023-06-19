package com.jp.controle;
import com.jp.modelos.*;

import java.util.*;

public interface IGastosControle {
    void incluir(Gastos objeto) throws Exception;

    void alterar(Gastos objeto) throws Exception;

    ArrayList<Gastos> listar(Veiculo objeto) throws Exception;
    HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto) throws Exception;

    HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto, int ano) throws Exception;

    Gastos buscar(int id) throws Exception;
    Gastos buscar(TipoDeGastos TipoDeGastos) throws Exception;

    double[] valorTotalMes(Veiculo objeto, int ano) throws Exception;

    public HashMap<String, Double> valorTotalTipo(Veiculo objeto, int ano) throws Exception;

    ArrayList<Gastos> filtrarGastos(Collection gastos, String filtro)throws Exception;
}
