package me.julie.wordle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    static Scene scene;

    @Override
    public void start(Stage stage) {
        WordleView view = new WordleView();
        scene = view.load();
        scene.setOnKeyPressed(e -> WordleController.getInstance().handleKeys(e));
        stage.setTitle("Wordle");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}