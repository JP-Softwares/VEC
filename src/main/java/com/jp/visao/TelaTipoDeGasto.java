package com.jp.visao;

import com.jp.modelos.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.apache.commons.io.FileUtils;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TelaTipoDeGasto implements Initializable {


    @FXML
    private AnchorPane PanefundoTipoDeGasto;

    @FXML
    private ScrollPane scrollPaneTipoDeGasto;

    @FXML
    private TextField searchBarTipoDeGasto;

    @FXML
    private VBox TipoDeGastoItems;

    int idAtual = 0;

    HashMap<Integer, TipoDeGastos> tipoDeGastosHashMap = null;

    String caminhoPadrao = "/com/jp/";

    public void addItem(Node item){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            item.setId("itemTipoDeGasto");

            TipoDeGastoItems.getChildren().add(item);
            PanefundoTipoDeGasto.setMinHeight(127 + 100 * TipoDeGastoItems.getChildren().size() + 15 * TipoDeGastoItems.getChildren().size());
            //JFXScrollPane.smoothScrolling(scrollPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editItemTipoDeGasto(int id){
        AnchorPane editTipoDeGasto = (AnchorPane) getScene("TipoDeGastosEdit.fxml");
        TipoDeGastos tipoDeGastos = tipoDeGastosHashMap.get(id);
        idAtual = id;
        //AnchorPane itemMarca = (AnchorPane) marcasItems.getChildren().filtered(node -> Integer.parseInt(((Label)node.lookup("#id")).getText()) == id).get(0);
        ((TextField) editTipoDeGasto.lookup("#textFieldNome")).setText(tipoDeGastos.getNome());
        try{
            ((ImageView) editTipoDeGasto.lookup("#imagem")).setImage(new Image(getClass().getResource(tipoDeGastos.getIcone()).toString()));
        }catch (Exception e){
            System.out.println("Erro ao pegar a imagem.\nErro: " + e.getMessage());
        }

        Run.telaPrincipal.setEditWindow("Editar Tipo de Gasto", editTipoDeGasto, (m) -> alterarTipoDeGasto());
    }

    private void showNewItem(String title, Node anchorPane, ActionListener method){
        Run.telaPrincipal.setEditWindow(title, anchorPane, method);
    }

    @FXML
    void filtrarTipoDeGasto(ActionEvent event) {
        try {
            ArrayList<TipoDeGastos> tipoDeGastosArrayList = Run.tipoDeGastosControle.filtrarTipoDeGastos(tipoDeGastosHashMap.values(), searchBarTipoDeGasto.getText());

            TipoDeGastoItems.getChildren().clear();
            tipoDeGastosArrayList.forEach(tipoDeGastos -> {

                AnchorPane itemTipoDeGasto = (AnchorPane) getScene("Item.fxml");
                ((Label) itemTipoDeGasto.lookup("#title")).setText(tipoDeGastos.getNome() + "");
                ((Label) itemTipoDeGasto.lookup("#description")).setText("");
                ((Label) itemTipoDeGasto.lookup("#id")).setText(tipoDeGastos.getId() + "");

                try{
                    ((ImageView) itemTipoDeGasto.lookup("#imagem")).setImage(new Image(getClass().getResource(tipoDeGastos.getIcone()).toString()));
                }catch (Exception erroImagem){
                    System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                }

                addItem(itemTipoDeGasto);
//
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                            }
//                        });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newItemTipoDeGasto(ActionEvent event) {
        showNewItem("Novo Tipo de Gasto", getScene("TipoDeGastosEdit.fxml"), (m) -> inserirTipoDeGasto() /* Comando do banco de dados */);
    }

    public void inserirTipoDeGasto(){
        try {
            TipoDeGastos tipoDeGastos = new TipoDeGastos();
            tipoDeGastos.setNome(Run.tipoDeGastosEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.tipoDeGastosEdit.imagem.getImage().getUrl().replaceFirst("file:", "").replace("%20", " ");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = caminhoPadrao + "imagens/TipoDeGasto/" + tipoDeGastos.getNome() + "." + extensao;
            File imagemNoProjeto = new File("src/main/resources" + url);
            tipoDeGastos.setIcone(url);
            Run.tipoDeGastosControle.incluir(tipoDeGastos);

            File imagemPessoal = new File(urlImagemPessoal);
            FileUtils.copyFile(imagemPessoal, imagemNoProjeto, StandardCopyOption.REPLACE_EXISTING);

            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarTipoDeGasto(){
        try {
            TipoDeGastos tipoDeGastos = new TipoDeGastos();
            tipoDeGastos.setId(idAtual);
            tipoDeGastos.setNome(Run.tipoDeGastosEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.tipoDeGastosEdit.imagem.getImage().getUrl().replaceFirst("file:", "");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = caminhoPadrao + "imagens/TipoDeGasto/" + tipoDeGastos.getNome() + "." + extensao;
            File imagemNoProjeto = new File("src/main/resources" + url);
            tipoDeGastos.setIcone(url);

            String marcaAnterior = Run.tipoDeGastosControle.buscar(idAtual).getIcone();

            Run.tipoDeGastosControle.alterar(tipoDeGastos);

            //if(!marcaAnterior.equals(url)) imagemNoProjeto.createNewFile();


            File imagemPessoal = new File(urlImagemPessoal);

            //BufferedImage image = ImageIO.read(imagemPessoal);

            //ImageIO.write(image, extensao, imagemNoProjeto);
            FileUtils.copyFile(imagemPessoal, imagemNoProjeto, StandardCopyOption.REPLACE_EXISTING);
            if(!marcaAnterior.equals(url)){
                try{
                    File arquivoAntigo = new File("src/main/resources" + marcaAnterior);

                    if(arquivoAntigo.exists()) FileUtils.delete(arquivoAntigo);

                }catch (Exception e){e.printStackTrace();}
            }

            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getScene(String fxml){
        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listar(){
        try {
            ArrayList<TipoDeGastos> tipoDeGastosArrayList = Run.tipoDeGastosControle.listar();
            tipoDeGastosHashMap = new HashMap<>();

            TipoDeGastoItems.getChildren().clear();
            tipoDeGastosArrayList.forEach(tipoDeGastos -> {

                AnchorPane itemTipoDeGasto = (AnchorPane) getScene("Item.fxml");
                ((Label) itemTipoDeGasto.lookup("#title")).setText(tipoDeGastos.getNome());
                ((Label) itemTipoDeGasto.lookup("#description")).setText("");
                ((Label) itemTipoDeGasto.lookup("#id")).setText(tipoDeGastos.getId() + "");

                try{
                    ((ImageView)itemTipoDeGasto.lookup("#imagem")).setImage(new Image(getClass().getResource(tipoDeGastos.getIcone()).toString()));
                }catch (Exception e){
                    System.out.println("Erro ao pegar a imagem.\nErro: " + e.getMessage());
                }

                addItem(itemTipoDeGasto);
                tipoDeGastosHashMap.put(tipoDeGastos.getId(), tipoDeGastos);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaTipoDeGasto = this;

        listar();
    }
}
