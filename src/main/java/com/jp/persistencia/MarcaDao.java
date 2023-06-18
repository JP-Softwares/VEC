package com.jp.persistencia;

import com.jp.modelos.Marca;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import com.jp.tools.ConexaoBD;
import org.apache.commons.compress.compressors.zstandard.ZstdCompressorOutputStream;

public class MarcaDao implements IMarcaDao{

    private Connection conexao = null;

    public MarcaDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }
    @Override
    public void incluir(Marca objeto) throws Exception {
        try {
            String sql = "insert into marca(nome, url ) values (?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getUrl());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public void alterar(Marca objeto) throws Exception {
        try {
            String sql = "update Marca set nome = ?, url = ?  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getUrl());
            preparedStatement.setInt(3, objeto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<Marca> listar() throws Exception {
        ArrayList<Marca> listaMarcas = new ArrayList<Marca>();
        String sql = "select * from Marca";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Marca marca = new Marca();
                marca.setId(rs.getInt("id"));
                marca.setNome(rs.getString("nome"));
                marca.setUrl(rs.getString("url"));
                listaMarcas.add(marca);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaMarcas;
    }

    @Override
    public Marca buscar(String nomeDaMarca) throws Exception {
        try {
            String sql = "select * from Marca where nome = '"+nomeDaMarca+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Marca marca = new Marca();
            if(rs.next()){
                marca.setId(rs.getInt("id"));
                marca.setNome(rs.getString("nome"));
                marca.setUrl(rs.getString("url"));
            }
            return marca;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Marca buscar(int id) throws Exception {
        try {
            String sql = "select * from Marca where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Marca marca = new Marca();
            if(rs.next()){
                marca.setId(rs.getInt("id"));
                marca.setNome(rs.getString("nome"));
                marca.setUrl(rs.getString("url"));
            }
            return marca;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Marca> filtrarMarcas(Collection<Marca> marcas, String nome) throws Exception {
        ArrayList<Marca> ar = new ArrayList<>();
        marcas.forEach(marca -> {
            if(marca.getNome().toLowerCase().contains(nome.toLowerCase())) ar.add(marca);
        });
        return ar;
    }
}
