package rawCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class QuestionProvider {

    private Queue<Question> questionQueue = new LinkedList<>();
    private Question currentQuestion;
    private int correct = 0;
    private int attempted = 0;

    public QuestionProvider(ArrayList<Question> questions) {
        ArrayList<Question> copy = new ArrayList<>(questions);
        Collections.shuffle(copy);
        questionQueue.addAll(copy);
    }

    
    public int getCorrect() {
        return correct;
    }

    public int getAttempted() {
        return attempted;
    }
    
    public double getPercent() {
    	return correct/attempted;
    }
    
    public Question getQuestion() {
        if (questionQueue.isEmpty()) {
            currentQuestion = null;
            return null;
        }
        currentQuestion = questionQueue.poll();
        return currentQuestion;
    }

    public String getType() {
        if (currentQuestion == null) return null;
        return currentQuestion.getType();
    }

    public boolean checkAnswer(String userInput) {

        if (currentQuestion == null) return false;

        attempted++;

        boolean correctAnswer =
                currentQuestion.getAnswer().equalsIgnoreCase(userInput);

        if (correctAnswer) {
            correct++;
        } else {
            increaseDifficulty(currentQuestion);
            questionQueue.add(currentQuestion);
        }

        return correctAnswer;
    }

    private void increaseDifficulty(Question q) {
        q.setDifficulty(q.getDifficulty() + 1);
    }

    public boolean hasNext() {
        return !questionQueue.isEmpty();
    }
}