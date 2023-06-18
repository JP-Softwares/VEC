package com.jp.visao;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class VeiculosEdit implements Initializable {

    
    @FXML
    public ComboBox<?> comboBoxModelo;

    @FXML
    public ComboBox<?> comboBoxSituacaoDoVeiculo;

    @FXML
    public ComboBox<?> comboBoxTipoDoCombustivel;

    @FXML
    public ComboBox<?> comboBoxproprietario;

    @FXML
    public TextField textFieldAnoDeFabricacao;

    @FXML
    public TextField textFieldAnoDoModelo;

    @FXML
    public TextField textFieldPlaca;

    @FXML
    public TextField textFieldQuilometragemAtual;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.veiculosEdit = this;
    }
}
