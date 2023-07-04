package me.julie.wordle;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class WordleController {
    private String answerString;
    private ArrayList<String> answerArray = new ArrayList<>(5);
    private ArrayList<String> guessArray = new ArrayList<>(5);
    private int numGuesses;
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
    private static WordleController controller;
    private ArrayList<String> dictionary = new ArrayList<>();
    private Scanner scanner;

    public static WordleController getInstance() {
        return controller;
    }

    public WordleController() {
        controller = this;
    }

    @FXML
    public void initialize() {
        newGameButton.setFocusTraversable(false);
        newGameButton.setOnAction(e -> handleNewGame());
        mainVbox.setStyle("-fx-background-color: #5d819d");
        scanner = new Scanner(Objects.requireNonNull(WordleController.class.getClassLoader()
                .getResourceAsStream("dictionary.txt")));
        dictionary = new ArrayList<>();
        while (scanner.hasNext()) {
            dictionary.add(scanner.nextLine());
        }
        run();
    }

    public void run() {
        numGuesses = 0;
        generateAnswer();
        setGrid();
    }

    private void handleNewGame() {
        grid.getChildren().clear();
        answerArray = new ArrayList<>(5);
        guessArray = new ArrayList<>(5);
        endGameLabel.setText("");
        answerLabel.setText("");
        run();
    }

    public void handleKeys(KeyEvent key) {
        if (key.getCode().isLetterKey()) {
            handleLetterKey(key);
        }
        if (key.getCode() == KeyCode.ENTER) {
            handleEnterKey();
        }
        if (key.getCode() == KeyCode.BACK_SPACE) {
            handleBackspaceKey();
        }
    }

    // letter input
    private void handleLetterKey(KeyEvent key) {
        String letter = key.getText();
        if (guessArray.size() < 5) {
            guessArray.add(letter);
            VBox vbox = (VBox) grid.getChildren().get(guessArray.size() - 1 + numGuesses * 5);
            Label label = (Label) vbox.getChildren().get(0);
            label.setText(letter);
        }

    }

    // player's word guess
    private void handleEnterKey() {
        if (guessArray.size() == 5) {
            StringBuilder guessString = new StringBuilder();
            for (String letter : guessArray) {
                guessString.append(letter);
            }
            if (!dictionary.contains(guessString.toString())) {
                endGameLabel.setText("Invalid word");
                guessArray = new ArrayList<>(5);
                for (int i = 0; i < 5; i++) {
                    VBox vbox = (VBox) grid.getChildren().get(numGuesses * 5 + i);
                    vbox.setStyle("-fx-background-color: #8bbbe1");
                    Label label = (Label) vbox.getChildren().get(0);
                    label.setText("?");
                }
                return;
            }
            endGameLabel.setText("");
            compareAnswer();
            numGuesses++;
            if (guessArray.equals(answerArray)) {
                endGameLabel.setText("You win!");
                return;
            }
            if (numGuesses == 6) {
                endGameLabel.setText("You lose!");
                answerLabel.setText("The answer was " + answerString);
                return;
            }
            guessArray = new ArrayList<>(5);
            for (int i = 0; i < 5; i++) {
                VBox vbox = (VBox) grid.getChildren().get(numGuesses * 5 + i);
                vbox.setStyle("-fx-background-color: #8bbbe1");
                Label label = (Label) vbox.getChildren().get(0);
                label.setText("?");
            }
        }
    }

    // delete last letter of guess
    private void handleBackspaceKey() {
        if (guessArray.size() > 0) {
            guessArray.remove(guessArray.size() - 1);
            VBox vbox = (VBox) grid.getChildren().get(guessArray.size() + numGuesses * 5);
            Label label = (Label) vbox.getChildren().get(0);
            label.setText("?");
        }
    }

    // get random word from words.txt
    private void generateAnswer() {
        scanner = new Scanner(Objects.requireNonNull(WordleController.class.getClassLoader()
                        .getResourceAsStream("words.txt")));
        ArrayList<String> words = new ArrayList<>();
        while (scanner.hasNext()) {
            words.add(scanner.nextLine());
        }
        Random random = new Random();
        int num = random.nextInt(words.size());
        answerString = words.get(num).toLowerCase();
        for (int i = 0; i < 5; i++) {
            answerArray.add(String.valueOf(answerString.charAt(i)));
        }
    }

    // set initial grid layout at the start of the game
    private void setGrid() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                VBox vbox = new VBox();
                vbox.setAlignment(Pos.CENTER);
                vbox.setStyle("-fx-background-color: #254b68");
                Label label = new Label();
                if (i == 0) {
                    vbox.setStyle("-fx-background-color: #8bbbe1");
                    label.setText("?");
                }
                label.setFont(Font.font("Courier New", 30));
                vbox.getChildren().add(label);
                grid.add(vbox, j, i);
            }
        }
    }

    // compare guess to answer
    private void compareAnswer() {
        for (int i = 0; i < 5; i++) {
            VBox vbox = (VBox) grid.getChildren().get(numGuesses * 5 + i);
            if (guessArray.get(i).equals(answerArray.get(i))) {
                vbox.setStyle("-fx-background-color: #90ce95");
                continue;
            }
            if (answerArray.contains(guessArray.get(i))) {
                vbox.setStyle("-fx-background-color: #dfc988");
            }
        }
    }
}