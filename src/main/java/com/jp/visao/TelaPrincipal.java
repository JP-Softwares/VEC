package com.jp.visao;

//import javafx.animation.ScaleTransition;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

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

    @FXML
    private Pane paneLeft;

    @FXML
    private Pane centerPane;

    @FXML
    private Button botaoVeiculos;

    @FXML
    private Button botaoHome;

    @FXML
    private Button botaoProprietarios;

    @FXML
    private Button botaoTipoDeGasto;

    @FXML
    private VBox vBoxLeft;

    @FXML
    private Button menuHamburguer;

    private boolean lateralMenuOpen = false;

    private double x, y;

    Timeline transicao = new Timeline();

    enum TelaAtual {
        TELAHOME
    };

    TelaAtual telaAtual = TelaAtual.TELAHOME;

    @FXML
    void lateralMenu(ActionEvent event) {
        double width = paneLeft.getWidth();
        int aumento = 3;
        if(transicao.getStatus() != Animation.Status.RUNNING){
            if(!lateralMenuOpen){

                transicao = new Timeline(
                        new KeyFrame(Duration.seconds(0.15), new KeyValue(paneLeft.prefWidthProperty(),width * aumento, Interpolator.LINEAR))
                );

                transicao.setOnFinished(actionEvent -> {
                    vBoxLeft.setPrefWidth(width * aumento);
                    botaoVeiculos.setText("Veículos");
                    botaoHome.setText("Home");
                    botaoProprietarios.setText("Proprietários");
                    botaoTipoDeGasto.setText("Tipo de Gasto");
                    vBoxLeft.lookupAll(".lateralButtons").forEach(node -> {
                        if(node != menuHamburguer){
                            ((Button)node).setPrefWidth(width * aumento);
                            ((Button)node).setAlignment(Pos.CENTER_LEFT);
                        }
                    });
                });

                transicao.play();
                lateralMenuOpen = true;
                return;
            }


            transicao = new Timeline(
                    //new KeyFrame(Duration.seconds(0.2), new KeyValue(paneLeft.translateXProperty(), 30, Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(0.15), new KeyValue(paneLeft.prefWidthProperty(), width / aumento, Interpolator.LINEAR))
            );

            botaoVeiculos.setPrefWidth(width / aumento);
            vBoxLeft.setPrefWidth(width / aumento);
            botaoVeiculos.setText("");
            botaoHome.setText("");
            botaoProprietarios.setText("");
            botaoTipoDeGasto.setText("");
            botaoVeiculos.setAlignment(Pos.CENTER_LEFT);

            transicao.play();
            lateralMenuOpen = false;
        }
    }

    private void setMaximized(boolean maximize){
        double width = centerPane.getWidth();
        Stage stage = Run.app.stage;
        System.out.println(centerPane.getHeight());
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

            System.out.println(centerPane.getHeight());
            ((Pane)centerPane.getChildren().get(0)).setPrefWidth(centerPane.getWidth() + 40);
            ((Pane)centerPane.getChildren().get(0)).setPrefHeight(centerPane.getHeight() - 10);
            if(telaAtual == TelaAtual.TELAHOME){
                Run.telaHome.imagem.setFitWidth(centerPane.getWidth() + 40);
                Run.telaHome.imagem.setFitHeight(centerPane.getHeight() - 10);
            }
            System.out.println(Run.telaHome.paneRoot.getHeight());
            return;
        }
        maximizedRectangle.setVisible(false);
        ((Region) stage.getScene().getRoot()).setPadding(new Insets(20,20,20,20));
        maximizeButton.lookupAll(".maximizeRectangle").forEach(node -> {
            node.getStyleClass().remove("maximized");
        });

        ((Pane)centerPane.getChildren().get(0)).setPrefWidth(centerPane.getWidth() - 40);
        ((Pane)centerPane.getChildren().get(0)).setPrefHeight(centerPane.getHeight() - 40);

        if(telaAtual == TelaAtual.TELAHOME){
            Run.telaHome.imagem.setFitWidth(centerPane.getWidth() - 40);
            Run.telaHome.imagem.setFitHeight(centerPane.getHeight() - 40);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaPrincipal = this;

        Run.app.stage.setOnShown(windowEvent -> {
            try{
                centerPane.getChildren().add(FXMLLoader.load(getClass().getResource("TelaHome.fxml")));
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        // Movimentação da barra de título da janela
        barraDeTitulo.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        barraDeTitulo.setOnMouseDragged(mouseEvent -> {
            Stage stage = Run.app.stage;
            double sizeX = stage.getWidth();
            double sizeY = stage.getHeight();
            if(stage.isMaximized()) {
                setMaximized(false);
                x = x * stage.getWidth()/sizeX;
                y += 20;
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
