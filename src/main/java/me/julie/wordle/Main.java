package me.julie.wordle;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        WordleController controller = new WordleController();
        WordleView view = new WordleView();
        stage.setTitle("Wordle");
        stage.setScene(view.load());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}