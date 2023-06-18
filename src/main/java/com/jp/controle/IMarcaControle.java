package com.jp.controle;
import com.jp.modelos.Marca;
import java.util.ArrayList;
import java.util.Collection;

public interface IMarcaControle {
    void incluir(Marca objeto) throws Exception;

    void alterar(Marca objeto) throws Exception;


    ArrayList<Marca> listar() throws Exception;

    Marca buscar(String descricao) throws Exception;

    Marca buscar(int id) throws Exception;

    ArrayList<Marca> filtrarMarca(Collection marcas, String nome)throws Exception;
}
