package com.jp.controle;

import com.jp.modelos.*;
import com.jp.persistencia.GastosDao;
import com.jp.persistencia.IGastosDao;

import java.util.ArrayList;
import java.util.Collection;
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
    public ArrayList<Gastos> listar(Veiculo objeto) throws Exception {
        try{
            return GastosPersistencia.listar(objeto);
        }catch(Exception erro){
            return new ArrayList<Gastos>();
        }
    }

    @Override
    public HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto) throws Exception {
        return GastosPersistencia.listarPorMes(objeto);
    }

    @Override
    public HashMap<Integer, ArrayList> listarPorMes(Veiculo objeto,int ano) throws Exception {
        return GastosPersistencia.listarPorMes(objeto, ano);
    }

    @Override
    public Gastos buscar(int id) throws Exception {
        return GastosPersistencia.buscar(id);
    }

    @Override
    public Gastos buscar(TipoDeGastos TipoDeGastos) throws Exception {
        return GastosPersistencia.buscar(TipoDeGastos);
    }

    @Override
    public double[] valorTotalMes(Veiculo objeto, int ano) throws Exception {
        return GastosPersistencia.valorTotalMes(objeto, ano);
    }

    @Override
    public HashMap<String, Double> valorTotalTipo(Veiculo objeto, int ano) throws Exception {
        return GastosPersistencia.valorTotalTipo(objeto,ano);
    }

    @Override
    public ArrayList<Gastos> filtrarGastos(Collection gastos, String filtro) throws Exception {
        return GastosPersistencia.filtrarGastos(gastos,filtro);
    }

    private boolean verificarCampos(Gastos objeto) throws Exception{
        if(objeto.getDescricao().length() < 1) throw new Exception("A descrição informada é inválida");
        return true;
    }
}
