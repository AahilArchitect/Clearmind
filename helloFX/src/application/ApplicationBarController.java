package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ApplicationBarController {

    @FXML
    private VBox logo;

    @FXML
    private BorderPane editorPane;

    @FXML
    public void initialize() {
        loadLogo();

        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/FXML/Home.fxml")
            );

            editorPane.setCenter(root); 

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Clearmind(ActionEvent e) {
        editorPane.setCenter(null); 
    }

    public void home(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/FXML/Home.fxml")
            );

            editorPane.setCenter(root); 

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void quizzes(ActionEvent e) {
        try {
            Parent root = FXMLLoader.load(
                getClass().getResource("/FXML/QuizzesHome.fxml")
            );

            editorPane.setCenter(root); 

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void flashcards(ActionEvent e) { }

    public void dungeonEscapeGame(ActionEvent e) { }

    public void callender(ActionEvent e) { }

    public void usageTimer(ActionEvent e) { }

    public void notes(ActionEvent e) { }

    private void loadLogo() {
        try {
            Image image = new Image(
                getClass().getResourceAsStream("/FXML/Start_Logo_Improved2.png")
            );

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);

            logo.getChildren().clear();
            logo.getChildren().add(imageView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}