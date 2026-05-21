/**
***************************************************************************************************
@Author: Aahil Bashar
@Last Modified: May 21th, 2026
@Description: This is a main menu system with a Start button. Currently, the start button
starts the notes editor the user is able to type on it
***************************************************************************************************
*/

package com.clearmind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    
 /*
 * Method Name: start
 * Author: Aahil Bashar
 * Creation Date: May 15, 2026
 * Modified Date: May 20, 2026
 * Description: Initializes the primary JavaFX stage, loads the ClearMind logo, and displays the main menu UI from an FXML file.
 * Parameters: Stage stage (the primary window provided by the JavaFX runtime)
 * Return Value: None
 * Data Type: void
 * Dependencies: javafx.stage.Stage, javafx.scene.Scene, javafx.scene.Parent, javafx.fxml.FXMLLoader, javafx.scene.image.Image
 * Throws/Exceptions: Exception (if FXML or resource loading fails)
 */
    @Override
    public void start(Stage stage) throws Exception {

        stage.getIcons().add(
            new Image(getClass().getResourceAsStream("/images/App_Logo.jpg"))
        );

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("ClearMind – Main Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
