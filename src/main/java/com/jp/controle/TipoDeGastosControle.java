package com.jp.controle;

import com.jp.modelos.Gastos;
import com.jp.modelos.TipoDeGastos;

import java.util.ArrayList;

public class TipoDeGastosControle implements ITipoDeGastosControle {


    public TipoDeGastosControle() {
        this.TipoDeGastosPersistencia = new TipoDeGastosDao();
    }

    ITipoDeGastosDao TipoDeGastosPersistencia = null;

    @Override
    public void incluir(TipoDeGastos objeto) throws Exception {
        verificarCampos(objeto);
        TipoDeGastosPersistencia.incluir(objeto);

    }

    @Override
    public void alterar(TipoDeGastos objeto) throws Exception {
        TipoDeGastosPersistencia.alterar(objeto);

    }

    @Override
    public ArrayList<TipoDeGastos> listar() throws Exception {
        try{
            return TipoDeGastosPersistencia.listar();
        }catch(Exception erro){
            return new ArrayList<TipoDeGastos>();
        }
    }

    @Override
    public TipoDeGastos buscar(String nome) throws Exception {
        return TipoDeGastosPersistencia.buscar(nome);
    }

    @Override
    public TipoDeGastos buscar(int id) throws Exception {
        return TipoDeGastosPersistencia.buscar(id);
    }

    private boolean verificarCampos(TipoDeGastos objeto) throws Exception {
        if (objeto.getNome().length() < 1) throw new Exception("A descrição informada é inválida");
        return true;
    }
}
