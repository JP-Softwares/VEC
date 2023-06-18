package com.jp.visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ModelosEdit implements Initializable {

    @FXML
    public ComboBox<?> comboBoxMarca;

    @FXML
    public ComboBox<?> comboBoxTipoDoVeiculo;

    @FXML
    public ImageView imagem;

    @FXML
    public TextField textFieldNome;

    @FXML
    void abrirImagem(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setTitle("Abrir imagem");
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("Imagens", "*.jpg", "*.png", "*.jpeg", "*.gif"));
        File arquivo = fc.showOpenDialog(Run.app.stage.getScene().getWindow());

        imagem.setImage(new Image(arquivo.toURI().toString()));
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.modelosEdit = this;
    }
}
