package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;



public class TFController {


		@FXML
		private VBox rootBox;
		
		 @FXML
		    public TextField question;
		 
		    @FXML
		    private RadioButton trueA;
		    
		    @FXML
		    private RadioButton falseA;
		    
		    @FXML
		    private ToggleGroup Group;
		    
			@FXML
			private Label errors;
			
		    public void setQuestion(Question q) {

		        question.setText(
		                String.valueOf(q.getQuestion()));

		        if (q.getAnswer().equals("true")) {
		        	
		        	trueA.setSelected(true);
		    
		        }
		        else {
			        	trueA.setSelected(false);
		        }
		  
		    }
			
			
			
			public void add(ActionEvent e) {

			    // 1. validation first
			    if (question.getText().isEmpty() || Group.getSelectedToggle() == null) {
			        System.out.println("Fill everything first");
			        if (errors != null) errors.setText("Fill everything first.");
			        return;
			    }

			    try {

			        // 2. get values
			        String questionFull = question.getText();
			        String answerFull = String.valueOf(trueA.isSelected());

			        // 3. store
			        QuizGeneratorController.set.add(
			            new Question(questionFull, answerFull, "TF")
			        );

			        // 4. confirm
			        System.out.println("Added: " + questionFull);

			        // 5. CLEAR INPUTS (THIS IS WHAT YOU FORGOT)
			        question.clear();
			        trueA.setSelected(false);
			        falseA.setSelected(false);
			        Group.selectToggle(null);

			        // 6. optional message
			        if (errors != null) {
			            errors.setText("Question added!");
			        }

			    } catch (Exception ex) {
			        ex.printStackTrace();
			    }
			}

		
 
		    
		    
		  
	
	
}
