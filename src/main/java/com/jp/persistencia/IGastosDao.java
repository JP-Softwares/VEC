package com.jp.persistencia;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.jp.modelos.Gastos;
import com.jp.modelos.Modelo;
import com.jp.modelos.TipoDeGastos;
import com.jp.modelos.Veiculo;

public interface IGastosDao {
    void incluir(Gastos objeto) throws Exception;
    void alterar(Gastos objeto) throws Exception;
    ArrayList<Gastos> listar(Veiculo objeto) throws Exception;
    HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto) throws Exception;

    HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto, int ano) throws Exception;

    Gastos buscar(String descricao) throws Exception;
    Gastos buscar(int id) throws Exception;

    Gastos buscar(TipoDeGastos tipoDeGastos)throws Exception;

    double[] valorTotalMes(Veiculo objeto, int ano) throws Exception;

    public HashMap<String, Double> valorTotalTipo(Veiculo objeto, int ano) throws Exception;

    ArrayList<Gastos> filtrarGastos(Collection<Gastos> gastos, String filtro)throws Exception;
}
