package com.jp.visao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ProprietariosEdit implements Initializable {

    @FXML
    public ComboBox<?> comboBoxCategoriaCNH;

    @FXML
    public TextField textFieldCPF;

    @FXML
    public TextField textFieldDDD;

    @FXML
    public TextField textFieldDDI;

    @FXML
    public TextField textFieldEmail;

    @FXML
    public TextField textFieldNome;

    @FXML
    public TextField textFieldNumero;

    @FXML
    public TextField textFieldNumeroCNH;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.proprietariosEdit = this;
    }
}
