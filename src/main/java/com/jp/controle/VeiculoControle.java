package com.jp.controle;

import com.jp.modelos.Veiculo;

import java.util.ArrayList;
import java.util.Iterator;

public class VeiculoControle implements IVeiculoControle{
    @Override
    public void incluir(Veiculo objeto) throws Exception {
        try {
            verificarCampos(objeto);
            if(buscarPlacaVeiculo(objeto)) throw new Exception("Placa ja cadastrada");
        } catch (Exception e) {
            throw e;
        }
        veiculoPersistencia.incluir(objeto);
    }

    @Override
    public void alterar(Veiculo objeto) throws Exception {
        try {
            verificarCampos(objeto);
            if(buscarPlacaVeiculo(objeto)) throw new Exception("Placa ja cadastrada");
        } catch (Exception e) {
            throw e;
        }
        veiculoPersistencia.alterar(objeto);
    }


    @Override
    public ArrayList<Veiculo> listar() throws Exception {
        try{
            return veiculoPersistencia.listar();
        }catch(Exception erro){
            return new ArrayList<Veiculo>();
        }
    }

    @Override
    public Veiculo buscar(int id) throws Exception {
        return veiculoPersistencia.buscar(id);
    }

    @Override
    public Veiculo buscar(String placa) throws Exception {
        return veiculoPersistencia.buscar(placa);
    }

    private boolean buscarPlacaVeiculo(Veiculo objeto) throws Exception{
        try {
            ArrayList<Veiculo> listagem = listar();
            Iterator<Veiculo> lista = listagem.iterator();
            while(lista.hasNext()){
                Veiculo aux = lista.next();
                if(aux.getPlaca().equalsIgnoreCase(objeto.getPlaca()) && aux.getId() != objeto.getId()){
                    return true;
                }
            }
            return false;
        } catch (Exception erro) {
            throw erro;
        }
    }

    private boolean verificarCampos(Veiculo objeto) throws Exception{
        if((objeto.getPlaca()).length() < 7) throw new Exception("A placa informada é invalida, por favor digite uma placa com 7 digitos");
        if(objeto.getKilometragem() < 0) throw new Exception("Por favor digite uma quilometragem válida");
        return true;
    }
}
