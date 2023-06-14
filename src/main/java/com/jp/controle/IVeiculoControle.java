package com.jp.controle;
import com.jp.modelos.Veiculo;
import java.util.ArrayList;
import java.util.Iterator;
import com.jp.modelos.Modelo;
//import com.jp.tools.Data;

public interface IVeiculoControle {
    void incluir(Veiculo objeto) throws Exception;

    void alterar(Veiculo objeto) throws Exception;

    ArrayList<Veiculo> listar() throws Exception;

    Veiculo buscar(int id) throws Exception;

    Veiculo buscar(String placa) throws Exception;

    ArrayList<Veiculo> listarNaoLocados(Data inicio, Categoria categoria) throws Exception;
}
