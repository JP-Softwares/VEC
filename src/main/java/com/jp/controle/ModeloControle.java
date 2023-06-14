package com.jp.controle;
import java.util.ArrayList;
import java.util.Iterator;
//import com.jp.persistencia.IModeloDao;
//import com.jp.persistencia.ModeloDao;
//import com.jp.persistencia.IMarcaDao;
import com.jp.modelos.Modelo;
import com.jp.modelos.Marca;

public class ModeloControle {
    public ModeloControle(){
        this.modeloPersistencia = new ModeloDao();
    }
    IModeloDao modeloPersistencia = null;

    private String verificarDescricao(String descricao){
        String str;
        while(descricao.endsWith(" ")){
            descricao = descricao.replaceFirst(".$", "");
        }
        while(descricao.startsWith(" ")){
            descricao = descricao.substring(1);
        }
        return descricao;
    }

    private boolean buscarModelo(String descricao) throws Exception{
        try {
            ArrayList<Modelo> listagem = listar();
            Iterator<Modelo> lista = listagem.iterator();
            while(lista.hasNext()){
                Modelo aux = lista.next();
                if(aux.getNome().equalsIgnoreCase(descricao)){
                    return true;
                }
            }
            return false;
        } catch (Exception erro) {
            throw erro;
        }
    }
    private boolean buscarModelo(Modelo objeto) throws Exception{
        try {
            ArrayList<Modelo> listagem = listar();
            Iterator<Modelo> lista = listagem.iterator();
            while(lista.hasNext()){
                Modelo aux = lista.next();
                if(aux.getNome().equalsIgnoreCase(objeto.getNome()) && aux.getId() != objeto.getId()){
                    return true;
                }
            }
            return false;
        } catch (Exception erro) {
            throw erro;
        }
    }



    private String verificarVazio(Modelo objeto){
        String erro = "";
        boolean descricaovazia = false;
        if(objeto.getNome().equals("")){
            erro = "Preencha o campo de descrição";
            descricaovazia = true;
        }

        if(objeto.getUrlModelo().equals("")){
            if(descricaovazia){
                erro += " e insira uma imagem";
            }else{
                erro = "Insira uma imagem";
            }
        }

//        if(objeto.getDescricao().equals("") || objeto.getUrl().equals("")){
//            if(objeto.getDescricao().equals(""))erro = "Preencha a descrição corretamente";
//            else if(objeto.getUrl().equals(""))erro = "Insira uma imagem";
//        }
        return erro;
    }

    @Override
    public void incluir(Modelo objeto) throws Exception{
        objeto.setNome(verificarDescricao(objeto.getNome()));
        if(!verificarVazio(objeto).equals("")) throw new Exception(verificarVazio(objeto));
        try{
            if(buscarModelo(objeto.getNome())){
                throw new Exception("Modelo já cadastrado");
            }
        }catch(Exception erro){
            if(erro.getMessage().contains("arquivo especificado")){
            }else{
                throw erro;
            }
        }
        modeloPersistencia.incluir(objeto);
    }
    @Override
    public void alterar(Modelo objeto) throws Exception{
        objeto.setNome(verificarDescricao(objeto.getNome()));
        if(!verificarVazio(objeto).equals("")) throw new Exception(verificarVazio(objeto));
        if(buscarModelo(objeto)){
            throw new Exception("Modelo já cadastrado");
        }
        modeloPersistencia.alterar(objeto);
    }
    @Override
    public ArrayList<Modelo> listar() throws Exception{
        try{
            ArrayList<Modelo> modelos = modeloPersistencia.listar();
            Iterator<Modelo> array = modelos.iterator();
            Marca marca = new Marca();

            while(array.hasNext()){
                Modelo modelo = array.next();
                marca.buscar(modelo.getMarca  ().getId());
            }
            return modeloPersistencia.listar();
        }catch(Exception erro){
            modeloPersistencia.apagar();
            return new ArrayList<Modelo>();
        }

    }
    @Override
    public Modelo buscar(String descricao) throws Exception {
        return modeloPersistencia.buscar(descricao);
    }

    @Override
    public Modelo buscar(int id) throws Exception {
        return modeloPersistencia.buscar(id);
    }
}
