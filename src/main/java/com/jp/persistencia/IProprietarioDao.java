package com.jp.persistencia;
import com.jp.modelos.Proprietario;

import java.util.ArrayList;
public interface IProprietarioDao {
    void incluir(Proprietario objeto) throws Exception;
    void alterar(Proprietario objeto) throws Exception;
    ArrayList<Proprietario> listar() throws Exception;
    Proprietario buscar(String cpf) throws Exception;

    Proprietario buscar(String cpf, boolean pessoa) throws Exception;
    Proprietario buscar(int id) throws Exception;
}
