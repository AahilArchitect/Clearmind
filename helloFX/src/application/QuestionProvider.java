//Example for the game: 
    //QuestionProvider(set) list =new QuestionProvider();
    //list.getQuestion(); print question
	//list.getType(); use this to limit user input. if type is "N" only accenmpt number responses, 
	//if type is "MC" only accept responces between 1-4, if type is "TF" only accept true/false responves
    //allow user input 
    //list.getAsnwer(); print the correct answer 
    //list.checkAnswer(); print you got it right/you got it wrong 
    //after you do checkAnswer, it will automatically update the question difficulty,
    //and next time you call list.getQuestion(); it will be a new question.



//NOT COMPLETE. FOLLOW isntructions above, and this will later be programmed to reflect that. 
package application;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionProvider {

    private ArrayList<Question> questions;
    private int currentIndex = 0;

    public QuestionProvider(ArrayList<Question> questions) {
        this.questions = new ArrayList<>(questions);
    }

    public void randomize() {
        Collections.shuffle(questions);
    }


    public Question Question() {
        if (currentIndex >= questions.size()) {

            return null;
        }
        return questions.get(currentIndex++);

    }
    
    


    public String getType() {
        Question q = questions.get(currentIndex - 1);
        return q.getType();
    }

    
    public boolean checkAnswer(String userInput) {
        Question q = questions.get(currentIndex - 1);

        boolean correct = q.getAnswer().toString().equalsIgnoreCase(userInput);

        if (!correct) {
            organizeByDifficulty(q);
        }

        return correct;
    }

    private void organizeByDifficulty(Question q) {
    }
}
