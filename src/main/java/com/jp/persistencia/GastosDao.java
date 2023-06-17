package com.jp.persistencia;

import com.jp.controle.*;
import com.jp.modelos.Gastos;
import com.jp.modelos.Modelo;
import com.jp.modelos.TipoDeGastos;
import com.jp.modelos.TipoDoVeiculo;
import com.jp.tools.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GastosDao implements IGastosDao{

    private Connection conexao = null;

    public GastosDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }
    @Override
    public void incluir(Gastos objeto) throws Exception {
        try {
            String sql = "insert into Gastos(descricao, valor, data, idVeiculo, idTipoDeGasto) values (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.setFloat(2, Float.parseFloat(objeto.getValor() + ""));
            preparedStatement.setDate(3, objeto.getData());
            preparedStatement.setInt(4, objeto.getVeiculo().getId());
            preparedStatement.setInt(5, objeto.getTipoDeGastos().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }
    }

    @Override
    public void alterar(Gastos objeto) throws Exception {
        try {
            String sql = "update Gastos set descricao = ?, valor = ?, data = ?, idVeiculo = ?, idTipoDeGasto  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getDescricao());
            preparedStatement.setFloat(2, Float.parseFloat(objeto.getValor() + ""));
            preparedStatement.setDate(3, objeto.getData());
            preparedStatement.setInt(4, objeto.getVeiculo().getId());
            preparedStatement.setInt(5, objeto.getTipoDeGastos().getId());
            preparedStatement.setInt(6, objeto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<Gastos> listar() throws Exception {
        ArrayList<Gastos> listaGastos = new ArrayList<Gastos>();
        String sql = "select * from Gastos";
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Gastos gastos = new Gastos();
                gastos.setId(rs.getInt("id"));
                gastos.setDescricao(rs.getString("descricao"));
                gastos.setValor(rs.getDouble("valor"));
                gastos.setData(rs.getDate("data"));
                gastos.setVeiculo(veiculoControle.buscar(rs.getInt("idVeiculo")));
                gastos.setTipoDeGastos(tipoDeGastosControle.buscar(rs.getInt("idTipoDeGasto")));
                listaGastos.add(gastos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaGastos;
    }

    public HashMap<Integer, ArrayList> listarPorMes() throws Exception {
        GastosControle gastosControle = new GastosControle();
        HashMap<Integer, ArrayList> hm = new HashMap<Integer, ArrayList>();
        Iterator<Gastos> lista = gastosControle.listar().iterator();
        /*ArrayList<Gastos> gastosJaneiro = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosFevereiro = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosMarco = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosAbril = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosMaio = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosJunho = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosJulho = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosAgosto = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosSetembro = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosOutubro = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosNovembro = new ArrayList<Gastos>();
        ArrayList<Gastos> gastosDezembro = new ArrayList<Gastos>();*/
        while(lista.hasNext()){
            ArrayList<Gastos> gastos = new ArrayList<Gastos>();
            Gastos aux = lista.next();
            switch (aux.getData().getMonth()){
                case 0:
                    if(hm.get(1)== null){
                        gastos.add(aux);
                        hm.put(1, gastos);
                    }else {
                        gastos = hm.get(1);
                        gastos.add(aux);
                        hm.put(1, gastos);
                    }
                    break;
                case 1:
                    if(hm.get(2)== null){
                        gastos.add(aux);
                        hm.put(2, gastos);
                    }else {
                        gastos = hm.get(2);
                        gastos.add(aux);
                        hm.put(2, gastos);
                    }
                    break;
                case 2:
                    if(hm.get(3)== null){
                        gastos.add(aux);
                        hm.put(3, gastos);
                    }else {
                        gastos = hm.get(3);
                        gastos.add(aux);
                        hm.put(3, gastos);
                    }
                    break;
                case 3:
                    if(hm.get(4)== null){
                        gastos.add(aux);
                        hm.put(4, gastos);
                    }else {
                        gastos = hm.get(4);
                        gastos.add(aux);
                        hm.put(4, gastos);
                    }
                    break;
                case 4:
                    if(hm.get(5) == null){
                        gastos.add(aux);
                        hm.put(5, gastos);
                    }else {
                        gastos = hm.get(5);
                        gastos.add(aux);
                        hm.put(5, gastos);
                    }
                    break;
                case 5:
                    if(hm.get(6) == null){
                        gastos.add(aux);
                        hm.put(6, gastos);
                    }else {
                        gastos = hm.get(6);
                        gastos.add(aux);
                        hm.put(6, gastos);
                    }
                    break;
                case 6:
                    if(hm.get(7)== null){
                        gastos.add(aux);
                        hm.put(7, gastos);
                    }else {
                        gastos = hm.get(7);
                        gastos.add(aux);
                        hm.put(7, gastos);
                    }
                    break;
                case 7:
                    if(hm.get(8)== null){
                        gastos.add(aux);
                        hm.put(8, gastos);
                    }else {
                        gastos = hm.get(8);
                        gastos.add(aux);
                        hm.put(8, gastos);
                    }
                    break;
                case 8:
                    if(hm.get(9)== null){
                        gastos.add(aux);
                        hm.put(9, gastos);
                    }else {
                        gastos = hm.get(9);
                        gastos.add(aux);
                        hm.put(9, gastos);
                    }
                    break;
                case 9:
                    if(hm.get(10)== null){
                        gastos.add(aux);
                        hm.put(10, gastos);
                    }else {
                        gastos = hm.get(10);
                        gastos.add(aux);
                        hm.put(10, gastos);
                    }
                    break;
                case 10:
                    if(hm.get(11)== null){
                        gastos.add(aux);
                        hm.put(11, gastos);
                    }else {
                        gastos = hm.get(11);
                        gastos.add(aux);
                        hm.put(11, gastos);
                    }
                    break;
                case 11:
                    if(hm.get(12)== null){
                        gastos.add(aux);
                        hm.put(12, gastos);
                    }else {
                        gastos = hm.get(12);
                        gastos.add(aux);
                        hm.put(12, gastos);
                    }
                    break;
                default:
                    break;
            }
        }
        return sort(hm);
    }

    @Override
    public Gastos buscar(String descricao) throws Exception {
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        try {
            String sql = "select * from Gastos where descricao = '"+descricao+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Gastos gastos = new Gastos();
            if(rs.next()) {
                gastos.setId(rs.getInt("id"));
                gastos.setDescricao(rs.getString("descricao"));
                gastos.setValor(rs.getDouble("valor"));
                gastos.setData(rs.getDate("data"));
                gastos.setVeiculo(veiculoControle.buscar(rs.getInt("idVeiculo")));
                gastos.setTipoDeGastos(tipoDeGastosControle.buscar(rs.getInt("idTipoDeGasto")));
            }
            return gastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Gastos buscar(int id) throws Exception {
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        try {
            String sql = "select * from Gastos where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Gastos gastos = new Gastos();
            if(rs.next()) {
                gastos.setId(rs.getInt("id"));
                gastos.setDescricao(rs.getString("descricao"));
                gastos.setValor(rs.getDouble("valor"));
                gastos.setData(rs.getDate("data"));
                gastos.setVeiculo(veiculoControle.buscar(rs.getInt("idVeiculo")));
                gastos.setTipoDeGastos(tipoDeGastosControle.buscar(rs.getInt("idTipoDeGasto")));
            }
            return gastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Gastos buscar(TipoDeGastos tipoDeGastos) throws Exception {
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        String tipo = String.valueOf(tipoDeGastos.getId());
        try {
            String sql = "select * from Gastos where idTipoDeGasto = '"+tipo+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Gastos gastos = new Gastos();
            gastos.setId(rs.getInt("id"));
            gastos.setDescricao(rs.getString("descricao"));
            gastos.setValor(rs.getDouble("valor"));
            gastos.setData(rs.getDate("data"));
            gastos.setVeiculo(veiculoControle.buscar(rs.getInt("idVeiculo")));
            gastos.setTipoDeGastos(tipoDeGastosControle.buscar(rs.getInt("idTipoDeGasto")));
            return gastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private HashMap<Integer, ArrayList> sort(HashMap<Integer, ArrayList> hm){
        int key = 1;
        ArrayList<Gastos> organizar = new ArrayList<>();
        while (key < 13){
            if(hm.get(key) != null){
                organizar = hm.get(key);
                organizar.sort((o1, o2) -> {
                    return Integer.compare(o1.getData().toLocalDate().getDayOfMonth()-1, o2.getData().toLocalDate().getDayOfMonth()-1);
                });
                hm.put(key, organizar);
            }
            key++;
        }
        return hm;
    }
}