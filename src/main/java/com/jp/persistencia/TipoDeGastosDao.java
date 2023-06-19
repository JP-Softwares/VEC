package com.jp.persistencia;

import com.jp.modelos.Modelo;
import com.jp.modelos.Proprietario;
import com.jp.modelos.TipoDeGastos;
import com.jp.tools.ConexaoBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class TipoDeGastosDao implements ITipoDeGastosDao{

    private Connection conexao = null;

    public TipoDeGastosDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }

    @Override
    public void incluir(TipoDeGastos objeto) throws Exception {
        try {
            String sql = "insert into TipoDeGasto(nome, url) values (?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getIcone() + "");
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public void alterar(TipoDeGastos objeto) throws Exception {
        try {
            String sql = "update TipoDeGasto set nome = ?, url = ?  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getNome());
            preparedStatement.setString(2, objeto.getIcone() + "");
            preparedStatement.setInt(3, objeto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<TipoDeGastos> listar() throws Exception {
        ArrayList<TipoDeGastos> listaTipoDeGastos = new ArrayList<TipoDeGastos>();
        String sql = "select * from TipoDeGasto";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                TipoDeGastos tipoDeGastos = new TipoDeGastos();
                tipoDeGastos.setId(rs.getInt("id"));
                tipoDeGastos.setNome(rs.getString("nome"));
                tipoDeGastos.setIcone(rs.getString("url"));
                listaTipoDeGastos.add(tipoDeGastos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaTipoDeGastos;
    }

    @Override
    public TipoDeGastos buscar(String nome) throws Exception {
        try {
            String sql = "select * from TipoDeGasto where nome = '"+nome+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            TipoDeGastos tipoDeGastos = new TipoDeGastos();
            if(rs.next()) {
                tipoDeGastos.setId(rs.getInt("id"));
                tipoDeGastos.setNome(rs.getString("nome"));
                tipoDeGastos.setIcone(rs.getString("url"));
            }
            return tipoDeGastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public TipoDeGastos buscar(int id) throws Exception {
        try {
            String sql = "select * from TipoDeGasto where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            TipoDeGastos tipoDeGastos = new TipoDeGastos();
            if(rs.next()) {
                tipoDeGastos.setId(rs.getInt("id"));
                tipoDeGastos.setNome(rs.getString("nome"));
                tipoDeGastos.setIcone(rs.getString("url"));
            }
            return tipoDeGastos;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<TipoDeGastos> filtrarTipoDeGastos(Collection<TipoDeGastos> tipoDeGastos, String filtro) throws Exception {
        ArrayList<TipoDeGastos> TA = new ArrayList<>();
        tipoDeGastos.forEach(t -> {
            if(t.getNome().toLowerCase().contains(filtro.toLowerCase())) TA.add(t);
        });
        return TA;
    }
}
