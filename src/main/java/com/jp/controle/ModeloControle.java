package com.jp.controle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import com.jp.persistencia.IModeloDao;
import com.jp.persistencia.ModeloDao;
import com.jp.persistencia.IMarcaDao;
import com.jp.modelos.Modelo;
import com.jp.modelos.Marca;
import com.jp.persistencia.ModeloDao;

public class ModeloControle implements IModeloControle{
    public ModeloControle(){
        try {
            this.modeloPersistencia = new ModeloDao();
        }catch (Exception erro){
        }

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
        modeloPersistencia.incluir(objeto);
    }
    @Override
    public void alterar(Modelo objeto) throws Exception{
        objeto.setNome(verificarDescricao(objeto.getNome()));
        if(!verificarVazio(objeto).equals("")) throw new Exception(verificarVazio(objeto));
        modeloPersistencia.alterar(objeto);
    }
    @Override
    public ArrayList<Modelo> listar() throws Exception{
        try{
            /*ArrayList<Modelo> modelos = modeloPersistencia.listar();
            Iterator<Modelo> array = modelos.iterator();
            Marca marca = new Marca();

            while(array.hasNext()){
                Modelo modelo = array.next();
                marca.buscar(modelo.getMarca  ().getId());
            }*/
            return modeloPersistencia.listar();
        }catch(Exception erro){
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

    @Override
    public ArrayList<Modelo> filtrarModelo(Collection modelo, String nome) throws Exception {
        return modeloPersistencia.filtrarModelo(modelo, nome);
    }
}
