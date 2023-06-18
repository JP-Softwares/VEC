package com.jp.controle;
import com.jp.modelos.Proprietario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.jp.modelos.Modelo;
import java.util.Date;

public interface IProprietarioControle {
    void incluir(Proprietario objeto) throws Exception;

    void alterar(Proprietario objeto) throws Exception;

    ArrayList<Proprietario> listar() throws Exception;

    Proprietario buscar(int id) throws Exception;
    Proprietario buscar(String cpf) throws Exception;
    Proprietario buscar(String nome, boolean pessoa) throws Exception;
    public String imprimeCPF(String CPF);
    ArrayList<Proprietario> filtrarProprietario(Collection<Proprietario> proprietario , String filtro)throws Exception;
    boolean validarCPF (String CPF);
}
