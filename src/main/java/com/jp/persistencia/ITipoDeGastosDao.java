package com.jp.persistencia;
import java.util.ArrayList;
import java.util.Collection;

import com.jp.modelos.TipoDeGastos;

public interface ITipoDeGastosDao {
    void incluir(TipoDeGastos objeto) throws Exception;
    void alterar(TipoDeGastos objeto) throws Exception;
    ArrayList<TipoDeGastos> listar() throws Exception;
    TipoDeGastos buscar(String nome) throws Exception;
    TipoDeGastos buscar(int id) throws Exception;

    ArrayList<TipoDeGastos> filtrarTipoDeGastos(Collection<TipoDeGastos> tipoDeGastos , String filtro)throws Exception;
}
