module me.julie.wordle {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens me.julie.wordle to javafx.fxml;
    exports me.julie.wordle;
}