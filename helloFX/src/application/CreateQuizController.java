package application;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import rawCode.Question;
import rawCode.Quiz;
import rawCode.QuizList;

public class CreateQuizController {

    @FXML
    private TextField titleField;

    @FXML
    private VBox questionContainer;
    
    @FXML
    private Label errors;

    private ArrayList<Object> questionControllers = new ArrayList<>();
    private ArrayList<Question> questions = new ArrayList<>();
    
    
    public void removeQuestionController(Object controller) {
        questionControllers.remove(controller);
    }

    
    
    public ArrayList<Question> getQuestions() {
        return questions;
    }
    
    @FXML
    public void saveQuiz() {
    	questions.clear();
        if (titleField.getText().isEmpty()) {
            errors.setText("Enter Title First.");
            return;
        }

        for (Object obj : questionControllers) {

            if (obj instanceof TFController tf) {
                Question q = tf.getQuestion();
                if (q == null) {
                    errors.setText("One or more questions are not filled.");
                    return;
                }
                questions.add(q);  
            }
            else if (obj instanceof MCcontroller mc) {
                Question q = mc.getQuestion();
                if (q == null) {
                    errors.setText("One or more questions are not filled.");
                    return;
                }
                questions.add(q);
            }
            else if (obj instanceof NController n) {
                Question q = n.getQuestion();
                if (q == null) {
                    errors.setText("One or more questions are not filled.");
                    return;
                }
                try {
                	Integer.parseInt(q.getAnswer()); 
                	}
                catch (Exception e) {
                    errors.setText("One or more number responses have an invalid input.");
                    return;
                }
                
                questions.add(q);
            }
        }
        Quiz thisQuiz = new Quiz(titleField.getText(), questions);
        QuizList.addQuiz(thisQuiz);

        
        try {
        	
        	if (questions.size()==0) {
                errors.setText("One or more questions are not filled.");
                return;
        	}
            Parent quizzesView = FXMLLoader.load(
                    getClass().getResource("/FXML/QuizzesHome.fxml")
            );

            BorderPane root = (BorderPane) questionContainer.getScene().getRoot();
            root.setCenter(quizzesView);

        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
    


    
    @FXML
    public void exitCreateQuiz() {

        try {
            Parent quizzesView = FXMLLoader.load(
                    getClass().getResource("/FXML/QuizzesHome.fxml")
            );

            BorderPane root = (BorderPane) questionContainer.getScene().getRoot();

            root.setCenter(quizzesView);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // PLACEHOLDERS (you already planned these)
    @FXML public void addTF() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/FXML/TFQuestion.fxml")
            );

            VBox question = loader.load();
            TFController controller = loader.getController();
            controller.setParent(this);
            
           
            questionControllers.add(controller);


            questionContainer.getChildren().add(0, question);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @FXML public void addNumber() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/FXML/NQuestion.fxml")
            );

            VBox question = loader.load();
            NController controller = loader.getController();
            controller.setParent(this);
            
            questionControllers.add(controller);
            

            questionContainer.getChildren().add(0, question);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @FXML public void generateAI() {}
    

    

    @FXML
    public void addMC() {

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/FXML/MCQuestion.fxml")
            );

            VBox question = loader.load();
            MCcontroller controller = loader.getController();
            controller.setParent(this);
            
            questionControllers.add(controller);
            

            questionContainer.getChildren().add(0, question);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
