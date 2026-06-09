package rawCode;

import java.util.ArrayList;

public class FlashcardSet {
	
	private String title;
    private ArrayList<Flashcard> flashcards = new ArrayList<>();

    public FlashcardSet(String title) {
        this.title = title;
    }

    public String getTitle() {return title;}
    
    public void setTitle(String title) {this.title = title;}

    public ArrayList<Flashcard> getQuestions() {return flashcards;}
    
    public void addQuestion(Flashcard q) {
    	flashcards.add(q);
    }

    public void removeQuestion(Flashcard q) {
    	flashcards.remove(q);
    }
}
