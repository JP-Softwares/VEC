package com.jp.persistencia;

import com.jp.controle.IMarcaControle;
import com.jp.controle.MarcaControle;
import com.jp.controle.ModeloControle;
import com.jp.controle.ProprietarioControle;
import com.jp.modelos.*;
import com.jp.tools.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class VeiculoDao implements IVeiculoDao{

    private Connection conexao = null;

    public VeiculoDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }

    ModeloControle modeloControle = new ModeloControle();
    ProprietarioControle proprietarioControle = new ProprietarioControle();

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
            preparedStatement.setInt(5, objeto.getKilometragem());
            preparedStatement.setString(6, objeto.getSituacao().toString());
            preparedStatement.setInt(7, objeto.getModelo().getId());
            preparedStatement.setInt(8, objeto.getProprietario().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }
        objeto.getProprietario().setNumeroDeCarros(objeto.getProprietario().getNumeroDeCarros()+1);
        proprietarioControle.alterar(objeto.getProprietario());
    }

    @Override
    public void alterar(Veiculo objeto) throws Exception {
        try {
            System.out.println(objeto.getId()+ "");
            String sql = "update Veiculo set  placa = '"+objeto.getPlaca()+"', anoFabricacao = '"+objeto.getAnoFabricacao()+"', anoModelo = '"+objeto.getAnoModelo()+"', tipoDoCombustivel = '"+objeto.getCombustivel().toString()+"', quilometragemAtual = '"+objeto.getKilometragem()+"', situacaoDoVeiculo = '" +objeto.getSituacao().toString() +"', idModelo = '"+objeto.getModelo().getId()+"', idProprietario = '"+objeto.getProprietario().getId()+"' where id = '"+objeto.getId()+"'";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
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
                veiculo.setAnoFabricacao(Integer.parseInt(rs.getString("anoFabricacao")));
                veiculo.setAnoModelo(Integer.parseInt(rs.getString("anoModelo")));
                veiculo.setKilometragem(rs.getInt("quilometragemAtual"));
                veiculo.setCombustivel(TipoDoCombustivel.valueOf(rs.getString("tipoDoCombustivel")));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(modeloControle.buscar(rs.getInt("idModelo")));
                veiculo.setSituacao(SituacaoDoVeiculo.valueOf(rs.getString("situacaoDoVeiculo")));
                veiculo.setProprietario(proprietarioControle.buscar(rs.getInt("idProprietario")));
                listaVeiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaVeiculos;
    }

    @Override
    public Veiculo buscar(String placa) throws Exception {
        try {
            String sql = "select * from Veiculo where placa = '"+placa+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Veiculo veiculo = new Veiculo();
            if(rs.next()) {
                veiculo.setId(rs.getInt("id"));
                veiculo.setAnoFabricacao(Integer.parseInt(rs.getString("anoFabricacao")));
                veiculo.setAnoModelo(Integer.parseInt(rs.getString("anoModelo")));
                veiculo.setKilometragem(rs.getInt("quilometragemAtual"));
                veiculo.setCombustivel(TipoDoCombustivel.valueOf(rs.getString("tipoDoCombustivel")));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(modeloControle.buscar(rs.getInt("idModelo")));
                veiculo.setSituacao(SituacaoDoVeiculo.valueOf(rs.getString("situacaoDoVeiculo")));
                veiculo.setProprietario(proprietarioControle.buscar(rs.getString("idProprietario")));
            }
            return veiculo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Veiculo buscar(int id) throws Exception {
        try {
            String sql = "select * from Veiculo where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Veiculo veiculo = new Veiculo();
            if(rs.next()) {
                veiculo.setId(rs.getInt("id"));
                veiculo.setAnoFabricacao(Integer.parseInt(rs.getString("anoFabricacao")));
                veiculo.setAnoModelo(Integer.parseInt(rs.getString("anoModelo")));
                veiculo.setKilometragem(rs.getInt("quilometragemAtual"));
                veiculo.setCombustivel(TipoDoCombustivel.valueOf(rs.getString("tipoDoCombustivel")));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(modeloControle.buscar(rs.getInt("idModelo")));
                veiculo.setSituacao(SituacaoDoVeiculo.valueOf(rs.getString("situacaoDoVeiculo")));
                veiculo.setProprietario(proprietarioControle.buscar(rs.getString("idProprietario")));
            }
            return veiculo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Veiculo buscar(int id, boolean modelo) throws Exception {
        try {
            String sql = "select * from Veiculo where idModelo = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Veiculo veiculo = new Veiculo();
            if(rs.next()) {
                veiculo.setId(rs.getInt("id"));
                veiculo.setAnoFabricacao(Integer.parseInt(rs.getString("anoFabricacao")));
                veiculo.setAnoModelo(Integer.parseInt(rs.getString("url")));
                veiculo.setKilometragem(rs.getInt("quilometragemAtual"));
                veiculo.setCombustivel(TipoDoCombustivel.valueOf(rs.getString("tipoDoCombustivel")));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(modeloControle.buscar(rs.getInt("idModelo")));
                veiculo.setSituacao(SituacaoDoVeiculo.valueOf(rs.getString("situacaoDoVeiculo")));
                veiculo.setProprietario(proprietarioControle.buscar(rs.getString("idProprietario")));
            }
            return veiculo;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Veiculo> filtrarVeiculo(Collection<Veiculo> veiculo, String filtro) throws Exception {
        ArrayList<Veiculo> VA = new ArrayList<>();
        veiculo.forEach(v -> {
            if(v.getModelo().getNome().toLowerCase().contains(filtro.toLowerCase()) || v.getPlaca().toLowerCase().contains(filtro.toLowerCase()) || v.getModelo().getMarca().getNome().toLowerCase().contains(filtro.toLowerCase()) || v.getProprietario().getNome().toLowerCase().contains(filtro.toLowerCase())) VA.add(v);
        });
        return VA;
    }
}
