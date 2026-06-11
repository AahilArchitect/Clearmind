package rawCode;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class QuizList {

    private static final ObservableList<Quiz> quizzes =
            FXCollections.observableArrayList();

    private static Quiz activeQuiz;

    
    public static void removeQuiz(Quiz q) {
        quizzes.remove(q);
    }

    public static ObservableList<Quiz> getQuizzes() {
        return quizzes;
    }

    public static void setActiveQuiz(Quiz q) {
        activeQuiz = q;
    }

    public static Quiz getActiveQuiz() {
        return activeQuiz;
    }

    public static void print() {

        for (Quiz q : quizzes) {
            System.out.println(q.getTitle());
        }

    }
    public static void addQuiz(Quiz q) {

        if (!quizzes.contains(q)) {
            quizzes.add(q);
        }

        // selection sort by title
        for (int i = 0; i < quizzes.size(); i++) {

            int minIndex = i;

            for (int j = i + 1; j < quizzes.size(); j++) {

                String titleJ = quizzes.get(j).getTitle();
                String titleMin = quizzes.get(minIndex).getTitle();

                if (titleJ.compareToIgnoreCase(titleMin) < 0) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                Quiz temp = quizzes.get(i);
                quizzes.set(i, quizzes.get(minIndex));
                quizzes.set(minIndex, temp);
            }
        }
    }
    
    public static ArrayList<Quiz> searchQuiz(String search) {
    	
        ArrayList<Quiz> results = new ArrayList<>();

        for (Quiz q : quizzes) {

            if (q.getTitle().toLowerCase().contains(search.toLowerCase())) {
                results.add(q);
            }
        }
        return results;
    }
    
    
    
}