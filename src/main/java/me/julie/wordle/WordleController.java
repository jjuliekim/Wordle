package me.julie.wordle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class WordleController {
    private String answerString;
    private char[] answerArray = new char[5];
    private char[] guessArray = new char[5];
    @FXML
    private Label endGameLabel;
    @FXML
    private Label answerLabel;
    @FXML
    private VBox mainVbox;
    @FXML
    private GridPane grid;
    @FXML
    private Button newGameButton;

    @FXML
    public void initialize() {
        newGameButton.setOnAction(e -> handleNewGame());
        mainVbox.setStyle("-fx-background-color: #5d819d");
        run();
    }

    public void run() {
        generateAnswer();
        setGrid();
    }

    private void handleNewGame() {
        endGameLabel.setText("");
        answerLabel.setText("");
        grid.getChildren().clear();
        run();
    }

    private void generateAnswer() {
        final Scanner fileScanner = new Scanner(
                Objects.requireNonNull(WordleController.class.getClassLoader().getResourceAsStream("words.txt")));
        ArrayList<String> words = new ArrayList<>();
        while (fileScanner.hasNext()) {
            words.add(fileScanner.nextLine());
        }
        Random random = new Random();
        int num = random.nextInt(words.size());
        answerString = words.get(num).toLowerCase();
        for (int i = 0; i < 5; i++) {
            answerArray[i] = answerString.charAt(i);
        }
        System.out.println(answerString);
    }

    private void setGrid() {
        for (int i = 0; i < 5; i++) {
            VBox vbox = new VBox();
            vbox.setAlignment(Pos.CENTER);
            vbox.setStyle("-fx-background-color: #8bbbe1");
            Label label = new Label();
            label.setText("?");
            label.setFont(Font.font("Courier New", 30));
            vbox.getChildren().add(label);
            grid.add(vbox, i, 0);
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 1; j < 6; j++) {
                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.setStyle("-fx-background-color: #254b68");
                Label label = new Label();
                vbox.getChildren().add(label);
                grid.add(vbox, i, j);
            }
        }
    }
}