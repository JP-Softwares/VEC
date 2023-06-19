package com.jp.visao;

import com.jp.modelos.*;
import com.jp.tools.GerarPDF;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TelaGastos implements Initializable {


    @FXML
    private AnchorPane PanefundoGastos;

    @FXML
    private ScrollPane scrollPaneGastos;

    @FXML
    private TextField searchBarGastos;

    @FXML
    private VBox gastosItems;

    @FXML
    private Label labelTitle;

    @FXML
    private TextField textFieldAno;

    int idAtual = 0;

    public Veiculo veiculo = null;

    HashMap<Integer, Gastos> gastosHashMap = null;

    public void addItem(Node item){
        try{
            //if(veiculoItems.getChildren().size() > 0) veiculoItems.getChildren().remove(0);

            item.setId("itemGasto");

            gastosItems.getChildren().add(item);
            PanefundoGastos.setMinHeight(127 + 100 * gastosItems.getChildren().size() + 15 * gastosItems.getChildren().size());
            //JFXScrollPane.smoothScrolling(scrollPane);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void editItemGasto(int id){
        try{
            AnchorPane editGasto = (AnchorPane) getScene("GastosEdit.fxml");

            idAtual = id;

            Gastos gastos = gastosHashMap.get(id);

            ComboBox comboBoxTipoDeGasto = (ComboBox) editGasto.lookup("#comboBoxTipoDeGasto");

            Collection<TipoDeGastos> tipoDeGastos = Run.tipoDeGastosControle.listar();
            ArrayList<String> nomeDosGastos = new ArrayList<>();
            tipoDeGastos.forEach(currentTipoDeGasto -> {
                nomeDosGastos.add(currentTipoDeGasto.getNome());
            });
            comboBoxTipoDeGasto.getItems().addAll(nomeDosGastos);
            comboBoxTipoDeGasto.setValue(gastos.getTipoDeGastos().getNome());


            ((TextField) editGasto.lookup("#textFieldDescricao")).setText(gastos.getDescricao());
            ((TextField) editGasto.lookup("#textFieldValor")).setText(gastos.getValor() + "");
            ((DatePicker)editGasto.lookup("#datePicker")).setValue(gastos.getData().toLocalDate());

            showNewItem("Editar Gasto", editGasto, (m) -> alterarGasto());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showNewItem(String title, Node anchorPane, ActionListener method){
        Run.telaPrincipal.setEditWindow(title, anchorPane, method);
    }

    @FXML
    void filtrarGasto(ActionEvent event) {
        try {
            ArrayList<Gastos> gastos = Run.gastosControle.filtrarGastos(gastosHashMap.values(), searchBarGastos.getText());

            gastosItems.getChildren().clear();
            gastos.forEach(gasto -> {

                AnchorPane itemGasto = (AnchorPane) getScene("Item.fxml");
                ((Label) itemGasto.lookup("#title")).setText(gasto.getTipoDeGastos().getNome());
                ((Label) itemGasto.lookup("#description")).setText("Descrição: " + gasto.getDescricao()
                        + "\nValor: " + gasto.getValor() + "   |   " + arrumarData(gasto.getData()));
                AnchorPane.setLeftAnchor((Label) itemGasto.lookup("#title"), 25.0);
                AnchorPane.setLeftAnchor((Label) itemGasto.lookup("#description"), 25.0);
                ((Label) itemGasto.lookup("#id")).setText(gasto.getId() + "");

                itemGasto.lookup("FlowPane").setVisible(false);

                addItem(itemGasto);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void newItemGasto(ActionEvent event) {
        try{
            AnchorPane editGasto = (AnchorPane) getScene("GastosEdit.fxml");

            ComboBox comboBoxTipoDeGasto = (ComboBox) editGasto.lookup("#comboBoxTipoDeGasto");

            Collection<TipoDeGastos> tipoDeGastos = Run.tipoDeGastosControle.listar();
            ArrayList<String> nomeDasMarcas = new ArrayList<>();
            tipoDeGastos.forEach(currentMarca -> {
                nomeDasMarcas.add(currentMarca.getNome());
            });
            comboBoxTipoDeGasto.getItems().addAll(nomeDasMarcas);

            showNewItem("Novo Gasto", editGasto, (m) -> inserirGasto());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void voltar(ActionEvent event) {
        Run.telaPrincipal.botaoVeiculos.fire();
    }

    public void inserirGasto(){
        try {
            Gastos gastos = new Gastos();
            GastosEdit edit = Run.gastosEdit;
            gastos.setTipoDeGastos(Run.tipoDeGastosControle.buscar(edit.comboBoxTipoDeGasto.getValue() + ""));
            gastos.setDescricao(edit.textFieldDescricao.getText());
            gastos.setValor(Double.parseDouble(edit.textFieldValor.getText()));
            gastos.setData(Date.valueOf(edit.datePicker.getValue().toString()));
            gastos.setVeiculo(veiculo);

            Run.gastosControle.incluir(gastos);

            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void alterarGasto(){
        try {
            Gastos gastos = new Gastos();
            GastosEdit edit = Run.gastosEdit;
            gastos.setId(idAtual);
            gastos.setTipoDeGastos(Run.tipoDeGastosControle.buscar(edit.comboBoxTipoDeGasto.getValue() + ""));
            gastos.setDescricao(edit.textFieldDescricao.getText());
            gastos.setValor(Double.parseDouble(edit.textFieldValor.getText()));
            gastos.setData(Date.valueOf(edit.datePicker.getValue().toString()));
            gastos.setVeiculo(veiculo);

            Run.gastosControle.alterar(gastos);

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
        labelTitle.setText("Gastos - " + veiculo.getModelo().getNome() + " | " + veiculo.getPlaca());
        try {
            ArrayList<Gastos> gastos = Run.gastosControle.listar(veiculo);

            gastosHashMap = new HashMap<>();

            gastosItems.getChildren().clear();
            gastos.forEach(gasto -> {

                AnchorPane itemGasto = (AnchorPane) getScene("Item.fxml");
                ((Label) itemGasto.lookup("#title")).setText(gasto.getTipoDeGastos().getNome());
                ((Label) itemGasto.lookup("#description")).setText("Descrição: " + gasto.getDescricao()
                + "\nValor: " + gasto.getValor() + "   |   " + arrumarData(gasto.getData()));
                AnchorPane.setLeftAnchor((Label) itemGasto.lookup("#title"), 25.0);
                AnchorPane.setLeftAnchor((Label) itemGasto.lookup("#description"), 25.0);
                ((Label) itemGasto.lookup("#id")).setText(gasto.getId() + "");

                itemGasto.lookup("FlowPane").setVisible(false);

                addItem(itemGasto);
                gastosHashMap.put(gasto.getId(), gasto);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String arrumarData(Date data){
        String dia = data.toLocalDate().getDayOfMonth() + "";
        if(dia.length() < 2){
            dia = "0"+dia;
        }
        String mes = data.toLocalDate().getMonthValue() + "";
        if(mes.length() < 2){
            mes = "0"+mes;
        }
        String ano = data.toLocalDate().getYear() + "";
        return dia+ "/" + mes + "/" + ano;
    }

    @FXML
    void gerarGrafico(ActionEvent event) {
        Stage novaTela = new Stage();
        novaTela.setTitle("Gráfico de " + veiculo.getModelo().getNome() + " da placa " + veiculo.getPlaca() + " - " + Integer.parseInt(textFieldAno.getText()));
        novaTela.setOnShown(windowEvent -> {
            Run.telaGrafico.borderPane.setCenter(Run.telaGrafico.buildBarChartMensal());
        });
        novaTela.setScene(new Scene((Parent) getScene("TelaGrafico.fxml")));
        Run.telaGrafico.veiculo = veiculo;
        Run.telaGrafico.ano = Integer.parseInt(textFieldAno.getText());
        novaTela.show();
    }

    @FXML
    void gerarRelatorioMensal(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar Relatório");


        GerarPDF.gerarPDFMes(
                dc.showDialog(Run.app.stage.getScene().getWindow()).getAbsolutePath() + "/Relatório Mensal " + veiculo.getModelo().getNome() +" - " + veiculo.getPlaca() + ".pdf",
                veiculo, Integer.parseInt(textFieldAno.getText()));
    }

    @FXML
    void gerarRelatorioPorTipo(ActionEvent event) {
        DirectoryChooser dc = new DirectoryChooser();
        dc.setTitle("Salvar Relatório");


        GerarPDF.gerarPDFTipo(
                dc.showDialog(Run.app.stage.getScene().getWindow()).getAbsolutePath() + "/Relatório por Tipo " + veiculo.getModelo().getNome() +" - " + veiculo.getPlaca() + ".pdf",
                veiculo, Integer.parseInt(textFieldAno.getText()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Run.telaGastos = this;
    }
}
