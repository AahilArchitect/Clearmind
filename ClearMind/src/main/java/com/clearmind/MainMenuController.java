/**
***************************************************************************************************
@Author: Aahil Bashar
@Last Modified: May 21th, 2026
@Description: Controller class for the ClearMind main menu screen. Handles UI interactions such as navigating to the Notes screen.

***************************************************************************************************
*/
package com.clearmind;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainMenuController {

    // This matches fx:id="rootPane" in the FXML file
    @FXML
    private StackPane rootPane;

    // This matches fx:id="startButton" in the FXML file
    @FXML
    private javafx.scene.control.Button startButton;

    /*
     * Method Name: openNotes
     * Author: Aahil Bashar
     * Creation Date: May 16, 2026
     * Modified Date: May 21, 2026
     * Description: Handles the Start button action. Retrieves the current window
     * 				and opens the Notes screen using the Notes class.
     * Parameters: javafx.event.ActionEvent event (the event triggered by the button click)
     * Return Value: None
     * Data Type: void
     * Dependencies: javafx.event.ActionEvent, javafx.scene.Node, javafx.stage.Stage,
     * 				 com.clearmind.Notes
     * Throws/Exceptions: N/A
     */
    @FXML
    private void openNotes(javafx.event.ActionEvent event) {

        // Get the current window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Open your Notes screen
        Notes notes = new Notes();
        notes.open(stage);
    }
}
