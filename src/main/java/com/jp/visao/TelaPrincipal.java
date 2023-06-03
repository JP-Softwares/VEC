package com.jp.visao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;

import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipal implements Initializable{

    @FXML
    private ToolBar barraDeTitulo;

    @FXML
    private Button closeButton;

    private double x, y;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barraDeTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barraDeTitulo.setOnMouseDragged(mouseEvent -> {
            barraDeTitulo.getScene().getWindow().setX(mouseEvent.getScreenX() - x);
            barraDeTitulo.getScene().getWindow().setY(mouseEvent.getScreenY() - y);
        });
    }
}
