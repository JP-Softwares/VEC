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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.awt.event.ActionListener;
import java.net.URL;
import java.security.spec.ECField;
import java.util.Arrays;
import java.util.Collections;
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
    private Pane blurPane;

    @FXML
    public Button botaoVeiculos;

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

    @FXML
    private AnchorPane editPane;

    @FXML
    private Label labelTitleEdit;

    @FXML
    private ScrollPane scrollPaneEdit;

    private boolean lateralMenuOpen = false;

    private double x, y;

    Timeline transicao = new Timeline();

    enum TelaAtual {
        TELAHOME, TELAVEICULOS, TELAPROPRIETARIOS, TELATIPODEGASTO
    };

    ActionListener metodoEditExit;

    TelaAtual telaAtual = TelaAtual.TELAHOME;
    
    String caminhoPadrao = "/com/jp/";


    public void setEditWindow(String title, Node anchorPane, ActionListener method){
        blurPane.setViewOrder(-1); //funcionou
        editPane.setViewOrder(-1);
        this.metodoEditExit = method;
        labelTitleEdit.setText(title);
        scrollPaneEdit.setContent(anchorPane);
        editPane.setVisible(true);
        blurPane.setVisible(true);

        transicao = new Timeline(
                new KeyFrame(Duration.seconds(0.15), new KeyValue(editPane.opacityProperty(),1, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(blurPane.opacityProperty(),0.5, Interpolator.LINEAR))
        );

        transicao.play();

        //JFXScrollPane.smoothScrolling(scrollPaneEdit);
    }

    @FXML
    void closeEdit(ActionEvent event) {

        transicao = new Timeline(
                new KeyFrame(Duration.seconds(0.15), new KeyValue(editPane.opacityProperty(),0, Interpolator.LINEAR)),
                new KeyFrame(Duration.seconds(0.15), new KeyValue(blurPane.opacityProperty(),0, Interpolator.LINEAR))
        );

        transicao.setOnFinished(actionEvent -> {
            editPane.setVisible(false);
            blurPane.setVisible(false);
        });

        transicao.play();
    }


    @FXML
    void cancelEdit(ActionEvent event) {
        closeEdit(event);
    }

    @FXML
    void saveEdit(ActionEvent event) {
        closeEdit(event);
        metodoEditExit.actionPerformed(null);
    }

    @FXML
    void setBlurPaneHidden(MouseEvent event) {
        if(lateralMenuOpen) menuHamburguer.fire();
    }

    @FXML
    void lateralMenu(ActionEvent event) {
        double width = paneLeft.getWidth();
        int aumento = 3;
        blurPane.setViewOrder(0);
        if(transicao.getStatus() != Animation.Status.RUNNING){
            if(!lateralMenuOpen){

                transicao = new Timeline(
                        new KeyFrame(Duration.seconds(0.15), new KeyValue(paneLeft.prefWidthProperty(),width * aumento, Interpolator.LINEAR)),
                        new KeyFrame(Duration.seconds(0.15), new KeyValue(blurPane.opacityProperty(),0.5, Interpolator.LINEAR))
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
                blurPane.setVisible(true);
                transicao.play();
                lateralMenuOpen = true;
                return;
            }


            transicao = new Timeline(
                    //new KeyFrame(Duration.seconds(0.2), new KeyValue(paneLeft.translateXProperty(), 30, Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(0.15), new KeyValue(paneLeft.prefWidthProperty(), width / aumento, Interpolator.LINEAR)),
                    new KeyFrame(Duration.seconds(0.15), new KeyValue(blurPane.opacityProperty(),0.0, Interpolator.LINEAR))
            );

            botaoVeiculos.setPrefWidth(width / aumento);
            vBoxLeft.setPrefWidth(width / aumento);
            botaoVeiculos.setText("");
            botaoHome.setText("");
            botaoProprietarios.setText("");
            botaoTipoDeGasto.setText("");
            botaoVeiculos.setAlignment(Pos.CENTER_LEFT);
            transicao.setOnFinished(actionEvent -> {
                blurPane.setVisible(false);
            });
            transicao.play();
            lateralMenuOpen = false;
        }
    }

    private void setMaximized(boolean maximize){
        double width = centerPane.getWidth();
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

            ((Pane)centerPane.getChildren().get(0)).setPrefWidth(centerPane.getWidth() + 40);
            ((Pane)centerPane.getChildren().get(0)).setPrefHeight(centerPane.getHeight() - 10);
            if(telaAtual == TelaAtual.TELAHOME){
                Run.telaHome.imagem.setFitWidth(centerPane.getWidth() + 40);
                Run.telaHome.imagem.setFitHeight(centerPane.getHeight() - 10);
                editPane.setLayoutX(stage.getWidth()/2 - editPane.getWidth()/2);
                editPane.setLayoutY(stage.getHeight()/2 - editPane.getHeight()/2 - 20);
            }
            alignTabPaneHeader();

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

        editPane.setLayoutX(stage.getWidth()/2 - editPane.getWidth()/2);
        editPane.setLayoutY(stage.getHeight()/2 - editPane.getHeight()/2 - 30);

        alignTabPaneHeader();
    }

    @FXML
    private void showHome(ActionEvent event) {
        telaAtual = TelaAtual.TELAHOME;
        setBotaoSelecionadoSideBar();

        setScene("TelaHome.fxml");
    }

    @FXML
    private void showProprietarios(ActionEvent event) {
        telaAtual = TelaAtual.TELAPROPRIETARIOS;
        setBotaoSelecionadoSideBar();
        setScene("TelaProprietarios.fxml");
    }

    @FXML
    private void showTipoDeGasto(ActionEvent event) {
        telaAtual = TelaAtual.TELATIPODEGASTO;
        setBotaoSelecionadoSideBar();
        setScene("TelaTipoDeGasto.fxml");
    }

    @FXML
    private void showVeiculos(ActionEvent event) {
        telaAtual = TelaAtual.TELAVEICULOS;
        setBotaoSelecionadoSideBar();
        setScene("TelaVeiculos.fxml");
        alignTabPaneHeader();
    }

    public void alignTabPaneHeader(){
        try {
            if(telaAtual == TelaAtual.TELAVEICULOS){
                TabPane tabPane = ((TabPane)((AnchorPane) centerPane.getChildren().get(0)).getChildren().get(0));
                tabPane.setTabMinWidth((Run.app.stage.getWidth() - Run.app.stage.getWidth() * 5/100) / tabPane.getTabs().size() - 40);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setScene(String fxml){
        try{
            if(centerPane.getChildren().size() > 0) centerPane.getChildren().remove(0);

            centerPane.getChildren().add(FXMLLoader.load(getClass().getResource(fxml)));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setBotaoSelecionadoSideBar(){
        botaoHome.setId("");
        ((ImageView) botaoHome.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/home.png").toString()));
        botaoVeiculos.setId("");
        ((ImageView) botaoVeiculos.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/veiculos.png").toString()));
        botaoProprietarios.setId("");
        ((ImageView) botaoProprietarios.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/proprietarios.png").toString()));
        botaoTipoDeGasto.setId("");
        ((ImageView) botaoTipoDeGasto.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/gastos.png").toString()));

        switch(telaAtual){
            case TELAHOME:
                botaoHome.setId("selectedButtonSideBar");
                ((ImageView) botaoHome.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/home_black.png").toString()));
                break;
            case TELAVEICULOS:
                botaoVeiculos.setId("selectedButtonSideBar");
                ((ImageView) botaoVeiculos.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/veiculos_black.png").toString()));
                break;
            case TELAPROPRIETARIOS:
                botaoProprietarios.setId("selectedButtonSideBar");
                ((ImageView) botaoProprietarios.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/proprietarios_black.png").toString()));
                break;
            case TELATIPODEGASTO:
                botaoTipoDeGasto.setId("selectedButtonSideBar");
                ((ImageView) botaoTipoDeGasto.lookup("ImageView")).setImage(new Image(getClass().getResource(caminhoPadrao + "icones/gastos_black.png").toString()));
                break;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaPrincipal = this;
        Run.app.stage.setOnShown(windowEvent -> {
            showHome(null);
            //setScene("TelaHome.fxml");
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
