package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

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

    @FXML
    private Label errors;

    public void setQuestion(Question q) {

    	String [] brokenDown = q.getQuestion().split("\\r?\\n");
    	
    	
    	
        question.setText(brokenDown[0]);
        option1.setText(brokenDown[1]);
        option2.setText(brokenDown[2]);
        option3.setText(brokenDown[3]);
        option4.setText(brokenDown[4]);

        if (q.getAnswer().equals("1")) {
        	correctA.setSelected(true);
        }
        if (q.getAnswer().equals("2")) {
        	correctB.setSelected(true);
        }
        if (q.getAnswer().equals("3")) {
        	correctC.setSelected(true);
        }
        if (q.getAnswer().equals("4")) {
        	correctD.setSelected(true);
        }


    }
    
    
    
    
    
    
    
    
    
    public void add(ActionEvent e) {

        // 1. validation
        if (question.getText().isEmpty()
                || option1.getText().isEmpty()
                || option2.getText().isEmpty()
                || option3.getText().isEmpty()
                || option4.getText().isEmpty()
                || GroupA.getSelectedToggle() == null) {

            System.out.println("Fill everything first");
            if (errors != null) {
                errors.setText("Fill everything first.");
            }
            return;
        }

        try {

            // 2. build question text
            String questionFull =
                    question.getText() + "\n" +
                    "1. " + option1.getText() + "\n" +
                    "2. " + option2.getText() + "\n" +
                    "3. " + option3.getText() + "\n" +
                    "4. " + option4.getText();

            // 3. determine correct answer (cleaner version)
            String answer = "";

            if (GroupA.getSelectedToggle() == correctA) answer = "1";
            else if (GroupA.getSelectedToggle() == correctB) answer = "2";
            else if (GroupA.getSelectedToggle() == correctC) answer = "3";
            else if (GroupA.getSelectedToggle() == correctD) answer = "4";

            // 4. store
            QuizGeneratorController.set.add(new Question(questionFull, answer, "MC"));

            int lastIndex = QuizGeneratorController.set.size() - 1;

            System.out.println(QuizGeneratorController.set.get(lastIndex).getQuestion());
            System.out.println(QuizGeneratorController.set.get(lastIndex).getAnswer());

            // 5. CLEAR INPUTS (IMPORTANT)
            question.clear();
            option1.clear();
            option2.clear();
            option3.clear();
            option4.clear();

            GroupA.selectToggle(null);

            if (errors != null) {
                errors.setText("Question added!");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
