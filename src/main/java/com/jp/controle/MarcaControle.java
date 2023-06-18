package com.jp.controle;
import com.jp.modelos.Marca;
import com.jp.persistencia.IMarcaDao;
import com.jp.persistencia.MarcaDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class MarcaControle implements IMarcaControle{
    IMarcaDao marcaPersistencia = null;


    public MarcaControle(){
        try {
            this.marcaPersistencia = new MarcaDao();
        }catch (Exception erro){
            erro.printStackTrace();
        }

    }
    //private String verificarDescricao(String descricao){
    //String str = descricao.replaceAll("\\s+", "");
    //return str;
    //}
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

    @Override
    public Marca buscar(String descricao) throws Exception{
        return marcaPersistencia.buscar(descricao);
    }

    @Override
    public Marca buscar(int id) throws Exception{
        return marcaPersistencia.buscar(id);
    }

    @Override
    public ArrayList<Marca> filtrarMarca(Collection marcas, String nome) throws Exception {
        return marcaPersistencia.filtrarMarcas(marcas, nome);
    }

    private boolean buscarMarca(String descricao) throws Exception{
        try {
            ArrayList<Marca> listagem = listar();
            Iterator<Marca> lista = listagem.iterator();
            while(lista.hasNext()){
                Marca aux = lista.next();
                if(aux.getNome().equalsIgnoreCase(descricao)){
                    return true;
                }
            }
            return false;
        } catch (Exception erro) {
            throw erro;
        }
    }

    private boolean buscarMarca(Marca objeto) throws Exception{
        try {
            ArrayList<Marca> listagem = listar();
            Iterator<Marca> lista = listagem.iterator();
            while(lista.hasNext()){
                Marca aux = lista.next();
                if(aux.getNome().equalsIgnoreCase(objeto.getNome()) && aux.getId() != objeto.getId()){
                    return true;
                }
            }
            return false;
        } catch (Exception erro) {

            throw erro;
        }
    }

    private String verificarVazio(Marca objeto){
        String erro = "";
        boolean descricaovazia = false;
        if(objeto.getNome().equals("")){
            erro = "Preencha o campo de descrição";
            descricaovazia = true;
        }

        if(objeto.getUrl().equals("")){
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

    public void incluir(Marca objeto) throws Exception{
        objeto.setNome(verificarDescricao(objeto.getNome()));
        if(!verificarVazio(objeto).equals("")) throw new Exception(verificarVazio(objeto));
        marcaPersistencia.incluir(objeto);
    }

    public void alterar(Marca objeto) throws Exception{
        objeto.setNome(verificarDescricao(objeto.getNome()));
        if(!verificarVazio(objeto).equals("")) throw new Exception(verificarVazio(objeto));
        marcaPersistencia.alterar(objeto);
    }


    public ArrayList<Marca> listar() throws Exception{
        try{
            return marcaPersistencia.listar();
        }catch(Exception erro){
            return new ArrayList<Marca>();
        }
    }

}
