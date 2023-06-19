package com.jp.visao;

import com.jp.modelos.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
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
import java.util.*;

public class TelaVeiculos implements Initializable {

    @FXML
    private AnchorPane PanefundoMarcas;

    @FXML
    private AnchorPane PanefundoModelos;

    @FXML
    private AnchorPane PanefundoVeiculos;

    @FXML
    private VBox marcasItems;

    @FXML
    private VBox modelosItems;

    @FXML
    private VBox veiculoItems;

    @FXML
    private TabPane tabPane;

    @FXML
    private ScrollPane scrollPaneMarcas;

    @FXML
    private ScrollPane scrollPaneModelos;

    @FXML
    private ScrollPane scrollPaneVeiculos;

    HashMap<Integer, Marca> marcaHashMap = null;
    HashMap<Integer, Modelo> modeloHashMap = null;
    HashMap<Integer, Veiculo> veiculoHashMap = null;

    int idAtual = 0;

    String caminhoPadrao = "/com/jp/";

    @FXML
    private TextField searchBarMarcas;

    @FXML
    private TextField searchBarModelos;

    @FXML
    private TextField searchBarVeiculos;

    public enum TipoDoVeiculo {
        MARCA, MODELO, VEICULO
    }

    public void addItem(VBox vbox, AnchorPane pane, Node item, TipoDoVeiculo tipoDoVeiculo){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            ScrollPane scrollPane = null;
            
            switch (tipoDoVeiculo){
                case MARCA:
                    item.setId("itemMarca");
                    scrollPane = scrollPaneMarcas;
                    break;
                case MODELO:
                    item.setId("itemModelo");
                    scrollPane = scrollPaneModelos;
                    break;
                case VEICULO:
                    item.setId("itemVeiculo");
                    scrollPane = scrollPaneVeiculos;
                    break;
            }
            vbox.getChildren().add(item);
            pane.setMinHeight(127 + 100 * vbox.getChildren().size() + 15 * vbox.getChildren().size());
            //JFXScrollPane.smoothScrolling(scrollPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editItemMarca(int id){
        AnchorPane editMarca = (AnchorPane) getScene("MarcasEdit.fxml");
        Marca marca = marcaHashMap.get(id);
        idAtual = id;
        //AnchorPane itemMarca = (AnchorPane) marcasItems.getChildren().filtered(node -> Integer.parseInt(((Label)node.lookup("#id")).getText()) == id).get(0);
        ((TextField) editMarca.lookup("#textFieldNome")).setText(marca.getNome());

        try{
            ((ImageView) editMarca.lookup("#imagem")).setImage(new Image(getClass().getResource(marca.getUrl()).toString()));
        }catch (Exception erroImagem){
            System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
        }

        Run.telaPrincipal.setEditWindow("Editar Marca", editMarca, (m) -> alterarMarca());
    }

    public void editItemModelo(int id){
        AnchorPane editModelo = (AnchorPane) getScene("ModelosEdit.fxml");

        Modelo modelo = modeloHashMap.get(id);
        idAtual = id;

        ((TextField) editModelo.lookup("#textFieldNome")).setText(modelo.getNome());

        try{
            ((ImageView) editModelo.lookup("#imagem")).setImage(new Image(getClass().getResource(modelo.getUrlModelo()).toString()));
        }catch (Exception erroImagem){
            System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
        }

        ComboBox comboBoxmarca = (ComboBox) editModelo.lookup("#marca");
        Collection<Marca> marcas = marcaHashMap.values();
        ArrayList<String> nomeDasMarcas = new ArrayList<>();
        marcas.forEach(currentMarca -> {
            nomeDasMarcas.add(currentMarca.getNome());
        });
        comboBoxmarca.getItems().addAll(nomeDasMarcas);
        comboBoxmarca.setValue(modelo.getMarca().getNome());

        ComboBox comboBoxtipoDoVeiculo = (ComboBox) editModelo.lookup("#tipoDoVeiculo");
        comboBoxtipoDoVeiculo.getItems().addAll(com.jp.modelos.TipoDoVeiculo.values());
        comboBoxtipoDoVeiculo.setValue(modelo.getTipo().toString());
        // Alterar item

        Run.telaPrincipal.setEditWindow("Editar Modelo", editModelo, (m) -> alterarModelo());
    }

    public void editItemVeiculo(int id){
        AnchorPane editVeiculo = (AnchorPane) getScene("VeiculosEdit.fxml");

        Veiculo veiculo = veiculoHashMap.get(id);
        idAtual = id;

        ComboBox comboBoxmodelo = (ComboBox) editVeiculo.lookup("#modelo");
        Collection<Modelo> modelos = modeloHashMap.values();
        ArrayList<String> nomeDosModelos = new ArrayList<>();
        modelos.forEach(currentModelo -> {
            nomeDosModelos.add(currentModelo.getNome());
        });
        comboBoxmodelo.getItems().addAll(nomeDosModelos);
        comboBoxmodelo.setValue(veiculo.getModelo().getNome());

        try {
            ComboBox comboBoxProprietario = (ComboBox) editVeiculo.lookup("#proprietario");
            Collection<Proprietario> proprietarios = Run.proprietarioControle.listar();
            ArrayList<String> nomeDosProprietarios = new ArrayList<>();
            proprietarios.forEach(currentProprietario -> {
                nomeDosProprietarios.add(currentProprietario.getNome());
            });
            comboBoxProprietario.getItems().addAll(nomeDosProprietarios);
            comboBoxProprietario.setValue(veiculo.getProprietario().getNome());
        }catch (Exception e){
            e.printStackTrace();
        }

        ((TextField) editVeiculo.lookup("#textFieldPlaca")).setText(veiculo.getPlaca());
        ((TextField) editVeiculo.lookup("#textFieldAnoDoModelo")).setText(veiculo.getAnoModelo() + "");
        ((TextField) editVeiculo.lookup("#textFieldAnoDeFabricacao")).setText(veiculo.getAnoFabricacao() + "");
        ((TextField) editVeiculo.lookup("#textFieldQuilometragemAtual")).setText(veiculo.getKilometragem() + "");

        ComboBox comboBoxtipoDoCombustivel = (ComboBox) editVeiculo.lookup("#tipoDoCombustivel");
        comboBoxtipoDoCombustivel.getItems().addAll(TipoDoCombustivel.values());
        comboBoxtipoDoCombustivel.setValue(veiculo.getCombustivel());

        ComboBox comboBoxsituacaoDoVeiculo = (ComboBox) editVeiculo.lookup("#situacaoDoVeiculo");
        comboBoxsituacaoDoVeiculo.getItems().addAll(com.jp.modelos.SituacaoDoVeiculo.values());
        comboBoxsituacaoDoVeiculo.setValue(veiculo.getSituacao().toString());

        //((ImageView) editVeiculo.lookup("#imageView")).setImage(new Image(getClass().getResource("..\\icones\\add dados_black.png").toString()));
        // Alterar item

        Run.telaPrincipal.setEditWindow("Editar Veículo", editVeiculo, (m) -> alterarVeiculo());
    }

    private void showNewItem(String title, Node anchorPane, ActionListener method){
        Run.telaPrincipal.setEditWindow(title, anchorPane, method);
    }

    @FXML
    void newItemMarcas(ActionEvent event) {
        showNewItem("Nova Marca", getScene("MarcasEdit.fxml"), (m) -> inserirMarca() /* Comando do banco de dados */);
    }

    @FXML
    void newItemModelos(ActionEvent event) {
        AnchorPane editModelo = (AnchorPane) getScene("ModelosEdit.fxml");

        ComboBox comboBoxmarca = (ComboBox) editModelo.lookup("#marca");
        Collection<Marca> marcas = marcaHashMap.values();
        ArrayList<String> nomeDasMarcas = new ArrayList<>();
        marcas.forEach(currentMarca -> {
            nomeDasMarcas.add(currentMarca.getNome());
        });
        comboBoxmarca.getItems().addAll(nomeDasMarcas);

        ComboBox comboBoxtipoDoVeiculo = (ComboBox) editModelo.lookup("#tipoDoVeiculo");
        comboBoxtipoDoVeiculo.getItems().addAll(com.jp.modelos.TipoDoVeiculo.values());
        showNewItem("Novo Modelo", editModelo, (m) -> inserirModelo() /* Comando do banco de dados */);
    }

    @FXML
    void newItemVeiculos(ActionEvent event) {
        AnchorPane editVeiculo = (AnchorPane) getScene("VeiculosEdit.fxml");

        ComboBox comboBoxmodelo = (ComboBox) editVeiculo.lookup("#modelo");
        Collection<Modelo> modelos = modeloHashMap.values();
        ArrayList<String> nomeDosModelos = new ArrayList<>();
        modelos.forEach(currentModelo -> {
            nomeDosModelos.add(currentModelo.getNome());
        });
        comboBoxmodelo.getItems().addAll(nomeDosModelos);

        try {
            ComboBox comboBoxProprietario = (ComboBox) editVeiculo.lookup("#proprietario");
            Collection<Proprietario> proprietarios = Run.proprietarioControle.listar();
            ArrayList<String> nomeDosProprietarios = new ArrayList<>();
            proprietarios.forEach(currentProprietario -> {
                nomeDosProprietarios.add(currentProprietario.getNome());
            });
            comboBoxProprietario.getItems().addAll(nomeDosProprietarios);
        }catch (Exception e){
            e.printStackTrace();
        }

        ComboBox comboBoxtipoDoCombustivel = (ComboBox) editVeiculo.lookup("#tipoDoCombustivel");
        comboBoxtipoDoCombustivel.getItems().addAll(TipoDoCombustivel.values());

        ComboBox comboBoxsituacaoDoVeiculo = (ComboBox) editVeiculo.lookup("#situacaoDoVeiculo");
        comboBoxsituacaoDoVeiculo.getItems().addAll(com.jp.modelos.SituacaoDoVeiculo.values());

        showNewItem("Novo Veículo", editVeiculo, (m) -> inserirVeiculo() /* Comando do banco de dados */);
    }

    public Node getScene(String fxml){
        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void inserirMarca(){
        try {
            Marca marca = new Marca();
            marca.setNome(Run.marcasEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.marcasEdit.imagem.getImage().getUrl().replaceFirst("file:", "").replace("%20", " ");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = "src/main/resources/com/jp/imagens/Marcas/" + marca.getNome() + "." + extensao;
            File imagemNoProjeto = new File(url);
            marca.setUrl("../imagens/Marcas/" + marca.getNome() + "." + extensao);
            Run.marcaControle.incluir(marca);

            File imagemPessoal = new File(urlImagemPessoal);
            FileUtils.copyFile(imagemPessoal, imagemNoProjeto, StandardCopyOption.REPLACE_EXISTING);

            listar(TipoDoVeiculo.MARCA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inserirModelo(){
        try {
            Modelo modelo = new Modelo();
            modelo.setNome(Run.modelosEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.modelosEdit.imagem.getImage().getUrl().replaceFirst("file:", "").replace("%20", " ");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = "src/main/resources/com/jp/imagens/Modelos/" + modelo.getNome() + "." + extensao;
            File imagemNoProjeto = new File(url);
            modelo.setUrlModelo("../imagens/Modelos/" + modelo.getNome() + "." + extensao);
            modelo.setTipo(com.jp.modelos.TipoDoVeiculo.valueOf(Run.modelosEdit.comboBoxTipoDoVeiculo.getValue() + ""));
            modelo.setMarca(Run.marcaControle.buscar(Run.modelosEdit.comboBoxMarca.getValue() + ""));

            Run.modeloControle.incluir(modelo);

            File imagemPessoal = new File(urlImagemPessoal);
            FileUtils.copyFile(imagemPessoal, imagemNoProjeto, StandardCopyOption.REPLACE_EXISTING);

            listar(TipoDoVeiculo.MODELO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void inserirVeiculo(){
        try {
            Veiculo veiculo = new Veiculo();
            VeiculosEdit veiculosEdit = Run.veiculosEdit;
            veiculo.setModelo(Run.modeloControle.buscar(veiculosEdit.comboBoxModelo.getValue() + ""));
            veiculo.setProprietario(Run.proprietarioControle.buscar(veiculosEdit.comboBoxproprietario.getValue() + "", true));
            veiculo.setPlaca(veiculosEdit.textFieldPlaca.getText());
            veiculo.setAnoModelo(Integer.parseInt(veiculosEdit.textFieldAnoDoModelo.getText()));
            veiculo.setAnoFabricacao(Integer.parseInt(veiculosEdit.textFieldAnoDeFabricacao.getText()));
            veiculo.setKilometragem(Integer.parseInt(veiculosEdit.textFieldQuilometragemAtual.getText()));
            veiculo.setCombustivel(TipoDoCombustivel.valueOf(veiculosEdit.comboBoxTipoDoCombustivel.getValue() + ""));
            veiculo.setSituacao(SituacaoDoVeiculo.valueOf(veiculosEdit.comboBoxSituacaoDoVeiculo.getValue() + ""));

            Run.veiculoControle.incluir(veiculo);

            listar(TipoDoVeiculo.VEICULO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarMarca(){
        try {
            Marca marca = new Marca();
            marca.setId(idAtual);
            marca.setNome(Run.marcasEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.marcasEdit.imagem.getImage().getUrl().replaceFirst("file:", "");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = caminhoPadrao + "imagens/Marcas/" + marca.getNome() + "." + extensao;
            File imagemNoProjeto = new File("src/main/resources" + url);
            marca.setUrl(url);

            String marcaAnterior = Run.marcaControle.buscar(idAtual).getUrl();

            Run.marcaControle.alterar(marca);

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

            listar(TipoDoVeiculo.MARCA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarModelo(){
        try {
            Modelo modelo = new Modelo();
            modelo.setId(idAtual);
            modelo.setNome(Run.modelosEdit.textFieldNome.getText());
            String urlImagemPessoal = Run.modelosEdit.imagem.getImage().getUrl().replaceFirst("file:", "").replace("%20", " ");
            String pontos[] = urlImagemPessoal.split("\\.");
            String extensao = pontos[pontos.length-1];
            String url = caminhoPadrao + "imagens/Modelos/" + modelo.getNome() + "." + extensao;
            File imagemNoProjeto = new File(url);
            modelo.setUrlModelo(url);
            modelo.setTipo(com.jp.modelos.TipoDoVeiculo.valueOf(Run.modelosEdit.comboBoxTipoDoVeiculo.getValue() + ""));
            modelo.setMarca(Run.marcaControle.buscar(Run.modelosEdit.comboBoxMarca.getValue() + ""));

            String modeloAnterior = Run.modeloControle.buscar(idAtual).getUrlModelo();

            Run.modeloControle.alterar(modelo);

            if(!modeloAnterior.equals(url)) imagemNoProjeto.createNewFile();

            File imagemPessoal = new File(urlImagemPessoal);

            //BufferedImage image = ImageIO.read(imagemPessoal);

            //ImageIO.write(image, extensao, imagemNoProjeto);
            FileUtils.copyFile(imagemPessoal, imagemNoProjeto, StandardCopyOption.REPLACE_EXISTING);
            if(!modeloAnterior.equals(url)){
                try{
                    File arquivoAntigo = new File("src/main/resources" + modeloAnterior);

                    if(arquivoAntigo.exists()) FileUtils.delete(arquivoAntigo);

                }catch (Exception e){e.printStackTrace();}
            }

            listar(TipoDoVeiculo.MODELO);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void alterarVeiculo(){
        try {
            VeiculosEdit veiculosEdit = Run.veiculosEdit;
            Veiculo veiculo = new Veiculo();
            veiculo.setId(idAtual);
            veiculo.setModelo(Run.modeloControle.buscar(veiculosEdit.comboBoxModelo.getValue() + ""));
            veiculo.setProprietario(Run.proprietarioControle.buscar(veiculosEdit.comboBoxproprietario.getValue() + "", true));
            veiculo.setPlaca(veiculosEdit.textFieldPlaca.getText());
            veiculo.setAnoModelo(Integer.parseInt(veiculosEdit.textFieldAnoDoModelo.getText()));
            veiculo.setAnoFabricacao(Integer.parseInt(veiculosEdit.textFieldAnoDeFabricacao.getText()));
            veiculo.setKilometragem(Integer.parseInt(veiculosEdit.textFieldQuilometragemAtual.getText()));
            veiculo.setCombustivel(TipoDoCombustivel.valueOf(veiculosEdit.comboBoxTipoDoCombustivel.getValue() + ""));
            veiculo.setSituacao(SituacaoDoVeiculo.valueOf(veiculosEdit.comboBoxSituacaoDoVeiculo.getValue() + ""));

            Run.veiculoControle.alterar(veiculo);

            listar(TipoDoVeiculo.VEICULO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void listar(TipoDoVeiculo tipoDoVeiculo){
        switch (tipoDoVeiculo){
            case MARCA:
                try {
                    ArrayList<Marca> marcas = Run.marcaControle.listar();
                    marcaHashMap = new HashMap<>();

                    marcasItems.getChildren().clear();
                    marcas.forEach(marca -> {

                        AnchorPane itemMarca = (AnchorPane) getScene("Item.fxml");
                        ((Label) itemMarca.lookup("#title")).setText(marca.getNome() + "");
                        ((Label) itemMarca.lookup("#description")).setText("");
                        ((Label) itemMarca.lookup("#id")).setText(marca.getId() + "");

                        try{
                            ((ImageView) itemMarca.lookup("#imagem")).setImage(new Image(getClass().getResource(marca.getUrl()).toString()));
                        }catch (Exception erroImagem){
                            System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                        }

                        addItem(marcasItems, PanefundoMarcas, itemMarca, tipoDoVeiculo);
                        marcaHashMap.put(marca.getId(), marca);
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
                break;
            case MODELO:
                try {
                    ArrayList<Modelo> modelos = Run.modeloControle.listar();
                    modeloHashMap = new HashMap<>();

                    modelosItems.getChildren().clear();

                    modelos.forEach(modelo -> {

                        AnchorPane itemModelo = (AnchorPane) getScene("Item.fxml");
                        ((Label) itemModelo.lookup("#title")).setText(modelo.getNome() + " - " + modelo.getMarca().getNome());
                        ((Label) itemModelo.lookup("#description")).setText("Tipo do Veículo: " + modelo.getTipo().toString());
                        ((Label) itemModelo.lookup("#id")).setText(modelo.getId() + "");

                        try{
                            ((ImageView) itemModelo.lookup("#imagem")).setImage(new Image(getClass().getResource(modelo.getUrlModelo()).toString()));
                        }catch (Exception erroImagem){
                            System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                        }
                        addItem(modelosItems, PanefundoModelos, itemModelo, tipoDoVeiculo);
                        modeloHashMap.put(modelo.getId(), modelo);
//                        Platform.runLater(new Runnable() {
//                            @Override
//                            public void run() {
//                            }
//                        });

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case VEICULO:
                try {
                    ArrayList<Veiculo> veiculos = Run.veiculoControle.listar();
                    veiculoHashMap = new HashMap<>();

                    veiculoItems.getChildren().clear();

                    veiculos.forEach(veiculo -> {

                        AnchorPane itemVeiculo = (AnchorPane) getScene("Item.fxml");
                        ((Label) itemVeiculo.lookup("#title")).setText(veiculo.getPlaca() + " - " + veiculo.getModelo().getNome());
                        ((Label) itemVeiculo.lookup("#description")).setText("Marca: "+ veiculo.getModelo().getMarca().getNome()
                                +"\nProprietário: " + veiculo.getProprietario().getNome());
                        ((Label) itemVeiculo.lookup("#id")).setText(veiculo.getId() + "");

                        try{
                            ((ImageView) itemVeiculo.lookup("#imagem")).setImage(new Image(getClass().getResource(veiculo.getModelo().getUrlModelo()).toString()));
                        }catch (Exception erroImagem){
                            System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                        }
                        addActionTelaGastos(itemVeiculo);
                        addItem(veiculoItems, PanefundoVeiculos, itemVeiculo, tipoDoVeiculo);
                        veiculoHashMap.put(veiculo.getId(), veiculo);
//                        Platform.runLater(new Runnable() {
//
//                            @Override
//                            public void run() {
//                            }
//                        });
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }


    @FXML
    void filtrarMarca(ActionEvent event) {
        try {
            ArrayList<Marca> marcas = Run.marcaControle.filtrarMarca(marcaHashMap.values(), searchBarMarcas.getText());

            marcasItems.getChildren().clear();
            marcas.forEach(marca -> {

                AnchorPane itemMarca = (AnchorPane) getScene("Item.fxml");
                ((Label) itemMarca.lookup("#title")).setText(marca.getNome() + "");
                ((Label) itemMarca.lookup("#description")).setText("");
                ((Label) itemMarca.lookup("#id")).setText(marca.getId() + "");

                try{
                    ((ImageView) itemMarca.lookup("#imagem")).setImage(new Image(getClass().getResource(marca.getUrl()).toString()));
                }catch (Exception erroImagem){
                    System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                }

                addItem(marcasItems, PanefundoMarcas, itemMarca, TipoDoVeiculo.MARCA);
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

    public void addActionTelaGastos(AnchorPane item){
        item.setCursor(Cursor.HAND);

        item.setOnMouseClicked(mouseEvent -> {
            Run.telaPrincipal.setScene("TelaGastos.fxml");

            Run.telaGastos.veiculo = veiculoHashMap.get(Integer.parseInt(((Label) item.lookup("#id")).getText()));

            Run.telaGastos.listar();
        });
    }

    @FXML
    void filtrarModelo(ActionEvent event) {
        try {
            ArrayList<Modelo> modelos = Run.modeloControle.filtrarModelo(modeloHashMap.values(), searchBarModelos.getText());

            modelosItems.getChildren().clear();

            modelos.forEach(modelo -> {

                AnchorPane itemModelo = (AnchorPane) getScene("Item.fxml");
                ((Label) itemModelo.lookup("#title")).setText(modelo.getNome() + " - " + modelo.getMarca().getNome());
                ((Label) itemModelo.lookup("#description")).setText("Tipo do Veículo: " + modelo.getTipo().toString());
                ((Label) itemModelo.lookup("#id")).setText(modelo.getId() + "");

                try{
                    ((ImageView) itemModelo.lookup("#imagem")).setImage(new Image(getClass().getResource(modelo.getUrlModelo()).toString()));
                }catch (Exception erroImagem){
                    System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                }

                addItem(modelosItems, PanefundoModelos, itemModelo, TipoDoVeiculo.MODELO);
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
    void filtrarVeiculo(ActionEvent event) {
        try {
            ArrayList<Veiculo> veiculos = Run.veiculoControle.filtrarVeiculo(veiculoHashMap.values(), searchBarVeiculos.getText());

            veiculoItems.getChildren().clear();

            veiculos.forEach(veiculo -> {

                AnchorPane itemVeiculo = (AnchorPane) getScene("Item.fxml");
                ((Label) itemVeiculo.lookup("#title")).setText(veiculo.getPlaca() + " - " + veiculo.getModelo().getNome());
                ((Label) itemVeiculo.lookup("#description")).setText("Marca: "+ veiculo.getModelo().getMarca().getNome()
                        +"\nProprietário: " + veiculo.getProprietario().getNome());
                ((Label) itemVeiculo.lookup("#id")).setText(veiculo.getId() + "");

                try{
                    ((ImageView) itemVeiculo.lookup("#imagem")).setImage(new Image(getClass().getResource(veiculo.getModelo().getUrlModelo()).toString()));
                }catch (Exception erroImagem){
                    System.out.println("Erro ao pegar a imagem.\nErro: " + erroImagem.getMessage());
                }
                addItem(veiculoItems, PanefundoVeiculos, itemVeiculo, TipoDoVeiculo.VEICULO);
//                        Platform.runLater(new Runnable() {
//
//                            @Override
//                            public void run() {
//                            }
//                        });
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaVeiculos = this;

        listar(TipoDoVeiculo.MARCA);
        listar(TipoDoVeiculo.MODELO);
        listar(TipoDoVeiculo.VEICULO);
        //for(int i = 0; i < 10; i++){
           // AnchorPane item = (AnchorPane) ;
            //item.setId("itemVeiculos");

            //addItem(veiculoItems, PanefundoVeiculos, getScene("Item.fxml"), TipoDoVeiculo.VEICULO);
            //addItem(modelosItems, PanefundoModelos, getScene("Item.fxml"), TipoDoVeiculo.MODELO);
            //addItem(marcasItems, PanefundoMarcas, getScene("Item.fxml"), TipoDoVeiculo.MARCA);
        //}
        //Run.telaPrincipal.setEditWindow("teste", getScene("MarcasEdit.fxml"), (m) -> System.out.println("teste"));
    }
}