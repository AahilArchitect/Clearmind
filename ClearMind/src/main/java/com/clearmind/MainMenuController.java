package com.clearmind;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenuController {

    // This matches fx:id="rootPane" in your FXML
    @FXML
    private StackPane rootPane;

    // This matches fx:id="startButton" (optional but allowed)
    @FXML
    private javafx.scene.control.Button startButton;

    @FXML
    private void openNotes(javafx.event.ActionEvent event) {

        // Get the current window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Open your Notes screen
        Notes notes = new Notes();
        notes.open(stage);
    }
}
