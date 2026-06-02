package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class PreviousQuestionController {

    @FXML
    private Label type;
    
    private Question question;

    @FXML
    private Label questionLabel;

    @FXML
    private Label answer;
    
    public void setQuestionObject(Question question) {
        this.question = question;
    }

    private QuizGeneratorController parentController;

    public void setParentController(
            QuizGeneratorController parentController) {

        this.parentController = parentController;
    }

    public void edit(ActionEvent e) {

        try {

            FXMLLoader loader = null;

            if (question.getType().equals("MC")) {

                loader = new FXMLLoader(
                        getClass().getResource("MCQuestion.fxml"));

            } else if (question.getType().equals("TF")) {

                loader = new FXMLLoader(
                        getClass().getResource("TFQuestion.fxml"));

            } else if (question.getType().equals("N")) {

                loader = new FXMLLoader(
                        getClass().getResource("NQuestion.fxml"));
            }

            Parent root = loader.load(); // MUST LOAD FIRST

            if (question.getType().equals("MC")) {

               MCcontroller controller = loader.getController();
               controller.setQuestion(question);

            } else if (question.getType().equals("TF")) {

               TFController controller = loader.getController();
               controller.setQuestion(question);

            } else if (question.getType().equals("N")) {

                NController controller = loader.getController();
                controller.setQuestion(question);
            }

            parentController.getEditorPane()
                    .getChildren()
                    .setAll(root);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void delete(ActionEvent e) {

        QuizGeneratorController.set.remove(question);

        System.out.println(
                "Deleted question: " + getQuestion());
        parentController.viewEdit(null);

    }

    public void setType(String value) {
        type.setText(value);
    }

    public void setQuestion(String value) {
        questionLabel.setText(value);
    }

    public void setAnswer(String value) {
        answer.setText(value);
    }

    public String getType() {
        return type.getText();
    }

    public String getQuestion() {
        return questionLabel.getText();
    }

    public String getAnswer() {
        return answer.getText();
    }
}