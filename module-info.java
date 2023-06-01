module com.jp.visao {
    requires javafx.controls;
    requires javafx.fxml;
    requires atlantafx.base;

    opens com.jp.visao to javafx.fxml;
    exports com.jp.visao;
}