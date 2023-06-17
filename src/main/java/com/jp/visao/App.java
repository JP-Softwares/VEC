package com.jp.visao;

import atlantafx.base.theme.PrimerDark;
import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    public Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        Run.app = this;
        this.stage = stage;

        stage.initStyle(StageStyle.TRANSPARENT);

        Application.setUserAgentStylesheet(new PrimerDark().getUserAgentStylesheet());

        scene = new Scene(loadFXML("TelaPrincipal"));
        scene.setFill(Color.TRANSPARENT);
        ((Region) scene.getRoot()).setPadding(new Insets(20,20,20,20));
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}