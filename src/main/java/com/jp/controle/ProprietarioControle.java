package com.jp.controle;

import com.jp.modelos.Proprietario;
import com.jp.persistencia.IProprietarioDao;
import com.jp.persistencia.MarcaDao;
import com.jp.persistencia.ProprietarioDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.InputMismatchException;

public class ProprietarioControle implements IProprietarioControle{


        IProprietarioDao ProprietarioPersistencia = null;

    public ProprietarioControle(){
        try{
            this.ProprietarioPersistencia = new ProprietarioDao();
        }catch (Exception erro){
        }

    }
    @Override
    public void incluir(Proprietario objeto) throws Exception {
        verificarCampos(objeto);
        ProprietarioPersistencia.incluir(objeto);

    }

    @Override
    public void alterar(Proprietario objeto) throws Exception {
        verificarCampos(objeto);
        ProprietarioPersistencia.alterar(objeto);

    }

    @Override
    public ArrayList<Proprietario> listar() throws Exception {
        try{
            return ProprietarioPersistencia.listar();
        }catch(Exception erro){
            return new ArrayList<Proprietario>();
        }
    }

    @Override
    public Proprietario buscar(int id) throws Exception {
        return ProprietarioPersistencia.buscar(id);
    }

    public Proprietario buscar(String nome, boolean pessoa) throws Exception {
        return ProprietarioPersistencia.buscar(nome, pessoa);
    }

    @Override
    public ArrayList<Proprietario> filtrarProprietario(Collection<Proprietario> proprietario, String filtro) throws Exception {
        return ProprietarioPersistencia.filtrarProprietario(proprietario, filtro);
    }

    @Override
    public Proprietario buscar(String cpf) throws Exception {
        try {
            return ProprietarioPersistencia.buscar(cpf);
        } catch (Exception e) {
            throw new Exception("O CPF informado é inválido!!");
        }
    }

    @Override
    public boolean validarCPF(String CPF) {
        if (CPF.length() != 11 || CPF.equals("00000000000") ||  CPF.equals("11111111111") ||    CPF.equals("22222222222") || CPF.equals("33333333333") ||   CPF.equals("44444444444") || CPF.equals("55555555555") ||   CPF.equals("66666666666") || CPF.equals("77777777777") ||   CPF.equals("88888888888") || CPF.equals("99999999999")) return(false);
        char dig10, dig11;
        int sm, i, r, num, peso;
        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }
            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }
    public String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

    private boolean verificarCampos(Proprietario objeto) throws Exception{
        if(!validarCPF(objeto.getCPF()))throw new Exception("O CPF informado é inválido!!");
        if(objeto.getNome().length() < 6) throw new Exception("O nome informado é inválido, por favor digite um nome com pelo menos 6 digitos!!");
        if(!objeto.getEmail().contains("@") || !objeto.getEmail().contains(".com") || objeto.getEmail().length() < 6 ) throw new Exception("O email informado é inválido, por favor digite um email válido!!");
        if(objeto.getTelefone().getDDD() < 11) throw new Exception("O DDD informado é inválido, por favor digite um DDD válido!!");
        if(objeto.getTelefone().getDDI() < 1) throw new Exception("O DDI informado é inválido, por favor digite um DDI válido!!");
        if(objeto.getTelefone().getNumero() < 10000000) throw new Exception("O telefone informado é inválido, por favor digite um telefone válido!!");
        return true;
    }

}
