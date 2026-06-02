package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class ApplicationBarController {

    @FXML
    private VBox editorPane;

    @FXML
    private VBox logo;

    @FXML
    public void initialize() {
        loadLogo();
        
        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("Home.fxml")
            );

            editorPane.getChildren().setAll(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    
    public void Clearmind(ActionEvent e) {
        editorPane.getChildren().clear();
    }
    
    public void home(ActionEvent e) {
        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("Home.fxml")
            );

            editorPane.getChildren().setAll(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void quizzesAndFlashcards(ActionEvent e) {

        try {

            Parent root = FXMLLoader.load(
                getClass().getResource("QuizGenerate.fxml")
            );

            editorPane.getChildren().setAll(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void dungeonEscapeGame(ActionEvent e) {

    }

    public void callender(ActionEvent e) {

    }

    public void usageTimer(ActionEvent e) {

    }

    public void notes(ActionEvent e) {

    }
    

    private void loadLogo() {

        try {

            Image image = new Image(
                getClass().getResourceAsStream("Start_Logo_Improved2.png")
            );

            ImageView imageView = new ImageView(image);

            imageView.setFitWidth(150);
            imageView.setPreserveRatio(true);

            logo.getChildren().clear();
            logo.getChildren().add(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}