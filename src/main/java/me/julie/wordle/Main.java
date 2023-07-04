package me.julie.wordle;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        WordleView view = new WordleView();
        stage.setTitle("Wordle");
        stage.setScene(view.load());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}