package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rawCode.Quiz;
import rawCode.QuizList;

public class QuizzesHomeController {





    @FXML
    private FlowPane libraryContainer;
    
    @FXML
    public void searchField (ActionEvent e) {
    	
    }
    
    
    @FXML
    public void initialize() {

                
        for (Quiz q : QuizList.getQuizzes()) {

            try {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/FXML/QuizCard.fxml")
                );

                Parent card = loader.load();

                quizCardController controller = loader.getController();
                controller.setQuiz(q);

                libraryContainer.getChildren().add(card);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }

    @FXML
    public void newQuiz(ActionEvent e) {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/FXML/CreateQuiz.fxml")
            );

            VBox createQuizView = loader.load();

            // get root BorderPane (ApplicationBar)
            BorderPane root = (BorderPane) searchField.getScene().getRoot();

            // replace CENTER (NOT just a VBox)
            root.setCenter(createQuizView);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void newFlashcardSet(ActionEvent e) {
        System.out.println("New Flashcard Set");
    }
}