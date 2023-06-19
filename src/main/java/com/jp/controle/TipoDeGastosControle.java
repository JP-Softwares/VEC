package com.jp.controle;

import com.jp.modelos.Gastos;
import com.jp.modelos.TipoDeGastos;
import com.jp.persistencia.ITipoDeGastosDao;
import com.jp.persistencia.TipoDeGastosDao;

import java.util.ArrayList;
import java.util.Collection;

public class TipoDeGastosControle implements ITipoDeGastosControle {


    public TipoDeGastosControle() {
        try {
            this.TipoDeGastosPersistencia = new TipoDeGastosDao();
        }catch (Exception erro){

        }

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

    @Override
    public ArrayList<TipoDeGastos> filtrarTipoDeGastos(Collection<TipoDeGastos> tipoDeGastos, String filtro) throws Exception {
        return TipoDeGastosPersistencia.filtrarTipoDeGastos(tipoDeGastos,filtro);
    }

    private boolean verificarCampos(TipoDeGastos objeto) throws Exception {
        if (objeto.getNome().length() < 1) throw new Exception("O nome informado é inválido");
        return true;
    }
}
