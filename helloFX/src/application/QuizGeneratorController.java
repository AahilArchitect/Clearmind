package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import rawCode.Question;

public class QuizGeneratorController {

    public static ArrayList<Question> set = new ArrayList<>();

    @FXML
    public TextField title;

    @FXML
    public HBox previousQuestions;

    @FXML
    private VBox editorPane;

    @FXML
    private Label erorrs;

    public void MCQuestion(ActionEvent e) {
        loadEditorPane("MCQuestion.fxml");
    }

    public void NQuestion(ActionEvent e) {
        loadEditorPane("NQuestion.fxml");
    }

    public void TFQuestion(ActionEvent e) {
        loadEditorPane("TFQuestion.fxml");
    }

    public void aiCreate(ActionEvent e) {
        loadEditorPane("aiHelper.fxml");
    }

    public void loadEditorPane(String fxmlFile) {

        try {

            Parent root = FXMLLoader.load(
                    getClass().getResource(fxmlFile));

            editorPane.getChildren().setAll(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public VBox getEditorPane() {
        return editorPane;
    }
    
    public void Save(ActionEvent e) {

        if (title.getText().isEmpty()) {
            erorrs.setText("Set title.");
            return;
        }

        if (set.size() == 0) {
            erorrs.setText("Must have at least 1 question.");
            return;
        }

        erorrs.setText("Quiz has been created.");

        editorPane.getChildren().clear();
    }

    public void viewEdit(ActionEvent e) {

        if (set.size() == 0) {
            erorrs.setText("No Questions Created.");
            return;
        }

        try {

            previousQuestions.getChildren().clear();

            for (Question q : set) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("PreviousQuestions.fxml"));

                Parent root = loader.load();

                PreviousQuestionController controller =
                        loader.getController();

                controller.setQuestionObject(q);
                controller.setParentController(this);

                previousQuestions.getChildren().add(root);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}