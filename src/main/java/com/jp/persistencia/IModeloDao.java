package com.jp.persistencia;
import java.util.ArrayList;
import java.util.Collection;

import com.jp.modelos.Modelo;

public interface IModeloDao {
    void incluir(Modelo objeto) throws Exception;
    void alterar(Modelo objeto) throws Exception;
    ArrayList<Modelo> listar() throws Exception;
    Modelo buscar(String nome) throws Exception;
    Modelo buscar(int id) throws Exception;
    ArrayList<Modelo> filtrarModelo(Collection<Modelo> Modelo, String nome)throws Exception;
}
