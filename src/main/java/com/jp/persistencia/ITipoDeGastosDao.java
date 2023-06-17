package com.jp.persistencia;
import java.util.ArrayList;
import com.jp.modelos.TipoDeGastos;

public interface ITipoDeGastosDao {
    void incluir(TipoDeGastos objeto) throws Exception;
    void alterar(TipoDeGastos objeto) throws Exception;
    ArrayList<TipoDeGastos> listar() throws Exception;
    TipoDeGastos buscar(String nome) throws Exception;
    TipoDeGastos buscar(int id) throws Exception;
}
