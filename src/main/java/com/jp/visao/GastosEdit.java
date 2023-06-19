package com.jp.visao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GastosEdit implements Initializable {

    @FXML
    public ComboBox<?> comboBoxTipoDeGasto;

    @FXML
    public DatePicker datePicker;

    @FXML
    public TextField textFieldDescricao;

    @FXML
    public TextField textFieldValor;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.gastosEdit = this;
    }
}
