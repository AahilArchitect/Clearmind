package application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import rawCode.Question;



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

		    
		    
		    public Question getQuestion() {

		        String text = question.getText();

		        String answer = null;

		        if (trueA.isSelected()) {
		            answer = "true";
		        } else if (falseA.isSelected()) {
		            answer = "false";
		        }

		        // return null if incomplete
		        if (text == null || text.trim().isEmpty() || answer == null) {
		            return null;
		        }

		        return new Question(text.trim(), answer, "TF");
		    }
			

			
			
			   @FXML
			    public void back() {

			        VBox parent = (VBox) rootBox.getParent();

			        if (parent != null) {
			            parent.getChildren().remove(rootBox);
			        }
			        ArrayList<Question> qs = controller.getQuestions();
			        CreateQuizController.questionControllers.remove(this);

			    }
				

		
 
		    
		    
		  
	
	
}
