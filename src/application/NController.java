package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import rawCode.Question;

public class NController {

    @FXML
    private VBox rootBox;

    @FXML
    private TextField question;

    @FXML
    private TextField answer;
    
    
    public Question getQuestion() {

        String q = question.getText();
        String a = answer.getText();

        if (q == null || q.trim().isEmpty()
                || a == null || a.trim().isEmpty()) {
            return null;
        }

        return new Question(q.trim(), a.trim(), "N");
    }

    
    
    @FXML
    public void back() {

        VBox parent = (VBox) rootBox.getParent();

        if (parent != null) {
            parent.getChildren().remove(rootBox);
        }
    }
	
    
}