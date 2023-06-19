package com.jp.visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class Item implements Initializable {


    @FXML
    private Label description;

    @FXML
    private Button editButton;

    @FXML
    private Label id;

    @FXML
    private Label title;

    @FXML
    private AnchorPane Card;

    @FXML
    void editItem(ActionEvent event) {
        switch (Card.getId()){
            case "itemMarca":
                Run.telaVeiculos.editItemMarca(Integer.parseInt(id.getText()));
                break;
            case "itemModelo":
                Run.telaVeiculos.editItemModelo(Integer.parseInt(id.getText()));
                break;
            case "itemVeiculo":
                Run.telaVeiculos.editItemVeiculo(Integer.parseInt(id.getText()));
                break;
            case "itemProprietario":
                Run.telaProprietarios.editItemProprietario(Integer.parseInt(id.getText()));
                break;
            case "itemTipoDeGasto":
                Run.telaTipoDeGasto.editItemTipoDeGasto(Integer.parseInt(id.getText()));
                break;
            case "itemGasto":
                Run.telaGastos.editItemGasto(Integer.parseInt(id.getText()));
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
