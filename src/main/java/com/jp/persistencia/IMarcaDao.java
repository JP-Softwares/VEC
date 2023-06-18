package com.jp.persistencia;
import java.util.ArrayList;
import java.util.Collection;

import com.jp.modelos.Marca;

public interface IMarcaDao {

    void incluir(Marca objeto) throws Exception;
    void alterar(Marca objeto) throws Exception;
    ArrayList<Marca> listar() throws Exception;
    Marca buscar(String nome) throws Exception;
    Marca buscar(int id) throws Exception;
    ArrayList<Marca> filtrarMarcas(Collection<Marca> marcas, String nome) throws Exception;
}
