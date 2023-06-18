package com.jp.controle;
import com.jp.modelos.Modelo;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.jp.modelos.Marca;

public interface IModeloControle {
    void incluir(Modelo objeto) throws Exception;

    void alterar(Modelo objeto) throws Exception;

    ArrayList<Modelo> listar() throws Exception;

    Modelo buscar(String descricao) throws Exception;

    Modelo buscar(int id) throws Exception;

    ArrayList<Modelo> filtrarModelo(Collection modelo, String nome)throws Exception;
}
