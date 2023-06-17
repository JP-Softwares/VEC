package com.jp.visao;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class VeiculosEdit implements Initializable {


    public String url = "";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.veiculosEdit = this;
    }
}
