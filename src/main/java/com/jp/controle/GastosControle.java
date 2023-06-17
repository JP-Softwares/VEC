package com.jp.controle;

import com.jp.modelos.Gastos;
import com.jp.modelos.Proprietario;
import com.jp.modelos.TipoDeGastos;
import com.jp.modelos.Veiculo;
import com.jp.persistencia.GastosDao;
import com.jp.persistencia.IGastosDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;

public class GastosControle implements IGastosControle{

    public GastosControle(){
        try{
            this.GastosPersistencia = new GastosDao();
        }catch (Exception erro){

        }

    }
    IGastosDao GastosPersistencia = null;
    @Override
    public void incluir(Gastos objeto) throws Exception {
        verificarCampos(objeto);
        GastosPersistencia.incluir(objeto);
    }

    @Override
    public void alterar(Gastos objeto) throws Exception {
        GastosPersistencia.alterar(objeto);
    }

    @Override
    public ArrayList<Gastos> listar() throws Exception {
        try{
            return GastosPersistencia.listar();
        }catch(Exception erro){
            return new ArrayList<Gastos>();
        }
    }

    @Override
    public HashMap<Integer, ArrayList> listarPorMes() throws Exception {
        return GastosPersistencia.listarPorMes();
    }

    @Override
    public Gastos buscar(int id) throws Exception {
        return GastosPersistencia.buscar(id);
    }

    @Override
    public Gastos buscar(TipoDeGastos TipoDeGastos) throws Exception {
        return GastosPersistencia.buscar(TipoDeGastos);
    }

    private boolean verificarCampos(Gastos objeto) throws Exception{
        if(objeto.getDescricao().length() < 1) throw new Exception("A descrição informada é inválida");
        return true;
    }
}