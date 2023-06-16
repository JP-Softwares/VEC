package com.jp.persistencia;
import java.util.ArrayList;
import com.jp.modelos.Marca;
import com.jp.modelos.Modelo;

public interface iModeloDao {
    void incluir(Modelo objeto) throws Exception;
    void alterar(Modelo objeto) throws Exception;
    ArrayList<Modelo> listar() throws Exception;
    Modelo buscar(String nome) throws Exception;
    Modelo buscar(int id) throws Exception;
}
