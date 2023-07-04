package me.julie.wordle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;

public class WordleView {
    FXMLLoader loader;

    public WordleView() {
        this.loader = new FXMLLoader(Main.class.getResource("/wordle-board.fxml"));
    }

    public Scene load() throws IllegalStateException {
        try {
            return this.loader.load();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to load FXML file", e);
        }
    }
}
