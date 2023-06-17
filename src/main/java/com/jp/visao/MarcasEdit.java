package com.jp.visao;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.awt.desktop.OpenFilesEvent;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class MarcasEdit implements Initializable {


    public String url = "";

    @FXML
    private ImageView imagem;

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
        Run.marcasEdit = this;
    }
}
