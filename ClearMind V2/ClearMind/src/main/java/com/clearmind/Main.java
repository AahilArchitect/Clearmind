package com.clearmind;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main application entry. Loads the main menu UI.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/App_Logo.jpg")));

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainMenu.fxml"));

        stage.setScene(new Scene(root, 600, 400));
        stage.setTitle("ClearMind – Main Menu");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}