package com.clearmind;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller for the main menu. Handles navigation to Notes.
 */
public class MainMenuController {
    @FXML private StackPane rootPane;
    @FXML private Button startButton;

    /** Open the notes screen on Start button click. */
    @FXML
    private void openNotes(javafx.event.ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Notes notes = new Notes();
        notes.open(stage);
    }
}