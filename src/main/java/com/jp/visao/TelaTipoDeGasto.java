package com.jp.visao;

import com.jp.modelos.CategoriaCNH;
import com.jp.modelos.Proprietario;
import com.jp.modelos.Telefone;
import com.jp.modelos.TipoDeGastos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TelaTipoDeGasto implements Initializable {


    @FXML
    private AnchorPane PanefundoTipoDeGasto;

    @FXML
    private ScrollPane scrollPaneTipoDeGasto;

    @FXML
    private TextField searchBarTipoDeGasto;

    @FXML
    private VBox TipoDeGastoItems;

    int idAtual = 0;

    HashMap<Integer, TipoDeGastos> tipoDeGastosHashMap = null;

    public void addItem(Node item){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            item.setId("itemTipoDeGasto");

            TipoDeGastoItems.getChildren().add(item);
            PanefundoTipoDeGasto.setMinHeight(127 + 100 * TipoDeGastoItems.getChildren().size() + 15 * TipoDeGastoItems.getChildren().size());
            //JFXScrollPane.smoothScrolling(scrollPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editItemTipoDeGasto(int id){

        try {
            AnchorPane editProprietario = (AnchorPane) getScene("ProprietariosEdit.fxml");

            idAtual = id;

            Proprietario proprietario = Run.proprietarioControle.buscar(id);

            ComboBox comboBoxProprietario = (ComboBox) editProprietario.lookup("#comboBoxCategoriaCNH");

            comboBoxProprietario.getItems().addAll(CategoriaCNH.values());
            comboBoxProprietario.setValue(proprietario.getCategoria().toString());

            ((TextField) editProprietario.lookup("#textFieldNome")).setText(proprietario.getNome());
            ((TextField) editProprietario.lookup("#textFieldCPF")).setText(proprietario.getCPF());
            ((TextField) editProprietario.lookup("#textFieldDDI")).setText(proprietario.getTelefone().getDDI() + "");
            ((TextField) editProprietario.lookup("#textFieldDDD")).setText(proprietario.getTelefone().getDDD() + "");
            ((TextField) editProprietario.lookup("#textFieldNumero")).setText(proprietario.getTelefone().getNumero() + "");
            ((TextField) editProprietario.lookup("#textFieldEmail")).setText(proprietario.getEmail());
            ((TextField) editProprietario.lookup("#textFieldNumeroCNH")).setText(proprietario.getCNH());

            showNewItem("Novo Proprietario", editProprietario, (m) -> alterarTipoDeGasto());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showNewItem(String title, Node anchorPane, ActionListener method){
        Run.telaPrincipal.setEditWindow(title, anchorPane, method);
    }

    @FXML
    void filtrarTipoDeGasto(ActionEvent event) {
//
//        try {
//            ArrayList<Proprietario> proprietarios = Run.proprietarioControle.filtrarProprietario(proprietarioHashMap.values(), searchBarTipoDeGasto.getText());
//
//            TipoDeGastoItems.getChildren().clear();
//
//            proprietarios.forEach(proprietario -> {
//
//                AnchorPane itemProprietario = (AnchorPane) getScene("Item.fxml");
//                ((Label) itemProprietario.lookup("#title")).setText(proprietario.getNome() + "  -  " + Run.proprietarioControle.imprimeCPF(proprietario.getCPF()));
//                ((Label) itemProprietario.lookup("#description")).setText("E-mail: " + proprietario.getEmail()
//                        + " | Telefone: +" + proprietario.getTelefone().getDDI() + " (" + proprietario.getTelefone().getDDD() + ") " + proprietario.getTelefone().getNumero()
//                        + "\nCNH: " + proprietario.getCNH() + " - " + proprietario.getCategoria().toString() + " | Número de Carros: " + proprietario.getNumeroDeCarros());
//                AnchorPane.setLeftAnchor((Label) itemProprietario.lookup("#title"), 25.0);
//                AnchorPane.setLeftAnchor((Label) itemProprietario.lookup("#description"), 25.0);
//                ((Label) itemProprietario.lookup("#id")).setText(proprietario.getId() + "");
//
//                itemProprietario.lookup("FlowPane").setVisible(false);
//
//                addItem(itemProprietario);
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    @FXML
    void newItemTipoDeGasto(ActionEvent event) {
        AnchorPane editProprietario = (AnchorPane) getScene("ProprietariosEdit.fxml");

        ComboBox comboBoxProprietario = (ComboBox) editProprietario.lookup("#comboBoxCategoriaCNH");

        comboBoxProprietario.getItems().addAll(CategoriaCNH.values());

        showNewItem("Novo Proprietario", editProprietario, (m) -> inserirTipoDeGasto());
    }

    public void inserirTipoDeGasto(){
        try {
            Proprietario proprietario = new Proprietario();
            ProprietariosEdit proprietariosEdit = Run.proprietariosEdit;
            proprietario.setCPF(proprietariosEdit.textFieldCPF.getText());
            proprietario.setNome(proprietariosEdit.textFieldNome.getText());
            Telefone telefone = new Telefone();
            telefone.setDDI(Integer.parseInt(proprietariosEdit.textFieldDDI.getText()));
            telefone.setDDD(Integer.parseInt(proprietariosEdit.textFieldDDD.getText()));
            telefone.setNumero(Integer.parseInt(proprietariosEdit.textFieldNumero.getText()));

            proprietario.setTelefone(telefone);

            proprietario.setEmail(proprietariosEdit.textFieldEmail.getText());
            proprietario.setCNH(proprietariosEdit.textFieldNumeroCNH.getText());
            proprietario.setCategoria(CategoriaCNH.valueOf(proprietariosEdit.comboBoxCategoriaCNH.getValue() + ""));

            Run.proprietarioControle.incluir(proprietario);

            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarTipoDeGasto(){
        try {
            Proprietario proprietario = new Proprietario();
            ProprietariosEdit proprietariosEdit = Run.proprietariosEdit;
            proprietario.setId(idAtual);
            proprietario.setCPF(proprietariosEdit.textFieldCPF.getText());
            proprietario.setNome(proprietariosEdit.textFieldNome.getText());
            Telefone telefone = new Telefone();
            telefone.setDDI(Integer.parseInt(proprietariosEdit.textFieldDDI.getText()));
            telefone.setDDD(Integer.parseInt(proprietariosEdit.textFieldDDD.getText()));
            telefone.setNumero(Integer.parseInt(proprietariosEdit.textFieldNumero.getText()));

            proprietario.setTelefone(telefone);

            proprietario.setEmail(proprietariosEdit.textFieldEmail.getText());
            proprietario.setCNH(proprietariosEdit.textFieldNumeroCNH.getText());
            proprietario.setCategoria(CategoriaCNH.valueOf(proprietariosEdit.comboBoxCategoriaCNH.getValue() + ""));
            //proprietario.setNumeroDeCarros(proprietarioHashMap.get(idAtual).getNumeroDeCarros());

            Run.proprietarioControle.alterar(proprietario);

            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Node getScene(String fxml){
        try {
            return FXMLLoader.load(getClass().getResource(fxml));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void listar(){
        try {
            ArrayList<TipoDeGastos> tipoDeGastosArrayList = Run.tipoDeGastosControle.listar();
            tipoDeGastosHashMap = new HashMap<>();

            TipoDeGastoItems.getChildren().clear();
            tipoDeGastosArrayList.forEach(tipoDeGastos -> {

                AnchorPane itemTipoDeGasto = (AnchorPane) getScene("Item.fxml");
                ((Label) itemTipoDeGasto.lookup("#title")).setText(tipoDeGastos.getNome());
                ((Label) itemTipoDeGasto.lookup("#description")).setText("");
                ((Label) itemTipoDeGasto.lookup("#id")).setText(tipoDeGastos.getId() + "");

                try{
                    ((ImageView)itemTipoDeGasto.lookup("#imagem")).setImage(new Image(getClass().getResource(tipoDeGastos.getIcone()).toString()));
                }catch (Exception e){
                    e.printStackTrace();
                }

                addItem(itemTipoDeGasto);
                tipoDeGastosHashMap.put(tipoDeGastos.getId(), tipoDeGastos);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaTipoDeGasto = this;

        listar();
    }
}
