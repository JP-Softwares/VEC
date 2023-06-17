package com.jp.persistencia;

import com.jp.controle.IMarcaControle;
import com.jp.controle.MarcaControle;
import com.jp.controle.TipoDeGastosControle;
import com.jp.controle.VeiculoControle;
import com.jp.modelos.Gastos;
import com.jp.modelos.Modelo;
import com.jp.modelos.TipoDeGastos;
import com.jp.modelos.TipoDoVeiculo;
import com.jp.tools.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;

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
            preparedStatement.setDate(3, Date.valueOf(objeto.getData().toString()));
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
            preparedStatement.setDate(3, Date.valueOf(objeto.getData().toString()));
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

    @Override
    public Gastos buscar(String descricao) throws Exception {
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        try {
            String sql = "select * from Gastos where descricao = '"+descricao+"'";
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

    @Override
    public Gastos buscar(int id) throws Exception {
        VeiculoControle veiculoControle = new VeiculoControle();
        TipoDeGastosControle tipoDeGastosControle = new TipoDeGastosControle();
        try {
            String sql = "select * from Gastos where id = '"+id+"'";
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
}
