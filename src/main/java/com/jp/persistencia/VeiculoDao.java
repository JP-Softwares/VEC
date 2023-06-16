package com.jp.persistencia;

import com.jp.modelos.Marca;
import com.jp.modelos.Veiculo;
import com.jp.tools.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;

public class VeiculoDao implements IVeiculoDao{

    private Connection conexao = null;

    public VeiculoDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }

    @Override
    public void incluir(Veiculo objeto) throws Exception {
        try {
            String sql = "insert into Veiculo(placa, anoFabricacao, anoModelo, tipoDoCombustivel, quilometragemAtual, situacaoDoVeiculo, idModelo, idProprietario) values (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getPlaca());
            preparedStatement.setString(2, objeto.getAnoFabricacao() + "");
            preparedStatement.setString(3, objeto.getAnoModelo() + "");
            preparedStatement.setString(4, objeto.getCombustivel().toString());
            preparedStatement.setString(5, objeto.getKilometragem() + "");
            preparedStatement.setString(6, objeto.getSituacao().toString());
            preparedStatement.setInt(7, objeto.getModelo().getId());
            preparedStatement.setInt(8, objeto.getProprietario().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public void alterar(Veiculo objeto) throws Exception {
        try {
            String sql = "update Veiculo set placa = ?, anoFabricacao = ?, anoModelo = ?, tipoDoCombustivel = ?, quilometragemAtual = ?, situacaoDoVeiculo = ?, idModelo = ?, idProprietario = ?  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getPlaca());
            preparedStatement.setString(2, objeto.getAnoFabricacao() + "");
            preparedStatement.setString(3, objeto.getAnoModelo() + "");
            preparedStatement.setString(4, objeto.getCombustivel().toString());
            preparedStatement.setString(5, objeto.getKilometragem() + "");
            preparedStatement.setString(6, objeto.getSituacao().toString());
            preparedStatement.setInt(7, objeto.getModelo().getId());
            preparedStatement.setInt(8, objeto.getProprietario().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<Veiculo> listar() throws Exception {
        ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        String sql = "select * from Veiculo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.getAnoFabricacao(Integer.parseInt(rs.getString("anoFabricacao")));
                marca.setUrl(rs.getString("url"));
                listaMarcas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMarcas;
    }

    @Override
    public Veiculo buscar(String placa) throws Exception {
        return null;
    }

    @Override
    public Veiculo buscar(int id) throws Exception {
        return null;
    }

    @Override
    public Veiculo buscar(int id, boolean modelo) throws Exception {
        return null;
    }
}
