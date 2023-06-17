package com.jp.persistencia;
import java.util.ArrayList;
import java.util.HashMap;

import com.jp.modelos.Gastos;
import com.jp.modelos.TipoDeGastos;

public interface IGastosDao {
    void incluir(Gastos objeto) throws Exception;
    void alterar(Gastos objeto) throws Exception;
    ArrayList<Gastos> listar() throws Exception;
    HashMap<Integer, ArrayList> listarPorMes() throws Exception;
    Gastos buscar(String descricao) throws Exception;
    Gastos buscar(int id) throws Exception;

    Gastos buscar(TipoDeGastos tipoDeGastos)throws Exception;
}
