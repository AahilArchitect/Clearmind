package rawCode;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FlashcardSetList {

    private ObservableList<FlashcardSet> flashcardSets = FXCollections.observableArrayList();
    private FlashcardSet activeSet;

    public void addFlashcardSet(FlashcardSet set) {
        flashcardSets.add(set);
    }

    public void removeFlashcardSet(FlashcardSet set) {
        flashcardSets.remove(set);
    }

    public ObservableList<FlashcardSet> getFlashcardSets() {
        return flashcardSets;
    }

    public void setActiveSet(FlashcardSet set) {
        this.activeSet = set;
    }

    public FlashcardSet getActiveSet() {
        return activeSet;
    }

    public FlashcardSet getFlashcardSetByTitle(String title) {
        for (FlashcardSet set : flashcardSets) {
            if (set.getTitle().equalsIgnoreCase(title)) {
                return set;
            }
        }
        return null;
    }
}
