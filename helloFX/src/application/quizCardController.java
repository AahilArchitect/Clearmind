package application;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import rawCode.Quiz;
import rawCode.QuizList;

public class quizCardController {

    @FXML
    private Label titleLabel;

    private Quiz quiz;

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
        titleLabel.setText(quiz.getTitle());
    }

    @FXML
    public void runQuiz() {

        // Make this the active quiz
        QuizList.setActiveQuiz(quiz);

        System.out.println("Running " + quiz.getTitle());

        // TODO: Load quiz playing screen
    }

    @FXML
    public void editQuiz() {

        QuizList.setActiveQuiz(quiz);

        System.out.println("Editing " + quiz.getTitle());

        // TODO: Load CreateQuiz.fxml
        // and populate it with quiz.getQuestions()
    }

    @FXML
    public void deleteQuiz() {

        QuizList.removeQuiz(quiz);
        javafx.scene.Node card = titleLabel.getParent().getParent(); 
        ((javafx.scene.layout.Pane) card.getParent()).getChildren().remove(card);

        System.out.println("Deleted " + quiz.getTitle());

        // TODO: Refresh library
    }
}