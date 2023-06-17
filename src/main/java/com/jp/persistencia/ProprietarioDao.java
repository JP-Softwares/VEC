package com.jp.persistencia;

import com.jp.controle.TipoDeGastosControle;
import com.jp.controle.VeiculoControle;
import com.jp.modelos.CategoriaCNH;
import com.jp.modelos.Gastos;
import com.jp.modelos.Proprietario;
import com.jp.modelos.Telefone;
import com.jp.tools.ConexaoBD;

import java.sql.*;
import java.util.ArrayList;

public class ProprietarioDao implements IProprietarioDao{

    private Connection conexao = null;

    public ProprietarioDao() throws Exception{
        conexao = ConexaoBD.getConexao();
    }
    @Override
    public void incluir(Proprietario objeto) throws Exception {
        try {
            String sql = "insert into Proprietario(CPF, nome, telefone_DDI, telefone_DDD, telefone_numero, email, numeroDaCNH, categoriaDaCNH) values (?, ?, ? , ? , ? , ?, ?, ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getCPF());
            preparedStatement.setString(2, objeto.getNome());
            preparedStatement.setString(3, objeto.getTelefone().getDDI() + "");
            preparedStatement.setString(4, objeto.getTelefone().getDDD() + "");
            preparedStatement.setString(5, objeto.getTelefone().getNumero() + "");
            preparedStatement.setString(6, objeto.getEmail());
            preparedStatement.setString(7, objeto.getCNH());
            preparedStatement.setString(8, objeto.getCategoria().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public void alterar(Proprietario objeto) throws Exception {
        try {
            String sql = "update Proprietario set CPF = ?, nome = ?, telefone_DDI = ?, telefone_DDD = ?, telefone_numero = ?, email = ?, numeroDaCNH = ?, categoriaDaCNH = ?  where (id = ?)";
            PreparedStatement preparedStatement = conexao.prepareStatement(sql);
            // Parameters iniciar os elementos
            preparedStatement.setString(1, objeto.getCPF());
            preparedStatement.setString(2, objeto.getNome());
            preparedStatement.setString(3, objeto.getTelefone().getDDI() + "");
            preparedStatement.setString(4, objeto.getTelefone().getDDD() + "");
            preparedStatement.setString(5, objeto.getTelefone().getNumero() + "");
            preparedStatement.setString(6, objeto.getEmail());
            preparedStatement.setString(7, objeto.getCNH());
            preparedStatement.setString(8, objeto.getCategoria().toString());
            preparedStatement.setString(9, objeto.getId() + "");
            preparedStatement.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("SQL Erro: "+ erro.getMessage());
        } catch(Exception erro){
            throw erro;
        }

    }

    @Override
    public ArrayList<Proprietario> listar() throws Exception {
        ArrayList<Proprietario> listaProprietario = new ArrayList<Proprietario>();
        String sql = "select * from Proprietario";
        try {
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Proprietario proprietario = new Proprietario();
                proprietario.setId(rs.getInt("id"));
                proprietario.setNome(rs.getString("nome"));
                proprietario.setCPF(rs.getString("CPF"));
                Telefone telefone = new Telefone();
                telefone.setDDI(rs.getInt("telefone_DDI"));
                telefone.setDDD(rs.getInt("telefone_DDD"));
                telefone.setNumero(rs.getInt("telefone_numero"));
                proprietario.setTelefone(telefone);
                proprietario.setEmail(rs.getString("email"));
                proprietario.setCNH(rs.getString("numeroDaCNH"));
                proprietario.setCategoria(CategoriaCNH.valueOf(rs.getString("categoriaDaCNH")));
                listaProprietario.add(proprietario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaProprietario;
    }

    @Override
    public Proprietario buscar(String cpf) throws Exception {
        try {
            String sql = "select * from Proprietario where CPF = '"+cpf+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Proprietario proprietario = new Proprietario();
            if(rs.next()) {
                proprietario.setId(rs.getInt("id"));
                proprietario.setNome(rs.getString("nome"));
                proprietario.setCPF(rs.getString("CPF"));
                Telefone telefone = new Telefone();
                telefone.setDDI(rs.getInt("telefone_DDI"));
                telefone.setDDD(rs.getInt("telefone_DDD"));
                telefone.setNumero(rs.getInt("telefone_numero"));
                proprietario.setTelefone(telefone);
                proprietario.setEmail(rs.getString("email"));
                proprietario.setCNH(rs.getString("numeroDaCNH"));
                proprietario.setCategoria(CategoriaCNH.valueOf(rs.getString("categoriaDaCNH")));
            }
            return proprietario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Proprietario buscar(int id) throws Exception {
        try {
            String sql = "select * from Proprietario where id = '"+id+"'";
            Statement statement = conexao.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            Proprietario proprietario = new Proprietario();
            if(rs.next()) {
                proprietario.setId(rs.getInt("id"));
                proprietario.setNome(rs.getString("nome"));
                proprietario.setCPF(rs.getString("CPF"));
                Telefone telefone = new Telefone();
                telefone.setDDI(rs.getInt("telefone_DDI"));
                telefone.setDDD(rs.getInt("telefone_DDD"));
                telefone.setNumero(rs.getInt("telefone_numero"));
                proprietario.setTelefone(telefone);
                proprietario.setEmail(rs.getString("email"));
                proprietario.setCNH(rs.getString("numeroDaCNH"));
                proprietario.setCategoria(CategoriaCNH.valueOf(rs.getString("categoriaDaCNH")));
            }
            return proprietario;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
