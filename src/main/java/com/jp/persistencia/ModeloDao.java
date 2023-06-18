package com.jp.persistencia;

import com.jp.controle.IMarcaControle;
import com.jp.controle.IModeloControle;
import com.jp.modelos.Marca;
import com.jp.modelos.Modelo;
import com.jp.modelos.TipoDoVeiculo;
import com.jp.tools.ConexaoBD;
import com.jp.controle.MarcaControle;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ModeloDao  implements IModeloDao {

    private Connection conexao = null;

    public ModeloDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }
    @Override
    public void incluir(Modelo objeto) throws Exception {
        try {
            String sql = "insert into Modelo(nome, url, tipoDoVeiculo, idMarca) values (?, ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getUrlModelo());
            preparedStatement.setString(3, objeto.getTipo().toString());
            preparedStatement.setInt(4, objeto.getMarca().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public void alterar(Modelo objeto) throws Exception {
        try {
            String sql = "update Modelo set nome = ?, url = ?, tipoDoVeiculo = ?, idMarca = ?  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getUrlModelo());
            preparedStatement.setString(3, objeto.getTipo().toString());
            preparedStatement.setInt(4, objeto.getMarca().getId());
            preparedStatement.setInt(5, objeto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<Modelo> listar() throws Exception {
        ArrayList<Modelo> listaModelos = new ArrayList<Modelo>();
        String sql = "select * from Modelo";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Modelo modelo = new Modelo();
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setUrlModelo(rs.getString("url"));
                modelo.setTipo(TipoDoVeiculo.valueOf((rs.getString("tipoDoVeiculo"))));
                IMarcaControle marcaControle = new MarcaControle();
                modelo.setMarca(marcaControle.buscar(rs.getInt("idMarca")));
                listaModelos.add(modelo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaModelos;
    }

    @Override
    public Modelo buscar(String descricao) throws Exception {
        try {
            String sql = "select * from Modelo where nome = '"+descricao+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Modelo modelo = new Modelo();
            if(rs.next()) {
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setUrlModelo(rs.getString("url"));
                modelo.setTipo(TipoDoVeiculo.valueOf((rs.getString("tipoDoVeiculo"))));
                IMarcaControle marcaControle = new MarcaControle();
                modelo.setMarca(marcaControle.buscar(rs.getInt("idMarca")));
            }
            return modelo;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Modelo buscar(int id) throws Exception {
        try {
            String sql = "select * from Modelo where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Modelo modelo = new Modelo();
            if(rs.next()) {
                modelo.setId(rs.getInt("id"));
                modelo.setNome(rs.getString("nome"));
                modelo.setUrlModelo(rs.getString("url"));
                modelo.setTipo(TipoDoVeiculo.valueOf((rs.getString("tipoDoVeiculo"))));
                IMarcaControle marcaControle = new MarcaControle();
                modelo.setMarca(marcaControle.buscar(rs.getInt("idMarca")));
            }
            return modelo;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Modelo> filtrarModelo(Collection<Modelo> modelo, String nome) throws Exception {
        ArrayList<Modelo> m = new ArrayList<>();
        modelo.forEach(modelos -> {
            if(modelos.getNome().toLowerCase().contains(nome.toLowerCase())) m.add(modelos);
        });
        return m;
    }
}
