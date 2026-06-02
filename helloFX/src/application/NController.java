package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class NController {

    @FXML
    private VBox rootBox;

    @FXML
    private TextField question;

    @FXML
    private TextField answer;

    @FXML
    private Label errors;

    
    public void setQuestion(Question q) {

        question.setText(
                String.valueOf(q.getQuestion()));

        answer.setText(
                String.valueOf(q.getAnswer()));
    }
    
    public void add(ActionEvent e) {

        // 1. validation
        if (question.getText().isEmpty() || answer.getText().isEmpty()) {
            System.out.println("Fill everything first");
            if (errors != null) {
                errors.setText("Fill everything first.");
            }

            return;

        }

        try {

            // 2. get values correctly
            String questionFull = question.getText();
            String answerFull = answer.getText(); // FIXED

            // 3. store
            QuizGeneratorController.set.add(
                new Question(questionFull, answerFull, "N")
            );

            // 4. debug output
            int lastIndex = QuizGeneratorController.set.size() - 1;

            System.out.println(QuizGeneratorController.set.get(lastIndex).getQuestion());
            System.out.println(QuizGeneratorController.set.get(lastIndex).getAnswer());

            // 5. clear inputs (GOOD UX)
            question.clear();
            answer.clear();

            if (errors != null) {
                errors.setText("Question added!");
            }

        } catch (NumberFormatException x) {

            System.out.println("Enter Number");

            if (errors != null) {
                errors.setText("Answer must be a number.");
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }
}