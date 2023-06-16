package com.jp.visao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    public void addItem(VBox vbox, AnchorPane pane, Node item){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            vbox.getChildren().add(item);
            pane.setMinHeight(127 + 100 * vbox.getChildren().size());
        }catch (Exception e){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaVeiculos = this;
        for(int i = 0; i < 10; i++){
            addItem(veiculoItems, PanefundoVeiculos, getScene("Item.fxml"));
            addItem(modelosItems, PanefundoModelos, getScene("Item.fxml"));
            addItem(marcasItems, PanefundoMarcas, getScene("Item.fxml"));
        }
    }
}
