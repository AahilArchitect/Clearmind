package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import rawCode.Question;

public class MCcontroller {

    @FXML
    private VBox rootBox;

    @FXML
    private TextField question;

    @FXML
    private TextField option1;

    @FXML
    private TextField option2;

    @FXML
    private TextField option3;

    @FXML
    private TextField option4;

    @FXML
    private RadioButton correctA;

    @FXML
    private RadioButton correctB;

    @FXML
    private RadioButton correctC;

    @FXML
    private RadioButton correctD;

    @FXML
    private ToggleGroup GroupA;


    public Question getQuestion() {

        String q = question.getText();

        String o1 = option1.getText();
        String o2 = option2.getText();
        String o3 = option3.getText();
        String o4 = option4.getText();

        String a = null;

        if (correctA.isSelected()) a = "1";
        else if (correctB.isSelected()) a = "2";
        else if (correctC.isSelected()) a = "3";
        else if (correctD.isSelected()) a = "4";

        // validation check
        if (q == null || q.trim().isEmpty()
                || o1 == null || o1.trim().isEmpty()
                || o2 == null || o2.trim().isEmpty()
                || o3 == null || o3.trim().isEmpty()
                || o4 == null || o4.trim().isEmpty()
                || a == null) {
            return null;
        }

        return new Question(q.trim(), a, o1, o2, o3, o4, "MC");
    }
	
    
    

    public VBox getView() {
        return rootBox;
    }


    @FXML
    public void back() {

        VBox parent = (VBox) rootBox.getParent();

        if (parent != null) {
            parent.getChildren().remove(rootBox);
        }
    }
	
    
    
}
