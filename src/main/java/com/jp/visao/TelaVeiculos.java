package com.jp.visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

    public enum Veiculo{
        MARCA, MODELO, VEICULO
    }

    public void addItem(VBox vbox, AnchorPane pane, Node item, Veiculo veiculo){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            ScrollPane scrollPane = null;
            
            switch (veiculo){
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
            pane.setMinHeight(127 + 100 * vbox.getChildren().size());
            JFXScrollPane.smoothScrolling(scrollPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editItemMarca(int id){
        AnchorPane editMarca = (AnchorPane) getScene("MarcasEdit.fxml");

        ((TextField) editMarca.lookup("#textFieldNome")).setText("sus");
        ((ImageView) editMarca.lookup("#imageView")).setImage(new Image(getClass().getResourceAsStream("..\\icones\\add dados_black.png")));
        // Alterar item

        Run.telaPrincipal.setEditWindow("Editar Marca", editMarca, (m) -> System.out.println("oi"));
    }

    public void editItemModelo(int id){
        AnchorPane editModelo = (AnchorPane) getScene("ModelosEdit.fxml");

        ((TextField) editModelo.lookup("#textFieldNome")).setText("sus");
        ((ImageView) editModelo.lookup("#imageView")).setImage(new Image(getClass().getResourceAsStream("..\\icones\\add dados_black.png")));
        // Alterar item

        Run.telaPrincipal.setEditWindow("Editar Modelo", editModelo, (m) -> System.out.println("oi"));
    }

    public void editItemVeiculo(int id){
        AnchorPane editVeiculo = (AnchorPane) getScene("VeiculosEdit.fxml");

        //((TextField) editVeiculo.lookup("#textFieldNome")).setText("sus");
        //((ImageView) editVeiculo.lookup("#imageView")).setImage(new Image(getClass().getResourceAsStream("..\\icones\\add dados_black.png")));
        // Alterar item

        Run.telaPrincipal.setEditWindow("Editar Veículo", editVeiculo, (m) -> System.out.println("oi"));
    }

    private void showNewItem(String title, Node anchorPane, ActionListener method){
        Run.telaPrincipal.setEditWindow(title, anchorPane, method);
    }

    @FXML
    void newItemMarcas(ActionEvent event) {
        showNewItem("Nova Marca", getScene("MarcasEdit.fxml"), (m) -> System.out.println("teste") /* Comando do banco de dados */);
    }

    @FXML
    void newItemModelos(ActionEvent event) {
        showNewItem("Novo Modelo", getScene("ModelosEdit.fxml"), (m) -> System.out.println("teste") /* Comando do banco de dados */);
    }

    @FXML
    void newItemVeiculos(ActionEvent event) {
        showNewItem("Novo Veículo", getScene("VeiculosEdit.fxml"), (m) -> System.out.println("teste") /* Comando do banco de dados */);
    }

    public Node getScene(String fxml){
        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaVeiculos = this;
        for(int i = 0; i < 10; i++){
           // AnchorPane item = (AnchorPane) ;
            //item.setId("itemVeiculos");

            addItem(veiculoItems, PanefundoVeiculos, getScene("Item.fxml"), Veiculo.VEICULO);
            addItem(modelosItems, PanefundoModelos, getScene("Item.fxml"), Veiculo.MODELO);
            addItem(marcasItems, PanefundoMarcas, getScene("Item.fxml"), Veiculo.MARCA);
        }
        //Run.telaPrincipal.setEditWindow("teste", getScene("MarcasEdit.fxml"), (m) -> System.out.println("teste"));
    }
}
