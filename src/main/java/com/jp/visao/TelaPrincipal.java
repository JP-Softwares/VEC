package com.jp.visao;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class TelaPrincipal implements Initializable{

    @FXML
    private BorderPane barraDeTitulo;

    @FXML
    private Button closeButton;

    @FXML
    private ImageView logoVEC;

    @FXML
    private Button maximizeButton;

    @FXML
    private Button minimizeButton;

    @FXML
    private Rectangle maximizedRectangle;

    private double x, y;

    private void setMaximized(boolean maximize){
        Stage stage = Run.app.stage;
        stage.setMaximized(maximize);
        if(maximize){
            maximizedRectangle.setVisible(true);
            ((Region) stage.getScene().getRoot()).setPadding(new Insets(0));
            maximizeButton.lookupAll(".maximizeRectangle").forEach(node -> {
                node.getStyleClass().add("maximized");
            });
            java.awt.Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
            stage.setWidth(bounds.getWidth());
            stage.setHeight(bounds.getHeight());
            return;
        }
        maximizedRectangle.setVisible(false);
        ((Region) stage.getScene().getRoot()).setPadding(new Insets(20,20,20,20));
        maximizeButton.lookupAll(".maximizeRectangle").forEach(node -> {
            node.getStyleClass().remove("maximized");
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Run.app.scene.getRoot().getLayout
        // Movimentação da barra de título da janela
        barraDeTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barraDeTitulo.setOnMouseDragged(mouseEvent -> {
            Stage stage = Run.app.stage;
            double size = stage.getWidth();
            if(stage.isMaximized()) {
                setMaximized(false);
                x = x * stage.getWidth()/size;
            }
            stage.setX(mouseEvent.getScreenX() - x);
            stage.setY(mouseEvent.getScreenY() - y);
        });

        // ações da barra de título
        barraDeTitulo.setOnMouseReleased(mouseEvent -> {
            if(mouseEvent.getScreenY() == 0) setMaximized(true);
        });

        barraDeTitulo.setOnMouseClicked(mouseEvent -> {
            if(mouseEvent.getClickCount() >= 2) maximizeButton.fire();
        });

        closeButton.setOnAction(actionEvent -> {
            Platform.exit();
        });

        minimizeButton.setOnAction(actionEvent -> {
            Run.app.stage.setIconified(true);
        });

        maximizeButton.setOnAction(actionEvent -> {
            Stage stage = Run.app.stage;
            setMaximized(!stage.isMaximized());
        });
    }
}
