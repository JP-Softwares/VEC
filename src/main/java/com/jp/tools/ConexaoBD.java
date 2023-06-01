/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jp.tools;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.ini4j.Ini;

public class ConexaoBD {
    private static Connection conexao = null;
    
    private ConexaoBD(){}
    
    public static Connection getConexao() throws Exception{
        try{
            if(conexao == null){
                File arquivo = new File(ConexaoBD.class.getResource("/DatabaseConnection.ini").toURI());
                Ini arquivoini = new Ini(arquivo);
                String DatabaseConnection = arquivoini.get("connection", "url").replace("postgresql://", "");
                String user = DatabaseConnection.substring(0, DatabaseConnection.indexOf(":"));
                DatabaseConnection = DatabaseConnection.replace(user + ":", "");
                String password = DatabaseConnection.substring(0, DatabaseConnection.indexOf("@"));
                DatabaseConnection = DatabaseConnection.replace(password + "@", "");
                
                String driver = "org.postgresql.Driver";
                String url = "jdbc:postgresql://" + DatabaseConnection;
                Class.forName(driver);
                conexao = DriverManager.getConnection(url, user, password);
            }               
          }
        catch(ClassNotFoundException erro){
            //Erro de não encontrar o drive do banco no projeto
            throw new Exception("Drive: "+erro.getMessage());
        }
        catch(SQLException erro){
            //Erro no banco de dados: usuario, senha ou banco de dados 
            throw new Exception("Banco: " + erro.getMessage());
        }
        return conexao;
    }   
}
