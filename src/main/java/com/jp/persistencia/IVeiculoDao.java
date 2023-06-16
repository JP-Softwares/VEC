package com.jp.persistencia;
import java.util.ArrayList;

import com.jp.modelos.Modelo;
import com.jp.modelos.Veiculo;
public interface IVeiculoDao {
    void incluir(Veiculo objeto) throws Exception;
    void alterar(Veiculo objeto) throws Exception;
    ArrayList<Veiculo> listar() throws Exception;
    Veiculo buscar(String placa) throws Exception;
    Veiculo buscar(int id) throws Exception;
    Veiculo buscar(int id, boolean modelo) throws Exception;
}
