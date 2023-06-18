package com.jp.controle;

import com.jp.modelos.Proprietario;
import com.jp.modelos.Veiculo;
import com.jp.persistencia.IVeiculoDao;
import com.jp.persistencia.VeiculoDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class VeiculoControle implements IVeiculoControle{

    IVeiculoDao veiculoPersistencia = null;


    public VeiculoControle() throws Exception {
        this.veiculoPersistencia = new VeiculoDao();
    }
    @Override
    public void incluir(Veiculo objeto) throws Exception {
        verificarCampos(objeto);
        veiculoPersistencia.incluir(objeto);
    }

    @Override
    public void alterar(Veiculo objeto) throws Exception {
        verificarCampos(objeto);
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

    @Override
    public ArrayList<Veiculo> filtrarVeiculo(Collection<Veiculo> veiculo, String filtro) throws Exception {
        return veiculoPersistencia.filtrarVeiculo(veiculo, filtro);
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
        if((objeto.getPlaca()).length() < 7 || objeto.getPlaca().length() > 7) throw new Exception("A placa informada é invalida, por favor digite uma placa com 7 digitos");
        if(objeto.getKilometragem() < 0) throw new Exception("Por favor digite uma quilometragem válida");
        return true;
    }
}
